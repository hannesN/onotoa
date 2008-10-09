package de.topicmapslab.tmcledit.diagram.command;

import org.eclipse.gef.commands.Command;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

public class CreateEdgeCommand extends Command {

	private Diagram diagram;
	private Edge edge;
	
	public CreateEdgeCommand(Edge newEdge) {
		edge = newEdge;
		
	}
	
	public void setSource(Node source) {
		edge.setSource(source);
	}
	
	public void setTarget(Node target) {
		edge.setTarget(target);
	}
	
	public void setDiagram(Diagram diagram) {
		this.diagram = diagram;
	}
	
	@Override
	public boolean canExecute() {
		return (edge.getTarget()!=null) && (edge.getSource()!=null) && (diagram!=null);
	}
	
	@Override
	public void execute() {
		switch (edge.getType()) {
			case AKO_TYPE:
				TopicType source = ((TypeNode)edge.getSource()).getTopicType();
				TopicType target = ((TypeNode)edge.getTarget()).getTopicType();
				source.getAko().add(target);
				break;
			case IS_ATYPE:
				source = ((TypeNode)edge.getSource()).getTopicType();
				target = ((TypeNode)edge.getTarget()).getTopicType();
				source.getIsa().add(target);
				break;
			case ROLE_CONSTRAINT_TYPE:
				break;
		
		}
		
		//diagram.getEdges().add(edge);
	}
	
	@Override
	public void undo() {
		switch (edge.getType()) {
		case AKO_TYPE:
			TopicType source = ((TypeNode)edge.getSource()).getTopicType();
			TopicType target = ((TypeNode)edge.getTarget()).getTopicType();
			source.getAko().remove(target);
			break;
		case IS_ATYPE:
			source = ((TypeNode)edge.getSource()).getTopicType();
			target = ((TypeNode)edge.getTarget()).getTopicType();
			source.getIsa().remove(target);
			break;
		case ROLE_CONSTRAINT_TYPE:
			break;
	
	}
		//diagram.getEdges().remove(edge);
	}
	
	
}
