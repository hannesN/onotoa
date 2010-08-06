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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

import de.topicmapslab.onotoa.search.util.SearchDataObject;

/**
 * Creates search dialog for TopicTypes.
 * 
 * This includes a basic and an advanced search. After all inputs are done the
 * class creates an returnable SearchDataObject which includes all values from
 * the user.
 * 
 * @author Sebastian Lippert
 */

public class TopicTypeSearchDialog extends Dialog {

	private BasicTopicTypeSearchTab basicTab;
	private AdvancedTopicTypeSearchTab advancedTab;

	private boolean isAdvancedSearch = false;

	private String searchString = "";
	private String type = "Any";
	private boolean isCaseSensitive = false;
	private boolean isExactMatch = false;
	private boolean isRegExp = false;

	private TabFolder tabFolder;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 */
	public TopicTypeSearchDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Creates the main dialog with its two tabs for basic and advanced search.
	 */

	@Override
	protected Control createDialogArea(Composite parent) {

		// main composite
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new FillLayout());
		comp.setBackground(parent.getDisplay().getSystemColor(SWT.COLOR_BLUE));

		// tab folder
		tabFolder = new TabFolder(comp, SWT.NONE);
		hookSearchModusListener();

		// basic search tab
		TabItem basicTab = new TabItem(tabFolder, SWT.NONE);
		basicTab.setText("Basic");
		basicTab.setToolTipText("Basic search");
		basicTab.setControl(createBasicTab(tabFolder));

		// advanced search tab
		TabItem advancedTab = new TabItem(tabFolder, SWT.NONE);
		advancedTab.setText("Advanced");
		advancedTab.setToolTipText("Advanced search");
		advancedTab.setControl(createAdvancedTab(tabFolder));

		return comp;
	}

	/**
	 * Creates whole basic search mask.
	 * 
	 * @param parent
	 *            parent composite
	 * @return the basic search mask composite
	 */

	private Control createBasicTab(Composite parent) {

		basicTab = new BasicTopicTypeSearchTab(parent);
		return basicTab.getComposite();

	}

	private Control createAdvancedTab(Composite parent) {

		advancedTab = new AdvancedTopicTypeSearchTab(parent);
		return advancedTab.getComposite();

	}

	/**
	 * Listener for the chosen tab
	 */

	private void hookSearchModusListener() {

		tabFolder.addSelectionListener(new SelectionAdapter() {

			public void widgetSelected(SelectionEvent e) {

				if (tabFolder.getSelectionIndex() == 0)
					isAdvancedSearch = false;

				if (tabFolder.getSelectionIndex() == 1)
					isAdvancedSearch = true;

			}

		});
	}

	/**
	 * Creates button at the bottom of the dialog
	 */

	@Override
	protected void createButtonsForButtonBar(Composite parent) {

		createButton(parent, IDialogConstants.OK_ID, "Search", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

	}

	/**
	 * Shell configuration
	 */

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(450, 700);
	}

	/**
	 * Handler for the pressed OK button
	 */

	@Override
	protected void okPressed() {

		if (!isAdvancedSearch) {

			searchString = basicTab.getNameFieldValue();
			type = basicTab.getTypeBoxValue();
			isCaseSensitive = basicTab.getCaseButtonValue();
			isExactMatch = basicTab.getMatchButtonValue();
			isRegExp = basicTab.getRegularButtonValue();

			setReturnCode(OK);
			close();

		}

		if (isAdvancedSearch) {

		}

	}

	public SearchDataObject getSearchDataObject() {

		return new SearchDataObject(isAdvancedSearch, searchString, type, isCaseSensitive, isExactMatch, isRegExp);

	}

}
