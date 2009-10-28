/*******************************************************************************
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     IBM Corporation - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.tmclimport.wizards;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.preference.FileFieldEditor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;


public class ImportWizardPage extends WizardPage {
	
	protected FileFieldEditor sourceEditor;
	
	protected FileFieldEditor targetEditor;
	
	private String file;
	
	private String newFile;

	public ImportWizardPage(String pageName, IStructuredSelection selection) {
		super(pageName);
		setTitle(pageName); //NON-NLS-1
		setDescription("Import a TMCL schema"); //NON-NLS-1
	}

	protected void createAdvancedControls(Composite parent) {
		Composite fileSelectionArea = new Composite(parent, SWT.NONE);
		GridData fileSelectionData = new GridData(GridData.GRAB_HORIZONTAL
				| GridData.FILL_HORIZONTAL);
		fileSelectionArea.setLayoutData(fileSelectionData);

		GridLayout fileSelectionLayout = new GridLayout();
		fileSelectionLayout.numColumns = 3;
		fileSelectionLayout.makeColumnsEqualWidth = false;
		fileSelectionLayout.marginWidth = 0;
		fileSelectionLayout.marginHeight = 0;
		fileSelectionArea.setLayout(fileSelectionLayout);
		
		sourceEditor = new FileFieldEditor("fileSelect","Select Source File: ",fileSelectionArea); //NON-NLS-1 //NON-NLS-2
		sourceEditor.getTextControl(fileSelectionArea).addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				IPath path = new Path(ImportWizardPage.this.sourceEditor.getStringValue());
				file = path.toOSString();
			}
		});
		String[] extensions = new String[] { "*.ctm;*.xtm" }; //NON-NLS-1
		sourceEditor.setFileExtensions(extensions);
		fileSelectionArea.moveAbove(null);
	
		targetEditor = new FileFieldEditor("fileSelect","Select Target File: ",fileSelectionArea); //NON-NLS-1 //NON-NLS-2
		targetEditor.getTextControl(fileSelectionArea).addModifyListener(new ModifyListener(){
			public void modifyText(ModifyEvent e) {
				IPath path = new Path(ImportWizardPage.this.targetEditor.getStringValue());
				newFile = path.toOSString();
			}
		});
		extensions = new String[] { "*.ono" }; //NON-NLS-1
		targetEditor.setFileExtensions(extensions);
		fileSelectionArea.moveAbove(null);
		
		
		// TODO remove
		sourceEditor.setStringValue("/home/mai00ckx/Desktop/test.ctm");
		targetEditor.setStringValue("/home/mai00ckx/Desktop/test.ono");
		
	}
	
	protected void createLinkTarget() {
	}

	protected InputStream getInitialContents() {
		try {
			return new FileInputStream(new File(sourceEditor.getStringValue()));
		} catch (FileNotFoundException e) {
			return null;
		}
	}

	public String getFile() {
		return file;
	}
	
	public String getNewFile() {
		return newFile;
	}
	
	protected String getNewFileLabel() {
		return "New File Name:"; //NON-NLS-1
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.dialogs.WizardNewFileCreationPage#validateLinkedResource()
	 */
	protected IStatus validateLinkedResource() {
		return new Status(IStatus.OK, "de.topicmapslab.tmcledit.tmclimport", IStatus.OK, "", null); //NON-NLS-1 //NON-NLS-2
	}

	public void createControl(Composite parent) {
		Composite comp = new Composite(parent, SWT.None);
		comp.setLayout(new GridLayout());
		createAdvancedControls(comp);
	
		setControl(comp);
	}
}
