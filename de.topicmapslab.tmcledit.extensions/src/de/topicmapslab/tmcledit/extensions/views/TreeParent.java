/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views;

import java.util.ArrayList;

import org.eclipse.jface.viewers.TreeViewer;


class TreeParent extends TreeObject {
	private ArrayList<TreeObject> children;

	public TreeParent(TreeViewer viewer, String name) {
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