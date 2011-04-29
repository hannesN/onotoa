/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
package de.topicmapslab.tmcledit.model.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 
 * 
 * @author Hannes Niederhausen
 *
 */
public class ValidationResult {

	/**
	 * 	Enumeration to classify the priority of an error
	 */
	public enum Priority {
		/** Fatal error */
		FATAL,
		/** Error */
		ERROR,
		/** Warning */
		WARNING
	}
	
	private Priority priority;
	private String message;
	private Object object;
	private List<IValidationAction> actions = Collections.emptyList(); 
	
	
	/**
	 * Constructor 
	 * @param message the error message
	 * @param object the object which failed the validation
	 */
	public ValidationResult(String message, Object object) {
		this(message,object, Priority.ERROR);
	}
	
	/**
	 * Constructor 
	 * @param message the error message
	 * @param object the object which failed the validation
	 * @param priority the priority of the error
	 */
	public ValidationResult(String message, Object object, Priority priority) {
		this.message = message;
		this.object = object;
		this.priority = priority;
	}
	
	/**
	 * Adds an action to the validation error which can be used to fix the problem.
	 * @param action
	 */
	public void addValidationAction(IValidationAction action) {
		if (actions==Collections.EMPTY_LIST) {
			actions = new ArrayList<IValidationAction>();
		}
		action.setModelObject(object);
		actions.add(action);
	}
	
	/**
	 * 
	 * @return the error message of the result
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * 
	 * @return the object which failed the validation
	 */
	public Object getObject() {
		return object;
	}
	
	/**
	 * 
	 * @return a list of actions to fix the problem
	 */
	public List<IValidationAction> getActions() {
		return actions;
	}
	
	/**
	 * The priority
	 * @return
	 */
	public Priority getPriority() {
	    return priority;
    }
}
