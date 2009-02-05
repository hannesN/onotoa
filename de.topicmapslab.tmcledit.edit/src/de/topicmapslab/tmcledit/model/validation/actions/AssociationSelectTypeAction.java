/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;


/**
 * @author Hannes Niederhausen
 *
 */
public class AssociationSelectTypeAction extends SelectTypeAction {

	
	
	public AssociationSelectTypeAction(CommandStack cmdStack) {
		super(cmdStack, KindOfTopicType.ASSOCIATION_TYPE);
		setText("Select Type");
	}
		
	@Override
	protected AbstractCommand getCommand(TopicType topicType) {
		return new GenericSetCommand(getModelObject(),
				ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE,
				topicType);
	}
}
