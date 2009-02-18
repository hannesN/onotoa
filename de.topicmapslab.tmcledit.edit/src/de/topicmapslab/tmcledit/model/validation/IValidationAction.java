package de.topicmapslab.tmcledit.model.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.jface.action.IAction;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IValidationAction extends IAction{

	public abstract void setModelObject(EObject modelObject);

}