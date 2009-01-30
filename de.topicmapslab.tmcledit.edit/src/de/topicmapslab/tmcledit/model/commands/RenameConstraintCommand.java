/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;


import org.eclipse.emf.common.command.AbstractCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
//TODO reimplement it or delete it
public class RenameConstraintCommand extends AbstractCommand{

//	private final AbstractConstraint constraint;
//	private final String oldName;
//	private final String newName;

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void redo() {
		// TODO Auto-generated method stub
		
	}

	/*
	
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
	*/
}
