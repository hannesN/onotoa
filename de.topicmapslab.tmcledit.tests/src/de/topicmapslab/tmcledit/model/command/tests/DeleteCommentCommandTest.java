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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.DeleteCommentCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteCommentCommandTest {

	private DeleteCommentCommand command;
	private Comment comment0;
	private Comment comment1;
	private Diagram diagram;
	private List<Comment> list;
	private int size;

	@Before
	public void prepare() {

		if (comment0 == null) 
			comment0 = ModelFactory.eINSTANCE.createComment();
		
		if (comment1 == null) 
			comment1 = ModelFactory.eINSTANCE.createComment();

		if (diagram == null) {

			diagram = ModelFactory.eINSTANCE.createDiagram();
			diagram.getComments().add(comment0);
			diagram.getComments().add(comment1);

		}

		if (command == null)
			command = new DeleteCommentCommand(comment0);
	}

	@After
	public void shudown() {

		list = null;
		diagram = null;
		comment0 = null;
		comment1 = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		list = new ArrayList<Comment>(diagram.getComments());
		size = diagram.getComments().size();
		Assert.assertTrue(diagram.getComments().contains(comment0));

		command.execute();

		Assert.assertTrue((size - 1) == diagram.getComments().size());
		Assert.assertFalse(diagram.getComments().contains(comment0));
		list.remove(comment0);
		Assert.assertTrue(Tools
				.commentListCompare(list, diagram.getComments()));

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		Assert.assertTrue(command.canExecute());

		size = diagram.getComments().size();
		list = new ArrayList<Comment>(diagram.getComments());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(diagram.getComments().contains(comment0));
		Assert.assertTrue(size == diagram.getComments().size());
		Assert.assertTrue(Tools
				.commentListCompare(list, diagram.getComments()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		
		size = diagram.getComments().size();
		list = new ArrayList<Comment>(diagram.getComments());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		
		Assert.assertTrue(size == diagram.getComments().size());
		Assert.assertFalse(diagram.getComments().contains(comment0));
		Assert.assertTrue(Tools.commentListCompare(list, diagram.getComments()));
		
	}

}
