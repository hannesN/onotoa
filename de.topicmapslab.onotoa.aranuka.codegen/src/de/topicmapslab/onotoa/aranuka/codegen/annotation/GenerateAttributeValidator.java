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
public class GenerateAttributeValidator implements IAnnotationValidator {

	/**
	 * 
	 */
	public GenerateAttributeValidator() {
	}

	@Override
	public Class<?> getType() {
		return Boolean.class;
	}

	@Override
	public boolean isValid(Object obj) {
		if (obj instanceof Boolean)
			return true;
		return false;
	}

}
