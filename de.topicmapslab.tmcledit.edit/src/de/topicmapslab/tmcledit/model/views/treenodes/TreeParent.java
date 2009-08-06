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
package de.topicmapslab.tmcledit.model.views.treenodes;

import java.util.ArrayList;
import java.util.Iterator;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.views.ModelView;


public class TreeParent extends TreeObject {
	private ArrayList<TreeObject> children;
	
	public TreeParent(ModelView viewer, String name) {
		this(viewer, name, null);
	}
			
	public TreeParent(ModelView viewer, String name, KindOfTopicType kindOfTopicType) {
		super(viewer, name, kindOfTopicType);
		children = new ArrayList<TreeObject>();
	}

	public void addChild(TreeObject child) {
		children.add(child);
		child.setParent(this);
	}

	public void removeChild(TreeObject child) {
		children.remove(child);
		child.setParent(null);
	}

	public TreeObject[] getChildren() {
		return (TreeObject[]) children.toArray(new TreeObject[children
				.size()]);
	}
	
	public ArrayList<TreeObject> getChildrenList() {
		return children;
	}

	public boolean hasChildren() {
		return children.size() > 0;
	}

	@Override
	public void dispose() {
		super.dispose();
		for (TreeObject to : children) {
			to.dispose();
		}
	}
	
	@Override
	public void setSyncView(boolean syncView) {
	    super.setSyncView(syncView);
	    for (TreeObject child : getChildrenList()) {
	    	child.setSyncView(syncView);
	    }
	}
	
	protected void clearChildren() {
	    for (Iterator<TreeObject> it=getChildrenList().iterator(); it.hasNext();) {
			TreeObject child = it.next();
			it.remove();
			child.dispose();
		}
	}
	
	public TreeObject findChildPerModel(Object childModel) {
		for (TreeObject o : children) {
			if (childModel.equals(o.getModel()))
				return o;
		}
		return null;
	}
}