/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

/**
 * @author Hanes Niederhausen
 * 
 */
public class NameValidator implements IAnnotationValidator {

	/**
	 * 
	 */
	public NameValidator() {
	}

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
	public boolean isValid(Object obj) {
		if (obj instanceof String)
			return ((String) obj).length() > 0;
		else
			return false;
	}

}
