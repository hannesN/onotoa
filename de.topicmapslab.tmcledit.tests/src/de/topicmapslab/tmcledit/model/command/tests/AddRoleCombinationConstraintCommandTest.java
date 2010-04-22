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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.commands.AddRoleCombinationConstraintCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddRoleCombinationConstraintCommandTest {

	private AddRoleCombinationConstraintCommand command;
	private AssociationType aType;
	private RoleCombinationConstraint rcc;
	private List<RoleCombinationConstraint> list;
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

		Assert.assertTrue(command.canExecute());
		Assert.assertFalse(aType.getRoleCombinations().contains(rcc));

		list = new ArrayList<RoleCombinationConstraint>(aType
				.getRoleCombinations());
		list.add(rcc);
		size = aType.getRoleCombinations().size();

		command.execute();

		Assert.assertTrue((size + 1) == aType.getRoleCombinations().size());
		Assert.assertTrue(aType.getRoleCombinations().contains(rcc));
		Assert.assertTrue(Tools.roleCombinationConstraintCompare(rcc, aType
				.getRoleCombinations().get(size)));
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				aType.getRoleCombinations()));

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		Assert.assertTrue(command.canExecute());

		list = new ArrayList<RoleCombinationConstraint>(aType
				.getRoleCombinations());
		size = aType.getRoleCombinations().size();

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertFalse(aType.getRoleCombinations().contains(rcc));
		Assert.assertTrue((size) == aType.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				aType.getRoleCombinations()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		list = new ArrayList<RoleCombinationConstraint>(aType
				.getRoleCombinations());
		size = aType.getRoleCombinations().size();

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == aType.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintCompare(rcc, aType
				.getRoleCombinations().get(size - 1)));
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				aType.getRoleCombinations()));

	}

}
