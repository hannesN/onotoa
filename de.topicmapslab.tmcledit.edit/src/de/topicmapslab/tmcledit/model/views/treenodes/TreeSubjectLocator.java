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
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

public class TreeSubjectLocator extends TreeObject{

	public TreeSubjectLocator(ModelView modelView, SubjectLocatorConstraint slc) {
		this(modelView, slc, null);
	}

	public TreeSubjectLocator(ModelView modelView, SubjectLocatorConstraint slc, String name) {
		super(modelView, name, KindOfTopicType.TOPIC_TYPE);
		setModel(slc);
	}

	@Override
	public String getName() {
		return "Subject Locator Constraint";
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.SUBJECTLOCATORCONSTRAINT);
		
	}
	
	@Override
	public void notifyChanged(Notification notification) {
//		if (notification.getNotifier().equals(getOccurrenceTypeConstraint())) {
//			if (notification.getNewValue() instanceof TopicType) {
//				if (notification.getOldValue()!=null)
//					((TopicType)notification.getOldValue()).eAdapters().remove(this);
//				((TopicType)notification.getNewValue()).eAdapters().add(this);
//			}
//		}
//		getModelView().getViewer().refresh(this);
	}
}
