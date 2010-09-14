/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

import de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator;

/**
 * @author niederhausen
 *
 */
public class IntegerValidator implements IAnnotationValidator {

	/**
	 * 
	 */
	public IntegerValidator() {
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator#getType()
	 */
	@Override
	public Class<?> getType() {
		return Integer.class;
	}

	/* (non-Javadoc)
	 * @see de.topicmapslab.tmcledit.model.annotationprovider.IAnnotationValidator#isValid(java.lang.Object)
	 */
	@Override
	public boolean isValid(Object obj) {
		return (obj instanceof Integer);
	}

}
