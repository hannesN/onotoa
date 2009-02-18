/**
 * 
 */
package de.topicmapslab.tmcledit.model.validation.actions;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.GenericSetCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class DeactivateFlagAction extends ValidationAction {

	private int featureId;
	private TopicMapSchema schema;
	private Boolean value;
	

	public DeactivateFlagAction(CommandStack cmdStack, TopicMapSchema schema, int featureId, boolean value) {
		super(cmdStack);
		this.schema = schema;
		this.featureId = featureId;
		this.value = value;
		
		setText("Change "+schema.eClass().getEStructuralFeature(featureId).getName());
	}


	

	@Override
	public void run() {
		
		
		AbstractCommand cmd = new GenericSetCommand(schema, featureId, value);
		getCommandStack().execute(cmd);
	}

	
}
