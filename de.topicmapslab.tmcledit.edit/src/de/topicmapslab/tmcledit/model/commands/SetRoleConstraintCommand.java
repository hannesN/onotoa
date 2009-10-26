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

import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetRoleConstraintCommand extends AbstractCommand {

	private final RoleConstraint newConstraint;
	private final RoleConstraint oldConstraint;
	
	private final RolePlayerConstraint rolePlayerConstraint;
	
	public SetRoleConstraintCommand(RolePlayerConstraint rolePlayerConstraint,
			RoleConstraint newConstraint) {
		super();
		this.rolePlayerConstraint = rolePlayerConstraint;
		this.newConstraint = newConstraint;
		this.oldConstraint = rolePlayerConstraint.getRole();
	}

		
	
	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		rolePlayerConstraint.setRole(newConstraint);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		rolePlayerConstraint.setRole(newConstraint);
	}
	
	@Override
	public void undo() {
		rolePlayerConstraint.setRole(oldConstraint);
	}

	@Override
	protected boolean prepare() {
		if (newConstraint == null) {
	        if (oldConstraint == null)
		        return false;
        } else {
	        if (newConstraint.equals(oldConstraint))
		        return false;
        }
		return true;
	}
}
