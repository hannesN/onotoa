/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.awt.Point;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;

/**
 * @author Hannes Niederhausen
 * 
 */
public class NewNodeCommand extends AbstractCommand {

	public enum Type {
		ASSOCIATION, TYPE
	}

	private final Type type;

	private final Node node;

	private final Diagram diagram;

	private boolean createdNewType = false;

	public NewNodeCommand(Diagram diagram, Point location, Node node) {
		if (node instanceof TypeNode) {
			this.type = Type.TYPE;
		} else {
			this.type = Type.ASSOCIATION;
		}

		this.diagram = diagram;

		this.node = node;

		node.setPosX(location.x);
		node.setPosY(location.y);
	}

	@Override
	public boolean canExecute() {
		prepare();
		if ((node instanceof TypeNode) && (!createdNewType)) {
			TopicType tt = ((TypeNode) node).getTopicType();
			for (Node n : diagram.getNodes()) {
				if (n instanceof TypeNode) {
					if (((TypeNode) n).getTopicType().equals(tt))
						return false;
				}
			}
		}
		return true;
	}

	@Override
	public void execute() {
		redo();
	}

	@Override
	protected boolean prepare() {
		if (node instanceof TypeNode) {
			if (((TypeNode) node).getTopicType().eContainer() == null) {
				createdNewType = true;
			}
		}
		return true;
	}

	@Override
	public void redo() {
		if (type == Type.TYPE) {
			if (createdNewType) {
				TopicType tt = ((TypeNode) node).getTopicType();
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
			if (createdNewType) {
				File file = (File) diagram.eContainer();
				file.getTopicMapSchema().getTopicTypes().remove(tt);
			}

		}
	}

	@Override
	public String getLabel() {
		return "Create Node";
	}
}
