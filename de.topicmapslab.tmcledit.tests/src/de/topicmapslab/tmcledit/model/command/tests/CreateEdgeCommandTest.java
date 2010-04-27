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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.CreateEdgeCommand;

public class CreateEdgeCommandTest {

	private Edge newEdge;
	private Diagram diagram;
	private TopicType topicType;
	private EdgeType edgeType;
	private boolean hasRoleConstraint = false;
	private final boolean DEBUG = true;
	private String typeOfEdge = "AKO_TYPE";
	private CreateEdgeCommand command;
	private int constructor = 1;
	private int edgeTypeSwitch = 1;
	private int size = 0;
	private RolePlayerConstraint rpc;
	private Node source;
	private TypeNode target;

	@Before
	public void prepare() {

		if (typeOfEdge.equals("AKO_TYPE"))
			edgeType = EdgeType.AKO_TYPE;

		if (typeOfEdge.equals("IS_ATYPE"))
			edgeType = EdgeType.IS_ATYPE;

		if (typeOfEdge.equals("ROLE_CONSTRAINT_TYPE"))
			edgeType = EdgeType.ROLE_CONSTRAINT_TYPE;

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			TopicType tt1 = ModelFactory.eINSTANCE.createTopicType();
			topicType.getAko().add(tt1);
		}

		if (source == null)
			source = ModelFactory.eINSTANCE.createNode();

		if (target == null) {

			target = ModelFactory.eINSTANCE.createTypeNode();
			target.setTopicType(topicType);

		}

		newEdge = ModelFactory.eINSTANCE.createEdge();
		newEdge.setSource(source);
		newEdge.setTarget(target);
		newEdge.setType(edgeType);

		if (rpc == null && hasRoleConstraint == true)
			rpc = ModelFactory.eINSTANCE.createRolePlayerConstraint();

		if (hasRoleConstraint == true)
			newEdge.setRoleConstraint(rpc);

		diagram = ModelFactory.eINSTANCE.createDiagram();
		diagram.getEdges().add(newEdge);

		// impossible constructor
		// if (command == null && constructor == 1)
		// command = new CreateEdgeCommand(newEdge);

		if (constructor == 1) {

			if (DEBUG)
				System.out.println("constructor = " + constructor
						+ " , hasRoleConstraint = " + hasRoleConstraint
						+ " , edgeType = " + newEdge.getType());
			command = new CreateEdgeCommand(newEdge, diagram);

		}
		if (constructor == 2) {

			if (DEBUG)
				System.out.println("constructor = " + constructor
						+ " , hasRoleConstraint = " + hasRoleConstraint
						+ " , edgeType = " + newEdge.getType());
			command = new CreateEdgeCommand(newEdge, diagram, true);

		}
		if (constructor == 3) {

			if (DEBUG)
				System.out.println("constructor = " + constructor
						+ " , hasRoleConstraint = " + hasRoleConstraint
						+ " , edgeType = " + newEdge.getType());
			command = new CreateEdgeCommand(newEdge, diagram, false);

		}

	}

	@After
	public void shutdown() {

		topicType = null;
		typeOfEdge = null;
		edgeType = null;
		typeOfEdge = null;
		rpc = null;
		newEdge = null;
		source = null;
		target = null;
		diagram = null;
		command = null;

	}

//	@Test
//	public void canExecute() {
//
//		while (constructor == 1) {
//
//			Assert.assertTrue(command.canExecute());
//
//			if (edgeTypeSwitch % 3 == 1)
//				typeOfEdge = "IS_ATYPE";
//
//			if (edgeTypeSwitch % 3 == 2)
//				typeOfEdge = "ROLE_CONSTRAINT_TYPE";
//
//			if (edgeTypeSwitch == 3) {
//				typeOfEdge = "AKO_TYPE";
//				hasRoleConstraint = true;
//			}
//
//			if (edgeTypeSwitch == 6) {
//
//				hasRoleConstraint = false;
//				constructor++;
//				edgeTypeSwitch = 1;
//				break;
//
//			}
//			prepare();
//			edgeTypeSwitch++;
//		}
//		prepare();
//
//		while (constructor == 2) {
//
//			Assert.assertTrue(command.canExecute());
//
//			if (edgeTypeSwitch % 3 == 1)
//				typeOfEdge = "IS_ATYPE";
//
//			if (edgeTypeSwitch % 3 == 2)
//				typeOfEdge = "ROLE_CONSTRAINT_TYPE";
//
//			if (edgeTypeSwitch == 3) {
//
//				typeOfEdge = "AKO_TYPE";
//				hasRoleConstraint = true;
//
//			}
//			if (edgeTypeSwitch == 6) {
//
//				hasRoleConstraint = false;
//				constructor++;
//				edgeTypeSwitch = 1;
//				break;
//
//			}
//			prepare();
//			edgeTypeSwitch++;
//		}
//		prepare();
//
//		while (constructor == 3) {
//
//			Assert.assertTrue(command.canExecute());
//
//			if (edgeTypeSwitch % 3 == 1)
//				typeOfEdge = "IS_ATYPE";
//
//			if (edgeTypeSwitch % 3 == 2)
//				typeOfEdge = "ROLE_CONSTRAINT_TYPE";
//
//			if (edgeTypeSwitch == 3) {
//
//				typeOfEdge = "AKO_TYPE";
//				hasRoleConstraint = true;
//
//			}
//			if (edgeTypeSwitch == 6) {
//
//				hasRoleConstraint = false;
//				constructor++;
//				edgeTypeSwitch = 1;
//				break;
//
//			}
//			prepare();
//			edgeTypeSwitch++;
//		}
//
//		constructor = 1;
//		hasRoleConstraint = false;
//		edgeTypeSwitch = 1;
//
//	}

	@Test
	public void executeTest() {

		while (constructor == 1) {

			Assert.assertTrue(command.canExecute());
			command.execute();

			if (edgeTypeSwitch % 3 == 1)
				typeOfEdge = "IS_ATYPE";

			if (edgeTypeSwitch % 3 == 2)
				typeOfEdge = "ROLE_CONSTRAINT_TYPE";

			if (edgeTypeSwitch == 3) {
				typeOfEdge = "AKO_TYPE";
				hasRoleConstraint = true;
			}

			if (edgeTypeSwitch == 6) {

				hasRoleConstraint = false;
				constructor++;
				edgeTypeSwitch = 1;
				break;

			}
			prepare();
			edgeTypeSwitch++;
		}

	}

	//
	// private Diagram diagram;
	// private Edge edge;
	// private final boolean editType;
	// private Command cmd;
	//
	// public CreateEdgeCommand(Edge newEdge) {
	// this(newEdge, null, true);
	// }
	//
	// public CreateEdgeCommand(Edge newEdge, Diagram diagram) {
	// this(newEdge, diagram, true);
	// }
	//
	// public CreateEdgeCommand(Edge newEdge, Diagram diagram, boolean editType)
	// {
	// edge = newEdge;
	// this.diagram = diagram;
	// this.editType = editType;
	// }
	//
	// public void setSource(Node source) {
	// edge.setSource(source);
	// }
	//
	// public void setTarget(Node target) {
	// edge.setTarget(target);
	// }
	//
	// public void setDiagram(Diagram diagram) {
	// this.diagram = diagram;
	// }
	//
	// @Override
	// public boolean canExecute() {
	// return prepare();
	// }
	//
	// @Override
	// protected boolean prepare() {
	// if ((edge.getTarget() != null) && (edge.getSource() != null)
	// && (diagram != null)) {
	// if (edge.getRoleConstraint() != null) {
	// edge.getRoleConstraint().setPlayer(
	// ((TypeNode) edge.getTarget()).getTopicType());
	// } else {
	//
	// }
	// } else {
	// return false;
	// }
	// return true;
	// }
	//
	// public void execute() {
	// redo();
	// }
	//
	// public Edge getEdge() {
	// return edge;
	// }
	//
	// @Override
	// public void undo() {
	// if (editType) {
	// switch (edge.getType()) {
	// case AKO_TYPE:
	// case IS_ATYPE:
	// cmd.undo();
	// break;
	// case ROLE_CONSTRAINT_TYPE:
	// ((AssociationNode) edge.getSource()).getAssociationConstraint()
	// .getPlayerConstraints()
	// .remove(edge.getRoleConstraint());
	// diagram.getEdges().remove(edge);
	// break;
	//
	// }
	// }
	// diagram.getEdges().remove(edge);
	// }
	//
	// public void redo() {
	// if (editType) {
	// switch (edge.getType()) {
	// case AKO_TYPE:
	// case IS_ATYPE:
	// cmd.redo();
	// break;
	// case ROLE_CONSTRAINT_TYPE:
	// ((AssociationNode) edge.getSource()).getAssociationConstraint()
	// .getPlayerConstraints().add(edge.getRoleConstraint());
	// diagram.getEdges().add(edge);
	// break;
	//
	// }
	// }
	// diagram.getEdges().add(edge);
	//
	// }

}
