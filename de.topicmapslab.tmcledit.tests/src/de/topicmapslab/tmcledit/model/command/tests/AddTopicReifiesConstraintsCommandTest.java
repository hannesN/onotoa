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
	private List<TopicReifiesConstraint> trConstraintsList;
	private int size;

	@Before
	public void prepare() {

		if (tt == null)
			tt = ModelFactory.eINSTANCE.createTopicType();

		if (trConstraintsList == null) {

			trConstraintsList = new ArrayList<TopicReifiesConstraint>();
			TopicReifiesConstraint trc1 = ModelFactory.eINSTANCE
					.createTopicReifiesConstraint();
			TopicReifiesConstraint trc2 = ModelFactory.eINSTANCE
					.createTopicReifiesConstraint();
			trConstraintsList.add(trc1);
			trConstraintsList.add(trc2);

		}

		if (command == null)
			command = new AddTopicReifiesConstraintsCommand(tt,
					trConstraintsList);

	}

	@After
	public void shutdown() {

		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		size = tt.getTopicReifiesConstraints().size();
		Assert.assertFalse(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList));
		command.execute();
		Assert.assertTrue((size + 2) == tt.getTopicReifiesConstraints().size());
		Assert.assertTrue(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList));

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		command.execute();

		size = tt.getTopicReifiesConstraints().size();
		Assert.assertTrue(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList));
		command.undo();
		Assert.assertTrue((size - 2) == tt.getTopicReifiesConstraints().size());
		Assert.assertFalse(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList));
	}

	@Test
	public void redoTest() {

		command.execute();
		command.undo();

		size = tt.getTopicReifiesConstraints().size();
		Assert.assertFalse(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList));
		command.redo();
		Assert.assertTrue((size + 2) == tt.getTopicReifiesConstraints().size());
		Assert.assertTrue(tt.getTopicReifiesConstraints().containsAll(
				trConstraintsList));

	}

}
