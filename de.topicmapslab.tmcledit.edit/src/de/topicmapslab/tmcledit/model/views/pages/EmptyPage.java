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

package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

public class EmptyPage extends AbstractEMFModelPage {

	public EmptyPage() {
		super("empty");
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		FormToolkit toolkit = new FormToolkit(folder.getDisplay());
		CTabItem item = new CTabItem(folder, SWT.NONE);

		Composite comp = toolkit.createComposite(folder);
		comp.setLayout(new GridLayout());
		toolkit.createLabel(comp, "No Item Selected");
		
		item.setControl(comp);
	}
	
	@Override
	public boolean canAnnotate() {
	    return false;
	}
	
	@Override
	protected boolean hasDocumentation() {
		return false;
	}
	
	@Override
	public void setFocus() {
	}

	@Override
	public void updateUI() {
	}

	public void notifyChanged(Notification notification) {
	}
	
	@Override
	protected void setEnabled(boolean enabled) {
	}
}