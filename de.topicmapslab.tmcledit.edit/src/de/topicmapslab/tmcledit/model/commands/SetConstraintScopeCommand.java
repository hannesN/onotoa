package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedConstraint;

public class SetConstraintScopeCommand extends AbstractCommand {

	private final ScopedConstraint constraint;
	private final List<ScopeConstraint> newScope;

	private List<ScopeConstraint> oldScope;
	
	
	
	public SetConstraintScopeCommand(ScopedConstraint constraint,
			List<ScopeConstraint> newScope) {
		super("Set Scope");
		this.constraint = constraint;
		this.newScope = newScope;
	}

	@Override
	protected boolean prepare() {
		oldScope = new ArrayList<ScopeConstraint>();
		oldScope.addAll(constraint.getScope());
		return true;
	}
	
	@Override
	public void execute() {
		setScopeList(newScope);
	}

	private void setScopeList(List<ScopeConstraint> scopeList) {
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
