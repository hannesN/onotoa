package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.ContainerEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.commands.CreateOccurenceConstraintCommand;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class TypeContainerEditPolicy extends ContainerEditPolicy {

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType()==OccurenceTypeConstraint.class) {
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			CreateOccurenceConstraintCommand cmd = new CreateOccurenceConstraintCommand(((TypeNodeEditPart)getHost()).getCastedModel().getTopicType(), (OccurenceTypeConstraint) request.getNewObject());
			return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		}
		return null;
	}
}
