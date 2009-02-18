/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.requests.DirectEditRequest;

import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;



/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceConstraintDirectEditPolicy extends AbstractDirectEditPolicy {

	@Override
	public Command getRenameCommand(Object model, DirectEditRequest request) {
		if (model instanceof OccurenceTypeConstraint)
			return new RenameTopicTypeCommand(((OccurenceTypeConstraint) model).getType(), getNewString(request));
		return null;
	}

}
