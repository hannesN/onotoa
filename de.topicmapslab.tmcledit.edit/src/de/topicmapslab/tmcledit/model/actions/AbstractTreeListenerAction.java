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
package de.topicmapslab.tmcledit.model.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;

import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeObject;

/**
 * @author Hannes Niederhausen
 *
 */
public abstract class AbstractTreeListenerAction extends Action implements ISelectionChangedListener {

	private final ModelView view;

	private TreeObject treeObject;
	
	/**
	 * 
	 * @param view the {@link ModelView} 
	 */
	public AbstractTreeListenerAction(ModelView view) {
	    super();
	    this.view = view;
	    this.view.getViewer().addSelectionChangedListener(this);
    }
	
	protected void setTreeObject(TreeObject treeObject) {
	    this.treeObject = treeObject;
    }
	
	protected TreeObject getTreeObject() {
	    return treeObject;
    }
	
	protected ModelView getView() {
	    return view;
    }
	
	public void selectionChanged(SelectionChangedEvent event) {
	    IStructuredSelection sel = (IStructuredSelection) event.getSelection();
	    if (!sel.isEmpty()) {
	    	Object obj = sel.getFirstElement();
	    	if (obj instanceof TreeObject) {
	    		setTreeObject((TreeObject) obj);
	    		return;
	    	}
	    }
	    setTreeObject(null);
    	return;
    }

}
