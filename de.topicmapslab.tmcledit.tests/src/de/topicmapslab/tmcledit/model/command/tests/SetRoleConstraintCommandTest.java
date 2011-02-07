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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.commands.SetRoleConstraintCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetRoleConstraintCommandTest {

	private SetRoleConstraintCommand command;
	private RoleConstraint newConstraint;
	private RoleConstraint oldConstraint;
	private RolePlayerConstraint rolePlayerConstraint;

	@Before
	public void prepare() {

		if (newConstraint == null)
			newConstraint = ModelFactory.eINSTANCE.createRoleConstraint();

		if (oldConstraint == null)
			oldConstraint = ModelFactory.eINSTANCE.createRoleConstraint();

		if (rolePlayerConstraint == null) {

			rolePlayerConstraint = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();
			rolePlayerConstraint.setRole(oldConstraint);

		}

		if (command == null)
			command = new SetRoleConstraintCommand(rolePlayerConstraint,
					newConstraint);
	}

	@After
	public void shutdown() {

		oldConstraint = null;
		newConstraint = null;
		rolePlayerConstraint = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		// old == null, new == null
		rolePlayerConstraint.setRole(null);
		command = new SetRoleConstraintCommand(rolePlayerConstraint, null);
		Assert.assertFalse(command.canExecute());

		// old == null, new != null
		rolePlayerConstraint.setRole(null);
		command = new SetRoleConstraintCommand(rolePlayerConstraint,
				newConstraint);
		Assert.assertTrue(command.canExecute());

		// old == new
		rolePlayerConstraint.setRole(newConstraint);
		command = new SetRoleConstraintCommand(rolePlayerConstraint,
				newConstraint);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(Tools.roleConstraintCompare(newConstraint,
				rolePlayerConstraint.getRole()));

	}

	@Test
	public void canUndo() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		Assert.assertTrue(Tools.roleConstraintCompare(oldConstraint,
				rolePlayerConstraint.getRole()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertTrue(Tools.roleConstraintCompare(newConstraint,
				rolePlayerConstraint.getRole()));

	}

}
