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
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveRoleCombinationConstraintCommand extends AbstractCommand {
	private final AssociationType assType;
	private final List<RoleCombinationConstraint> constraintList;

	private List<RoleCombinationConstraint> oldConstraintList;
	
	public RemoveRoleCombinationConstraintCommand(AssociationType assType,
			List<RoleCombinationConstraint> constraintList) {
		super();
		this.assType = assType;
		this.constraintList = constraintList;
	}
	
	public void execute() {
		assType.getRoleCombinations().removeAll(constraintList);
	}
	
	public void redo() {
		assType.getRoleCombinations().removeAll(constraintList);		
	}
	
	@Override
	public void undo() {
		assType.getRoleCombinations().clear();
		assType.getRoleCombinations().addAll(oldConstraintList);
	}
	
	@Override
	protected boolean prepare() {
		oldConstraintList = new ArrayList<RoleCombinationConstraint>(assType.getRoleCombinations());
		return true;
	}
}
