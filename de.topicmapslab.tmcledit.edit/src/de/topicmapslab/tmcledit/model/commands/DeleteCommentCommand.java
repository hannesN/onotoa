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
