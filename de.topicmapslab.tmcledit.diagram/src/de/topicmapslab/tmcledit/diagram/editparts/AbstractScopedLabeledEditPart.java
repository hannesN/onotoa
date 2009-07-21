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
package de.topicmapslab.tmcledit.diagram.editparts;

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicType;

public abstract class AbstractScopedLabeledEditPart extends
		AbstractLabelEditPart {

	public AbstractScopedLabeledEditPart() {
		super();
	}

	protected void addScopeText() {
				
		StringBuffer buffer = new StringBuffer();
		ReifierConstraint rc = getReifierConstraint();
		if (rc!=null) {
			buffer.append("~ ");
			if (rc.getType()==null)
				buffer.append("tmdm:subject");
			else
				buffer.append(rc.getType().getName());
			buffer.append(" [");
			buffer.append(rc.getCardMin());
			buffer.append("..");
			buffer.append(rc.getCardMax());
			buffer.append("]\n");
		}
		for (ScopeConstraint sc : getScope()) {
			buffer.append("@");
			buffer.append(sc.getType().getName());
			buffer.append("  [");
			buffer.append(sc.getCardMin());
			buffer.append("..");
			buffer.append(sc.getCardMax());
			buffer.append("]");
			buffer.append("\n");
		}
		if (buffer.length()==0)
			return;
		buffer.setLength(buffer.length()-1);
		addScopeLabel(buffer.toString());
	}
	
	@Override
	public void setModel(Object model) {
		if (getModel()!=null)
			removeScopeAdapters();
		super.setModel(model);
		updateScopeAdapters();
	}
	
	private void updateScopeAdapters() {
		for (ScopeConstraint sc : getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters().add(this);
			sc.eAdapters().add(this);
		}
	}
	
	private void removeScopeAdapters() {
		for (ScopeConstraint sc : getScope()) {
			if (sc.getType()!=null)
				sc.getType().eAdapters().remove(this);
			sc.eAdapters().remove(this);
		}
	}

	private ReifierConstraint getReifierConstraint() {
		if (getModel() instanceof AbstractTypedConstraint) {
			AbstractTypedConstraint atc = (AbstractTypedConstraint) getModel();
			if (atc.getType() instanceof ReifiableTopicType)
				return ((ReifiableTopicType)atc.getType()).getReifierConstraint();
		}
		return null;
	}
	
	private List<ScopeConstraint> getScope() {
		if (getModel() instanceof AbstractTypedConstraint) {
			TopicType type = ((AbstractTypedConstraint)getModel()).getType();
			if (type instanceof ScopedTopicType)
				return ((ScopedTopicType)type).getScope();
		}
		return Collections.emptyList();
	}
	
	@SuppressWarnings("unchecked")
	public void notifyChanged(Notification notification) {
		EObject oldValue = null;
		EObject newValue = null; 
		if (notification.getOldValue() instanceof EObject)
			oldValue = (EObject) notification.getOldValue();
		if (notification.getNewValue() instanceof EObject)
			newValue = (EObject) notification.getNewValue();
		
		if (notification.getFeatureID(List.class)==ModelPackage.SCOPED_TOPIC_TYPE__SCOPE) {
			if (notification.getEventType()==Notification.ADD) {
				ScopeConstraint sc = (ScopeConstraint) newValue;
				sc.eAdapters().add(this);
				if (sc.getType()!=null)
					sc.getType().eAdapters().add(this);
			} else if (notification.getEventType()==Notification.ADD_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) newValue) {
					sc.eAdapters().remove(this);
					if (sc.getType()!=null)
						sc.getType().eAdapters().remove(this);
				}
			} else if (notification.getEventType()==Notification.REMOVE) {
				ScopeConstraint sc = (ScopeConstraint) oldValue;
				sc.eAdapters().remove(this);
				if (sc.getType()!=null)
					sc.getType().eAdapters().remove(this);
			} else if (notification.getEventType()==Notification.REMOVE_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) oldValue) {
					sc.eAdapters().remove(this);
					if (sc.getType()!=null)
						sc.getType().eAdapters().remove(this);
				}
			}
		} else if (notification.getFeatureID(RoleConstraint.class)==ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT) {
			if (oldValue!=null)
				oldValue.eAdapters().remove(this);
			if (newValue!=null)
				newValue.eAdapters().add(this);
				
				
		}
		
	}
}