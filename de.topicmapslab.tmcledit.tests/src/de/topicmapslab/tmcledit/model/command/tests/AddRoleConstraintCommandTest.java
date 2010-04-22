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

import java.util.ArrayList;
import java.util.List;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.commands.AddRoleConstraintCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddRoleConstraintCommandTest {

	private AssociationType aType;
	private List<RoleConstraint> rolesList1;
	private List<RoleConstraint> rolesList2;
	private AddRoleConstraintCommand command;
	private RoleConstraint rc;
	private int size;
	private int constructor = 1;

	@Before
	public void prepare() {

		if (rc == null && constructor == 2) {

			rc = ModelFactory.eINSTANCE.createRoleConstraint();
			rc.setId(0);
		}

		aType = ModelFactory.eINSTANCE.createAssociationType();

		if (rolesList1 == null && constructor == 1) {

			rolesList1 = new ArrayList<RoleConstraint>();
			RoleConstraint rc1 = ModelFactory.eINSTANCE.createRoleConstraint();
			rc1.setId(2);
			rolesList1.add(rc1);

		}

		if (command == null && constructor == 1)
			command = new AddRoleConstraintCommand(aType, rolesList1);
		else
			command = new AddRoleConstraintCommand(aType, rc);

	}

	@After
	public void shutdown() {

		aType = null;
		rolesList1 = null;
		command = null;
		rc = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		if (constructor == 1) {
			constructor = 2;
			prepare();
			canExecuteTest();

		}

		constructor = 1;

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		size = aType.getRoles().size();
		rolesList2 = new ArrayList<RoleConstraint>(aType.getRoles());
		rolesList2.add(rc);

		Assert.assertFalse((aType.getRoles().containsAll(rolesList1))
				&& (aType.getRoles().contains(rc)));

		command.execute();

		if (constructor == 1) {

			Assert.assertTrue((aType.getRoles().containsAll(rolesList1)));
			Assert.assertTrue(Tools.roleConstraintListCompare(rolesList1, aType
					.getRoles()));

		}

		if (constructor == 2) {

			Assert.assertTrue((aType.getRoles().contains(rc)));
			Assert.assertTrue(Tools.roleConstraintListCompare(rolesList2, aType
					.getRoles()));

		}

		if (constructor == 1) {
			constructor = 2;
			prepare();
			executeTest();

		}

		constructor = 1;

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

		if (constructor == 1) {
			constructor = 2;
			prepare();
			canUndoTest();

		}

		constructor = 1;

	}

	@Test
	public void undoTest() {

		Assert.assertTrue(command.canExecute());

		size = aType.getRoles().size();
		rolesList2 = new ArrayList<RoleConstraint>(aType.getRoles());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue((size) == aType.getRoles().size());
		Assert.assertFalse(aType.getRoles().containsAll(rolesList1)
				&& aType.getRoles().contains(rc));

		Assert.assertTrue(Tools.roleConstraintListCompare(rolesList2, aType
				.getRoles()));

		if (constructor == 1) {
			constructor = 2;
			prepare();
			undoTest();

		}

		constructor = 1;

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		
		size = aType.getRoles().size();
		rolesList2 = new ArrayList<RoleConstraint>(aType.getRoles());
		
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue((size ) == aType.getRoles().size());
		
		if (constructor == 1) {

			Assert.assertTrue((aType.getRoles().containsAll(rolesList1)));
			Assert.assertTrue(Tools.roleConstraintListCompare(rolesList1, aType
					.getRoles()));

		}

		if (constructor == 2) {

			Assert.assertTrue((aType.getRoles().contains(rc)));
			Assert.assertTrue(Tools.roleConstraintListCompare(rolesList2, aType
					.getRoles()));

		}
		
		if (constructor == 1) {
			constructor = 2;
			prepare();
			redoTest();

		}

		constructor = 1;

		
	}

}
