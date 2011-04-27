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
import de.topicmapslab.tmcledit.model.ItemIdentifierConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateItemIdentifierConstraintCommand;

public class CreateItemIdentifierConstraintCommandTest {

	private CreateItemIdentifierConstraintCommand command;
	private TopicType topicType;
	private ItemIdentifierConstraint itemIdentifierConstraint;
	private List<ItemIdentifierConstraint> list;
	private int size;
	private int id;

	@Before
	public void prepare() {

		if (topicType == null)
			topicType = ModelFactory.eINSTANCE.createTopicType();

		if (itemIdentifierConstraint == null)
			itemIdentifierConstraint = ModelFactory.eINSTANCE
					.createItemIdentifierConstraint();

	}

	@After
	public void shutdown() {

		topicType = null;
		itemIdentifierConstraint = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestC0() {

		command = new CreateItemIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1() {

		command = new CreateItemIdentifierConstraintCommand(topicType,
				itemIdentifierConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestC0() {

		command = new CreateItemIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

		size = topicType.getItemIdentifierConstraints().size();
		list = new ArrayList<ItemIdentifierConstraint>(topicType
				.getItemIdentifierConstraints());

		command.execute();
		id = topicType.getItemIdentifierConstraints().get(size).getId();
		itemIdentifierConstraint.setId(id);
		list.add(itemIdentifierConstraint);

		Assert.assertTrue((size + 1) == topicType
				.getItemIdentifierConstraints().size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(list,
				topicType.getItemIdentifierConstraints()));
	}

	@Test
	public void executeTestC1() {

		command = new CreateItemIdentifierConstraintCommand(topicType,
				itemIdentifierConstraint);
		Assert.assertTrue(command.canExecute());

		size = topicType.getItemIdentifierConstraints().size();
		list = new ArrayList<ItemIdentifierConstraint>(topicType
				.getItemIdentifierConstraints());
		list.add(itemIdentifierConstraint);

		command.execute();

		Assert.assertTrue((size + 1) == topicType
				.getItemIdentifierConstraints().size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(list,
				topicType.getItemIdentifierConstraints()));

	}

	@Test
	public void canUndoTestC0() {

		command = new CreateItemIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1() {

		command = new CreateItemIdentifierConstraintCommand(topicType,
				itemIdentifierConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestC0() {

		command = new CreateItemIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

		size = topicType.getItemIdentifierConstraints().size();
		list = new ArrayList<ItemIdentifierConstraint>(topicType
				.getItemIdentifierConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == topicType.getItemIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(list,
				topicType.getItemIdentifierConstraints()));
	}

	@Test
	public void undoTestC1() {

		command = new CreateItemIdentifierConstraintCommand(topicType,
				itemIdentifierConstraint);
		Assert.assertTrue(command.canExecute());

		size = topicType.getItemIdentifierConstraints().size();
		list = new ArrayList<ItemIdentifierConstraint>(topicType
				.getItemIdentifierConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == topicType.getItemIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(list,
				topicType.getItemIdentifierConstraints()));

	}

	@Test
	public void redoTestC0() {

		command = new CreateItemIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getItemIdentifierConstraints().size();
		list = new ArrayList<ItemIdentifierConstraint>(topicType
				.getItemIdentifierConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getItemIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(list,
				topicType.getItemIdentifierConstraints()));
	}

	@Test
	public void redoTestC1() {

		command = new CreateItemIdentifierConstraintCommand(topicType,
				itemIdentifierConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getItemIdentifierConstraints().size();
		list = new ArrayList<ItemIdentifierConstraint>(topicType
				.getItemIdentifierConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getItemIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.itemIdentifierConstraintListCompare(list,
				topicType.getItemIdentifierConstraints()));

	}

}
