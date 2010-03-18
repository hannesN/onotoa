/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.wizard.WizardDialog;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.commands.AddRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.SetRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.dialogs.NewTopicTypeWizard;

public class SetRoleAction extends AbstractCommandStackAction {

	private SetRoleData data = new SetRoleData();

	public SetRoleAction(SetRoleData data) {
		super(data.editDomain.getEditingDomain().getCommandStack());
		this.data = data;
		init();
	}

	private void init() {
		String text = (data.role == null) ? "New Role..." : data.role
				.getName();
		setText(text);
	}

	@Override
	public void run() {
		CompoundCommand cmd = new CompoundCommand();
		if (data.role == null) {
			Command c = createRoleCommand();
			if (c != null)
				cmd.append(c);
			else 
				return;
		}

		Command c = createRoleConstraintCommand();
		if (c != null)
			cmd.append(c);

		c = new SetRoleConstraintCommand(data.rpc, data.rc);
		cmd.append(c);

		getCommandStack().execute(cmd);
	}

	private Command createRoleConstraintCommand() {
		AssociationType at = (AssociationType) ((AssociationTypeConstraint) data.rpc
				.eContainer()).getType();
		for (RoleConstraint rc : at.getRoles()) {
			if (rc.getType().equals(data.role)) {
				data.rc = rc;
				return null;
			}
		}
		RoleConstraint rc = ModelFactory.eINSTANCE.createRoleConstraint();
		rc.setType(data.role);
		data.rc = rc;
		AddRoleConstraintCommand c = new AddRoleConstraintCommand(at, rc);

		return c;
	}

	private Command createRoleCommand() {
		NewTopicTypeWizard tt = new NewTopicTypeWizard(
				KindOfTopicType.ROLE_TYPE);
		WizardDialog dlg = new WizardDialog(DiagramActivator.getCurrentShell(), tt);
		if (dlg.open() != Dialog.OK) {
			return null;
		}

		CreateTopicTypeCommand cmd = new CreateTopicTypeCommand(
				data.schema, tt.getNewTopicType());

		data.role = (RoleType) tt.getNewTopicType();
		return cmd;
	}
}