/**
 * 
 */
package de.topicmapslab.onotoa.selection.service;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;

import de.topicmapslab.tmcledit.model.File;

/**
 * Service interface for the Onotoa internal model selections.
 * 
 * @author Hannes Niederhausen
 *
 */
public interface IOnotoaSelectionService {

	/**
	 * Adds a {@link ISelectionChangedListener} to the list.
	 * 
	 * @param listener the new listener
	 */
	public void addSelectionChangedListener(ISelectionChangedListener listener);
	
	/**
	 * Removes a {@link ISelectionChangedListener} to the list.
	 * 
	 * @param listener the listener to remove
	 */
	public void removeSelectionChangedListener(ISelectionChangedListener listener);
	
	/**
	 * Sets the selection
	 * 
	 * @param selection the new selection
	 * @param source the source of the selection
	 */
	public void setSelection(ISelection selection, ISelectionProvider source);
	
	/**
	 * 
	 * @return the selection
	 */
	public ISelection getSelection();
	
	/**
	 * 
	 * @return the opened model or <code>null</code>
	 */
	public File getOnotoaFile();
	
	/**
	 * Sets the current file model. 
	 * 
	 * INTERNAL: This should never be called ans is only for the model view to set.
	 * 
	 * @param file the file model
	 */
	public void setOnotoaFile(File file);
}
