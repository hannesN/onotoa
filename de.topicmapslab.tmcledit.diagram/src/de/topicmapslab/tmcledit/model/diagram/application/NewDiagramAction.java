package de.topicmapslab.tmcledit.model.diagram.application;

import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.wizard.WizardDialog;

import de.topicmapslab.tmcledit.model.diagram.part.TmcleditCreationWizard;

/**
 * @generated
 */
public class NewDiagramAction extends WorkbenchWindowActionDelegate {

	/**
	 * @generated
	 */
	public void run(IAction action) {
		TmcleditCreationWizard wizard = new TmcleditCreationWizard();
		wizard.init(getWindow().getWorkbench(), StructuredSelection.EMPTY);
		WizardDialog wizardDialog = new WizardDialog(
				getWindow().getShell(), wizard);
		wizardDialog.open();
	}
}