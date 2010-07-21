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

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetAssociationTypeCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetAssociationTypeCommandTest {

	private SetAssociationTypeCommand command;
	private TopicType newType;
	private TopicType oldType;
	private AssociationTypeConstraint constraint;
	private RoleConstraint role;
	private RolePlayerConstraint rpcHasRole;
	private RolePlayerConstraint rpcNoRole;

	@Before
	public void prepare() {

		if (role == null)
			role = ModelFactory.eINSTANCE.createRoleConstraint();

		if (rpcHasRole == null) {

			rpcHasRole = ModelFactory.eINSTANCE.createRolePlayerConstraint();
			rpcHasRole.setRole(role);

		}

		if (rpcNoRole == null)
			rpcNoRole = ModelFactory.eINSTANCE.createRolePlayerConstraint();

		if (oldType == null)
			oldType = ModelFactory.eINSTANCE.createTopicType();

		if (newType == null)
			newType = ModelFactory.eINSTANCE.createTopicType();

		if (constraint == null) {

			constraint = ModelFactory.eINSTANCE
					.createAssociationTypeConstraint();
			constraint.setType(oldType);

		}

		if (command == null)
			command = new SetAssociationTypeCommand(constraint, newType);
	}

	@After
	public void shutdown() {

		role = null;
		rpcHasRole = null;
		rpcNoRole = null;
		constraint = null;
		oldType = null;
		newType = null;
		command = null;

	}

	@Test
	public void canExecuteTestNoPlayerConstraints() {

		Assert.assertTrue(command.canExecute());

		// old != null , new == null
		command = new SetAssociationTypeCommand(constraint, null);
		Assert.assertTrue(command.canExecute());

		// old == null , new == null
		constraint.setType(null);
		command = new SetAssociationTypeCommand(constraint, null);
		Assert.assertFalse(command.canExecute());

		// old == null , new != null
		command = new SetAssociationTypeCommand(constraint, newType);
		Assert.assertTrue(command.canExecute());

		// old == new
		constraint.setType(newType);
		command = new SetAssociationTypeCommand(constraint, newType);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void canExecuteTestPlayerConstraints() {

		constraint.getPlayerConstraints().add(rpcHasRole);
		constraint.getPlayerConstraints().add(rpcNoRole);
		command = new SetAssociationTypeCommand(constraint, newType);

		Assert.assertTrue(command.canExecute());

		// old != null , new == null
		command = new SetAssociationTypeCommand(constraint, null);
		Assert.assertTrue(command.canExecute());

		// old == null , new == null
		constraint.setType(null);
		command = new SetAssociationTypeCommand(constraint, null);
		Assert.assertFalse(command.canExecute());

		// old == null , new != null
		command = new SetAssociationTypeCommand(constraint, newType);
		Assert.assertTrue(command.canExecute());

		// old == new
		constraint.setType(newType);
		command = new SetAssociationTypeCommand(constraint, newType);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTestNoPlayerConstraints() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert
				.assertTrue(Tools.topicTypeCompare(newType, constraint
						.getType()));

	}

	@Test
	public void executeTestPlayerConstraints() {

		constraint.getPlayerConstraints().add(rpcHasRole);
		constraint.getPlayerConstraints().add(rpcNoRole);
		command = new SetAssociationTypeCommand(constraint, newType);

		Assert.assertTrue(command.canExecute());
		Assert.assertTrue(rpcHasRole.getRole() != null);
		command.execute();

		Assert.assertTrue(rpcHasRole.getRole() == null);
		Assert.assertTrue(rpcNoRole.getRole() == null);
		Assert
				.assertTrue(Tools.topicTypeCompare(newType, constraint
						.getType()));
	}

	@Test
	public void canUndoNoPlayerConstraints() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoPlayerConstraints() {

		constraint.getPlayerConstraints().add(rpcHasRole);
		constraint.getPlayerConstraints().add(rpcNoRole);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestNoPlayerConstraints() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert
				.assertTrue(Tools.topicTypeCompare(oldType, constraint
						.getType()));

	}

	@Test
	public void undoTestPlayerConstraints() {

		constraint.getPlayerConstraints().add(rpcHasRole);
		constraint.getPlayerConstraints().add(rpcNoRole);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(Tools.roleConstraintCompare(role, rpcHasRole
				.getRole()));
		Assert.assertTrue(rpcNoRole.getRole() == null);
		Assert
				.assertTrue(Tools.topicTypeCompare(oldType, constraint
						.getType()));

	}

	@Test
	public void redoTestNoPlayerConstraints() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert
				.assertTrue(Tools.topicTypeCompare(newType, constraint
						.getType()));

	}

	@Test
	public void redoTestPlayerConstraints() {

		constraint.getPlayerConstraints().add(rpcHasRole);
		constraint.getPlayerConstraints().add(rpcNoRole);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(rpcHasRole.getRole() == null);
		Assert.assertTrue(rpcNoRole.getRole() == null);
		Assert
				.assertTrue(Tools.topicTypeCompare(newType, constraint
						.getType()));

	}

}
