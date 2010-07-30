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

import java.util.ArrayList;

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
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.part.PageBook;

/**
 * @author sip
 * 
 */

public class AdvancedTopicTypeSearchTab {

	private PageBook pageBook;
	private PageBook subPage;
	private Composite comp;
	private Text nameField;
	private Combo typeBox;
	private Button occType, nameType, roleType, assoType;
	private Button radioSub, radioSuper, radioNone;
	private Button noIdentifier, noLocator;
	private Button addConstraint;

	private String itemsAdvanced[] = { "TopicType", "OccurrenceType", "NameType", "RoleType", "AssociationType" };
	private int count = 1;

	public AdvancedTopicTypeSearchTab(Composite parent) {

		Label label;
		GridData gridData;

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));

		gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 1;

		label = new Label(comp, SWT.NONE);
		label.setText("Search");
		label.setLayoutData(gridData);

		label = new Label(comp, SWT.NONE);
		label.setText("Type");
		label.setLayoutData(gridData);

		nameField = new Text(comp, SWT.SINGLE | SWT.BORDER);
		gridData = new GridData(GridData.HORIZONTAL_ALIGN_FILL);
		nameField.setLayoutData(gridData);

		typeBox = new Combo(comp, SWT.NONE | SWT.READ_ONLY);
		typeBox.setItems(itemsAdvanced);
		typeBox.select(0);
		typeBox.setLayoutData(gridData);
		hookAdvancedTypeListener();

		gridData = new GridData(GridData.FILL_BOTH);
		gridData.horizontalSpan = 2;

		pageBook = new PageBook(comp, SWT.NONE);
		pageBook.setLayoutData(gridData);
		pageBook.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLACK));
		pageBook.showPage(createTopicTypePage(pageBook));

	}

	private void hookAdvancedTypeListener() {

		typeBox.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				switch (typeBox.getSelectionIndex()) {

				case 0:
					pageBook.showPage(createTopicTypePage(pageBook));
					break;
				case 1:
					pageBook.showPage(createOccurrenceTypePage(pageBook));
					break;
				case 2:
					pageBook.showPage(createNameTypePage(pageBook));
					break;
				case 3:
					pageBook.showPage(createRoleTypePage(pageBook));
					break;
				case 4:
					pageBook.showPage(createAssociationTypePage(pageBook));

					break;

				}

			}

		});

	}

	private Composite createTopicTypePage(Composite parent) {

		String[] items = new String[] { "abc", "def", "hij" };

		if (parent.getChildren().length != 0) {
			for (Control ctrl : ((PageBook) parent).getChildren()) {
				ctrl.dispose();
			}
		}

		GridData gridData = new GridData(GridData.FILL_HORIZONTAL);
		gridData.horizontalSpan = 2;

		GridData groupGrid;

		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new GridLayout(2, false));
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));

		groupGrid = new GridData(GridData.FILL_BOTH);
		groupGrid.horizontalSpan = 2;

		Group context = new Group(comp, SWT.NONE);
		context.setText("Constraints");
		context.setLayout(new GridLayout(2, false));
		context.setLayoutData(groupGrid);

		groupGrid = new GridData(GridData.FILL_BOTH);
		groupGrid.horizontalSpan = 2;

		subPage = new PageBook(context, SWT.NONE);
		subPage.setLayout(new GridLayout(1, true));
		subPage.setLayoutData(groupGrid);
		subPage.showPage(createSubPageTopicTypeDefault(subPage));
		// subPage.setTabList(new Control[] { createSubPageTopicType(subPage),
		// createSubPageTopicType(subPage) });

		// GridData gridData1 = new GridData(GridData.FILL_BOTH);
		// gridData.horizontalSpan = 2;
		//
		// subPage = new PageBook(context, SWT.NONE);
		// subPage.setLayout(new GridLayout(2, false));
		// subPage.setLayoutData(gridData1);
		// //subPage.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_DARK_MAGENTA));
		// subPage.showPage(createSubPageLine(subPage));

		Group hierarchy = new Group(comp, SWT.NONE);
		hierarchy.setText("Hierarchy");
		hierarchy.setLayout(new GridLayout());
		hierarchy.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

		radioNone = new Button(hierarchy, SWT.RADIO);
		radioNone.setText("None");
		radioNone.setSelection(true);

		radioSuper = new Button(hierarchy, SWT.RADIO);
		radioSuper.setText("is Supertype");

		radioSub = new Button(hierarchy, SWT.RADIO);
		radioSub.setText("is Subtype");

		hierarchy.pack();

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

		additional.pack();

		return comp;
	}

	/**
     * 
     */
	private void hookAddButtonListener(final Button b) {

		b.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				if (subPage.getChildren().length != 0) {
					for (Control ctrl : subPage.getChildren()) {
						ctrl.dispose();
					}
				}

				subPage.showPage(createSubPageTopicType(subPage));
				b.dispose();

			}
		});
		// TODO Auto-generated method stub

	}

	/**
	 * @param parent
	 * @return
	 */

	private Control createSubPageTopicType(PageBook parent) {

		final Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comp.setLayout(new GridLayout(3, false));
		// comp.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_GREEN));

		for (int i = 0; i < count; i++) {
			Button checkBox = new Button(comp, SWT.PUSH);
			checkBox.setText("-");
			checkBox.addSelectionListener(new SelectionAdapter() {

				public void widgetSelected(SelectionEvent e) {

					comp.dispose();
					count -= 2;
					subPage.showPage(createSubPageTopicType(subPage));

				}

			});

			Combo occList = new Combo(comp, SWT.READ_ONLY);
			occList.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			occList.setItems(itemsAdvanced);

			Combo oList = new Combo(comp, SWT.READ_ONLY);
			oList.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
			oList.setItems(itemsAdvanced);
		}

		addConstraint = new Button(comp, SWT.PUSH);
		addConstraint.setText("+");
		hookAddButtonListener(addConstraint);

		Label addLabel = new Label(comp, SWT.NONE);
		addLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addLabel.setText("click to add a constraint");

		// Button addConstraint = new Button(comp, SWT.PUSH);
		// addConstraint.setText("+");
		//
		// hookAddButtonListener(addConstraint);

		comp.pack();

		count++;
		return comp;
	}

	/**
	 * @param parent
	 * @return
	 */

	private Control createSubPageTopicTypeDefault(PageBook parent) {

		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		comp.setLayout(new GridLayout(2, false));

		addConstraint = new Button(comp, SWT.PUSH);
		addConstraint.setText("+");
		hookAddButtonListener(addConstraint);

		Label addLabel = new Label(comp, SWT.NONE);
		addLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		addLabel.setText("click to add a constraint");

		return comp;
	}

	private Composite createOccurrenceTypePage(Composite parent) {

		if (parent.getChildren().length != 0) {
			for (Control ctrl : ((PageBook) parent).getChildren()) {
				ctrl.dispose();
			}
		}

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));
		nameType = new Button(comp, SWT.NONE);
		nameType.setText("name");

		occType = new Button(comp, SWT.NONE);
		occType.setText("ooc");

		return comp;
	}

	private Composite createNameTypePage(Composite parent) {

		if (parent.getChildren().length != 0) {
			for (Control ctrl : ((PageBook) parent).getChildren()) {
				ctrl.dispose();
			}
		}

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));
		nameType = new Button(comp, SWT.NONE);
		nameType.setText("name");

		occType = new Button(comp, SWT.NONE);
		occType.setText("ooc");

		return comp;
	}

	private Composite createRoleTypePage(Composite parent) {

		if (parent.getChildren().length != 0) {
			for (Control ctrl : ((PageBook) parent).getChildren()) {
				ctrl.dispose();
			}
		}

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));
		nameType = new Button(comp, SWT.NONE);
		nameType.setText("name");

		occType = new Button(comp, SWT.NONE);
		occType.setText("ooc");

		return comp;
	}

	private Composite createAssociationTypePage(Composite parent) {

		if (parent.getChildren().length != 0) {
			for (Control ctrl : ((PageBook) parent).getChildren()) {
				ctrl.dispose();
			}
		}

		comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));
		nameType = new Button(comp, SWT.NONE);
		nameType.setText("name");

		occType = new Button(comp, SWT.NONE);
		occType.setText("ooc");

		return comp;
	}

	public Composite getComposite() {
		return this.comp;
	}

}
