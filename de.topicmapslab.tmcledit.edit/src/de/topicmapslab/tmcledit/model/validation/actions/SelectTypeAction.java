/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.dialogs.FilterTopicSelectionDialog;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;


/**
 * @author Hannes Niederhausen
 *
 */
public abstract class SelectTypeAction extends TopicTypeAction {
	
	public SelectTypeAction(CommandStack cmdStack, KindOfTopicType type) {
		super(cmdStack, type);
		
	}
	
	@Override
	public void run() {
		IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
	
		KindOfTopicType tmp = null;
		if (ModelIndexer.getInstance().isFilterActivated(kindOfType))
			tmp = kindOfType;
		
		FilterTopicSelectionDialog dialog = new FilterTopicSelectionDialog(
				activeWorkbenchWindow.getShell(), tmp);
		
		if (dialog.open()==Dialog.OK) {
			if (dialog.getResult().length>0) {
				TopicType type = (TopicType) dialog.getFirstResult();
		
				AbstractCommand cmd = getCommand(type);
				getCommandStack().execute(cmd);
				
			}
		}
		
	}
}
