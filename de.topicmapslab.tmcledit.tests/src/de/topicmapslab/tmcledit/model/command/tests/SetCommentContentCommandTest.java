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
import de.topicmapslab.tmcledit.model.commands.SetCommentContentCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetCommentContentCommandTest {

	private SetCommentContentCommand command;
	private String newText;
	private String oldText;
	private Comment comment;

	@Before
	public void prepare() {

		if (oldText == null)
			oldText = "old";

		if (newText == null)
			newText = "new";

		if (comment == null) {

			comment = ModelFactory.eINSTANCE.createComment();
			comment.setContent(oldText);

		}

		if (command == null)
			command = new SetCommentContentCommand(comment, newText);
	}

	@After
	public void shutdown() {

		newText = null;
		oldText = null;
		comment = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		// old == null , new != null
		comment.setContent(null);
		command = new SetCommentContentCommand(comment, newText);
		Assert.assertTrue(command.canExecute());

		// old != null , new == null
		comment.setContent(oldText);
		command = new SetCommentContentCommand(comment, null);
		Assert.assertTrue(command.canExecute());

		// old == null , new == null
		comment.setContent(null);
		command = new SetCommentContentCommand(comment, null);
		Assert.assertFalse(command.canExecute());

		// old = new
		comment.setContent(oldText);
		command = new SetCommentContentCommand(comment, oldText);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(comment.getContent().equals(newText));

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
		Assert.assertTrue(comment.getContent().equals(oldText));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertTrue(comment.getContent().equals(newText));

	}

}
