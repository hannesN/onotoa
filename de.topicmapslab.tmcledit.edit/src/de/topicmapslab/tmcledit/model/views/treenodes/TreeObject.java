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
import de.topicmapslab.tmcledit.model.TmcleditEditPlugin;
import de.topicmapslab.tmcledit.model.util.extension.ModelViewExtensionInfo;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Superclass for the core nodes of the {@link ModelView}. 
 * 
 * @author Hannes Niederhausen
 *
 */
public class TreeObject extends AbstractModelViewNode implements IAdaptable, Adapter {

	/**
	 * Default node id
	 */
	public static final int NOT_SET = -1;
	
	/**
	 * Id for the Topic Map Schema node
	 */
	public static final int TOPIC_MAP_SCHEMA = 1;
	
	/**
	 * Id for the Diagrams node
	 */
	public static final int DIAGRAMS = 2;
	
	
	private Notifier target;
	private final KindOfTopicType kindOfTopicType;
	
	/**
	 * Constructor
	 * @param modelView modelview containing the node
	 * @param id id of the node
	 */
	public TreeObject(ModelView modelView, int id) {
		super(modelView);
		this.id = id;
		this.kindOfTopicType = null; 
	}
	/**
	 * Constructor
	 * @param modelView modelview containing the node
	 */
	public TreeObject(ModelView modelView) {
		super(modelView);
		this.kindOfTopicType = null;
	}
	
	/**
	 * Constructor
	 * @param odelView modelview containing the node
	 * @param name label of the node
	 * @param id id of the node
	 */
	public TreeObject(ModelView modelView, String name, int id) {
		this(modelView, name);
		this.id = id;
	}
	
	/**
	 * Constructor
	 * @param modelView modelview containing the node
	 * @param name label of the node
	 */
	public TreeObject(ModelView modelView, String name) {
		super(modelView);
		this.name = name;
		this.kindOfTopicType = null;
		if ((name!=null) && (name.length()>0))
			setModel(name);
	}

	/**
	 * Constructor
	 * @param modelView modelview containing the node
	 * @param kindOfTopicType kind of topic type represented by the node
	 */
	public TreeObject(ModelView modelView, KindOfTopicType kindOfTopicType) {
		super(modelView);
		this.kindOfTopicType = kindOfTopicType;
	}

	/**
	 * Constructor
	 * @param modelView modelview containing the node
	 * @param name label for the node
	 * @param kindOfTopicType kind of topic type represented by the node
	 */
	public TreeObject(ModelView modelView, String name, KindOfTopicType kindOfTopicType) {
		this(modelView, kindOfTopicType);
		this.name = name;
		if ((name!=null) && (name.length()>0))
			setModel(name);
	}

	/**
	 * Returns the kind of topic which should be created when the node is selected
	 * 
	 * @return the {@link KindOfTopicType} of the node
	 */
	public KindOfTopicType getKindOfTopicType() {
		return kindOfTopicType;
	}

	@Override
	public String toString() {
		return getName();
	}

	public Notifier getTarget() {
		return target;
	}
	
	@Override
	public void addChild(AbstractModelViewNode child) {
	    super.addChild(child);
	    getExtensionChildren(child);
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

	@Override
	public void dispose() {
		if ( (getModel() != null) && (getModel() instanceof EObject) ){
    		((EObject) getModel()).eAdapters().remove(this);
    	}
	    super.dispose();
	}
	
	protected Adapter getAdapter() {
    	return this;
    }

	protected void setId(int id) {
    	this.id = id;
    }

	protected void setHandleRename(boolean handleRename) {
	    this.handleRename = handleRename;
    }
	
	private void getExtensionChildren(AbstractModelViewNode parent) {
		for (ModelViewExtensionInfo mvei : TmcleditEditPlugin.getExtensionManager().getModelViewExtensionInfos()) {
			for (AbstractModelViewNode n : mvei.getProvider().getChildNodes(getModelView(), parent)) {
				parent.addChild(n);
			}
		}
	}
}