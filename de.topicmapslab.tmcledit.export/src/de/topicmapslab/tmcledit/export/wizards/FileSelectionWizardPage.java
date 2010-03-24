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
package de.topicmapslab.tmcledit.export.wizards;

import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

public class FileSelectionWizardPage extends WizardPage {
	private Text text;

	private String[] fileExtensions = {".ctm", ".xtm"};
	
	public FileSelectionWizardPage() {
		super("File Selection");
		
	}

	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.None);
		comp.setLayout(new GridLayout(3, false));
		
		Label l = new Label(comp, SWT.NONE);
		l.setText("&Filename");
		
		text = new Text(comp, SWT.BORDER);
		text.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String tmp = text.getText();
				for (String s : fileExtensions) {
					if (tmp.endsWith(s.substring(1)))
							return;
				}
				text.setText(tmp+fileExtensions[0].substring(1));
			}
		});
		
		Button browseButton = new Button(comp, SWT.PUSH);
		browseButton.setText("...");
		browseButton.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog dlg = new FileDialog(text.getShell(), SWT.SAVE);
				dlg.setFilterExtensions(fileExtensions);
				String file = dlg.open();
				if (file!=null) {
					if (!file.endsWith(fileExtensions[0].substring(1)))
						file+=fileExtensions[0].substring(1);
					text.setText(file);
				}
			}
		});
		
		addAdditionalWidgets(comp);
		
		setControl(comp);
	}

	public void setFileExtensions(String[] fileExtensions) {
		this.fileExtensions = fileExtensions;
	}
	
	public void addAdditionalWidgets(Composite parent) {
		
	}
	
	public String getFilename() {
		return text.getText();
	}
}
