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

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.AddBendpointCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddBendpointCommandTest {

	private Edge edge;
	private AddBendpointCommand addBendpointCommand;

	@Before
	public void prepare() {

		if (edge == null)
			edge = ModelFactory.eINSTANCE.createEdge();

		if (addBendpointCommand == null)
			addBendpointCommand = new AddBendpointCommand(edge, 0, 1, 1);

	}

	@After
	public void shutdown() {

		edge = null;
		addBendpointCommand = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(addBendpointCommand.canExecute());

	}

	@Test
	public void executeTest() {

		// int size = edge.getBendpoints().size();

		addBendpointCommand.execute();
		// Assert.assertEquals(edge.getBendpoints().size(), (size + 1));

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
