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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.ScopedTopicType;
import de.topicmapslab.tmcledit.model.commands.RemoveScopeConstraintsCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveScopeConstraintsCommandTest {

	private RemoveScopeConstraintsCommand command;
	private ScopedTopicType scopedTopicType;
	private ScopeConstraint scope0;
	private ScopeConstraint scope1;
	private ScopeConstraint scope2;
	private List<ScopeConstraint> scopeList;
	private List<ScopeConstraint> list;
	private int size;

	@Before
	public void prepare() {

		if (scope0 == null)
			scope0 = ModelFactory.eINSTANCE.createScopeConstraint();

		if (scope1 == null)
			scope1 = ModelFactory.eINSTANCE.createScopeConstraint();

		if (scope2 == null)
			scope2 = ModelFactory.eINSTANCE.createScopeConstraint();

		if (scopeList == null) {

			scopeList = new ArrayList<ScopeConstraint>();
			scopeList.add(scope0);
			scopeList.add(scope1);

		}

		if (scopedTopicType == null) {

			scopedTopicType = ModelFactory.eINSTANCE
					.createScopedReifiableTopicType();
			scopedTopicType.getScope().add(scope0);
			scopedTopicType.getScope().add(scope1);
			scopedTopicType.getScope().add(scope2);

		}

	}

	@After
	public void shutdown() {

		scope0 = null;
		scope1 = null;
		scope2 = null;
		scopedTopicType = null;
		scopeList = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestC0() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scope0);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scopeList);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestC0() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scope0);
		Assert.assertTrue(command.canExecute());

		size = scopedTopicType.getScope().size();
		list = new ArrayList<ScopeConstraint>(scopedTopicType.getScope());
		list.remove(scope0);

		command.execute();

		Assert.assertTrue((size - 1) == scopedTopicType.getScope().size());
		Assert.assertTrue(Tools.scopeConstraintListCompare(list,
				scopedTopicType.getScope()));

	}

	@Test
	public void executeTestC1() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scopeList);
		Assert.assertTrue(command.canExecute());

		size = scopedTopicType.getScope().size();
		list = new ArrayList<ScopeConstraint>(scopedTopicType.getScope());
		list.remove(scope0);
		list.remove(scope1);

		command.execute();

		Assert.assertTrue((size - 2) == scopedTopicType.getScope().size());
		Assert.assertTrue(Tools.scopeConstraintListCompare(list,
				scopedTopicType.getScope()));

	}

	@Test
	public void canUndoC0() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scope0);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoC1() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scopeList);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestC0() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scope0);
		Assert.assertTrue(command.canExecute());

		size = scopedTopicType.getScope().size();
		list = new ArrayList<ScopeConstraint>(scopedTopicType.getScope());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == scopedTopicType.getScope().size());
		Assert.assertTrue(Tools.scopeConstraintListCompare(list,
				scopedTopicType.getScope()));

	}

	@Test
	public void undoTestC1() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scopeList);
		Assert.assertTrue(command.canExecute());

		size = scopedTopicType.getScope().size();
		list = new ArrayList<ScopeConstraint>(scopedTopicType.getScope());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == scopedTopicType.getScope().size());
		Assert.assertTrue(Tools.scopeConstraintListCompare(list,
				scopedTopicType.getScope()));

	}

	@Test
	public void redoTestC0() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scope0);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = scopedTopicType.getScope().size();
		list = new ArrayList<ScopeConstraint>(scopedTopicType.getScope());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == scopedTopicType.getScope().size());
		Assert.assertTrue(Tools.scopeConstraintListCompare(list,
				scopedTopicType.getScope()));

	}

	@Test
	public void redoTestC1() {

		command = new RemoveScopeConstraintsCommand(scopedTopicType, scopeList);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = scopedTopicType.getScope().size();
		list = new ArrayList<ScopeConstraint>(scopedTopicType.getScope());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == scopedTopicType.getScope().size());
		Assert.assertTrue(Tools.scopeConstraintListCompare(list,
				scopedTopicType.getScope()));

	}
	
}
