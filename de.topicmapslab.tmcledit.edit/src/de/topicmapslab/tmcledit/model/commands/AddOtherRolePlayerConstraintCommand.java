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

import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddOtherRolePlayerConstraintCommand extends AbstractCommand {
	private final RoleType roleType;
	private final OtherRolePlayerConstraint orpc;

	public AddOtherRolePlayerConstraintCommand(RoleType roleType,
			OtherRolePlayerConstraint orpc) {
		super();
		this.roleType = roleType;
		this.orpc = orpc;
	}

	public void execute() {
		roleType.getOtherRoles().add(orpc);
	}

	public void redo() {
		roleType.getOtherRoles().add(orpc);
	}

	@Override
	public void undo() {
		roleType.getOtherRoles().remove(orpc);
	}

	@Override
	protected boolean prepare() {
		return true;
	}

}
