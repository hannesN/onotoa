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

/**
 * Node for subject locator constraints
 * 
 * @author Hannes Niederhausen
 *
 */
public class TreeSubjectLocator extends TreeObject{

	/**
	 * Constructor
	 * @param modelView the modelview containing this node
	 * @param slc the subject locator constraint
	 */
	public TreeSubjectLocator(ModelView modelView, SubjectLocatorConstraint slc) {
		this(modelView, slc, null);
	}

	/**
	 * Constructor
	 * @param modelView the modelview containing this node
	 * @param slc the subject locator constraint
	 * @param name the label of the node
	 */
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
		// nothing to do
	}
}
