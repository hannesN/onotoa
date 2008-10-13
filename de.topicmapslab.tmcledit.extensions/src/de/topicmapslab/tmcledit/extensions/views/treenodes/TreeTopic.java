/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.views.treenodes;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.swt.graphics.Image;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.ModelPackage;
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
	public void notifyChanged(Notification notification) {
		if ((notification.getEventType() == Notification.SET)
				&& (notification.getFeatureID(String.class) == ModelPackage.TOPIC_TYPE__ID)) {
			getModelView().getViewer().refresh(this);
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
