/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.RoleConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveRoleConstraintCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final List<RoleConstraint> roles;
	
	public RemoveRoleConstraintCommand(AssociationType associationType,
			RoleConstraint role) {
		this(associationType, new ArrayList<RoleConstraint>());
		this.roles.add(role);
	}
	
	public RemoveRoleConstraintCommand(AssociationType associationType,
			List<RoleConstraint> roles) {
		super("Add Scope Constraints");
		this.associationType = associationType;
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		associationType.getRoles().removeAll(roles);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		associationType.getRoles().addAll(roles);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
