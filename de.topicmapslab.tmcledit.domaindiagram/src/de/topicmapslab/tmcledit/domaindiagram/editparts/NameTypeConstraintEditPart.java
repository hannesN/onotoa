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
import org.eclipse.gef.EditPart;
import org.eclipse.gef.EditPolicy;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.MenuManager;

import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeAction;
import de.topicmapslab.tmcledit.domaindiagram.action.SetTypeData;
import de.topicmapslab.tmcledit.domaindiagram.policies.AbstractTypedConstraintDirectEditPolicy;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

/**
 * {@link EditPart} for the name constraint
 * 
 * @author Hannes Niederhausen
 *
 */
public class NameTypeConstraintEditPart extends AbstractLabelEditPart {

	private NameTypeConstraint getCastedModel() {
		return (NameTypeConstraint) getModel();
	}

	@Override
	protected IFigure createFigure() {
		IFigure f = super.createFigure();
		getNameLabel().setIcon(
				ImageProvider.getImage(ImageConstants.NAMECONSTRAINT_SM));

		return f;
	}

	@Override
	protected boolean isEditable() {
		return (getCastedModel().getType() != null);
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
			getNameLabel().setText("name");
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
		return (TopicMapSchema) getCastedModel().eContainer().eContainer();
	}

	@Override
	public List<IAction> getActions() {
//		ArrayList<IAction> result = new ArrayList<IAction>();

		// to ealry need to refactor contextmenuprovider
//		result.add(new DeleteTypedConstraintAction(getEMFCommendStack(),
//				getCastedModel()));

		return Collections.emptyList();
	}

	@Override
	public List<IContributionItem> getItems() {
		List<IContributionItem> result = new ArrayList<IContributionItem>();

		MenuManager subMenu = new MenuManager("Set Name Type");
		SetTypeData data = new SetTypeData();
		data.typedConstraint = getCastedModel();
		data.editDomain = getEditDomain();
		data.schema = getTopicMapSchema();
		data.kind = KindOfTopicType.NAME_TYPE;
		data.featureId = ModelPackage.NAME_TYPE_CONSTRAINT__TYPE;

		subMenu.add(new SetTypeAction(data));
		
		NameType nt = (NameType) getCastedModel().getType();
		List<SetTypeData> dataList = new ArrayList<SetTypeData>();
		for (TopicType tt : getTopicMapSchema().getTopicTypes()) {
			if ((tt instanceof NameType) && (!tt.equals(nt))) {
				if (!alreadyUsed((TopicType) getCastedModel().eContainer(), (NameType) tt)) {
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
	 * Checks if the given topic type already has a name type constraint with the given name type
	 * @param tt the topic type containing the constraints
	 * @param nt the name type to check
	 * @return
	 */
	private boolean alreadyUsed(TopicType tt, NameType nt) {
		for (NameTypeConstraint ntc : tt.getNameConstraints()) {
			if (ntc.equals(getModel()))
				continue;
			if (nt.equals(ntc.getType()))
				return true;
		}
		return false;
	}

}
