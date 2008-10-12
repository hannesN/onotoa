package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateTopicTypeCommand extends AbstractCommand {

	private TopicMapSchema schema; 
	private String name;
	private TopicType topicType;
	
	public CreateTopicTypeCommand(TopicMapSchema schema, String name) {
		this.schema = schema;
		this.name = name;
	}
	
	@Override
	protected boolean prepare() {
		topicType = ModelFactory.eINSTANCE.createTopicType();
		topicType.setId(name);
		return true;
	}
	
	@Override
	public void execute() {
		schema.getTopicTypes().add(topicType);
	}
	
	@Override
	public boolean canUndo() {
		return super.canUndo();
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		schema.getTopicTypes().remove(topicType);
	}
}