package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.gef.commands.Command;
import org.eclipse.gef.editpolicies.GraphicalNodeEditPolicy;
import org.eclipse.gef.requests.CreateConnectionRequest;
import org.eclipse.gef.requests.ReconnectRequest;

import de.topicmapslab.tmcledit.diagram.command.CommandAdapter;
import de.topicmapslab.tmcledit.diagram.editor.TMCLEditDomain;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.CreateEdgeCommand;

public class NodeGraphicalNodeEditPolicy extends GraphicalNodeEditPolicy {

	@Override
	protected Command getConnectionCompleteCommand(
			CreateConnectionRequest request) {
		
		CreateEdgeCommand cmd = (CreateEdgeCommand) ((CommandAdapter)request
				.getStartCommand()).getEmfCommand();
		if (request.getTargetEditPart()==request.getSourceEditPart())
			return null;
		cmd.setTarget((Node) request.getTargetEditPart().getModel());
		return request.getStartCommand();
	}

	@Override
	protected Command getConnectionCreateCommand(CreateConnectionRequest request) {
		CreateEdgeCommand cmd = new CreateEdgeCommand((Edge) request.getNewObject());
		TMCLEditDomain ed = (TMCLEditDomain) getHost().getViewer().getEditDomain();
		Diagram d = (Diagram) getHost().getParent().getModel();
		cmd.setDiagram(d);
		cmd.setSource((Node) getHost().getModel());

		CommandAdapter cmdAdapter = new CommandAdapter(ed.getEditingDomain().getCommandStack(), cmd);
		request.setStartCommand(cmdAdapter);

		return cmdAdapter;
	}

	@Override
	protected Command getReconnectSourceCommand(ReconnectRequest request) {
		return null;
	}

	@Override
	protected Command getReconnectTargetCommand(ReconnectRequest request) {
		return null;
	}

}
