/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetRoleConstraintCommand extends AbstractCommand {

	private final RoleConstraint newConstraint;
	private final RoleConstraint oldConstraint;
	
	private final RolePlayerConstraint rolePlayerConstraint;
	
	public SetRoleConstraintCommand(RolePlayerConstraint rolePlayerConstraint,
			RoleConstraint newConstraint) {
		super();
		this.rolePlayerConstraint = rolePlayerConstraint;
		this.newConstraint = newConstraint;
		this.oldConstraint = rolePlayerConstraint.getRole();
	}

		
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		rolePlayerConstraint.setRole(newConstraint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	@Override
	public void redo() {
		rolePlayerConstraint.setRole(newConstraint);
	}
	
	@Override
	public void undo() {
		rolePlayerConstraint.setRole(oldConstraint);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
