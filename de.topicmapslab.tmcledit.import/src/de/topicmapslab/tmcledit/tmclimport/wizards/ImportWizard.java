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

import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.ui.IImportWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.tmclimport.builder.OnotoaBuilder;

public class ImportWizard extends Wizard implements IImportWizard {
	
	ImportWizardPage mainPage;

	public ImportWizard() {
		super();
	}

	public boolean performFinish() {
		OnotoaBuilder builder = new OnotoaBuilder(mainPage.getFile());
		
		File file = builder.getFile();

		try {
			IWorkbenchPage activePage = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			ModelView mv = (ModelView) activePage.findView(ModelView.ID);
			if (mv==null) {
				// TODO look at msg
				System.out.println("Error, please show a dialog");
			}
			
			mv.setFilename("", true, file);
			
			
			activePage.activate(mv);
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		return true;
	}
	 
	public void init(IWorkbench workbench, IStructuredSelection selection) {
		setWindowTitle("TMCL Schema Import Wizard"); //NON-NLS-1
		setNeedsProgressMonitor(true);
		mainPage = new ImportWizardPage("Import TMCL schema",selection); //NON-NLS-1
	}
	
	/* (non-Javadoc)
     * @see org.eclipse.jface.wizard.IWizard#addPages()
     */
    public void addPages() {
        super.addPages(); 
        addPage(mainPage);        
    }

}
