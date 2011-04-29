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
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.util.ImageConstants;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * Property page for name constraints
 * 
 * @author Hannes Niederhausen
 *
 */
public class TreeName extends TreeObject{

	/**
	 * Constructor
	 * 
	 * @param modelView the modelview containing the node
	 * @param ntc the represented name constraint
	 */
	public TreeName(ModelView modelView, NameTypeConstraint ntc) {
		this(modelView, ntc, null);
	}

	/**
	 * Constructor
	 * 
	 * @param modelView the modelview containing the node
	 * @param ntc the represented name constraint
	 * @param name the label of the node
	 */
	public TreeName(ModelView modelView, NameTypeConstraint ntc, String name) {
		super(modelView, name, KindOfTopicType.NAME_TYPE);
		setModel(ntc);
	}

	private NameTypeConstraint getNameTypeConstraint() {
		return (NameTypeConstraint) getModel();
	}
	
	@Override
	public void setModel(Object model) {
		super.setModel(model);
		if (getNameTypeConstraint().getType()!=null)
			getNameTypeConstraint().getType().eAdapters().add(this);
		
	}
	
	@Override
	public void dispose() {
		if ( (getModel()!=null) && (getNameTypeConstraint().getType()!=null) )
			getNameTypeConstraint().getType().eAdapters().remove(this);
		super.dispose();
	}
	
	@Override
	public String getName() {
		if (getNameTypeConstraint().getType()==null)
			return "Default";
		return getNameTypeConstraint().getType().getName();
	}
	
	@Override
	public void notifyChanged(Notification notification) {
		if (notification.getNotifier().equals(getNameTypeConstraint())) {
			if (notification.getNewValue() instanceof TopicType) {
				if (notification.getOldValue()!=null)
					((TopicType)notification.getOldValue()).eAdapters().remove(this);
				((TopicType)notification.getNewValue()).eAdapters().add(this);
			}
		}
		
		getModelView().getViewer().refresh(this);
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getImage(ImageConstants.NAMECONSTRAINT);
	}
	
}
