package de.topicmapslab.tmcledit.extensions.views;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.action.RedoAction;
import org.eclipse.emf.edit.ui.action.UndoAction;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.emf.workspace.WorkspaceEditingDomainFactory;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.InputDialog;
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
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerSorter;
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
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.ISharedImages;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.extensions.actions.RedoActionWrapper;
import de.topicmapslab.tmcledit.extensions.actions.UndoActionWrapper;
import de.topicmapslab.tmcledit.extensions.actions.UpdateAction;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeDiagram;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeName;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeOccurence;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeParent;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeTopic;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.provider.ModelItemProviderAdapterFactory;
import de.topicmapslab.tmcledit.model.util.DirtyStateObserver;
import de.topicmapslab.tmcledit.model.util.FileUtil;

/**
 * @author Hannes Niederhausen
 */

public class ModelView extends ViewPart implements IEditingDomainProvider,
		ISelectionProvider, CommandStackListener {

	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.ModelView";

	private TreeViewer viewer;
	private ViewContentProvider contentProvider;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;
	private TMCLDiagramEditor currentEditor;
	private ComposedAdapterFactory adapterFactory;

	private EditingDomain editingDomain;

	private File currFile;
	
	private List<ISelectionChangedListener> listeners = Collections.emptyList();
	private ISelection currentSelection;

	private Map<String, UpdateAction> actionRegistry;

	private DirtyStateObserver dirtyStateObserver;

	private AdapterImpl dirtyListener = new AdapterImpl() {
		@Override
		public void notifyChanged(Notification msg) {
			if (msg.getFeatureID(Boolean.class) == ModelPackage.FILE__DIRTY) {
				if ((currFile != null) && (currFile.isDirty()))
					setPartName("*ModelView");
				else
					setPartName("ModelView");
			}

		}
	};

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private TreeParent invisibleRoot;
		private TreeParent diagramNode;
		private TreeParent schemaNode;

		private AdapterImpl tmsListener = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if (msg.getFeatureID(EList.class) == ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES) {
					switch (msg.getEventType()) {
					case Notification.ADD:
						addType((TopicType) msg.getNewValue());
						break;
					case Notification.REMOVE:
						removeType((TopicType) msg.getOldValue());
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
			if (getCurrentTopicMapSchema() != null)
				getCurrentTopicMapSchema().eAdapters().remove(tmsListener);
		}

		public void initialize() {
			invisibleRoot = new TreeParent(ModelView.this, "");

			if (currFile != null) {
				schemaNode = new TreeParent(ModelView.this, "Topic Map Schema");
				diagramNode = new TreeParent(ModelView.this, "Diagrams");

				invisibleRoot.addChild(diagramNode);
				invisibleRoot.addChild(schemaNode);

				getCurrentTopicMapSchema().eAdapters().add(tmsListener);

				ttNode = new TreeParent(ModelView.this, "TopicTypes");
				rtNode = new TreeParent(ModelView.this, "RoleTypes");
				ntNode = new TreeParent(ModelView.this, "NameTypes");
				otNode = new TreeParent(ModelView.this, "OccurenceTypes");
				atNode = new TreeParent(ModelView.this, "AssociationTypes");
				stNode = new TreeParent(ModelView.this, "ScopeTypes");

				schemaNode.addChild(ttNode);
				schemaNode.addChild(rtNode);
				schemaNode.addChild(ntNode);
				schemaNode.addChild(otNode);
				schemaNode.addChild(atNode);
				schemaNode.addChild(stNode);

				for (TopicType tt : getCurrentTopicMapSchema().getTopicTypes()) {
					addType(tt);
				}

				for (Diagram d : currFile.getDiagrams()) {
					diagramNode.addChild(new TreeDiagram(ModelView.this, d));
				}
			} else {
				TreeParent root = new TreeParent(ModelView.this,
						"No Diagramm Editor Open");
				invisibleRoot.addChild(root);
			}
		}

		private void addType(TopicType tt) {
			TreeTopic to = new TreeTopic(ModelView.this, tt);
			TreeParent parent = null;

			parent = getParentNode(tt);

			if (parent != null) {
				parent.addChild(to);
				for (NameTypeConstraint ntc : tt.getNameContraints()) {
					to.addChild(new TreeName(ModelView.this, ntc));
				}
				for (OccurenceTypeConstraint otc : tt.getOccurenceConstraints()) {
					to.addChild(new TreeOccurence(ModelView.this, otc));
				}
				viewer.refresh(parent);
			}
		}

		private void removeType(TopicType tt) {
			TreeParent parent = ttNode;

			parent = getParentNode(tt);

			for (TreeObject to : parent.getChildren()) {
				if (((TreeTopic) to).getTopic().equals(tt))
					parent.removeChild(to);
			}
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
			case OCCURENCE_TYPE:
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

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			return ((TreeObject) obj).getImage();
		}
	}

	class NameSorter extends ViewerSorter {

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

			@Override
			public void selectionChanged(SelectionChangedEvent event) {
				IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
				if (sel.isEmpty())
					currentSelection = new StructuredSelection();
				else {
					TreeObject to = (TreeObject) sel.getFirstElement();
					if (to.getModel()==null)
						currentSelection = new StructuredSelection();
					else
						currentSelection = new StructuredSelection(to.getModel());
				}
				fireSelectionChanged();
				
			}
			
		});
		
		adapterFactory = new ComposedAdapterFactory(
				ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory
				.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ModelItemProviderAdapterFactory());
		adapterFactory
				.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		initDragAndDrop();
	}

	private void initDragAndDrop() {
		DragSource dragSource = new DragSource(viewer.getTree(), DND.DROP_COPY);
		dragSource.setTransfer(new Transfer[] { TextTransfer.getInstance() });
		dragSource.addDragListener(new DragSourceAdapter() {

			@Override
			public void dragStart(DragSourceEvent event) {
				IStructuredSelection sel = (IStructuredSelection) viewer
						.getSelection();
				Object obj = sel.getFirstElement();
				if (obj instanceof TreeTopic) {
					event.data = ((TreeTopic) obj).getTopic().toString();
					event.doit = true;
				} else {
					event.doit = false;
				}

			}

			@Override
			public void dragSetData(DragSourceEvent event) {
				IStructuredSelection sel = (IStructuredSelection) viewer
						.getSelection();
				Object obj = sel.getFirstElement();
				if (obj instanceof TreeTopic) {
					event.data = ((TreeTopic) obj).getTopic().toString();
				} else {
					event.data = null;
				}
			}
		});
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);

		actionRegistry = new HashMap<String, UpdateAction>();
		createActions();
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

		actionRegistry.put(ActionFactory.UNDO.getId(), undoAction);
		actionRegistry.put(ActionFactory.REDO.getId(), redoAction);
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

	@Override
	public void dispose() {
		super.dispose();
	}

	private void contributeToActionBars() {
		IActionBars bars = getViewSite().getActionBars();
		fillLocalPullDown(bars.getMenuManager());
		fillLocalToolBar(bars.getToolBarManager());
	}

	private void fillLocalPullDown(IMenuManager manager) {
		manager.add(action1);
		manager.add(new Separator());
		manager.add(action2);
	}

	private void fillContextMenu(IMenuManager manager) {
		manager.add(action1);
		manager.add(action2);

		manager.add(new Action() {
			@Override
			public String getText() {
				return "Create Topic Type";
			}

			@Override
			public void run() {
				InputDialog dlg = new InputDialog(getSite().getShell(),
						"New Topic Type..",
						"Please Enter the name of the new type", "", null);

				if (dlg.open() == Dialog.OK) {
					String name = dlg.getValue();
					getEditingDomain().getCommandStack().execute(
							new CreateTopicTypeCommand(currFile
									.getTopicMapSchema(), name));
				}

			}
		});
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
		manager.add(new Separator());

	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				contentProvider.initialize();
				viewer.setInput(getViewSite());
				viewer.expandToLevel(2);
			}
		};
		action1.setText("Refresh");
		action1.setToolTipText("Refreshs the Model View");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_ELCL_SYNCED));

		action2 = new Action() {
			public void run() {
				showMessage("Action 2 executed");
			}
		};
		action2.setText("Action 2");
		action2.setToolTipText("Action 2 tooltip");
		action2.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));
		doubleClickAction = new Action() {
			public void run() {
				ISelection selection = viewer.getSelection();
				Object obj = ((IStructuredSelection) selection)
						.getFirstElement();
				((TreeObject) obj).handleDoubleClick();
			}
		};
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
						if (e.keyCode!=SWT.F2)
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

	private void showMessage(String message) {
		MessageDialog.openInformation(viewer.getControl().getShell(),
				"Model View", message);
	}

	/**
	 * Passing the focus request to the viewer's control.
	 */
	public void setFocus() {
		viewer.getControl().setFocus();
	}

	public IPropertySheetPage getPropertySheetPage() {
		if (currentEditor == null)
			return null;
		PropertySheetPage propertySheetPage = new ExtendedPropertySheetPage(
				(AdapterFactoryEditingDomain) currentEditor.getEditingDomain()) {
			@Override
			public void setSelectionToViewer(List<?> selection) {
				/*
				 * ModelEditor.this.setSelectionToViewer(selection);
				 * ModelEditor.this.setFocus();
				 */
			}

			@Override
			public void setActionBars(IActionBars actionBars) {
				super.setActionBars(actionBars);
			}
		};
		propertySheetPage
				.setPropertySourceProvider(new AdapterFactoryContentProvider(
						adapterFactory));
		return propertySheetPage;

	}

	@Override
	public EditingDomain getEditingDomain() {
		if (editingDomain == null) {
			editingDomain = WorkspaceEditingDomainFactory.INSTANCE
					.createEditingDomain();
			UpdateAction action = actionRegistry
					.get(ActionFactory.UNDO.getId());
			((UndoAction) action).setEditingDomain(editingDomain);

			action = actionRegistry.get(ActionFactory.REDO.getId());
			((RedoAction) action).setEditingDomain(editingDomain);
			editingDomain.getCommandStack().addCommandStackListener(this);
			updateActions();
		}

		return editingDomain;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners == Collections.EMPTY_LIST)
			listeners = new ArrayList<ISelectionChangedListener>();
		listeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		if (currentSelection==null)
			currentSelection = new StructuredSelection();
		return currentSelection;
	}

	public TopicMapSchema getCurrentTopicMapSchema() {
		if (currFile != null)
			return currFile.getTopicMapSchema();
		return null;
	}

	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (listeners == Collections.EMPTY_LIST)
			listeners = new ArrayList<ISelectionChangedListener>();
		listeners.remove(listener);
	}

	@Override
	public void setSelection(ISelection selection) {
		if (viewer != null)
			viewer.setSelection(selection);
	}

	private void fireSelectionChanged() {
		SelectionChangedEvent e = new SelectionChangedEvent(this, currentSelection);
		for (ISelectionChangedListener l : listeners) {
			l.selectionChanged(e);
		}
	}
	
	public void setFilename(String filename) {

		if (dirtyStateObserver != null)
			dirtyStateObserver.dispose();
		if (currFile != null)
			currFile.eAdapters().remove(dirtyListener);
		// TODO check dirty state and ask for saving
		contentProvider.uninitialize();

		if (filename != null) {
			currFile = FileUtil.loadFile(filename);
			currFile.eAdapters().add(dirtyListener);
			if (editingDomain != null)
				editingDomain.getCommandStack()
						.removeCommandStackListener(this);

			editingDomain = null; // clear it for new creation in getter
			dirtyStateObserver = new DirtyStateObserver(currFile,
					getEditingDomain().getCommandStack());
		}
		contentProvider.initialize();

		viewer.refresh();
	}

	public void doSave() {
		try {
			FileUtil.saveFile(currFile);
			currFile.setDirty(false);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void updateActions() {
		for (UpdateAction a : actionRegistry.values()) {
			a.update();
		}
	}

	public Map<String, UpdateAction> getActionRegistry() {
		return actionRegistry;
	}

	public TreeViewer getViewer() {
		return viewer;
	}

	@Override
	public void commandStackChanged(EventObject event) {
		updateActions();
	}

}