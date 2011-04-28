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
import org.eclipse.emf.common.command.CompoundCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveRoleConstraintCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final List<RoleConstraint> roles;
	
	private CompoundCommand cmd;
	
	private List<RoleConstraint> oldRoleConstraintList = Collections.emptyList();
	
	/**
	 * 
	 * @param associationType
	 * @param role
	 */
	public RemoveRoleConstraintCommand(AssociationType associationType,
			RoleConstraint role) {
		this(associationType, new ArrayList<RoleConstraint>());
		this.roles.add(role);
	}
	
	/**
	 * 
	 * @param associationType
	 * @param roles
	 */
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
		prepare();
		
		cmd.execute();
		
		associationType.getRoles().removeAll(roles);
	}

	/* (non-Javadoc)
	 * @see org.eclipse.emf.common.command.Command#redo()
	 */
	public void redo() {
		cmd.redo();
		
		associationType.getRoles().removeAll(roles);
	}

	@Override
	public void undo() {
		associationType.getRoles().clear();
		associationType.getRoles().addAll(oldRoleConstraintList);

		cmd.undo();
	}
		
	@Override
	protected boolean prepare() {
		cmd = new CompoundCommand();
		prepareRoleConstraintCommands();	
		prepareRoleCombinationCommands();
		
		oldRoleConstraintList = new ArrayList<RoleConstraint>(associationType.getRoles());
		
		return true;
	}

	private void prepareRoleConstraintCommands() {
		for (AssociationTypeConstraint asc : ModelIndexer.getInstance().getTopicMapSchema().getAssociationTypeConstraints() ) {
			if (associationType.equals(asc.getType())) {
				for (RolePlayerConstraint rpc : asc.getPlayerConstraints()) {
					if (roles.contains(rpc.getRole())) {
						SetRoleConstraintCommand cmd = new SetRoleConstraintCommand(rpc, null);
						this.cmd.appendIfCanExecute(cmd);
					}
				}
			}
		}
		
		
	}
	
	private void prepareRoleCombinationCommands() {
		// get list of role combination constraints which use this code and remove them
		List<RoleCombinationConstraint> rccList = new ArrayList<RoleCombinationConstraint>();
		for (RoleCombinationConstraint rcc : associationType.getRoleCombinations()) {
			if ((isUsedRoleType(rcc.getRole())) || (isUsedRoleType(rcc.getOtherRole()))) {
				rccList.add(rcc);
			}
		}
		
		if (!rccList.isEmpty())
			cmd.appendIfCanExecute(new RemoveRoleCombinationConstraintCommand(associationType, rccList));
	}
	
	
	private boolean isUsedRoleType(TopicType tt) {
		for (RoleConstraint rc : oldRoleConstraintList) {
			if (tt.equals(rc.getType()))
				return true;
		}
		return false;
	}
	
}
