package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

public class CreateSubjectIdentifierConstraintCommand extends AbstractCommand {


	private final TopicType topicType;
	private final SubjectIdentifierConstraint subjectIdentifierConstraint;
	
	

	public CreateSubjectIdentifierConstraintCommand(TopicType topicType,
			SubjectIdentifierConstraint subjectIdentifierConstraint) {
		super("Create Subject Locator");
		this.topicType = topicType;
		this.subjectIdentifierConstraint = subjectIdentifierConstraint;
	}
	
	@Override
	public void execute() {
		topicType.getSubjectIdentifierConstraints().add(subjectIdentifierConstraint);
	}

	@Override
	public void redo() {
		execute();
	}
	
	@Override
	public void undo() {
		topicType.getSubjectIdentifierConstraints().remove(subjectIdentifierConstraint);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}

	

}
