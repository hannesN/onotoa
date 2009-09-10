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

import de.topicmapslab.tmcledit.model.LabelPos;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetLabelPosCommand extends AbstractCommand {

	private final LabelPos position;
	private final int newPosX;
	private final int newPosY;
	
	private int oldPosX;
	private int oldPosY;
	
	
	
	public SetLabelPosCommand(LabelPos position, int newPosX, int newPosY) {
		super();
		this.position = position;
		this.newPosX = newPosX;
		this.newPosY = newPosY;
	}

	public void execute() {
		position.eSetDeliver(false);
		position.setPosX(newPosX);
		position.eSetDeliver(true);
		position.setPosY(newPosY);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		position.eSetDeliver(false);
		position.setPosX(oldPosX);
		position.eSetDeliver(true);
		position.setPosY(oldPosY);
	}
	@Override
	protected boolean prepare() {
		oldPosX = position.getPosX();
		oldPosY = position.getPosY();
		return true;
	}
}
