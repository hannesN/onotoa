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

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.application.DiagramEditorActionBarAdvisor;
import de.topicmapslab.tmcledit.model.preferences.RecentUsedManager;

/**
 * @author niederhausen
 *
 */
public class RecentUsedContributionItem extends ContributionItem {

	/**
	 * 
	 */
	public RecentUsedContributionItem() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param id
	 */
	public RecentUsedContributionItem(String id) {
		super(id);
	}
	
	@Override
	public void fill(Menu menu, int index) {
		int c = 0;
		for (final String f : RecentUsedManager.getFilesList()) {
			MenuItem item = new MenuItem(menu, SWT.PUSH);
			item.setText("&"+c+" "+f);
			item.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					IWorkbench workbench = PlatformUI.getWorkbench();
					DiagramEditorActionBarAdvisor.openModelView(workbench, f, false);
				}
			});
			c++;
		}
	}
	
	@Override
	public boolean isDynamic() {
		return true;
	}
}
