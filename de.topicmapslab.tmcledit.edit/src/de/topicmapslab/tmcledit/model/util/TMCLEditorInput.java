/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package de.topicmapslab.tmcledit.model.util;

import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.gef.ui.actions.ActionRegistry;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import de.topicmapslab.tmcledit.model.Diagram;

/**
 * @author Hannes Niederhausen
 *
 */
public class TMCLEditorInput implements IEditorInput {

	private final Diagram diagram;
	private final boolean exists;
	private final EditingDomain editingDomain;
	
	private final ActionRegistry actionRegistry;
	
	
	
	
	public TMCLEditorInput(Diagram diagram, EditingDomain editingDomain,
			ActionRegistry actionRegistry, boolean exists) {
		super();
		this.diagram = diagram;
		this.editingDomain = editingDomain;
		this.exists = exists;
		this.actionRegistry = actionRegistry;
	}

	public boolean exists() {
		return exists;
	}

	public ImageDescriptor getImageDescriptor() {
		return ImageProvider.getImageDescriptor(ImageConstants.DIAGRAM);
	}

	public String getName() {
		return diagram.getName();
	}

	public IPersistableElement getPersistable() {
		return null;
	}

	public String getToolTipText() {
		return diagram.getName();
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class adapter) {
		return null;
	}
	
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	public Diagram getDiagram() {
		return diagram;
	}

	public ActionRegistry getActionRegistry() {
	    return actionRegistry;
    }

	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((actionRegistry == null) ? 0 : actionRegistry.hashCode());
	    result = prime * result + ((diagram == null) ? 0 : diagram.hashCode());
	    result = prime * result + ((editingDomain == null) ? 0 : editingDomain.hashCode());
	    result = prime * result + (exists ? 1231 : 1237);
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (obj == null)
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    TMCLEditorInput other = (TMCLEditorInput) obj;
	    if (actionRegistry == null) {
		    if (other.actionRegistry != null)
			    return false;
	    } else if (!actionRegistry.equals(other.actionRegistry))
		    return false;
	    if (diagram == null) {
		    if (other.diagram != null)
			    return false;
	    } else if (!diagram.equals(other.diagram))
		    return false;
	    if (editingDomain == null) {
		    if (other.editingDomain != null)
			    return false;
	    } else if (!editingDomain.equals(other.editingDomain))
		    return false;
	    if (exists != other.exists)
		    return false;
	    return true;
    }

	
}
