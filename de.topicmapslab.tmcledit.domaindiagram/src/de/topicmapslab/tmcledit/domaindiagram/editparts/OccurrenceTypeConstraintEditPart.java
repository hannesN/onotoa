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
import java.util.Collections;
import java.util.List;

import org.eclipse.draw2d.IFigure;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeData;
import de.topicmapslab.tmcledit.domaindiagram.policies.AbstractTypedConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * {@link EditPart} for the occurrence constraint
 * 
 * @author Hannes Niederhausen
 *
 */
public class OccurrenceTypeConstraintEditPart extends AbstractLabelEditPart {

	OccurrenceTypeConstraint getCastedModel() {
		return (OccurrenceTypeConstraint) getModel();
	}

	@Override
	protected IFigure createFigure() {
		IFigure f = super.createFigure();
		getNameLabel().setIcon(
				ImageProvider.getImage(ImageConstants.OCCURRENCECONSTRAINT_SM));

		return f;
	}

	@Override
	protected void createEditPolicies() {
		installEditPolicy(EditPolicy.DIRECT_EDIT_ROLE,
				new AbstractTypedConstraintDirectEditPolicy());
	}

	@Override
	protected boolean isEditable() {
		return (getCastedModel().getType() != null);
	}

	@Override
	protected void refreshVisuals() {
		OccurrenceTypeConstraint otc = getCastedModel();
		TopicType type = otc.getType();
		if (type != null) {
			getNameLabel().setText(type.getName());
		} else
			getNameLabel().setText("tmdm:subject");

	}

	@Override
	public void activate() {
		if (getCastedModel().getType() != null)
			getCastedModel().getType().eAdapters().add(this);
		super.activate();
	}

	@Override
	public void deactivate() {
		if (getCastedModel().getType() != null)
			getCastedModel().getType().eAdapters().remove(this);
		super.deactivate();

	}

	@Override
	public List<IAction> getActions() {
//		ArrayList<IAction> result = new ArrayList<IAction>();

		// to ealry need to refactor contextmenuprovider
//		result.add(new DeleteTypedConstraintAction(getEMFCommendStack(),
//				getCastedModel()));

		return Collections.emptyList();
	}

	public void notifyChanged(Notification notification) {
		if (notification.getEventType() == Notification.SET) {
			if (notification.getNewValue() instanceof TopicType) {
				TopicType old = (TopicType) notification.getOldValue();
				if (old != null)
					old.eAdapters().remove(this);
				if (notification.getNewValue() != null)
					((TopicType) notification.getNewValue()).eAdapters().add(
							this);

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

		MenuManager subMenu = new MenuManager("Set Occurrence Type");
		SetTypeData data = new SetTypeData();
		data.typedConstraint = getCastedModel();
		data.editDomain = getEditDomain();
		data.schema = getTopicMapSchema();
		data.featureId = ModelPackage.OCCURRENCE_TYPE_CONSTRAINT__TYPE;

		// subMenu.add(new SetAssociationAction(data));
		OccurrenceType ot = (OccurrenceType) getCastedModel().getType();
		List<SetTypeData> dataList = new ArrayList<SetTypeData>();
		for (TopicType tt : getTopicMapSchema().getTopicTypes()) {
			if ((tt instanceof OccurrenceType) && (!(tt.equals(ot)))) {
				if (!alreadyUsed((TopicType)getCastedModel().eContainer(), (OccurrenceType) tt)) {
					SetTypeData d = data.clone();
					d.type = tt;
					dataList.add(d);
				}
			}
		}
		
		EditPartUtil.sortAndAddAction(dataList, subMenu);
		
		result.add(subMenu);

		return result;
	}

	/**
	 * Checks if the given topic type already has a occurrence type constraint with the given name type
	 * @param tt the topic type containing the constraints
	 * @param ot the occurrence type to check
	 * @return
	 */
	public boolean alreadyUsed(TopicType tt, OccurrenceType ot) {
		for (OccurrenceTypeConstraint otc : tt.getOccurrenceConstraints()) {
			if (otc.equals(getModel()))
				continue;
			if (ot.equals(otc.getType()))
				return true;
		}
		return false;
	}
}
