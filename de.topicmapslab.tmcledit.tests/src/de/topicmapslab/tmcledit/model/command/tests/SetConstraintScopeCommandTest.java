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
package de.topicmapslab.tmcledit.model.command.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.commands.SetConstraintScopeCommand;

public class SetConstraintScopeCommandTest {

	private SetConstraintScopeCommand command;
	private ScopedTopicType scopedTopicType;
	private List<ScopeConstraint> newScopeList;
	private List<ScopeConstraint> oldScopeList;
	private ScopeConstraint oldScope0;
	private ScopeConstraint oldScope1;
	private ScopeConstraint newScope0;
	private ScopeConstraint newScope1;

	@Before
	public void prepare() {

		if (oldScope0 == null)
			oldScope0 = ModelFactory.eINSTANCE.createScopeConstraint();

		if (oldScope1 == null)
			oldScope1 = ModelFactory.eINSTANCE.createScopeConstraint();

		if (newScope0 == null)
			newScope0 = ModelFactory.eINSTANCE.createScopeConstraint();

		if (newScope1 == null)
			newScope1 = ModelFactory.eINSTANCE.createScopeConstraint();

		if (newScopeList == null) {

			newScopeList = new ArrayList<ScopeConstraint>();
			newScopeList.add(newScope0);
			newScopeList.add(newScope1);

		}

		if (oldScopeList == null) {

			oldScopeList = new ArrayList<ScopeConstraint>();
			oldScopeList.add(oldScope0);
			oldScopeList.add(oldScope1);

		}

		if (scopedTopicType == null) {

			scopedTopicType = ModelFactory.eINSTANCE
					.createScopedReifiableTopicType();
			scopedTopicType.getScope().add(oldScope0);
			scopedTopicType.getScope().add(oldScope1);

		}

		if (command == null)
			command = new SetConstraintScopeCommand(scopedTopicType,
					newScopeList);

	}

	@After
	public void shutdown() {

		scopedTopicType = null;
		newScope1 = null;
		newScope0 = null;
		oldScope1 = null;
		oldScope0 = null;
		newScopeList = null;
		oldScopeList = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(Tools.scopeConstraintListCompare(newScopeList,
				scopedTopicType.getScope()));

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
		Assert.assertTrue(Tools.scopeConstraintListCompare(oldScopeList,
				scopedTopicType.getScope()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertTrue(Tools.scopeConstraintListCompare(newScopeList,
				scopedTopicType.getScope()));

	}

}
