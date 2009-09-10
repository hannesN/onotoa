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
package de.topicmapslab.tmcledit.diagram.action;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractSelectionAction extends Action implements
		UpdateAction {

	private IStructuredSelection selections;
	private final CommandStack commandStack;

	public AbstractSelectionAction(CommandStack commandStack) {
		super();
		this.commandStack = commandStack;
		selections = new StructuredSelection();
		update();
	}

	public void setSelections(IStructuredSelection selections) {
		this.selections = selections;
		update();
	}
	
	protected CommandStack getCommandStack() {
		return commandStack;
	}
	
	protected IStructuredSelection getSelections() {
		return selections;
	}

}
