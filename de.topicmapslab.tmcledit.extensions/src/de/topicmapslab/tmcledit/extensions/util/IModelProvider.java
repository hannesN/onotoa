package de.topicmapslab.tmcledit.extensions.util;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.ecore.EObject;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IModelProvider {
	public EObject getModel();
	
	public CommandStack getCommandStack();
}
