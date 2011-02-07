/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

/**
 * The {@link RowsValidator} checks if the given value is greater than or equal  1
 * 
 * @author Hannes Niederhausen
 * 
 */
public class RowsValidator extends IntegerValidator {

	@Override
	public boolean isValid(String obj) {
		if (super.isValid(obj)) {
			return Integer.parseInt(obj) >= 1;
		}
		return false;
	}
}
