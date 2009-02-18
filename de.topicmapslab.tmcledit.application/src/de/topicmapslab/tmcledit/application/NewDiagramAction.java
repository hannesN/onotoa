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