/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddScopeConstraintsCommand extends AbstractCommand {

	private final ScopedConstraint constraint;
	private final List<ScopeConstraint> scopes;
	
	
	
	public AddScopeConstraintsCommand(ScopedConstraint constraint,
			List<ScopeConstraint> scopes) {
		super("Add Scope Constraints");
		this.constraint = constraint;
		this.scopes = scopes;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		constraint.getScope().addAll(scopes);
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
		constraint.getScope().removeAll(scopes);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
