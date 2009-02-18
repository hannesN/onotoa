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
