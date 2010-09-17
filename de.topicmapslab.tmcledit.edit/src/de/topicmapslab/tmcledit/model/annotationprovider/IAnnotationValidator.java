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
package de.topicmapslab.tmcledit.model.annotationprovider;


/**
 * @author Hannes Niederhausen
 *
 */
public interface IAnnotationValidator {

	/**
	 * Returns the type which is expected. E.g. Boolean.class, String.class
	 * 
	 * @return the class which represents the type
	 */
	public Class<?> getType();
	
	
	/**
	 * Validates the given Object, which is a new value of an annotation.
	 * @param object The string representation of the value 
	 * @return <code>true</code> if the object has a valid value, <code>false</code> else 
	 */
	public boolean isValid(String obj);

}
