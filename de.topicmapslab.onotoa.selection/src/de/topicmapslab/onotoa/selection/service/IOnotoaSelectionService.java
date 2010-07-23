/**
 * 
 */
package de.topicmapslab.onotoa.selection.service;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;

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
}
