package de.topicmapslab.tmcledit.model.commands;

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * A generic command to remove an item from a topic type.
 * @author Hannes Niederhausen
 *
 */
public class DeleteTopicTypeConstraintItemCommand extends AbstractCommand {

	private final int featureID;
	private final AbstractConstraint constraint;
	private final TopicType topicType;
	
	@SuppressWarnings("unchecked")
	private List constraintList;
	int index;
	
	/**
	 * 
	 * @param topicType the topic type containing the constraint
	 * @param constraint the constraint to remove
	 * @param featureID the featureID of the container containing the constraint in the topic type
	 */
	public DeleteTopicTypeConstraintItemCommand(TopicType topicType,
			AbstractConstraint constraint, int featureID) {
		super();
		this.topicType = topicType;
		this.constraint = constraint;
		this.featureID = featureID;
	}

	@Override
	public void execute() {
		constraintList.remove(constraint);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void undo() {
		constraintList.add(constraint);
	}

	@Override
	protected boolean prepare() {
		switch (featureID) {
		case ModelPackage.TOPIC_TYPE__NAME_CONTRAINTS:
			constraintList = topicType.getNameContraints();
			break;
		case ModelPackage.TOPIC_TYPE__OCCURENCE_CONSTRAINTS:
			constraintList = topicType.getOccurenceConstraints();
			break;
		case ModelPackage.TOPIC_TYPE__SUBJECT_IDENTIFIER_CONSTRAINTS:
			constraintList = topicType.getSubjectIdentifierConstraints();
			break;
		case ModelPackage.TOPIC_TYPE__SUBJECT_LOCATOR_CONSTRAINT:
			constraintList = topicType.getSubjectLocatorConstraint();
			break;
		}
		
		index = constraintList.indexOf(constraint);
		
		return true;
	}
	
	@Override
	public void redo() {
		execute();
	}

}