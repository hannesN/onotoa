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
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
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
	private EObject model;

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
	
	public EObject getModel() {
		return model;
	}
	
	public void setModel(EObject model) {
		dispose();
		this.model = model;
		model.eAdapters().add(this);
	}
	
	public void dispose() {
		if (getModel()!=null) {
			getModel().eAdapters().remove(this);
		}
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
	
	public void refresh() {
		getModelView().getViewer().refresh(this);
	}
	
	public void handleDoubleClick() {
		
	}
	
	public void handleRename() {
		
	}
}