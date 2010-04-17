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
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.DeleteCommentCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteCommentCommandTest {

	private Comment comment;
	private DeleteCommentCommand delCom;
	private Diagram diagram;
	private int size;

	@Before
	public void prepare() {

		if (comment == null) {

			comment = ModelFactory.eINSTANCE.createComment();
			comment.setContent("TMCL");

		}

		if (diagram == null) {

			diagram = ModelFactory.eINSTANCE.createDiagram();
			diagram.getComments().add(comment);

		}

		if (delCom == null)
			delCom = new DeleteCommentCommand(comment);
	}

	@After
	public void shudown() {

		diagram = null;
		comment = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(delCom.canExecute());

	}

	@Test
	public void executeTest() {

		size = diagram.getComments().size();
		Assert.assertTrue(diagram.getComments().contains(comment));
		delCom.execute();
		Assert.assertTrue((size - 1) == diagram.getComments().size());
		Assert.assertFalse(diagram.getComments().contains(comment));

	}

	@Test
	public void undoTest() {

		delCom.execute();

		size = diagram.getComments().size();
		Assert.assertFalse(diagram.getComments().contains(comment));
		delCom.undo();
		Assert.assertTrue((size + 1) == diagram.getComments().size());
		Assert.assertTrue(diagram.getComments().contains(comment));

	}

	@Test
	public void redoTest() {

		delCom.execute();
		delCom.undo();

		size = diagram.getComments().size();
		Assert.assertTrue(diagram.getComments().contains(comment));
		delCom.redo();
		Assert.assertTrue((size - 1) == diagram.getComments().size());
		Assert.assertFalse(diagram.getComments().contains(comment));

	}

}
