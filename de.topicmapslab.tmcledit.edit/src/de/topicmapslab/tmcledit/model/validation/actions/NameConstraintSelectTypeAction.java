/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;


/**
 * @author Hannes Niederhausen
 *
 */
public class NameConstraintSelectTypeAction extends SelectTypeAction {

	
	
	public NameConstraintSelectTypeAction() {
		super(KindOfTopicType.NAME_TYPE);
		setText("Select Type");
	}
		
	@Override
	protected AbstractCommand getCommand(TopicType topicType) {
		return new GenericSetCommand(getModelObject(),
				ModelPackage.NAME_TYPE_CONSTRAINT__TYPE,
				topicType);
	}
}
