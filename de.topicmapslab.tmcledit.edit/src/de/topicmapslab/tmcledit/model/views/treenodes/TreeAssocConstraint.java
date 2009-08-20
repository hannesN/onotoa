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
package de.topicmapslab.tmcledit.model.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TreeAssocConstraint extends TreeParent {

	public TreeAssocConstraint(ModelView modelView, AssociationTypeConstraint constr) {
		super(modelView, "", null);
		setModel(constr);
	}

	@Override
	public void notifyChanged(Notification notification) {
		super.notifyChanged(notification);
		if (notification.getNotifier().equals(getModel())) {
			if (notification.getFeatureID(TopicType.class)==ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE) {
				if (notification.getNewValue()!=null)
					((EObject) notification.getNewValue()).eAdapters().add(this);
				if (notification.getOldValue()!=null)
					((EObject) notification.getOldValue()).eAdapters().remove(this);
			} else if (notification.getFeatureID(EList.class)==ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS) {
				if (notification.getEventType()==Notification.ADD) {
					addTopicRoleNode((RolePlayerConstraint) notification.getNewValue());
				} else if (notification.getEventType()==Notification.REMOVE) {
					removeTopicRoleNode((RolePlayerConstraint) notification.getOldValue());
				}
				
			}
		}
		
		refresh();
	}

	@Override
	public void setModel(EObject model) {
		super.setModel(model);
		if (getAssocType() != null)
			getAssocType().eAdapters().add(this);

		createChildNodes();
	}

	private void createChildNodes() {
		for (RolePlayerConstraint rpc : getCastedModel().getPlayerConstraints()) {
			addTopicRoleNode(rpc);
		}
	}
	
	private void addTopicRoleNode(RolePlayerConstraint rpc) {
		TreeRolePlayer newChild = new TreeRolePlayer(getModelView(), rpc);
		addChild(newChild);
		refresh();
	}


	private void removeTopicRoleNode(RolePlayerConstraint rpc) {
		TreeObject child = findChildPerModel(rpc);
		removeChild(child);
		refresh();
	}

	@Override
	public String getName() {
		TopicType assocType = getAssocType();
		if (assocType != null)
			return assocType.getName();
		else
			return "no type set";
	}

	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.ASSOCIATIONCONSTRAINT_SM);
	}

	private AssociationType getAssocType() {
		AssociationTypeConstraint ac = getCastedModel();

		return (AssociationType) ac.getType();

	}

	private AssociationTypeConstraint getCastedModel() {
		return (AssociationTypeConstraint) getModel();
	}

}
