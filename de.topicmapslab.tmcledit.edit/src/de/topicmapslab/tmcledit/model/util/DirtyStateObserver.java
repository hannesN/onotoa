/**
 * 
 */
package de.topicmapslab.tmcledit.model.util;

import java.util.EventObject;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.AdapterImpl;

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelPackage;

/**
 * 
 * 
 * @author Hannes Niederhausen
 *
 */
public class DirtyStateObserver extends AdapterImpl implements CommandStackListener {

	private final CommandStack cmdStack;
	private Command lastCommand;
	private final File file;
	
	
	
	public DirtyStateObserver(File file, CommandStack cmdStack) {
		super();
		this.file = file;
		this.cmdStack = cmdStack;
		file.eAdapters().add(this);
		this.cmdStack.addCommandStackListener(this);
	}

	@Override
	public void commandStackChanged(EventObject event) {
		if (cmdStack.getUndoCommand()==lastCommand)
			file.setDirty(false);
		else
			file.setDirty(true);

	}
	
	public void dispose() {
		cmdStack.removeCommandStackListener(this);
		file.eAdapters().remove(this);
	}
	
	@Override
	public void notifyChanged(Notification msg) {
		if (msg.getFeatureID(Boolean.class)==ModelPackage.FILE__DIRTY) {
			if (!file.isDirty())
				lastCommand = cmdStack.getUndoCommand();				
		}
	}

}
