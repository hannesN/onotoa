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
import de.topicmapslab.tmcledit.model.commands.SetAkoCommand;
import de.topicmapslab.tmcledit.model.index.ModelIndexer;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetAkoCommandTest {

	private SetAkoCommand command;
	private TopicType topicType;
	private TopicType oldAkoTopicType0;
	private TopicType oldAkoTopicType1;
	private TopicType sameAkoTopicType;
	private TopicType newAkoTopicType0;
	private TopicType newAkoTopicType1;
	private TopicType newAkoTopicType2;
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

		if (oldAkoTopicType0 == null)
			oldAkoTopicType0 = ModelFactory.eINSTANCE.createTopicType();

		if (oldAkoTopicType1 == null)
			oldAkoTopicType1 = ModelFactory.eINSTANCE.createTopicType();

		if (sameAkoTopicType == null)
			sameAkoTopicType = ModelFactory.eINSTANCE.createTopicType();

		if (newAkoTopicType0 == null)
			newAkoTopicType0 = ModelFactory.eINSTANCE.createTopicType();

		if (newAkoTopicType1 == null)
			newAkoTopicType1 = ModelFactory.eINSTANCE.createTopicType();

		if (newAkoTopicType2 == null)
			newAkoTopicType2 = ModelFactory.eINSTANCE.createTopicType();

		if (newList == null) {

			newList = new ArrayList<TopicType>();
			newList.add(newAkoTopicType0);
			newList.add(newAkoTopicType1);
			newList.add(newAkoTopicType2);
			newList.add(sameAkoTopicType);

		}

		if (emptyList == null)
			emptyList = new ArrayList<TopicType>();

		if (topicType == null) {

			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.getAko().add(oldAkoTopicType0);
			topicType.getAko().add(oldAkoTopicType1);
			topicType.getAko().add(sameAkoTopicType);

		}

		if (diaNode0Source == null) {

			diaNode0Source = ModelFactory.eINSTANCE.createTypeNode();
			diaNode0Source.setTopicType(topicType);

		}

		if (diaNode1Source == null)
			diaNode1Source = ModelFactory.eINSTANCE.createTypeNode();

		if (diaNode0Target == null) {

			diaNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			diaNode0Target.setTopicType(oldAkoTopicType0);

		}

		if (diaNode1Target == null) {

			diaNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			diaNode1Target.setTopicType(oldAkoTopicType1);

		}

		if (diaNode2Target == null) {

			diaNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			diaNode2Target.setTopicType(sameAkoTopicType);

		}

		if (domNode0Source == null) {

			domNode0Source = ModelFactory.eINSTANCE.createTypeNode();
			domNode0Source.setTopicType(topicType);

		}

		if (domNode1Source == null)
			domNode1Source = ModelFactory.eINSTANCE.createTypeNode();

		if (domNode0Target == null) {

			domNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			domNode0Target.setTopicType(oldAkoTopicType0);

		}

		if (domNode1Target == null) {

			domNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			domNode1Target.setTopicType(oldAkoTopicType1);

		}

		if (domNode2Target == null) {

			domNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			domNode2Target.setTopicType(sameAkoTopicType);

		}

		if (newDiaNode0Target == null) {

			newDiaNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			newDiaNode0Target.setTopicType(newAkoTopicType0);

		}

		if (newDiaNode1Target == null) {

			newDiaNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			newDiaNode1Target.setTopicType(newAkoTopicType1);

		}

		if (newDiaNode2Target == null) {

			newDiaNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			newDiaNode2Target.setTopicType(newAkoTopicType2);

		}

		if (newDomNode0Target == null) {

			newDomNode0Target = ModelFactory.eINSTANCE.createTypeNode();
			newDomNode0Target.setTopicType(newAkoTopicType0);

		}

		if (newDomNode1Target == null) {

			newDomNode1Target = ModelFactory.eINSTANCE.createTypeNode();
			newDomNode1Target.setTopicType(newAkoTopicType1);

		}

		if (newDomNode2Target == null) {

			newDomNode2Target = ModelFactory.eINSTANCE.createTypeNode();
			newDomNode2Target.setTopicType(newAkoTopicType2);

		}

		if (edgeType == null)
			edgeType = EdgeType.AKO_TYPE;

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
			schema.getTopicTypes().add(oldAkoTopicType0);
			schema.getTopicTypes().add(oldAkoTopicType1);
			schema.getTopicTypes().add(sameAkoTopicType);
			schema.getTopicTypes().add(newAkoTopicType0);
			schema.getTopicTypes().add(newAkoTopicType1);
			schema.getTopicTypes().add(newAkoTopicType2);

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
		oldAkoTopicType0 = null;
		oldAkoTopicType1 = null;
		sameAkoTopicType = null;
		newAkoTopicType2 = null;
		newAkoTopicType1 = null;
		newAkoTopicType0 = null;
		schema = null;
		dia = null;
		domDia = null;
		file = null;
		command = null;

	}

	@Test
	public void canExecuteTestDiagramSet() {

		command = new SetAkoCommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		command = new SetAkoCommand(topicType.getAko(), topicType);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void canExecuteTestClear() {

		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestSet() {

		command = new SetAkoCommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		command.execute();

		Assert.assertTrue(newList.size() == topicType.getAko().size());
		Assert.assertTrue(newList.size() == dia.getEdges().size());
		Assert.assertTrue(newList.size() == domDia.getEdges().size());
		Assert.assertTrue(newEdgeTest(dia));
		Assert.assertTrue(newEdgeTest(domDia));

		for (TopicType tt : newList)
			if (!topicType.getAko().contains(tt))
				Assert.fail();

	}

	@Test
	public void executeTestSetClear() {

		command = new SetAkoCommand(emptyList, topicType);

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(topicType.getAko().size() == 0);
		Assert.assertTrue(dia.getEdges().size() == 0);
		Assert.assertTrue(domDia.getEdges().size() == 0);

	}

	@Test
	public void canUndoSet() {

		command = new SetAkoCommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoClear() {

		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestSet() {

		command = new SetAkoCommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		topicTypeSize = topicType.getAko().size();
		topicTypeList = new ArrayList<TopicType>(topicType.getAko());
		edgeListDia = new ArrayList<Edge>(dia.getEdges());
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(Tools.edgeListCompare(edgeListDia, dia.getEdges()));
		Assert
				.assertTrue(Tools.edgeListCompare(edgeListDom, domDia
						.getEdges()));
		Assert.assertTrue(topicTypeSize == topicType.getAko().size());
		for (TopicType tt : topicTypeList)
			if (!topicType.getAko().contains(tt))
				Assert.fail();

	}

	@Test
	public void undoTestClear() {

		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

		edgeSizeDia = dia.getEdges().size();
		edgeSizeDom = domDia.getEdges().size();

		edgeListDia = new ArrayList<Edge>(dia.getEdges());
		edgeListDom = new ArrayList<Edge>(domDia.getEdges());

		topicTypeSize = topicType.getAko().size();
		topicTypeList = new ArrayList<TopicType>(topicType.getAko());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(topicTypeSize == topicType.getAko().size());
		for (TopicType tt : topicTypeList)
			if (!topicType.getAko().contains(tt))
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

		command = new SetAkoCommand(newList, topicType);
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

		Assert.assertTrue(newList.size() == topicType.getAko().size());
		for (TopicType tt : newList)
			if (!topicType.getAko().contains(tt))
				Assert.fail();

	}

	@Test
	public void redoTestClear() {

		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(topicType.getAko().size() == 0);
		Assert.assertTrue(dia.getEdges().size() == 0);
		Assert.assertTrue(domDia.getEdges().size() == 0);

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
