package de.topicmapslab.onotoa.search.views;

import java.util.List;

import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.viewers.DoubleClickEvent;
import org.eclipse.jface.viewers.IDoubleClickListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.part.ViewPart;

import de.topicmapslab.kuria.annotation.AnnotationBindingFactory;
import de.topicmapslab.kuria.runtime.IBindingContainer;
import de.topicmapslab.kuria.swtgenerator.WidgetGenerator;
import de.topicmapslab.onotoa.search.util.ImageCallBack;
import de.topicmapslab.onotoa.search.wrapper.IDoubleClickHandler;
import de.topicmapslab.onotoa.search.wrapper.IdentifierWrapper;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * This sample class demonstrates how to plug-in a new workbench view. The view
 * shows data obtained from the model. The sample creates a dummy model on the
 * fly, but a real implementation would connect to the model available either in
 * this or another plug-in (e.g. the workspace). The view is connected to the
 * model using a content provider.
 * <p>
 * The view uses a label provider to define how model objects should be
 * presented in the view. Each view can present the same model objects using
 * different labels and icons, if needed. Alternatively, a single label provider
 * can be shared between views in order to ensure that objects of the same type
 * are presented in the same way everywhere.
 * <p>
 */

public class SearchView extends ViewPart {

	/**
	 * The ID of the view as specified by the extension.
	 */
	public static final String ID = "de.topicmapslab.onotoa.search.views.SearchView";
	private Container container;
	private TreeViewer viewer;
	private MenuManager menuMgr;

	// private IContextMenuListener contextMenu;

	/**
	 * The constructor.
	 */
	public SearchView() {
	}

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {

		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout());

		AnnotationBindingFactory fac = new AnnotationBindingFactory();
		fac.addClass(IdentifierWrapper.class);
		fac.addClass(TopicTypeWrapper.class);
		fac.addClass(Container.class);
		IBindingContainer bc = fac.getBindingContainer();

		WidgetGenerator gen = new WidgetGenerator(bc);
		WidgetGenerator.addImageCallback(new ImageCallBack());
		viewer = gen.generateTree(comp, true);
		viewer.getTree().setLayoutData(new GridData(GridData.FILL_BOTH));
		viewer.setInput(container);

		viewer.addDoubleClickListener(new IDoubleClickListener() {
			public void doubleClick(DoubleClickEvent event) {

				IStructuredSelection sel = (IStructuredSelection) event.getSelection();

				Object o = sel.getFirstElement();
				if (o instanceof IDoubleClickHandler) {
					((IDoubleClickHandler) o).doubleClickHappend();
				}
			}

		});

	}

	public void addContextMenu(final List<Action> actionList) {

		menuMgr = new MenuManager("PopupMenu");
		menuMgr.setRemoveAllWhenShown(true);
		menuMgr.addMenuListener(new IMenuListener() {
			public void menuAboutToShow(IMenuManager manager) {
				for (Action a : actionList) {
					manager.add(a);
				}
			}
		});

		Menu menu = menuMgr.createContextMenu(viewer.getControl());
		viewer.getControl().setMenu(menu);
		getSite().registerContextMenu(menuMgr, viewer);

	}

	private void registerModelView() {
		// register actions

		ModelView modelView = ModelView.getInstance();
		ActionRegistry ar = (ActionRegistry) modelView.getAdapter(ActionRegistry.class);
		if (ar != null) {
			IActionBars actionBars = getViewSite().getActionBars();

			String tmp = ActionFactory.UNDO.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

			tmp = ActionFactory.REDO.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

			tmp = ActionFactory.SAVE.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));

			tmp = ActionFactory.CLOSE.getId();
			actionBars.setGlobalActionHandler(tmp, (IAction) ar.getAction(tmp));
		}

	}

	public void removeContextMenu() {
		viewer.getControl().setMenu(null);
	}

	public void setContent(Container container) {
		this.container = container;
		viewer.setInput(container);
		viewer.expandAll();
		registerModelView();
	}

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.eclipse.ui.part.WorkbenchPart#setFocus()
	 */
	@Override
	public void setFocus() {
		// Not in use
	}

	public TreeViewer getTreeViewer() {
		return viewer;
	}

}