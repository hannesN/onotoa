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
/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TreeTopic extends TreeParent {

	public TreeTopic(ModelView viewer, TopicType topic) {
		super(viewer, null);
		setModel(topic);
	}

	@Override
	public void notifyChanged(Notification notification) {
		if ((notification.getEventType() == Notification.SET)
				&& (notification.getFeatureID(String.class) == ModelPackage.TOPIC_TYPE__NAME)) {
			getModelView().getViewer().refresh(this);
		} else if (notification.getFeatureID(EList.class) == ModelPackage.TOPIC_TYPE__OCCURENCE_CONSTRAINTS) {
			if (notification.getEventType() == Notification.ADD) {
				addChild(new TreeOccurence(getModelView(),
						(OccurenceTypeConstraint) notification.getNewValue()));
				refresh();
			} else if (notification.getEventType() == Notification.REMOVE) {
				for (Iterator<TreeObject> it = getChildrenList().iterator(); it
						.hasNext();) {
					TreeObject obj = it.next();
					if (obj instanceof TreeOccurence) {
						if (((TreeOccurence) obj).getModel().equals(
								notification.getOldValue())) {
							it.remove();
							break;
						}
					}
				}
				refresh();
			}
		} else if (notification.getFeatureID(EList.class) == ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS) {
			if (notification.getEventType() == Notification.ADD) {
				addChild(new TreeName(getModelView(),
						(NameTypeConstraint) notification.getNewValue()));
				refresh();
			} else if (notification.getEventType() == Notification.REMOVE) {
				for (Iterator<TreeObject> it = getChildrenList().iterator(); it
						.hasNext();) {
					TreeObject obj = it.next();
					if (obj instanceof TreeName) {
						if (((TreeName) obj).getModel().equals(
								notification.getOldValue())) {
							it.remove();
							break;
						}
					}
				}
				refresh();
			}
		}
		
	}
	
	private TopicType getTopicType() {
		return (TopicType) getModel();
	}

	@Override
	public String getName() {
		return getTopicType().getName();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class key) {
		if (getTopicType() == null)
			return null;

		return null;
	}

	@Override
	public void handleRename() {
		String oldName = getTopicType().getName();
		InputDialog dlg = new InputDialog(getModelView().getViewer().getTree()
				.getShell(), "New Topic Id..", "Please enter the new Topic ID",
				oldName, new IInputValidator() {

					@Override
					public String isValid(String newText) {
						if (newText.length() == 0)
							return "no name given";

						if (ModelIndexer.getInstance().getTopicType(newText)!=null) {
							return "name already used";
						}
						return null;
					}
				});
		if (InputDialog.OK == dlg.open()) {

			getModelView().getEditingDomain().getCommandStack().execute(
					new RenameTopicTypeCommand(getTopicType(), dlg.getValue()));
		}
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getTopicTypeImage(getTopicType());
	}
}
