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
package de.topicmapslab.tmcledit.diagram.command;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;

/**
 * GEF COmmand adapter encapsulating EMF Commands.
 * 
 * @author Hannes Niederhausen
 * 
 */
public class CommandAdapter extends org.eclipse.gef.commands.Command {

	private Command emfCommand;

	private CommandStack cmdStack;

	/**
	 * Constructor
	 * 
	 * @param cmdStack
	 *            the emf command stack
	 * @param emfCommand
	 *            the encapsulated emf command
	 */
	public CommandAdapter(CommandStack cmdStack, Command emfCommand) {
		super();
		this.cmdStack = cmdStack;
		this.emfCommand = emfCommand;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean canExecute() {
		return emfCommand.canExecute();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean canUndo() {
		return emfCommand.canUndo();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void execute() {
		cmdStack.execute(emfCommand);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getLabel() {
		return emfCommand.getLabel();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void redo() {
		cmdStack.redo();
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public void undo() {
		cmdStack.undo();
	}

	/**
	 * @return the encapsulated EMF COmmand
	 */
	public Command getEmfCommand() {
		return emfCommand;
	}

}
