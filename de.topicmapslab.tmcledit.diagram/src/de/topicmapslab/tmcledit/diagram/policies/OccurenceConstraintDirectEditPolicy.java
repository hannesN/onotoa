/**
 * 
 */
package de.topicmapslab.tmcledit.diagram.policies;

import org.eclipse.emf.common.command.Command;

import de.topicmapslab.tmcledit.model.OccurenceTypeConstraint;
import de.topicmapslab.tmcledit.model.commands.RenameConstraintCommand;



/**
 * @author Hannes Niederhausen
 *
 */
public class OccurenceConstraintDirectEditPolicy extends AbstractDirectEditPolicy {

	@Override
	public Command getRenameCommand(Object model, String name) {
		if (model instanceof OccurenceTypeConstraint)
			return new RenameConstraintCommand((OccurenceTypeConstraint) model, name);
		return null;
	}

}
