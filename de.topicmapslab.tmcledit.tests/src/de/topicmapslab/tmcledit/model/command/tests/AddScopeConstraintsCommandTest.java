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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.commands.AddScopeConstraintsCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddScopeConstraintsCommandTest {

	private AddScopeConstraintsCommand command;
	private ScopedTopicType sTopicType;
	private ScopeConstraint sConstraint1;
	private ScopeConstraint sConstraint2;
	private List<ScopeConstraint> scopeList1;
	private List<ScopeConstraint> scopeList2;
	private int constructor = 1;
	private int size;

	@Before
	public void prepare() {

		if (sConstraint1 == null && constructor == 2) {

			sConstraint1 = ModelFactory.eINSTANCE.createScopeConstraint();
			sConstraint1.setId(2);

		}

		if (sConstraint2 == null && constructor == 2) {

			sConstraint2 = ModelFactory.eINSTANCE.createScopeConstraint();
			sConstraint2.setId(3);

		}

		sTopicType = ModelFactory.eINSTANCE.createScopedReifiableTopicType();

		if (scopeList1 == null && constructor == 1) {

			scopeList1 = new ArrayList<ScopeConstraint>();
			ScopeConstraint sConstraint1 = ModelFactory.eINSTANCE
					.createScopeConstraint();
			ScopeConstraint sConstraint2 = ModelFactory.eINSTANCE
					.createScopeConstraint();
			sConstraint1.setId(0);
			sConstraint2.setId(1);
			scopeList1.add(sConstraint1);
			scopeList1.add(sConstraint2);

		}

		if (command == null && constructor == 1)
			command = new AddScopeConstraintsCommand(sTopicType, scopeList1);
		else
			command = new AddScopeConstraintsCommand(sTopicType, sConstraint1,
					sConstraint2);

	}

	@After
	public void shutdown() {

		sConstraint1 = null;
		sConstraint2 = null;
		sTopicType = null;
		scopeList1 = null;
		scopeList2 = null;
		command = null;

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
		size = sTopicType.getScope().size();
		scopeList2 = new ArrayList<ScopeConstraint>(sTopicType.getScope());
		scopeList2.add(sConstraint1);
		scopeList2.add(sConstraint2);

		Assert.assertFalse(sTopicType.getScope().containsAll(scopeList1));
		Assert.assertFalse(sTopicType.getScope().contains(sConstraint1));
		Assert.assertFalse(sTopicType.getScope().contains(sConstraint2));

		command.execute();

		Assert.assertTrue((size + 2) == sTopicType.getScope().size());

		if (constructor == 1) {

			Assert.assertTrue(sTopicType.getScope().containsAll(scopeList1));
			Assert.assertTrue(Tools.scopeConstraintListCompare(scopeList1,
					sTopicType.getScope()));

		}

		if (constructor == 2) {

			Assert.assertTrue(sTopicType.getScope().contains(sConstraint1));
			Assert.assertTrue(sTopicType.getScope().contains(sConstraint2));
			Assert.assertTrue(Tools.scopeConstraintListCompare(scopeList2,
					sTopicType.getScope()));

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

		size = sTopicType.getScope().size();
		scopeList2 = new ArrayList<ScopeConstraint>(sTopicType.getScope());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == sTopicType.getScope().size());
		Assert.assertFalse(sTopicType.getScope().containsAll(scopeList1));
		Assert.assertFalse(sTopicType.getScope().contains(sConstraint1));
		Assert.assertFalse(sTopicType.getScope().contains(sConstraint2));
		Assert.assertTrue(Tools.scopeConstraintListCompare(scopeList2,
				sTopicType.getScope()));

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

		size = sTopicType.getScope().size();
		scopeList2 = new ArrayList<ScopeConstraint>(sTopicType.getScope());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == sTopicType.getScope().size());

		if (constructor == 1) {

			Assert.assertTrue(sTopicType.getScope().containsAll(scopeList1));
			Assert.assertTrue(Tools.scopeConstraintListCompare(scopeList1,
					sTopicType.getScope()));

		}

		if (constructor == 2) {

			Assert.assertTrue(sTopicType.getScope().contains(sConstraint1));
			Assert.assertTrue(sTopicType.getScope().contains(sConstraint2));
			Assert.assertTrue(Tools.scopeConstraintListCompare(scopeList2,
					sTopicType.getScope()));

		}

		if (constructor == 1) {

			constructor = 2;
			prepare();
			redoTest();

		}

		constructor = 1;

	}

}
