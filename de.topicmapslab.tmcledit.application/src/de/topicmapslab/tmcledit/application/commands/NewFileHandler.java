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
package de.topicmapslab.tmcledit.application.commands;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.application.DiagramEditorActionBarAdvisor;

/**
 * @author Hannes Niederhausehn
 *
 */
public class NewFileHandler extends AbstractHandler {

	public Object execute(ExecutionEvent event) throws ExecutionException {

		IWorkbench workbench = PlatformUI.getWorkbench();
//		Shell shell = workbench.getActiveWorkbenchWindow().getShell();
//		
//		FileWizard wizard = new FileWizard(true);
//		WizardDialog dlg = new WizardDialog(shell, wizard);
//		dlg.setTitle("New Onotoa Model...");
//		if (dlg.open()==Dialog.OK) {
//			File file = new File(wizard.getPath());
//			if (file.exists()) {
//				if (MessageDialog.openQuestion(shell, "File already exists",
//								"A file with the chosen filename already exists. Do you want to overwrite it?")) {
//					file.delete();
//				} else {
//					return null;
//				}
//			}
//			
			DiagramEditorActionBarAdvisor.openModelView(workbench, "", true);
//		}
		return null;
	}

}
