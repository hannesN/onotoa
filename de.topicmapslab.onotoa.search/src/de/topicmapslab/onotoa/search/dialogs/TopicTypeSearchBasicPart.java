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

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author sip
 * 
 */
public class TopicTypeSearchBasicPart {

	private TopicTypeSearchAdvancedPart advancedTopic, advancedAssosiation;

	private Shell dockingShell;
	private Combo typeBox;
	private Text nameField;
	private Button caseButton, matchButton, regularButton;
	private Button checkSI, checkSL, advancedButton;
	private Composite comp;
	private Composite compTopic, compAssociation;

	private String itemsBasic[] = { "Any", "Topic Type", "Occurrence Type", "Name Type", "Role Type",
	        "Association Type" };
	private boolean isAdvanced = false;

	public TopicTypeSearchBasicPart(Composite parent) {

		Label label;
		Group group;
		GridData gridData;
		Composite buttonBar;

		dockingShell = new Shell();

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout());

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		group = new Group(comp, SWT.SHADOW_OUT);
		group.setLayout(new GridLayout(2, false));
		group.setLayoutData(gridData);
		group.setText("Search For");

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		nameField = new Text(group, SWT.SINGLE | SWT.BORDER);
		nameField.setLayoutData(gridData);

		label = new Label(group, SWT.NONE);
		label.setText("Type");

		typeBox = new Combo(group, SWT.NONE | SWT.READ_ONLY);
		typeBox.setItems(itemsBasic);
		typeBox.select(0);
		typeBox.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		hookTypeListener();

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		buttonBar = new Composite(group, SWT.NONE);
		buttonBar.setLayout(new GridLayout(2, false));
		buttonBar.setLayoutData(gridData);

		caseButton = new Button(buttonBar, SWT.CHECK);
		caseButton.setText("Case Sensitive");

		checkSI = new Button(buttonBar, SWT.CHECK);
		checkSI.setText("Check Subject Identifier");

		matchButton = new Button(buttonBar, SWT.CHECK);
		matchButton.setText("Exact Match");

		checkSL = new Button(buttonBar, SWT.CHECK);
		checkSL.setText("Check Subject Locator");

		regularButton = new Button(buttonBar, SWT.CHECK);
		regularButton.setText("Regular Expression");

		hookRegExpListener();

		advancedButton = new Button(comp, SWT.PUSH);
		advancedButton.setText("Advanced >>");
		advancedButton.setToolTipText("Enable advanced option to specify the seach");
		advancedButton.setEnabled(false);

		hookAdvancedListener();

		/*
		 * instantiate advanced parts and dock them to invisible shell to use
		 * them later in a dynamic way
		 */
		advancedTopic = new TopicTypeSearchAdvancedPart();
		advancedAssosiation = new TopicTypeSearchAdvancedPart();

		compTopic = advancedTopic.getAdvancedTopicComposite();
		compAssociation = advancedAssosiation.getAdvancedAssociationComposite();

	}

	private void hookAdvancedListener() {

		advancedButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				if (!isAdvanced) {
					isAdvanced = true;
					advancedButton.setText("Advanced <<");

					if (typeBox.getSelectionIndex() == KindOfTopicType.TOPIC_TYPE_VALUE + 1) {

						compAssociation.setParent(dockingShell);
						compTopic.setParent(comp);
						comp.layout(true);

					}
					if (typeBox.getSelectionIndex() == KindOfTopicType.ASSOCIATION_TYPE_VALUE + 1) {

						compTopic.setParent(dockingShell);
						compAssociation.setParent(comp);
						comp.layout(true);

					}

				} else {

					compAssociation.setParent(dockingShell);
					compTopic.setParent(dockingShell);

					isAdvanced = false;
					advancedButton.setText("Advanced >>");

				}

			}

		});

	}

	/**
	 * Listener for pressed dialog buttons
	 */

	private void hookRegExpListener() {

		regularButton.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				if (regularButton.getSelection()) {

					caseButton.setSelection(false);
					matchButton.setSelection(false);

					caseButton.setEnabled(false);
					matchButton.setEnabled(false);

				}

				if (!regularButton.getSelection()) {

					caseButton.setEnabled(true);
					matchButton.setEnabled(true);

				}

			}

		});
	}

	private void hookTypeListener() {

		typeBox.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				switch (typeBox.getSelectionIndex()) {

				case 1:
					advancedButton.setEnabled(true);

					if (isAdvanced) {

						compAssociation.setParent(dockingShell);
						compTopic.setParent(comp);
						comp.layout(true);
					}
					break;
				case 5:
					advancedButton.setEnabled(true);
					if (isAdvanced) {
						compTopic.setParent(dockingShell);
						compAssociation.setParent(comp);
						comp.layout(true);
					}
					break;
				default:

					compAssociation.setParent(dockingShell);
					compTopic.setParent(dockingShell);

					advancedButton.setEnabled(false);
					advancedButton.setText("Advanced >>");

					break;
				}

			}

		});

	}

	/**
	 * 
	 * @return the created composite
	 */
	public Composite getComposite() {
		return this.comp;
	}

	/**
	 * @return isAdvanced
	 */
	public boolean getIsAdvanced() {
		return isAdvanced;
	}

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

	/**
	 * @return the checkSI
	 */
	public boolean getCheckSubjectIdentifierValue() {
		return checkSI.getSelection();
	}

	/**
	 * @return the checkSL
	 */
	public boolean getCheckSubjectLocatorValue() {
		return checkSL.getSelection();
	}

	/**
	 * Getter for selected Topic Types of the advanced part of the search
	 * 
	 * @return List of selected Topic Types
	 */

	public List<TopicType> getSelectedTypes() {

		if (compAssociation.getParent() == dockingShell && compTopic.getParent() != dockingShell)
			return advancedTopic.getSelectedList();

		if (compTopic.getParent() == dockingShell && compAssociation.getParent() != dockingShell)
			return advancedAssosiation.getSelectedList();

		return null;
	}
}
