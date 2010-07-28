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
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;
import org.eclipse.swt.widgets.Text;

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

	private boolean isAdvancedSearch = false;

	private String searchString = "";
	private String type = "Any";
	private boolean isCaseSensitive = false;
	private boolean isExactMatch = false;
	private boolean isRegExp = false;

	private TabFolder tabFolder;
	private Text nameField;
	private Combo typeBox;
	private Button caseButton, matchButton, regularButton;
	private String items[] = { "Any", "TopicType", "OccurrenceType", "NameType", "RoleType", "AssociationType" };

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

		return comp;
	}

	/**
	 * Creates whole basic search mask. 
	 * 
	 * @param parent parent composite
	 * @return the basic search mask composite
	 */

	private Control createBasicTab(Composite parent) {

		Label label;
		GridData gridData;

		Composite comp = new Composite(parent, SWT.NONE);
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
		typeBox.setItems(items);
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

		return comp;
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
		newShell.setSize(400, 400);
	}

	/**
	 * Handler for the pressed OK button 
	 */

	@Override
	protected void okPressed() {

		searchString = nameField.getText();
		type = items[typeBox.getSelectionIndex()];
		isCaseSensitive = caseButton.getSelection();
		isExactMatch = matchButton.getSelection();
		isRegExp = regularButton.getSelection();

		setReturnCode(OK);
		close();
	}

	/**
	 * @return the isAdvancedSearch
	 */

	public boolean getIsAdvancedSearch() {
		return isAdvancedSearch;
	}

	/**
	 * @return the searchString
	 */

	public String getSearchString() {
		return searchString;
	}

	/**
	 * @return the type
	 */

	public String getType() {
		return type;
	}

	/**
	 * @return the isCaseSensitive
	 */

	public boolean getIsCaseSensitive() {
		return isCaseSensitive;
	}

	/**
	 * @return the isExactMatch
	 */

	public boolean getIsExactMatch() {
		return isExactMatch;
	}

	/**
	 * @return the isRegExp
	 */

	public boolean getIsRegExp() {
		return isRegExp;
	}

	/**
	 * 
	 * @return
	 */

	public SearchDataObject getSearchDataObject() {

		return new SearchDataObject(isAdvancedSearch, searchString, type, isCaseSensitive, isExactMatch, isRegExp);

	}

}
