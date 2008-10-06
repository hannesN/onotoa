/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.command;

import org.eclipse.draw2d.geometry.Point;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.gef.commands.Command;

import de.topicmapslab.tmcledit.diagram.model.Node;


/**
 * @author Hannes Niederhausen
 *
 */
public class MoveNodeCommand extends Command {

	private final Node node;
	
	private Point newLocation;
	private Point oldLocation;
	
	public MoveNodeCommand(Node node, int newX, int newY) {
		this.node = node;
		newLocation = new Point(newX, newY);
	}

	@Override
	public void execute() {
		oldLocation = new Point(node.getPosX(), node.getPosY());
		redo();
	}
	
	@Override
	public void redo() {
		setLocation(newLocation);
	}
	
	private void setLocation(Point location) {
		((EObjectImpl) node).eSetDeliver(false);
		node.setPosX(location.x);
		((EObjectImpl) node).eSetDeliver(true);
		node.setPosY(location.y);
	}
	
	@Override
	public void undo() {
		setLocation(oldLocation);
	}
	
}