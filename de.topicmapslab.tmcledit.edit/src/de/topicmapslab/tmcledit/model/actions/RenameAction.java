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
 * @author Hannes Niederhausen
 *
 */
public class RenameAction extends AbstractTreeListenerAction {

	public RenameAction(ModelView view) {
	    super(view);
	    setText("Rename...");
	    setId("rename");
    }
	
	protected void setTreeObject(TreeObject treeObject) {
	    super.setTreeObject(treeObject);
	    if ( (treeObject==null) || (!treeObject.canHandleRename()) )
	    	setEnabled(false);
	    else
	    	setEnabled(true);
	    
    }
	
	@Override
	public void run() {
		getTreeObject().handleRename();
	}
}
