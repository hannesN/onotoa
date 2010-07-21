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

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.ResizeCommentCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ResizeCommentCommandTest {

	private ResizeCommentCommand command;
	private Comment comment;

	private int newX = 2;
	private int newY = 2;
	private int newW = 200;
	private int newH = 200;

	private int oldX = 1;
	private int oldY = 1;
	private int oldW = 100;
	private int oldH = 100;

	@Before
	public void prepare() {

		if (comment == null) {

			comment = ModelFactory.eINSTANCE.createComment();
			comment.setPosX(oldX);
			comment.setPosY(oldY);
			comment.setWidth(oldW);
			comment.setHeight(oldH);

		}

		if (command == null)
			command = new ResizeCommentCommand(comment, newX, newY, newW, newH);
	}

	@After
	public void shutdown() {

		comment = null;
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

		Assert.assertEquals(newX, comment.getPosX());
		Assert.assertEquals(newY, comment.getPosY());
		Assert.assertEquals(newW, comment.getWidth());
		Assert.assertEquals(newW, comment.getHeight());

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

		Assert.assertEquals(oldX, comment.getPosX());
		Assert.assertEquals(oldY, comment.getPosY());
		Assert.assertEquals(oldW, comment.getWidth());
		Assert.assertEquals(oldW, comment.getHeight());

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertEquals(newX, comment.getPosX());
		Assert.assertEquals(newY, comment.getPosY());
		Assert.assertEquals(newW, comment.getWidth());
		Assert.assertEquals(newW, comment.getHeight());

	}

}