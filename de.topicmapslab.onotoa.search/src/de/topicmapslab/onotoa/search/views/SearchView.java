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
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
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
import de.topicmapslab.onotoa.search.util.ImageConstants;
import de.topicmapslab.onotoa.search.util.ImageProvider;
import de.topicmapslab.onotoa.search.wrapper.AssociationTypeConstraintWrapper;
import de.topicmapslab.onotoa.search.wrapper.IDoubleClickHandler;
import de.topicmapslab.onotoa.search.wrapper.IdentifierWrapper;
import de.topicmapslab.onotoa.search.wrapper.KindOfUseWrapper;
import de.topicmapslab.onotoa.search.wrapper.TopicTypeWrapper;
import de.topicmapslab.onotoa.search.wrapper.UseWrapper;
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
		refreshButton.setImage(ImageProvider.getImage(ImageConstants.ICON_RELOAD));
		refreshButton.setToolTipText("Refresh the search results");
		refreshButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				updateContent();
			}
		});

		AnnotationBindingFactory fac = new AnnotationBindingFactory();
		fac.addClass(IdentifierWrapper.class);
		fac.addClass(TopicTypeWrapper.class);
		fac.addClass(KindOfUseWrapper.class);
		fac.addClass(AssociationTypeConstraintWrapper.class);
		fac.addClass(UseWrapper.class);
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

		/*
		 * this listener is used to abort the context menu while clicking on non
		 * TopicType based entries (like the root or kind of use informations in
		 * the use finder view
		 * 
		 * Maybe some day a public method for more customization is needed.
		 */
		viewer.getControl().addMouseListener(new MouseListener() {

			public void mouseUp(MouseEvent e) {
			}

			/*
			 * check the mouseDown of the right button event. (the mouseUp event
			 * is catched by the manager for the context menu and isn't useable
			 * at this point
			 */
			public void mouseDown(MouseEvent e) {

				// check if right mouse button was pressed
				// should be tested for all os, I think this is to lazy.
				if (e.button == 3) {
					IStructuredSelection sel = (IStructuredSelection) viewer.getSelection();
					Object o = sel.getFirstElement();

					// check if menu for this selection exists, to avoid
					// nullPointerException
					if (viewer.getControl().getMenu() != null) {
						/*
						 * only wrappers with a TopicType inside implements
						 * IDoubleClickHandler to open the Property Page by
						 * double clicking. We use this to ensure that only for
						 * this nodes the context menu is visible
						 */
						if (!(o instanceof IDoubleClickHandler))
							menuMgr.getMenu().setVisible(false);
						else
							menuMgr.getMenu().setVisible(true);
					}
				}
			}

			public void mouseDoubleClick(MouseEvent e) {
				// implemented otherwise to avoid reaction one other double
				// clicks than with the left mouse button
			}
		});
	}

	/**
	 * Add context menu.
	 * 
	 * @param actionList
	 *            List of actions that build the menu
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

		// expand only level 1 and 2 to keep the tree clearly arranged
		viewer.expandToLevel(2);
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