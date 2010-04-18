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
	private List<RoleConstraint> rolesList;
	private AddRoleConstraintCommand command;
	private RoleConstraint rc;
	private int size;

	@Before
	public void prepare() {

		if (aType == null)
			aType = ModelFactory.eINSTANCE.createAssociationType();

		if (rolesList == null) {

			rolesList = new ArrayList<RoleConstraint>();
			RoleConstraint rc1 = ModelFactory.eINSTANCE.createRoleConstraint();
			RoleConstraint rc2 = ModelFactory.eINSTANCE.createRoleConstraint();
			rolesList.add(rc1);
			rolesList.add(rc2);

		}

		if (command == null)
			command = new AddRoleConstraintCommand(aType, rolesList);

		if (rc == null)
			rc = ModelFactory.eINSTANCE.createRoleConstraint();

	}

	@After
	public void shutdown() {

		aType = null;
		rolesList = null;
		command = null;
		rc = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		size = aType.getRoles().size();
		Assert.assertFalse(aType.getRoles().containsAll(rolesList));
		command.execute();
		Assert.assertTrue((size + 2) == aType.getRoles().size());
		Assert.assertTrue(aType.getRoles().containsAll(rolesList));

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		command.execute();

		size = aType.getRoles().size();
		Assert.assertTrue(aType.getRoles().containsAll(rolesList));
		command.undo();
		Assert.assertTrue((size - 2) == aType.getRoles().size());
		Assert.assertFalse(aType.getRoles().containsAll(rolesList));

	}

	@Test
	public void redoTest() {

		command.execute();
		command.undo();

		size = aType.getRoles().size();
		Assert.assertFalse(aType.getRoles().containsAll(rolesList));
		command.redo();
		Assert.assertTrue((size + 2) == aType.getRoles().size());
		Assert.assertTrue(aType.getRoles().containsAll(rolesList));

	}

}
