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
public class BoxRoleTypeUseCases {

	private Composite comp;
	private Composite compTopicType;
	private Composite compAssociationType;
	private String itemsAdvanced[] = { "Topic Type #1", "Topic Type #2", "Topic Type #3", "Topic Type #4",
	        "Topic Type #5" };
	private Group contextOne;

	public BoxRoleTypeUseCases(Composite parent) {

		comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, true));
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		contextOne = new Group(comp, SWT.NONE);
		contextOne.setText("Players");
		contextOne.setLayout(new GridLayout());
		contextOne.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button addTopicType = new Button(contextOne, SWT.PUSH);
		addTopicType.setText("Add Roles");
		addTopicType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addTopicType.setToolTipText("Add Topic Type that plays this role to specify the search");

		compTopicType = new Composite(contextOne, SWT.NONE);
		compTopicType.setLayout(new GridLayout());
		compTopicType.setLayoutData(new GridData(GridData.FILL_BOTH));

		hookAddButtonListener(addTopicType, compTopicType);

		Group contextTwo = new Group(comp, SWT.NONE);
		contextTwo.setText("Associations");
		contextTwo.setLayout(new GridLayout());
		contextTwo.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button addAssociationType = new Button(contextTwo, SWT.PUSH);
		addAssociationType.setText("Add Association");
		addAssociationType.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addAssociationType.setToolTipText("Add Association Type uses this Role to specify the search");

		compAssociationType = new Composite(contextTwo, SWT.NONE);
		compAssociationType.setLayout(new GridLayout());
		compAssociationType.setLayoutData(new GridData(GridData.FILL_BOTH));

		hookAddButtonListener(addAssociationType, compAssociationType);

	}

	private Control createTopicTypeElement(Composite parent) {

		Composite compRoleElement = new Composite(parent, SWT.NONE);
		compRoleElement.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		compRoleElement.setLayout(new GridLayout(2, false));

		Button removeConstraint = new Button(compRoleElement, SWT.PUSH);
		removeConstraint.setText("-");
		removeConstraint.setToolTipText("Remove this Topic Type");
		hookRemoveButtonListener(removeConstraint, parent);

		Combo topicTypeBox = new Combo(compRoleElement, SWT.READ_ONLY);
		topicTypeBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		topicTypeBox.setItems(itemsAdvanced);

		return compRoleElement;
	}

	private Control createAssociationTypeElement(Composite parent) {

		Composite compRoleElement = new Composite(parent, SWT.NONE);
		compRoleElement.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		compRoleElement.setLayout(new GridLayout(2, false));

		Button removeConstraint = new Button(compRoleElement, SWT.PUSH);
		removeConstraint.setText("-");
		removeConstraint.setToolTipText("Remove this Association Type");
		hookRemoveButtonListener(removeConstraint, parent);

		Combo topicTypeBox = new Combo(compRoleElement, SWT.READ_ONLY);
		topicTypeBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		topicTypeBox.setItems(itemsAdvanced);

		return compRoleElement;
	}

	private void hookAddButtonListener(final Button button, final Composite parent) {

		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				if (((Group) button.getParent()).getText().equals("Players"))
					parent.layout(new Control[] { createTopicTypeElement(compTopicType) });

				if (((Group) button.getParent()).getText().equals("Associations"))
					parent.layout(new Control[] { createAssociationTypeElement(compAssociationType) });

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
