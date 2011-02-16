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
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.MoveNodeCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class MoveNodeCommandTest {

	private MoveNodeCommand command;
	private Node node;
	private int newX = 0;
	private int newY = 0;
	private int oldX = 1;
	private int oldY = 1;

	@Before
	public void prepare() {

		if (node == null) {

			node = ModelFactory.eINSTANCE.createNode();
			node.setPosX(oldX);
			node.setPosY(oldY);
		}

		if (command == null)
			command = new MoveNodeCommand(node, newX, newY);
	}

	@After
	public void shutdown() {

		node = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertEquals(newX, node.getPosX());
		Assert.assertEquals(newY, node.getPosY());

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
		Assert.assertEquals(oldX, node.getPosX());
		Assert.assertEquals(oldY, node.getPosY());

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertEquals(newX, node.getPosX());
		Assert.assertEquals(newY, node.getPosY());

	}

}