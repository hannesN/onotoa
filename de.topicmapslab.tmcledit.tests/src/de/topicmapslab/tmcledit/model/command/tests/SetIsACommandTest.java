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
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.commands.SetIsACommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetIsACommandTest {

	private SetIsACommand command;
	private TopicType topicType;
	private TopicType oldIsaTopicType0;
	private TopicType oldIsaTopicType1;
	private TopicType sameIsaTopicType;
	private TopicType newIsaTopicType0;
	private TopicType newIsaTopicType1;
	private TopicType newIsaTopicType2;
	private TypeNode diaNode0Source, diaNode1Source;
	private TypeNode diaNode0Target, diaNode1Target, diaNode2Target;
	private TypeNode domNode0Source, domNode1Source;
	private TypeNode domNode0Target, domNode1Target, domNode2Target;
	private TypeNode newDiaNode0Target, newDiaNode1Target, newDiaNode2Target;
	private TypeNode newDomNode0Target, newDomNode1Target, newDomNode2Target;
	private Edge diaEdge0, diaEdge1, diaEdge2;
	private Edge domEdge0, domEdge1, domEdge2;
	private EdgeType edgeType;
	private TopicMapSchema schema;
	private DomainDiagram domDia;
	private Diagram dia;
	private File file;
	private List<TopicType> newList;
	private List<TopicType> emptyList;
	private List<TopicType> topicTypeList;
	private List<Edge> edgeListDia;
	private List<Edge> edgeListDom;
	private int topicTypeSize;
	private int edgeSizeDia;
	private int edgeSizeDom;

	@Before
	public void prepare() {

		if (oldIsaTopicType0 == null)
			oldIsaTopicType0 = ModelFactory.eINSTANCE.createTopicType();

		if (oldIsaTopicType1 == null)
			oldIsaTopicType1 = ModelFactory.eINSTANCE.createTopicType();

		if (sameIsaTopicType == null)
			sameIsaTopicType = ModelFactory.eINSTANCE.createTopicType();

		if (newIsaTopicType0 == null)
			newIsaTopicType0 = ModelFactory.eINSTANCE.createTopicType();

		if (newIsaTopicType1 == null)
			newIsaTopicType1 = ModelFactory.eINSTANCE.createTopicType();

		if (newIsaTopicType2 == null)
			newIsaTopicType2 = ModelFactory.eINSTANCE.createTopicType();

		if (newList == null) {

			newList = new ArrayList<TopicType>();
			newList.add(newIsaTopicType0);
			newList.add(newIsaTopicType1);
			newList.add(newIsaTopicType2);
			newList.add(sameIsaTopicType);

		}

		if (emptyList == null)
			emptyList = new ArrayList<TopicType>();

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.getIsa().add(oldIsaTopicType0);
			topicType.getIsa().add(oldIsaTopicType1);
			topicType.getIsa().add(sameIsaTopicType);

		}

		if (diaNode0Source == null) {

			diaNode0Source = ModelFactory.eINSTANCE.createTypeNode();
			diaNode0Source.setTopicType(topicType);

		}

		if (diaNode1Source == null)
			diaNode1Source = ModelFactory.eINSTANCE.createTypeNode();

		if (diaNode0Target == null) {

			diaNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			diaNode0Target.setTopicType(oldIsaTopicType0);

		}

		if (diaNode1Target == null) {

			diaNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			diaNode1Target.setTopicType(oldIsaTopicType1);

		}

		if (diaNode2Target == null) {

			diaNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			diaNode2Target.setTopicType(sameIsaTopicType);

		}

		if (domNode0Source == null) {

			domNode0Source = ModelFactory.eINSTANCE.createTypeNode();
			domNode0Source.setTopicType(topicType);

		}

		if (domNode1Source == null)
			domNode1Source = ModelFactory.eINSTANCE.createTypeNode();

		if (domNode0Target == null) {

			domNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			domNode0Target.setTopicType(oldIsaTopicType0);

		}

		if (domNode1Target == null) {

			domNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			domNode1Target.setTopicType(oldIsaTopicType1);

		}

		if (domNode2Target == null) {

			domNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			domNode2Target.setTopicType(sameIsaTopicType);

		}

		if (newDiaNode0Target == null) {

			newDiaNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			newDiaNode0Target.setTopicType(newIsaTopicType0);

		}

		if (newDiaNode1Target == null) {

			newDiaNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			newDiaNode1Target.setTopicType(newIsaTopicType1);

		}

		if (newDiaNode2Target == null) {

			newDiaNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			newDiaNode2Target.setTopicType(newIsaTopicType2);

		}

		if (newDomNode0Target == null) {

			newDomNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			newDomNode0Target.setTopicType(newIsaTopicType0);

		}

		if (newDomNode1Target == null) {

			newDomNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			newDomNode1Target.setTopicType(newIsaTopicType1);

		}

		if (newDomNode2Target == null) {

			newDomNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			newDomNode2Target.setTopicType(newIsaTopicType2);

		}

		if (edgeType == null)
			edgeType = EdgeType.IS_ATYPE;

		if (diaEdge0 == null) {

			diaEdge0 = ModelFactory.eINSTANCE.createEdge();
			diaEdge0.setSource(diaNode0Source);
			diaEdge0.setTarget(diaNode0Target);
			diaEdge0.setType(edgeType);

		}

		if (diaEdge2 == null) {

			diaEdge2 = ModelFactory.eINSTANCE.createEdge();
			diaEdge2.setSource(diaNode0Source);
			diaEdge2.setTarget(diaNode2Target);
			diaEdge2.setType(edgeType);

		}

		if (diaEdge1 == null) {

			diaEdge1 = ModelFactory.eINSTANCE.createEdge();
			diaEdge1.setSource(diaNode0Source);
			diaEdge1.setTarget(diaNode1Target);
			diaEdge1.setType(edgeType);

		}

		if (domEdge0 == null) {

			domEdge0 = ModelFactory.eINSTANCE.createEdge();
			domEdge0.setSource(domNode0Source);
			domEdge0.setTarget(domNode0Target);
			domEdge0.setType(edgeType);

		}

		if (domEdge2 == null) {

			domEdge2 = ModelFactory.eINSTANCE.createEdge();
			domEdge2.setSource(domNode0Source);
			domEdge2.setTarget(domNode2Target);
			domEdge2.setType(edgeType);

		}

		if (domEdge1 == null) {

			domEdge1 = ModelFactory.eINSTANCE.createEdge();
			domEdge1.setSource(domNode0Source);
			domEdge1.setTarget(domNode1Target);
			domEdge1.setType(edgeType);

		}

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getTopicTypes().add(topicType);
			schema.getTopicTypes().add(oldIsaTopicType0);
			schema.getTopicTypes().add(oldIsaTopicType1);
			schema.getTopicTypes().add(sameIsaTopicType);
			schema.getTopicTypes().add(newIsaTopicType0);
			schema.getTopicTypes().add(newIsaTopicType1);
			schema.getTopicTypes().add(newIsaTopicType2);

		}

		if (dia == null) {

			dia = ModelFactory.eINSTANCE.createDiagram();
			dia.getNodes().add(diaNode0Source);
			dia.getNodes().add(diaNode1Source);
			dia.getNodes().add(diaNode0Target);
			dia.getNodes().add(diaNode1Target);
			dia.getNodes().add(diaNode2Target);
			dia.getNodes().add(newDiaNode0Target);
			dia.getNodes().add(newDiaNode1Target);
			dia.getNodes().add(newDiaNode2Target);
			dia.getEdges().add(diaEdge0);
			dia.getEdges().add(diaEdge1);
			dia.getEdges().add(diaEdge2);

		}

		if (domDia == null) {

			domDia = ModelFactory.eINSTANCE.createDomainDiagram();
			domDia.getNodes().add(domNode0Source);
			domDia.getNodes().add(domNode1Source);
			domDia.getNodes().add(domNode0Target);
			domDia.getNodes().add(domNode1Target);
			domDia.getNodes().add(domNode2Target);
			domDia.getNodes().add(newDomNode0Target);
			domDia.getNodes().add(newDomNode1Target);
			domDia.getNodes().add(newDomNode2Target);
			domDia.getEdges().add(domEdge0);
			domDia.getEdges().add(domEdge1);
			domDia.getEdges().add(domEdge2);

		}

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.setTopicMapSchema(schema);
			file.getDiagrams().add(dia);
			file.getDiagrams().add(domDia);

		}

		ModelIndexer.createInstance(file);

	}

	@After
	public void shutdown() {

		newList = null;
		topicType = null;
		topicTypeList = null;
		edgeListDom = null;
		edgeListDia = null;
		edgeType = null;
		diaEdge0 = null;
		diaEdge1 = null;
		diaEdge2 = null;
		domEdge0 = null;
		domEdge1 = null;
		domEdge2 = null;
		diaNode0Target = null;
		diaNode1Target = null;
		diaNode0Target = null;
		diaNode1Target = null;
		diaNode2Target = null;
		domNode0Target = null;
		domNode1Target = null;
		domNode0Target = null;
		domNode1Target = null;
		domNode2Target = null;
		newDiaNode0Target = null;
		newDiaNode1Target = null;
		newDiaNode2Target = null;
		newDomNode0Target = null;
		newDomNode1Target = null;
		newDomNode2Target = null;
		emptyList = null;
		oldIsaTopicType0 = null;
		oldIsaTopicType1 = null;
		sameIsaTopicType = null;
		newIsaTopicType2 = null;
		newIsaTopicType1 = null;
		newIsaTopicType0 = null;
		schema = null;
		dia = null;
		domDia = null;
		file = null;
		command = null;

	}

	@Test
	public void canExecuteTestDiagramSet() {

		command = new SetIsACommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		command = new SetIsACommand(topicType.getIsa(), topicType);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void canExecuteTestClear() {

		command = new SetIsACommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestSet() {

		command = new SetIsACommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		edgeSizeDom = domDia.getEdges().size();
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		command.execute();

		Assert.assertTrue(newList.size() == topicType.getIsa().size());
		Assert.assertTrue(newList.size() == dia.getEdges().size());
		Assert.assertTrue(edgeSizeDom == domDia.getEdges().size());
		Assert.assertTrue(newEdgeTest(dia));
		Assert
				.assertTrue(Tools.edgeListCompare(edgeListDom, domDia
						.getEdges()));

		for (TopicType tt : newList)
			if (!topicType.getIsa().contains(tt))
				Assert.fail();

	}

	@Test
	public void executeTestSetClear() {

		command = new SetIsACommand(emptyList, topicType);

		edgeSizeDom = domDia.getEdges().size();
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(topicType.getIsa().size() == 0);
		Assert.assertTrue(dia.getEdges().size() == 0);

		Assert.assertTrue(edgeSizeDom == domDia.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeListDom, domDia
						.getEdges()));

	}

	@Test
	public void canUndoSet() {

		command = new SetIsACommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoClear() {

		command = new SetIsACommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestSet() {

		command = new SetIsACommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		topicTypeSize = topicType.getIsa().size();
		topicTypeList = new ArrayList<TopicType>(topicType.getIsa());

		edgeListDia = new ArrayList<Edge>(dia.getEdges());
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(Tools.edgeListCompare(edgeListDia, dia.getEdges()));
		Assert
				.assertTrue(Tools.edgeListCompare(edgeListDom, domDia
						.getEdges()));
		Assert.assertTrue(topicTypeSize == topicType.getIsa().size());
		for (TopicType tt : topicTypeList)
			if (!topicType.getIsa().contains(tt))
				Assert.fail();

	}

	@Test
	public void undoTestClear() {

		command = new SetIsACommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

		edgeSizeDia = dia.getEdges().size();
		edgeSizeDom = domDia.getEdges().size();

		edgeListDia = new ArrayList<Edge>(dia.getEdges());
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		topicTypeSize = topicType.getIsa().size();
		topicTypeList = new ArrayList<TopicType>(topicType.getIsa());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(topicTypeSize == topicType.getIsa().size());
		for (TopicType tt : topicTypeList)
			if (!topicType.getIsa().contains(tt))
				Assert.fail();

		Assert.assertTrue(dia.getEdges().size() == edgeSizeDia);
		Assert.assertTrue(domDia.getEdges().size() == edgeSizeDom);
		Assert.assertTrue(Tools.edgeListCompare(edgeListDia, dia.getEdges()));
		Assert
				.assertTrue(Tools.edgeListCompare(edgeListDom, domDia
						.getEdges()));

	}

	@Test
	public void redoTestSet() {

		command = new SetIsACommand(newList, topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();

		edgeListDia = new ArrayList<Edge>(dia.getEdges());
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(Tools.edgeListCompare(edgeListDia, dia.getEdges()));
		Assert
				.assertTrue(Tools.edgeListCompare(edgeListDom, domDia
						.getEdges()));

		Assert.assertTrue(newList.size() == topicType.getIsa().size());
		for (TopicType tt : newList)
			if (!topicType.getIsa().contains(tt))
				Assert.fail();

	}

	@Test
	public void redoTestClear() {

		command = new SetIsACommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();

		edgeSizeDom = domDia.getEdges().size();
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(topicType.getIsa().size() == 0);
		Assert.assertTrue(dia.getEdges().size() == 0);
		Assert.assertTrue(edgeSizeDom == domDia.getEdges().size());
		Assert
				.assertTrue(Tools.edgeListCompare(edgeListDom, domDia
						.getEdges()));

	}

	private boolean newEdgeTest(Diagram dia) {

		List<Edge> edgeList = new ArrayList<Edge>(edgeList = ModelIndexer
				.getNodeIndexer().getEdges(dia, edgeType));

		for (Edge e : edgeList) {

			TypeNode source = (TypeNode) e.getSource();
			if (!Tools.topicTypeCompare(source.getTopicType(), this.topicType))
				return false;

			TypeNode target = (TypeNode) e.getTarget();
			if (!this.newList.contains(target.getTopicType()))
				return false;

		}

		return true;
	}

}
