/**
 * 
 */
package de.topicmapslab.tmcledit.extensions.command;

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetIsACommand extends AbstractCommand {

	private final List<TopicType> oldList;
	private final List<TopicType> newList;
	private final TopicType topic;

	public SetIsACommand(List<TopicType> newList, TopicType topic) {
		super();
		this.newList = newList;
		this.oldList = topic.getIsa();
		this.topic = topic;
	}

	@Override
	public void execute() {
		topic.getIsa().clear();
		topic.getIsa().addAll(newList);

	}

	@Override
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		topic.getIsa().clear();
		topic.getIsa().addAll(oldList);

	}

	@Override
	public boolean canExecute() {
		return true;
	}

	@Override
	public boolean canUndo() {
		return true;
	}

}
