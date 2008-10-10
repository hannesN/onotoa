package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurenceType;
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
	private OccurenceType ot;
	
	public CreateOccurenceConstraintCommand(TopicType topicType) {
		this.topicType = topicType;
	}
	
	@Override
	public void execute() {
		redo();
	}

	@Override
	protected boolean prepare() {
		otc = ModelFactory.eINSTANCE.createOccurenceTypeConstraint();
		otc.setType(ot);
		otc.setCardMin("0");
		otc.setCardMin("1");
		otc.setUnique(false);
		otc.setName("<..>");
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
}
