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
import de.topicmapslab.tmcledit.model.commands.RemoveRoleCombinationConstraintCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveRoleCombinationConstraintCommandTest {

	private RemoveRoleCombinationConstraintCommand command;
	private AssociationType associationTypeConstraint;
	private RoleCombinationConstraint rcc0;
	private RoleCombinationConstraint rcc1;
	private RoleCombinationConstraint rcc2;
	private List<RoleCombinationConstraint> constraintList;
	private List<RoleCombinationConstraint> list;
	private int size;

	@Before
	public void prepare() {

		if (rcc0 == null)
			rcc0 = ModelFactory.eINSTANCE.createRoleCombinationConstraint();

		if (rcc1 == null)
			rcc1 = ModelFactory.eINSTANCE.createRoleCombinationConstraint();

		if (rcc2 == null)
			rcc2 = ModelFactory.eINSTANCE.createRoleCombinationConstraint();

		if (constraintList == null)
			constraintList = new ArrayList<RoleCombinationConstraint>();

		if (associationTypeConstraint == null) {

			associationTypeConstraint = ModelFactory.eINSTANCE
					.createAssociationType();
			associationTypeConstraint.getRoleCombinations().add(rcc0);
			associationTypeConstraint.getRoleCombinations().add(rcc1);
			associationTypeConstraint.getRoleCombinations().add(rcc2);

		}

	}

	@After
	public void shutdown() {

		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestOneConstraint() {

		constraintList.add(rcc0);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestTwoConstraint() {

		constraintList.add(rcc0);
		constraintList.add(rcc1);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestOneConstraint() {

		constraintList.add(rcc0);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());

		size = associationTypeConstraint.getRoleCombinations().size();
		list = new ArrayList<RoleCombinationConstraint>(
				associationTypeConstraint.getRoleCombinations());
		list.remove(rcc0);

		command.execute();

		Assert.assertTrue((size - 1) == associationTypeConstraint
				.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				associationTypeConstraint.getRoleCombinations()));

	}

	@Test
	public void executeTestTwoConstraints() {

		constraintList.add(rcc0);
		constraintList.add(rcc1);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());

		size = associationTypeConstraint.getRoleCombinations().size();
		list = new ArrayList<RoleCombinationConstraint>(
				associationTypeConstraint.getRoleCombinations());
		list.remove(rcc0);
		list.remove(rcc1);

		command.execute();

		Assert.assertTrue((size - 2) == associationTypeConstraint
				.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				associationTypeConstraint.getRoleCombinations()));

	}

	@Test
	public void canUndoTestOneConstraint() {

		constraintList.add(rcc0);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestTwoConstraints() {

		constraintList.add(rcc0);
		constraintList.add(rcc1);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestOneConstraint() {

		constraintList.add(rcc0);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());

		size = associationTypeConstraint.getRoleCombinations().size();
		list = new ArrayList<RoleCombinationConstraint>(
				associationTypeConstraint.getRoleCombinations());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == associationTypeConstraint
				.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				associationTypeConstraint.getRoleCombinations()));

	}

	@Test
	public void undoTestTwoConstraints() {

		constraintList.add(rcc0);
		constraintList.add(rcc1);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());

		size = associationTypeConstraint.getRoleCombinations().size();
		list = new ArrayList<RoleCombinationConstraint>(
				associationTypeConstraint.getRoleCombinations());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == associationTypeConstraint
				.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				associationTypeConstraint.getRoleCombinations()));

	}

	@Test
	public void redoTestOneConstraint() {

		constraintList.add(rcc0);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());
		command.execute();

		size = associationTypeConstraint.getRoleCombinations().size();
		list = new ArrayList<RoleCombinationConstraint>(
				associationTypeConstraint.getRoleCombinations());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == associationTypeConstraint
				.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				associationTypeConstraint.getRoleCombinations()));

	}

	@Test
	public void redoTestTwoConstraints() {

		constraintList.add(rcc0);
		constraintList.add(rcc1);
		command = new RemoveRoleCombinationConstraintCommand(
				associationTypeConstraint, constraintList);

		Assert.assertTrue(command.canExecute());
		command.execute();

		size = associationTypeConstraint.getRoleCombinations().size();
		list = new ArrayList<RoleCombinationConstraint>(
				associationTypeConstraint.getRoleCombinations());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == associationTypeConstraint
				.getRoleCombinations().size());
		Assert.assertTrue(Tools.roleCombinationConstraintListCompare(list,
				associationTypeConstraint.getRoleCombinations()));

	}

}
