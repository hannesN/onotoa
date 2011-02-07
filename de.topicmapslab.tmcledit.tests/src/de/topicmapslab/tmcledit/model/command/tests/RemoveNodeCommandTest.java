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

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.RemoveNodeCommand;

public class RemoveNodeCommandTest {

	private RemoveNodeCommand command;
	private Node node0;
	private Node node1;
	private Diagram diagram;
	private Edge edgeWithSource;
	private Edge edgeWithTarget;
	private Edge edgeWithout;
	private List<Edge> edgeList;
	private List<Node> nodeList;
	private int edgeSize;
	private int nodeSize;

	@Before
	public void prepare() {

		if (node0 == null)
			node0 = ModelFactory.eINSTANCE.createNode();

		if (node1 == null)
			node1 = ModelFactory.eINSTANCE.createNode();

		if (edgeWithSource == null) {

			edgeWithSource = ModelFactory.eINSTANCE.createEdge();
			edgeWithSource.setSource(node0);
			edgeWithSource.setTarget(node1);

		}

		if (edgeWithTarget == null) {

			edgeWithTarget = ModelFactory.eINSTANCE.createEdge();
			edgeWithTarget.setTarget(node0);
			edgeWithTarget.setSource(node1);

		}

		if (edgeWithout == null) {

			edgeWithout = ModelFactory.eINSTANCE.createEdge();
			edgeWithout.setSource(node1);
			edgeWithout.setTarget(node1);

		}

		if (diagram == null) {

			diagram = ModelFactory.eINSTANCE.createDiagram();
			diagram.getNodes().add(node0);
			diagram.getNodes().add(node1);
			diagram.getEdges().add(edgeWithSource);
			diagram.getEdges().add(edgeWithTarget);
			diagram.getEdges().add(edgeWithout);

		}

		if (command == null)
			command = new RemoveNodeCommand(diagram, node0);

	}

	@After
	public void shutdown() {

		node0 = null;
		node1 = null;
		edgeWithSource = null;
		edgeWithTarget = null;
		edgeWithout = null;
		edgeList = null;
		nodeList = null;
		diagram = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {
		//
		// if (diagram.getEdges().get(0).getSource().equals(node0))
		// System.out.println("lala");
		// if (diagram.getEdges().get(1).getTarget().equals(node0))
		// System.out.println("lala");
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		// copy diagrams edges
		edgeSize = diagram.getEdges().size();
		edgeList = new ArrayList<Edge>(diagram.getEdges());
		edgeList.remove(edgeWithSource);
		edgeList.remove(edgeWithTarget);

		// copy diagrams nodes
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());
		nodeList.remove(node0);

		command.execute();

		// check diagrams edges
		Assert.assertTrue((edgeSize - 2) == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgeList, diagram.getEdges()));

		// check diagrams nodes
		Assert.assertTrue((nodeSize - 1) == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

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

		// copy diagrams edges
		edgeSize = diagram.getEdges().size();
		edgeList = new ArrayList<Edge>(diagram.getEdges());

		// copy diagrams nodes
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check diagrams edges
		Assert.assertTrue(edgeSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgeList, diagram.getEdges()));

		// check diagrams nodes
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		// copy diagrams edges
		edgeSize = diagram.getEdges().size();
		edgeList = new ArrayList<Edge>(diagram.getEdges());

		// copy diagrams nodes
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());
		nodeList.remove(node0);

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check diagrams edges
		Assert.assertTrue(edgeSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgeList, diagram.getEdges()));

		// check diagrams nodes
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

	}

}
