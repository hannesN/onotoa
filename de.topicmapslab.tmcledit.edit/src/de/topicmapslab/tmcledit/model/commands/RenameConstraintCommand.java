/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;


import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AbstractConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class RenameConstraintCommand extends AbstractCommand{

	private final AbstractConstraint constraint;
	private final String oldName;
	private final String newName;

	
	
	public RenameConstraintCommand(AbstractConstraint constraint, String newName) {
		super();
		this.constraint = constraint;
		this.oldName = constraint.getName();
		this.newName = newName;
	}

	@Override
	public void execute() {
		constraint.setName(newName);
	}
	
	@Override
	public void undo() {
		constraint.setName(oldName);
	}
	
	@Override
	public void redo() {
		execute();
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
