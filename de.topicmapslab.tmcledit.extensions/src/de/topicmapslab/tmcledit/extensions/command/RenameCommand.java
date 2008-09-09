/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.command;


import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RenameCommand extends AbstractCommand {

	private final TopicType tt;
	private final String oldName;
	private final String newName;

	
	
	public RenameCommand(TopicType tt, String newName) {
		super();
		this.tt = tt;
		this.oldName = tt.getId();
		this.newName = newName;
	}

	@Override
	public void execute() {
		tt.setId(newName);
	}
	
	@Override
	public void undo() {
		tt.setId(oldName);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public boolean canUndo() {
		return true;
	}

	@Override
	public boolean canExecute() {
		return true;
	}
}
