/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.awt.Point;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import de.topicmapslab.tmcledit.model.Node;


/**
 * @author Hannes Niederhausen
 *
 */
public class MoveNodeCommand extends AbstractCommand {

	private final Node node;
	
	private Point newLocation;
	private Point oldLocation;
	
	public MoveNodeCommand(Node node, int newX, int newY) {
		this.node = node;
		newLocation = new Point(newX, newY);
	}

	@Override
	public void execute() {
	
		redo();
	}
	
	@Override
	protected boolean prepare() {
		oldLocation = new Point(node.getPosX(), node.getPosY());
		return true;
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