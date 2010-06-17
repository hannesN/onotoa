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
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetCannotReifyConstraint;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetCannotReifyConstraintTest {

	private SetCannotReifyConstraint command;
	private TopicType topicType;
	private TopicReifiesConstraint trc0;
	private TopicReifiesConstraint trc1;
	private List<TopicReifiesConstraint> oldList;

	@Before
	public void prepare() {

		if (trc0 == null)
			trc0 = ModelFactory.eINSTANCE.createTopicReifiesConstraint();

		if (trc1 == null)
			trc1 = ModelFactory.eINSTANCE.createTopicReifiesConstraint();

		if (oldList == null) {

			oldList = new ArrayList<TopicReifiesConstraint>();
			oldList.add(trc0);
			oldList.add(trc1);

		}

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.getTopicReifiesConstraints().add(trc0);
			topicType.getTopicReifiesConstraints().add(trc1);

		}

		if (command == null)
			command = new SetCannotReifyConstraint(topicType);
	}

	@After
	public void shutdown() {

		trc0 = null;
		trc1 = null;
		oldList = null;
		topicType = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		topicType = ModelFactory.eINSTANCE.createTopicType();
		trc0.setType(null);
		topicType.getTopicReifiesConstraints().add(trc0);

		command = new SetCannotReifyConstraint(topicType);
		Assert.assertFalse(command.canExecute());
	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		Assert.assertTrue(topicType.getTopicReifiesConstraints().size() == 1);
		trc0 = topicType.getTopicReifiesConstraints().get(0);
		Assert.assertTrue(trc0.getCardMax().equals("0"));
		Assert.assertTrue(trc0.getCardMin().equals("0"));

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

		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(oldList,
				topicType.getTopicReifiesConstraints()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		
		Assert.assertTrue(topicType.getTopicReifiesConstraints().size() == 1);
		trc0 = topicType.getTopicReifiesConstraints().get(0);
		Assert.assertTrue(trc0.getCardMax().equals("0"));
		Assert.assertTrue(trc0.getCardMin().equals("0"));

	}

}
