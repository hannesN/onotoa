/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.swt.graphics.Point;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;

/**
 * @author Hannes Niederhausen
 *
 */
public class CreateCommentCommand extends AbstractCommand {

	private final Diagram diagram;
	private final Comment comment;
	private final Point location;
	
	public CreateCommentCommand(Diagram diagram, Comment comment, Point location) {
		super();
		this.diagram = diagram;
		this.comment = comment;
		this.location = location;
	}


	@Override
	public void execute() {
		diagram.getComments().add(comment);
	}

	@Override
	public void redo() {
		diagram.getComments().add(comment);
	}
	
	@Override
	public void undo() {
		diagram.getComments().remove(comment);
	}

	@Override
	protected boolean prepare() {
		comment.setPosX(location.x);
		comment.setPosY(location.y);
		return true;
	}
}
