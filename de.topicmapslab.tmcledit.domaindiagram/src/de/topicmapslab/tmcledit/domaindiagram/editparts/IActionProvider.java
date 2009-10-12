/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.List;

import org.eclipse.jface.action.IAction;

/**
 * @author Hannes Niederhausen
 *
 */
public interface IActionProvider {

	/**
	 * 
	 * @return a list of actions, which are connected to the provider.
	 */
	public List<IAction> getActions();
	
}
