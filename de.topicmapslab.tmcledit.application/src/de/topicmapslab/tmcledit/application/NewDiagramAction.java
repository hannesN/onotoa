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

import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;

import de.topicmapslab.tmcledit.wizards.FileWizard;

/**
 */
public class NewDiagramAction extends WorkbenchWindowActionDelegate {

	/**
	 * @generated NOT
	 */
	public void run(IAction action) {
		
		FileWizard wizard = new FileWizard(true);
		WizardDialog dlg = new WizardDialog(getWindow().getShell(), wizard);
		if (dlg.open()==Dialog.OK) {
			
			DiagramEditorActionBarAdvisor.openModelView(getWindow().getWorkbench(), wizard.getPath(), true);
		}
		
	}
}