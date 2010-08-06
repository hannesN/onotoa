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
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.PageBook;

/**
 * @author sip
 * 
 */

public class AdvancedTopicTypeSearchTab {

	private Composite topicTypePage;
	private Composite occurrenceTypePage;
	private Composite namePage;
	private Composite rolePage;
	private Composite associationPage;

	private PageBook pageBook;
	private Composite comp;
	private Text nameField;
	private Combo typeBox;

	private Button selectTypeButton, specifyTypeButton;
	private Button radioSub, radioSuper, radioNone;
	private Button noIdentifier, noLocator;
	private Button caseButton, matchButton, regularButton;

	private String itemsAdvanced[] = { "TopicType", "OccurrenceType", "NameType", "RoleType", "AssociationType" };

	public AdvancedTopicTypeSearchTab(Composite parent) {

		Label label;
		GridData gridData;

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comp.setLayout(new GridLayout(2, false));

		label = new Label(comp, SWT.NONE);
		label.setText("Search");

		nameField = new Text(comp, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		nameField.setLayoutData(gridData);

		label = new Label(comp, SWT.NONE);
		label.setText("Type");

		typeBox = new Combo(comp, SWT.NONE | SWT.READ_ONLY);
		typeBox.setItems(itemsAdvanced);
		typeBox.select(0);
		gridData = new GridData(GridData.FILL_HORIZONTAL);
		typeBox.setLayoutData(gridData);

		hookAdvancedTypeListener();

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		Composite buttonBar = new Composite(comp, SWT.NONE);
		buttonBar.setLayout(new GridLayout(3, false));
		buttonBar.setLayoutData(gridData);

		caseButton = new Button(buttonBar, SWT.CHECK);
		caseButton.setText("Case Sensitive");

		matchButton = new Button(buttonBar, SWT.CHECK);
		matchButton.setText("Exact Match");

		regularButton = new Button(buttonBar, SWT.CHECK);
		regularButton.setText("Regular Expression");

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;

		pageBook = new PageBook(comp, SWT.NONE);
		pageBook.setLayoutData(gridData);
		pageBook.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));

		BoxTopicTypeConstraints box = new BoxTopicTypeConstraints(pageBook);
		topicTypePage = box.getBox();
		pageBook.showPage(topicTypePage);

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		Composite compBottom = new Composite(comp, SWT.NONE);
		compBottom.setLayout(new GridLayout(2, true));
		compBottom.setLayoutData(gridData);
		createHierarchyMenu(compBottom);
		createAdditionalMenu(compBottom);

	}

	private void hookAdvancedTypeListener() {

		typeBox.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				switch (typeBox.getSelectionIndex()) {

				case 0:
					if (topicTypePage == null || topicTypePage.isDisposed()) {
						BoxTopicTypeConstraints box = new BoxTopicTypeConstraints(pageBook);
						topicTypePage = box.getBox();
					}
					pageBook.showPage(topicTypePage);
					break;
				case 1:
					if (occurrenceTypePage == null || occurrenceTypePage.isDisposed()) {
						BoxTopicTypeConstraints box = new BoxTopicTypeConstraints(pageBook);
						occurrenceTypePage = box.getBox();
					}
					pageBook.showPage(occurrenceTypePage);
					break;
				case 2:
					pageBook.showPage(createNameTypePage(pageBook));
					break;
				case 3:
					if (rolePage == null || rolePage.isDisposed()) {
						BoxRoleTypeUseCases box = new BoxRoleTypeUseCases(pageBook);
						rolePage = box.getBox();
					}
					pageBook.showPage(rolePage);
					break;
				case 4:
					if (associationPage == null || associationPage.isDisposed()) {
						BoxAssociationTypeRoles box = new BoxAssociationTypeRoles(pageBook);
						associationPage = box.getBox();
					}
					pageBook.showPage(associationPage);
					break;

				}

			}

		});

	}

	/**
	 * @param comp
	 */

	private void createAdditionalMenu(Composite comp) {

		Group additional = new Group(comp, SWT.NONE);
		additional.setText("Additional");
		additional.setLayout(new GridLayout());
		additional.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		noIdentifier = new Button(additional, SWT.CHECK);
		noIdentifier.setText("has no SubjectIdentifier");

		noLocator = new Button(additional, SWT.CHECK);
		noLocator.setText("has no SubjectLocator");

		Button dummyButton = new Button(additional, SWT.CHECK);
		dummyButton.setText("Dummy button");
		dummyButton.setVisible(false);

	}

	/**
	 * @param comp
	 */

	private void createHierarchyMenu(Composite comp) {

		Group hierarchy = new Group(comp, SWT.NONE);
		hierarchy.setText("Hierarchy");
		hierarchy.setLayout(new GridLayout());
		hierarchy.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		radioNone = new Button(hierarchy, SWT.CHECK);
		radioNone.setText("None");
		radioNone.setSelection(true);

		radioSuper = new Button(hierarchy, SWT.CHECK);
		radioSuper.setText("is Supertype");

		radioSub = new Button(hierarchy, SWT.CHECK);
		radioSub.setText("is Subtype");

	}

	private Composite createNameTypePage(Composite parent) {

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));
		specifyTypeButton = new Button(comp, SWT.NONE);
		specifyTypeButton.setText("name");

		selectTypeButton = new Button(comp, SWT.NONE);
		selectTypeButton.setText("ooc");

		return comp;
	}

	public Composite getComposite() {
		return this.comp;
	}

}
