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
/**
 * 
 */
package de.topicmapslab.tmcledit.model.command.tests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.graphics.Point;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.CreateEdgeCommand;
import de.topicmapslab.tmcledit.model.commands.CreateNodeCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CreateNodeCommandTest {

	public enum Type {
		ASSOCIATION, TYPE
	}

	private CreateNodeCommand command;
	private Type type;
	private TopicType topicType;
	private Node node;
	private TypeNode typeNode;
	private Diagram diagram;
	private Point point;
	private File file;
	private TopicMapSchema schema;
	private List<CreateEdgeCommand> edgeCommands = Collections.emptyList();
	private List<Node> nodeList;
	private List<TopicType> topicTypeList;
	private boolean createdNewType = false;
	private boolean addType;
	private int topicTypeSize;
	private int nodeSize;

	@Before
	public void prepare() {

		if (diagram == null)
			diagram = ModelFactory.eINSTANCE.createDiagram();

		if (topicType == null)
			topicType = ModelFactory.eINSTANCE.createTopicType();

		if (schema == null)
			schema = ModelFactory.eINSTANCE.createTopicMapSchema();

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);
			file.getDiagrams().add(diagram);

		}

		ModelIndexer.createInstance(file);

	}

	@After
	public void shutdown() {

		type = null;
		node = null;
		typeNode = null;
		diagram = null;
		point = null;
		edgeCommands = null;
		command = null;

	}

	@Test
	public void canExecuteC0TypeNodeNoContainer() {

		typeNode = ModelFactory.eINSTANCE.createTypeNode();
		typeNode.setTopicType(topicType);

		command = new CreateNodeCommand(diagram, typeNode);
		Assert.assertTrue(command.canExecute());
		// createNewType = true

	}

	@Test
	public void canExecuteC0TypeNodeContainer() {

		schema.getTopicTypes().add(topicType);
		typeNode = ModelFactory.eINSTANCE.createTypeNode();
		typeNode.setTopicType(topicType);

		command = new CreateNodeCommand(diagram, typeNode);
		Assert.assertTrue(command.canExecute());
		// createNewType = false
		
		schema.getTopicTypes().add(topicType);
		topicType.getIsa();
		typeNode = ModelFactory.eINSTANCE.createTypeNode();
		typeNode.setTopicType(topicType);

		command = new CreateNodeCommand(diagram, typeNode);
		Assert.assertTrue(command.canExecute());
		// createNewType = false


	}

	@Test
	public void executeC0TypeNodeNoContainer() {

		typeNode = ModelFactory.eINSTANCE.createTypeNode();
		typeNode.setTopicType(topicType);

		command = new CreateNodeCommand(diagram, typeNode);
		Assert.assertTrue(command.canExecute());

		// clone of topicTypes list from schema
		topicTypeSize = file.getTopicMapSchema().getTopicTypes().size();
		topicTypeList = new ArrayList<TopicType>(file.getTopicMapSchema()
				.getTopicTypes());
		topicTypeList.add(topicType);

		// clone of nodes list from diagram
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());
		nodeList.add(typeNode);

		command.execute();

		// test topicTypes list from schema
		Assert.assertTrue((topicTypeSize + 1) == file.getTopicMapSchema()
				.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(topicTypeList, file
				.getTopicMapSchema().getTopicTypes()));

		// test nodes list from diagram
		Assert.assertTrue((nodeSize + 1) == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

	}

	@Test
	public void canUndoC0TypeNodeNoContainer() {

		typeNode = ModelFactory.eINSTANCE.createTypeNode();
		typeNode.setTopicType(topicType);

		command = new CreateNodeCommand(diagram, typeNode);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoC0TypeNodeNoContainer() {

		typeNode = ModelFactory.eINSTANCE.createTypeNode();
		typeNode.setTopicType(topicType);

		command = new CreateNodeCommand(diagram, typeNode);
		Assert.assertTrue(command.canExecute());

		// clone of topicTypes list from schema
		topicTypeSize = file.getTopicMapSchema().getTopicTypes().size();
		topicTypeList = new ArrayList<TopicType>(file.getTopicMapSchema()
				.getTopicTypes());

		// clone of nodes list from diagram
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		// test topicTypes list from schema
		Assert.assertTrue(topicTypeSize == file.getTopicMapSchema()
				.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(topicTypeList, file
				.getTopicMapSchema().getTopicTypes()));

		// test nodes list from diagram
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

	}

	@Test
	public void redoC0TypeNodeNoContainer() {

		typeNode = ModelFactory.eINSTANCE.createTypeNode();
		typeNode.setTopicType(topicType);

		command = new CreateNodeCommand(diagram, typeNode);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// clone of topicTypes list from schema
		topicTypeSize = file.getTopicMapSchema().getTopicTypes().size();
		topicTypeList = new ArrayList<TopicType>(file.getTopicMapSchema()
				.getTopicTypes());

		// clone of nodes list from diagram
		nodeSize = diagram.getNodes().size();
		nodeList = new ArrayList<Node>(diagram.getNodes());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		// test topicTypes list from schema
		Assert.assertTrue(topicTypeSize == file.getTopicMapSchema()
				.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(topicTypeList, file
				.getTopicMapSchema().getTopicTypes()));

		// test nodes list from diagram
		Assert.assertTrue(nodeSize == diagram.getNodes().size());
		Assert.assertTrue(Tools.nodeListCompare(nodeList, diagram.getNodes()));

	}

	//	
	//	
	// public CreateNodeCommandTest(Diagram diagram, Node node) {
	// if (node instanceof TypeNode) {
	// this.type = Type.TYPE;
	// } else {
	// this.type = Type.ASSOCIATION;
	// }
	//
	// this.diagram = diagram;
	//
	// this.node = node;
	// }
	//
	// public CreateNodeCommandTest(Diagram diagram, Point location, Node node)
	// {
	// this(diagram, node);
	//
	// node.setPosX(location.x);
	// node.setPosY(location.y);
	// }
	//
	// @Override
	// public boolean canExecute() {
	// if (prepare()) {
	// if ((node instanceof TypeNode) && (!createdNewType)) {
	// TopicType tt = ((TypeNode) node).getTopicType();
	// for (Node n : diagram.getNodes()) {
	// if (n instanceof TypeNode) {
	// if (((TypeNode) n).getTopicType().equals(tt))
	// return false;
	// }
	// }
	// } else if (node instanceof AssociationNode) {
	// AssociationTypeConstraint atc = ((AssociationNode)
	// node).getAssociationConstraint();
	// if (atc != null) {
	// if (ModelIndexer.getNodeIndexer().getNodeFor(atc, diagram)!=null)
	// return false;
	// TopicType at = atc.getType();
	// if ((at!=null) && (at.eContainer()==null))
	// addType = true;
	// }
	// }
	//			
	// return true;
	// }
	// return false;
	// }
	//
	// public void execute() {
	// switch (type) {
	// case TYPE:
	// if (createdNewType) {
	// TopicType tt = ((TypeNode) node).getTopicType();
	// File file = (File) diagram.eContainer();
	// file.getTopicMapSchema().getTopicTypes().add(tt);
	// }
	// break;
	// case ASSOCIATION:
	// if (createdNewType) {
	// AssociationTypeConstraint atc =
	// ((AssociationNode)node).getAssociationConstraint();
	// File file = (File) diagram.eContainer();
	//				
	// if (addType)
	// file.getTopicMapSchema().getTopicTypes().add(atc.getType());
	//				
	// file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);
	// }
	// }
	// diagram.getNodes().add(node);
	// for (CreateEdgeCommand cmd : edgeCommands) {
	// if (cmd.canExecute())
	// cmd.execute();
	// }
	// }
	//
	// @Override
	// protected boolean prepare() {
	// if (isPrepared)
	// return true;
	//		
	// TopicTypeNodeIndexer nodeIndexer = ModelIndexer.getNodeIndexer();
	//		
	// switch (type) {
	// case TYPE:
	// TopicType topicType = ((TypeNode) node).getTopicType();
	// if (topicType.eContainer() == null) {
	// createdNewType = true;
	// } else {
	//				
	// TopicIndexer topicIndexer = ModelIndexer.getTopicIndexer();
	// createIsAEdges(topicType, nodeIndexer, topicIndexer);
	// createAkOEdges(topicType, nodeIndexer, topicIndexer);
	//				
	// for (RolePlayerConstraint rpc :
	// ModelIndexer.getAssociationIndexer().getRolePlayerConstraintsFor(topicType))
	// {
	// AssociationTypeConstraint atc = (AssociationTypeConstraint)
	// rpc.eContainer();
	// Node node2 = nodeIndexer.getNodeFor(atc, diagram);
	// if (node2!=null) {
	// createRPEdge(node2, node, rpc);
	// }
	// }
	// }
	// break;
	// case ASSOCIATION:
	// AssociationNode associationNode = (AssociationNode) node;
	// if (associationNode.getAssociationConstraint()
	// .eContainer() == null) {
	// createdNewType = true;
	// } else {
	// AssociationTypeConstraint atc =
	// associationNode.getAssociationConstraint();
	// if (atc!=null) {
	// for (RolePlayerConstraint rpc : atc.getPlayerConstraints()) {
	// TopicType player = rpc.getPlayer();
	// if (player==null)
	// continue;
	//						
	// Node pnode = nodeIndexer.getNodeFor(player, diagram);
	// if (pnode==null)
	// continue;
	// createRPEdge(node, pnode, rpc);
	// }
	//					
	// }
	//					
	//				
	// }
	// }
	// isPrepared = true;
	// return true;
	// }
	//
	// private void createRPEdge(Node node1, Node node2, RolePlayerConstraint
	// rpc) {
	// Edge edge = createEdge(node1, node2, EdgeType.ROLE_CONSTRAINT_TYPE);
	// edge.setRoleConstraint(rpc);
	// CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram, false);
	// addEdgeCommand(cmd);
	// }
	//	
	// private void createAkOEdges(TopicType topicType, TopicTypeNodeIndexer
	// nodeIndexer, TopicIndexer topicIndexer) {
	// for(TopicType tt : topicType.getAko()) {
	// Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	// if (node2!=null) {
	// Edge edge = createEdge(node, node2, EdgeType.AKO_TYPE);
	// CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram, false);
	// addEdgeCommand(cmd);
	// }
	// }
	// for(TopicType tt : topicIndexer.getUsedAsAko(topicType)) {
	// Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	// if (node2!=null) {
	// Edge edge = createEdge(node2, node, EdgeType.AKO_TYPE);
	// CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram, false);
	// addEdgeCommand(cmd);
	// }
	// }
	// }
	//
	// private void createIsAEdges(TopicType topicType, TopicTypeNodeIndexer
	// nodeIndexer, TopicIndexer topicIndexer) {
	// for(TopicType tt : topicType.getIsa()) {
	// Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	// if (node2!=null) {
	// Edge edge = createEdge(node, node2, EdgeType.IS_ATYPE);
	// CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram, false);
	// addEdgeCommand(cmd);
	// }
	// }
	// for(TopicType tt : topicIndexer.getUsedAsIsa(topicType) ) {
	// Node node2 = nodeIndexer.getNodeFor(tt, diagram);
	// if (node2!=null) {
	// Edge edge = createEdge(node2, node, EdgeType.IS_ATYPE);
	// CreateEdgeCommand cmd = new CreateEdgeCommand(edge, diagram, false);
	// addEdgeCommand(cmd);
	// }
	// }
	// }
	//
	// private Edge createEdge(Node node1, Node node2, EdgeType type) {
	// Edge edge = ModelFactory.eINSTANCE.createEdge();
	// edge.setSource(node1);
	// edge.setTarget(node2);
	// edge.setType(type);
	// return edge;
	// }
	//
	// public void redo() {
	// switch (type) {
	// case TYPE:
	// if (createdNewType) {
	// TopicType tt = ((TypeNode) node).getTopicType();
	// File file = (File) diagram.eContainer();
	// file.getTopicMapSchema().getTopicTypes().add(tt);
	// }
	// break;
	// case ASSOCIATION:
	// if (createdNewType) {
	// AssociationTypeConstraint atc =
	// ((AssociationNode)node).getAssociationConstraint();
	// File file = (File) diagram.eContainer();
	// if (addType) {
	// file.getTopicMapSchema().getTopicTypes().add(atc.getType());
	// }
	// file.getTopicMapSchema().getAssociationTypeConstraints().add(atc);
	// }
	// }
	// diagram.getNodes().add(node);
	// for (CreateEdgeCommand cmd : edgeCommands) {
	// cmd.redo();
	// }
	// }
	//
	// @Override
	// public void undo() {
	// for (CreateEdgeCommand cmd : edgeCommands) {
	// cmd.undo();
	// }
	// diagram.getNodes().remove(node);
	// switch (type) {
	// case TYPE:
	// if (createdNewType) {
	// TopicType tt = ((TypeNode) node).getTopicType();
	// File file = (File) diagram.eContainer();
	// file.getTopicMapSchema().getTopicTypes().remove(tt);
	// }
	//			
	// break;
	// case ASSOCIATION:
	// if (createdNewType) {
	// AssociationTypeConstraint atc =
	// ((AssociationNode)node).getAssociationConstraint();
	// File file = (File) diagram.eContainer();
	// if (addType)
	// file.getTopicMapSchema().getTopicTypes().add(atc.getType());
	// file.getTopicMapSchema().getAssociationTypeConstraints().remove(atc);
	// }
	// }
	//		
	// }
	//
	// @Override
	// public String getLabel() {
	// return (type==Type.TYPE) ? "Create Type Node" :
	// "Create Association Node";
	// }
	//	
	// private void addEdgeCommand(CreateEdgeCommand cmd) {
	// if (edgeCommands==Collections.EMPTY_LIST)
	// edgeCommands = new ArrayList<CreateEdgeCommand>();
	//		
	// edgeCommands.add(cmd);
	// }
}
