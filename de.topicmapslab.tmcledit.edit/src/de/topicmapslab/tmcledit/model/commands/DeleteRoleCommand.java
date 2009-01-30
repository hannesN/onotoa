package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraints;
import de.topicmapslab.tmcledit.model.RolePlayerConstraints;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class DeleteRoleCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final RoleConstraints roleConstraints;
	
	private List<DeleteRolePlayerConstraintCommand> deletePlayerCmds = Collections.emptyList();
	
	public DeleteRoleCommand(
			AssociationType associationType,
			RoleConstraints roleConstraints) {
		super();
		this.associationType = associationType;
		this.roleConstraints = roleConstraints;
	}

	@Override
	public void execute() {
		for (DeleteRolePlayerConstraintCommand cmd : deletePlayerCmds)
			if (cmd.canExecute())
				cmd.execute();
		associationType.getRoles().remove(roleConstraints);
	}

	@Override
	public void redo() {
		for (DeleteRolePlayerConstraintCommand cmd : deletePlayerCmds)
			cmd.redo();
		associationType.getRoles().remove(roleConstraints);
	}

	@Override
	public void undo() {
		for (DeleteRolePlayerConstraintCommand cmd : deletePlayerCmds)
			if (cmd.canUndo())
				cmd.undo();		
	}
	
	@Override
	protected boolean prepare() {
		
		TopicMapSchema schema = ModelIndexer.getInstance().getTopicMapSchema();
		
		for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
			if (atc.getType().equals(associationType)) {
				for (RolePlayerConstraints rpc : atc.getPlayerConstraints()) {
					if (rpc.getRole().equals(roleConstraints)) {
						addCommand(atc, rpc);
					}
				}
			}
		}
		return true;
	}

	private void addCommand(AssociationTypeConstraint atc, RolePlayerConstraints rpc) {
		if (deletePlayerCmds==Collections.EMPTY_LIST)
			deletePlayerCmds = new ArrayList<DeleteRolePlayerConstraintCommand>();
		
		deletePlayerCmds.add(new DeleteRolePlayerConstraintCommand(atc, rpc));
	}
}
