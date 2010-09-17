/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

/**
 * @author Hannes Niederhausen
 *
 */
public class IntegerValidator implements IAnnotationValidator {

	/**
	 * 
	 */
	public IntegerValidator() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public Class<?> getType() {
		return Integer.class;
	}

	@Override
	public boolean isValid(String obj) {
		if (obj != null) {
			try {
				Integer.parseInt(obj);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		return true;
	}

}
