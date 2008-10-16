package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class CreateOccurenceConstraintCommand extends AbstractCommand {

	private final TopicType topicType;
	
	private OccurenceTypeConstraint otc;
	private boolean isNew;
	
	
	public CreateOccurenceConstraintCommand(TopicType topicType, OccurenceTypeConstraint otc) {
		this.topicType = topicType;
		this.otc = otc;
		isNew = false;
	}
	
	@Override
	public void execute() {
		redo();
	}

	@Override
	protected boolean prepare() {
		isNew = !((TopicMapSchema)topicType.eContainer()).getTopicTypes().contains(otc.getType());
		otc.setCardMin("0");
		otc.setCardMin("1");
		otc.setUnique(false);
		
		return true;
	}
	
	@Override
	public void undo() {
		topicType.getOccurenceConstraints().remove(otc);
		if (isNew) {
			TopicMapSchema schema = (TopicMapSchema) topicType.eContainer();
			schema.getTopicTypes().remove(otc.getType());
		}
		
	}
	
	@Override
	public void redo() {
		if (isNew) {
			TopicMapSchema schema = (TopicMapSchema) topicType.eContainer();
			schema.getTopicTypes().add(otc.getType());
		}
		topicType.getOccurenceConstraints().add(otc);
	}
	
	@Override
	public String getLabel() {
		return "Create Occurence Constraint";
	}
}
