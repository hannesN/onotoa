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

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class TreeObject extends AbstractModelViewNode implements IAdaptable, Adapter {
	public static final int NOT_SET = -1;
	public static final int TOPIC_MAP_SCHEMA = 1;
	public static final int DIAGRAMS = 2;
	
	
	private Notifier target;
	private final KindOfTopicType kindOfTopicType;
	
	
	public TreeObject(ModelView modelView, int id) {
		super(modelView);
		this.id = id;
		this.kindOfTopicType = null; 
	}
	
	public TreeObject(ModelView modelView) {
		super(modelView);
		this.kindOfTopicType = null;
	}
	
	public TreeObject(ModelView modelView, String name, int id) {
		this(modelView, name);
		this.id = id;
	}
	
	public TreeObject(ModelView modelView, String name) {
		super(modelView);
		this.name = name;
		this.kindOfTopicType = null;
	}

	public TreeObject(ModelView modelView, KindOfTopicType kindOfTopicType) {
		super(modelView);
		this.kindOfTopicType = kindOfTopicType;
	}

	public TreeObject(ModelView modelView, String name, KindOfTopicType kindOfTopicType) {
		this(modelView, kindOfTopicType);
		this.name = name;
	}

	public KindOfTopicType getKindOfTopicType() {
		return kindOfTopicType;
	}

	protected void setId(int id) {
		this.id = id;
	}
	
	@Override
	public String toString() {
		return getName();
	}

	public Notifier getTarget() {
		return target;
	}

	public boolean isAdapterForType(Object type) {
		return false;
	}
	
	@Override
	public void setModel(Object model) {
	    super.setModel(model);
	    if (model instanceof EObject)
    		((EObject) model).eAdapters().add(this);
	}

	public void notifyChanged(Notification notification) {
	}

	public void setTarget(Notifier newTarget) {
		target = newTarget;
	}

	protected Adapter getAdapter() {
		return this;
	}

	@Override
	public void dispose() {
		if ( (getModel() != null) && (getModel() instanceof EObject) ){
    		((EObject) getModel()).eAdapters().remove(this);
    	}
	    super.dispose();
	}
	
	protected void setHandleRename(boolean handleRename) {
	    this.handleRename = handleRename;
    }
}