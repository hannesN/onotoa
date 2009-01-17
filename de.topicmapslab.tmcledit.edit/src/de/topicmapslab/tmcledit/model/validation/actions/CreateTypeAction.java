/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class CreateTypeAction extends TopicTypeAction {
	public CreateTypeAction(KindOfTopicType type) {
		super(type);
	}
	
	@Override
	public void run() {
		NewTopicTypeWizard wizard = new NewTopicTypeWizard();
		wizard.setDefaultType(getKindOfType());

		WizardDialog dlg = new WizardDialog(PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow().getShell(), wizard);
		
		if (dlg.open()==Dialog.OK) {
			TopicType tt = wizard.getNewTopicType();
			AbstractCommand cmd = getCommand(tt);
			if (cmd.canExecute())
				cmd.execute();
		}
	}

}
