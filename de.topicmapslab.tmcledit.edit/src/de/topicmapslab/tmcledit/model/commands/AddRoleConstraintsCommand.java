/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.RoleConstraints;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddRoleConstraintsCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final List<RoleConstraints> roles;
	
	public AddRoleConstraintsCommand(AssociationType associationType,
			RoleConstraints role) {
		this(associationType, new ArrayList<RoleConstraints>());
		this.roles.add(role);
	}
	
	public AddRoleConstraintsCommand(AssociationType associationType,
			List<RoleConstraints> roles) {
		super("Add Scope Constraints");
		this.associationType = associationType;
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		associationType.getRoles().addAll(roles);
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
		associationType.getRoles().removeAll(roles);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
