/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;

public class CreateTopicAction extends Action {
	/**
	 * 
	 */
	private final ModelView modelView;

	/**
	 * @param modelView
	 */
	public CreateTopicAction(ModelView modelView) {
		this.modelView = modelView;
	}

	@Override
	public String getText() {
		return "Create Topic Type";
	}

	@Override
	public void run() {
		NewTopicTypeWizard wizard = new NewTopicTypeWizard();
		wizard.setDefaultType(KindOfTopicType.TOPIC_TYPE);

		WizardDialog dlg = new WizardDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), wizard);
		
		if (dlg.open()==Dialog.OK) {
			this.modelView.getEditingDomain().getCommandStack().execute(
					new CreateTopicTypeCommand(this.modelView
							.getCurrentTopicMapSchema(), wizard.getNewTopicType()));
		}

	}
}