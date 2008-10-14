package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

public class RemovePrefixMappingCommand extends AbstractCommand{

	private final MappingElement me;
	private final TopicMapSchema schema;
	
	
	
	public RemovePrefixMappingCommand(TopicMapSchema schema, MappingElement me) {
		super();
		this.schema = schema;
		this.me = me;
	}

	@Override
	protected boolean prepare()
	{
		return true;
	}
	
	@Override
	public void execute() {
		schema.getMappings().remove(me);
	}
	
	@Override
	public void undo() {
		schema.getMappings().add(me);
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Remove Prefix Mapping";
	}
	
}
