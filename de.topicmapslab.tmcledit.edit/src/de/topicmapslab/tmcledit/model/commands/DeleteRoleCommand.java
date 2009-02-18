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
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.util.ModelIndexer;

public class DeleteRoleCommand extends AbstractCommand {

	private final AssociationType associationType;
	private final RoleConstraint RoleConstraint;
	
	private List<DeleteRolePlayerConstraintCommand> deletePlayerCmds = Collections.emptyList();
	
	public DeleteRoleCommand(
			AssociationType associationType,
			RoleConstraint RoleConstraint) {
		super();
		this.associationType = associationType;
		this.RoleConstraint = RoleConstraint;
	}

	@Override
	public void execute() {
		for (DeleteRolePlayerConstraintCommand cmd : deletePlayerCmds)
			if (cmd.canExecute())
				cmd.execute();
		associationType.getRoles().remove(RoleConstraint);
	}

	@Override
	public void redo() {
		for (DeleteRolePlayerConstraintCommand cmd : deletePlayerCmds)
			cmd.redo();
		associationType.getRoles().remove(RoleConstraint);
	}

	@Override
	public void undo() {
		for (DeleteRolePlayerConstraintCommand cmd : deletePlayerCmds)
			if (cmd.canUndo())
				cmd.undo();		
	}
	
	@Override
	protected boolean prepare() {
		
		TopicMapSchema schema = ModelIndexer.getInstance().getTopicMapSchema();
		
		for (AssociationTypeConstraint atc : schema.getAssociationTypeConstraints()) {
			if (atc.getType().equals(associationType)) {
				for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
					if (rpc.getRole().equals(RoleConstraint)) {
						addCommand(atc, rpc);
					}
				}
			}
		}
		return true;
	}

	private void addCommand(AssociationTypeConstraint atc, RolePlayerConstraint rpc) {
		if (deletePlayerCmds==Collections.EMPTY_LIST)
			deletePlayerCmds = new ArrayList<DeleteRolePlayerConstraintCommand>();
		
		deletePlayerCmds.add(new DeleteRolePlayerConstraintCommand(atc, rpc));
	}
}
