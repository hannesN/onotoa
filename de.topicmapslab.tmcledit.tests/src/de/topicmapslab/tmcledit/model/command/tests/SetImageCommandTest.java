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
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.SetImageCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetImageCommandTest {

	private SetImageCommand command;
	private String newValue;
	private TopicType type;
	private TopicType dummyType;
	private String oldValue;
	private TypeNode node0DiaOne;
	private TypeNode node1DiaOne;
	private TypeNode dummyNodeDiaOne;
	private List<TypeNode> nodeListDiaOne;
	private List<TypeNode> nodeListDiaTwo;
	private TypeNode node0DiaTwo;
	private TypeNode node1DiaTwo;
	private TypeNode dummyNodeDiaTwo;
	private TopicMapSchema schema;
	private Diagram diaOne;
	private Diagram diaTwo;
	private File file;

	@Before
	public void prepare() {

		if (nodeListDiaOne == null)
			nodeListDiaOne = new ArrayList<TypeNode>();

		if (nodeListDiaTwo == null)
			nodeListDiaTwo = new ArrayList<TypeNode>();

		if (newValue == null)
			newValue = "newValue";

		if (oldValue == null)
			oldValue = "oldValue";

		if (dummyType == null)
			dummyType = ModelFactory.eINSTANCE.createTopicType();

		if (type == null)
			type = ModelFactory.eINSTANCE.createTopicType();

		if (node0DiaOne == null) {

			node0DiaOne = ModelFactory.eINSTANCE.createTypeNode();
			node0DiaOne.setTopicType(type);
			node0DiaOne.setImage(oldValue);

		}

		if (node1DiaOne == null) {

			node1DiaOne = ModelFactory.eINSTANCE.createTypeNode();
			node1DiaOne.setTopicType(type);
			node1DiaOne.setImage(oldValue);

		}

		if (dummyNodeDiaOne == null) {

			dummyNodeDiaOne = ModelFactory.eINSTANCE.createTypeNode();
			dummyNodeDiaOne.setTopicType(dummyType);
			dummyNodeDiaOne.setImage(oldValue);

		}

		if (node0DiaTwo == null) {

			node0DiaTwo = ModelFactory.eINSTANCE.createTypeNode();
			node0DiaTwo.setTopicType(type);
			node0DiaTwo.setImage(oldValue);

		}

		if (node1DiaTwo == null) {

			node1DiaTwo = ModelFactory.eINSTANCE.createTypeNode();
			node1DiaTwo.setTopicType(type);
			node1DiaTwo.setImage(oldValue);

		}

		if (dummyNodeDiaTwo == null) {

			dummyNodeDiaTwo = ModelFactory.eINSTANCE.createTypeNode();
			dummyNodeDiaTwo.setTopicType(dummyType);
			dummyNodeDiaTwo.setImage(oldValue);

		}

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getTopicTypes().add(type);
			schema.getTopicTypes().add(dummyType);

		}

		if (diaOne == null) {

			diaOne = ModelFactory.eINSTANCE.createDiagram();
			diaOne.getNodes().add(node0DiaOne);
			diaOne.getNodes().add(node1DiaOne);
			diaOne.getNodes().add(dummyNodeDiaOne);

		}

		if (diaTwo == null) {

			diaTwo = ModelFactory.eINSTANCE.createDiagram();
			diaTwo.getNodes().add(node0DiaTwo);
			diaTwo.getNodes().add(node1DiaTwo);
			diaTwo.getNodes().add(dummyNodeDiaTwo);

		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);
			file.getDiagrams().add(diaOne);
			file.getDiagrams().add(diaTwo);

		}

		ModelIndexer.createInstance(file);

		if (command == null)
			command = new SetImageCommand(type, newValue);

	}

	@After
	public void shutdown() {

		type = null;
		newValue = null;
		oldValue = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		command = new SetImageCommand(null, newValue);
		Assert.assertFalse(command.canExecute());
		
		 command = new SetImageCommand(type, oldValue);
		 Assert.assertFalse(command.canExecute());
				
		 node0DiaOne.setImage(null);
		 node1DiaOne.setImage(null);
		 command = new SetImageCommand(type, null);
		 Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		List<TypeNode> nodeListDiaOne = getTypeNotes(diaOne.getNodes());
		List<TypeNode> nodeListDiaTwo = getTypeNotes(diaTwo.getNodes());

		for (TypeNode t : nodeListDiaOne)
			t.setImage(newValue);

		for (TypeNode t : nodeListDiaTwo)
			t.setImage(newValue);

		command.execute();

		List<TypeNode> tmpOne = getTypeNotes(diaOne.getNodes());
		List<TypeNode> tmpTwo = getTypeNotes(diaTwo.getNodes());

		Assert.assertTrue(Tools.typeNodeListCompare(nodeListDiaOne, tmpOne));
		Assert.assertTrue(Tools.typeNodeListCompare(nodeListDiaTwo, tmpTwo));

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

		List<TypeNode> nodeListDiaOne = getTypeNotes(diaOne.getNodes());
		List<TypeNode> nodeListDiaTwo = getTypeNotes(diaTwo.getNodes());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		List<TypeNode> tmpOne = getTypeNotes(diaOne.getNodes());
		List<TypeNode> tmpTwo = getTypeNotes(diaTwo.getNodes());

		Assert.assertTrue(Tools.typeNodeListCompare(nodeListDiaOne, tmpOne));
		Assert.assertTrue(Tools.typeNodeListCompare(nodeListDiaTwo, tmpTwo));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		List<TypeNode> nodeListDiaOne = getTypeNotes(diaOne.getNodes());
		List<TypeNode> nodeListDiaTwo = getTypeNotes(diaTwo.getNodes());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		List<TypeNode> tmpOne = getTypeNotes(diaOne.getNodes());
		List<TypeNode> tmpTwo = getTypeNotes(diaTwo.getNodes());

		Assert.assertTrue(Tools.typeNodeListCompare(nodeListDiaOne, tmpOne));
		Assert.assertTrue(Tools.typeNodeListCompare(nodeListDiaTwo, tmpTwo));

	}

	private List<TypeNode> getTypeNotes(List<Node> list) {

		List<TypeNode> tmp = new ArrayList<TypeNode>();
		for (Node n : list)
			tmp.add((TypeNode) n);

		return tmp;

	}
}
