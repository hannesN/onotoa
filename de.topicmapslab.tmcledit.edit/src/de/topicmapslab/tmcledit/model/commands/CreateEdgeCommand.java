package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

public class CreateEdgeCommand extends AbstractCommand {

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
		prepare();
		return (edge.getTarget()!=null) && (edge.getSource()!=null) && (diagram!=null);
	}
	
	@Override
	protected boolean prepare() {
		if ((edge.getTarget()!=null) && (edge.getSource()!=null) && (diagram!=null)) {
			if (edge.getRoleConstraint()!=null) {
				edge.getRoleConstraint().setTopicType(((TypeNode)edge.getTarget()).getTopicType());
			}
				
		}
		return true;
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
				((AssociationNode) edge.getSource()).getAssociationConstraint().getRoleTypeConstraints().add(edge.getRoleConstraint());
				diagram.getEdges().add(edge);
				break;
		
		}
		
		diagram.getEdges().add(edge);
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
			((AssociationNode) edge.getSource()).getAssociationConstraint().getRoleTypeConstraints().remove(edge.getRoleConstraint());
			diagram.getEdges().remove(edge);
			break;
	
	}
		diagram.getEdges().remove(edge);
	}

	@Override
	public void redo() {
		execute();
		
	}
	
	
}
