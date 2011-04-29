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
package de.topicmapslab.tmcledit.model.util;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;

/**
 * Interface to get a commandstack and a model to modify it.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IModelProvider {
	
	/**
	 * 
	 * @return the model element
	 */
	public EObject getModel();
	
	/**
	 * 
	 * @return a command stack which may be used to execute modification commands
	 */
	public CommandStack getCommandStack();
}
