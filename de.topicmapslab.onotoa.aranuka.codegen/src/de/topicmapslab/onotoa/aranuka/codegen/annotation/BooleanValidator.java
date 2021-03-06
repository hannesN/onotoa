/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

/**
 * Annotation provider for the Code Generation Plug-in.
 * 
 * 
 * @author Hannes Niederhausen
 *
 */
public class BooleanValidator implements IAnnotationValidator {

	/**
	 * 
	 */
	public BooleanValidator() {
	}

	@Override
	public Class<?> getType() {
		return Boolean.class;
	}

	@Override
	public boolean isValid(String obj) {
		if (obj != null) {
			try {
				Boolean.parseBoolean(obj);
			} catch (NumberFormatException e) {
				return false;
			}
		}
		
		return true;
	}

}
