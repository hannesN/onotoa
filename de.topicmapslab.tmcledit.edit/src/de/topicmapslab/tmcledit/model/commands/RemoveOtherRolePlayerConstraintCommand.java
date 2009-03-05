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

import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveOtherRolePlayerConstraintCommand extends AbstractCommand {
	private final RoleType roleType;
	private final List<OtherRolePlayerConstraint> constraintList;
	
	public RemoveOtherRolePlayerConstraintCommand(RoleType roleType,
			List<OtherRolePlayerConstraint> constraintList) {
		super();
		this.roleType = roleType;
		this.constraintList = constraintList;
	}
	
	public void execute() {
		roleType.getOtherRoles().removeAll(constraintList);
	}
	
	public void redo() {
		roleType.getOtherRoles().removeAll(constraintList);		
	}
	
	@Override
	public void undo() {
		roleType.getOtherRoles().addAll(constraintList);
	}
	
	@Override
	protected boolean prepare() {
		return true;
	}
}
