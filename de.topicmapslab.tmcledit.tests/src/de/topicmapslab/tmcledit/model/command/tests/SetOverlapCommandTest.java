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
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetOverlapCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetOverlapCommandTest {

	private SetOverlapCommand command;
	private TopicType topicType;
	private TopicType oldOverlap0, oldOverlap1, sameOverlap, newOverlap;
	private List<TopicType> newList;
	private List<TopicType> oldList;

	@Before
	public void prepare() {

		if (oldOverlap0 == null)
			oldOverlap0 = ModelFactory.eINSTANCE.createTopicType();

		if (oldOverlap1 == null)
			oldOverlap1 = ModelFactory.eINSTANCE.createTopicType();

		if (sameOverlap == null)
			sameOverlap = ModelFactory.eINSTANCE.createTopicType();

		if (newOverlap == null)
			newOverlap = ModelFactory.eINSTANCE.createTopicType();

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.getOverlap().add(oldOverlap0);
			topicType.getOverlap().add(oldOverlap1);
			topicType.getOverlap().add(sameOverlap);

		}

		if (newList == null) {

			newList = new ArrayList<TopicType>();
			newList.add(newOverlap);
			newList.add(sameOverlap);

		}

		if (oldList == null) {

			oldList = new ArrayList<TopicType>();
			oldList.add(oldOverlap0);
			oldList.add(oldOverlap1);
			oldList.add(sameOverlap);

		}

		if (command == null)
			command = new SetOverlapCommand(newList, topicType);

	}

	@After
	public void shutdown() {

		oldList = null;
		newList = null;
		oldOverlap0 = null;
		oldOverlap1 = null;
		newOverlap = null;
		sameOverlap = null;
		topicType = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		command = new SetOverlapCommand(oldList, topicType);
		Assert.assertFalse(command.canExecute());

		List<TopicType> sameListOtherOrder = new ArrayList<TopicType>();
		sameListOtherOrder.add(oldOverlap1);
		sameListOtherOrder.add(sameOverlap);
		sameListOtherOrder.add(oldOverlap0);
		command = new SetOverlapCommand(sameListOtherOrder, topicType);
		Assert.assertFalse(command.canExecute());
	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		command.execute();
		Assert.assertTrue(topicType.getOverlap().size() == newList.size());
		Assert.assertTrue(newOverlapTest(topicType.getOverlap(), newList));

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

		Assert.assertTrue(Tools.topicTypeListCompare(oldList, topicType
				.getOverlap()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		oldList = new ArrayList<TopicType>(topicType.getOverlap());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(Tools.topicTypeListCompare(oldList, topicType.getOverlap()));
	}

	private boolean newOverlapTest(List<TopicType> listActual,
			List<TopicType> listShould) {

		for (TopicType t : listShould)
			if (!listActual.contains(t))
				return false;

		return true;

	}

}
