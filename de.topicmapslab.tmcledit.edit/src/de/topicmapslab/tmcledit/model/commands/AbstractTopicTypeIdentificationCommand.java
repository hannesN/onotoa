package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.TopicType;

public abstract class AbstractTopicTypeIdentificationCommand extends
		AbstractCommand {

	protected List<String> newList;
	protected List<String> oldList;
	protected TopicType type;


	public AbstractTopicTypeIdentificationCommand(String label, List<String> newList, TopicType type) {
		super(label);
		this.newList = newList;
		this.type = type;
	}

	protected abstract List<String> getStringList();

	protected final void setList(List<String> list) {
		if (list.size()>0)
			type.eSetDeliver(false);
		getStringList().clear();
		type.eSetDeliver(true);
		getStringList().addAll(list);
	}
	
	@Override
	protected boolean prepare() {
		oldList = new ArrayList<String>();
		oldList.addAll(getStringList());
		return true;
	}
	
	@Override
	public void execute() {
		setList(newList);
	}

	@Override
	public void redo() {
		setList(newList);
	}

	@Override
	public void undo() {
		setList(oldList);
	}

}