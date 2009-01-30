package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;

public class SetConstraintScopeCommand extends AbstractCommand {

	private final ScopedTopicType scopedTopicType;
	private final List<ScopeConstraint> newScope;

	private List<ScopeConstraint> oldScope;
	
	
	
	public SetConstraintScopeCommand(ScopedTopicType scopedTopicType,
			List<ScopeConstraint> newScope) {
		super("Set Scope");
		this.scopedTopicType = scopedTopicType;
		this.newScope = newScope;
	}

	@Override
	protected boolean prepare() {
		oldScope = new ArrayList<ScopeConstraint>();
		oldScope.addAll(scopedTopicType.getScope());
		return true;
	}
	
	@Override
	public void execute() {
		setScopeList(newScope);
	}

	private void setScopeList(List<ScopeConstraint> scopeList) {
		scopedTopicType.eSetDeliver(false);
		scopedTopicType.getScope().clear();
		scopedTopicType.eSetDeliver(true);
		scopedTopicType.getScope().addAll(scopeList);
	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		setScopeList(oldScope);
	}
}
