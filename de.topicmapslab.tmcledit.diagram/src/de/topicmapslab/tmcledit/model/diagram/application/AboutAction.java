package de.topicmapslab.tmcledit.model.diagram.application;

import org.eclipse.emf.common.ui.action.WorkbenchWindowActionDelegate;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.dialogs.MessageDialog;

import de.topicmapslab.tmcledit.model.diagram.part.Messages;

/**
 * @generated
 */
public class AboutAction extends WorkbenchWindowActionDelegate {

	/**
	 * @generated
	 */
	public void run(IAction action) {
		MessageDialog.openInformation(getWindow().getShell(),
				Messages.DiagramEditorActionBarAdvisor_AboutDialogTitle,
				Messages.DiagramEditorActionBarAdvisor_AboutDialogMessage);
	}
}