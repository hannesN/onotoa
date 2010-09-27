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

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author sip
 * 
 */
public class CleanSchemaDialog extends Dialog {

	private final List<TopicType> unusedTypesList;
	private CleanSchemaComposite comp;

	/**
	 * @param parentShell
	 */
	public CleanSchemaDialog(Shell parentShell, List<TopicType> unusedTypesList) {
		super(parentShell);

		this.unusedTypesList = unusedTypesList;
	}

	@Override
	protected Control createDialogArea(Composite parent) {

		comp = new CleanSchemaComposite(parent, unusedTypesList);
		return comp.getComposite();

	}

	/**
	 * Creates button at the bottom of the dialog
	 */

	@Override
	protected void createButtonsForButtonBar(Composite parent) {

		createButton(parent, IDialogConstants.OK_ID, "Clean", true);
		createButton(parent, IDialogConstants.CANCEL_ID, IDialogConstants.CANCEL_LABEL, false);

	}

	/**
	 * Shell configuration
	 */

	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setSize(450, 500);
	}

	/**
	 * Getter for cleaning list of opicTypes
	 * 
	 * @return list with TopicTypes
	 */
	public List<TopicType> getCleanList() {
		return comp.getCleanList();
	}

}
