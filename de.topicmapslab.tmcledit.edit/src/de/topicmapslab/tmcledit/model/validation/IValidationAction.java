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
package de.topicmapslab.tmcledit.model.validation;

import org.eclipse.jface.action.IAction;

/**
 * 
 * Interface for actions used in the validation view. These action represent quick fixes for a specific model element.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IValidationAction extends IAction{

	/**
	 * Sets the model which is invalid and should be modified
	 * 
	 * @param modelObject
	 */
	public abstract void setModelObject(Object modelObject);

}