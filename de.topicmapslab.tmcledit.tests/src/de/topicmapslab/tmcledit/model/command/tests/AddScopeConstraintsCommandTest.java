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
	private List<ScopeConstraint> scopeList;
	private int size;

	@Before
	public void prepare() {

		if (sTopicType == null)
			sTopicType = ModelFactory.eINSTANCE
					.createScopedReifiableTopicType();

		if (scopeList == null) {

			scopeList = new ArrayList<ScopeConstraint>();
			ScopeConstraint sConstraint1 = ModelFactory.eINSTANCE
					.createScopeConstraint();
			ScopeConstraint sConstraint2 = ModelFactory.eINSTANCE
					.createScopeConstraint();
			scopeList.add(sConstraint1);
			scopeList.add(sConstraint2);

		}

		if (command == null)
			command = new AddScopeConstraintsCommand(sTopicType, scopeList);

	}

	@After
	public void shutdown() {

		sTopicType = null;
		scopeList = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		size = sTopicType.getScope().size();
		Assert.assertFalse(sTopicType.getScope().containsAll(scopeList));
		command.execute();
		Assert.assertTrue((size + 2) == sTopicType.getScope().size());
		Assert.assertTrue(sTopicType.getScope().containsAll(scopeList));

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		command.execute();

		size = sTopicType.getScope().size();
		Assert.assertTrue(sTopicType.getScope().containsAll(scopeList));
		command.undo();
		Assert.assertTrue((size - 2) == sTopicType.getScope().size());
		Assert.assertFalse(sTopicType.getScope().containsAll(scopeList));

	}

	@Test
	public void redoTest() {

		command.execute();
		command.undo();

		size = sTopicType.getScope().size();
		Assert.assertFalse(sTopicType.getScope().containsAll(scopeList));
		command.execute();
		Assert.assertTrue((size + 2) == sTopicType.getScope().size());
		Assert.assertTrue(sTopicType.getScope().containsAll(scopeList));

	}

}
