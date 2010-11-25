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
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.commands.SetDatatypeCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetDatatypeCommandTest {

	private SetDatatypeCommand command;
	private OccurrenceType type;
	private String newString;
	private String oldString;

	@Before
	public void prepare() {

		if (oldString == null)
			oldString = "old";

		if (newString == null)
			newString = "new";

		if (type == null) {

			type = ModelFactory.eINSTANCE.createOccurrenceType();
			type.setDataType(oldString);

		}

		if (command == null)
			command = new SetDatatypeCommand(type, newString);

	}

	@After
	public void shutdown() {

		type = null;
		oldString = null;
		newString = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		// old == null , new != null
		type.setDataType(null);
		command = new SetDatatypeCommand(type, newString);
		Assert.assertTrue(command.canExecute());

		// old != null , new == null
		type.setDataType(oldString);
		command = new SetDatatypeCommand(type, null);
		Assert.assertTrue(command.canExecute());

		// old == null , new == null
		type.setDataType(null);
		command = new SetDatatypeCommand(type, null);
		Assert.assertFalse(command.canExecute());

		// old = new
		type.setDataType(oldString);
		command = new SetDatatypeCommand(type, oldString);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(type.getDataType().equals(newString));

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
		Assert.assertTrue(type.getDataType().equals(oldString));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertTrue(type.getDataType().equals(newString));

	}

}
