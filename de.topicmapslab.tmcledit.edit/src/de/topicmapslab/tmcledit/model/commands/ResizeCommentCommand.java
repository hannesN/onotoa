/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.awt.Dimension;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import de.topicmapslab.tmcledit.model.Comment;


/**
 * @author Hannes Niederhausen
 *
 */
public class ResizeCommentCommand extends AbstractCommand {

	private final Comment commentNode;
	
	private Dimension newSize;
	private Dimension oldSize;
	
	public ResizeCommentCommand(Comment node, int newW, int newH) {
		this.commentNode = node;
		newSize = new Dimension(newW, newH);
	}

	@Override
	public void execute() {
		redo();
	}
	
	@Override
	protected boolean prepare() {
		oldSize = new Dimension(commentNode.getWidth(), commentNode.getHeight());
		return true;
	}
	
	@Override
	public void redo() {
		setSize(newSize);
	}
	
	private void setSize(Dimension size) {
		((EObjectImpl) commentNode).eSetDeliver(false);
		commentNode.setWidth(size.width);
		((EObjectImpl) commentNode).eSetDeliver(true);
		commentNode.setHeight(size.height);
	}
	
	@Override
	public void undo() {
		setSize(oldSize);
	}
	
}