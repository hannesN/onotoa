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


public class CommandAdapter extends org.eclipse.gef.commands.Command {

	private Command emfCommand;
	
	private CommandStack cmdStack;

	public CommandAdapter(CommandStack cmdStack, Command emfCommand) {
		super();
		this.cmdStack = cmdStack;
		this.emfCommand = emfCommand;
	}

	public boolean canExecute() {
		return emfCommand.canExecute();
	}

	public boolean canUndo() {
		return emfCommand.canUndo();
	}

	public void execute() {
		cmdStack.execute(emfCommand);
	}

	public String getDescription() {
		return emfCommand.getDescription();
	}

	public String getLabel() {
		return emfCommand.getLabel();
	}

	public void redo() {
		cmdStack.redo();
	}

	public void undo() {
		cmdStack.undo();
	}
	
	public Command getEmfCommand() {
		return emfCommand;
	}
	
}
