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

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;

/**
 * @author Hannes Niederhausen
 *
 */
public class RemoveRoleCombinationConstraintCommand extends AbstractCommand {
	private final AssociationType asssType;
	private final List<RoleCombinationConstraint> constraintList;
	int index = -1;
	
	public RemoveRoleCombinationConstraintCommand(AssociationType assType,
			List<RoleCombinationConstraint> constraintList) {
		super();
		this.asssType = assType;
		this.constraintList = constraintList;
	}
	
	public void execute() {
		asssType.getRoleCombinations().removeAll(constraintList);
	}
	
	public void redo() {
		asssType.getRoleCombinations().removeAll(constraintList);		
	}
	
	@Override
	public void undo() {
		if (index!=-1)
			asssType.getRoleCombinations().add(index, constraintList.get(0));
		else
			asssType.getRoleCombinations().addAll(constraintList);
	}
	
	@Override
	protected boolean prepare() {
		if (constraintList.size()==1)
			index = asssType.getRoleCombinations().indexOf(constraintList.get(0));
		return true;
	}
}
