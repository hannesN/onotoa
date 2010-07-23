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

	public void addSelectionChangedListener(ISelectionChangedListener listener);
	
	public void removeSelectionChangedListener(ISelectionChangedListener listener);
	
	public void setSelection(ISelection selection, ISelectionProvider source);
	
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
