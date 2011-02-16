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
package de.topicmapslab.onotoa.wordlisteditor.emfcommands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.onotoa.wordlisteditor.model.Word;

/**
 * Command to set a comment to a model.
 * 
 * @author Hannes Niederhausen
 *
 */
public class ModifyWordCommentCommand extends AbstractCommand {

	private Word word;
	
	private String newComment;
	
	private String oldComment;
	
	
	/**
	 * Constructor
	 * 
	 * @param word the {@link Word} to modify
	 * @param newComment the new word value
	 */
	public ModifyWordCommentCommand(Word word, String newComment) {
	    super();
	    this.word = word;
	    this.newComment = newComment;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		this.word.setComment(newComment);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		this.word.setComment(newComment);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		this.word.setComment(oldComment);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean prepare() {
		
		// don't need to save empty strings so we set it to null
		if ( (newComment!=null) && (newComment.length()==0)) {
			newComment = null;
		}

		oldComment = this.word.getComment();
		
		// check if both comment values are null
		if (oldComment==null) {
			if (newComment==null) {
				return false;
			}
			else {
				return true;
			}
		}
		return !oldComment.equals(oldComment);
	}
}
