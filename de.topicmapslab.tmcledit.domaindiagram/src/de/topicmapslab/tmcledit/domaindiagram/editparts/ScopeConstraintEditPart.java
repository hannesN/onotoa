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
package de.topicmapslab.tmcledit.domaindiagram.editparts;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EObject;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class ScopeConstraintEditPart extends AbstractLabelEditPart {

	@Override
	protected void createEditPolicies() {
	}

	public void notifyChanged(Notification notification) {
		if (notification.getFeatureID(TopicType.class)==ModelPackage.SCOPE_CONSTRAINT__TYPE) {
			EObject oldVal = (EObject) notification.getOldValue();
			EObject newVal = (EObject) notification.getNewValue();
			
			if (oldVal!=null)
				oldVal.eAdapters().remove(this);
			if (newVal!=null)
				newVal.eAdapters().add(this);
		}
		refreshVisuals();
	}
	
	

	@Override
	protected void refreshVisuals() {
		TopicType type = getCastedModel().getType();
		if (type!=null)
			getNameLabel().setText("@"+type.getName());
		else
			getNameLabel().setText("@tmdm:subject");
		
		StringBuilder b = new StringBuilder("");
		b.append(getCastedModel().getCardMin());
		b.append("..");
		b.append(getCastedModel().getCardMax());
		b.append("");
		getCardLabel().setText(b.toString());
	}
	
	private ScopeConstraint getCastedModel() {
		return (ScopeConstraint) getModel();
	}
}
