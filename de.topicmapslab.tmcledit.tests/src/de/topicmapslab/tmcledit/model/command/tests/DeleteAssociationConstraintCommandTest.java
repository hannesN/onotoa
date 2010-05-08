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
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.commands.DeleteAssociationConstraintCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

public class DeleteAssociationConstraintCommandTest implements Cloneable {

	private DeleteAssociationConstraintCommand command;
	private AssociationTypeConstraint associationConstraint;
	private RolePlayerConstraint playerConstraint;
	private AssociationNode associationNode;
	private File file;
	private Diagram diagram;
	private TopicMapSchema schema;
	private List<AssociationTypeConstraint> atcList;
	private List<Node> nodeList;
	private List<RolePlayerConstraint> rpcList;
	private int atcSize;
	private int nodeSize;
	private int rpcSize;

	@Before
	public void prepare() {

		if (associationNode == null)
			associationNode = ModelFactory.eINSTANCE.createAssociationNode();

		if (playerConstraint == null)
			playerConstraint = ModelFactory.eINSTANCE
					.createRolePlayerConstraint();

		if (associationConstraint == null)
			associationConstraint = ModelFactory.eINSTANCE
					.createAssociationTypeConstraint();

		if (schema == null)
			schema = ModelFactory.eINSTANCE.createTopicMapSchema();

		if (diagram == null)
			diagram = ModelFactory.eINSTANCE.createDiagram();

		if (file == null)
			file = ModelFactory.eINSTANCE.createFile();

	}

	@After
	public void shutdown() {

		schema = null;
		diagram = null;
		file = null;
		associationConstraint = null;
		playerConstraint = null;
		atcList = null;
		rpcList = null;
		nodeList = null;
		command = null;

	}

	@Test
	public void canExecuteTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationConstraint);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestHasPlayer() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestHasPlayerAndAssNode() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);
		associationNode.setAssociationConstraint(associationConstraint);
		diagram.getNodes().add(associationNode);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationConstraint);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);
		Assert.assertTrue(command.canExecute());

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		command.execute();
		atcList.remove(associationConstraint);

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue((atcSize - 1) == schema
				.getAssociationTypeConstraints().size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void executeTestHasPlayer() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		command.execute();
		rpcList.remove(playerConstraint);
		atcList.remove(associationConstraint);

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue((atcSize - 1) == schema
				.getAssociationTypeConstraints().size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue((rpcSize - 1) == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void executeTestHasPlayerAndAssNode() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);
		associationNode.setAssociationConstraint(associationConstraint);
		diagram.getNodes().add(associationNode);

		System.out.println(diagram.getNodes().size());

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		command.execute();
		nodeList.remove(associationNode);
		rpcList.remove(playerConstraint);
		atcList.remove(associationConstraint);

		// check diagrams node list
		Assert.assertTrue((nodeSize - 1) == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue((atcSize - 1) == schema
				.getAssociationTypeConstraints().size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue((rpcSize - 1) == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void canUndoTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationConstraint);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);
		command = new DeleteAssociationConstraintCommand(associationConstraint);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestHasPlayer() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);
		ModelIndexer.createInstance(file);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestHasPlayerAndAssNode() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);
		associationNode.setAssociationConstraint(associationConstraint);
		diagram.getNodes().add(associationNode);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);
		ModelIndexer.createInstance(file);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationConstraint);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);
		Assert.assertTrue(command.canExecute());

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void undoTestHasPlayer() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void undoTestHasPlayerAndAssNode() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);
		associationNode.setAssociationConstraint(associationConstraint);
		diagram.getNodes().add(associationNode);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void redoTestNoPlayer() {

		schema.getAssociationTypeConstraints().add(associationConstraint);
		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void redoTestHasPlayer() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

	@Test
	public void redoTestHasPlayerAndAssNode() {

		associationConstraint.getPlayerConstraints().add(playerConstraint);
		schema.getAssociationTypeConstraints().add(associationConstraint);
		associationNode.setAssociationConstraint(associationConstraint);
		diagram.getNodes().add(associationNode);

		file.setTopicMapSchema(schema);
		file.getDiagrams().add(diagram);
		ModelIndexer.createInstance(file);

		command = new DeleteAssociationConstraintCommand(associationConstraint);

		ModelIndexer.createInstance(file);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone of diagrams node list
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		// clone of schemas atc list
		atcSize = schema.getAssociationTypeConstraints().size();
		atcList = new ArrayList<AssociationTypeConstraint>(schema
				.getAssociationTypeConstraints());

		// clone of atcs player list
		rpcSize = associationConstraint.getPlayerConstraints().size();
		rpcList = new ArrayList<RolePlayerConstraint>(associationConstraint
				.getPlayerConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// check diagrams node list
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

		// check schemas atc list
		Assert.assertTrue(atcSize == schema.getAssociationTypeConstraints()
				.size());
		Assert.assertTrue(Tools.associationTypeConstraintListCompare(atcList,
				schema.getAssociationTypeConstraints()));

		// check atcs player list
		Assert.assertTrue(rpcSize == associationConstraint
				.getPlayerConstraints().size());
		Assert.assertTrue(Tools.rolePlayerConstraintListCompare(rpcList,
				associationConstraint.getPlayerConstraints()));

	}

}
