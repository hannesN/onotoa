/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

/**
 * Validator for Strings. Basically it just checks if the obj is a String and the
 * length is greater than 0.
 * 
 * @author Hanes Niederhausen
 * 
 */
public class StringValidator implements IAnnotationValidator {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Class<?> getType() {
		return String.class;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean isValid(String obj) {
		return ((String) obj).length() > 0;
	}

}
