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
package de.topicmapslab.tmcledit.application;

import java.io.File;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.wizards.FileWizard;

/**
 */
public class NewDiagramAction extends Action {

	public NewDiagramAction() {
		setText("New..");
	}
	
	@Override
	public void run() {
		IWorkbench workbench = PlatformUI.getWorkbench();
		Shell shell = workbench.getActiveWorkbenchWindow().getShell();
		
		FileWizard wizard = new FileWizard(true);
		WizardDialog dlg = new WizardDialog(shell, wizard);
		if (dlg.open()==Dialog.OK) {
			File file = new File(wizard.getPath());
			if (file.exists()) {
				if (MessageDialog.openQuestion(shell, "File already exists",
								"A file with the chosen filename already exists. Do you want to overwrite it?")) {
					file.delete();
				} else {
					return;
				}
			}
			
			DiagramEditorActionBarAdvisor.openModelView(workbench, wizard.getPath(), true);
		}
		
	}
}