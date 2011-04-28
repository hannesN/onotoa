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
package de.topicmapslab.tmcledit.model.commands;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.CompoundCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Command to delete a role.
 *  
 * @author Hannes Niederhausen
 *
 */
public class DeleteRoleCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final RoleConstraint roleConstraint;
	private final boolean cascade;
	
	private CompoundCommand otherCmds;
	private int idx;
	
	/**
	 * Constructor
	 * @param associationType
	 * @param roleConstraint
	 * @param cascade
	 */
	public DeleteRoleCommand(
			AssociationType associationType,
			RoleConstraint roleConstraint,
			boolean cascade) {
		super();
		this.associationType = associationType;
		this.roleConstraint = roleConstraint;
		this.cascade = cascade;
		otherCmds = new CompoundCommand();
	}
	
	/**
	 * Constructor
	 * @param associationType
	 * @param roleConstraint
	 */
	public DeleteRoleCommand(
			AssociationType associationType,
			RoleConstraint roleConstraint) {
		this(associationType, roleConstraint, true);
	}

	public void execute() {
		otherCmds.execute();
		associationType.getRoles().remove(roleConstraint);
	}

	public void redo() {
		otherCmds.redo();
		associationType.getRoles().remove(roleConstraint);
	}

	@Override
	public void undo() {
		associationType.getRoles().add(idx, roleConstraint);
		otherCmds.undo();
	}
	
	@Override
	protected boolean prepare() {
		idx = associationType.getRoles().indexOf(roleConstraint);
		
		if (!cascade)
			return true;
		
		TopicMapSchema schema = ModelIndexer.getInstance().getTopicMapSchema();
		
		
		for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
			if (atc.getType().equals(associationType)) {
				for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
					if (rpc.getRole().equals(roleConstraint)) {
						otherCmds.appendIfCanExecute(new DeleteRolePlayerConstraintCommand(rpc));
					}
				}
			}
		}
		
		// get list of role combination constraints which use this code and remove them
		List<RoleCombinationConstraint> rccList = new ArrayList<RoleCombinationConstraint>();
		for (RoleCombinationConstraint rcc : associationType.getRoleCombinations()) {
			if ((rcc.getRole().equals(roleConstraint.getType()))
			        || ((rcc.getOtherRole().equals(roleConstraint.getType())))) {
				rccList.add(rcc);
			}
		}
		
		if (!rccList.isEmpty())
			otherCmds.appendIfCanExecute(new RemoveRoleCombinationConstraintCommand(associationType, rccList));
		
		return true;
	}
}
