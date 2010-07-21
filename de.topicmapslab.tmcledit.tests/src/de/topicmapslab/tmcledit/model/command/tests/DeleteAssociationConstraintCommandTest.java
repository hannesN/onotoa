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

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.DeleteAssociationConstraintCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class DeleteAssociationConstraintCommandTest implements Cloneable {

	private DeleteAssociationConstraintCommand command;
	private AssociationTypeConstraint associationTypeConstraint0;
	private AssociationTypeConstraint associationTypeConstraint1;
	private RolePlayerConstraint rolePlayerConstraint0;
	private RolePlayerConstraint rolePlayerConstraint1;
	private AssociationNode associationNode0;
	private AssociationNode associationNode1;
	private AssociationNode associationNode2;
	private File file;
	private Diagram diagram0;
	private Diagram diagram1;
	private TopicMapSchema schema;
	private List<Edge> edgeList0;
	private List<Edge> edgeList1;
	private List<AssociationTypeConstraint> atcList;
	private List<Node> nodeList0;
	private List<Node> nodeList1;
	private List<RolePlayerConstraint> rpcList;
	private Edge edge0;
	private Edge edge1;
	private Edge edge2;
	private EdgeType edgeType;
	private int edgeSize0;
	private int edgeSize1;
	private int atcSize;
	private int nodeSize0;
	private int nodeSize1;
	private int rpcSize;

	@Before
	public void prepare() {

		if (associationNode0 == null)
			associationNode0 = ModelFactory.eINSTANCE.createAssociationNode();

		if (associationNode1 == null)
			associationNode1 = ModelFactory.eINSTANCE.createAssociationNode();

		if (associationNode2 == null)
			associationNode2 = ModelFactory.eINSTANCE.createAssociationNode();

		if (rolePlayerConstraint0 == null)
			rolePlayerConstraint0 = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();

		if (rolePlayerConstraint1 == null)
			rolePlayerConstraint1 = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();

		if (associationTypeConstraint0 == null)
			associationTypeConstraint0 = ModelFactory.eINSTANCE
					.createAssociationTypeConstraint();

		if (associationTypeConstraint1 == null)
			associationTypeConstraint1 = ModelFactory.eINSTANCE
					.createAssociationTypeConstraint();

		if (edgeType == null)
			edgeType = EdgeType.ROLE_CONSTRAINT_TYPE;

		if (edge0 == null) {

			edge0 = ModelFactory.eINSTANCE.createEdge();
			edge0.setType(edgeType);
			edge0.setRoleConstraint(rolePlayerConstraint0);

		}

		if (edge1 == null) {

			edge1 = ModelFactory.eINSTANCE.createEdge();
			edge1.setType(edgeType);
			edge1.setRoleConstraint(rolePlayerConstraint0);

		}

		if (edge2 == null)
			edge2 = ModelFactory.eINSTANCE.createEdge();

		if (schema == null)
			schema = ModelFactory.eINSTANCE.createTopicMapSchema();

		if (diagram0 == null) {

			diagram0 = ModelFactory.eINSTANCE.createDiagram();
			diagram0.getEdges().add(edge0);
			diagram0.getEdges().add(edge2);

		}

		if (diagram1 == null) {

			diagram1 = ModelFactory.eINSTANCE.createDiagram();
			diagram1.getEdges().add(edge1);

		}

		if (file == null)
			file = ModelFactory.eINSTANCE.createFile();

	}

	@After
	public void shutdown() {

		schema = null;
		diagram0 = null;
		diagram1 = null;
		file = null;
		associationTypeConstraint0 = null;
		associationTypeConstraint1 = null;
		associationNode0 = null;
		associationNode1 = null;
		associationNode2 = null;
		rolePlayerConstraint0 = null;
		rolePlayerConstraint1 = null;
		edge0 = null;
		edge1 = null;
		edge2 = null;
		edgeType = null;
		edgeList0 = null;
		edgeList1 = null;
		atcList = null;
		rpcList = null;
		nodeList0 = null;
		nodeList1 = null;
		command = null;

	}

	@Test
	public void canExecuteTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestHasPlayer() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestHasPlayerAndAssNode() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);

		associationNode0.setAssociationConstraint(associationTypeConstraint0);
		associationNode1.setAssociationConstraint(associationTypeConstraint0);
		diagram0.getNodes().add(associationNode0);
		diagram1.getNodes().add(associationNode1);
		diagram0.getNodes().add(associationNode2);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);
		Assert.assertTrue(command.canExecute());

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram1.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		command.execute();
		atcList.remove(associationTypeConstraint0);

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize1 == diagram1.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList1, diagram1
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue((atcSize - 1) == schema
				.getAssociationTypeConstraints().size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void executeTestHasPlayer() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram1.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		command.execute();
		rpcList.remove(rolePlayerConstraint0);
		rpcList.remove(rolePlayerConstraint1);
		atcList.remove(associationTypeConstraint0);

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize1 == diagram1.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList1, diagram1
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue((atcSize - 1) == schema
				.getAssociationTypeConstraints().size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue((rpcSize - 2) == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void executeTestHasPlayerAndAssNode() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		associationNode0.setAssociationConstraint(associationTypeConstraint0);
		associationNode1.setAssociationConstraint(associationTypeConstraint0);
		diagram0.getNodes().add(associationNode0);
		diagram1.getNodes().add(associationNode1);
		diagram0.getNodes().add(associationNode2);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram1.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		command.execute();
		edgeList0.remove(edge0);
		edgeList1.remove(edge1);
		nodeList0.remove(associationNode0);
		nodeList1.remove(associationNode1);
		rpcList.remove(rolePlayerConstraint0);
		rpcList.remove(rolePlayerConstraint1);
		atcList.remove(associationTypeConstraint0);

		// check diagrams edges 0
		Assert.assertTrue((edgeSize0 - 1) == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue((edgeSize1 - 1) == diagram1.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList1, diagram1
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue((nodeSize0 - 1) == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue((nodeSize1 - 1) == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue((atcSize - 1) == schema
				.getAssociationTypeConstraints().size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue((rpcSize - 2) == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void canUndoTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);
		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestHasPlayer() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);
		ModelIndexer.createInstance(file);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestHasPlayerAndAssNode() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);

		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		associationNode0.setAssociationConstraint(associationTypeConstraint0);
		associationNode1.setAssociationConstraint(associationTypeConstraint0);
		diagram0.getNodes().add(associationNode0);
		diagram1.getNodes().add(associationNode1);
		diagram0.getNodes().add(associationNode2);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);
		ModelIndexer.createInstance(file);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);
		Assert.assertTrue(command.canExecute());

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram1.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize1 == diagram1.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList1, diagram1
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void undoTestHasPlayer() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram1.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize1 == diagram1.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList1, diagram1
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void undoTestHasPlayerAndAssNode() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		associationNode0.setAssociationConstraint(associationTypeConstraint0);
		associationNode1.setAssociationConstraint(associationTypeConstraint0);
		diagram0.getNodes().add(associationNode0);
		diagram1.getNodes().add(associationNode1);
		diagram0.getNodes().add(associationNode2);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram1.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize1 == diagram1.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList1, diagram1
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void redoTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram0.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void redoTestHasPlayer() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram0.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

	@Test
	public void redoTestHasPlayerAndAssNode() {

		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint0);
		associationTypeConstraint0.getPlayerConstraints().add(
				rolePlayerConstraint1);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint0);
		schema.getAssociationTypeConstraints().add(associationTypeConstraint1);
		associationNode0.setAssociationConstraint(associationTypeConstraint0);
		associationNode1.setAssociationConstraint(associationTypeConstraint0);
		diagram0.getNodes().add(associationNode0);
		diagram1.getNodes().add(associationNode1);
		diagram0.getNodes().add(associationNode2);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram0);
		file.getDiagrams().add(diagram1);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(
				associationTypeConstraint0);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone diagrams edge list 0
		edgeSize0 = diagram0.getEdges().size();
		edgeList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edge list 1
		edgeSize1 = diagram0.getEdges().size();
		edgeList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone of diagrams node list 0
		nodeSize0 = diagram0.getNodes().size();
		nodeList0 = new ArrayList<Node>(diagram0.getNodes());

		// clone of diagrams node list 1
		nodeSize1 = diagram1.getNodes().size();
		nodeList1 = new ArrayList<Node>(diagram1.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationTypeConstraint0.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(
				associationTypeConstraint0.getPlayerConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check diagrams edges 0
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgeSize0 == diagram0.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeList0, diagram0
						.getEdges()));

		// check diagrams node list 0
		Assert.assertTrue(nodeSize0 == diagram0.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList0, diagram0
						.getNodes()));

		// check diagrams node list 1
		Assert.assertTrue(nodeSize1 == diagram1.getNodes().size());
		Assert
				.assertTrue(Tools.nodeListCompare(nodeList1, diagram1
						.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationTypeConstraint0
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint0.getPlayerConstraints()));

	}

}
