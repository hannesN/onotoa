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
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.AddTopicReifiesConstraintsCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddTopicReifiesConstraintsCommandTest {

	private AddTopicReifiesConstraintsCommand command;
	private TopicType tt;
	private List<TopicReifiesConstraint> trConstraintsList1;
	private List<TopicReifiesConstraint> trConstraintsList2;
	private TopicReifiesConstraint trc1;
	private TopicReifiesConstraint trc2;
	private int size;
	private int constructor = 1;

	@Before
	public void prepare() {

		tt = ModelFactory.eINSTANCE.createTopicType();

		if (trConstraintsList1 == null && constructor == 1) {

			trConstraintsList1 = new ArrayList<TopicReifiesConstraint>();
			TopicReifiesConstraint trc1 = ModelFactory.eINSTANCE
					.createTopicReifiesConstraint();
			TopicReifiesConstraint trc2 = ModelFactory.eINSTANCE
					.createTopicReifiesConstraint();
			trc1.setId(0);
			trc2.setId(1);
			trConstraintsList1.add(trc1);
			trConstraintsList1.add(trc2);

		}

		if (trc1 == null && constructor == 2) {

			trc1 = ModelFactory.eINSTANCE.createTopicReifiesConstraint();
			trc1.setId(3);

		}

		if (trc2 == null && constructor == 2) {

			trc2 = ModelFactory.eINSTANCE.createTopicReifiesConstraint();
			trc2.setId(4);

		}

		if (command == null && constructor == 1)
			command = new AddTopicReifiesConstraintsCommand(tt,
					trConstraintsList1);
		else
			command = new AddTopicReifiesConstraintsCommand(tt, trc1, trc2);

	}

	@After
	public void shutdown() {

		tt = null;
		trConstraintsList1 = null;
		trConstraintsList2 = null;
		trc1 = null;
		trc2 = null;
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
		size = tt.getTopicReifiesConstraints().size();
		trConstraintsList2 = new ArrayList<TopicReifiesConstraint>(tt
				.getTopicReifiesConstraints());
		trConstraintsList2.add(trc1);
		trConstraintsList2.add(trc2);

		Assert.assertFalse(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList1));
		Assert.assertFalse(tt.getTopicReifiesConstraints().contains(trc1));
		Assert.assertFalse(tt.getTopicReifiesConstraints().contains(trc2));

		command.execute();

		Assert.assertTrue((size + 2) == tt.getTopicReifiesConstraints().size());

		if (constructor == 1) {

			Assert.assertTrue(tt.getTopicReifiesConstraints().containsAll(
					trConstraintsList1));
			Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(
					trConstraintsList1, tt.getTopicReifiesConstraints()));

		}

		if (constructor == 2) {

			Assert.assertTrue(tt.getTopicReifiesConstraints().contains(trc1));
			Assert.assertTrue(tt.getTopicReifiesConstraints().contains(trc2));
			Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(
					trConstraintsList2, tt.getTopicReifiesConstraints()));

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

		size = tt.getTopicReifiesConstraints().size();
		trConstraintsList2 = new ArrayList<TopicReifiesConstraint>(tt
				.getTopicReifiesConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == tt.getTopicReifiesConstraints().size());
		Assert.assertFalse(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList1));
		Assert.assertFalse(tt.getTopicReifiesConstraints().contains(trc1));
		Assert.assertFalse(tt.getTopicReifiesConstraints().contains(trc2));
		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(
				trConstraintsList2, tt.getTopicReifiesConstraints()));

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

		size = tt.getTopicReifiesConstraints().size();
		trConstraintsList2 = new ArrayList<TopicReifiesConstraint>(tt
				.getTopicReifiesConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == tt.getTopicReifiesConstraints().size());

		if (constructor == 1) {

			Assert.assertTrue(tt.getTopicReifiesConstraints().containsAll(
					trConstraintsList1));
			Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(
					trConstraintsList1, tt.getTopicReifiesConstraints()));

		}

		if (constructor == 2) {

			Assert.assertTrue(tt.getTopicReifiesConstraints().contains(trc1));
			Assert.assertTrue(tt.getTopicReifiesConstraints().contains(trc2));
			Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(
					trConstraintsList2, tt.getTopicReifiesConstraints()));

		}

		if (constructor == 1) {

			constructor = 2;
			prepare();
			redoTest();

		}

		constructor = 1;

	}

}
