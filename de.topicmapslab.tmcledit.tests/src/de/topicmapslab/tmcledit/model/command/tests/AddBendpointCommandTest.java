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
import de.topicmapslab.tmcledit.model.commands.AddBendpointCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddBendpointCommandTest {

	private Edge edge;
	private AddBendpointCommand command;
	private Bendpoint bendpoint;
	private List<Bendpoint> list;
	private int index = 0;
	private int posX = 1;
	private int posY = 1;
	private int size;

	@Before
	public void prepare() {

		if (bendpoint == null) {

			bendpoint = ModelFactory.eINSTANCE.createBendpoint();
			bendpoint.setPosX(posX);
			bendpoint.setPosY(posY);

		}

		if (edge == null)
			edge = ModelFactory.eINSTANCE.createEdge();

		if (command == null)
			command = new AddBendpointCommand(edge, index, posX, posY);

	}

	@After
	public void shutdown() {

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

		size = edge.getBendpoints().size();
		list = new ArrayList<Bendpoint>(edge.getBendpoints());

		command.execute();
		bendpoint.setId(edge.getBendpoints().get(index).getId());
		list.add(bendpoint);

		Assert.assertTrue((size + 1) == edge.getBendpoints().size());
		Assert.assertTrue(Tools
				.bendpointListCompare(list, edge.getBendpoints()));

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

	// private final Edge edge;
	// private final int index;
	// private final int posX;
	// private final int posY;
	//	
	// private Bendpoint bendpoint;
	//
	// public AddBendpointCommandTest(Edge edge, int index, int posX, int posY)
	// {
	// super();
	// this.edge = edge;
	// this.index = index;
	// this.posX = posX;
	// this.posY = posY;
	// }
	//
	// public void execute() {
	// edge.getBendpoints().add(index, bendpoint);
	// }
	//
	// public void redo() {
	// execute();
	// }
	//	
	// @Override
	// public void undo() {
	// edge.getBendpoints().remove(index);
	// }
	//	
	//	
	// @Override
	// protected boolean prepare() {
	// bendpoint = ModelFactory.eINSTANCE.createBendpoint();
	// bendpoint.setPosX(posX);
	// bendpoint.setPosY(posY);
	//	    
	// return true;
	// }

}
