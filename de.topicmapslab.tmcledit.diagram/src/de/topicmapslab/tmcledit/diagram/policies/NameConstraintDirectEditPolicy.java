package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.requests.DirectEditRequest;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.commands.RenameConstraintCommand;

public class NameConstraintDirectEditPolicy extends AbstractDirectEditPolicy {

	@Override
	public Command getRenameCommand(Object model, DirectEditRequest request) {
		if (model instanceof AbstractConstraint) 
			return new RenameConstraintCommand((AbstractConstraint) model, getNewString(request));
		return null;
	}

}
