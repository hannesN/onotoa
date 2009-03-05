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

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.RoleConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddRoleConstraintCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final List<RoleConstraint> roles;
	
	public AddRoleConstraintCommand(AssociationType associationType,
			RoleConstraint role) {
		this(associationType, new ArrayList<RoleConstraint>());
		this.roles.add(role);
	}
	
	public AddRoleConstraintCommand(AssociationType associationType,
			List<RoleConstraint> roles) {
		super("Add Scope Constraints");
		this.associationType = associationType;
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		associationType.getRoles().addAll(roles);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		associationType.getRoles().removeAll(roles);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
