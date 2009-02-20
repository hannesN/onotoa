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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;

import de.topicmapslab.tmcledit.model.validation.IValidationAction;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class ValidationAction extends Action implements IValidationAction {

	private EObject modelObject;
	private CommandStack commandStack;
	
	public ValidationAction(CommandStack cmdStack) {
		commandStack = cmdStack;
	}
	
	public ValidationAction(EObject model) {
		this.modelObject = model;
	}
	
	public void setModelObject(EObject modelObject) {
		this.modelObject = modelObject;
	}

	protected EObject getModelObject() {
		return modelObject;
	}
	
	protected CommandStack getCommandStack() {
		return commandStack;
	}
		
	@Override
	public abstract void run();
}
