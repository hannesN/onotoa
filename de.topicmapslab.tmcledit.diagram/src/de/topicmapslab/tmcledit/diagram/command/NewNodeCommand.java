/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.gef.commands.Command;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * @author Hannes Niederhausen
 * 
 */
public class NewNodeCommand extends Command {

	public enum Type {
		ASSOCIATION, TYPE
	}

	private final Type type;

	private final Node node;

	private final Diagram diagram;

	private final boolean createNewType;

	public NewNodeCommand(Diagram diagram, Point location, Node node) {
		if (node instanceof TypeNode) {
			createNewType = (((TypeNode) node).getTopicType() == null);
			this.type = Type.TYPE;
		} else {
			this.type = Type.ASSOCIATION;
			createNewType = false;
		}

		this.diagram = diagram;

		this.node = node;

		node.setPosX(location.x);
		node.setPosY(location.y);
	}

	@Override
	public boolean canExecute() {
		if ((node instanceof TypeNode)
				&& (((TypeNode) node).getTopicType() != null)) {
			TopicType tt = ((TypeNode) node).getTopicType();
			for (Node n : diagram.getNodes()) {
				if (n instanceof TypeNode) {
					if ( ((TypeNode)n).getTopicType().equals(tt) )
						return false;
				}
			}
		}
		return true;
	}

	@Override
	public void execute() {
		if ((type == Type.TYPE) && (createNewType)) {
			TopicType tt = de.topicmapslab.tmcledit.model.ModelFactory.eINSTANCE
					.createTopicType();
			tt.setId("default type");
			((TypeNode) node).setTopicType(tt);
		}

		redo();
	}

	@Override
	public void redo() {
		if (type == Type.TYPE) {

			TopicType tt = ((TypeNode) node).getTopicType();
			if (createNewType) {
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getTopicTypes().add(tt);
			}
			diagram.getNodes().add(node);
		}
	}

	@Override
	public void undo() {
		if (type == Type.TYPE) {
			TopicType tt = ((TypeNode) node).getTopicType();
			diagram.getNodes().remove(node);
			if (createNewType) {
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getTopicTypes().remove(tt);
			}

		}
	}

}
