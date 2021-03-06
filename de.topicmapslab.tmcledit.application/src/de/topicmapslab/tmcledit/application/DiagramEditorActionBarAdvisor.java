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
package de.topicmapslab.tmcledit.application;


import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.GroupMarker;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarContributionItem;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchActionConstants;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ActionFactory.IWorkbenchAction;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.part.ViewPart;

/**
 * Action Bar Advisor for the Onotoa window
 * @author Hannes Niederhausen
 *
 */
public class DiagramEditorActionBarAdvisor extends ActionBarAdvisor {

	private ActionFactory.IWorkbenchAction lockToolBarAction;

	private ActionFactory.IWorkbenchAction toggleCoolbarAction;

	private IWorkbenchAction aboutAction;

	private IWorkbenchAction prefAction;

	/**
	 * Constructor
	 * @param configurer the {@link IActionBarConfigurer} for the window
	 */
	public DiagramEditorActionBarAdvisor(IActionBarConfigurer configurer) {
		super(configurer);
	}

	@Override
	protected void makeActions(IWorkbenchWindow window) {
		toggleCoolbarAction = ActionFactory.TOGGLE_COOLBAR.create(window);
		register(toggleCoolbarAction);
		lockToolBarAction = ActionFactory.LOCK_TOOL_BAR.create(window);
		register(lockToolBarAction);

		register(ActionFactory.CLOSE.create(window));
		
		register(ActionFactory.SAVE.create(window));

		register(ActionFactory.SAVE_AS.create(window));

		register(ActionFactory.SAVE_ALL.create(window));

		register(ActionFactory.QUIT.create(window));

		register(ActionFactory.UNDO.create(window));

		register(ActionFactory.REDO.create(window));

		register(ActionFactory.CUT.create(window));

		register(ActionFactory.COPY.create(window));

		register(ActionFactory.PASTE.create(window));

		register(ActionFactory.DELETE.create(window));

		register(ActionFactory.SELECT_ALL.create(window));

		register(ActionFactory.PRINT.create(window));
		
		register(ActionFactory.RESET_PERSPECTIVE.create(window));
		
		register(ActionFactory.IMPORT.create(window));
		
		aboutAction = ActionFactory.ABOUT.create(window);
		register(aboutAction);
		
		register(ActionFactory.EXPORT.create(window));
		
		prefAction = ActionFactory.PREFERENCES.create(window);
		prefAction.setText(getPointText(prefAction.getText()));
		register(prefAction);
		
		
	}

	@Override
	protected void fillMenuBar(IMenuManager menu) {

		createFileMenu(menu);
		createEditMenu(menu);
		
		menu.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		createWindowMenu(menu);
		createHelpMenu(menu);
	}

	private void createHelpMenu(IMenuManager menu) {
		IMenuManager menuX = new MenuManager(
				Messages.ApplicationMenuName_Help,
				IWorkbenchActionConstants.M_HELP);

		menuX.add(new GroupMarker(IWorkbenchActionConstants.HELP_START));
		menuX.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		
		menuX.add(new GroupMarker(IWorkbenchActionConstants.HELP_END));
		
		ActionContributionItem aboutItem = new ActionContributionItem(aboutAction);
		aboutItem.setVisible(!isMac());
		menuX.add(aboutItem);

		menu.add(menuX);
	}

	private void createWindowMenu(IMenuManager menu) {
		IMenuManager menuX = new MenuManager(
				Messages.ApplicationMenuName_Window,
				IWorkbenchActionConstants.M_WINDOW);
		

		
		menuX.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));
		menuX.add(getAction(ActionFactory.RESET_PERSPECTIVE.getId()));
		
		ActionContributionItem prefItem = new ActionContributionItem(prefAction);
		prefItem.setVisible(!isMac());
		menuX.add(prefItem);

		menu.add(menuX);
	}

	private void createEditMenu(IMenuManager menu) {
		IMenuManager menuX = new MenuManager(
				Messages.ApplicationMenuName_Edit,
				IWorkbenchActionConstants.M_EDIT);

		menuX.add(new GroupMarker(IWorkbenchActionConstants.EDIT_START));

		menuX.add(getAction(ActionFactory.UNDO.getId()));

		menuX.add(getAction(ActionFactory.REDO.getId()));

		menuX.add(new GroupMarker(IWorkbenchActionConstants.UNDO_EXT));

		menuX.add(new Separator());

		menuX.add(getAction(ActionFactory.CUT.getId()));

		menuX.add(getAction(ActionFactory.COPY.getId()));

		menuX.add(getAction(ActionFactory.PASTE.getId()));

		menuX.add(new GroupMarker(IWorkbenchActionConstants.CUT_EXT));

		menuX.add(new Separator());

		menuX.add(getAction(ActionFactory.DELETE.getId()));

		menuX.add(getAction(ActionFactory.SELECT_ALL.getId()));

		menuX.add(new Separator());

		menuX.add(new GroupMarker(IWorkbenchActionConstants.ADD_EXT));

		menuX.add(new GroupMarker(IWorkbenchActionConstants.EDIT_END));

		menuX.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
		menu.add(menuX);
	}

	
	private void createFileMenu(IMenuManager menu) {
		IMenuManager menuX = new MenuManager(
				Messages.ApplicationMenuName_File,
				IWorkbenchActionConstants.M_FILE);

		menuX.add(new GroupMarker(IWorkbenchActionConstants.FILE_START));

		menuX.add(new Separator());

		menuX.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		menuX.add(new Separator());

		menuX.add(getAction(ActionFactory.CLOSE.getId()));

		menuX.add(new Separator());

		menuX.add(getAction(ActionFactory.SAVE.getId()));

		menuX.add(getAction(ActionFactory.SAVE_AS.getId()));

		menuX.add(getAction(ActionFactory.SAVE_ALL.getId()));

		menuX.add(new Separator());
		
		menuX.add(getAction(ActionFactory.IMPORT.getId()));
		menuX.add(getAction(ActionFactory.EXPORT.getId()));
		menuX.add(new Separator());
		menuX.add(getAction(ActionFactory.QUIT.getId()));

		menuX.add(new Separator());
		menuX.add(new GroupMarker("recent"));
		
		menuX.add(new GroupMarker(IWorkbenchActionConstants.FILE_END));
		menu.add(menuX);
	}
	
	private boolean isMac() {
		return ( ("carbon".equals(SWT.getPlatform())) || ("cocoa".equals(SWT.getPlatform())) );
	}
	
	@Override
	protected void fillCoolBar(ICoolBarManager toolBar) {
		IMenuManager popUpMenu = new MenuManager();
		popUpMenu.add(new ActionContributionItem(lockToolBarAction));
		popUpMenu.add(new ActionContributionItem(toggleCoolbarAction));
		toolBar.setContextMenuManager(popUpMenu);

		toolBar.add(new GroupMarker("group.file"));

		{
			IToolBarManager toolBarX = new ToolBarManager();

			toolBarX.add(new Separator(IWorkbenchActionConstants.NEW_GROUP));

			toolBarX.add(new GroupMarker(IWorkbenchActionConstants.NEW_EXT));

			toolBarX.add(new GroupMarker(IWorkbenchActionConstants.SAVE_GROUP));

			toolBarX.add(getAction(ActionFactory.SAVE.getId()));

			toolBarX.add(new GroupMarker(IWorkbenchActionConstants.SAVE_EXT));

			toolBarX.add(getAction(ActionFactory.PRINT.getId()));

			toolBarX.add(new GroupMarker(IWorkbenchActionConstants.PRINT_EXT));

			toolBarX.add(new Separator(IWorkbenchActionConstants.MB_ADDITIONS));
			toolBar.add(new ToolBarContributionItem(toolBarX,
					IWorkbenchActionConstants.TOOLBAR_FILE));
		}

		toolBar.add(new GroupMarker(IWorkbenchActionConstants.MB_ADDITIONS));

		toolBar.add(new GroupMarker("group.nav"));

		toolBar.add(new GroupMarker(IWorkbenchActionConstants.GROUP_EDITOR));

		
		
		toolBar.add(new GroupMarker(IWorkbenchActionConstants.GROUP_HELP));

		{
			IToolBarManager toolBarX = new ToolBarManager();

			toolBarX.add(new Separator(IWorkbenchActionConstants.GROUP_HELP));

			toolBarX.add(new GroupMarker(IWorkbenchActionConstants.GROUP_APP));
			toolBar.add(new ToolBarContributionItem(toolBarX,
					IWorkbenchActionConstants.TOOLBAR_HELP));
		}
	}

	/**
	 * Opens the model view
	 * @param workbench the workbench 
	 * @param filename the filename to open
	 * @param newFile flag whether its a new file
	 * @return <code>true</code>
	 */
	public static boolean openModelView(IWorkbench workbench, String filename, boolean newFile) {
		IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
		IWorkbenchPage page = workbenchWindow.getActivePage();
		
		try {
			String modelViewId = "de.topicmapslab.tmcledit.extensions.views.ModelView";
			page.showView(modelViewId);
			ViewPart modelView =  (ViewPart) page.findView(modelViewId);
			if (modelView!=null) {
				String key = (newFile) ? "newfilename" : "filename";
				modelView.setPartProperty(key, filename);
			}
			
			
		} catch (PartInitException e) {
			throw new RuntimeException(e);
		}
		
		return true;
	}

	private String getPointText(String s) {
		if (s.endsWith("..."))
			return s;
		else
			return s+"...";
	}
}
