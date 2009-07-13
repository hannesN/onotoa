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
package de.topicmapslab.tmcledit.extensions.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.jobs.Job;
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

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditorInput;
import de.topicmapslab.tmcledit.extensions.actions.CloseAction;
import de.topicmapslab.tmcledit.extensions.actions.CreateDiagramAction;
import de.topicmapslab.tmcledit.extensions.actions.CreateTopicAction;
import de.topicmapslab.tmcledit.extensions.actions.DeleteDiagramAction;
import de.topicmapslab.tmcledit.extensions.actions.DeleteTopicTypeAction;
import de.topicmapslab.tmcledit.extensions.actions.RedoActionWrapper;
import de.topicmapslab.tmcledit.extensions.actions.UndoActionWrapper;
import de.topicmapslab.tmcledit.extensions.actions.UpdateAction;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeDiagram;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeName;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeOccurrence;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeParent;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeTopic;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.FileUtil;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;
import de.topicmapslab.tmcledit.model.validation.ModelValidator;
import de.topicmapslab.tmcledit.model.validation.ValidationResult;

/**
 * @author Hannes Niederhausen
 */

public class ModelView extends ViewPart implements IEditingDomainProvider,
		ISelectionProvider, CommandStackListener, ISaveablePart, IResourceChangeListener {

	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.ModelView";

	private TreeViewer viewer;
	private ViewContentProvider contentProvider;
	private Action refreshAction;
	private Action doubleClickAction;
	// private TMCLDiagramEditor currentEditor;

	private EditingDomain editingDomain;
	private ValidateJob validateJob = new ValidateJob();
	File currFile;

	private List<ISelectionChangedListener> listeners = Collections.emptyList();
	private ISelection currentSelection;

	private Map<String, IAction> actionRegistry;

	private AdapterImpl dirtyListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Boolean.class) == ModelPackage.FILE__DIRTY) {
				firePropertyChange(PROP_DIRTY);
			}

		}
	};

	private CreateDiagramAction createDiagramAction;

	private CreateTopicAction createTopicAction;

	private DeleteTopicTypeAction deleteTopicTypeAction;

	private DeleteDiagramAction deleteDiagramAction;

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

			public void selectionChanged(SelectionChangedEvent event) {
				if (currFile == null) {
					currentSelection = new StructuredSelection();
					return;
				}

				IStructuredSelection sel = (IStructuredSelection) viewer
						.getSelection();
				if (sel.isEmpty()) {
					currentSelection = new StructuredSelection(currFile);
				} else {

					TreeObject to = (TreeObject) sel.getFirstElement();
					if (to.getModel() == null)
						currentSelection = new StructuredSelection(currFile);
					else
						currentSelection = new StructuredSelection(to
								.getModel());
				}
				fireSelectionChanged();

			}

		});
		/*
		 * adapterFactory = new ComposedAdapterFactory(
		 * ComposedAdapterFactory.Descriptor.Registry.INSTANCE);
		 * 
		 * adapterFactory .addAdapterFactory(new
		 * ResourceItemProviderAdapterFactory());
		 * adapterFactory.addAdapterFactory(new
		 * ModelItemProviderAdapterFactory()); adapterFactory
		 * .addAdapterFactory(new ReflectiveItemProviderAdapterFactory());
		 */
		initDragAndDrop();
	}

	private void initDragAndDrop() {
		DragSource dragSource = new DragSource(viewer.getTree(), DND.DROP_COPY);
		dragSource.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		dragSource.addDragListener(new DragSourceAdapter() {

			@SuppressWarnings("unchecked")
			@Override
			public void dragStart(DragSourceEvent event) {
				IStructuredSelection sel = (IStructuredSelection) viewer
						.getSelection();
				// concat every model string and ignore other nodes..
				Iterator<Object> it = sel.iterator();
				StringBuffer tmp = new StringBuffer();
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof TreeTopic) {
						tmp.append(((TreeTopic) obj).getModel().toString());
						// tmp.append("&*&*");
					}
				}
				if (tmp.length() > 0) {
					event.doit = true;
					event.data = tmp.toString();
				} else {
					event.doit = false;
				}

			}

			@SuppressWarnings("unchecked")
			@Override
			public void dragSetData(DragSourceEvent event) {
				if (viewer==null)
					return;
				
				IStructuredSelection sel = (IStructuredSelection) viewer
						.getSelection();
				// concat every model string and ignore other nodes..
				Iterator<Object> it = sel.iterator();
				StringBuffer tmp = new StringBuffer();
				while (it.hasNext()) {
					Object obj = it.next();
					if (obj instanceof TreeTopic) {
						tmp.append(((TreeTopic) obj).getModel().toString());
						tmp.append("--_--");
					}
				}
				if (tmp.length() > 0) {
					event.data = tmp.toString();
				} else {
					event.data = null;
				}
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
				getViewSite().getPage().openEditor(
						new TMCLEditorInput(currDiagram, getEditingDomain(),
								(UndoAction) getActionRegistry().get(
										ActionFactory.UNDO.getId()),
								(RedoAction) getActionRegistry().get(
										ActionFactory.REDO.getId()), true),
						TMCLDiagramEditor.ID);
			}
		}

	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);

		actionRegistry = new HashMap<String, IAction>();
		createActions();
		ResourcesPlugin.getWorkspace().addResourceChangeListener(this, IResourceChangeEvent.POST_CHANGE);
	}

	public void createActions() {
		IActionBars actionBars = getViewSite().getActionBars();
		ISharedImages sharedImages = PlatformUI.getWorkbench()
				.getSharedImages();

		UndoActionWrapper undoAction = new UndoActionWrapper();
		undoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_UNDO));
		actionBars.setGlobalActionHandler(ActionFactory.UNDO.getId(),
				undoAction);

		RedoActionWrapper redoAction = new RedoActionWrapper();
		redoAction.setImageDescriptor(sharedImages
				.getImageDescriptor(ISharedImages.IMG_TOOL_REDO));
		actionBars.setGlobalActionHandler(ActionFactory.REDO.getId(),
				redoAction);

		CloseAction closeAction = new CloseAction(this);
		actionBars.setGlobalActionHandler(ActionFactory.CLOSE.getId(),
				closeAction);

		actionRegistry.put(ActionFactory.UNDO.getId(), undoAction);
		actionRegistry.put(ActionFactory.REDO.getId(), redoAction);

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
		manager.add(refreshAction);
		manager.add(new Separator());
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(refreshAction);
		manager.add(new Separator());

		manager.add(createDiagramAction);
		manager.add(createTopicAction);
		manager.add(new Separator());
		if (!currentSelection.isEmpty()) {
			Object selection = ((IStructuredSelection) currentSelection)
					.getFirstElement();
			if (selection instanceof TopicType) {
				deleteTopicTypeAction.setType((TopicType) selection);
				manager.add(deleteTopicTypeAction);
			} else if (selection instanceof Diagram) {
				deleteDiagramAction.setDiagram((Diagram) selection);
				manager.add(deleteDiagramAction);
			}
		}
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(refreshAction);
		manager.add(new Separator());

	}

	private void makeActions() {
		refreshAction = new Action() {
			@Override
			public void run() {
				contentProvider.update();
				viewer.setInput(getViewSite());
				viewer.expandToLevel(2);
			}
		};
		refreshAction.setText("Refresh");
		refreshAction.setToolTipText("Refreshs the Model View");
		refreshAction.setImageDescriptor(PlatformUI.getWorkbench()
				.getSharedImages().getImageDescriptor(
						ISharedImages.IMG_ELCL_SYNCED));

		doubleClickAction = new Action() {
			@Override
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				((TreeObject) obj).handleDoubleClick();
			}
		};
		deleteDiagramAction = new DeleteDiagramAction(this);
		deleteTopicTypeAction = new DeleteTopicTypeAction(this);
		createDiagramAction = new CreateDiagramAction(this);
		createTopicAction = new CreateTopicAction(this);
	}

	private void hookDoubleClickAction() {
		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {
				doubleClickAction.run();
			}
		});
	}

	private void hookKeyListener() {
		viewer.getTree().addKeyListener(
				new org.eclipse.swt.events.KeyAdapter() {
					@Override
					public void keyReleased(KeyEvent e) {
						if (e.keyCode != SWT.F2)
							return;

						IStructuredSelection sel = (IStructuredSelection) viewer
								.getSelection();
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
			editingDomain = WorkspaceEditingDomainFactory.INSTANCE
					.createEditingDomain();
			IAction action = actionRegistry.get(ActionFactory.UNDO.getId());
			((UndoAction) action).setEditingDomain(editingDomain);

			action = actionRegistry.get(ActionFactory.REDO.getId());
			((RedoAction) action).setEditingDomain(editingDomain);
			editingDomain.getCommandStack().addCommandStackListener(this);
			updateActions();
		}

		return editingDomain;
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

	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
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
		SelectionChangedEvent e = new SelectionChangedEvent(this,
				currentSelection);
		for (ISelectionChangedListener l : listeners) {
			l.selectionChanged(e);
		}
	}

	public void setFilename(String filename, boolean newFile) {
		IWorkbenchPage page = getViewSite().getPage();
		if (currFile != null) {
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

		
		updateTitle(filename);
		
		if (contentProvider != null)
			contentProvider.uninitialize();

		if (editingDomain != null)
			editingDomain.getCommandStack().removeCommandStackListener(this);

		editingDomain = null; // clear it for new creation in getter
		if (filename != null) {
			if (!newFile)
				currFile = FileUtil.loadFile(filename);
			else {
				currFile = ModelFactory.eINSTANCE.createFile();
				currFile.setTopicMapSchema(ModelFactory.eINSTANCE
						.createTopicMapSchema());
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
		if (filename != null)
			getSite().getShell().setText("Onotoa - "+filename);
		else
			getSite().getShell().setText("Onotoa");
	}

	private void checkSavedState() {
		if (currFile != null) {
			currFile.eAdapters().remove(dirtyListener);
			WorkspaceCommandStackImpl cmdStack = (WorkspaceCommandStackImpl) getEditingDomain()
					.getCommandStack();
			if (cmdStack.isSaveNeeded()) {
				if (MessageDialog.openQuestion(getViewSite().getShell(),
						"Unsaved model",
						"Youre model is not saved. Do you want to save now?")) {
					doSave(null);
				}
			}
		}
	}

	public void updateActions() {
		for (IAction a : actionRegistry.values()) {
			if (a instanceof UpdateAction)
				((UpdateAction) a).update();
		}
		// updating export selection
		getViewSite().getActionBars().getGlobalActionHandler(
				ActionFactory.EXPORT.getId());
	}

	public Map<String, IAction> getActionRegistry() {
		return actionRegistry;
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	public void commandStackChanged(EventObject event) {
		updateActions();
		WorkspaceCommandStackImpl cmdStack = (WorkspaceCommandStackImpl) getEditingDomain()
				.getCommandStack();
		currFile.setDirty(cmdStack.isSaveNeeded());

		if (!validateJob.isRunning())
			validateJob.schedule();
	}

	public void close() {
		setFilename(null, false);
		IWorkbenchPage activePage = getViewSite().getWorkbenchWindow()
				.getActivePage();
		for (IEditorReference ref : activePage.getEditorReferences()) {
			if (ref.getId().equals(TMCLDiagramEditor.ID)) {
				activePage.closeEditor(ref.getEditor(false), false);
			}
		}
		firePropertyChange(PROP_DIRTY);
		updateActions();
		viewer.refresh();
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
		for (IEditorReference ref : getViewSite().getPage()
				.getEditorReferences()) {
			IEditorPart part = ref.getEditor(false);
			if ((part != null) && (part instanceof TMCLDiagramEditor)) {
				i++;
				TMCLEditorInput ei = (TMCLEditorInput) part.getEditorInput();
				IMemento partChild = memento.createChild("editor");
				partChild.putTextData(ei.getDiagram().getName());
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
			FileUtil.saveFile((File) currFile);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		WorkspaceCommandStackImpl cmdStack = (WorkspaceCommandStackImpl) getEditingDomain()
				.getCommandStack();
		cmdStack.saveIsDone();
		currFile.setDirty(false);
	}

	public void doSaveAs() {
		FileDialog dlg = new FileDialog(getSite().getShell(), SWT.SAVE);
		dlg.setText("Save As..");
		String result = dlg.open();
		if (result!=null) {
			currFile.setFilename(result);
			doSave(new NullProgressMonitor());
			updateTitle(currFile.getFilename());
		}
	}

	public boolean isDirty() {
		if (currFile == null)
			return false;
		return currFile.isDirty();
	}

	public boolean isSaveAsAllowed() {
		return true;
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
	IResourceDelta delta = event.getDelta();
		if (currFile==null)
			return;
		String filename = currFile.getFilename();
		IFile file = ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(filename));
		IProject project = file.getProject();
		String projectPath = project.getLocation().toOSString();
		if (filename.startsWith(projectPath)) {
			filename = project.getName()+"/"+filename.substring(projectPath.length()+1);
			
		}
		IResourceDelta d2 = delta.findMember(new Path(filename));
		if (d2==null)
			return;
		
		if ((d2.getFlags()&IResourceDelta.MOVED_TO)!=0) {
			
		}
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private TreeParent invisibleRoot;
		private TreeParent diagramNode;
		private TreeParent schemaNode;

		private AdapterImpl tmsListener = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if ((msg.getNotifier() instanceof TopicMapSchema)
						&& (msg.getFeatureID(EList.class) == ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES)) {
					switch (msg.getEventType()) {
					case Notification.ADD:
						addType((TopicType) msg.getNewValue(), true);
						break;
					case Notification.REMOVE:
						removeType((TopicType) msg.getOldValue(), true);
						break;
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
		private TreeParent stNode;

		public void inputChanged(Viewer v, Object oldInput, Object newInput) {
		}

		public void dispose() {
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
				schemaNode = new TreeParent(ModelView.this, "Topic Map Schema");
				schemaNode.setModel(getCurrentTopicMapSchema());
				diagramNode = new TreeParent(ModelView.this, "Diagrams");

				invisibleRoot.addChild(diagramNode);
				invisibleRoot.addChild(schemaNode);

				ttNode = new TreeParent(ModelView.this, "TopicTypes");
				rtNode = new TreeParent(ModelView.this, "RoleTypes");
				ntNode = new TreeParent(ModelView.this, "NameTypes");
				otNode = new TreeParent(ModelView.this, "OccurrenceTypes");
				atNode = new TreeParent(ModelView.this, "AssociationTypes");
				stNode = new TreeParent(ModelView.this, "ScopeTypes");

				schemaNode.addChild(ttNode);
				schemaNode.addChild(rtNode);
				schemaNode.addChild(ntNode);
				schemaNode.addChild(otNode);
				schemaNode.addChild(atNode);
				schemaNode.addChild(stNode);

				for (TopicType tt : getCurrentTopicMapSchema().getTopicTypes()) {
					addType(tt, false);
				}

				for (Diagram d : currFile.getDiagrams()) {
					diagramNode.addChild(new TreeDiagram(ModelView.this, d));
				}
				if (!viewer.isBusy())
					viewer.refresh();
			} else {
				TreeParent root = new TreeParent(ModelView.this,
						"No Diagramm Editor Open");
				invisibleRoot.addChild(root);
			}
		}

		private void addDiagram(Diagram diagram) {
			diagramNode.addChild(new TreeDiagram(ModelView.this, diagram));
			getViewer().refresh(diagramNode);
		}

		private void removeDiagram(Diagram diagram) {
			TreeObject removableNode = null;
			for (TreeObject to : diagramNode.getChildren()) {
				if (diagram.equals(to.getModel())) {
					removableNode = to;
				}
			}
			if (removableNode != null) {
				diagramNode.removeChild(removableNode);
				getViewer().refresh(diagramNode);
			}
		}

		private void addType(TopicType tt, boolean refresh) {
			TreeTopic to = new TreeTopic(ModelView.this, tt);
			TreeParent parent = null;

			parent = getParentNode(tt);

			if (parent != null) {
				parent.addChild(to);
				for (NameTypeConstraint ntc : tt.getNameContraints()) {
					to.addChild(new TreeName(ModelView.this, ntc));
				}
				for (OccurrenceTypeConstraint otc : tt
						.getOccurrenceConstraints()) {
					to.addChild(new TreeOccurrence(ModelView.this, otc));
				}
				if (refresh)
					viewer.refresh(parent);
			}
		}

		private void removeType(TopicType tt, boolean refresh) {
			TreeParent parent = ttNode;

			parent = getParentNode(tt);

			for (TreeObject to : parent.getChildren()) {
				if (((TreeTopic) to).getModel().equals(tt)) {
					parent.removeChild(to);
					to.dispose();
				}
			}
			if (refresh)
				viewer.refresh(parent);
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
			case SCOPE_TYPE:
				parent = stNode;
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
			if (element instanceof TreeOccurrence) {
				return 3;
			}
			if (element instanceof TreeName) {
				return 2;
			}

			return super.category(element);
		}
	}

	class ValidateJob extends Job {
		private boolean running = false;

		private FeedbackRunnable feedBackRunnable = new FeedbackRunnable();

		public ValidateJob() {
			super("Validate...");
		}

		@Override
		protected IStatus run(IProgressMonitor monitor) {
			if (currFile == null)
				return Status.OK_STATUS;

			running = true;

			IWorkbenchWindow activeWorkbenchWindow = getViewSite()
					.getWorkbenchWindow();
			IWorkbenchPage activePage = activeWorkbenchWindow.getActivePage();

			File file = currFile;

			ModelValidator validator = new ModelValidator(file);

			List<ValidationResult> list = validator.validate(getEditingDomain()
					.getCommandStack());

			try {
				ValidationErrorView vew = (ValidationErrorView) activePage
						.findView(ValidationErrorView.ID);

				if (list.size() > 0) {
					if (vew == null)
						vew = (ValidationErrorView) activePage
								.showView(ValidationErrorView.ID);
				}
				if (vew != null) {
					feedBackRunnable.setList(list);
					feedBackRunnable.setView(vew);
					getViewSite().getWorkbenchWindow().getShell().getDisplay()
							.syncExec(feedBackRunnable);
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

		};
	}

}