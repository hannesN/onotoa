package de.topicmapslab.tmcledit.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;


/**
 * @author Hannes Niederhausen
 *
 */
public class ValidationResult {

	private String message;
	private EObject object;
	private List<IValidationAction> actions = Collections.emptyList(); 
	
	public ValidationResult(String message, EObject object) {
		this.message = message;
		this.object = object;
	}
	
	public void addValidationAction(IValidationAction action) {
		if (actions==Collections.EMPTY_LIST) {
			actions = new ArrayList<IValidationAction>();
		}
		action.setModelObject(object);
		actions.add(action);
	}
	
	public String getMessage() {
		return message;
	}
	
	public List<IValidationAction> getActions() {
		return actions;
	}
}
