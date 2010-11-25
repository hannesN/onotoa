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
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateNameTypeConstraintCommand;

public class CreateNameTypeConstraintCommandTest {

	private CreateNameTypeConstraintCommand command;
	private TopicType topicType1;
	private TopicType topicType2;
	private NameTypeConstraint nameTypeConstraint;
	private TopicMapSchema schema;
	private List<NameTypeConstraint> list;
	private int sizeNameConstraints;
	private int sizeTopicTypes;

	@Before
	public void prepare() {

		schema = ModelFactory.eINSTANCE.createTopicMapSchema();
		nameTypeConstraint = ModelFactory.eINSTANCE.createNameTypeConstraint();
		topicType1 = ModelFactory.eINSTANCE.createTopicType();
		topicType2 = ModelFactory.eINSTANCE.createTopicType();

	}

	@After
	public void shutdown() {

		topicType1 = null;
		topicType2 = null;
		nameTypeConstraint = null;
		schema = null;
		command = null;

	}

	@Test
	public void canExecuteC0() {

		command = new CreateNameTypeConstraintCommand(topicType1);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteC1NoSetSchema0() {

		// NameTypeCOnstraint has no type
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteC1NoSetSchema1() {

		// NameTypeCOnstraint has type, but the type has container
		schema.getTopicTypes().add(topicType2);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteC1SetSchema0() {

		// schema = this.schema
		schema.getTopicTypes().add(topicType1);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteC1SetSchema1() {

		// schema = null
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeC0() {

		command = new CreateNameTypeConstraintCommand(topicType1);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		command.execute();
		nameTypeConstraint.setId(topicType1.getNameConstraints().get(
				sizeNameConstraints).getId());
		list.add(nameTypeConstraint);

		Assert.assertTrue((sizeNameConstraints + 1) == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void executeC1NoSetSchema0() {

		// NameTypeCOnstraint has no type
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());
		list.add(nameTypeConstraint);

		command.execute();

		Assert.assertTrue((sizeNameConstraints + 1) == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void executeC1NoSetSchema1() {

		// NameTypeCOnstraint has type, but the type has container
		schema.getTopicTypes().add(topicType2);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());
		list.add(nameTypeConstraint);

		command.execute();

		Assert.assertTrue((sizeNameConstraints + 1) == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void executeC1SetSchema0() {

		// schema = this.schema
		schema.getTopicTypes().add(topicType1);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		sizeTopicTypes = schema.getTopicTypes().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());
		list.add(nameTypeConstraint);

		command.execute();

		Assert.assertTrue((sizeNameConstraints + 1) == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));
		Assert
				.assertTrue((sizeTopicTypes + 1) == schema.getTopicTypes()
						.size());
		Assert.assertTrue(Tools.topicTypeCompare(topicType2, schema
				.getTopicTypes().get(sizeTopicTypes)));

	}

	@Test
	public void executeC1SetSchema1() {

		// schema = null
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		sizeTopicTypes = schema.getTopicTypes().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());
		list.add(nameTypeConstraint);

		command.execute();

		Assert.assertTrue((sizeNameConstraints + 1) == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));
		Assert.assertTrue(sizeTopicTypes == schema.getTopicTypes().size());
		Assert.assertTrue(schema.getTopicTypes().contains(topicType2) == false);

	}

	@Test
	public void canUndoC0() {

		command = new CreateNameTypeConstraintCommand(topicType1);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoC1NoSetSchema0() {

		// NameTypeCOnstraint has no type
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoC1NoSetSchema1() {

		// NameTypeCOnstraint has type, but the type has container
		schema.getTopicTypes().add(topicType2);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoC1SetSchema0() {

		// schema = this.schema
		schema.getTopicTypes().add(topicType1);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoC1SetSchema1() {

		// schema = null
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoC0() {

		command = new CreateNameTypeConstraintCommand(topicType1);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void undoC1NoSetSchema0() {

		// NameTypeCOnstraint has no type
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void undoC1NoSetSchema1() {

		// NameTypeCOnstraint has type, but the type has container
		schema.getTopicTypes().add(topicType2);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void undoC1SetSchema0() {

		// schema = this.schema
		schema.getTopicTypes().add(topicType1);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		sizeTopicTypes = schema.getTopicTypes().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));
		Assert.assertTrue(sizeTopicTypes == schema.getTopicTypes().size());
		Assert
				.assertTrue(schema.getTopicTypes().contains(sizeTopicTypes) == false);

	}

	@Test
	public void undoC1SetSchema1() {

		// schema = null
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());

		sizeNameConstraints = topicType1.getNameConstraints().size();
		sizeTopicTypes = schema.getTopicTypes().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));
		Assert.assertTrue(sizeTopicTypes == schema.getTopicTypes().size());
		Assert
				.assertTrue(schema.getTopicTypes().contains(sizeTopicTypes) == false);

	}

	@Test
	public void redoC0() {

		command = new CreateNameTypeConstraintCommand(topicType1);
		Assert.assertTrue(command.canExecute());
		command.execute();

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void redoC1NoSetSchema0() {

		// NameTypeCOnstraint has no type
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void redoC1NoSetSchema1() {

		// NameTypeCOnstraint has type, but the type has container
		schema.getTopicTypes().add(topicType2);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		sizeNameConstraints = topicType1.getNameConstraints().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));

	}

	@Test
	public void redoC1SetSchema0() {

		// schema = this.schema
		schema.getTopicTypes().add(topicType1);
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		sizeNameConstraints = topicType1.getNameConstraints().size();
		sizeTopicTypes = schema.getTopicTypes().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));
		Assert.assertTrue(sizeTopicTypes == schema.getTopicTypes().size());

	}

	@Test
	public void redoC1SetSchema1() {

		// schema = null
		nameTypeConstraint.setType(topicType2);
		command = new CreateNameTypeConstraintCommand(topicType1,
				nameTypeConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		sizeNameConstraints = topicType1.getNameConstraints().size();
		sizeTopicTypes = schema.getTopicTypes().size();
		list = new ArrayList<NameTypeConstraint>(topicType1
				.getNameConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(sizeNameConstraints == topicType1
				.getNameConstraints().size());
		Assert.assertTrue(Tools.nameTypeConstraintListCompare(list, topicType1
				.getNameConstraints()));
		Assert.assertTrue(sizeTopicTypes == schema.getTopicTypes().size());

	}

}
