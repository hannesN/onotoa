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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateOccurrenceConstraintCommand;

/**
 * 
 * @author Hannes Niederhausen
 * 
 */
public class CreateOccurrenceConstraintCommandTest {

	private CreateOccurrenceConstraintCommand command;
	private TopicType topicType;
	private TopicMapSchema schema;
	private OccurrenceTypeConstraint otc;
	private List<OccurrenceTypeConstraint> list;
	private int size;
	private int id;

	@Before
	public void prepare() {

		if (topicType == null)
			topicType = ModelFactory.eINSTANCE.createTopicType();

		if (otc == null)
			otc = ModelFactory.eINSTANCE.createOccurrenceTypeConstraint();

		if (schema == null)
			schema = ModelFactory.eINSTANCE.createTopicMapSchema();

	}

	@After
	public void shutdown() {

		topicType = null;
		schema = null;
		otc = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestCO() {

		command = new CreateOccurrenceConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1NoType() {

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1TypeContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		schema.getTopicTypes().add(tt);
		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1TypeNoContainerTtContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();
		otc.setType(tt);

		schema.getTopicTypes().add(topicType);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1TypeNoContainerTtNoContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestCO() {

		command = new CreateOccurrenceConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());

		command.execute();

		id = topicType.getOccurrenceConstraints().get(size).getId();
		otc.setId(id);
		list.add(otc);

		Assert.assertTrue((size + 1) == topicType.getOccurrenceConstraints()
				.size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void executeTestC1NoType() {

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());
		list.add(otc);

		command.execute();

		Assert.assertTrue((size + 1) == topicType.getOccurrenceConstraints()
				.size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void executeTestC1TypeContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		schema.getTopicTypes().add(tt);
		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());
		list.add(otc);

		command.execute();

		Assert.assertTrue((size + 1) == topicType.getOccurrenceConstraints()
				.size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void executeTestC1TypeNoContainerTtContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();
		otc.setType(tt);
		schema.getTopicTypes().add(topicType);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

		// create tt list clone
		int ttSize = schema.getTopicTypes().size();
		List<TopicType> topicTypeList = new ArrayList<TopicType>(schema
				.getTopicTypes());
		topicTypeList.add(tt);

		// create otc list clone
		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());
		list.add(otc);

		command.execute();

		Assert.assertTrue((ttSize + 1) == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(topicTypeList, schema
				.getTopicTypes()));
		Assert.assertTrue((size + 1) == topicType.getOccurrenceConstraints()
				.size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void executeTestC1TypeNoContainerTtNoContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());
		list.add(otc);

		command.execute();

		Assert.assertTrue((size + 1) == topicType.getOccurrenceConstraints()
				.size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void canUndoTestCO() {

		command = new CreateOccurrenceConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1NoType() {

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1TypeContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		schema.getTopicTypes().add(tt);
		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1TypeNoContainerTtContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();
		otc.setType(tt);

		schema.getTopicTypes().add(topicType);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1TypeNoContainerTtNoContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void redoTestCO() {

		command = new CreateOccurrenceConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void redoTestC1NoType() {

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void redoTestC1TypeContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		schema.getTopicTypes().add(tt);
		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void redoTestC1TypeNoContainerTtContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();
		otc.setType(tt);
		schema.getTopicTypes().add(topicType);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();

		// create tt list clone
		int ttSize = schema.getTopicTypes().size();
		List<TopicType> topicTypeList = new ArrayList<TopicType>(schema
				.getTopicTypes());

		// create otc list clone
		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(ttSize == schema.getTopicTypes().size());
		Assert.assertTrue(Tools.topicTypeListCompare(topicTypeList, schema
				.getTopicTypes()));
		Assert.assertTrue(size == topicType.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

	@Test
	public void redoTestC1TypeNoContainerTtNoContainer() {

		TopicType tt = ModelFactory.eINSTANCE.createTopicType();

		otc.setType(tt);

		command = new CreateOccurrenceConstraintCommand(topicType, otc);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getOccurrenceConstraints().size();
		list = new ArrayList<OccurrenceTypeConstraint>(topicType
				.getOccurrenceConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getOccurrenceConstraints().size());
		Assert.assertTrue(Tools.occurrenceTypeConstraintListCompare(list,
				topicType.getOccurrenceConstraints()));

	}

}
