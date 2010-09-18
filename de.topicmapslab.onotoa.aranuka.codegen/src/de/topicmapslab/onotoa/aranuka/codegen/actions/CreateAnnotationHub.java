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
package de.topicmapslab.onotoa.aranuka.codegen.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.actions.UpdateAction;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * @author Hannes Niederhausen
 *
 */
public class CreateAnnotationHub extends Action implements UpdateAction, ISelectionChangedListener {

	private final ModelView modelView;
	
	private IStructuredSelection sel;

	public CreateAnnotationHub(ModelView modelView) {
	    super();
	    this.modelView = modelView;
	    this.modelView.addSelectionChangedListener(this);
	    setText("Create Code Generator Meta Data");
	    sel = StructuredSelection.EMPTY;
	    
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public void selectionChanged(SelectionChangedEvent event) {
    	if (event.getSelection() instanceof IStructuredSelection) {
    		sel = (IStructuredSelection) event.getSelection();
    	} else { 
    		sel = StructuredSelection.EMPTY;
    	}
    	
    	update();
    }

	/**
     * {@inheritDoc}
     */
    @Override
    public void update() {
		if (sel.size() == 1) {
			Object obj = sel.getFirstElement();
			if (obj instanceof AbstractModelViewNode) {
				Object model = ((AbstractModelViewNode) obj).getModel();
				if ( (model instanceof TMCLConstruct) 
					&& (!(model instanceof AssociationTypeConstraint))) {
					setEnabled(true);	
					return;
				}
			}
		}
    	setEnabled(false);
    }
	
    public void dispose() {
    	modelView.removeSelectionChangedListener(this);
    }
}
