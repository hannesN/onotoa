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
package de.topicmapslab.tmcledit.diagram.action;

import org.eclipse.emf.common.command.Command;
import org.eclipse.gef.commands.CommandStack;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.CreateNameTypeConstraintCommand;

/**
 * @author Hannes Niederhausen
 *
 */
public class AddNameConstraintAction extends AddConstraintAction {
	public static final String ID = "de.topicmapslab.tmcleditor.addnameconstraint";
	
	public AddNameConstraintAction(CommandStack commandStack) {
		super(commandStack);
		setText("Add Name Constraint");
		setId(ID);
	}
	
	@Override
	protected Command getEmfCommand() {
		return new CreateNameTypeConstraintCommand(
				getSelectedTopicType(), ModelFactory.eINSTANCE
						.createNameTypeConstraint());
	}

}
