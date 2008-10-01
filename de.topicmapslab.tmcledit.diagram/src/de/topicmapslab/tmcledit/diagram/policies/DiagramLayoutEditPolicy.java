package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.DiagramActivator;
import de.topicmapslab.tmcledit.diagram.command.MoveNodeCommand;
import de.topicmapslab.tmcledit.diagram.command.NewNodeCommand;
import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.Node;
import de.topicmapslab.tmcledit.diagram.model.TypeNode;

public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		
		if (constraint instanceof Rectangle) {
			Rectangle rec = (Rectangle) constraint;
			return new MoveNodeCommand((Node) child.getModel(), rec.x, rec.y);
		}
		
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if (request.getNewObjectType()==TypeNode.class) {
			Diagram diagram = DiagramActivator.getCurrentDiagram();
			if (diagram==null)
				return null;
			
			NewNodeCommand cmd = new NewNodeCommand(diagram, request.getLocation(), NewNodeCommand.Type.TYPE);
			return cmd;
			
		}
		
		
		return null;
	}

	
}
