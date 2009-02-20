/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.awt.Rectangle;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import de.topicmapslab.tmcledit.model.Comment;


/**
 * @author Hannes Niederhausen
 *
 */
public class ResizeCommentCommand extends AbstractCommand {

	private final Comment commentNode;
	
	private Rectangle newBounds;
	private Rectangle oldBounds;
	
	public ResizeCommentCommand(Comment node, int newX, int newY, int newW, int newH) {
		this.commentNode = node;
		newBounds = new Rectangle(newX, newY, newW, newH);
	}

	@Override
	public void execute() {
		redo();
	}
	
	@Override
	protected boolean prepare() {
		oldBounds = new Rectangle(commentNode.getPosX(),
								commentNode.getPosY(),
								commentNode.getWidth(), 
								commentNode.getHeight());
		return true;
	}
	
	@Override
	public void redo() {
		setBounds(newBounds);
	}
	
	private void setBounds(Rectangle bounds) {
		((EObjectImpl) commentNode).eSetDeliver(false);
		commentNode.setPosX(bounds.x);
		commentNode.setPosY(bounds.y);
		commentNode.setWidth(bounds.width);
		((EObjectImpl) commentNode).eSetDeliver(true);
		commentNode.setHeight(bounds.height);
	}
	
	@Override
	public void undo() {
		setBounds(oldBounds);
	}
	
}