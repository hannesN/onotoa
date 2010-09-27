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

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.CreatePrefixMappingCommand;

public class CreatePrefixMappingCommandTest {

	private CreatePrefixMappingCommand command;
	private String newKey = "newKey";
	private String newValue = "newValue";
	private TopicMapSchema schema;
	private MappingElement mappingElement;
	private List<MappingElement> list;
	private int size;
	private int id;

	@Before
	public void prepare() {

		if (schema == null)
			schema = ModelFactory.eINSTANCE.createTopicMapSchema();

		if (mappingElement == null) {

			mappingElement = ModelFactory.eINSTANCE.createMappingElement();
			mappingElement.setKey(newKey);
			mappingElement.setValue(newValue);

		}

		if (command == null)
			command = new CreatePrefixMappingCommand(schema, newKey, newValue);

	}

	@After
	public void shutdown() {

		schema = null;
		newKey = null;
		newValue = null;
		mappingElement = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		schema.getMappings().add(mappingElement);
		command = new CreatePrefixMappingCommand(schema, newKey, newValue);

		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		size = schema.getMappings().size();
		list = new ArrayList<MappingElement>(schema.getMappings());

		command.execute();

		id = schema.getMappings().get(size).getId();
		mappingElement.setId(id);
		list.add(mappingElement);

		Assert.assertTrue((size + 1) == schema.getMappings().size());
		Assert.assertTrue(Tools.mappingElementListCompare(list, schema
				.getMappings()));

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

		size = schema.getMappings().size();
		list = new ArrayList<MappingElement>(schema.getMappings());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == schema.getMappings().size());
		Assert.assertTrue(Tools.mappingElementListCompare(list, schema
				.getMappings()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		size = schema.getMappings().size();
		list = new ArrayList<MappingElement>(schema.getMappings());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == schema.getMappings().size());
		Assert.assertTrue(Tools.mappingElementListCompare(list, schema
				.getMappings()));

	}

}
