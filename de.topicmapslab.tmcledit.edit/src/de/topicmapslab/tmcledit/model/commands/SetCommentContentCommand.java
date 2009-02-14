/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.Comment;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetCommentContentCommand extends AbstractCommand {

	private final String newText;
	private final String oldText;
	private final Comment comment;
	
	public SetCommentContentCommand(Comment comment, String newText) {
		super();
		oldText = comment.getContent();
		this.comment = comment;
		this.newText = newText;
	}


	@Override
	public void execute() {
		comment.setContent(newText);
	}

	@Override
	public void redo() {
		comment.setContent(newText);
	}
	
	@Override
	public void undo() {
		comment.setContent(oldText);
	}

	@Override
	protected boolean prepare() {
		return true;
	}
}
