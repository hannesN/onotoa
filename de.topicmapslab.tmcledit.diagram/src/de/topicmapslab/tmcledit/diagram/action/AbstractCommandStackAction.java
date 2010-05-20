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