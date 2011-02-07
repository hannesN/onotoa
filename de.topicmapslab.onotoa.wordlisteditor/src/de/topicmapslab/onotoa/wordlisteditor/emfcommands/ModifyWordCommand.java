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
 * @author Hannes Niederhausen
 *
 */
public class ModifyWordCommand extends AbstractCommand {

	private Word word;
	
	private String newWord;
	
	private String oldWord;
	
	
	/**
	 * Constructor
	 * 
	 * @param word the {@link Word} to modify
	 * @param newWord the new word value
	 */
	public ModifyWordCommand(Word word, String newWord) {
	    super();
	    this.word = word;
	    this.newWord = newWord;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		this.word.setWord(newWord);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		this.word.setWord(newWord);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		this.word.setWord(oldWord);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean prepare() {
		oldWord = this.word.getWord();
		
		return !newWord.equals(oldWord);
	}
}
