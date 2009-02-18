/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddOtherRolePlayerConstraintCommand extends AbstractCommand {
	private final RoleType roleType;
	private final OtherRolePlayerConstraint orpc;

	public AddOtherRolePlayerConstraintCommand(RoleType roleType,
			OtherRolePlayerConstraint orpc) {
		super();
		this.roleType = roleType;
		this.orpc = orpc;
	}

	@Override
	public void execute() {
		roleType.getOtherRoles().add(orpc);
	}

	@Override
	public void redo() {
		roleType.getOtherRoles().add(orpc);
	}

	@Override
	public void undo() {
		roleType.getOtherRoles().remove(orpc);
	}

	@Override
	protected boolean prepare() {
		return true;
	}

}
