package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.Request;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.LayoutEditPolicy;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.diagram.editparts.TypeNodeEditPart;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.CreateOccurenceConstraintCommand;

public class TypeNodeLayoutEditPolicy extends LayoutEditPolicy {


	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType()==TypeNode.class) {
			Node node = (Node) getHost().getModel();
			// getting container which is a diagram
			Diagram diagram = (Diagram) node.eContainer();
			if (diagram==null)
				return null;
			TopicType tt = ((TypeNodeEditPart)getHost()).getCastedModel().getTopicType();
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			CreateOccurenceConstraintCommand cmd = new CreateOccurenceConstraintCommand(tt);

			return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
			
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
