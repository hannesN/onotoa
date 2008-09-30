/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.impl.AssociationNodeImpl;

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
	
	private final TopicMapSchema schema;
	
	public NewNodeCommand(TopicMapSchema schema, Point location, Type type) {
		this.type = type;
		this.schema = schema;
		
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
			TopicType tt = ModelFactory.eINSTANCE.createTopicType();
			tt.setId("default type");
			((TypeNode)node).setType(tt);
		}
		
		redo();
	}
	
	@Override
	public void redo() {
		if (type==Type.TYPE) {
			TopicType tt = ((TypeNode)node).getType(); 
			schema.getTopicTypes().add(tt);
			schema.getDiagram().getNodes().add(node);
		}
	}
	
	@Override
	public void undo() {
		if (type==Type.TYPE) {
			TopicType tt = ((TypeNode)node).getType(); 
			schema.getDiagram().getNodes().remove(node);
			schema.getTopicTypes().remove(tt);
		}
	}
	
}
