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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.SetAbstractTopicTypeCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetAbstractTopicTypeCommandTest {

	private SetAbstractTopicTypeCommand command;
	private TopicType topicType;
	private boolean isAbstract = true;

	@Before
	public void prepare() {

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.setAbstract(false);

		}

		if (command == null)
			command = new SetAbstractTopicTypeCommand(topicType, isAbstract);

	}

	@After
	public void shutdown() {

		topicType = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		topicType.setAbstract(true);
		command = new SetAbstractTopicTypeCommand(topicType, isAbstract);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		Assert.assertTrue(topicType.isAbstract());

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

		Assert.assertTrue(topicType.isAbstract() == false);

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(topicType.isAbstract());

	}

}
