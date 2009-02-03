/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.OccurenceType;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetDatatypeCommand extends AbstractCommand {

	private final OccurenceType type;
	private final String newString;
	private final String oldString;
	
	
	
	public SetDatatypeCommand(OccurenceType type, String newString) {
		super();
		this.type = type;
		this.newString = newString;
		this.oldString = type.getDataType();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	@Override
	public void execute() {
		type.setDataType(newString);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	@Override
	public void redo() {
		type.setDataType(newString);
	}
	
	@Override
	public void undo() {
		type.setDataType(oldString);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	};

}
