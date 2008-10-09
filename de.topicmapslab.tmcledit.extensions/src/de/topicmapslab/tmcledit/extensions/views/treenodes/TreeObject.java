/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.provider.ModelItemProviderAdapterFactory;


public class TreeObject implements IAdaptable, Adapter {
	private String name;
	private TreeParent parent;
	private Notifier target;
	protected static ModelItemProviderAdapterFactory factory = new ModelItemProviderAdapterFactory();
	protected final TreeViewer viewer;

	public TreeObject(TreeViewer viewer) {
		this.viewer = viewer;
	}

	public TreeObject(TreeViewer viewer, String name) {
		this.viewer = viewer;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
	}

	public TreeParent getParent() {
		return parent;
	}

	public String toString() {
		return getName();
	}

	@SuppressWarnings("unchecked")
	public Object getAdapter(Class key) {
		
		return null;
	}
	
	public void dispose() {
	}

	@Override
	public Notifier getTarget() {
		return target;
	}

	@Override
	public boolean isAdapterForType(Object type) {
		return false;
	}

	@Override
	public void notifyChanged(Notification notification) {
	}

	@Override
	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}
	
	public Image getImage() {
		return null;
	}
	
	public void handleDoubleClick() {
		
	}
}