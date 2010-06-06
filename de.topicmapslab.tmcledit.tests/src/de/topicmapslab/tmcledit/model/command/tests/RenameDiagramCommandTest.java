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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.RenameDiagramCommand;

public class RenameDiagramCommandTest {

	private RenameDiagramCommand command;
	private String newName;
	private String oldName;
	private Diagram diagram;

	@Before
	public void prepare() {

		if (oldName == null)
			oldName = "oldName";

		if (newName == null)
			newName = "newName";

		if (diagram == null) {

			diagram = ModelFactory.eINSTANCE.createDiagram();
			diagram.setName(oldName);

		}

		if (command == null)
			command = new RenameDiagramCommand(newName, diagram);
	}

	@After
	public void shutdown() {

		newName = null;
		diagram = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		diagram.setName(newName);
		command = new RenameDiagramCommand(newName, diagram);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertEquals(newName, diagram.getName());

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
		Assert.assertEquals(oldName, diagram.getName());

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertEquals(newName, diagram.getName());

	}

}
