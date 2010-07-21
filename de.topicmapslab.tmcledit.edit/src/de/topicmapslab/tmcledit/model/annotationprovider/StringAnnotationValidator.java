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
 * A validator which uses String as type and every String is valid, but <code>null</code>.
 * 
 * @author Hannes Niederhausen
 *
 */
public class StringAnnotationValidator implements IAnnotationValidator {

	
	public Class<?> getType() {
		return String.class;
	}

	/**
	 * Only checks if obj is null and if not is instance of String.
	 * @param obj the obj to check
	 * @return <code>true</code> if obj is not <code>null</code> 
	 */
	public boolean isValid(Object obj) {
		if (obj==null)
			return false;
		
		return obj instanceof String;
	}

}
