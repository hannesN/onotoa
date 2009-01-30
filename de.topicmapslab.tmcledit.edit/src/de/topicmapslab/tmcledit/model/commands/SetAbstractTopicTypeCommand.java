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
public class SetAbstractTopicTypeCommand extends AbstractCommand {

	private final TopicType tt;
	private final boolean isAbstract;
	
	
	public SetAbstractTopicTypeCommand(TopicType topicType, boolean isAbstract) {
		super();
		this.isAbstract = isAbstract;
		tt = topicType;
	}

	@Override
	public void execute() {
		tt.setAbstract(isAbstract);
	}
	
	@Override
	public void undo() {
		tt.setAbstract(!isAbstract);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Update Set Abstract";
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
