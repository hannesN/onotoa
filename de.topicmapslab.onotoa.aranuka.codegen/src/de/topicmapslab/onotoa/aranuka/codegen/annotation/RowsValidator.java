/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

/**
 * @author niederhausen
 * 
 */
public class RowsValidator extends IntegerValidator {

	@Override
	public boolean isValid(Object obj) {
		if (super.isValid(obj)) {
			return (((Integer)obj) >= 1);
		}
		return false;
	}
}
