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