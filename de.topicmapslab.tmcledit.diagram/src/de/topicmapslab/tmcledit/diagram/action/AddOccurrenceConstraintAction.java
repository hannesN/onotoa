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
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.commands.CreateOccurrenceConstraintCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * Action to create a new occurrence contraint
 * 
 * @author Hannes Niederhausen
 * 
 */
public class AddOccurrenceConstraintAction extends AddConstraintAction {
	/**
	 * ID
	 */
	public static final String ID = "de.topicmapslab.tmcleditor.addoccurrenceconstraint";
	private boolean createType = false;
	
	/**
	 * Constructor
	 * 
	 * @param commandStack the {@link CommandStack} used to execute model changes
	 * @param createType flag whether a occurrence type should be created
	 */
	public AddOccurrenceConstraintAction(CommandStack commandStack, boolean createType) {
		this(commandStack);
		this.createType = createType;
	}
	
	/**
	 * Constructor
	 * 
	 * @param commandStack the {@link CommandStack} used to execute model changes
	 */
	public AddOccurrenceConstraintAction(CommandStack commandStack) {
		super(commandStack);
		setText("Add Occurrence Constraint");
		setId(ID);
	}

	@Override
	protected Command getEmfCommand() {
		OccurrenceTypeConstraint otc = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();
		if (createType) {
			String newName = "occurrence";
			int i = 0;
			while (ModelIndexer.getTopicIndexer().getTopicTypeByName(newName + i) != null) {
				i++;
			}
			OccurrenceType ot = ModelFactory.eINSTANCE.createOccurrenceType();
			ot.setName(newName + i);
			otc.setType(ot);
		}
		return new CreateOccurrenceConstraintCommand(getSelectedTopicType(), otc);
	}

}
