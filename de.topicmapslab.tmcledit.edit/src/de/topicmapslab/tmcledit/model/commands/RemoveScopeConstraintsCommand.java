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
public class RemoveScopeConstraintsCommand extends AbstractCommand {

	private final ScopedConstraint constraint;
	private final List<ScopeConstraint> scope;
	
	
	public RemoveScopeConstraintsCommand(ScopedConstraint constraint,
			List<ScopeConstraint> scope) {
		super("Remove Scope");
		this.constraint = constraint;
		this.scope = scope;
	}

	@Override
	public void execute() {
		constraint.getScope().removeAll(scope);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		constraint.getScope().addAll(scope);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
