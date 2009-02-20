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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IValidationAction extends IAction{

	public abstract void setModelObject(EObject modelObject);

}