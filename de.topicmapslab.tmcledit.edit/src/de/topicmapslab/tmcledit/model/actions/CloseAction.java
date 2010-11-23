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
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.ui.IPropertyListener;
import org.eclipse.ui.actions.ActionFactory;

import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Closes the model in the {@link ModelView}.
 * 
 * @author Hannes Niederhausen
 *
 */
public class CloseAction extends Action implements IPropertyListener {

	private ModelView modelView;
	
	/**
	 * The constructor 
	 * 
	 * @param modelView the {@link ModelView}
	 */
	public CloseAction(ModelView modelView) {
		this.modelView = modelView;
		setEnabled(modelView.getCurrentTopicMapSchema()!=null);
	}
	
	@Override
	public String getText() {
		return "Close fsdhjfsdkhfjs";
	}
	
	@Override
	public int getAccelerator() {
		return SWT.CTRL | 'w';
	}
	
	@Override
	public String getId() {
		return ActionFactory.CLOSE.getId();
	}
	
	@Override
	public void run() {
		modelView.close();
	}

	
	public void propertyChanged(Object source, int propId) {
		setEnabled(modelView.getCurrentTopicMapSchema()!=null);
    }
	
}
