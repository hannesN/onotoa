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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.views.pages;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.forms.widgets.FormToolkit;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class NameTypeModelPage extends AbstractRegExpTopicTypeModelPage {
	private Button uniqueButton;

	public NameTypeModelPage() {
		super("name type");
	}

	@Override
	protected void createAdditionalControls(Composite parent, FormToolkit toolkit) {
		toolkit.createLabel(parent, "Unique:");
		uniqueButton = toolkit.createButton(parent, "", SWT.CHECK);
		GridData gd = new GridData();
		gd.horizontalSpan = 2;
		uniqueButton.setLayoutData(gd);
		
		hookButtonListener();
		super.createAdditionalControls(parent, toolkit);
	}

	private void hookButtonListener() {
		uniqueButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if ((getModel()!=null) &&
					(uniqueButton.getSelection()!=getCastedModel().isUnique()) )
					getCommandStack().execute(
					        new GenericSetCommand(getModel(), ModelPackage.NAME_TYPE__UNIQUE, new Boolean(
					                uniqueButton.getSelection())));
			}
		});
	}
	
	private NameType getCastedModel() {
		return (NameType) getModel();
	}
	
	@Override
	protected int getRegExpFeatureId() {
		return ModelPackage.NAME_TYPE__REG_EXP;
	}
	
	@Override
	public void updateUI() {
		if (getModel()!=null) {
			uniqueButton.setSelection(getCastedModel().isUnique());
		} else {
			uniqueButton.setSelection(false);
		}
		
		super.updateUI();
	}
}
