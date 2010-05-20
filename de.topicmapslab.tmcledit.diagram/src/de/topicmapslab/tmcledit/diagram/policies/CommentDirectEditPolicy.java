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
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.requests.DirectEditRequest;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.commands.SetCommentContentCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class CommentDirectEditPolicy extends AbstractDirectEditPolicy {

	@Override
	public Command getCommand(Object model, DirectEditRequest request) {
		Comment comment = (Comment) model;
		return new SetCommentContentCommand(comment, getNewString(request));
	}

}
