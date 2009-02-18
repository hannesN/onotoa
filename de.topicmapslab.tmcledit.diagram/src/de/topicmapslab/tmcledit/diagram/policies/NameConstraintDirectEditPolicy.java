package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.requests.DirectEditRequest;

public class NameConstraintDirectEditPolicy extends AbstractDirectEditPolicy {

	@Override
	public Command getRenameCommand(Object model, DirectEditRequest request) {

//		if (model instanceof AbstractConstraint) 
//			return new RenameConstraintCommand((AbstractConstraint) model, getNewString(request));
		return null;
	}

}
