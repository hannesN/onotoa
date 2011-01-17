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
import de.topicmapslab.tmcledit.model.KindOfTopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class ModifyWordTypeCommand extends AbstractCommand {

	private Word word;
	
	private KindOfTopicType newType;
	
	private KindOfTopicType oldType;
	
	
	/**
	 * Constructor
	 * 
	 * @param word the {@link Word} to modify
	 * @param newType the new type value
	 */
	public ModifyWordTypeCommand(Word word, KindOfTopicType newType) {
	    super();
	    this.word = word;
	    this.newType = newType;
    }

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		this.word.setType(newType);

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		this.word.setType(newType);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		this.word.setType(oldType);
	}
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected boolean prepare() {
		oldType = this.word.getType();
		return oldType!=newType;
	}

}
