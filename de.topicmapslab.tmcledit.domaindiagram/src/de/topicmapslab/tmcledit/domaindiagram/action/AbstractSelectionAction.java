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
package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.gef.commands.CommandStack;
import org.eclipse.gef.ui.actions.UpdateAction;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

/**
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractSelectionAction extends AbstractCommandStackAction implements
		UpdateAction {

	private IStructuredSelection selections;
	public AbstractSelectionAction(CommandStack commandStack) {
		super(commandStack);
		selections = new StructuredSelection();
		update();
	}

	public void setSelections(IStructuredSelection selections) {
		this.selections = selections;
		update();
	}
	
	protected IStructuredSelection getSelections() {
		return selections;
	}

}
