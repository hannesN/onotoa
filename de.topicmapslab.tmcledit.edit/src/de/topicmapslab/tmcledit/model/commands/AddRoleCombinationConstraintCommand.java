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

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddRoleCombinationConstraintCommand extends AbstractCommand {
	private final AssociationType associationType;
	private final RoleCombinationConstraint rcc;
	private int index;

	public AddRoleCombinationConstraintCommand(AssociationType associationType,
			RoleCombinationConstraint rcc) {
		super();
		this.associationType = associationType;
		this.rcc = rcc;
	}

	public void execute() {
		associationType.getRoleCombinations().add(rcc);
	}

	public void redo() {
		associationType.getRoleCombinations().add(index, rcc);
	}

	@Override
	public void undo() {
		index = associationType.getRoleCombinations().indexOf(rcc); 
		associationType.getRoleCombinations().remove(rcc);
	}

	@Override
	protected boolean prepare() {
		return true;
	}

}
