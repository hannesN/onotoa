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

import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.SetLabelPosCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetLabelPosCommandTest {

	private SetLabelPosCommand command;
	private LabelPos position;
	private int newPosX;
	private int newPosY;

	private int oldPosX;
	private int oldPosY;

	@Before
	public void prepare() {

		oldPosX = 0;
		oldPosY = 0;
		newPosX = 1;
		newPosY = 1;

		if (position == null) {

			position = ModelFactory.eINSTANCE.createLabelPos();
			position.setPosX(oldPosX);
			position.setPosY(oldPosY);

		}

		if (command == null)
			command = new SetLabelPosCommand(position, newPosX, newPosY);
	}

	@After
	public void shutdown() {

		position = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		command = new SetLabelPosCommand(position, oldPosX, oldPosY);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		Assert.assertTrue(position.getPosX() == newPosX);
		Assert.assertTrue(position.getPosY() == newPosY);

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

		Assert.assertTrue(position.getPosX() == oldPosX);
		Assert.assertTrue(position.getPosY() == oldPosY);

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(position.getPosX() == newPosX);
		Assert.assertTrue(position.getPosY() == newPosY);

	}

}
