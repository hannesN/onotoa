/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.gef.ui.actions.ActionBarContributor;
import org.eclipse.gef.ui.actions.GEFActionConstants;
import org.eclipse.gef.ui.actions.ZoomComboContributionItem;
import org.eclipse.gef.ui.actions.ZoomInRetargetAction;
import org.eclipse.gef.ui.actions.ZoomOutRetargetAction;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.ui.IWorkbenchActionConstants;

/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLEditorActionBarContributor extends ActionBarContributor {
	@Override
	public void contributeToToolBar(IToolBarManager toolBarManager) {
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_IN));
		toolBarManager.add(new ZoomComboContributionItem(getPage()));
		toolBarManager.add(getAction(GEFActionConstants.ZOOM_OUT));
	}
	
	@Override
	public void contributeToMenu(IMenuManager menuManager) {
		super.contributeToMenu(menuManager);

		MenuManager viewMenu = new MenuManager("&View");
	    viewMenu.add(getAction(GEFActionConstants.ZOOM_IN));
	    viewMenu.add(getAction(GEFActionConstants.ZOOM_OUT));
	    menuManager.insertAfter(IWorkbenchActionConstants.M_EDIT, viewMenu);

	}

	@Override
	protected void buildActions() {
		addRetargetAction(new ZoomInRetargetAction());
		addRetargetAction(new ZoomOutRetargetAction());
	}

	@Override
	protected void declareGlobalActionKeys() {
	}
}
