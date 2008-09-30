/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.command;


import org.eclipse.gef.commands.Command;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RenameCommand extends Command {

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
	
}
