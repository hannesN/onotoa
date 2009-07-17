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

import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;

public class IdentifierConstraintModelPage extends AbstractConstraintModelPage {

	public IdentifierConstraintModelPage() {
		super("identifier constraint");
	}

	private CTabItem item;
	
	public Composite createPage(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));
		
		createCommonConstraintControls(comp, toolkit);
		
		
		return comp;
	}
	
	@Override
	protected void createItems(CTabFolder folder) {
		super.createItems(folder);
		item = new CTabItem(folder, SWT.None);
		item.setText("Constraint");
		item.setControl(createPage(folder));
	}
	
	@Override
	protected void setEnabled(boolean enabled) {
		item.getControl().setEnabled(enabled);
	}

	@Override
	public void setModel(Object model) {
		if (model instanceof SubjectLocatorConstraint)
			item.setText("Subject Locator");
		else
			item.setText("Subject Identifier");
		super.setModel(model);
	}
	
}
