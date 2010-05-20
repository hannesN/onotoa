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
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.CreateEdgeCommand;

public class CreateEdgeCommandTest {

	private Edge edge;
	private Diagram diagram;
	private TopicType topicType;
	private EdgeType edgeType;
	private boolean hasRoleConstraint = true;
	private final boolean DEBUG = false;
	private String typeOfEdge = "AKO_TYPE";
	private CreateEdgeCommand command;
	private List<Edge> list;
	private int constructor = 0;
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

		edge = ModelFactory.eINSTANCE.createEdge();
		edge.setId(111);
		edge.setSource(source);
		edge.setTarget(target);
		edge.setType(edgeType);

		if (rpc == null && hasRoleConstraint == true)
			rpc = ModelFactory.eINSTANCE.createRolePlayerConstraint();

		if (hasRoleConstraint == true)
			edge.setRoleConstraint(rpc);

		diagram = ModelFactory.eINSTANCE.createDiagram();

		if (constructor == 0) {

			if (DEBUG)
				System.out.println("constructor = " + constructor
						+ " , hasRoleConstraint = " + hasRoleConstraint
						+ " , edgeType = " + edge.getType());
			command = new CreateEdgeCommand(edge);
			command.setDiagram(diagram);

		}

		if (constructor == 1) {

			if (DEBUG)
				System.out.println("constructor = " + constructor
						+ " , hasRoleConstraint = " + hasRoleConstraint
						+ " , edgeType = " + edge.getType());
			command = new CreateEdgeCommand(edge, diagram);

		}
		if (constructor == 2) {

			if (DEBUG)
				System.out.println("constructor = " + constructor
						+ " , hasRoleConstraint = " + hasRoleConstraint
						+ " , edgeType = " + edge.getType());
			command = new CreateEdgeCommand(edge, diagram, true);

		}
		if (constructor == 3) {

			if (DEBUG)
				System.out.println("constructor = " + constructor
						+ " , hasRoleConstraint = " + hasRoleConstraint
						+ " , edgeType = " + edge.getType());
			command = new CreateEdgeCommand(edge, diagram, false);

		}

	}

	@After
	public void shutdown() {

		topicType = null;
		typeOfEdge = null;
		edgeType = null;
		typeOfEdge = null;
		rpc = null;
		edge = null;
		source = null;
		target = null;
		diagram = null;
		command = null;

	}

	 @Test
	 public void canExecuteAll() {
	
	 while (constructor == 0) {
	
	 Assert.assertTrue(command.canExecute());
	
	 if (edgeTypeSwitch % 3 == 1)
	 typeOfEdge = "IS_ATYPE";
	
	 if (edgeTypeSwitch % 3 == 2)
	 typeOfEdge = "ROLE_CONSTRAINT_TYPE";
	
	 if (edgeTypeSwitch == 3) {
	 typeOfEdge = "AKO_TYPE";
	 constructor++;
	 edgeTypeSwitch = 1;
	 break;
	
	 }
	
	 prepare();
	 edgeTypeSwitch++;
	 }
	 prepare();
	
	 while (constructor == 1) {
	
	 Assert.assertTrue(command.canExecute());
	
	 if (edgeTypeSwitch % 3 == 1)
	 typeOfEdge = "IS_ATYPE";
	
	 if (edgeTypeSwitch % 3 == 2)
	 typeOfEdge = "ROLE_CONSTRAINT_TYPE";
	
	 if (edgeTypeSwitch == 3) {
	 typeOfEdge = "AKO_TYPE";
	 constructor++;
	 edgeTypeSwitch = 1;
	 break;
	
	 }
	
	 prepare();
	 edgeTypeSwitch++;
	 }
	 prepare();
	
	 while (constructor == 2) {
	
	 Assert.assertTrue(command.canExecute());
	
	 if (edgeTypeSwitch % 3 == 1)
	 typeOfEdge = "IS_ATYPE";
	
	 if (edgeTypeSwitch % 3 == 2)
	 typeOfEdge = "ROLE_CONSTRAINT_TYPE";
	
	 if (edgeTypeSwitch == 3) {
	
	 typeOfEdge = "AKO_TYPE";
	 hasRoleConstraint = true;
	
	 }
	 if (edgeTypeSwitch == 3) {
	 typeOfEdge = "AKO_TYPE";
	 constructor++;
	 edgeTypeSwitch = 1;
	 break;
	
	 }
	
	 prepare();
	 edgeTypeSwitch++;
	 }
	
	 hasRoleConstraint = false;
	 prepare();
	
	 while (constructor == 3) {
	
	 Assert.assertTrue(command.canExecute());
	
	 if (edgeTypeSwitch % 3 == 1)
	 typeOfEdge = "IS_ATYPE";
	
	 if (edgeTypeSwitch % 3 == 2)
	 typeOfEdge = "ROLE_CONSTRAINT_TYPE";
	
	 if (edgeTypeSwitch == 3) {
	 typeOfEdge = "AKO_TYPE";
	 constructor++;
	 edgeTypeSwitch = 1;
	 break;
	
	 }
	
	 prepare();
	 edgeTypeSwitch++;
	 }
	
	 constructor = 0;
	 hasRoleConstraint = true;
	 edgeTypeSwitch = 1;
	
	 }

	@Test
	public void executeC0TrueTest() {

		try {
			while (constructor == 0) {

				Assert.assertTrue(command.canExecute());

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());
				list.add(edge);

				command.execute();

				Assert.assertTrue((size + 1) == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 1;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 1;

	}

	@Test
	public void executeC1TrueTest() {

		constructor = 1;
		prepare();

		try {
			while (constructor == 1) {

				Assert.assertTrue(command.canExecute());
				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());
				list.add(edge);

				command.execute();

				Assert.assertTrue((size + 1) == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 2;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 2;

	}

	@Test
	public void executeC2TrueTest() {

		constructor = 2;
		prepare();

		try {
			while (constructor == 2) {

				Assert.assertTrue(command.canExecute());

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());
				list.add(edge);

				command.execute();

				Assert.assertTrue((size + 1) == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 3;
			hasRoleConstraint = false;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 3;
		hasRoleConstraint = false;

	}

	@Test
	public void executeC3FalseTest() {

		constructor = 3;
		hasRoleConstraint = false;
		prepare();

		try {
			while (constructor == 3) {

				Assert.assertTrue(command.canExecute());

				size = diagram.getEdges().size();
				System.out.println("size " + size);
				System.out.println("diagram " + diagram.getEdges().size());
				list = new ArrayList<Edge>(diagram.getEdges());
				list.add(edge);

				command.execute();

				System.out.println("size " + size);
				System.out.println("diagram " + diagram.getEdges().size());

				Assert.assertTrue((size + 1) == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					constructor++;
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 0;

	}

	@Test
	public void canUndoC0TrueTest() {

		try {
			while (constructor == 0) {

				Assert.assertTrue(command.canExecute());
				command.execute();
				Assert.assertTrue(command.canUndo());

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 1;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 1;

	}

	@Test
	public void canUndoC1TrueTest() {

		constructor = 1;
		prepare();

		try {
			while (constructor == 1) {

				Assert.assertTrue(command.canExecute());
				command.execute();
				Assert.assertTrue(command.canUndo());

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 2;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 2;

	}

	@Test
	public void canUndoC2TrueTest() {

		constructor = 2;
		prepare();

		try {
			while (constructor == 2) {

				Assert.assertTrue(command.canExecute());
				command.execute();
				Assert.assertTrue(command.canUndo());

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 3;
			hasRoleConstraint = false;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 3;
		hasRoleConstraint = false;

	}

	@Test
	public void canUndoC3FalseTest() {

		constructor = 3;
		hasRoleConstraint = false;
		prepare();

		try {
			while (constructor == 3) {

				Assert.assertTrue(command.canExecute());
				command.execute();
				Assert.assertTrue(command.canUndo());

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					constructor++;
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 0;

	}

	@Test
	public void undoC0TrueTest() {

		try {
			while (constructor == 0) {

				Assert.assertTrue(command.canExecute());

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				command.execute();
				Assert.assertTrue(command.canUndo());
				command.undo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 1;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 1;

	}

	@Test
	public void undoC1TrueTest() {

		constructor = 1;
		prepare();

		try {
			while (constructor == 1) {

				Assert.assertTrue(command.canExecute());

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				command.execute();
				Assert.assertTrue(command.canUndo());
				command.undo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 2;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 2;

	}

	@Test
	public void undoC2TrueTest() {

		constructor = 2;
		prepare();

		try {
			while (constructor == 2) {

				Assert.assertTrue(command.canExecute());

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				command.execute();
				Assert.assertTrue(command.canUndo());
				command.undo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 3;
			hasRoleConstraint = false;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 3;
		hasRoleConstraint = false;

	}

	@Test
	public void undoC3FalseTest() {

		constructor = 3;
		hasRoleConstraint = false;
		prepare();

		try {
			while (constructor == 3) {

				Assert.assertTrue(command.canExecute());

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				command.execute();
				Assert.assertTrue(command.canUndo());
				command.undo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					constructor++;
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 0;

	}

	@Test
	public void redoC0TrueTest() {

		try {
			while (constructor == 0) {

				Assert.assertTrue(command.canExecute());
				command.execute();

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				Assert.assertTrue(command.canUndo());
				command.undo();
				command.redo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 1;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 1;

	}

	@Test
	public void redoC1TrueTest() {

		constructor = 1;
		prepare();

		try {
			while (constructor == 1) {

				Assert.assertTrue(command.canExecute());
				command.execute();

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				Assert.assertTrue(command.canUndo());
				command.undo();
				command.redo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 2;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 2;

	}

	@Test
	public void redoC2TrueTest() {

		constructor = 2;
		prepare();

		try {
			while (constructor == 2) {

				Assert.assertTrue(command.canExecute());
				command.execute();

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				Assert.assertTrue(command.canUndo());
				command.undo();
				command.redo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			constructor = 3;
			hasRoleConstraint = false;
			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 3;
		hasRoleConstraint = false;

	}

	@Test
	public void redoC3FalseTest() {

		constructor = 3;
		hasRoleConstraint = false;
		prepare();

		try {
			while (constructor == 3) {

				Assert.assertTrue(command.canExecute());
				command.execute();

				size = diagram.getEdges().size();
				list = new ArrayList<Edge>(diagram.getEdges());

				Assert.assertTrue(command.canUndo());
				command.undo();
				command.redo();

				Assert.assertTrue(size == diagram.getEdges().size());
				Assert.assertTrue(Tools.edgeListCompare(list, diagram
						.getEdges()));

				if (edgeTypeSwitch % 3 == 1)
					typeOfEdge = "IS_ATYPE";

				if (edgeTypeSwitch % 3 == 2)
					typeOfEdge = "ROLE_CONSTRAINT_TYPE";

				if (edgeTypeSwitch == 3) {

					typeOfEdge = "AKO_TYPE";
					constructor++;
					edgeTypeSwitch = 1;
					break;

				}

				prepare();
				edgeTypeSwitch++;

			}

		} catch (Exception e) {

			Assert.fail("Exception was thrown:" + e.getClass());
			e.printStackTrace();

		}

		constructor = 0;

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
