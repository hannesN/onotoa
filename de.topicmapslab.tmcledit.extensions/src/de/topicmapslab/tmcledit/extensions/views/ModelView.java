package de.topicmapslab.tmcledit.extensions.views;

import java.util.ArrayList;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.part.*;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.gmf.runtime.diagram.ui.resources.editor.document.IDocumentProvider;
import org.eclipse.gmf.runtime.notation.Diagram;
import org.eclipse.jface.viewers.*;
import org.eclipse.swt.graphics.Image;
import org.eclipse.jface.action.*;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.ui.*;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.SWT;
import org.eclipse.core.runtime.IAdaptable;

import de.topicmapslab.tmcledit.model.AssociationsType;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.OccurenceType;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.diagram.part.TmcleditDiagramEditor;

/**
 * This view renders the model behind a diagram. It is used to add model
 * elements to the diagram or get an overview of the model.
 * 
 * Elements which are removed from a diagram may still be in the model and this
 * view makes it possible to see the domain model.
 */

public class ModelView extends ViewPart implements IPartListener {
	private TreeViewer viewer;
	private ViewContentProvider contentProvider;
	private DrillDownAdapter drillDownAdapter;
	private Action action1;
	private Action action2;
	private Action doubleClickAction;

	/*
	 * The content provider class is responsible for providing objects to the
	 * view. It can wrap existing objects in adapters or simply return objects
	 * as-is. These objects may be sensitive to the current input of the view,
	 * or ignore it and always show the same content (like Task List, for
	 * example).
	 */

	class TreeObject implements IAdaptable {
		private String name;
		private TreeParent parent;
		final private EObject model;

		public TreeObject(String name, EObject model) {
			this.model = model;
			this.name = name;
		}

		public TreeObject(String name) {
			this.name = name;
			this.model = null;
		}

		public String getName() {
			return name;
		}

		public void setParent(TreeParent parent) {
			this.parent = parent;
		}

		public TreeParent getParent() {
			return parent;
		}

		public String toString() {
			return getName();
		}

		public EObject getModel() {
			return model;
		}

		@SuppressWarnings("unchecked")
		public Object getAdapter(Class key) {
			return null;
		}
	}

	class TreeParent extends TreeObject {
		private ArrayList<TreeObject> children;

		public TreeParent(String name) {
			super(name);
			children = new ArrayList<TreeObject>();
		}

		public void addChild(TreeObject child) {
			children.add(child);
			child.setParent(this);
		}

		public void removeChild(TreeObject child) {
			children.remove(child);
			child.setParent(null);
		}

		public TreeObject[] getChildren() {
			return (TreeObject[]) children.toArray(new TreeObject[children
					.size()]);
		}

		public boolean hasChildren() {
			return children.size() > 0;
		}
	}

	class ViewContentProvider implements IStructuredContentProvider,
			ITreeContentProvider {
		private TreeParent invisibleRoot;

		private TopicMapSchema currTms;

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

		public void initialize() {

			invisibleRoot = new TreeParent("");

			if (currTms != null)
				currTms.eAdapters().remove(tmsListener);

			// getting the model of the current editor
			IWorkbenchPage page = getSite().getWorkbenchWindow()
					.getActivePage();
			if (page != null) {
				IEditorPart ep = getSite().getWorkbenchWindow().getActivePage()
						.getActiveEditor();
				if ((ep != null) && (ep instanceof TmcleditDiagramEditor)) {

					TmcleditDiagramEditor te = (TmcleditDiagramEditor) ep;
					IDocumentProvider dp = te.getDocumentProvider();
					currTms = (TopicMapSchema) ((Diagram) dp.getDocument(
							te.getEditorInput()).getContent()).getElement();

					currTms.eAdapters().add(tmsListener);

					ttNode = new TreeParent("TopicTypes");
					rtNode = new TreeParent("RoleTypes");
					ntNode = new TreeParent("NameTypes");
					otNode = new TreeParent("OccurenceTypes");
					atNode = new TreeParent("AssociationTypes");

					invisibleRoot.addChild(ttNode);
					invisibleRoot.addChild(rtNode);
					invisibleRoot.addChild(ntNode);
					invisibleRoot.addChild(otNode);
					invisibleRoot.addChild(atNode);

					for (TopicType tt : currTms.getTopicTypes()) {
						addType(tt);
					}

					return;
				}
			}

			TreeParent root = new TreeParent("No Diagramm Editor Open");

			invisibleRoot.addChild(root);
		}

		private void addType(TopicType tt) {
			TreeObject to = new TreeObject(tt.getId(), tt);

			if (tt instanceof RoleType)
				rtNode.addChild(to);
			else if (tt instanceof NameType)
				ntNode.addChild(to);
			else if (tt instanceof OccurenceType)
				otNode.addChild(to);
			else if (tt instanceof AssociationsType)
				atNode.addChild(to);
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
				if (to.getModel().equals(tt))
					parent.removeChild(to);
			}
		}
	}

	class ViewLabelProvider extends LabelProvider {

		public String getText(Object obj) {
			return obj.toString();
		}

		public Image getImage(Object obj) {
			String imageKey = ISharedImages.IMG_OBJ_ELEMENT;
			if (obj instanceof TreeParent)
				imageKey = ISharedImages.IMG_OBJ_FOLDER;
			return PlatformUI.getWorkbench().getSharedImages().getImage(
					imageKey);
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
		viewer.setInput(getViewSite());

		// Create the help context id for the viewer's control
		PlatformUI.getWorkbench().getHelpSystem().setHelp(viewer.getControl(),
				"de.topicmapslab.tmcledit.extensions.viewer");
		makeActions();
		hookContextMenu();
		hookDoubleClickAction();
		contributeToActionBars();

		for (IWorkbenchPage page : getSite().getWorkbenchWindow().getPages())
			page.addPartListener(this);
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
		for (IWorkbenchPage page : getSite().getWorkbenchWindow().getPages())
			page.removePartListener(this);
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
				showMessage("Action 1 executed");
			}
		};
		action1.setText("Action 1");
		action1.setToolTipText("Action 1 tooltip");
		action1.setImageDescriptor(PlatformUI.getWorkbench().getSharedImages()
				.getImageDescriptor(ISharedImages.IMG_OBJS_INFO_TSK));

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
				showMessage("Double-click detected on " + obj.toString());
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

	@Override
	public void partActivated(IWorkbenchPart part) {
		contentProvider.initialize();
		viewer.setInput(getViewSite());
	}

	@Override
	public void partBroughtToTop(IWorkbenchPart part) {
	}

	@Override
	public void partClosed(IWorkbenchPart part) {
	}

	@Override
	public void partDeactivated(IWorkbenchPart part) {
	}

	@Override
	public void partOpened(IWorkbenchPart part) {
	}
}