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
import de.topicmapslab.onotoa.wordlisteditor.model.WordListContainer;

/**
 * Command to remove a word from the word container
 * 
 * @author Hannes Niederhausen
 *
 */
public class RemoveWordCommand extends AbstractCommand {

	private WordListContainer container;
	private Word word;
	private int idx;
	
	
	/**
	 * Constructor
	 * @param container the container for the word to remove
	 * @param word the word of the new {@link Word} instance
	 * @param type the type of the new {@link Word} instance
	 */
	public RemoveWordCommand(WordListContainer container, Word word) {
	    super();
	    this.container = container;
	    this.word = word;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		idx = container.indexOf(word);
		container.remove(word);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		container.remove(word);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		container.add(idx, word);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean prepare() {
	    return true;
	}

}
