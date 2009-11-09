/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class SetTypeAction extends AbstractCommandStackAction {

	protected SetTypeData data = new SetTypeData();

	public SetTypeAction(SetTypeData data) {
		super(data.editDomain.getEditingDomain().getCommandStack());
		this.data = data;
		init();
	}

	private void init() {
		String text = (data.type == null) ? "New Type..." : data.type.getName();
		setText(text);
	}

	@Override
	public void run() {
		CompoundCommand cmd = new CompoundCommand();

		if (data.typedConstraint instanceof AssociationTypeConstraint) {
			AssociationTypeConstraint atc = (AssociationTypeConstraint) data.typedConstraint;

			for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
				if (rpc.getRole() != null) {
					cmd.append(new GenericSetCommand(rpc,
							ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE, null));
				}
			}

		}
		cmd.append(getCommand());
		TopicType oldType = data.typedConstraint.getType();
		if (oldType!=null) {
			if (ModelIndexer.getConstraintIndexer().getConstraintsByType(oldType).size()==1) {
				cmd.append(new DeleteTopicTypeCommand(oldType, true));
			}
		}
		
		if (cmd.canExecute())
			getCommandStack().execute(cmd);
	}

	protected AbstractCommand getCommand() {
		return new GenericSetCommand(data.typedConstraint, data.featureId,
				data.type);
	}

}