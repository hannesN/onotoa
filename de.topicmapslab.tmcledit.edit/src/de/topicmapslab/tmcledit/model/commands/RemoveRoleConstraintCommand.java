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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveRoleConstraintCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final List<RoleConstraint> roles;
	
	private List<SetRoleConstraintCommand> roleConstraintList = Collections.emptyList();
	
	public RemoveRoleConstraintCommand(AssociationType associationType,
			RoleConstraint role) {
		this(associationType, new ArrayList<RoleConstraint>());
		this.roles.add(role);
	}
	
	public RemoveRoleConstraintCommand(AssociationType associationType,
			List<RoleConstraint> roles) {
		super("Add Scope Constraints");
		this.associationType = associationType;
		this.roles = roles;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#execute()
	 */
	public void execute() {
		for (SetRoleConstraintCommand cmd : roleConstraintList) {
			cmd.execute();
		}
		associationType.getRoles().removeAll(roles);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		execute();
	}

	@Override
	public void undo() {
		associationType.getRoles().addAll(roles);
		for (SetRoleConstraintCommand cmd : roleConstraintList) {
			cmd.undo();
		}
	}
	
	private void addRoleConstraintCommand(SetRoleConstraintCommand cmd) {
		if (roleConstraintList==Collections.EMPTY_LIST)
			roleConstraintList = new ArrayList<SetRoleConstraintCommand>();
		
		roleConstraintList.add(cmd);
	}
	
	@Override
	protected boolean prepare() {
		
		prepareRoleConstraintCommands();	
		
		return true;
	}

	private void prepareRoleConstraintCommands() {
		for (AssociationTypeConstraint asc : ModelIndexer.getInstance().getTopicMapSchema().getAssociationTypeConstraints() ) {
			if (associationType.equals(asc.getType())) {
				for (RolePlayerConstraint rpc : asc.getPlayerConstraints()) {
					if (roles.contains(rpc.getRole())) {
						SetRoleConstraintCommand cmd = new SetRoleConstraintCommand(rpc, null);
						if (cmd.canExecute())
							addRoleConstraintCommand(cmd);
					}
				}
			}
		}
		
	}
}
