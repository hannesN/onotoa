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

import de.topicmapslab.onotoa.aranuka.codegen.modelview.GeneratorDataNode;
import de.topicmapslab.tmcledit.model.actions.UpdateAction;
import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.AbstractModelViewNode;

/**
 * Abstract action class which registeres to the {@link ModelView}
 * and updates the action state.
 * 
 * @author Hannes Niederhausen
 * 
 */
public abstract class AbstractSelectionAction extends Action implements UpdateAction, ISelectionChangedListener {

	protected final ModelView modelView;
	protected IStructuredSelection sel;

	/**
	 * 
	 */
	public AbstractSelectionAction(ModelView modelView) {
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
	public abstract void run();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public abstract void update();

	public void dispose() {
		modelView.removeSelectionChangedListener(this);
	}

	/**
     * @param obj
     * @return
     */
    protected boolean hasGeneratorNode(AbstractModelViewNode obj) {
    	for (AbstractModelViewNode node : obj.getChildren()) {
    		if (node instanceof GeneratorDataNode)
    			return true;
    	}
    	
    	return false;
    }
}