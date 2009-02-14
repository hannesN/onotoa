/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;

/**
 * @author Hannes Niederhausen
 *
 */
public class DeleteCommentCommand extends AbstractCommand {

	private final Diagram diagram;
	private final Comment comment;
	
	public DeleteCommentCommand(Comment comment) {
		super();
		this.diagram = (Diagram) comment.eContainer();
		this.comment = comment;
	}


	@Override
	public void execute() {
		diagram.getComments().remove(comment);
	}

	@Override
	public void redo() {
		diagram.getComments().remove(comment);
	}
	
	@Override
	public void undo() {
		diagram.getComments().add(comment);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
