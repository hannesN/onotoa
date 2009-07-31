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
package de.topicmapslab.tmcledit.model.views.treenodes;

import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;
import de.topicmapslab.tmcledit.model.util.ImageProvider;
import de.topicmapslab.tmcledit.model.views.ModelView;

/**
 * @author Hannes Niederhausen
 * 
 */
public class TreeTopic extends TreeParent {

	public TreeTopic(ModelView viewer, TopicType topic) {
		super(viewer, null, topic.getKind());
		setModel(topic);
	}

	@Override
	public void notifyChanged(Notification notification) {
		if ((notification.getEventType() == Notification.SET)
		        && (notification.getFeatureID(String.class) == ModelPackage.TOPIC_TYPE__NAME)) {
			getModelView().getViewer().refresh(this);
		} else if (notification.getFeatureID(EList.class) == ModelPackage.TOPIC_TYPE__OCCURRENCE_CONSTRAINTS) {
			if (notification.getEventType() == Notification.ADD) {
				addChild(new TreeOccurrence(getModelView(), (OccurrenceTypeConstraint) notification.getNewValue()));
				refresh();
			} else if (notification.getEventType() == Notification.REMOVE) {
				for (Iterator<TreeObject> it = getChildrenList().iterator(); it.hasNext();) {
					TreeObject obj = it.next();
					if (obj instanceof TreeOccurrence) {
						if (((TreeOccurrence) obj).getModel().equals(notification.getOldValue())) {
							it.remove();
							break;
						}
					}
				}
				refresh();
			}
		} else if (notification.getFeatureID(EList.class) == ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS) {
			if (notification.getEventType() == Notification.ADD) {
				addChild(new TreeName(getModelView(), (NameTypeConstraint) notification.getNewValue()));
				refresh();
			} else if (notification.getEventType() == Notification.REMOVE) {
				for (Iterator<TreeObject> it = getChildrenList().iterator(); it.hasNext();) {
					TreeObject obj = it.next();
					if (obj instanceof TreeName) {
						if (((TreeName) obj).getModel().equals(notification.getOldValue())) {
							it.remove();
							break;
						}
					}
				}
				refresh();
			}
		} else if (notification.getFeatureID(EList.class) == ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS) {
			if (notification.getEventType() == Notification.ADD) {
				addChild(new TreeSubjectIdentifier(getModelView(), (SubjectIdentifierConstraint) notification.getNewValue()));
				refresh();
			} else if (notification.getEventType() == Notification.REMOVE) {
				for (Iterator<TreeObject> it = getChildrenList().iterator(); it.hasNext();) {
					TreeObject obj = it.next();
					if (obj instanceof TreeSubjectIdentifier) {
						if (obj.getModel().equals(notification.getOldValue())) {
							it.remove();
							break;
						}
					}
				}
				refresh();
			}
		} else if (notification.getFeatureID(EList.class) == ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT) {
			if (notification.getEventType() == Notification.ADD) {
				addChild(new TreeSubjectLocator(getModelView(),  (SubjectLocatorConstraint) notification.getNewValue()));
				refresh();
			} else if (notification.getEventType() == Notification.REMOVE) {
				for (Iterator<TreeObject> it = getChildrenList().iterator(); it.hasNext();) {
					TreeObject obj = it.next();
					if (obj instanceof TreeSubjectLocator) {
						if (obj.getModel().equals(notification.getOldValue())) {
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
		InputDialog dlg = new InputDialog(getModelView().getViewer().getTree().getShell(), "New Topic Id..",
		        "Please enter the new Topic ID", oldName, new IInputValidator() {

			        public String isValid(String newText) {
				        if (newText.length() == 0)
					        return "no name given";

				        if (ModelIndexer.getTopicIndexer().getTopicTypeByName(newText) != null) {
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
