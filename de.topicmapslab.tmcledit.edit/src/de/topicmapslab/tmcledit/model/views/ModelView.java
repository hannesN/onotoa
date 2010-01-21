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
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.emf.workspace.impl.WorkspaceCommandStackImpl;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredContentProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TreePathViewerSorter;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
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

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.OnoObject;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.actions.CloseAction;
import de.topicmapslab.tmcledit.model.actions.CreateDiagramAction;
import de.topicmapslab.tmcledit.model.actions.CreateDomainDiagramAction;
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
import de.topicmapslab.tmcledit.model.util.TMCLEditorInput;
import de.topicmapslab.tmcledit.model.util.io.FileUtil;
import de.topicmapslab.tmcledit.model.validation.ModelValidator;
import de.topicmapslab.tmcledit.model.validation.ValidationResult;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeAssocConstraint;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeDiagram;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeName;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeOccurrence;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeParent;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeSubjectIdentifier;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeSubjectLocator;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeTopic;

/**
 * @author Hannes Niederhausen
 */

public class ModelView extends ViewPart implements IEditingDomainProvider, ISelectionProvider, CommandStackListener,
        ISaveablePart, IResourceChangeListener {

	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.ModelView";

	public static final int MODEL_LOADED = 12345;
	
	private TreeViewer viewer;
	private ViewContentProvider contentProvider;
	private Action validationAction;
	private Action doubleClickAction;

	private EditingDomain editingDomain;
	private ValidateJob validateJob = new ValidateJob();
	private File currFile;

	private List<ISelectionChangedListener> listeners = Collections.emptyList();
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

	private DeleteTMCLConstruct deleteTopicTypeAction;

	private DeleteDiagramAction deleteDiagramAction;

	private CreateNameConstraintAction createNameConstraintAction;

	private CreateOccurrenceConstraintAction createOccurrenceConstraintAction;

	private CreateSubjectIdenifierConstraintAction createSubjectIdenifierConstraintAction;

	private CreateSubjectLocatorConstraintAction createSubjectLocatorConstraintAction;

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

		contentProvider = new ViewContentProvider();
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
				if (sel.isEmpty()) {
					currentSelection = new StructuredSelection(currFile);
					createTopicAction.setEnabled(false);
				} else {
					
					TreeObject to = (TreeObject) sel.getFirstElement();
					createTopicAction.setEnabled(false);
					createDiagramAction.setEnabled(false);
					createDomainDiagramAction.setEnabled(false);
					switch (to.getId()) {
					case TreeObject.DIAGRAMS:
						createDiagramAction.setEnabled(true);
						createDomainDiagramAction.setEnabled(true);
						break;
					}

					createTopicAction.setKindOfTopicType(to.getKindOfTopicType());
					if (to.getModel() == null)
						currentSelection = new StructuredSelection(currFile);
					
					Iterator<Object> it = sel.iterator();
					List<OnoObject> list = new ArrayList<OnoObject>();
					while (it.hasNext()) {
						to = (TreeObject) it.next();
						if (to.getModel()!=null)
							list.add((OnoObject) to.getModel());
					}
					currentSelection = new StructuredSelection(list);
				}
				fireSelectionChanged();

			}

		});
		initDragAndDrop();
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

	@Override
	public void init(IViewSite site, IMemento memento) throws PartInitException {
		init(site);
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
				String id = (currDiagram instanceof DomainDiagram) ? TmcleditEditPlugin.DOMAIN_DIAGRAMEDITOR_ID : TmcleditEditPlugin.DIAGRAMEDITOR_ID;
				
				getViewSite().getPage().openEditor(
				        new TMCLEditorInput(currDiagram, getEditingDomain(), getActionRegistry(), true), id);
			}
		}

	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);

		actionRegistry = new ActionRegistry();
		createActions();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	public void createActions() {
		IActionBars actionBars = getViewSite().getActionBars();
		ISharedImages sharedImages = PlatformUI.getWorkbench().getSharedImages();

		UndoActionWrapper undoAction = new UndoActionWrapper();
		undoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);

		RedoActionWrapper redoAction = new RedoActionWrapper();
		redoAction.setImageDescriptor(sharedImages.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);

		CloseAction closeAction = new CloseAction(this);
		addPropertyListener(closeAction);
		actionBars.setGlobalActionHandler(ActionFactory.CLOSE.getId(), closeAction);

		IAction a = ActionFactory.SAVE.create(getViewSite().getWorkbenchWindow());
		actionRegistry.registerAction(a);

		actionRegistry.registerAction(closeAction);
		actionRegistry.registerAction(undoAction);
		actionRegistry.registerAction(redoAction);

		actionBars.updateActionBars();
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
		if (currFile==null) {
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
		
		if (deleteTopicTypeAction.isEnabled())
			manager.add(deleteTopicTypeAction);
		
		
		manager.add(new Separator());
		
		if (createNameConstraintAction.isEnabled())
			manager.add(createNameConstraintAction);
		if (createOccurrenceConstraintAction.isEnabled())
			manager.add(createOccurrenceConstraintAction);
		if (createSubjectIdenifierConstraintAction.isEnabled())
			manager.add(createSubjectIdenifierConstraintAction);
		if (createSubjectLocatorConstraintAction.isEnabled())
			manager.add(createSubjectLocatorConstraintAction);
		

		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(validationAction);
		manager.add(new Separator());

	}

	private void makeActions() {
		validationAction = new ValidateAction(getSite());

		doubleClickAction = new Action() {
			@Override
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection).getFirstElement();
				((TreeObject) obj).handleDoubleClick();
			}
		};
		deleteDiagramAction = new DeleteDiagramAction(this);
		deleteTopicTypeAction = new DeleteTMCLConstruct(this);
		createDiagramAction = new CreateDiagramAction(this);
		createDomainDiagramAction = new CreateDomainDiagramAction(this);
		createTopicAction = new CreateTopicAction(this);
		renameAction = new RenameAction(this);
		
		createNameConstraintAction = new CreateNameConstraintAction(this);
		createOccurrenceConstraintAction = new CreateOccurrenceConstraintAction(this);
		createSubjectIdenifierConstraintAction = new CreateSubjectIdenifierConstraintAction(this);
		createSubjectLocatorConstraintAction = new CreateSubjectLocatorConstraintAction(this);

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
				if (e.keyCode != SWT.F2)
					return;

				IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
				if (sel.isEmpty())
					return;

				TreeObject obj = (TreeObject) sel.getFirstElement();
				obj.handleRename();
				e.doit = false;
				return;
			}
		});
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

	public CommandStack getCommandStack() {
		return getEditingDomain().getCommandStack();
	}
	
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners == Collections.EMPTY_LIST)
			listeners = new ArrayList<ISelectionChangedListener>();
		listeners.add(listener);
	}

	public ISelection getSelection() {
		if (currentSelection == null)
			if (currFile != null)
				currentSelection = new StructuredSelection(currFile);
			else
				currentSelection = new StructuredSelection();
		return currentSelection;
	}

	public TopicMapSchema getCurrentTopicMapSchema() {
		if (currFile != null)
			return currFile.getTopicMapSchema();
		return null;
	}

	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners == Collections.EMPTY_LIST)
			listeners = new ArrayList<ISelectionChangedListener>();
		listeners.remove(listener);
	}

	public void setSelection(ISelection selection) {
		if (viewer != null)
			viewer.setSelection(selection);
		currentSelection = selection;
		fireSelectionChanged();
	}

	private void fireSelectionChanged() {
		SelectionChangedEvent e = new SelectionChangedEvent(this, currentSelection);
		for (ISelectionChangedListener l : listeners) {
			l.selectionChanged(e);
		}
	}

	public void setFilename(String filename, boolean newFile) {
		if (getViewSite() != null) {

			IWorkbenchPage page = getViewSite().getPage();
			if (currFile != null) {
//				if ( (!currFile.isDirty()) && (currFile.getFilename().equals(filename))
//					return;

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
			} else {
				currFile = ModelFactory.eINSTANCE.createFile();
				currFile.setTopicMapSchema(ModelFactory.eINSTANCE.createTopicMapSchema());
				currFile.setFilename(filename);
			}
			currFile.eAdapters().add(dirtyListener);

			// initialize indexer
			ModelIndexer.createInstance(currFile);
		} else {
			currFile = null;
		}
		if (contentProvider != null)
			contentProvider.initialize();

		if (currFile != null)
			setSelection(new StructuredSelection(currFile));

		setSelection(new StructuredSelection());
		firePropertyChange(PROP_DIRTY);

		if (viewer != null) {
			viewer.refresh();
			viewer.expandToLevel(2);
		}
	}

	private void updateTitle(String filename) {
		if (filename != null) {
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

	@SuppressWarnings("unchecked")
	public void updateActions() {
		Iterator<IAction> it = actionRegistry.getActions();
		while (it.hasNext()) {
			IAction a = it.next();
			if (a instanceof UpdateAction)
				((UpdateAction) a).update();
		}
		// updating export selection
		getViewSite().getActionBars().getGlobalActionHandler(ActionFactory.EXPORT.getId());
	}

	public ActionRegistry getActionRegistry() {
		return actionRegistry;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class adapter) {
		if (adapter == ActionRegistry.class)
			return getActionRegistry();

		return super.getAdapter(adapter);
	}

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

	public void close() {
		setFilename(null, false);
		IWorkbenchPage activePage = getViewSite().getWorkbenchWindow().getActivePage();
		for (IEditorReference ref : activePage.getEditorReferences()) {
			if (ref.getId().equals(TmcleditEditPlugin.DIAGRAMEDITOR_ID)) {
				activePage.closeEditor(ref.getEditor(false), false);
			}
		}
		firePropertyChange(PROP_DIRTY);
		updateActions();
		viewer.refresh();
		setSelection(new StructuredSelection());
	}

	@Override
	public void saveState(IMemento memento) {
		String text = "null";

		if (currFile != null) {
			java.io.File file = new java.io.File(currFile.getFilename());
			if (file.exists())
				text = currFile.getFilename();
			file = null;
		}

		memento.putTextData(text);
		int i = 0;
		for (IEditorReference ref : getViewSite().getPage().getEditorReferences()) {
			IEditorPart part = ref.getEditor(false);
			if (part!=null) {
				if ( (ref.getId().equals(TmcleditEditPlugin.DIAGRAMEDITOR_ID)) 
				    || (ref.getId().equals(TmcleditEditPlugin.DOMAIN_DIAGRAMEDITOR_ID)) ) {
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
		super.dispose();
	}

	public void doSave(IProgressMonitor monitor) {
		try {
			FileUtil.saveFile((File) currFile, getEditingDomain());
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	public void doSaveAs() {
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setText("Save As..");
		String result = dlg.open();
		if (result != null) {
			if ((!result.endsWith(".ono")) && (!result.endsWith(".tmcl")))
				result += ".ono";
			currFile.setFilename(result);
			updateTitle(currFile.getFilename());
			doSave(new NullProgressMonitor());

		}
	}

	public boolean isDirty() {
		if (currFile == null)
			return false;
		return currFile.isDirty();
	}

	public boolean isSaveAsAllowed() {
		return (currFile!=null);
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

	class ViewContentProvider implements IStructuredContentProvider, ITreeContentProvider {
		private TreeParent invisibleRoot;
		private TreeParent diagramNode;
		private TreeParent schemaNode;

		private AdapterImpl tmsListener = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if (msg.getNotifier() instanceof TopicMapSchema) {
					if (msg.getFeatureID(EList.class) == ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES) {
						switch (msg.getEventType()) {
						case Notification.ADD:
							addType((TopicType) msg.getNewValue(), true);
							break;
						case Notification.REMOVE:
							removeType((TopicType) msg.getOldValue(), true);
							break;
						}
					} else if (msg.getFeatureID(EList.class) == ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS) {
						switch (msg.getEventType()) {
						case Notification.ADD:
							addAssocContraint((AssociationTypeConstraint) msg.getNewValue());
							break;
						case Notification.REMOVE:
							removeAssocContraint((AssociationTypeConstraint) msg.getOldValue());
							break;
						}
					}
				} else if ((msg.getNotifier() instanceof File)
				        && (msg.getFeatureID(EList.class) == ModelPackage.FILE__DIAGRAMS)) {
					switch (msg.getEventType()) {
					case Notification.ADD:
						addDiagram((Diagram) msg.getNewValue());
						break;
					case Notification.REMOVE:
						removeDiagram((Diagram) msg.getOldValue());
						break;
					}
				}
			}
		};

		private TreeParent ttNode;
		private TreeParent rtNode;
		private TreeParent ntNode;
		private TreeParent otNode;
		private TreeParent atNode;
		private TreeParent acNode;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
			setFilename(null, false);
		}

		public Object[] getElements(Object parent) {
			if (parent.equals(getViewSite())) {
				if (invisibleRoot == null)
					initialize();
				return getChildren(invisibleRoot);
			}
			return getChildren(parent);
		}

		public Object getParent(Object child) {
			if (child instanceof TreeObject) {
				return ((TreeObject) child).getParent();
			}
			return null;
		}

		public Object[] getChildren(Object parent) {
			if (parent instanceof TreeParent) {
				return ((TreeParent) parent).getChildren();
			}
			return new Object[0];
		}

		public boolean hasChildren(Object parent) {
			if (parent instanceof TreeParent)
				return ((TreeParent) parent).hasChildren();
			return false;
		}

		public void uninitialize() {
			if (currFile != null) {
				getCurrentTopicMapSchema().eAdapters().remove(tmsListener);
				currFile.eAdapters().remove(tmsListener);
			}
			invisibleRoot.dispose();
		}

		public void initialize() {
			if (currFile != null) {
				getCurrentTopicMapSchema().eAdapters().add(tmsListener);
				currFile.eAdapters().add(tmsListener);
			}
			update();
		}

		public void update() {

			invisibleRoot = new TreeParent(ModelView.this, "");
			if (currFile != null) {
				schemaNode = new TreeParent(ModelView.this, "Topic Map Schema", TreeObject.TOPIC_MAP_SCHEMA);
				
				schemaNode.setModel(getCurrentTopicMapSchema());
				diagramNode = new TreeParent(ModelView.this, "Diagrams", TreeObject.DIAGRAMS);

				invisibleRoot.addChild(diagramNode);
				invisibleRoot.addChild(schemaNode);

				ttNode = new TreeParent(ModelView.this, "TopicTypes", KindOfTopicType.TOPIC_TYPE);
				rtNode = new TreeParent(ModelView.this, "RoleTypes", KindOfTopicType.ROLE_TYPE);
				ntNode = new TreeParent(ModelView.this, "NameTypes", KindOfTopicType.NAME_TYPE);
				otNode = new TreeParent(ModelView.this, "OccurrenceTypes", KindOfTopicType.OCCURRENCE_TYPE);
				atNode = new TreeParent(ModelView.this, "AssociationTypes", KindOfTopicType.ASSOCIATION_TYPE);

				acNode = new TreeParent(ModelView.this, "Association Constraints");

				schemaNode.addChild(ttNode);
				schemaNode.addChild(rtNode);
				schemaNode.addChild(ntNode);
				schemaNode.addChild(otNode);
				schemaNode.addChild(atNode);
				schemaNode.addChild(acNode);

				for (TopicType tt : getCurrentTopicMapSchema().getTopicTypes()) {
					addType(tt, false);
				}

				for (Diagram d : currFile.getDiagrams()) {
					diagramNode.addChild(new TreeDiagram(ModelView.this, d));
				}

				for (AssociationTypeConstraint ac : getCurrentTopicMapSchema().getAssociationTypeConstraints()) {
					addAssocContraint(ac);
				}

				if (!viewer.isBusy())
					viewer.refresh();
				invisibleRoot.setSyncView(true);
			} else {
				TreeParent root = new TreeParent(ModelView.this, "No Diagramm Editor Open", KindOfTopicType.TOPIC_TYPE);
				invisibleRoot.addChild(root);
			}
		}

		private void addAssocContraint(AssociationTypeConstraint constraint) {
			TreeAssocConstraint node = new TreeAssocConstraint(ModelView.this, constraint);
			acNode.addChild(node);
			acNode.refresh();
		}

		private void removeAssocContraint(AssociationTypeConstraint constraint) {
			TreeObject child = acNode.findChildPerModel(constraint);
			acNode.removeChild(child);
			child.dispose();
			acNode.refresh();
		}

		private void addDiagram(Diagram diagram) {
			diagramNode.addChild(new TreeDiagram(ModelView.this, diagram));
			diagramNode.refresh();
		}

		private void removeDiagram(Diagram diagram) {
			TreeObject child = diagramNode.findChildPerModel(diagram);
			diagramNode.removeChild(child);
			child.dispose();
			diagramNode.refresh();

		}

		private void addType(TopicType tt, boolean refresh) {
			TreeTopic to = new TreeTopic(ModelView.this, tt);
			TreeParent parent = null;

			parent = getParentNode(tt);

			if (parent != null) {
				parent.setSyncView(refresh);
				parent.addChild(to);
				for (NameTypeConstraint ntc : tt.getNameContraints()) {
					to.addChild(new TreeName(ModelView.this, ntc));
				}
				for (OccurrenceTypeConstraint otc : tt.getOccurrenceConstraints()) {
					to.addChild(new TreeOccurrence(ModelView.this, otc));
				}
				for (SubjectIdentifierConstraint sic : tt.getSubjectIdentifierConstraints()) {
					to.addChild(new TreeSubjectIdentifier(ModelView.this, sic));
				}
				for (SubjectLocatorConstraint slc : tt.getSubjectLocatorConstraints()) {
					to.addChild(new TreeSubjectLocator(ModelView.this, slc));
				}
				parent.refresh();
				parent.setSyncView(true);
			}
		}

		private void removeType(TopicType tt, boolean refresh) {
			TreeParent parent = ttNode;

			parent = getParentNode(tt);

			TreeObject to = parent.findChildPerModel(tt);
			parent.removeChild(to);
			to.dispose();

			parent.setSyncView(refresh);
			parent.refresh();
			parent.setSyncView(true);
		}

		private TreeParent getParentNode(TopicType topicType) {
			TreeParent parent;
			switch (topicType.getKind()) {
			case ROLE_TYPE:
				parent = rtNode;
				break;
			case NAME_TYPE:
				parent = ntNode;
				break;
			case OCCURRENCE_TYPE:
				parent = otNode;
				break;
			case ASSOCIATION_TYPE:
				parent = atNode;
				break;
			default:
				parent = ttNode;
				break;
			}
			return parent;
		}
	}

	class ViewLabelProvider extends LabelProvider {

		@Override
		public String getText(Object obj) {
			return obj.toString();
		}

		@Override
		public Image getImage(Object obj) {
			return ((TreeObject) obj).getImage();
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

			return super.category(element);
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

}