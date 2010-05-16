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
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.AddTopicRoleCommand;
import de.topicmapslab.tmcledit.model.compare.TopicTypeComparator;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class AddTopicRoleCommandTest {

	private AddTopicRoleCommand command;
	private RolePlayerConstraint rpc;
	private AssociationTypeConstraint atc;
	private TopicType player;
	private AssociationNode source;
	private TypeNode target;
	private TopicMapSchema schema;
	private Diagram diagram;
	private File file;
	private EdgeType edgeType;
	private Edge edge0;
	private List<RolePlayerConstraint> rpcList;
	private List<Edge> edgeList;
	private int rpcSize;
	private int edgeSize;

	TopicTypeComparator comp;

	@Before
	public void prepare() {

		if (rpc == null)
			rpc = ModelFactory.eINSTANCE.createRolePlayerConstraint();

		if (atc == null)
			atc = ModelFactory.eINSTANCE.createAssociationTypeConstraint();

		if (player == null)
			player = ModelFactory.eINSTANCE.createTopicType();

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getAssociationTypeConstraints().add(atc);

		}

		if (source == null) {

			source = ModelFactory.eINSTANCE.createAssociationNode();
			source.setAssociationConstraint(atc);

		}

		if (target == null) {

			target = ModelFactory.eINSTANCE.createTypeNode();
			target.setTopicType(player);

		}

		if (edgeType == null)
			edgeType = EdgeType.ROLE_CONSTRAINT_TYPE;

		if (edge0 == null) {

			edge0 = ModelFactory.eINSTANCE.createEdge();
			edge0.setSource(source);
			edge0.setTarget(target);
			edge0.setType(edgeType);
			edge0.setRoleConstraint(rpc);

		}

		if (diagram == null) {

			diagram = ModelFactory.eINSTANCE.createDiagram();
			diagram.getNodes().add(source);
			diagram.getNodes().add(target);

		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.getDiagrams().add(diagram);
			file.setTopicMapSchema(schema);

		}

		if (command == null)
			command = new AddTopicRoleCommand(rpc, atc, player);

		ModelIndexer.createInstance(file);

	}

	@After
	public void shutdown() {

		rpc = null;
		atc = null;
		player = null;
		file = null;
		schema = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		schema.getAssociationTypeConstraints().remove(atc);
		command = new AddTopicRoleCommand(rpc, atc, player);
		Assert.assertFalse(command.canExecute());

		schema.getAssociationTypeConstraints().add(atc);
		file.setTopicMapSchema(null);
		command = new AddTopicRoleCommand(rpc, atc, player);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		// clone atcs rpc
		rpcSize = atc.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(atc
				.getPlayerConstraints());
		rpcList.add(rpc);

		// clone diagrams edges
		edgeSize = diagram.getEdges().size();
		edgeList = new ArrayList<Edge>(diagram.getEdges());

		command.execute();

		int id = diagram.getEdges().get(0).getId();
		edge0.setId(id);
		edgeList.add(edge0);

		// check atcs rpc
		Assert.assertTrue((rpcSize + 1) == atc.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList, atc
				.getPlayerConstraints()));

		// check diagrams edges
		Assert.assertTrue((edgeSize + 1) == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgeList, diagram.getEdges()));

		// check rpcs player
		Assert.assertTrue(Tools.topicTypeCompare(player, rpc.getPlayer()));


		// test with already existing edge
		
		// clone diagrams edges
		edgeSize = diagram.getEdges().size();
		edgeList = new ArrayList<Edge>(diagram.getEdges());
		
		command.execute();
		
		// check diagrams edges
		Assert.assertTrue(edgeSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgeList, diagram.getEdges()));

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

		// clone atcs rpc
		rpcSize = atc.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(atc
				.getPlayerConstraints());

		// clone diagrams edges
		edgeSize = diagram.getEdges().size();
		edgeList = new ArrayList<Edge>(diagram.getEdges());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check atcs rpc
		Assert.assertTrue(rpcSize == atc.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList, atc
				.getPlayerConstraints()));

		// check diagrams edges
		Assert.assertTrue(edgeSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgeList, diagram.getEdges()));

		// check rpcs player
		Assert.assertFalse(Tools.topicTypeCompare(player, rpc.getPlayer()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone atcs rpc
		rpcSize = atc.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(atc
				.getPlayerConstraints());

		// clone diagrams edges
		edgeSize = diagram.getEdges().size();
		edgeList = new ArrayList<Edge>(diagram.getEdges());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check atcs rpc
		Assert.assertTrue(rpcSize == atc.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList, atc
				.getPlayerConstraints()));

		// check diagrams edges
		Assert.assertTrue(edgeSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgeList, diagram.getEdges()));

		// check rpcs player
		Assert.assertTrue(Tools.topicTypeCompare(player, rpc.getPlayer()));

	}

}
