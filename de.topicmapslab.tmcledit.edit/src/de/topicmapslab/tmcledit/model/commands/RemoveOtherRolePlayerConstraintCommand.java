/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveOtherRolePlayerConstraintCommand extends AbstractCommand {
	private final RoleType roleType;
	private final List<OtherRolePlayerConstraint> constraintList;
	
	public RemoveOtherRolePlayerConstraintCommand(RoleType roleType,
			List<OtherRolePlayerConstraint> constraintList) {
		super();
		this.roleType = roleType;
		this.constraintList = constraintList;
	}
	
	@Override
	public void execute() {
		roleType.getOtherRoles().removeAll(constraintList);
	}
	
	@Override
	public void redo() {
		roleType.getOtherRoles().removeAll(constraintList);		
	}
	
	@Override
	public void undo() {
		roleType.getOtherRoles().addAll(constraintList);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
