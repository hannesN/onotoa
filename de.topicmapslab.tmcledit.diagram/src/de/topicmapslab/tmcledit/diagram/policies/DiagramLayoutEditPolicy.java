package de.topicmapslab.tmcledit.diagram.policies;

import java.awt.Point;

import org.eclipse.draw2d.geometry.Rectangle;
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.NonResizableEditPolicy;
import org.eclipse.gef.editpolicies.XYLayoutEditPolicy;
import org.eclipse.gef.requests.CreateRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.MoveNodeCommand;
import de.topicmapslab.tmcledit.model.commands.NewNodeCommand;

public class DiagramLayoutEditPolicy extends XYLayoutEditPolicy {

	@Override
	protected Command createChangeConstraintCommand(EditPart child,
			Object constraint) {
		TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
		if (constraint instanceof Rectangle) {
			Rectangle rec = (Rectangle) constraint;
			return new CommandAdapter(ed.getEditingDomain().getCommandStack(),  
				new MoveNodeCommand((Node) child.getModel(), rec.x, rec.y));
		}
		
		return null;
	}

	@Override
	protected Command getCreateCommand(CreateRequest request) {
		if ( (request.getNewObjectType()==TypeNode.class) ||
			 (request.getNewObjectType()==AssociationNode.class) ){
			Diagram diagram = (Diagram) getHost().getModel();
			TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
			Point p = new Point();
			p.x = request.getLocation().x;
			p.y = request.getLocation().y;
			NewNodeCommand cmd = new NewNodeCommand(diagram, p, (Node) request.getNewObject());
			
			return new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
			
		}
		
		return null;
	}
	
	

	@Override
	protected EditPolicy createChildEditPolicy(EditPart child) {
		return new NonResizableEditPolicy();
	}
	
}
