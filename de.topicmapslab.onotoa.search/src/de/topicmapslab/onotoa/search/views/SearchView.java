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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
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
import de.topicmapslab.onotoa.search.container.AbstractContainer;
import de.topicmapslab.onotoa.search.container.BasicTopicTypeContainer;
import de.topicmapslab.onotoa.search.container.NeverUsedTopicsContainer;
import de.topicmapslab.onotoa.search.container.SubjectIdentifierContainer;
import de.topicmapslab.onotoa.search.container.SubjectLocatorContainer;
import de.topicmapslab.onotoa.search.container.TopicsWithoutIdentifierContainer;
import de.topicmapslab.onotoa.search.util.ImageCallBack;
import de.topicmapslab.onotoa.search.wrapper.IDoubleClickHandler;
import de.topicmapslab.onotoa.search.wrapper.IdentifierWrapper;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * View to present content.
 * 
 * @author Sebastian Lippert
 */

public class SearchView extends ViewPart {

	/**
	 * The ID of the input view as specified by the extension.
	 */
	public static final String ID = "de.topicmapslab.onotoa.search.views.SearchView";
	private AbstractContainer container;
	private TreeViewer viewer;
	private MenuManager menuMgr;

	/**
	 * This is a callback that will allow us to create the viewer and initialize
	 * it.
	 */
	public void createPartControl(Composite parent) {

		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout());

		Button refreshButton = new Button(comp, SWT.PUSH);
		refreshButton.setImage(ImageProvider.getImage("./reload.gif"));
		refreshButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateContent();
			}
		});

		AnnotationBindingFactory fac = new AnnotationBindingFactory();
		fac.addClass(IdentifierWrapper.class);
		fac.addClass(TopicTypeWrapper.class);
		fac.addClass(AbstractContainer.class);
		fac.addClass(BasicTopicTypeContainer.class);
		fac.addClass(NeverUsedTopicsContainer.class);
		fac.addClass(SubjectIdentifierContainer.class);
		fac.addClass(SubjectLocatorContainer.class);
		fac.addClass(TopicsWithoutIdentifierContainer.class);
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

	/**
	 * Add context menu.
	 * 
	 * @param actionList
	 *            the represents the menu
	 */

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

	/**
	 * Global registration of SeachView to enable undo and redo from everywhere
	 * at the Onotoa window.
	 */

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

	/**
	 * Remove context menu.
	 */

	public void removeContextMenu() {
		viewer.getControl().setMenu(null);
	}

	/**
	 * Set content of the TreeViewer and add possible adapters at container.
	 * 
	 * @param con
	 *            that holds the content
	 */

	public void setContent(AbstractContainer con) {

		// dispose() remove all adapters from former content
		if (container != null)
			container.dispose();

		container = con;

		// marriage of view and container
		container.setView(this);

		// nothing happens if container is a read-only
		container.addAdapter();
		viewer.setInput(con);
		viewer.expandAll();
		registerModelView();

	}

	/**
	 * {@inheritDoc}
	 */

	@Override
	public void init(IViewSite site) throws PartInitException {
		super.init(site);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void setFocus() {
		// Not in use
	}

	/**
	 * Get TreeViewer of the SearchView.
	 * 
	 * @return TreeViewer
	 */

	public TreeViewer getTreeViewer() {
		return viewer;
	}

	/**
	 * Update content.
	 */

	public void updateContent() {

		if (container != null) {
			container.dispose();
			container.refresh();
			container.addAdapter();
		}
		updateUI();

	}

	/**
	 * Refresh TreeViewer while content has changed.
	 */

	public void updateUI() {
		viewer.refresh();
	}

}