package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.commands.RenameConstraintCommand;

public class NameConstraintDirectEditPolicy extends AbstractDirectEditPolicy {

	@Override
	public Command getRenameCommand(Object model, String name) {
		if (model instanceof AbstractConstraint) 
			return new RenameConstraintCommand((AbstractConstraint) model, name);
		return null;
	}

}
