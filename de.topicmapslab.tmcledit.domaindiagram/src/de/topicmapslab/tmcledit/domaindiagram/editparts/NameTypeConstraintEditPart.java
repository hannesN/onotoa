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

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeData;
import de.topicmapslab.tmcledit.domaindiagram.policies.AbstractTypedConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

public class NameTypeConstraintEditPart extends AbstractLabelEditPart {

	private NameTypeConstraint getCastedModel() {
		return (NameTypeConstraint) getModel();
	}
	
	@Override
	protected IFigure createFigure() {
		IFigure f = super.createFigure();
		getNameLabel().setIcon(ImageProvider.getImage(ImageConstants.NAMECONSTRAINT_SM));
		
		return f;
	}
	
	@Override
	protected boolean isEditable() {
		return (getCastedModel().getType()!=null);
	}
	
	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new AbstractTypedConstraintDirectEditPolicy());

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
		}

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

	public void notifyChanged(Notification notification) {
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
	private TopicMapSchema getTopicMapSchema() {
		return (TopicMapSchema) getCastedModel().getType().eContainer();
	}

	@Override
	public List<IContributionItem> getItems() {
		List<IContributionItem> result = new ArrayList<IContributionItem>();

		MenuManager subMenu = new MenuManager("Set Name");
		SetTypeData data = new SetTypeData();
		data.typedConstraint = getCastedModel();
		data.editDomain = getEditDomain();
		data.schema = getTopicMapSchema();
		data.featureId = ModelPackage.NAME_TYPE_CONSTRAINT__TYPE;
		
//		subMenu.add(new SetAssociationAction(data));
		for (TopicType tt : getTopicMapSchema().getTopicTypes()) {
			if (tt instanceof NameType) {
				SetTypeData d = data.clone();
				d.type = tt;
				subMenu.add(new SetTypeAction(d));
			}
		}
		result.add(subMenu);

		return result;
	}
	
}
