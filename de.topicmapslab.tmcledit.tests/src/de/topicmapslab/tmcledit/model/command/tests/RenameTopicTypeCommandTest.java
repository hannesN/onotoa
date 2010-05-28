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

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameTopicTypeCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RenameTopicTypeCommandTest {

	private final String BASELOCATOR_1 = "http://onotoa.topicmapslab.de/";

	private RenameTopicTypeCommand command;
	private TopicType topicType0;
	private TopicType topicType1;
	private String oldName;
	private String newName;
	private String otherName;
	private TopicMapSchema schema;
	private File file;

	@Before
	public void prepare() {

		if (oldName == null)
			oldName = "oldName";

		if (newName == null)
			newName = "new Name";

		if (otherName == null)
			otherName = "otherName";

		if (topicType0 == null) {

			topicType0 = ModelFactory.eINSTANCE.createTopicType();
			topicType0.setName(oldName);

		}

		if (topicType1 == null) {

			topicType1 = ModelFactory.eINSTANCE.createTopicType();
			topicType1.setName(otherName);

		}

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getTopicTypes().add(topicType0);
			schema.getTopicTypes().add(topicType1);

		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);

		}

		ModelIndexer.createInstance(file);

		if (command == null)
			command = new RenameTopicTypeCommand(topicType0, newName);

	}

	@After
	public void shutdown() {

		oldName = null;
		newName = null;
		otherName = null;
		topicType0 = null;
		topicType1 = null;
		schema = null;
		file = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		command = new RenameTopicTypeCommand(topicType0, otherName);
		Assert.assertFalse(command.canExecute());

		command = new RenameTopicTypeCommand(topicType0, "");
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void canExecuteTestWithBaseLocator() {

		topicType0.getIdentifiers().add(BASELOCATOR_1 + "test/");
		schema.setBaseLocator(BASELOCATOR_1);

		Assert.assertTrue(command.canExecute());

		command = new RenameTopicTypeCommand(topicType0, otherName);
		Assert.assertFalse(command.canExecute());

		command = new RenameTopicTypeCommand(topicType0, "");
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		Assert.assertEquals(newName, topicType0.getName());

	}

	@Test
	public void executeTestWithBaseLocator() {

		topicType0.getIdentifiers().add(BASELOCATOR_1 + "test/");
		schema.setBaseLocator(BASELOCATOR_1);

		Assert.assertTrue(command.canExecute());
		command.execute();

		Assert.assertTrue("http://onotoa.topicmapslab.de/new_name"
				.equals(topicType0.getIdentifiers().get(0)));
		Assert.assertEquals(newName, topicType0.getName());

	}

	@Test
	public void canUndo() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoWithBaseLocator() {

		topicType0.getIdentifiers().add(BASELOCATOR_1 + "test/");
		schema.setBaseLocator(BASELOCATOR_1);

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

		Assert.assertEquals(oldName, topicType0.getName());

	}

	@Test
	public void undoTestWithBaseLocator() {

		topicType0.getIdentifiers().add(BASELOCATOR_1 + "test/");
		schema.setBaseLocator(BASELOCATOR_1);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue("http://onotoa.topicmapslab.de/test/"
				.equals(topicType0.getIdentifiers().get(0)));
		Assert.assertEquals(oldName, topicType0.getName());

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertEquals(newName, topicType0.getName());

	}

	@Test
	public void redoTestWithBaseLocator() {

		topicType0.getIdentifiers().add(BASELOCATOR_1 + "test/");
		schema.setBaseLocator(BASELOCATOR_1);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue("http://onotoa.topicmapslab.de/new_name"
				.equals(topicType0.getIdentifiers().get(0)));
		Assert.assertEquals(newName, topicType0.getName());

	}

}
