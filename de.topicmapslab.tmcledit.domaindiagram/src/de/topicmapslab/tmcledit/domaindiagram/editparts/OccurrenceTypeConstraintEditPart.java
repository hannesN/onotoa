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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeData;
import de.topicmapslab.tmcledit.domaindiagram.policies.AbstractTypedConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class OccurrenceTypeConstraintEditPart extends AbstractLabelEditPart {

	
	OccurrenceTypeConstraint getCastedModel() {
		return (OccurrenceTypeConstraint) getModel();
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE, new AbstractTypedConstraintDirectEditPolicy());
	}

	@Override
	protected boolean isEditable() {
		return (getCastedModel().getType()!=null);
	}
	
	@Override
	protected void refreshVisuals() {
		OccurrenceTypeConstraint otc = getCastedModel();
		TopicType type = otc.getType();
		if (type!=null) {
			getNameLabel().setText(type.getName());
		} else
			getNameLabel().setText("tmdm:subject");
		
	}

	@Override
	public void activate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().add(this);
		super.activate();
	}
	
	@Override
	public void deactivate() {
		if (getCastedModel().getType()!=null)
			getCastedModel().getType().eAdapters().remove(this);
		super.deactivate();

	}
	
	public void notifyChanged(Notification notification) {
		if (notification.getEventType()==Notification.SET) {
			if (notification.getNewValue() instanceof TopicType) {
				TopicType old = (TopicType) notification.getOldValue();
				if (old!=null)
					old.eAdapters().remove(this);
				if (notification.getNewValue()!=null)
					((TopicType) notification.getNewValue()).eAdapters().add(this);
			
			}
		}
		refreshVisuals();
		
	}
	
	private TopicMapSchema getTopicMapSchema() {
		return (TopicMapSchema) getCastedModel().getType().eContainer();
	}

	@Override
	public List<IContributionItem> getItems() {
		List<IContributionItem> result = new ArrayList<IContributionItem>();

		MenuManager subMenu = new MenuManager("Set Occurrence");
		SetTypeData data = new SetTypeData();
		data.typedConstraint = getCastedModel();
		data.editDomain = getEditDomain();
		data.schema = getTopicMapSchema();
		data.featureId = ModelPackage.OCCURRENCE_TYPE_CONSTRAINT__TYPE;
		
//		subMenu.add(new SetAssociationAction(data));
		for (TopicType tt : getTopicMapSchema().getTopicTypes()) {
			if (tt instanceof OccurrenceType) {
				SetTypeData d = data.clone();
				d.type = tt;
				subMenu.add(new SetTypeAction(d));
			}
		}
		result.add(subMenu);

		return result;
	}
}
