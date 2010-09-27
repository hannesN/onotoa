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

import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.swt.graphics.Point;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.eclipse.ui.application.IWorkbenchWindowConfigurer;
import org.eclipse.ui.application.WorkbenchWindowAdvisor;

import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;


/**
 * @generated
 */
public class DiagramEditorWorkbenchWindowAdvisor extends WorkbenchWindowAdvisor {

	public static final String MODEL_VIEW_ID = "de.topicmapslab.tmcledit.extensions.views.ModelView";
	private String[] args;

	/**
	 * @generated
	 */
	public DiagramEditorWorkbenchWindowAdvisor(
			IWorkbenchWindowConfigurer configurer) {
		super(configurer);
	}

	/**
	 * @generated
	 */
	@Override
	public ActionBarAdvisor createActionBarAdvisor(
			IActionBarConfigurer configurer) {
		return new DiagramEditorActionBarAdvisor(configurer);
	}

	/**
	 * @generated
	 */
	@Override
	public void preWindowOpen() {
		IWorkbenchWindowConfigurer configurer = getWindowConfigurer();
		configurer.setInitialSize(new Point(1000, 700));
		configurer.setTitle(Messages.DiagramEditorWorkbenchWindowAdvisor_Title);
		configurer.setShowCoolBar(true);
		configurer.setShowStatusLine(true);
		
		int length = args.length;
		if (length==0)
			return;
		// temporary storing the filename in the preference store of the edit plugin
		TmcleditEditPlugin.getPlugin().getPreferenceStore().setValue("cmdLineFilename", args[length-1]);
	}

	public void setArguments(String[] args) {
		this.args = args;		
	}
	
	@Override
	public void postWindowOpen() {
		super.postWindowOpen();
		IContributionItem[] mItems, mSubItems;
		IMenuManager mm = getWindowConfigurer().getActionBarConfigurer()
				.getMenuManager();
		mItems = mm.getItems();
		for (int i = 0; i < mItems.length; i++) {
			if (mItems[i] instanceof MenuManager) {
				mSubItems = ((MenuManager) mItems[i]).getItems();
				for (int j = 0; j < mSubItems.length; j++) {
					if (mItems[i].getId().equals("file"))
						((MenuManager) mItems[i]).remove("org.eclipse.ui.openLocalFile");
				}
			}
		}
	}

}
