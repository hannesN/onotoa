package de.topicmapslab.tmcledit.diagram.editor;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.commands.CommandStack;

/**
 * Interface to encapsulate the EMF {@link EditingDomain} and the {@link CommandStack}
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IOnotoaEditDomain {
	
	/**
	 * Sets the Editing Domain
	 * @param editingDomain
	 */
	public void setEditingDomain(EditingDomain editingDomain);
	
	/**
	 * 
	 * @return the {@link EditingDomain}
	 */
	public EditingDomain getEditingDomain();
	
	/**
	 * 
	 * @return the GEF {@link CommandStack}
	 */
	public CommandStack getCommandStack();
}