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
package de.topicmapslab.tmcledit.extensions.views.pages;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.forms.widgets.Section;

import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;

public class IdentifierConstraintModelPage extends AbstractConstraintModelPage {

	public IdentifierConstraintModelPage() {
		super("identifier constraint");
	}

	private Section section;
	
	@Override
	public void createControl(Composite parent) {
		FormToolkit toolkit = new FormToolkit(parent.getDisplay());
		
		section = toolkit.createSection(parent, Section.EXPANDED
				| Section.TITLE_BAR);
		section.setText("Constraint");

		Composite comp = toolkit.createComposite(section);
		comp.setLayout(new GridLayout(2, false));
		
		createCommonConstraintControls(comp, toolkit);
		
		section.setClient(comp);
		setControl(section);
	}

	@Override
	public void setModel(Object model) {
		if (model instanceof SubjectLocatorConstraint)
			section.setText("Subject Locator");
		else
			section.setText("Subject Identifier");
		super.setModel(model);
	}
	
}
