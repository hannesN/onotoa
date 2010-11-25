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

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.RemoveBendpointCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveBendpointCommandTest {

	private RemoveBendpointCommand command;
	private Edge edge;
	private int index = 0;
	private Bendpoint bendpoint0;
	private Bendpoint bendpoint1;
	private List<Bendpoint> list;
	private int size;

	@Before
	public void prepare() {

		if (bendpoint0 == null)
			bendpoint0 = ModelFactory.eINSTANCE.createBendpoint();

		if (bendpoint1 == null)
			bendpoint1 = ModelFactory.eINSTANCE.createBendpoint();

		if (edge == null) {

			edge = ModelFactory.eINSTANCE.createEdge();
			edge.getBendpoints().add(bendpoint0);
			edge.getBendpoints().add(bendpoint1);
		}

		if (command == null)
			command = new RemoveBendpointCommand(edge, index);
	}

	@After
	public void shutdown() {

		bendpoint0 = null;
		bendpoint1 = null;
		edge = null;
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

		size = edge.getBendpoints().size();
		list = new ArrayList<Bendpoint>(edge.getBendpoints());
		list.remove(index);

		command.execute();

		Assert.assertTrue((size - 1) == edge.getBendpoints().size());
		Assert.assertTrue(Tools
				.bendpointListCompare(list, edge.getBendpoints()));

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

		size = edge.getBendpoints().size();
		list = new ArrayList<Bendpoint>(edge.getBendpoints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == edge.getBendpoints().size());
		Assert.assertTrue(Tools
				.bendpointListCompare(list, edge.getBendpoints()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		size = edge.getBendpoints().size();
		list = new ArrayList<Bendpoint>(edge.getBendpoints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == edge.getBendpoints().size());
		Assert.assertTrue(Tools
				.bendpointListCompare(list, edge.getBendpoints()));
		
	}

}
