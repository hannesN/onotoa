/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;

import de.topicmapslab.tmcledit.model.validation.IValidationAction;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class ValidationAction extends Action implements IValidationAction {

	private EObject modelObject;
	
	public ValidationAction() {
	}
	
	public ValidationAction(EObject model) {
		this.modelObject = model;
	}
	
	public void setModelObject(EObject modelObject) {
		this.modelObject = modelObject;
	}

	protected EObject getModelObject() {
		return modelObject;
	}
		
	@Override
	public abstract void run();
}
