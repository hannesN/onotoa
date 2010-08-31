/**
 * 
 */
package de.topicmapslab.onotoa.selection.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import de.topicmapslab.tmcledit.model.File;

/**
 * Implementation of  {@link IOnotoaSelectionService} 
 * 
 * @author Hannes Niederhausen
 *
 */
public class OnotoaSelectionService implements IOnotoaSelectionService {

	List<ISelectionChangedListener> listeners;
	private ISelection selection; 
	
	private File file;
	
	@Override
	public void addSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners==null)
			listeners = new ArrayList<ISelectionChangedListener>();
		
		listeners.add(listener);

	}

	@Override
	public void removeSelectionChangedListener(ISelectionChangedListener listener) {
		if (listeners!=null)
			listeners.remove(listener);

	}

	@Override
	public void setSelection(ISelection selection, ISelectionProvider source) {
		this.selection = selection;
		notifyListeners(selection, source);
	}

	private void notifyListeners(ISelection selection, ISelectionProvider source) {
		if (getListeners().isEmpty())
			return;
		
		ISelectionChangedListener listeners[] = getListeners().toArray(new ISelectionChangedListener[this.listeners.size()]);
		
		SelectionChangedEvent e = new SelectionChangedEvent(source, selection);
		for (ISelectionChangedListener l : listeners) {
			l.selectionChanged(e);
		}
	}
	
	private List<ISelectionChangedListener> getListeners() {
	    if (listeners==null)
	    	return Collections.emptyList();
	    
		return listeners;
    }
	
	@Override
	public ISelection getSelection() {
	    if (this.selection==null)
	    	return new StructuredSelection();
	    return selection;
	}
	
	@Override
	public File getOnotoaFile() {
	    return file;
	}

	@Override
    public void setOnotoaFile(File file) {
		 this.file = file;
	    
    }
}
