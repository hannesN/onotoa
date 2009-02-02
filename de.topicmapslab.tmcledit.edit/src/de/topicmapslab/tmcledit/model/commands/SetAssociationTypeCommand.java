/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetAssociationTypeCommand extends AbstractCommand {

	private final TopicType newType;
	private final TopicType oldType;
	
	private final AssociationTypeConstraint constraint;
	private List<DeleteRolePlayerConstraintCommand> cmds = Collections.emptyList();
	
	
	public SetAssociationTypeCommand(AssociationTypeConstraint constraint,
			TopicType newType) {
		super();
		this.constraint = constraint;
		this.newType = newType;
		this.oldType = constraint.getType();
	}

	@Override
	public void execute() {
		for (AbstractCommand cmd : cmds) {
			if (cmd.canExecute())
				cmd.execute();
		}
		constraint.setType(newType);
	}

	@Override
	public void redo() {
		for (AbstractCommand cmd : cmds) {
			cmd.redo();
		}
		constraint.setType(newType);
	}
	
	@Override
	public void undo() {
		for (AbstractCommand cmd : cmds) {
			cmd.undo();
		}
		constraint.setType(oldType);
	}

	@Override
	protected boolean prepare() {
		if (constraint.getPlayerConstraints().size()>0)
			cmds = new ArrayList<DeleteRolePlayerConstraintCommand>();
		
		for (RolePlayerConstraint rpc : constraint.getPlayerConstraints()) {
			cmds.add(new DeleteRolePlayerConstraintCommand(constraint, rpc));
		}
		return true;
	}
}
