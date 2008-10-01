/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import de.topicmapslab.tmcledit.diagram.model.Diagram;
import de.topicmapslab.tmcledit.diagram.model.ModelFactory;
import de.topicmapslab.tmcledit.diagram.model.Node;
import de.topicmapslab.tmcledit.diagram.model.TypeNode;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class NewNodeCommand extends Command {

	public enum Type {
		ASSOCIATION,
		TYPE
	}
	
	private final Type type;
	
	private final Node node;
	
	private final Diagram diagram;
	
	public NewNodeCommand(Diagram diagram, Point location, Type type) {
		this.type = type;
		this.diagram = diagram;
		
		if (type==Type.ASSOCIATION) {
			node = ModelFactory.eINSTANCE.createAssociationNode();		
		} else {
			node = ModelFactory.eINSTANCE.createTypeNode();
		}
		node.setPosX(location.x);
		node.setPosY(location.y);
	}
	
	@Override
	public void execute() {
		if (type==Type.TYPE) {
			TopicType tt = de.topicmapslab.tmcledit.model.ModelFactory.eINSTANCE.createTopicType();
			tt.setId("default type");
			((TypeNode)node).setTopicType(tt);
		}
		
		redo();
	}
	
	@Override
	public void redo() {
		if (type==Type.TYPE) {
			TopicType tt = ((TypeNode)node).getTopicType(); 
			diagram.getTopicMapSchema().getTopicTypes().add(tt);
			diagram.getNodes().add(node);
		}
	}
	
	@Override
	public void undo() {
		if (type==Type.TYPE) {
			TopicType tt = ((TypeNode)node).getTopicType(); 
			diagram.getNodes().remove(node);
			diagram.getTopicMapSchema().getTopicTypes().remove(tt);
		}
	}
	
}
