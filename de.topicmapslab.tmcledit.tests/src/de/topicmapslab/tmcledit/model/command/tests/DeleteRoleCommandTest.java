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
import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.DeleteRoleCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class DeleteRoleCommandTest {

	private DeleteRoleCommand command;
	private AssociationType associationType;
	private AssociationType associationTypeClone;
	private RoleConstraint toDeleteRoleConstraint;
	private RoleConstraint toDeleteRoleConstraintClone;
	private RoleConstraint existingRoleConstraint;
	private RoleConstraint existingRoleConstraintClone;
	private RolePlayerConstraint rolePlayerConstraint;
	private RolePlayerConstraint rolePlayerConstraintClone;
	private AssociationTypeConstraint associationTypeConstraint;
	private AssociationTypeConstraint associationTypeConstraintClone;
	private File file;
	private Edge edge;
	private Edge edgeClone;
	private EdgeType edgeType;
	private EdgeType edgeTypeClone;
	private Diagram diagram;
	private Diagram diagramClone;
	private TopicMapSchema schema;
	private List<RoleConstraint> roleConstraintList;
	private List<Edge> edgesList;
	private AssociationNode associationNode;
	private AssociationNode associationNodeClone;
	private boolean cascade;
	private int roleConstraintSize;
	private int edgesSize;

	@Before
	public void prepare() {

		if (existingRoleConstraint == null)
			existingRoleConstraint = ModelFactory.eINSTANCE
					.createRoleConstraint();

		if (toDeleteRoleConstraint == null)
			toDeleteRoleConstraint = ModelFactory.eINSTANCE
					.createRoleConstraint();

		if (existingRoleConstraintClone == null) {

			existingRoleConstraintClone = ModelFactory.eINSTANCE
					.createRoleConstraint();
			existingRoleConstraintClone.setId(existingRoleConstraint.getId());

		}

		if (toDeleteRoleConstraintClone == null) {

			toDeleteRoleConstraintClone = ModelFactory.eINSTANCE
					.createRoleConstraint();
			toDeleteRoleConstraintClone.setId(toDeleteRoleConstraint.getId());

		}

		if (associationType == null) {

			associationType = ModelFactory.eINSTANCE.createAssociationType();
			associationType.getRoles().add(existingRoleConstraint);
			associationType.getRoles().add(toDeleteRoleConstraint);

		}

		if (associationTypeClone == null) {

			associationTypeClone = ModelFactory.eINSTANCE
					.createAssociationType();
			associationTypeClone.setId(associationType.getId());
			associationTypeClone.getRoles().add(existingRoleConstraintClone);
			associationTypeClone.getRoles().add(toDeleteRoleConstraintClone);

		}

		if (rolePlayerConstraint == null) {

			rolePlayerConstraint = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();
			rolePlayerConstraint.setRole(toDeleteRoleConstraint);

		}

		if (rolePlayerConstraintClone == null) {

			rolePlayerConstraintClone = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();
			rolePlayerConstraintClone.setId(rolePlayerConstraint.getId());
			rolePlayerConstraintClone.setRole(toDeleteRoleConstraintClone);

		}

		if (associationTypeConstraint == null) {

			associationTypeConstraint = ModelFactory.eINSTANCE
					.createAssociationTypeConstraint();
			associationTypeConstraint.setType(associationType);
			associationTypeConstraint.getPlayerConstraints().add(
					rolePlayerConstraint);

		}

		if (associationTypeConstraintClone == null) {

			associationTypeConstraintClone = ModelFactory.eINSTANCE
					.createAssociationTypeConstraint();
			associationTypeConstraintClone.setId(associationTypeConstraint
					.getId());
			associationTypeConstraintClone.setType(associationTypeClone);
			associationTypeConstraintClone.getPlayerConstraints().add(
					rolePlayerConstraintClone);

		}

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getAssociationTypeConstraints().add(
					associationTypeConstraint);
			schema.getAssociationTypeConstraints().add(
					associationTypeConstraintClone);

		}

		if (edge == null) {

			edge = ModelFactory.eINSTANCE.createEdge();
			edgeType = EdgeType.ROLE_CONSTRAINT_TYPE;
			edge.setType(edgeType);
			edge.setRoleConstraint(rolePlayerConstraint);

		}

		if (edgeClone == null) {

			edgeClone = ModelFactory.eINSTANCE.createEdge();
			edgeClone.setId(edge.getId());
			edgeTypeClone = EdgeType.ROLE_CONSTRAINT_TYPE;
			edgeClone.setType(edgeTypeClone);
			edgeClone.setRoleConstraint(rolePlayerConstraintClone);

		}

		if (associationNode == null) {

			associationNode = ModelFactory.eINSTANCE.createAssociationNode();
			associationNode.setAssociationConstraint(associationTypeConstraint);

		}

		if (associationNodeClone == null) {

			associationNodeClone = ModelFactory.eINSTANCE
					.createAssociationNode();
			associationNodeClone.setId(associationNode.getId());
			associationNodeClone
					.setAssociationConstraint(associationTypeConstraintClone);

		}

		if (diagram == null) {

			diagram = ModelFactory.eINSTANCE.createDiagram();
			diagram.getEdges().add(edge);
			diagram.getNodes().add(associationNode);

		}

		if (diagramClone == null) {

			diagramClone = ModelFactory.eINSTANCE.createDiagram();
			diagramClone.setId(diagram.getId());
			diagramClone.getEdges().add(edgeClone);
			diagramClone.getNodes().add(associationNodeClone);

		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);
			file.getDiagrams().add(diagram);
			file.getDiagrams().add(diagramClone);

		}

		ModelIndexer.createInstance(file);

	}

	@After
	public void shutdown() {

		file = null;
		schema = null;
		diagram = null;
		diagramClone = null;
		existingRoleConstraint = null;
		existingRoleConstraintClone = null;
		toDeleteRoleConstraint = null;
		toDeleteRoleConstraintClone = null;
		associationType = null;
		associationTypeClone = null;
		associationNode = null;
		associationNodeClone = null;
		associationTypeConstraint = null;
		associationTypeConstraintClone = null;
		rolePlayerConstraint = null;
		rolePlayerConstraintClone = null;
		edge = null;
		edgeClone = null;
		roleConstraintList = null;
		edgesList = null;
		command = null;

	}

	@Test
	public void canExecuteTestNoCascade() {

		cascade = false;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestCascade() {

		cascade = true;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestNoCascade() {

		cascade = false;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		roleConstraintSize = associationType.getRoles().size();
		roleConstraintList = new ArrayList<RoleConstraint>(associationType
				.getRoles());
		roleConstraintList.remove(toDeleteRoleConstraint);

		// clone diagrams edges
		edgesSize = diagram.getEdges().size();
		edgesList = new ArrayList<Edge>(diagram.getEdges());

		command.execute();

		// check associationTypes roles
		Assert.assertTrue((roleConstraintSize - 1) == associationType
				.getRoles().size());
		Assert.assertTrue(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

		// check diagrams edges
		Assert.assertTrue(edgesSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgesList, diagram.getEdges()));

		roleConstraintList.add(toDeleteRoleConstraint);
		roleConstraintList.remove(existingRoleConstraint);

		Assert.assertFalse(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

	}

	@Test
	public void executeTestCascade() {

		cascade = true;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		roleConstraintSize = associationType.getRoles().size();
		roleConstraintList = new ArrayList<RoleConstraint>(associationType
				.getRoles());
		roleConstraintList.remove(toDeleteRoleConstraint);

		// clone diagrams edges
		edgesSize = diagram.getEdges().size();
		edgesList = new ArrayList<Edge>(diagram.getEdges());
		edgesList.remove(edge);

		command.execute();

		// check associationTypes roles
		Assert.assertTrue((roleConstraintSize - 1) == associationType
				.getRoles().size());
		Assert.assertTrue(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

		// check diagrams edges
		Assert.assertTrue((edgesSize - 1) == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgesList, diagram.getEdges()));

	}

	@Test
	public void canUndoTestNoCascade() {

		cascade = false;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestCascade() {

		cascade = true;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestNoCascade() {

		cascade = false;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		roleConstraintSize = associationType.getRoles().size();
		roleConstraintList = new ArrayList<RoleConstraint>(associationType
				.getRoles());

		// clone diagrams edges
		edgesSize = diagram.getEdges().size();
		edgesList = new ArrayList<Edge>(diagram.getEdges());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check associationTypes roles
		Assert.assertTrue(roleConstraintSize == associationType.getRoles()
				.size());
		Assert.assertTrue(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

		// check diagrams edges
		Assert.assertTrue(edgesSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgesList, diagram.getEdges()));

		roleConstraintList.remove(existingRoleConstraint);

		Assert.assertFalse(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

	}

	@Test
	public void undoTestCascade() {

		cascade = true;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());

		// clone associationTypes roles
		roleConstraintSize = associationType.getRoles().size();
		roleConstraintList = new ArrayList<RoleConstraint>(associationType
				.getRoles());

		// clone diagrams edges
		edgesSize = diagram.getEdges().size();
		edgesList = new ArrayList<Edge>(diagram.getEdges());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check associationTypes roles
		Assert.assertTrue(roleConstraintSize == associationType.getRoles()
				.size());
		Assert.assertTrue(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

		// check diagrams edges
		Assert.assertTrue(edgesSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgesList, diagram.getEdges()));

	}

	@Test
	public void redoTestNoCascade() {

		cascade = false;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());

		// clone diagrams edges
		edgesSize = diagram.getEdges().size();
		edgesList = new ArrayList<Edge>(diagram.getEdges());

		command.execute();

		// clone associationTypes roles
		roleConstraintSize = associationType.getRoles().size();
		roleConstraintList = new ArrayList<RoleConstraint>(associationType
				.getRoles());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check associationTypes roles
		Assert.assertTrue(roleConstraintSize == associationType.getRoles()
				.size());
		Assert.assertTrue(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

		// check diagrams edges
		Assert.assertTrue(edgesSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgesList, diagram.getEdges()));

	}

	@Test
	public void redoTestCascade() {

		cascade = true;
		command = new DeleteRoleCommand(associationType,
				toDeleteRoleConstraint, cascade);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone associationTypes roles
		roleConstraintSize = associationType.getRoles().size();
		roleConstraintList = new ArrayList<RoleConstraint>(associationType
				.getRoles());

		// clone diagrams edges
		edgesSize = diagram.getEdges().size();
		edgesList = new ArrayList<Edge>(diagram.getEdges());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check associationTypes roles
		Assert.assertTrue(roleConstraintSize == associationType.getRoles()
				.size());
		Assert.assertTrue(Tools.roleConstraintListCompare(roleConstraintList,
				associationType.getRoles()));

		// check diagrams edges
		Assert.assertTrue(edgesSize == diagram.getEdges().size());
		Assert.assertTrue(Tools.edgeListCompare(edgesList, diagram.getEdges()));

	}

}
