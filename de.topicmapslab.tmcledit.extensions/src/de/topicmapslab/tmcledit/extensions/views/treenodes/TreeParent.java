/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import java.util.ArrayList;

import de.topicmapslab.tmcledit.extensions.views.ModelView;


public class TreeParent extends TreeObject {
	private ArrayList<TreeObject> children;

	public TreeParent(ModelView viewer, String name) {
		super(viewer, name);
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

	public boolean hasChildren() {
		return children.size() > 0;
	}
}