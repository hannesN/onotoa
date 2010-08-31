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


	public void execute() {
		diagram.getComments().add(comment);
	}

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
