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
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.onotoa.search.util.SearchDataObject;

/**
 * Creates search dialog for TopicTypes.
 * 
 * This includes a basic and an optional advanced part. After all inputs are
 * done the class creates an returnable SearchDataObject which includes all
 * values from the user.
 * 
 * @author Sebastian Lippert
 */

public class TopicTypeSearchDialog extends Dialog {

	private TopicTypeSearchBasicPart basicPart;

	private String searchString = "";
	private String type = "Any";
	private boolean isCaseSensitive = false;
	private boolean isExactMatch = false;
	private boolean isRegExp = false;
	private boolean isAdvancedSearch = false;

	/**
	 * Constructor
	 * 
	 * @param parentShell
	 */
	public TopicTypeSearchDialog(Shell parentShell) {
		super(parentShell);
	}

	/**
	 * Creates the basic part of the search dialog.
	 * 
	 * @return the basic part
	 */

	@Override
	protected Control createDialogArea(Composite parent) {

		basicPart = new TopicTypeSearchBasicPart(parent);
		return basicPart.getComposite();

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

			searchString = basicPart.getNameFieldValue();
			type = basicPart.getTypeBoxValue();
			isCaseSensitive = basicPart.getCaseButtonValue();
			isExactMatch = basicPart.getMatchButtonValue();
			isRegExp = basicPart.getRegularButtonValue();

			setReturnCode(OK);
			close();

		}

		if (isAdvancedSearch) {

		}

	}

	/**
	 * Getter for SearchDataObject that holds all user input
	 * 
	 * @return SearchDataObject
	 */

	public SearchDataObject getSearchDataObject() {

		return new SearchDataObject(isAdvancedSearch, searchString, type, isCaseSensitive, isExactMatch, isRegExp);

	}

}
