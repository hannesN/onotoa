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
import org.eclipse.ui.ISaveablePart;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Editor Input for the two diagram editors
 * 
 * @author Hannes Niederhausen
 *
 */
public class TMCLEditorInput implements IEditorInput {

	private final Diagram diagram;
	private final EditingDomain editingDomain;
	
	private final ActionRegistry actionRegistry;
	private ModelView modelView;
	
	/**
	 * Constructor
	 * @param diagram the diagram to edit
	 * @param editingDomain the editingDomain containing the command stack and other stuff
	 * @param actionRegistry the action registry for shared actions, like undo, redo and save
	 * @param modelView the model view which opens the editor
	 */
	public TMCLEditorInput(Diagram diagram, EditingDomain editingDomain,
			ActionRegistry actionRegistry, ModelView modelView) {
		super();
		this.diagram = diagram;
		this.editingDomain = editingDomain;
		this.actionRegistry = actionRegistry;
		this.modelView = modelView;
	}

	public boolean exists() {
		return true;
	}

	public ImageDescriptor getImageDescriptor() {
		if (diagram instanceof DomainDiagram)
			return ImageProvider.getImageDescriptor(ImageConstants.DOMAINDIAGRAM);
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

	public Object getAdapter(Class adapter) {
		if (adapter==ISaveablePart.class)
			return modelView;
		if (adapter == EditingDomain.class)
			return getEditingDomain();
		if (adapter == ActionRegistry.class)
			return getActionRegistry();
		if (adapter == ModelView.class)
			return getModelView();
		return null;
	}
	
	/**
	 * Returns the editing domain
	 * @return
	 */
	public EditingDomain getEditingDomain() {
		return editingDomain;
	}

	/**
	 * 
	 * @return the diagram
	 */
	public Diagram getDiagram() {
		return diagram;
	}

	/**
	 * 
	 * @return the action registry
	 */
	public ActionRegistry getActionRegistry() {
	    return actionRegistry;
    }

	/**
	 * 
	 * @return the model view
	 */
	public ModelView getModelView() {
	    return modelView;
    }
	
	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = 1;
	    result = prime * result + ((actionRegistry == null) ? 0 : actionRegistry.hashCode());
	    result = prime * result + ((diagram == null) ? 0 : diagram.hashCode());
	    result = prime * result + ((editingDomain == null) ? 0 : editingDomain.hashCode());
	    result = prime * result + 1231;
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
	    return true;
    }

	
}
