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
package de.topicmapslab.tmcledit.doc.dialogs;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;

/**
 * Dialog showing a website via browser widget
 * 
 * @author Hannes Niederhausen
 *
 */
public class BrowserDialog extends Dialog {

	protected Browser browser;
	private String url;
	private String title;
	private String content;
	
	/**
	 * @param parentShell
	 */
	public BrowserDialog(Shell parentShell) {
		this(parentShell, true);
		
	}
	
	/**
	 * Constructor
	 * @param parentShell the parent shell
	 * @param isModal flag whether the dialog is model
	 */
	public BrowserDialog(Shell parentShell, boolean isModal) {
		super(parentShell);
		if (!isModal)
			setShellStyle(SWT.DIALOG_TRIM  | SWT.MAX | SWT.RESIZE | SWT.MIN
					| getDefaultOrientation());
		
	}

	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createDialogArea(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected Control createDialogArea(Composite parent) {
		Composite comp = new Composite(parent, SWT.NONE);
		comp.setLayout(new FillLayout());
		comp.setLayoutData(new GridData(GridData.FILL_BOTH));
		browser = new Browser(comp, SWT.BORDER);
		if (url!=null)
			browser.setUrl(url);
		if (content!=null)
			browser.setText(content);
		return comp;
	}
	
	/**
	 * Sets the url for to show
	 * @param url the new url
	 */
	public void setUrl(String url) {
		this.url = url;
		if (browser!=null)
			browser.setUrl(url);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.dialogs.Dialog#createButtonsForButtonBar(org.eclipse.swt.widgets.Composite)
	 */
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		// create OK and Cancel buttons by default
		createButton(parent, IDialogConstants.OK_ID, "Close",
				true);
		
	}
	
	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
		if (browser!=null)
			browser.setText(content);
	}
	
	/* (non-Javadoc)
	 * @see org.eclipse.jface.window.Window#configureShell(org.eclipse.swt.widgets.Shell)
	 */
	@Override
	protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		
		if (title!=null)
			newShell.setText(title);
	}
	
	@Override
	protected Point getInitialSize() {
		return new Point(1024, 800);
	}


	/**
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
		if (getShell()!=null)
			getShell().setText(title);
	}
}
