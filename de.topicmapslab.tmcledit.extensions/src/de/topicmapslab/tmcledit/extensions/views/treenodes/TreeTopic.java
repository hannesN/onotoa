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

/**
 * @author Hannes Niederhausen
 * 
 */
public class TreeTopic extends TreeParent {

	private final TopicType topic;

	public TreeTopic(ModelView viewer, TopicType topic) {
		super(viewer, null);
		this.topic = topic;
		topic.eAdapters().add(this);
	}

	public TopicType getTopic() {
		return topic;
	}
	
	@Override
	public Object getModel() {
		return getTopic();
	}

	@Override
	public void notifyChanged(Notification notification) {
		if ((notification.getEventType() == Notification.SET)
				&& (notification.getFeatureID(String.class) == ModelPackage.TOPIC_TYPE__ID)) {
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
					if (obj instanceof TreeOccurence) {
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

	@Override
	public String getName() {
		return topic.getId();
	}

	@Override
	public void dispose() {
		topic.eAdapters().remove(this);
		super.dispose();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object getAdapter(Class key) {
		if (topic == null)
			return null;

		return null;
	}

	@Override
	public void handleRename() {
		String oldName = topic.getId();
		InputDialog dlg = new InputDialog(getModelView().getViewer().getTree()
				.getShell(), "New Topic Id..", "Please enter the new Topic ID",
				oldName, new IInputValidator() {

					@Override
					public String isValid(String newText) {
						if (newText.length() == 0)
							return "no name given";

						// TODO check if there's a topic with the same name

						return null;
					}
				});
		if (InputDialog.OK == dlg.open()) {

			getModelView().getEditingDomain().getCommandStack().execute(
					new RenameTopicTypeCommand(topic, dlg.getValue()));
		}
	}
	
	@Override
	public Image getImage() {
		return ImageProvider.getTopicTypeImage(topic);
	}
}
