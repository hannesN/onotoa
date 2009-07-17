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

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.views.ModelView;


public class TreeParent extends TreeObject {
	private ArrayList<TreeObject> children;

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
}