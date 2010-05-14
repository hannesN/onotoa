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
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.DeleteRolePlayerConstraintCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class DeleteRolePlayerConstraintCommandTest {

	private DeleteRolePlayerConstraintCommand command;
	private RoleConstraint toDeleteRoleConstraint;
	private RolePlayerConstraint rolePlayerConstraint0;
	private RolePlayerConstraint rolePlayerConstraint1;
	private AssociationTypeConstraint associationTypeConstraint;
	private File file;
	private Edge edge0;
	private Edge edge1;
	private EdgeType edgeType;
	private Diagram diagram0;
	private Diagram diagram1;
	private TopicMapSchema schema;
	private List<RolePlayerConstraint> rpcList;
	private List<Edge> edgesList0;
	private List<Edge> edgesList1;
	private AssociationNode associationNode0;
	private AssociationNode associationNode1;
	private int rpcSize;
	private int edgesSize0;
	private int edgesSize1;

	@Before
	public void prepare() {

		if (rolePlayerConstraint0 == null) {

			rolePlayerConstraint0 = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();
			rolePlayerConstraint0.setRole(toDeleteRoleConstraint);

		}

		if (rolePlayerConstraint1 == null) {

			rolePlayerConstraint1 = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();
			rolePlayerConstraint1.setRole(toDeleteRoleConstraint);

		}

		if (associationTypeConstraint == null) {

			associationTypeConstraint = ModelFactory.eINSTANCE
					.createAssociationTypeConstraint();
			// associationTypeConstraint.setType(associationType);
			associationTypeConstraint.getPlayerConstraints().add(
					rolePlayerConstraint0);
			associationTypeConstraint.getPlayerConstraints().add(
					rolePlayerConstraint1);

		}

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getAssociationTypeConstraints().add(
					associationTypeConstraint);

		}

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

		if (associationNode0 == null) {

			associationNode0 = ModelFactory.eINSTANCE.createAssociationNode();
			associationNode0
					.setAssociationConstraint(associationTypeConstraint);

		}

		if (associationNode1 == null) {

			associationNode1 = ModelFactory.eINSTANCE.createAssociationNode();
			associationNode1
					.setAssociationConstraint(associationTypeConstraint);

		}

		if (diagram0 == null) {

			diagram0 = ModelFactory.eINSTANCE.createDiagram();
			diagram0.getEdges().add(edge0);
			diagram0.getNodes().add(associationNode0);

		}

		if (diagram1 == null) {

			diagram1 = ModelFactory.eINSTANCE.createDiagram();
			diagram1.getEdges().add(edge1);
			diagram1.getNodes().add(associationNode1);

		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);
			file.getDiagrams().add(diagram0);
			file.getDiagrams().add(diagram1);

		}

		ModelIndexer.createInstance(file);

		if (command == null)
			command = new DeleteRolePlayerConstraintCommand(
					associationTypeConstraint, rolePlayerConstraint0);

	}
	
	@After
	public void shutdown(){
		
		file = null;
		schema = null;
		diagram0 = null;
		diagram1 = null;
		toDeleteRoleConstraint = null;
		associationNode0 = null;
		associationNode1 = null;
		associationTypeConstraint = null;
		rolePlayerConstraint0 = null;
		rolePlayerConstraint1 = null;
		edge0 = null;
		edge1 = null;
		edgesList0 = null;
		edgesList1 = null;
		command = null;

		
	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		// clone diagrams edges 0
		edgesSize0 = diagram0.getEdges().size();
		edgesList0 = new ArrayList<Edge>(diagram0.getEdges());
		edgesList0.remove(edge0);

		// clone diagrams edges 1
		edgesSize1 = diagram1.getEdges().size();
		edgesList1 = new ArrayList<Edge>(diagram1.getEdges());
		edgesList1.remove(edge1);

		// clone associationTypeConstraints rpc
		rpcSize = associationTypeConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationTypeConstraint
				.getPlayerConstraints());
		rpcList.remove(rolePlayerConstraint0);

		command.execute();

		// check diagrams edges 0
		Assert.assertTrue((edgesSize0 - 1) == diagram0.getEdges().size());
		Assert.assertTrue(Tools
				.edgeListCompare(edgesList0, diagram0.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue((edgesSize1 - 1) == diagram1.getEdges().size());
		Assert.assertTrue(Tools
				.edgeListCompare(edgesList1, diagram1.getEdges()));

		// check associationTypeConstraints rpc
		Assert.assertTrue((rpcSize - 1) == associationTypeConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint.getPlayerConstraints()));

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

		// clone diagrams edges 0
		edgesSize0 = diagram0.getEdges().size();
		edgesList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edges 1
		edgesSize1 = diagram1.getEdges().size();
		edgesList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone associationTypeConstraints rpc
		rpcSize = associationTypeConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationTypeConstraint
				.getPlayerConstraints());

		command.execute();

		Assert.assertTrue(command.canUndo());
		command.undo();

		System.out.println("original: "
				+ associationTypeConstraint.getPlayerConstraints().indexOf(
						rolePlayerConstraint0));
		System.out
				.println("rpcList: " + rpcList.indexOf(rolePlayerConstraint0));

		// check diagrams edges 0
		Assert.assertTrue(edgesSize0 == diagram0.getEdges().size());
		Assert.assertTrue(Tools
				.edgeListCompare(edgesList0, diagram0.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgesSize1 == diagram1.getEdges().size());
		Assert.assertTrue(Tools
				.edgeListCompare(edgesList1, diagram1.getEdges()));

		// check associationTypeConstraints rpc
		Assert.assertTrue(rpcSize == associationTypeConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint.getPlayerConstraints()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone diagrams edges 0
		edgesSize0 = diagram0.getEdges().size();
		edgesList0 = new ArrayList<Edge>(diagram0.getEdges());

		// clone diagrams edges 1
		edgesSize1 = diagram1.getEdges().size();
		edgesList1 = new ArrayList<Edge>(diagram1.getEdges());

		// clone associationTypeConstraints rpc
		rpcSize = associationTypeConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationTypeConstraint
				.getPlayerConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		System.out.println("original: "
				+ associationTypeConstraint.getPlayerConstraints().indexOf(
						rolePlayerConstraint0));
		System.out
				.println("rpcList: " + rpcList.indexOf(rolePlayerConstraint0));

		// check diagrams edges 0
		Assert.assertTrue(edgesSize0 == diagram0.getEdges().size());
		Assert.assertTrue(Tools
				.edgeListCompare(edgesList0, diagram0.getEdges()));

		// check diagrams edges 1
		Assert.assertTrue(edgesSize1 == diagram1.getEdges().size());
		Assert.assertTrue(Tools
				.edgeListCompare(edgesList1, diagram1.getEdges()));

		// check associationTypeConstraints rpc
		Assert.assertTrue(rpcSize == associationTypeConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationTypeConstraint.getPlayerConstraints()));

	}

}
