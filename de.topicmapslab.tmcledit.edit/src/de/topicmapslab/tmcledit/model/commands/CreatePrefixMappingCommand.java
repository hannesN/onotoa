package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

public class CreatePrefixMappingCommand extends AbstractCommand{

	private final String newKey;
	private final String newValue;
	private final TopicMapSchema schema;
	
	private MappingElement newElement;
	
	public CreatePrefixMappingCommand(TopicMapSchema schema, String newKey,
			String newValue) {
		super();
		this.schema = schema;
		this.newKey = newKey;
		this.newValue = newValue;
	}
	
	@Override
	protected boolean prepare() {
		newElement = ModelFactory.eINSTANCE.createMappingElement();
		newElement.setKey(newKey);
		newElement.setValue(newValue);
		
		return true;
	}
	
	@Override
	public void execute() {
		schema.getMappings().add(newElement);
	}
	
	@Override
	public void undo() {
		schema.getMappings().remove(newElement);
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public String getLabel() {
		return "Create new Prefix Mapping";
	}
	
}
