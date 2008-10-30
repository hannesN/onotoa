package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopedConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class SetConstraintScopeCommand extends AbstractCommand {

	private final ScopedConstraint constraint;
	private final List<TopicType> newScope;

	private List<TopicType> oldScope;
	
	
	
	public SetConstraintScopeCommand(ScopedConstraint constraint,
			List<TopicType> newScope) {
		super("Set Scope");
		this.constraint = constraint;
		this.newScope = newScope;
	}

	@Override
	protected boolean prepare() {
		oldScope = new ArrayList<TopicType>();
		oldScope.addAll(constraint.getScope());
		return true;
	}
	
	@Override
	public void execute() {
		setScopeList(newScope);
	}

	private void setScopeList(List<TopicType> scopeList) {
		constraint.eSetDeliver(false);
		constraint.getScope().clear();
		constraint.eSetDeliver(true);
		constraint.getScope().addAll(scopeList);
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
