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
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.RemoveRoleConstraintCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveRoleConstraintCommandTest {

	private RemoveRoleConstraintCommand command;
	private AssociationTypeConstraint atc0;
	private AssociationTypeConstraint atc1;
	private AssociationType associationType0;
	private AssociationType associationType1;
	private RolePlayerConstraint rpc0;
	private RolePlayerConstraint rpc1;
	private RolePlayerConstraint rpc2;
	private RolePlayerConstraint rpc3;
	private RoleConstraint rc0;
	private RoleConstraint rc1;
	private RoleConstraint rc2;
	private TopicMapSchema schema;
	private File file;
	private List<RoleConstraint> rolesConstraintsList;
	private List<RoleConstraint> rcList;
	private int rcSize;

	@Before
	public void prepare() {

		if (rolesConstraintsList == null)
			rolesConstraintsList = new ArrayList<RoleConstraint>();

		if (rc0 == null)
			rc0 = ModelFactory.eINSTANCE.createRoleConstraint();

		if (rc1 == null)
			rc1 = ModelFactory.eINSTANCE.createRoleConstraint();

		if (rc2 == null)
			rc2 = ModelFactory.eINSTANCE.createRoleConstraint();

		if (rpc0 == null) {

			rpc0 = ModelFactory.eINSTANCE.createRolePlayerConstraint();
			rpc0.setRole(rc0);

		}

		if (rpc1 == null) {

			rpc1 = ModelFactory.eINSTANCE.createRolePlayerConstraint();
			rpc1.setRole(rc1);

		}

		if (rpc2 == null) {

			rpc2 = ModelFactory.eINSTANCE.createRolePlayerConstraint();
			rpc2.setRole(rc2);

		}
		
		if (rpc3 == null) {

			rpc3 = ModelFactory.eINSTANCE.createRolePlayerConstraint();
			rpc3.setRole(rc2);

		}

		if (associationType0 == null) {

			associationType0 = ModelFactory.eINSTANCE.createAssociationType();
			associationType0.getRoles().add(rc0);
			associationType0.getRoles().add(rc1);
			associationType0.getRoles().add(rc2);

		}


		if (atc0 == null) {

			atc0 = ModelFactory.eINSTANCE.createAssociationTypeConstraint();
			atc0.setType(associationType0);
			atc0.getPlayerConstraints().add(rpc0);
			atc0.getPlayerConstraints().add(rpc1);
			atc0.getPlayerConstraints().add(rpc2);

		}

		if (atc1 == null) {

			atc1 = ModelFactory.eINSTANCE.createAssociationTypeConstraint();
			atc1.setType(associationType1);
			atc1.getPlayerConstraints().add(rpc3);

		}

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getAssociationTypeConstraints().add(atc0);
			schema.getAssociationTypeConstraints().add(atc1);

		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);

		}

		ModelIndexer.createInstance(file);

	}

	@After
	public void shutdown() {

		associationType0 = null;
		associationType1 = null;
		rc0 = null;
		rc1 = null;
		rc2 = null;
		rpc0 = null;
		rpc1 = null;
		rpc2 = null;
		rolesConstraintsList = null;
		rcList = null;
		command = null;

	}

	@Test
	public void canExecuteTestC0() {

		command = new RemoveRoleConstraintCommand(associationType0, rc0);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1() {

		rolesConstraintsList.add(rc0);
		rolesConstraintsList.add(rc1);

		command = new RemoveRoleConstraintCommand(associationType0,
				rolesConstraintsList);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestC0() {

		command = new RemoveRoleConstraintCommand(associationType0, rc0);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		rcSize = associationType0.getRoles().size();
		rcList = new ArrayList<RoleConstraint>(associationType0.getRoles());
		rcList.remove(rc0);

		command.execute();

		// check associationTypes roles
		Assert.assertTrue((rcSize - 1) == associationType0.getRoles().size());
		Assert.assertTrue(Tools.roleConstraintListCompare(rcList,
				associationType0.getRoles()));

		// check rpcs roles
		Assert.assertTrue(rpc0.getRole() == null);
		Assert.assertTrue(rpc1.getRole() == rc1);
		Assert.assertTrue(rpc2.getRole() == rc2);
		// check if rpc3 remains unchanged (from atc1) 
		Assert.assertTrue(rpc3.getRole() == rc2);

	}

	@Test
	public void executeTestC1() {

		rolesConstraintsList.add(rc0);
		rolesConstraintsList.add(rc1);

		command = new RemoveRoleConstraintCommand(associationType0,
				rolesConstraintsList);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		rcSize = associationType0.getRoles().size();
		rcList = new ArrayList<RoleConstraint>(associationType0.getRoles());
		rcList.remove(rc0);
		rcList.remove(rc1);

		command.execute();

		// check associationTypes roles
		Assert.assertTrue((rcSize - 2) == associationType0.getRoles().size());
		Assert.assertTrue(Tools.roleConstraintListCompare(rcList,
				associationType0.getRoles()));

		// check rpcs roles
		Assert.assertTrue(rpc0.getRole() == null);
		Assert.assertTrue(rpc1.getRole() == null);
		Assert.assertTrue(rpc2.getRole() == rc2);
		// check if rpc3 remains unchanged (from atc1) 
		Assert.assertTrue(rpc3.getRole() == rc2);

	}

	@Test
	public void canUndoTestC0() {

		command = new RemoveRoleConstraintCommand(associationType0, rc0);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1() {

		rolesConstraintsList.add(rc0);
		rolesConstraintsList.add(rc1);

		command = new RemoveRoleConstraintCommand(associationType0,
				rolesConstraintsList);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestC0() {

		command = new RemoveRoleConstraintCommand(associationType0, rc0);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		rcSize = associationType0.getRoles().size();
		rcList = new ArrayList<RoleConstraint>(associationType0.getRoles());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		 // check associationTypes roles
		 Assert.assertTrue(rcSize == associationType0.getRoles().size());
		 Assert.assertTrue(Tools.roleConstraintListCompare(rcList,
		 associationType0.getRoles()));

		// check rpcs roles
		Assert.assertTrue(rpc0.getRole() == rc0);
		Assert.assertTrue(rpc1.getRole() == rc1);
		Assert.assertTrue(rpc2.getRole() == rc2);
		// check if rpc3 remains unchanged (from atc1) 
		Assert.assertTrue(rpc3.getRole() == rc2);

	}

	@Test
	public void undoTestC1() {

		rolesConstraintsList.add(rc0);
		rolesConstraintsList.add(rc1);

		command = new RemoveRoleConstraintCommand(associationType0,
				rolesConstraintsList);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		rcSize = associationType0.getRoles().size();
		rcList = new ArrayList<RoleConstraint>(associationType0.getRoles());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		
		 // check associationTypes roles
		 Assert.assertTrue(rcSize == associationType0.getRoles().size());
		 Assert.assertTrue(Tools.roleConstraintListCompare(rcList,
		 associationType0.getRoles()));

		// check rpcs roles
		Assert.assertTrue(rpc0.getRole() == rc0);
		Assert.assertTrue(rpc1.getRole() == rc1);
		Assert.assertTrue(rpc2.getRole() == rc2);
		// check if rpc3 remains unchanged (from atc1) 
		Assert.assertTrue(rpc3.getRole() == rc2);

	}

	@Test
	public void redoTestC0() {

		command = new RemoveRoleConstraintCommand(associationType0, rc0);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone associationTypes roles
		rcSize = associationType0.getRoles().size();
		rcList = new ArrayList<RoleConstraint>(associationType0.getRoles());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check associationTypes roles
		Assert.assertTrue(rcSize == associationType0.getRoles().size());
		Assert.assertTrue(Tools.roleConstraintListCompare(rcList,
				associationType0.getRoles()));

		// check rpcs roles
		Assert.assertTrue(rpc0.getRole() == null);
		Assert.assertTrue(rpc1.getRole() == rc1);
		Assert.assertTrue(rpc2.getRole() == rc2);
		// check if rpc3 remains unchanged (from atc1) 
		Assert.assertTrue(rpc3.getRole() == rc2);

	}

	@Test
	public void redoTestC1() {

		rolesConstraintsList.add(rc0);
		rolesConstraintsList.add(rc1);

		command = new RemoveRoleConstraintCommand(associationType0,
				rolesConstraintsList);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone associationTypes roles
		rcSize = associationType0.getRoles().size();
		rcList = new ArrayList<RoleConstraint>(associationType0.getRoles());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check associationTypes roles
		Assert.assertTrue(rcSize == associationType0.getRoles().size());
		Assert.assertTrue(Tools.roleConstraintListCompare(rcList,
				associationType0.getRoles()));

		// check rpcs roles
		Assert.assertTrue(rpc0.getRole() == null);
		Assert.assertTrue(rpc1.getRole() == null);
		Assert.assertTrue(rpc2.getRole() == rc2);
		// check if rpc3 remains unchanged (from atc1) 
		Assert.assertTrue(rpc3.getRole() == rc2);

	}

}
