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
public class BoxAssociationTypeRoles {

	private Composite comp;
	private Composite compRoles;

	private String itemsAdvanced[] = { "Role #1", "Role #2", "Role #3", "Role #4", "Role #5" };

	public BoxAssociationTypeRoles(Composite parent) {

		GridData groupGrid;

		comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout());
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		groupGrid = new GridData(GridData.FILL_BOTH);
		groupGrid.horizontalSpan = 2;

		Group context = new Group(comp, SWT.NONE);
		context.setText("Roles");
		context.setLayout(new GridLayout());
		context.setLayoutData(new GridData(GridData.FILL_BOTH));

		Button addRole = new Button(context, SWT.PUSH);
		addRole.setText("Add Roles");
		addRole.setLayoutData(groupGrid = new GridData(GridData.FILL_HORIZONTAL));
		addRole.setToolTipText("Add a Role to specify the search");

		compRoles = new Composite(context, SWT.NONE);
		compRoles.setLayout(new GridLayout());
		compRoles.setLayoutData(new GridData(GridData.FILL_BOTH));

		hookAddButtonListener(addRole, compRoles);

	}

	private Control createRoleElement(Composite parent) {

		Composite compRoleElement = new Composite(parent, SWT.NONE);
		compRoleElement.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		compRoleElement.setLayout(new GridLayout(3, false));

		Button removeConstraint = new Button(compRoleElement, SWT.PUSH);
		removeConstraint.setText("-");
		removeConstraint.setToolTipText("Remove this Role");
		hookRemoveButtonListener(removeConstraint, parent);

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		Combo rolesBox = new Combo(compRoleElement, SWT.READ_ONLY);
		rolesBox.setLayoutData(gridData);
		rolesBox.setItems(itemsAdvanced);

		return compRoleElement;
	}

	private void hookAddButtonListener(final Button button, final Composite parent) {

		button.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				parent.layout(new Control[] { createRoleElement(compRoles) });

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
