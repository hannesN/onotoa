package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

public class DeleteTopicType extends AbstractCommand {

	private final TopicType topicType;
	
	public DeleteTopicType(TopicType topicType) {
		this.topicType = topicType;
	}
	
	@Override
	public void execute() {
		// TODO Auto-generated method stub

	}

	@Override
	public void redo() {
		// TODO Auto-generated method stub

	}

	@Override
	protected boolean prepare() {
		// TODO Auto-generated method stub
		return super.prepare();
	}
	
}
