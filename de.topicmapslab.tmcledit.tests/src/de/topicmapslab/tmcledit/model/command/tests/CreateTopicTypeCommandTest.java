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

import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateTopicTypeCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class CreateTopicTypeCommandTest {

	private CreateTopicTypeCommand command;
	private TopicMapSchema schema;
	private String existingName = "TMCL";
	private String newName = "TMQL";
	private TopicType existingTopicType;
	private TopicType newTopicType;
	private List<TopicType> list;
	private File file;
	private int size;
	private int id;

	@Before
	public void prepare() {

		if (existingTopicType == null) {

			existingTopicType = ModelFactory.eINSTANCE.createTopicType();
			existingTopicType.setName(existingName);

		}

		if (newTopicType == null)
			newTopicType = ModelFactory.eINSTANCE.createTopicType();

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getTopicTypes().add(existingTopicType);
		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);

		}

		ModelIndexer.createInstance(file);

	}

	@After
	public void shutdown() {

		existingTopicType = null;
		newTopicType = null;
		existingName = null;
		newName = null;
		file = null;
		schema = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestC0() {

		command = new CreateTopicTypeCommand(schema, existingName);
		Assert.assertFalse(command.canExecute());

		command = new CreateTopicTypeCommand(schema, newName);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1() {

		newTopicType.setName(existingName);
		command = new CreateTopicTypeCommand(schema, newTopicType);
		Assert.assertFalse(command.canExecute());

		newTopicType.setName(newName);
		command = new CreateTopicTypeCommand(schema, newTopicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestC0() {

		command = new CreateTopicTypeCommand(schema, newName);
		Assert.assertTrue(command.canExecute());

		size = schema.getTopicTypes().size();
		list = new ArrayList<TopicType>(schema.getTopicTypes());

		command.execute();
		id = schema.getTopicTypes().get(size).getId();
		existingTopicType.setName(newName);
		existingTopicType.setId(id);
		list.add(existingTopicType);

		Assert.assertTrue((size + 1) == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(list, schema
				.getTopicTypes()));

	}

	@Test
	public void executeTestC1() {

		newTopicType.setName(newName);
		command = new CreateTopicTypeCommand(schema, newTopicType);
		Assert.assertTrue(command.canExecute());

		size = schema.getTopicTypes().size();
		list = new ArrayList<TopicType>(schema.getTopicTypes());

		command.execute();
		list.add(newTopicType);

		Assert.assertTrue((size + 1) == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(list, schema
				.getTopicTypes()));

	}

	@Test
	public void canUndoTestC0() {

		command = new CreateTopicTypeCommand(schema, newName);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1() {

		newTopicType.setName(newName);
		command = new CreateTopicTypeCommand(schema, newTopicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestC0() {

		command = new CreateTopicTypeCommand(schema, newName);
		Assert.assertTrue(command.canExecute());

		size = schema.getTopicTypes().size();
		list = new ArrayList<TopicType>(schema.getTopicTypes());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(list, schema
				.getTopicTypes()));

	}

	@Test
	public void undoTestC1() {

		newTopicType.setName(newName);
		command = new CreateTopicTypeCommand(schema, newTopicType);
		Assert.assertTrue(command.canExecute());

		size = schema.getTopicTypes().size();
		list = new ArrayList<TopicType>(schema.getTopicTypes());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(list, schema
				.getTopicTypes()));

	}

	@Test
	public void redoTestC0() {

		command = new CreateTopicTypeCommand(schema, newName);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = schema.getTopicTypes().size();
		list = new ArrayList<TopicType>(schema.getTopicTypes());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(list, schema
				.getTopicTypes()));

	}

	@Test
	public void redoTestC1() {

		newTopicType.setName(newName);
		command = new CreateTopicTypeCommand(schema, newTopicType);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = schema.getTopicTypes().size();
		list = new ArrayList<TopicType>(schema.getTopicTypes());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(list, schema
				.getTopicTypes()));

	}

}