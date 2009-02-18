/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.actions;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import de.topicmapslab.tmcledit.extensions.views.ModelView;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.DeleteTopicTypeCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteTopicTypeAction extends Action {

	private TopicType type;
	private ModelView modelView;

	public DeleteTopicTypeAction(ModelView modelView) {
		setText("Delete Type");
		this.modelView = modelView;
	}

	public void setType(TopicType type) {
		this.type = type;
	}
	
	@Override
	public void run() {
		Shell shell = modelView.getSite().getShell();
		if (MessageDialog.openQuestion(shell, "Are you sure?",
				"Do you really want to delete the selected type?")) {
			DeleteTopicTypeCommand cmd = new DeleteTopicTypeCommand(type);
			modelView.getEditingDomain().getCommandStack().execute(cmd);
		}

	}
}
