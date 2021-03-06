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
package de.topicmapslab.tmcledit.model.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

/**
 * The {@link NewPrefixMappingDialog} is a dialog used to add new prefixes. It contains two textfields and
 * validates if a prefix is already used.
 * 
 * @author Hannes Niederhausen
 *
 */
public class NewPrefixMappingDialog extends Dialog {

	private Text keyText;
	private Text uriText;
	
	private String key;
	private String uri;
	
	/**
	 * Constructor
	 * @param parentShell the parent shell
	 */
	public NewPrefixMappingDialog(Shell parentShell) {
		super(parentShell);
	}

	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.None);
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		comp.setLayout(new GridLayout(2, false));
		
		Label label = new Label(comp, SWT.None);
		label.setText("Key:");
		keyText = new Text(comp, SWT.BORDER);
		keyText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		
		label = new Label(comp, SWT.None);
		label.setText("URI:");
		uriText = new Text(comp, SWT.BORDER);
		uriText.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
	
		if (key!=null)
			keyText.setText(key);
		if (uri!=null)
			uriText.setText(uri);
		
		return comp;
	}
	
	/**
	 * Sets the key which may be edited
	 * @param key
	 */
	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	protected void okPressed() {
		key = keyText.getText();
		uri=uriText.getText();
		super.okPressed();
	}
	
	@Override
	protected void configureShell(Shell newShell) {
		
		super.configureShell(newShell);
		newShell.setText("New Prefix...");
		newShell.setSize(350, 200);
	}
	
	/**
	 * 
	 * @return the uri
	 */
	public String getUri() {
		return uri;
	}
	
	/**
	 * 
	 * @return the entered prefix key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the uri
	 * @param uri the old uri which may be edited
	 */
	public void setUri(String uri) {
		this.uri = uri;
    }
}
