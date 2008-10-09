package de.topicmapslab.tmcledit.diagram.command;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.gef.commands.Command;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.Node;



public class RemoveNodeCommand extends Command {

	private final Node node;
	private final Diagram diagram;

	private List<Edge> edgeList = Collections.emptyList();

	public RemoveNodeCommand(Diagram diagram, Node node) {
		super();
		this.diagram = diagram;
		this.node = node;
	}
	
	@Override
	public void execute() {
		for (Edge e : diagram.getEdges()) {
			if ( (e.getSource().equals(node)) || (e.getTarget().equals(node)) ) {
				if (edgeList==Collections.EMPTY_LIST)
					edgeList = new ArrayList<Edge>();
				edgeList.add(e);
			}
		}
		redo();
	}
	
	@Override
	public void redo() {
		diagram.getEdges().removeAll(edgeList);
		diagram.getNodes().remove(node);
	}
	
	@Override
	public void undo() {
		diagram.getNodes().add(node);
		diagram.getEdges().addAll(edgeList);
	}
	
}
