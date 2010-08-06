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
package de.topicmapslab.onotoa.search.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Group;

/**
 * @author sip
 * 
 */
public class BoxTopicTypeConstraints {

	private Composite comp;
	private Composite compConstraint;

	private String itemsAdvanced[] = { "Occurrence Constraint", "Name Constraint", "Role Constraint",
	        "Association Constraint" };

	public BoxTopicTypeConstraints(Composite parent) {

		comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		Group context = new Group(comp, SWT.NONE);
		context.setText("Constraints");
		context.setLayout(new GridLayout());
		context.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button addConstraint = new Button(context, SWT.PUSH);
		addConstraint.setText("Add Constraint");
		addConstraint.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addConstraint.setToolTipText("Add a Constraint to specify the search");

		compConstraint = new Composite(context, SWT.NONE);
		compConstraint.setLayout(new GridLayout());
		compConstraint.setLayoutData(new GridData(GridData.FILL_BOTH));

		hookAddButtonListener(addConstraint, compConstraint);

	}

	private Control createConstraintElement(Composite parent) {

		Composite compConstraintElement = new Composite(parent, SWT.NONE);
		compConstraintElement.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		compConstraintElement.setLayout(new GridLayout(3, false));

		Button removeConstraint = new Button(compConstraintElement, SWT.PUSH);
		removeConstraint.setText("-");
		removeConstraint.setToolTipText("Remove this Constraint");
		hookRemoveButtonListener(removeConstraint, parent);

		Combo typeOfTopicBox = new Combo(compConstraintElement, SWT.READ_ONLY);
		typeOfTopicBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		typeOfTopicBox.setItems(itemsAdvanced);

		Combo selectTypeBox = new Combo(compConstraintElement, SWT.READ_ONLY);
		selectTypeBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		selectTypeBox.setItems(itemsAdvanced);

		return compConstraintElement;
	}

	private void hookAddButtonListener(final Button button, final Composite parent) {

		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				parent.layout(new Control[] { createConstraintElement(compConstraint) });

			}
		});

	}

	private void hookRemoveButtonListener(final Button button, final Composite parent) {

		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				button.getParent().dispose();
				parent.layout(true);

			}

		});

	}

	public Composite getBox() {
		return this.comp;
	}

}
