/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * 
 */
package de.topicmapslab.tmcledit.model.commands;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.OccurrenceType;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetDatatypeCommand extends AbstractCommand {

	private final OccurrenceType type;
	private final String newString;
	private final String oldString;
	
	
	
	public SetDatatypeCommand(OccurrenceType type, String newString) {
		super();
		this.type = type;
		this.newString = newString;
		this.oldString = type.getDataType();
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		type.setDataType(newString);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
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
