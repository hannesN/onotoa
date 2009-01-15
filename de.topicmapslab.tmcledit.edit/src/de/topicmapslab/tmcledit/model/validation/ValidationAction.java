/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.Action;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class ValidationAction extends Action {

	private EObject modelObject;
	
	public void setModelObject(EObject modelObject) {
		this.modelObject = modelObject;
	}

	protected EObject getModelObject() {
		return modelObject;
	}
	
}
