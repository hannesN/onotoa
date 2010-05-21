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
import de.topicmapslab.tmcledit.model.commands.RemovePrefixMappingCommand;

public class RemovePrefixMappingCommandTest {

	private RemovePrefixMappingCommand command;
	private MappingElement mappingElement0;
	private MappingElement mappingElement1;
	private TopicMapSchema schema;
	private List<MappingElement> list;
	private int size;

	@Before
	public void prepare() {

		if (mappingElement0 == null)
			mappingElement0 = ModelFactory.eINSTANCE.createMappingElement();

		if (mappingElement1 == null)
			mappingElement1 = ModelFactory.eINSTANCE.createMappingElement();

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getMappings().add(mappingElement0);
			schema.getMappings().add(mappingElement1);

		}

		if (command == null)
			command = new RemovePrefixMappingCommand(schema, mappingElement0);

	}

	@After
	public void shutdown() {

		mappingElement0 = null;
		mappingElement1 = null;
		schema = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());
		schema.getMappings().remove(mappingElement0);
		command = new RemovePrefixMappingCommand(schema, mappingElement0);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		size = schema.getMappings().size();
		list = new ArrayList<MappingElement>(schema.getMappings());
		list.remove(mappingElement0);

		command.execute();

		Assert.assertTrue((size - 1) == schema.getMappings().size());
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
		command.canUndo();

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
		command.canUndo();
		command.redo();

		Assert.assertTrue(size == schema.getMappings().size());
		Assert.assertTrue(Tools.mappingElementListCompare(list, schema
				.getMappings()));

	}

}
