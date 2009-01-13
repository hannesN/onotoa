/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveScopeConstraintCommand extends AbstractCommand {

	private final ScopedConstraint constraint;
	private final ScopeConstraint scope;
	
	
	public RemoveScopeConstraintCommand(ScopedConstraint constraint,
			ScopeConstraint scope) {
		super("Remove Scope");
		this.constraint = constraint;
		this.scope = scope;
	}

	@Override
	public void execute() {
		constraint.getScope().remove(scope);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		constraint.getScope().add(scope);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
