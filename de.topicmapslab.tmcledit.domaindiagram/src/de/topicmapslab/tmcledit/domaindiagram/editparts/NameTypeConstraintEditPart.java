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
import org.eclipse.gef.EditPolicy;

import de.topicmapslab.tmcledit.domaindiagram.policies.NameConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class NameTypeConstraintEditPart extends AbstractScopedLabeledEditPart {

	private NameTypeConstraint getCastedModel() {
		return (NameTypeConstraint) getModel();
	}
	
	@Override
	protected boolean isEditable() {
		return false;
	}
	
	@Override
	protected void createEditPolicies() {
		super.createEditPolicies();
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new NameConstraintDirectEditPolicy());

	}

	@Override
	protected void refreshVisuals() {
		super.refreshVisuals();
		NameTypeConstraint ntc = getCastedModel();
		NameType type = (NameType) ntc.getType();

		if (type == null) {
			getNameLabel().setText("default");
		} else {
			getNameLabel().setText(type.getName());

			if (".*".equals(type.getRegExp()))
				getRegExpLabel().setText("");
			else
				getRegExpLabel().setText("[" + type.getRegExp() + "]");
		}

		StringBuffer buffer = new StringBuffer(50);
		buffer.append(ntc.getCardMin());
		buffer.append("..");
		buffer.append(ntc.getCardMax());
		getCardLabel().setText(buffer.toString());

		buffer.setLength(0);
//		clearScopeLables();
		// addScopeText();

		// getFigure().revalidate();
		// getFigure().getParent().repaint();

	}

	@Override
	public void activate() {
		super.activate();
		if (getCastedModel().getType() != null)
			getCastedModel().getType().eAdapters().add(this);
	}

	@Override
	public void deactivate() {
		if (getCastedModel().getType() != null)
			getCastedModel().getType().eAdapters().remove(this);
		super.deactivate();
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);

		if (notification.getNotifier() == getModel()) {
			if (notification.getFeatureID(TopicType.class) == ModelPackage.NAME_TYPE_CONSTRAINT__TYPE) {
				TopicType tmp = (TopicType) notification.getOldValue();
				if (tmp != null)
					tmp.eAdapters().remove(this);
				tmp = (TopicType) notification.getNewValue();
				if (tmp != null)
					tmp.eAdapters().add(this);
			}
		}

		refreshVisuals();
	}

}
