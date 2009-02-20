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
