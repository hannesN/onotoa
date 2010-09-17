/**
 * 
 */
package de.topicmapslab.onotoa.aranuka.codegen.annotation;

/**
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
