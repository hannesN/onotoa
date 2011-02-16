/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.model.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePathViewerSorter;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DragSource;
import org.eclipse.swt.dnd.DragSourceAdapter;
import org.eclipse.swt.dnd.DragSourceEvent;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorReference;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.ISaveablePart;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.progress.UIJob;

import de.topicmapslab.onotoa.selection.service.IOnotoaSelectionService;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.actions.CloseAction;
import de.topicmapslab.tmcledit.model.actions.CreateDiagramAction;
import de.topicmapslab.tmcledit.model.actions.CreateDomainDiagramAction;
import de.topicmapslab.tmcledit.model.actions.CreateItemIdenifierConstraintAction;
import de.topicmapslab.tmcledit.model.actions.CreateNameConstraintAction;
import de.topicmapslab.tmcledit.model.actions.CreateOccurrenceConstraintAction;
import de.topicmapslab.tmcledit.model.actions.CreateSubjectIdenifierConstraintAction;
import de.topicmapslab.tmcledit.model.actions.CreateSubjectLocatorConstraintAction;
import de.topicmapslab.tmcledit.model.actions.CreateTopicAction;
import de.topicmapslab.tmcledit.model.actions.DeleteDiagramAction;
import de.topicmapslab.tmcledit.model.actions.DeleteTMCLConstruct;
import de.topicmapslab.tmcledit.model.actions.RedoActionWrapper;
import de.topicmapslab.tmcledit.model.actions.RenameAction;
import de.topicmapslab.tmcledit.model.actions.UndoActionWrapper;
import de.topicmapslab.tmcledit.model.actions.UpdateAction;
import de.topicmapslab.tmcledit.model.actions.ValidateAction;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.preferences.PreferenceConstants;
import de.topicmapslab.tmcledit.model.preferences.RecentUsedManager;
import de.topicmapslab.tmcledit.model.util.TMCLEditorInput;
import de.topicmapslab.tmcledit.model.util.extension.ModelViewExtensionInfo;
import de.topicmapslab.tmcledit.model.util.io.FileUtil;
import de.topicmapslab.tmcledit.model.validation.ModelValidator;
import de.topicmapslab.tmcledit.model.validation.ValidationResult;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeName;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeOccurrence;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeSubjectIdentifier;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeSubjectLocator;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeTopic;

/**
 * @author Hannes Niederhausen
 */

public class ModelView extends ViewPart implements IEditingDomainProvider, ISelectionProvider, CommandStackListener,
        ISaveablePart, IResourceChangeListener, ISelectionChangedListener {

	/**
	 * The ID of the view
	 */
	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.ModelView";

	TreeViewer viewer;
	private ModelViewContentProvider contentProvider;
	private Action validationAction;
	private Action doubleClickAction;

	private EditingDomain editingDomain;
	private ValidateJob validateJob = new ValidateJob();
	private File currFile;
	
	/* flag witch indicates if the view should have the global selection, or not */
	private boolean syncView = false;

	private List<ISelectionChangedListener> selectionListeners;
	private ISelection currentSelection;

	// private Map<String, IAction> actionRegistry;
	private ActionRegistry actionRegistry;

	private AdapterImpl dirtyListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Boolean.class) == ModelPackage.FILE__DIRTY) {
				firePropertyChange(PROP_DIRTY);
			}
		}
	};

	private RenameAction renameAction;

	private CreateDiagramAction createDiagramAction;

	private CreateDomainDiagramAction createDomainDiagramAction;

	private CreateTopicAction createTopicAction;

	private DeleteTMCLConstruct deleteConstructAction;

	private DeleteDiagramAction deleteDiagramAction;

	private CreateNameConstraintAction createNameConstraintAction;

	private CreateOccurrenceConstraintAction createOccurrenceConstraintAction;

	private CreateSubjectIdenifierConstraintAction createSubjectIdenifierConstraintAction;
	
	private CreateItemIdenifierConstraintAction createItemIdenifierConstraintAction;

	private CreateSubjectLocatorConstraintAction createSubjectLocatorConstraintAction;

	private IAction saveAction;

	private CloseAction closeAction;

	private RedoActionWrapper redoAction;

	private UndoActionWrapper undoAction;

	/**
	 * Helper method to retrieve the view from the PlatformUI
	 * @return the instance of the {@link ModelView} or <code>null</code>
	 */
	public static final ModelView getInstance() {
		return (ModelView) TmcleditEditPlugin.getPlugin().getWorkbench().getActiveWorkbenchWindow().getActivePage().findView(ID);
	}
	
	/**
	 * The constructor.
	 */
	public ModelView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	@Override
	public void createPartControl(Composite parent) {
		viewer = new TreeViewer(parent, SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL);

		contentProvider = new ModelViewContentProvider(this);
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());

		viewer.setInput(getViewSite());
		viewer.expandToLevel(2);
		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
		        "de.topicmapslab.tmcledit.extensions.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		hookKeyListener();
		contributeToActionBars();

		getSite().setSelectionProvider(this);

		viewer.addSelectionChangedListener(new ISelectionChangedListener() {

			@SuppressWarnings("unchecked")
			public void selectionChanged(SelectionChangedEvent event) {
				if (currFile == null) {
					currentSelection = new StructuredSelection();
					return;
				}

				IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
				
				// notify all selectionListeners in this 
				fireSelectionChanged(sel);
				// getting element for selection service
				
				if (sel.isEmpty()) {
					currentSelection = new StructuredSelection(currFile);
					createTopicAction.setEnabled(false);
				} else {

					AbstractModelViewNode to = (AbstractModelViewNode) sel.getFirstElement();
					createTopicAction.setEnabled(false);
					createDiagramAction.setEnabled(false);
					createDomainDiagramAction.setEnabled(false);
					switch (to.getId()) {
					case TreeObject.DIAGRAMS:
						createDiagramAction.setEnabled(true);
						createDomainDiagramAction.setEnabled(true);
						break;
					}

					if (to instanceof TreeObject) {
						createTopicAction.setKindOfTopicType(((TreeObject) to).getKindOfTopicType());
						if (to.getModel() == null)
							currentSelection = new StructuredSelection(currFile);
					}
					Iterator<Object> it = sel.iterator();
					List<Object> list = new ArrayList<Object>();
					while (it.hasNext()) {
						to = (AbstractModelViewNode) it.next();
						if (to.getModel() != null)
							list.add((Object) to.getModel());
					}
					currentSelection = new StructuredSelection(list);
				}
				TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().setSelection(currentSelection, ModelView.this);
			}

		});
		initDragAndDrop();
	}

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		init(site);

		IPreferenceStore pref = TmcleditEditPlugin.getPlugin().getPreferenceStore();
		String filename = pref.getString(PreferenceConstants.P_LOADFILE);
		if ((filename != null) && (filename.length() > 0) && (filename.endsWith(".ono")) ){
			// check if the parameter is really a file
			java.io.File f = new java.io.File(filename);
			if (!f.exists()) {
				// try to add working directory because filename is a relative path
				String tmp = System.getProperty("user.dir")+"/"+filename;
				f = new java.io.File(tmp);
			}
			if (filename.endsWith(".ono")&&(f.exists())) {
				setFilename(filename, false);
				pref.setValue(PreferenceConstants.P_LOADFILE, "");
				return;
			}
		}

		if (memento == null)
			return;

		String text = memento.getTextData();
		if ((text == null) || ("null".equals(text)))
			return;

		setFilename(text, false);

		for (IMemento children : memento.getChildren("editor")) {
			text = children.getTextData();

			Diagram currDiagram = null;
			for (Diagram d : currFile.getDiagrams()) {
				if (d.getName().equals(text))
					currDiagram = d;
			}
			if (currDiagram != null) {
				String id = (currDiagram instanceof DomainDiagram) ? TmcleditEditPlugin.DOMAIN_DIAGRAMEDITOR_ID
				        : TmcleditEditPlugin.DIAGRAMEDITOR_ID;

				getViewSite().getPage().openEditor(
				        new TMCLEditorInput(currDiagram, getEditingDomain(), getActionRegistry(), this, true), id);
			}
		}
		
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);

		actionRegistry = new ActionRegistry();
		createActions();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
		
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().addSelectionChangedListener(this);
		
	}

	private void createActions() {
		IActionBars actionBars = getViewSite().getActionBars();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();

		undoAction = new UndoActionWrapper();
		undoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		

		redoAction = new RedoActionWrapper();
		redoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		
		closeAction = new CloseAction(this);
		addPropertyListener(closeAction);
		
		saveAction = ActionFactory.SAVE.create(getViewSite().getWorkbenchWindow());
		
		
		
		
		actionRegistry.registerAction(saveAction);
		actionRegistry.registerAction(closeAction);
		actionRegistry.registerAction(undoAction);
		actionRegistry.registerAction(redoAction);

		actionBars.updateActionBars();
		
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
		actionBars.setGlobalActionHandler(ActionFactory.CLOSE.getId(), closeAction);
		
		undoAction.setEnabled(false);
		redoAction.setEnabled(false);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	@Override
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public EditingDomain getEditingDomain() {
		if (editingDomain == null) {
			editingDomain = WorkspaceEditingDomainFactory.INSTANCE.createEditingDomain();
			IAction action = actionRegistry.getAction(ActionFactory.UNDO.getId());
			((UndoAction) action).setEditingDomain(editingDomain);

			action = actionRegistry.getAction(ActionFactory.REDO.getId());
			((RedoAction) action).setEditingDomain(editingDomain);
			editingDomain.getCommandStack().addCommandStackListener(this);
			updateActions();
		}

		return editingDomain;
	}

	/**
	 * 
	 * @return the {@link CommandStack} used to execute the modification operations
	 */
	public CommandStack getCommandStack() {
		return getEditingDomain().getCommandStack();
	}

	private List<ISelectionChangedListener> getSelectionListeners() {
		if (selectionListeners == null)
			return Collections.emptyList();
		return selectionListeners;
    }
	
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (selectionListeners == null)
			selectionListeners = new ArrayList<ISelectionChangedListener>();
		selectionListeners.add(listener);
	}

	public ISelection getSelection() {
		if (currentSelection == null)
			if (currFile != null)
				currentSelection = new StructuredSelection(currFile);
			else
				currentSelection = new StructuredSelection();
		return currentSelection;
	}

	/**
	 * 
	 * @return the current {@link TopicMapSchema} or <code>null</code> if not loaded
	 * @deprecated Use the {@link IOnotoaSelectionService} instead
	 */
	public TopicMapSchema getCurrentTopicMapSchema() {
		File currFile = TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().getOnotoaFile();
		if (currFile != null)
			return currFile.getTopicMapSchema();
		return null;
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		if (selectionListeners != null)
			selectionListeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		if (!syncView)
			return;
		
		if (viewer != null) {
			if (selection.isEmpty())
				viewer.setSelection(selection);
			else {
				List<AbstractModelViewNode> nodes = new ArrayList<AbstractModelViewNode>();
				Iterator<?> it = ((IStructuredSelection)selection).iterator();
				while (it.hasNext()) {
					IContentProvider cp = viewer.getContentProvider();
					AbstractModelViewNode n = ((ModelViewContentProvider) cp).findNodeFor(it.next());
					if (n!=null)
						nodes.add(n);
				}
				StructuredSelection nodeSel = new StructuredSelection(nodes);
				viewer.setSelection(nodeSel);
				fireSelectionChanged(nodeSel);
			}
		}
		currentSelection = selection;
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().setSelection(currentSelection, ModelView.this);
	}

	/**
	 * Sets the filename and loads the file 
	 * @param filename the name of a new file
	 * @param newFile flag if the file is new or should be loaded
	 */
	public void setFilename(String filename, boolean newFile) {
		setFilename(filename, newFile, null);
		if (filename!=null&&filename.length()>0)
			RecentUsedManager.addFile(filename);
	}
	
	/**
	 * Sets the filename and loads the file 
	 * @param filename the name of a new file
	 * @param newFile flag if the file is new or should be loaded
	 * @param newOnotoaFile a file which is already opened and set to this {@link ModelView}
	 */
	// TODO refactor me PLEEEEAAASSE!!!
	public void setFilename(String filename, boolean newFile, File newOnotoaFile) {
		IViewSite viewSite = getViewSite();
		if (viewSite != null) {

			IWorkbenchPage page = viewSite.getPage();
			if (currFile != null) {
				// if ( (!currFile.isDirty()) &&
				// (currFile.getFilename().equals(filename))
				// return;

				currFile.eAdapters().remove(dirtyListener);

				for (IEditorReference ref : page.getEditorReferences()) {
					try {
						if (ref.getEditorInput() instanceof TMCLEditorInput) {
							IEditorPart part = ref.getEditor(false);
							page.closeEditor(part, false);
						}
					} catch (PartInitException e) {
						throw new RuntimeException(e);
					}
				}
			}

			checkSavedState();
		}
		updateTitle(filename);

		if (contentProvider != null)
			contentProvider.uninitialize();

		if (editingDomain != null)
			editingDomain.getCommandStack().removeCommandStackListener(this);

		editingDomain = null; // clear it for new creation in getter
		if (filename != null) {
			if (!newFile) {
				try {
					currFile = FileUtil.loadFile(filename);
				} catch (Exception e) {
					TmcleditEditPlugin.getPlugin().log(e);
					currFile = null;
				}
			} else if (newOnotoaFile!=null){
				currFile = newOnotoaFile;
			} else{
				currFile = ModelFactory.eINSTANCE.createFile();
				currFile.setTopicMapSchema(ModelFactory.eINSTANCE.createTopicMapSchema());
				if (filename.length() > 0)
					currFile.setFilename(filename);
			}
			currFile.eAdapters().add(dirtyListener);

			// initialize indexer
			ModelIndexer.createInstance(currFile);
		} else {
			currFile = null;
		}
		
		// tell the selection service about the new file
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().setOnotoaFile(currFile);
		
		
		if (contentProvider != null)
			contentProvider.initialize();

		if (currFile != null)
			setSelection(new StructuredSelection(currFile));
		else 
			setSelection(new StructuredSelection());
		
		firePropertyChange(PROP_DIRTY);

		if (viewer != null) {
			viewer.refresh();
			viewer.expandToLevel(2);
		}
		
		// update actions of modelview
		updateModelViewActions();

//		// inform NotesView
//		if (viewSite != null) {
//			IWorkbenchWindow workbenchWindow = viewSite.getWorkbenchWindow();
//			IWorkbenchPage activePage = workbenchWindow.getActivePage();
//			if (activePage != null) {
//				IViewPart notesView = activePage.findView(NotesView.ID);
//				if (notesView != null)
//					((NotesView) notesView).update();
//			}
//		}
	
	}

		
	/**
	 * Refreshes the {@link TreeViewer}. If recreate is <code>true</code> the content provider is reinitialized, else
	 * the view will just refresh.
	 * @param recreate flag whether the contentProvider should be reinitialzed
	 */
	public void refreshView(boolean recreate) {
		if (recreate) {
			contentProvider.initialize();
		} else {
			getViewer().refresh();
		}
		viewer.refresh();
		viewer.expandToLevel(2);
	}

	
	private void updateActions() {
		@SuppressWarnings("unchecked")
        Iterator<IAction> it = actionRegistry.getActions();
		while (it.hasNext()) {
			IAction a = it.next();
			if (a instanceof UpdateAction)
				((UpdateAction) a).update();
		}
		// updating export selection
		
		List<ModelViewExtensionInfo> mveInfos = TmcleditEditPlugin.getExtensionManager().getModelViewExtensionInfos();
		for (ModelViewExtensionInfo mve : mveInfos) {
			for (UpdateAction a : mve.getProvider().getActions()) {
				a.update();
			}
		}
		
		getViewSite().getActionBars().getGlobalActionHandler(ActionFactory.EXPORT.getId());
	}

	/**
	 * 
	 * @return the {@link ActionRegistry} of this view containing all actions of the context menu
	 */
	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	@Override
	public Object getAdapter(@SuppressWarnings("rawtypes") Class adapter) {
		if (adapter == ActionRegistry.class)
			return getActionRegistry();

		if (adapter == File.class)
			return currFile;

		if (adapter == CommandStack.class)
			return getEditingDomain().getCommandStack();

		return super.getAdapter(adapter);
	}

	/**
	 * 
	 * @return the {@link TreeViewer} representing the model
	 */
	public TreeViewer getViewer() {
		return viewer;
	}

	public void commandStackChanged(EventObject event) {
		updateActions();
		WorkspaceCommandStackImpl cmdStack = (WorkspaceCommandStackImpl) getEditingDomain().getCommandStack();
		currFile.setDirty(cmdStack.isSaveNeeded());

		if (!validateJob.isRunning())
			validateJob.schedule();
	}

	/**
	 * Closes the model
	 */
	public void close() {
		setFilename(null, false);
		IWorkbenchPage activePage = getViewSite().getWorkbenchWindow().getActivePage();
		for (IEditorReference ref : activePage.getEditorReferences()) {
			if (ref.getId().equals(TmcleditEditPlugin.DIAGRAMEDITOR_ID)) {
				activePage.closeEditor(ref.getEditor(false), false);
			}
		}
		
		// notify extensions
    	List<ModelViewExtensionInfo> mveInfos = TmcleditEditPlugin.getExtensionManager().getModelViewExtensionInfos();
    	for (ModelViewExtensionInfo mve : mveInfos) {
    			mve.getProvider().close();
    	}
		
    	TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().setSelection(new StructuredSelection(), this);
    	
		firePropertyChange(PROP_DIRTY);
		updateActions();
		viewer.refresh();
		setSelection(new StructuredSelection());
		
		actionRegistry.getAction(ActionFactory.UNDO.getId()).setEnabled(false);
		actionRegistry.getAction(ActionFactory.REDO.getId()).setEnabled(false);
		
	}

	@Override
	public void saveState(IMemento memento) {
		String text = "null";

	
		if ((currFile != null) && (currFile.getFilename() != null)) {
			java.io.File file = new java.io.File(currFile.getFilename());
			if (file.exists())
				text = currFile.getFilename();
			file = null;
		} else {
			return;
		}

		memento.putTextData(text);
		int i = 0;
		for (IEditorReference ref : getViewSite().getPage().getEditorReferences()) {
			IEditorPart part = ref.getEditor(false);
			if (part != null) {
				if ((ref.getId().equals(TmcleditEditPlugin.DIAGRAMEDITOR_ID))
				        || (ref.getId().equals(TmcleditEditPlugin.DOMAIN_DIAGRAMEDITOR_ID))) {
					i++;
					TMCLEditorInput ei = (TMCLEditorInput) part.getEditorInput();
					IMemento partChild = memento.createChild("editor");
					partChild.putTextData(ei.getDiagram().getName());
				}
			}
		}
	}

	@Override
	public void dispose() {
		ResourcesPlugin.getWorkspace().removeResourceChangeListener(this);
		TmcleditEditPlugin.getPlugin().getOnotoaSelectionService().removeSelectionChangedListener(this);
		super.dispose();
	}

	public void doSave(IProgressMonitor monitor) {
		try {
			if (currFile.getFilename() == null) {
				doSaveAs();
				return;
			}

			FileUtil.saveFile((File) currFile, getEditingDomain());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void doSaveAs() {
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setText("Save As..");
		List<String> filesList = RecentUsedManager.getFilesList();
		if (!filesList.isEmpty()) {
			String file = filesList.get(0);
			int idx = file.lastIndexOf("/");
			if (idx!=-1)
				dlg.setFilterPath(file.substring(0, idx));
		}
		String result = dlg.open();
		if (result != null) {
			if ((!result.endsWith(".ono")) && (!result.endsWith(".tmcl")))
				result += ".ono";
			currFile.setFilename(result);
			updateTitle(currFile.getFilename());
			RecentUsedManager.addFile(currFile.getFilename());
			doSave(new NullProgressMonitor());

		}
	}

	public boolean isDirty() {
		if (currFile == null)
			return false;
		return currFile.isDirty();
	}

	public boolean isSaveAsAllowed() {
		return (currFile != null);
	}

	public boolean isSaveOnCloseNeeded() {
		return isDirty();
	}

	@Override
	public void setPartProperty(String key, String value) {
		if ("newfilename".equals(key))
			setFilename(value, true);
		else if ("filename".equals(key))
			setFilename(value, false);
		else
			super.setPartProperty(key, value);
	}

	public void resourceChanged(IResourceChangeEvent event) {
		if (currFile == null)
			return;
		String filename = currFile.getFilename();
		IWorkspace workspace = ResourcesPlugin.getWorkspace();
		IFile file = workspace.getRoot().getFileForLocation(new Path(filename));
		assert (file != null);
		IProject project = file.getProject();
		if (project == null)
			return;
		String projectPath = project.getLocation().toOSString();
		if (filename.startsWith(projectPath)) {
			filename = project.getName() + "/" + filename.substring(projectPath.length() + 1);

		}

		IResourceDelta delta = event.getDelta().findMember(new Path(filename));
		if (delta == null)
			return;

		if ((delta.getFlags() & IResourceDelta.MOVED_TO) != 0) {
			String newPath = workspace.getRoot().getLocation().toOSString() + delta.getMovedToPath().toOSString();
			currFile.setFilename(newPath);
		}
	}

	private void initDragAndDrop() {
    	DragSource dragSource = new DragSource(viewer.getTree(), DND.DROP_COPY);
    	dragSource.setTransfer(new Transfer[] { TextTransfer.getInstance() });
    	dragSource.addDragListener(new DragSourceAdapter() {
    
    		@Override
    		public void dragStart(DragSourceEvent event) {
    
    			IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
    			StringBuffer tmp = prepareTopicTypes(sel);
    
    			if (tmp.length() > 0) {
    				event.doit = true;
    				event.data = tmp.toString();
    			} else {
    				event.doit = false;
    			}
    
    		}
    
    		@Override
    		public void dragSetData(DragSourceEvent event) {
    			if (viewer == null)
    				return;
    
    			IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
    
    			StringBuffer tmp = prepareTopicTypes(sel);
    			if (tmp.length() > 0) {
    				event.data = tmp.toString();
    			} else {
    				event.data = null;
    			}
    		}
    
    		@SuppressWarnings("unchecked")
    		private StringBuffer prepareTopicTypes(IStructuredSelection sel) {
    			// concat every model string and ignore other nodes..
    			StringBuffer tmp = new StringBuffer();
    
    			if (sel.size() == 1) {
    				TreeObject selObj = (TreeObject) sel.getFirstElement();
    
    				if ((selObj.getModel() instanceof AssociationTypeConstraint)
    				        || (selObj.getModel() instanceof TopicType)) {
    					tmp.append(selObj.getModel().toString());
    				}
    			} else {
    				Iterator<Object> it = sel.iterator();
    				while (it.hasNext()) {
    					Object obj = it.next();
    					if (obj instanceof TreeTopic) {
    						tmp.append(((TreeTopic) obj).getModel().toString());
    						tmp.append("--_--");
    					}
    				}
    			}
    			return tmp;
    		}
    
    	});
    }

	/**
	 * Notifies the selection service that a viewer has a new selection
	 */
	private void fireSelectionChanged(ISelection selection) {
    	SelectionChangedEvent e = new SelectionChangedEvent(this, selection);
    	for (ISelectionChangedListener l : getSelectionListeners()) {
    		l.selectionChanged(e);
    	}
    }
	

	private void updateModelViewActions() {
    	// the actions aren't initialized therefore we return
    	if (createDiagramAction==null)
    		return;
    	
    	boolean enabled = (currFile!=null);
    	
    	createDiagramAction.setEnabled(enabled);
    	createDomainDiagramAction.setEnabled(enabled);
    	validationAction.setEnabled(enabled);
    }

	private void updateTitle(String filename) {
    	if (filename != null) {
    		if (filename.length() == 0)
    			filename = "New File...";
    		getSite().getShell().setText("Onotoa - " + filename);
    	} else {
    		getSite().getShell().setText("Onotoa");
    	}
    }

	private void checkSavedState() {
    	if (currFile != null) {
    		currFile.eAdapters().remove(dirtyListener);
    		WorkspaceCommandStackImpl cmdStack = (WorkspaceCommandStackImpl) getEditingDomain().getCommandStack();
    		if (cmdStack.isSaveNeeded()) {
    			if (MessageDialog.openQuestion(getViewSite().getShell(), "Unsaved model",
    			        "Your model is not saved. Do you want to save now?")) {
    				doSave(null);
    			}
    		}
    	}
    }

	private void hookContextMenu() {
    	MenuManager menuMgr = new MenuManager("#PopupMenu");
    	menuMgr.setRemoveAllWhenShown(true);
    	menuMgr.addMenuListener(new IMenuListener() {
    		public void menuAboutToShow(IMenuManager manager) {
    			ModelView.this.fillContextMenu(manager);
    		}
    	});
    	Menu menu = menuMgr.createContextMenu(viewer.getControl());
    	viewer.getControl().setMenu(menu);
    	getSite().registerContextMenu(menuMgr, viewer);
    }

	private void contributeToActionBars() {
    	IActionBars bars = getViewSite().getActionBars();
    	fillLocalPullDown(bars.getMenuManager());
    	fillLocalToolBar(bars.getToolBarManager());
    }

	private void fillLocalPullDown(IMenuManager manager) {
    	manager.add(createDiagramAction);
    	manager.add(createDomainDiagramAction);
    	manager.add(validationAction);
    
    	manager.add(new Separator());
    }

	private void fillContextMenu(IMenuManager manager) {
    	if (currFile == null) {
    		return;
    	}
    
    	if (createDiagramAction.isEnabled())
    		manager.add(createDiagramAction);
    
    	if (createDomainDiagramAction.isEnabled())
    		manager.add(createDomainDiagramAction);
    
    	if (createTopicAction.isEnabled())
    		manager.add(createTopicAction);
    
    	if (renameAction.isEnabled()) {
    		manager.add(renameAction);
    	}
    
    	if (deleteDiagramAction.isEnabled())
    		manager.add(deleteDiagramAction);
    
    	if (deleteConstructAction.isEnabled())
    		manager.add(deleteConstructAction);
    
    	manager.add(new Separator());
    
    	if (createNameConstraintAction.isEnabled())
    		manager.add(createNameConstraintAction);
    	if (createOccurrenceConstraintAction.isEnabled())
    		manager.add(createOccurrenceConstraintAction);
    	if (createItemIdenifierConstraintAction.isEnabled())
    		manager.add(createItemIdenifierConstraintAction);
    	if (createSubjectIdenifierConstraintAction.isEnabled())
    		manager.add(createSubjectIdenifierConstraintAction);
    	if (createSubjectLocatorConstraintAction.isEnabled())
    		manager.add(createSubjectLocatorConstraintAction);
    
    	// add enabled actions from extensions
    	List<ModelViewExtensionInfo> mveInfos = TmcleditEditPlugin.getExtensionManager().getModelViewExtensionInfos();
    	for (ModelViewExtensionInfo mve : mveInfos) {
    		for (UpdateAction a : mve.getProvider().getActions()) {
    			if (a.isEnabled())
    				manager.add(a);
    		}
    	}
    	
    }

	private void fillLocalToolBar(IToolBarManager manager) {
    	validationAction.setEnabled(currFile!=null);
    	manager.add(validationAction);
    	manager.add(new SyncAction("Syncronize", IAction.AS_CHECK_BOX, syncView));
    	manager.add(new Separator());
    	manager.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
    
    }

	private void makeActions() {
    	validationAction = new ValidateAction(getSite());
    
    	doubleClickAction = new Action() {
    		@Override
    		public void run() {
    			ISelection selection = viewer.getSelection();
    			Object obj = ((IStructuredSelection) selection).getFirstElement();
    			((AbstractModelViewNode) obj).handleDoubleClick();
    		}
    	};
    	deleteDiagramAction = new DeleteDiagramAction(this);
    	deleteConstructAction = new DeleteTMCLConstruct(this);
    	createDiagramAction = new CreateDiagramAction(this);
    	createDomainDiagramAction = new CreateDomainDiagramAction(this);
    	createTopicAction = new CreateTopicAction(this);
    	renameAction = new RenameAction(this);
    
    	createNameConstraintAction = new CreateNameConstraintAction(this);
    	createOccurrenceConstraintAction = new CreateOccurrenceConstraintAction(this);
    	createItemIdenifierConstraintAction = new CreateItemIdenifierConstraintAction(this);
    	createSubjectIdenifierConstraintAction = new CreateSubjectIdenifierConstraintAction(this);
    	createSubjectLocatorConstraintAction = new CreateSubjectLocatorConstraintAction(this);
    
    	boolean enabled = (currFile!=null);
    	createDiagramAction.setEnabled(enabled);
    	createDomainDiagramAction.setEnabled(enabled);
    	validationAction.setEnabled(enabled);
    	
    	// add enabled actions from extensions
    	List<ModelViewExtensionInfo> mveInfos = TmcleditEditPlugin.getExtensionManager().getModelViewExtensionInfos();
    	for (ModelViewExtensionInfo mve : mveInfos) {
    			mve.getProvider().createActions(this);
    	}
    }

	private void hookDoubleClickAction() {
    	viewer.addDoubleClickListener(new IDoubleClickListener() {
    		public void doubleClick(DoubleClickEvent event) {
    			doubleClickAction.run();
    		}
    	});
    }

	private void hookKeyListener() {
    	viewer.getTree().addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
    		@Override
    		public void keyReleased(KeyEvent e) {
    			IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
				if (sel.isEmpty())
					return;

				AbstractModelViewNode obj = (AbstractModelViewNode) sel.getFirstElement();
				
    			if (e.keyCode == SWT.F2) {
					obj.handleRename();
				} else if (e.keyCode == SWT.ARROW_RIGHT) {
					viewer.expandToLevel(obj, 1);
				} else if (e.keyCode == SWT.ARROW_LEFT) {
					viewer.collapseToLevel(obj, AbstractTreeViewer.ALL_LEVELS);
				} else if (e.keyCode == SWT.DEL) {
					if (deleteConstructAction.isEnabled()) {
						deleteConstructAction.run();
					} else if (deleteDiagramAction.isEnabled()) {
						deleteDiagramAction.run();
					}
					
					
				}
				e.doit = false;				
    		}
    	});
    }

	private final class SyncAction extends Action {
	    private SyncAction(String text, int style, boolean checked) {
		    super(text, style);
		    setChecked(checked);
	    }

	    @Override
	    public void run() {
	        syncView = this.isChecked();
	    }
	    
	    @Override
	    public ImageDescriptor getImageDescriptor() {
	    	return PlatformUI.getWorkbench().getSharedImages().getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED);
	    }
    }

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(Object obj) {
			return ((AbstractModelViewNode) obj).getName();
		}

		@Override
		public Image getImage(Object obj) {
			return ((AbstractModelViewNode) obj).getImage();
		}
	}

	class NameSorter extends TreePathViewerSorter {
		@Override
		public int category(Object element) {
			if (element instanceof TreeSubjectIdentifier) {
				return 4;
			}
			if (element instanceof TreeSubjectLocator) {
				return 5;
			}
			if (element instanceof TreeOccurrence) {
				return 3;
			}
			if (element instanceof TreeName) {
				return 2;
			}

			// extensions to the topic map schema should be the first nodes,
			// else the extension nodes are at the and of the list
			AbstractModelViewNode n = (AbstractModelViewNode) element;
			if (!(n instanceof TreeObject)) {
				if (n.getParent().getModel() instanceof TopicMapSchema)
					return 0;
				else
					return 6;
			}
			
			return 1;
		}
	}

	class ValidateJob extends UIJob {
		private boolean running = false;

		private FeedbackRunnable feedBackRunnable = new FeedbackRunnable();

		public ValidateJob() {
			super("Validate...");
		}

		@Override
		public IStatus runInUIThread(IProgressMonitor monitor) {
			if (currFile == null)
				return Status.OK_STATUS;

			running = true;

			IWorkbenchWindow activeWorkbenchWindow = getViewSite().getWorkbenchWindow();
			IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();

			File file = currFile;

			ModelValidator validator = new ModelValidator(file);

			List<ValidationResult> list = validator.validate(getEditingDomain().getCommandStack());

			try {
				ValidationErrorView vew = (ValidationErrorView) activePage.findView(ValidationErrorView.ID);

				if (list.size() > 0) {
					if (vew == null)
						vew = (ValidationErrorView) activePage.showView(ValidationErrorView.ID);
				}
				if (vew != null) {
					feedBackRunnable.setList(list);
					feedBackRunnable.setView(vew);
					getViewSite().getWorkbenchWindow().getShell().getDisplay().syncExec(feedBackRunnable);
				}

			} catch (PartInitException e) {
				running = false;
				throw new RuntimeException(e);
			} finally {
				running = false;
			}

			return Status.OK_STATUS;
		}

		public boolean isRunning() {
			return running;
		}

		private class FeedbackRunnable implements Runnable {
			private List<ValidationResult> list;
			private ValidationErrorView vew;

			public void run() {
				vew.setValidationResults(list);
			}

			public void setList(List<ValidationResult> list) {
				this.list = list;
			}

			public void setView(ValidationErrorView vew) {
				this.vew = vew;
			}

		}

	}

	public void selectionChanged(SelectionChangedEvent event) {
		if (event.getSelectionProvider()!=this)
			setSelection(event.getSelection());
    }

}
