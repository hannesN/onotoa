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
package de.topicmapslab.tmcledit.application.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.menus.ExtensionContributionFactory;
import org.eclipse.ui.menus.IContributionRoot;
import org.eclipse.ui.services.IServiceLocator;

import de.topicmapslab.tmcledit.application.DiagramEditorActionBarAdvisor;
import de.topicmapslab.tmcledit.application.DiagramEditorWorkbenchAdvisor;
import de.topicmapslab.tmcledit.application.DiagramEditorWorkbenchWindowAdvisor;
import de.topicmapslab.tmcledit.model.preferences.RecentUsedManager;

/**
 * @author niederhausen
 *
 */
public class MenuDefinition extends ExtensionContributionFactory {

	/**
	 * 
	 */
	public MenuDefinition() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.menus.AbstractContributionFactory#createContributionItems(org.eclipse.ui.services.IServiceLocator, org.eclipse.ui.menus.IContributionRoot)
	 */
	@Override
	public void createContributionItems(IServiceLocator serviceLocator,
			IContributionRoot additions) {
		additions.addContributionItem(new RecentUsedContributionItem(), null);
		
	}

	
}
