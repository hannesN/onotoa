/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;

import de.topicmapslab.tmcledit.model.validation.actions.ValidationAction;

/**
 * @author Hannes Niederhausen
 *
 */
public class ValidationResult {

	private String message;
	private EObject object;
	private List<ValidationAction> actions = Collections.emptyList(); 
	
	public ValidationResult(String message, EObject object) {
		this.message = message;
		this.object = object;
	}
	
	public void addValidationAction(ValidationAction action) {
		if (actions==Collections.EMPTY_LIST) {
			actions = new ArrayList<ValidationAction>();
		}
		action.setModelObject(object);
		actions.add(action);
	}
	
	public String getMessage() {
		return message;
	}
	
}
