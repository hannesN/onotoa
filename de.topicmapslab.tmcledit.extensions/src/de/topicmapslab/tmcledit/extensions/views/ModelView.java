package de.topicmapslab.tmcledit.extensions.views;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.ReflectiveItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.view.ExtendedPropertySheetPage;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.IInputValidator;
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
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.DrillDownAdapter;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheetPage;

import de.topicmapslab.tmcledit.diagram.editor.TMCLDiagramEditor;
import de.topicmapslab.tmcledit.extensions.command.RenameCommand;
import de.topicmapslab.tmcledit.extensions.util.FileUtil;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeDiagram;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeObject;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeParent;
import de.topicmapslab.tmcledit.extensions.views.treenodes.TreeTopic;
import de.topicmapslab.tmcledit.model.AssociationsType;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.ScopeType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.provider.ModelItemProviderAdapterFactory;

/**
 * @author Hannes Niederhausen
 */

public class ModelView extends ViewPart implements IEditingDomainProvider, ISelectionProvider {
	
	public static final String ID = "de.topicmapslab.tmcledit.extensions.views.ModelView"; 
	
	private TreeViewer viewer;
	private ViewContentProvider contentProvider;
	private DrillDownAdapter drillDownAdapter;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;
	private TMCLDiagramEditor currentEditor;
	private ComposedAdapterFactory adapterFactory;
	
	private EditingDomain editingDomain;
	
	private File currFile;
	private boolean dirty;
	
	private List<ISelectionChangedListener> listeners;
	
	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private TreeParent invisibleRoot;
		private TreeParent diagramNode;
		private TreeParent schemaNode;

		
		private AdapterImpl tmsListener = new AdapterImpl() {
			@Override
			public void notifyChanged(Notification msg) {
				if ((msg.getEventType() == Notification.ADD)
						&& (msg.getNewValue() instanceof TopicType)) {
					addType((TopicType) msg.getNewValue());
				}

				if ((msg.getEventType() == Notification.REMOVE)
						&& (msg.getNewValue() instanceof TopicType)) {
					removeType((TopicType) msg.getNewValue());
				}

				viewer.setInput(getViewSite());
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
			invisibleRoot = new TreeParent(viewer, "");
						
			if (currFile!=null) {
				schemaNode = new TreeParent(viewer, "Topic Map Schema");
				diagramNode = new TreeParent(viewer, "Diagrams");
				
				invisibleRoot.addChild(diagramNode);
				invisibleRoot.addChild(schemaNode);
				
				getCurrentTopicMapSchema().eAdapters().add(tmsListener);
	
				ttNode = new TreeParent(viewer, "TopicTypes");
				rtNode = new TreeParent(viewer, "RoleTypes");
				ntNode = new TreeParent(viewer, "NameTypes");
				otNode = new TreeParent(viewer, "OccurenceTypes");
				atNode = new TreeParent(viewer, "AssociationTypes");
				stNode = new TreeParent(viewer, "ScopeTypes");
	
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
					diagramNode.addChild(new TreeDiagram(viewer, d, editingDomain));
				}
			} else {
				TreeParent root = new TreeParent(viewer, "No Diagramm Editor Open");
				invisibleRoot.addChild(root);
			}
		}

		private void addType(TopicType tt) {
			TreeTopic to = new TreeTopic(viewer, tt);

			if (tt instanceof RoleType)
				rtNode.addChild(to);
			else if (tt instanceof NameType)
				ntNode.addChild(to);
			else if (tt instanceof OccurenceType)
				otNode.addChild(to);
			else if (tt instanceof AssociationsType)
				atNode.addChild(to);
			else if (tt instanceof ScopeType)
				stNode.addChild(to);
			else if (tt instanceof TopicType)
				ttNode.addChild(to);
		}

		private void removeType(TopicType tt) {
			TreeParent parent = ttNode;

			if (tt instanceof RoleType)
				parent = rtNode;
			else if (tt instanceof NameType)
				parent = ntNode;
			else if (tt instanceof OccurenceType)
				parent = otNode;
			else if (tt instanceof AssociationsType)
				parent = atNode;

			for (TreeObject to : parent.getChildren()) {
				if (((TreeTopic)to).getTopic().equals(tt))
					parent.removeChild(to);
			}
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
		drillDownAdapter = new DrillDownAdapter(viewer);

		contentProvider = new ViewContentProvider();
		viewer.setContentProvider(contentProvider);
		viewer.setLabelProvider(new ViewLabelProvider());
		viewer.setSorter(new NameSorter());
		viewer.getTree().addKeyListener(new org.eclipse.swt.events.KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
				if (sel.isEmpty())
					return;
				
				TreeObject obj = (TreeObject) sel.getFirstElement();
				if (obj instanceof TreeTopic) {
					TopicType tt = ((TreeTopic)obj).getTopic();
					
					String oldName = tt.getId();
					InputDialog dlg = new InputDialog(viewer.getTree().getShell(), "New Topic Id..",
							"Please enter the new Topic ID", oldName,
							new IInputValidator() {

								@Override
								public String isValid(String newText) {
									if (newText.length()==0)
										return "no name given";
									
									// TODO check if there's a topic with the same name
									
									return null;
								}
					});
					if (InputDialog.OK==dlg.open()) {
						if (currentEditor!=null) {
							currentEditor.getEditingDomain().getCommandStack().
									execute(new RenameCommand(tt, dlg.getValue()));
						
						}
						else 
							return;
					}
					viewer.refresh(obj);
					
				}
				
				
			}
		});
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
				"de.topicmapslab.tmcledit.extensions.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();

		getSite().setSelectionProvider(viewer);
		
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ModelItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		initDragAndDrop();
	}

	private void initDragAndDrop() {
		DragSource dragSource = new DragSource(viewer.getTree(), DND.DROP_COPY);
		dragSource.setTransfer(new Transfer[] {TextTransfer.getInstance()});
		dragSource.addDragListener(new DragSourceAdapter() {
			
			@Override
			public void dragStart(DragSourceEvent event) {
				IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
				Object obj = sel.getFirstElement();
				if (obj instanceof TreeTopic) {
					event.data = ((TreeTopic)obj).getTopic().toString();
					event.doit = true;
				} else {
					event.doit = false;
				}
				
			}
			
			@Override
			public void dragSetData(DragSourceEvent event) {
				IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
				Object obj = sel.getFirstElement();
				if (obj instanceof TreeTopic) {
					event.data = ((TreeTopic)obj).getTopic().toString();
				} else {
					event.data = null;
				}
			}
		});
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
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
		// Other plug-ins can contribute there actions here
		manager.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
	}

	private void fillLocalToolBar(IToolBarManager manager) {
		manager.add(action1);
		manager.add(action2);
		manager.add(new Separator());
		drillDownAdapter.addNavigationActions(manager);
	}

	private void makeActions() {
		action1 = new Action() {
			public void run() {
				contentProvider.initialize();
				viewer.setInput(getViewSite());
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
				((TreeObject)obj).handleDoubleClick();
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
		if (currentEditor!=null)
			return currentEditor.getEditingDomain();
		return null;
	}

	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners==Collections.EMPTY_LIST)
			listeners = new ArrayList<ISelectionChangedListener>();
		listeners.add(listener);
	}

	@Override
	public ISelection getSelection() {
		if (viewer!=null)
			return viewer.getSelection();
		else
			return new StructuredSelection();
	}

		
	public TopicMapSchema getCurrentTopicMapSchema() {
		if (currFile!=null)
			return currFile.getTopicMapSchema();
		return null;
	}
	
	@Override
	public void removeSelectionChangedListener(
			ISelectionChangedListener listener) {
		if (listeners==Collections.EMPTY_LIST)
			listeners = new ArrayList<ISelectionChangedListener>();
		listeners.remove(listener);	
	}

	@Override
	public void setSelection(ISelection selection) {
		if (viewer!=null)
			viewer.setSelection(selection);
	}
	
	public void setFilename(String filename) {
		// TODO check dirty state and ask for saving
		contentProvider.uninitialize();
		currFile = FileUtil.loadFile(filename);
		
		adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

		adapterFactory.addAdapterFactory(new ResourceItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ModelItemProviderAdapterFactory());
		adapterFactory.addAdapterFactory(new ReflectiveItemProviderAdapterFactory());

		BasicCommandStack commandStack = new BasicCommandStack();

		editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack, new HashMap<Resource, Boolean>());
		
		contentProvider.initialize();
		viewer.refresh();
	}
	
	public void doSave() {
		try {
			FileUtil.saveFile(currFile);
			// TODO set dirty state
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setDirty(boolean dirty) {
		this.dirty = dirty;
	}
	
	public boolean isDirty() {
		return dirty;
	}
	
}