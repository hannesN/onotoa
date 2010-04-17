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
package de.topicmapslab.tmcledit.model.command.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.eclipse.emf.common.command.AbstractCommand;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.commands.AddBendpointCommand;
import de.topicmapslab.tmcledit.model.commands.AddRoleCombinationConstraintCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddRoleCombinationConstraintCommandTest {

	private AddRoleCombinationConstraintCommand command;
	private AssociationType aType;
	private RoleCombinationConstraint rcc;
	private int size;

	@Before
	public void prepare() {

		if (aType == null)
			aType = ModelFactory.eINSTANCE.createAssociationType();

		if (rcc == null)
			rcc = ModelFactory.eINSTANCE.createRoleCombinationConstraint();

		if (command == null)
			command = new AddRoleCombinationConstraintCommand(aType, rcc);

	}

	@After
	public void shutdown() {

		command = null;
		rcc = null;
		aType = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		size = aType.getRoleCombinations().size();
		Assert.assertFalse(aType.getRoleCombinations().contains(rcc));
		command.execute();
		Assert.assertTrue((size + 1) == aType.getRoleCombinations().size());
		Assert.assertTrue(aType.getRoleCombinations().contains(rcc));

	}

	@Test
	public void undoTest() {
		
		command.execute();
		
		size = aType.getRoleCombinations().size();
		Assert.assertTrue(aType.getRoleCombinations().contains(rcc));
		command.undo();
		Assert.assertTrue((size - 1) == aType.getRoleCombinations().size());
		Assert.assertFalse(aType.getRoleCombinations().contains(rcc));

	}
	
	@Test
	public void redoTest() {
		
		command.execute();
		command.undo();
		
		size = aType.getRoleCombinations().size();
		Assert.assertFalse(aType.getRoleCombinations().contains(rcc));
		command.redo();
		Assert.assertTrue((size + 1) == aType.getRoleCombinations().size());
		Assert.assertTrue(aType.getRoleCombinations().contains(rcc));

	}

	
	// private final AssociationType associationType;
	// private final RoleCombinationConstraint rcc;
	// private int index;
	//
	// public AddRoleCombinationConstraintCommandTest(AssociationType
	// associationType,
	// RoleCombinationConstraint rcc) {
	// super();
	// this.associationType = associationType;
	// this.rcc = rcc;
	// }
	//
	// public void execute() {
	// associationType.getRoleCombinations().add(rcc);
	// }
	//
	// public void redo() {
	// associationType.getRoleCombinations().add(index, rcc);
	// }
	//
	// @Override
	// public void undo() {
	// index = associationType.getRoleCombinations().indexOf(rcc);
	// associationType.getRoleCombinations().remove(rcc);
	// }
	//
	// @Override
	// protected boolean prepare() {
	// return true;
	// }

}
