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
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

/**
 * Validator for the weight annotation. Only values between 0 and 10 are allowed
 * 
 * @author Hannes Niederhausen
 *
 */
public class WeightValidator implements IAnnotationValidator {

	/**
	 * 
	 */
	public WeightValidator() {
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getType() {
		return Integer.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(String obj) {
		try {
			int i = Integer.parseInt(obj);
			return ( (i>=0) && (i<=10) );			
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
