/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;


import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RenameTopicTypeCommand extends AbstractCommand {

	private final TopicType tt;
	private final String oldName;
	private final String newName;

	
	
	public RenameTopicTypeCommand(TopicType tt, String newName) {
		super();
		this.tt = tt;
		this.oldName = tt.getName();
		this.newName = newName;
	}

	@Override
	public void execute() {
		tt.setName(newName);
	}
	
	@Override
	public void undo() {
		tt.setName(oldName);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
