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
package de.topicmapslab.tmcledit.domaindiagram.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.commands.CommandStack;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.CreateOccurrenceConstraintCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddOccurrenceConstraintAction extends AddConstraintAction {
	public static final String ID = "de.topicmapslab.tmcleditor.addoccurrenceconstraint";
	public AddOccurrenceConstraintAction(CommandStack commandStack) {
		super(commandStack);
		setText("Add Occurrence Constraint");
		setId(ID);
	}
	
	@Override
	protected Command getEmfCommand() {
		return new CreateOccurrenceConstraintCommand(
				getSelectedTopicType(), ModelFactory.eINSTANCE
						.createOccurrenceTypeConstraint());
	}

}
