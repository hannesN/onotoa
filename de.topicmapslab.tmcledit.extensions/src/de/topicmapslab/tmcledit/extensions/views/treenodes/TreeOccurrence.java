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
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;

public class TreeOccurrence extends TreeObject{

	public TreeOccurrence(ModelView modelView, OccurrenceTypeConstraint otc) {
		this(modelView, otc, null);
	}

	public TreeOccurrence(ModelView modelView, OccurrenceTypeConstraint otc, String name) {
		super(modelView, name, KindOfTopicType.OCCURRENCE_TYPE);
		setModel(otc);
	}

	private OccurrenceTypeConstraint getOccurrenceTypeConstraint() {
		return (OccurrenceTypeConstraint) getModel();
	}
	
	@Override
	public String getName() {
		
		TopicType type = getOccurrenceTypeConstraint().getType();
		
		return (type==null) ? "no type set" : type.getName();
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.OCCURRENCECONSTRAINT);
		
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier().equals(getOccurrenceTypeConstraint())) {
			if (notification.getNewValue() instanceof TopicType) {
				if (notification.getOldValue()!=null)
					((TopicType)notification.getOldValue()).eAdapters().remove(this);
				((TopicType)notification.getNewValue()).eAdapters().add(this);
			}
		}
		getModelView().getViewer().refresh(this);
	}
}
