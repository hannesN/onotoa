/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;

import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceConstraintCreateTypeAction extends CreateTypeAction {

	public OccurenceConstraintCreateTypeAction() {
		super(KindOfTopicType.OCCURENCE_TYPE);
	}
	
	@Override
	protected AbstractCommand getCommand(TopicType topicType) {
		CompoundCommand cmd = new CompoundCommand();
		cmd.append(new CreateTopicTypeCommand(ModelIndexer.getInstance()
				.getTopicMapSchema(), topicType));
		cmd.append(new GenericSetCommand(getModelObject(),
				ModelPackage.OCCURENCE_TYPE_CONSTRAINT__TYPE,
				topicType));
		return cmd;
			
	}
}
