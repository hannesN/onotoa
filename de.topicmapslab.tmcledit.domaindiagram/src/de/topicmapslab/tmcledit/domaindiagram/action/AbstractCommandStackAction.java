package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.action.Action;

public abstract class AbstractCommandStackAction extends Action {

	protected final CommandStack commandStack;

	public AbstractCommandStackAction(CommandStack commandStack) {
		super();
		this.commandStack = commandStack;
	}

	protected CommandStack getCommandStack() {
		return commandStack;
	}

}