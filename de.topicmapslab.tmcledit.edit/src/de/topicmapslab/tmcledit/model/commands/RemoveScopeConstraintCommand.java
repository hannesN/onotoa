/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveScopeConstraintCommand extends AbstractCommand {

	private final ScopedTopicType scopedTopicType;
	private final ScopeConstraint scope;
	
	
	public RemoveScopeConstraintCommand(ScopedTopicType scopedTopicType,
			ScopeConstraint scope) {
		super("Remove Scope");
		this.scopedTopicType = scopedTopicType;
		this.scope = scope;
	}

	@Override
	public void execute() {
		scopedTopicType.getScope().remove(scope);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		scopedTopicType.getScope().add(scope);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
