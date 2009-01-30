/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveScopeConstraintsCommand extends AbstractCommand {

	private final ScopedTopicType scopedTopicType;
	private final List<ScopeConstraint> scope;
	
	
	public RemoveScopeConstraintsCommand(ScopedTopicType scopedTopicType,
			List<ScopeConstraint> scope) {
		super("Remove Scope");
		this.scopedTopicType = scopedTopicType;
		this.scope = scope;
	}

	@Override
	public void execute() {
		scopedTopicType.getScope().removeAll(scope);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		scopedTopicType.getScope().addAll(scope);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
