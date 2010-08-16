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
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RemoveTopicReifiesConstraintsCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveTopicReifiesConstraintsCommandTest {

	private RemoveTopicReifiesConstraintsCommand command;
	private TopicReifiesConstraint trc0;
	private TopicReifiesConstraint trc1;
	private TopicReifiesConstraint trc2;
	private TopicType topicType;
	private List<TopicReifiesConstraint> reifiesList;
	private List<TopicReifiesConstraint> list;
	private int size;

	@Before
	public void prepare() {

		if (trc0 == null)
			trc0 = ModelFactory.eINSTANCE.createTopicReifiesConstraint();

		if (trc1 == null)
			trc1 = ModelFactory.eINSTANCE.createTopicReifiesConstraint();

		if (trc2 == null)
			trc2 = ModelFactory.eINSTANCE.createTopicReifiesConstraint();

		if (reifiesList == null) {

			reifiesList = new ArrayList<TopicReifiesConstraint>();
			reifiesList.add(trc0);
			reifiesList.add(trc1);

		}

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.getTopicReifiesConstraints().add(trc0);
			topicType.getTopicReifiesConstraints().add(trc1);
			topicType.getTopicReifiesConstraints().add(trc2);

		}

	}

	@After
	public void shutdown() {

		trc0 = null;
		trc1 = null;
		trc2 = null;
		topicType = null;
		reifiesList = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestC0() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType, trc0);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType,
				reifiesList);
		Assert.assertTrue(command.canExecute());

		reifiesList = new ArrayList<TopicReifiesConstraint>();
		command = new RemoveTopicReifiesConstraintsCommand(topicType,
				reifiesList);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTestC0() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType, trc0);
		Assert.assertTrue(command.canExecute());

		size = topicType.getTopicReifiesConstraints().size();
		list = new ArrayList<TopicReifiesConstraint>(topicType
				.getTopicReifiesConstraints());
		list.remove(trc0);

		command.execute();

		Assert.assertTrue((size - 1) == topicType.getTopicReifiesConstraints()
				.size());
		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(list,
				topicType.getTopicReifiesConstraints()));

	}

	@Test
	public void executeTestC1() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType,
				reifiesList);
		Assert.assertTrue(command.canExecute());

		size = topicType.getTopicReifiesConstraints().size();
		list = new ArrayList<TopicReifiesConstraint>(topicType
				.getTopicReifiesConstraints());
		list.remove(trc0);
		list.remove(trc1);

		command.execute();

		Assert.assertTrue((size - 2) == topicType.getTopicReifiesConstraints()
				.size());
		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(list,
				topicType.getTopicReifiesConstraints()));

	}

	@Test
	public void canUndoC0() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType, trc0);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoC1() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType,
				reifiesList);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestC0() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType, trc0);
		Assert.assertTrue(command.canExecute());

		size = topicType.getTopicReifiesConstraints().size();
		list = new ArrayList<TopicReifiesConstraint>(topicType
				.getTopicReifiesConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert
				.assertTrue(size == topicType.getTopicReifiesConstraints()
						.size());
		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(list,
				topicType.getTopicReifiesConstraints()));

	}

	@Test
	public void undoTestC1() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType,
				reifiesList);
		Assert.assertTrue(command.canExecute());

		size = topicType.getTopicReifiesConstraints().size();
		list = new ArrayList<TopicReifiesConstraint>(topicType
				.getTopicReifiesConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert
				.assertTrue(size == topicType.getTopicReifiesConstraints()
						.size());
		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(list,
				topicType.getTopicReifiesConstraints()));

	}

	@Test
	public void redoTestC0() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType, trc0);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getTopicReifiesConstraints().size();
		list = new ArrayList<TopicReifiesConstraint>(topicType
				.getTopicReifiesConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert
				.assertTrue(size == topicType.getTopicReifiesConstraints()
						.size());
		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(list,
				topicType.getTopicReifiesConstraints()));

	}

	@Test
	public void redoTestC1() {

		command = new RemoveTopicReifiesConstraintsCommand(topicType,
				reifiesList);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getTopicReifiesConstraints().size();
		list = new ArrayList<TopicReifiesConstraint>(topicType
				.getTopicReifiesConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert
				.assertTrue(size == topicType.getTopicReifiesConstraints()
						.size());
		Assert.assertTrue(Tools.topicReifiesConstraintsListCompare(list,
				topicType.getTopicReifiesConstraints()));

	}

}
