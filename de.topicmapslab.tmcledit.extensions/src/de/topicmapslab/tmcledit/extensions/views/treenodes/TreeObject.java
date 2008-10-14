/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.provider.ModelItemProviderAdapterFactory;


public class TreeObject implements IAdaptable, Adapter {
	private String name;
	protected EditingDomain editingDomain;
	private TreeParent parent;
	private Notifier target;
	protected static ModelItemProviderAdapterFactory factory = new ModelItemProviderAdapterFactory();
	
	private final ModelView modelView;

	public TreeObject(ModelView modelView) {
		this.modelView = modelView;
	}

	public TreeObject(ModelView modelView, String name) {
		this(modelView);
		this.name = name;
	}

	public String getName() {
		return name;
	}

	protected ModelView getModelView() {
		return modelView;
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
	
	public Object getModel() {
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
	
	public void handleRename() {
		
	}
}