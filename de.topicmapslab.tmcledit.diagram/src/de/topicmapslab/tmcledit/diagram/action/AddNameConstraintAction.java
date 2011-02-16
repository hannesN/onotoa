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
import org.eclipse.emf.common.command.CommandStack;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameType;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.commands.CreateNameTypeConstraintCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Action to create a new name contraint
 * 
 * @author Hannes Niederhausen
 *
 */
public class AddNameConstraintAction extends AddConstraintAction {
	/**
	 * ID of the action
	 */
	public static final String ID = "de.topicmapslab.tmcleditor.addnameconstraint";
	
	private boolean createType = false;
	
	/**
	 * Constructor
	 * 
	 * @param commandStack the commandstack used to execute model changes
	 */
	public AddNameConstraintAction(CommandStack commandStack) {
		super(commandStack);
		setText("Add Name Constraint");
		setId(ID);
	}
	
	/**
	 * Constructor
	 * 
	 * @param commandStack the commandstack used to execute model changes
	 * @param createType flag whether a name type should be created
	 */
	public AddNameConstraintAction(CommandStack commandStack, boolean createType) {
		this(commandStack);
		this.createType = createType;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	protected Command getEmfCommand() {
		NameTypeConstraint nameTypeConstraint = ModelFactory.eINSTANCE
				.createNameTypeConstraint();
		if (createType) {
			String newName = "name";
			int i = 0;
			while (ModelIndexer.getTopicIndexer().getTopicTypeByName(newName+i)!=null) {
				i++;
			}
			NameType nt = ModelFactory.eINSTANCE.createNameType();
			nt.setName(newName+i);
			nameTypeConstraint.setType(nt);
		}
		return new CreateNameTypeConstraintCommand(
				getSelectedTopicType(), nameTypeConstraint);
	}

}
