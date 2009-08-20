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

import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class TreeObject implements IAdaptable, Adapter {
	public static final int NOT_SET = -1;
	public static final int TOPIC_MAP_SCHEMA = 1;
	public static final int DIAGRAMS = 2;
	
	
	private String name;
	protected EditingDomain editingDomain;
	private TreeParent parent;
	private Notifier target;
	private final KindOfTopicType kindOfTopicType;
	private boolean syncView = false;
	private boolean handleRename = false;
	
	private int id = -1;

	private final ModelView modelView;
	private EObject model;

	public TreeObject(ModelView modelView, int id) {
		this(modelView, null);
		this.id = NOT_SET;
	}
	
	public TreeObject(ModelView modelView) {
		this(modelView, null);
	}

	public TreeObject(ModelView modelView, KindOfTopicType kindOfTopicType) {
		this.kindOfTopicType = kindOfTopicType;
		this.modelView = modelView;
	}

	public TreeObject(ModelView modelView, String name, KindOfTopicType kindOfTopicType) {
		this(modelView, kindOfTopicType);
		this.name = name;
	}

	public KindOfTopicType getKindOfTopicType() {
		return kindOfTopicType;
	}

	public String getName() {
		return name;
	}

	protected ModelView getModelView() {
		return modelView;
	}

	public void setParent(TreeParent parent) {
		this.parent = parent;
		if (parent != null)
			this.syncView = parent.isSyncView();
	}

	public int getId() {
	    return id;
    }
	
	protected void setId(int id) {
		this.id = id;
	}
	
	public TreeParent getParent() {
		return parent;
	}

	@Override
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
		if (getModel() != null) {
			getModel().eAdapters().remove(this);
		}
	}

	public Notifier getTarget() {
		return target;
	}

	public boolean isAdapterForType(Object type) {
		return false;
	}

	public void notifyChanged(Notification notification) {
	}

	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}

	public Image getImage() {
		return null;
	}

	public void refresh() {
		if (syncView)
			getModelView().getViewer().refresh(this);
	}

	protected boolean isSyncView() {
		return syncView;
	}

	public void setSyncView(boolean syncView) {
		this.syncView = syncView;
	}

	public void handleDoubleClick() {

	}
	
	protected Adapter getAdapter() {
		return this;
	}

	public void handleRename() {

	}
	
	public boolean canHandleRename() {
		return handleRename;
	}
	
	protected void setHandleRename(boolean handleRename) {
	    this.handleRename = handleRename;
    }
}