/**
 * 
 */
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import java.util.List;

import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;

/**
 * @author Hannes Niederhausen
 *
 */
public interface IContextMenuProvider {

	/**
	 * 
	 * @return a list of actions, which are connected to the provider.
	 */
	public List<IAction> getActions();
	
	
	/**
	 * 
	 * @return a list of actions, which are connected to the provider.
	 */
	public List<IContributionItem> getItems();
}
