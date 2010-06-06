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
import de.topicmapslab.tmcledit.model.commands.SetTopicTypeIdentifiersCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetTopicTypeIdentifiersCommandTest {

	private SetTopicTypeIdentifiersCommand command;
	private TopicType topicType;
	private String oldValue0;
	private String oldValue1;
	private String newValue0;
	private String newValue1;;
	private List<String> newList;
	private List<String> oldList;

	@Before
	public void prepare() {

		if (oldValue0 == null)
			oldValue0 = "TMCL0";

		if (oldValue1 == null)
			oldValue1 = "TMCL1";

		if (newValue0 == null)
			newValue0 = "TMQL0";

		if (newValue1 == null)
			newValue1 = "TMQL1";

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.getIdentifiers().add(oldValue0);
			topicType.getIdentifiers().add(oldValue1);

		}

		if (newList == null) {

			newList = new ArrayList<String>();
			newList.add(newValue0);
			newList.add(newValue1);

		}

		if (oldList == null) {

			oldList = new ArrayList<String>();
			oldList.add(oldValue0);
			oldList.add(oldValue1);

		}

		if (command == null)
			command = new SetTopicTypeIdentifiersCommand(newList, topicType);
	}

	@After
	public void shutdown() {

		oldList = null;
		newList = null;
		oldValue0 = null;
		oldValue1 = null;
		newValue0 = null;
		newValue1 = null;
		topicType = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		command = new SetTopicTypeIdentifiersCommand(oldList, topicType);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		Assert.assertTrue(topicType.getIdentifiers().equals(newList));

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
		Assert.assertTrue(topicType.getIdentifiers().equals(oldList));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertTrue(topicType.getIdentifiers().equals(newList));

	}

}
