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

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.views.extension.AbstractModelPage;

/**
 * The empty property page
 * 
 * @author Hannes Niederhausen
 *
 */
public class EmptyPage extends AbstractModelPage {

	/**
	 * Constructor
	 */
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
	public void setFocus() {
	}

	
	

	public void aboutToHide() {
    }

	public void updateUI() {
    }
	
	@Override
	public void setModel(Object model) {
	}
	
	@Override
	public Object getModel() {
	    return super.getModel();
	}
	
}