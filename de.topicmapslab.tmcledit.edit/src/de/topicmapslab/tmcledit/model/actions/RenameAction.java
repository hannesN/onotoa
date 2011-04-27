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

import de.topicmapslab.tmcledit.model.views.ModelView;
import de.topicmapslab.tmcledit.model.views.treenodes.TreeObject;


/**
 * Action to rename a construct shown in a node in the {@link ModelView}
 * 
 * @author Hannes Niederhausen
 *
 */
public class RenameAction extends AbstractTreeListenerAction {

	/**
	 * 
	 * @param view the {@link ModelView}
	 */
	public RenameAction(ModelView view) {
	    super(view);
	    setText("Rename...");
	    setId("rename");
    }
	
	/**
	 * {@inheritDoc}
	 */
	protected void setTreeObject(TreeObject treeObject) {
	    super.setTreeObject(treeObject);
	    if ( (treeObject==null) || (!treeObject.canHandleRename()) )
	    	setEnabled(false);
	    else
	    	setEnabled(true);
	    
    }
	
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void run() {
		getTreeObject().handleRename();
	}
}
