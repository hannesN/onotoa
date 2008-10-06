package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import de.topicmapslab.tmcledit.diagram.command.CreateEdgeCommand;
import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.Edge;
import de.topicmapslab.tmcledit.diagram.model.Node;

public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		CreateEdgeCommand cmd = (CreateEdgeCommand) request
				.getStartCommand();
		if (request.getTargetEditPart()==request.getSourceEditPart())
			return null;
		cmd.setTarget((Node) request.getTargetEditPart().getModel());
		return cmd;
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateEdgeCommand cmd = new CreateEdgeCommand((Edge) request.getNewObject());

		Diagram d = (Diagram) getHost().getParent().getModel();
		cmd.setDiagram(d);
		cmd.setSource((Node) getHost().getModel());
		request.setStartCommand(cmd);

		return cmd;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
