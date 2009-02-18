/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetExclusiveCommand extends AbstractCommand {

	private TopicType topicType;

	private List<TopicType> removeList;
	private List<TopicType> addList;

	public SetExclusiveCommand(List<TopicType> newList, TopicType topic) {
		super("Set exclusive types");
		this.topicType = topic;
		this.removeList = new ArrayList<TopicType>();
		this.addList = new ArrayList<TopicType>(newList);
	}

	@Override
	public void execute() {

		for (TopicType tt : removeList) {
			tt.getExclusive().remove(topicType);
		}
		for (TopicType tt : addList) {
			tt.getExclusive().add(topicType);
		}

		topicType.eSetDeliver(false);
		topicType.getExclusive().removeAll(removeList);
		topicType.eSetDeliver(true);
		topicType.getExclusive().addAll(addList);
	}

	@Override
	protected boolean prepare() {
		removeList = new ArrayList<TopicType>();
		for (TopicType tt : topicType.getExclusive()) {
			if (addList.contains(tt)) {
				addList.remove(tt);
			} else {
				removeList.add(tt);
			}
		}

		return true;
	}

	@Override
	public void undo() {
		for (TopicType tt : removeList) {
			tt.getExclusive().add(topicType);
		}
		for (TopicType tt : addList) {
			tt.getExclusive().remove(topicType);
		}

		topicType.eSetDeliver(false);
		topicType.getExclusive().addAll(removeList);
		topicType.eSetDeliver(true);
		topicType.getExclusive().removeAll(addList);
	}

	@Override
	public void redo() {
		execute();
	}
}
