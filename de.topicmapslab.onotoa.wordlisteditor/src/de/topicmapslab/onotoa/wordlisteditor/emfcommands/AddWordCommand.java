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
import de.topicmapslab.tmcledit.model.KindOfTopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddWordCommand extends AbstractCommand {

	private WordListContainer container;
	private String word;
	private KindOfTopicType type;
	private Word newWord;
	
	
	/**
	 * Constructor
	 * @param container the container for the new word
	 * @param word the word of the new {@link Word} instance
	 * @param type the type of the new {@link Word} instance
	 */
	public AddWordCommand(WordListContainer container, String word, KindOfTopicType type) {
	    super();
	    this.container = container;
	    this.word = word;
	    this.type = type;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		container.add(newWord);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		container.add(newWord);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		container.remove(newWord);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean prepare() {
		newWord = new Word();
		newWord.setType(type);
		newWord.setWord(this.word);
		
	    return true;
	}
}
