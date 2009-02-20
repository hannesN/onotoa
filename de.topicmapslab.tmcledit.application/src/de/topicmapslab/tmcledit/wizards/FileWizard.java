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
package de.topicmapslab.tmcledit.wizards;

import org.eclipse.jface.wizard.Wizard;

/**
 * @author Hannes Niederhausen
 *
 */
public class FileWizard extends Wizard {

	private FileWizardPage page1;

	private final boolean newFile;
	
	

	public FileWizard(boolean newFile) {
		super();
		this.newFile = newFile;
	}

	@Override
	public void addPages() {
		String title = (newFile) ? "New File.." : "OpenFile";
		page1 = new FileWizardPage(title);
		addPage(page1);
		
	}
	
	public String getPath() {
		return page1.getPath();
	}
	
	@Override
	public boolean performFinish() {
		return true;
	}

}
