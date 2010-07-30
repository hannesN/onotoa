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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

/**
 * @author sip
 * 
 */
public class BasicTopicTypeSearchTab {

	private Combo typeBox;
	private Button caseButton, matchButton, regularButton;
	private String itemsBasic[] = { "Any", "TopicType", "OccurrenceType", "NameType", "RoleType", "AssociationType" };
	private Composite comp;

	public BasicTopicTypeSearchTab(Composite parent) {

		Label label;
		GridData gridData;

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));

		label = new Label(comp, SWT.NONE);
		label.setText("Search");

		nameField = new Text(comp, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		nameField.setLayoutData(gridData);

		label = new Label(comp, SWT.NONE);
		label.setText("Type");

		typeBox = new Combo(comp, SWT.NONE | SWT.READ_ONLY);
		typeBox.setItems(itemsBasic);
		typeBox.select(0);
		typeBox.setLayoutData(gridData);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		caseButton = new Button(comp, SWT.CHECK);
		caseButton.setText("Case Sensitive");
		caseButton.setLayoutData(gridData);

		matchButton = new Button(comp, SWT.CHECK);
		matchButton.setText("Exact Match");
		matchButton.setLayoutData(gridData);

		regularButton = new Button(comp, SWT.CHECK);
		regularButton.setText("Regular Expression");
		regularButton.setLayoutData(gridData);
		hookButtonSelectionListener();

	}

	/**
	 * Listener for pressed dialog buttons
	 */

	private void hookButtonSelectionListener() {

		regularButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {
				if (e.widget == regularButton && regularButton.getSelection() == true) {

					caseButton.setSelection(false);
					matchButton.setSelection(false);

					caseButton.setEnabled(false);
					matchButton.setEnabled(false);

				}

				if (e.widget == regularButton && regularButton.getSelection() == false) {

					caseButton.setEnabled(true);
					matchButton.setEnabled(true);

				}
			}

		});
	}

	public Composite getComposite() {
		return this.comp;
	}

	private Text nameField;

	/**
	 * @return the nameField
	 */
	public String getNameFieldValue() {
		return nameField.getText();
	}

	/**
	 * @return the typeBox
	 */
	public String getTypeBoxValue() {
		return itemsBasic[typeBox.getSelectionIndex()];
	}

	/**
	 * @return the caseButton
	 */
	public boolean getCaseButtonValue() {
		return caseButton.getSelection();
	}

	/**
	 * @return the matchButton
	 */
	public boolean getMatchButtonValue() {
		return matchButton.getSelection();
	}

	/**
	 * @return the regularButton
	 */
	public boolean getRegularButtonValue() {
		return regularButton.getSelection();
	}
}
