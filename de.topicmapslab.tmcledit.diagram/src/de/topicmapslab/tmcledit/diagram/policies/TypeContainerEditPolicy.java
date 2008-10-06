package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CreateOccurenceConstraintCommand;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class TypeContainerEditPolicy extends ContainerEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType()==OccurenceTypeConstraint.class) {
			return new CreateOccurenceConstraintCommand(((TypeNodeEditPart)getHost()).getCastedModel().getTopicType());
		}
		return null;
	}
}
