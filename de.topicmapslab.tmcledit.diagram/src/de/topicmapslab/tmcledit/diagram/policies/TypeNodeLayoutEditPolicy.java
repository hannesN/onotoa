package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.diagram.command.CreateOccurenceConstraintCommand;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.TypeNode;
import de.topicmapslab.tmcledit.model.TopicType;

public class TypeNodeLayoutEditPolicy extends LayoutEditPolicy {


	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType()==TypeNode.class) {
			Diagram diagram = DiagramActivator.getCurrentDiagram();
			if (diagram==null)
				return null;
			TopicType tt = ((TypeNodeEditPart)getHost()).getCastedModel().getTopicType();
			CreateOccurenceConstraintCommand cmd = new CreateOccurenceConstraintCommand(tt);
			return cmd;
			
		}
		
		return null;
	}
	

	

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy();
	}

	@Override
	protected Command getMoveChildrenCommand(Request request) {
		return null;
	}
	
}
