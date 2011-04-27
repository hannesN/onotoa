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

import org.eclipse.swt.graphics.Point;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.CreateCommentCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CreateCommentCommandTest {

	private CreateCommentCommand command;
	private Diagram diagram;
	private Comment comment;
	private org.eclipse.swt.graphics.Point location;
	private List<Comment> list;
	private int posX = 1;
	private int posY = 1;
	private int size;

	@Before
	public void prepare() {

		if (location == null)
			location = new Point(posX, posY);

		if (diagram == null)
			diagram = ModelFactory.eINSTANCE.createDiagram();

		if (comment == null) {

			comment = ModelFactory.eINSTANCE.createComment();
			comment.setPosX(posX);
			comment.setPosY(posY);

		}

		if (command == null)
			command = new CreateCommentCommand(diagram, comment, location);

	}

	@After
	public void shutdown() {

		location = null;
		diagram = null;
		comment = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		size = diagram.getComments().size();
		list = new ArrayList<Comment>(diagram.getComments());

		command.execute();
		comment.setId(diagram.getComments().get(size).getId());
		list.add(comment);

		Assert.assertTrue((size + 1) == diagram.getComments().size());
		Assert
				.assertTrue(Tools.commentListCompare(list, diagram
						.getComments()));
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

		Assert.assertTrue(size == diagram.getComments().size());
		Assert
				.assertTrue(Tools.commentListCompare(list, diagram
						.getComments()));

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
		Assert
				.assertTrue(Tools.commentListCompare(list, diagram
						.getComments()));

	}

}
