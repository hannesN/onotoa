package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * 
 * @author Hannes Niederhausen
 *
 */
public class CreateOccurenceConstraintCommand extends AbstractCommand {

	private final TopicType topicType;
	
	private OccurenceTypeConstraint otc;
	
	
	public CreateOccurenceConstraintCommand(TopicType topicType, OccurenceTypeConstraint otc) {
		this.topicType = topicType;
		this.otc = otc;
	}
	
	@Override
	public void execute() {
		redo();
	}

	@Override
	protected boolean prepare() {
		otc.setCardMin("0");
		otc.setCardMin("1");
		otc.setUnique(false);
		
		return true;
	}
	
	@Override
	public void undo() {
		topicType.getOccurenceConstraints().remove(otc);		
	}
	
	@Override
	public void redo() {
		topicType.getOccurenceConstraints().add(otc);
	}
	
	@Override
	public String getLabel() {
		return "Create Occurence Constraint";
	}
}
