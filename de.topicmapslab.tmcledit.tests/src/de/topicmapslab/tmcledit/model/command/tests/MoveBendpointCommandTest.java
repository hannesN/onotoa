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

import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.MoveBendpointCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class MoveBendpointCommandTest {

	private MoveBendpointCommand command;
	private Edge edge;
	private int index = 0;
	private int posX;
	private int posY;
	private Bendpoint bendpoint;
	private int oldX;
	private int oldY;

	@Before
	public void prepare() {

		if (bendpoint == null) {

			bendpoint = ModelFactory.eINSTANCE.createBendpoint();
			bendpoint.setPosX(oldX);
			bendpoint.setPosY(oldY);

		}

		if (edge == null) {

			edge = ModelFactory.eINSTANCE.createEdge();
			edge.getBendpoints().add(bendpoint);

		}

		if (command == null)
			command = new MoveBendpointCommand(edge, index, posX, posY);

	}

	@After
	public void shutdown() {

		bendpoint = null;
		edge = null;
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
		Assert.assertEquals(posX, edge.getBendpoints().get(index).getPosX());
		Assert.assertEquals(posY, edge.getBendpoints().get(index).getPosY());

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
		command.undo();
		Assert.assertEquals(oldX, edge.getBendpoints().get(index).getPosX());
		Assert.assertEquals(oldY, edge.getBendpoints().get(index).getPosY());
	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertEquals(posX, edge.getBendpoints().get(index).getPosX());
		Assert.assertEquals(posY, edge.getBendpoints().get(index).getPosY());

	}

}
