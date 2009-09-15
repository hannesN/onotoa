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

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * @author Hannes Niederhausen
 *
 */
public class SetAssociationTypeCommand extends AbstractCommand {

	private final TopicType newType;
	private final TopicType oldType;
	
	private final AssociationTypeConstraint constraint;
	private List<SetRoleConstraintCommand> cmds = Collections.emptyList();
	
	
	public SetAssociationTypeCommand(AssociationTypeConstraint constraint,
			TopicType newType) {
		super();
		this.constraint = constraint;
		this.newType = newType;
		this.oldType = constraint.getType();
	}

	public void execute() {
		for (AbstractCommand cmd : cmds) {
			if (cmd.canExecute())
				cmd.execute();
		}
		constraint.setType(newType);
	}

	public void redo() {
		for (AbstractCommand cmd : cmds) {
			cmd.redo();
		}
		constraint.setType(newType);
	}
	
	@Override
	public void undo() {
		for (AbstractCommand cmd : cmds) {
			cmd.undo();
		}
		constraint.setType(oldType);
	}

	@Override
	protected boolean prepare() {
		if (newType==null) {
			if (oldType==null)
				return false;
		} else {
			if (newType.equals(oldType))
				return false;
		}
		
		if (constraint.getPlayerConstraints().size()>0)
			cmds = new ArrayList<SetRoleConstraintCommand>();
		
		for (RolePlayerConstraint rpc : constraint.getPlayerConstraints()) {
			cmds.add(new SetRoleConstraintCommand(rpc, null));
		}
		return true;
	}
}
