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

import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.TopicType;

public abstract class AbstractScopedLabeledEditPart extends
		AbstractLabelEditPart {

	public AbstractScopedLabeledEditPart() {
		super();
	}

	protected void addScopeText(StringBuffer buffer) {
		for (ScopeConstraint sc : getScope()) {
			buffer.append("\n@");
			buffer.append(sc.getType().getName());
			buffer.append("  [");
			buffer.append(sc.getCardMin());
			buffer.append("..");
			buffer.append(sc.getCardMax());
			buffer.append("]");
		}
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

	private List<ScopeConstraint> getScope() {
		if (getModel() instanceof AbstractTypedConstraint) {
			TopicType type = ((AbstractTypedConstraint)getModel()).getType();
			if (type instanceof ScopedTopicType)
				return ((ScopedTopicType)type).getScope();
		}
		return Collections.emptyList();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(List.class)==ModelPackage.SCOPED_TOPIC_TYPE__SCOPE) {
			if (notification.getEventType()==Notification.ADD) {
				ScopeConstraint sc = (ScopeConstraint) notification.getNewValue();
				sc.eAdapters().add(this);
				if (sc.getType()!=null)
					sc.getType().eAdapters().add(this);
			} else if (notification.getEventType()==Notification.ADD_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) notification.getNewValue()) {
					sc.eAdapters().remove(this);
					if (sc.getType()!=null)
						sc.getType().eAdapters().remove(this);
				}
			} else if (notification.getEventType()==Notification.REMOVE) {
				ScopeConstraint sc = (ScopeConstraint) notification.getOldValue();
				sc.eAdapters().remove(this);
				if (sc.getType()!=null)
					sc.getType().eAdapters().remove(this);
			} else if (notification.getEventType()==Notification.REMOVE_MANY) {
				for (ScopeConstraint sc : (EList<ScopeConstraint>) notification.getOldValue()) {
					sc.eAdapters().remove(this);
					if (sc.getType()!=null)
						sc.getType().eAdapters().remove(this);
				}
			}
		}		
	}
}