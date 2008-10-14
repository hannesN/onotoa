/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetAkoCommand extends AbstractCommand {

	private final List<TopicType> oldList;
	private final List<TopicType> newList;
	private final TopicType topic;

	public SetAkoCommand(List<TopicType> newList, TopicType topic) {
		super();
		this.newList = newList;
		this.oldList = topic.getAko();
		this.topic = topic;
	}

	@Override
	public void execute() {
		topic.getAko().clear();
		topic.getAko().addAll(newList);

	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		topic.getAko().clear();
		topic.getAko().addAll(oldList);

	}

	@Override
	protected boolean prepare() {
		return true;
	}

	@Override
	public String getLabel() {
		return "Set a kind of relationship";
	}
}
