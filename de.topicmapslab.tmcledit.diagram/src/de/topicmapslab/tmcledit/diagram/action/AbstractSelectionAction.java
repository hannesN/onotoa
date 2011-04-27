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
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;

import de.topicmapslab.tmcledit.model.actions.UpdateAction;

/**
 * Abstract action which handles selection events and updates the actions state
 * 
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractSelectionAction extends AbstractCommandStackAction implements
		UpdateAction {

	private IStructuredSelection selections;
	
	/**
	 * Constructor
	 * 
	 * @param commandStack the commandstack for changes
	 */
	public AbstractSelectionAction(CommandStack commandStack) {
		super(commandStack);
		selections = new StructuredSelection();
		update();
	}

	/**
	 * Sets the selection and updates the actions state
	 * 
	 * @param selections
	 */
	public void setSelections(IStructuredSelection selections) {
		this.selections = selections;
		update();
	}
	
	/**
	 * Returns the selection
	 * @return
	 */
	protected IStructuredSelection getSelections() {
		return selections;
	}

}
