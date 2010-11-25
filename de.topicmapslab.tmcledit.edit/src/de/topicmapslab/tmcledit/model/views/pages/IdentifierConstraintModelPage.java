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
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.util.TextObserver;

public class IdentifierConstraintModelPage extends AbstractCardinalityConstraintModelPage {

	private Text regExpText;
	
	public IdentifierConstraintModelPage() {
		super("identifier constraint");
	}

	private CTabItem item;
	
	public Composite createPage(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		Composite comp = toolkit.createComposite(parent);
		comp.setLayout(new GridLayout(2, false));
		
		createCommonConstraintControls(comp, toolkit);
		
		toolkit.createLabel(comp, "reg. exp");
		regExpText = toolkit.createText(comp, "", SWT.BORDER);
		regExpText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		TextObserver.observe(regExpText, this, ModelPackage.ABSTRACT_REG_EXP_CONSTRAINT__REGEXP);
		
		
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
		else if (model instanceof SubjectIdentifierConstraint)
			item.setText("Subject Identifier");
		else
			item.setText("Item Identifier");
		
		super.setModel(model);
	}
	
	@Override
	public void updateUI() {
		if (getModel()!=null) {
			if (regExpText!=null)
				regExpText.setText(((AbstractRegExpConstraint) getModel()).getRegexp());
		} else {
			if (regExpText!=null)
				regExpText.setText("");
		}
	    super.updateUI();
	}
}
