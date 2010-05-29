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
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
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
	private TopicMapSchema schema;
	private DomainDiagram domDia;
	private Diagram dia;
	private File file;
	private List<TopicType> newList;
	private List<TopicType> emptyList;
	private List<TopicType> topicTypeList;
	private int topicTypeSize;

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

		if (schema == null) {

			schema = ModelFactory.eINSTANCE.createTopicMapSchema();
			schema.getTopicTypes().add(topicType);

		}

		if (dia == null) {

			dia = ModelFactory.eINSTANCE.createDiagram();

		}

		if (domDia == null) {

			domDia = ModelFactory.eINSTANCE.createDomainDiagram();

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

	}
	
	@Test
	public void canExecuteTestDiagramClear() {

		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestSet() {

		
		command = new SetAkoCommand(newList, topicType);
		Assert.assertTrue(command.canExecute());

		topicTypeSize = newList.size();
		topicTypeList = new ArrayList<TopicType>(topicType.getAko());
		System.out.println(topicType.getAko().size());

		command.execute();
		
		Assert.assertTrue(Tools.topicTypeListCompare(newList, topicType.getAko()));

		System.out.println(topicType.getAko().size());

	}

	@Test
	public void executeTestSetClear() {

		
		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());

		System.out.println(topicType.getAko().size());

		command.execute();

		System.out.println(topicType.getAko().size());

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
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

	}
	
	@Test
	public void undoTestClear() {

		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

	}

	@Test
	public void redoTestSet() {

		command = new SetAkoCommand(newList, topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

	}
	
	@Test
	public void redoTestClear() {

		command = new SetAkoCommand(emptyList, topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

	}

	// public SetAkoCommandTest(List<TopicType> newList, TopicType topic) {
	// super("Set a kind of relationship", newList, topic);
	// }
	//
	// @Override
	// protected EdgeType getEdgeType() {
	// return EdgeType.AKO_TYPE;
	// }
	//	
	// @Override
	// protected boolean prepare() {
	// if (topic.getAko().equals(newList))
	// return false;
	//		
	// return super.prepare();
	// }
}
