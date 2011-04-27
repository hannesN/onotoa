/*******************************************************************************
 * Copyright (c) 2008-2011 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *      Hannes Niederhausen - initial API and implementation
 ******************************************************************************/
package de.topicmapslab.onotoa.wordlisteditor.editor.input;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Editor Input for the Word List Editor
 * 
 * @author Hannes Niederhausen
 *
 */
public class WordListEditorInput implements IEditorInput {

	private final ModelView modelView;
	
	/**
	 * Constructor
	 * @param modelView 
	 */
	public WordListEditorInput(ModelView modelView) {
	    super();
	    assert(modelView!=null);
	    
	    this.modelView = modelView;
    }

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("rawtypes")
    @Override
	public Object getAdapter(Class adapter) {
		if (adapter==ModelView.class)
			return modelView;
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean exists() {
		return false;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public ImageDescriptor getImageDescriptor() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getName() {
		return "Ontology Word List";
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public IPersistableElement getPersistable() {
		return null;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String getToolTipText() {
		return "";
	}

	/**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((modelView == null) ? 0 : modelView.hashCode());
	    return result;
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
	    if (this == obj) {
		    return true;
	    }
	    if (obj == null) {
		    return false;
	    }
	    if (getClass() != obj.getClass()) {
		    return false;
	    }
	    WordListEditorInput other = (WordListEditorInput) obj;
	    if (modelView == null) {
		    if (other.modelView != null) {
			    return false;
		    }
	    } else if (!modelView.equals(other.modelView)) {
		    return false;
	    }
	    return true;
    }

	
}
