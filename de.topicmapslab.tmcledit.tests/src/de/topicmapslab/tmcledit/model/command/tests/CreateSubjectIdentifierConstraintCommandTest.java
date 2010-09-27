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
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateSubjectIdentifierConstraintCommand;

public class CreateSubjectIdentifierConstraintCommandTest {

	private CreateSubjectIdentifierConstraintCommand command;
	private TopicType topicType;
	private SubjectIdentifierConstraint subjectIdentifierConstraint;
	private List<SubjectIdentifierConstraint> list;
	private int size;
	private int id;

	@Before
	public void prepare() {

		if (topicType == null)
			topicType = ModelFactory.eINSTANCE.createTopicType();

		if (subjectIdentifierConstraint == null)
			subjectIdentifierConstraint = ModelFactory.eINSTANCE
					.createSubjectIdentifierConstraint();

	}

	@After
	public void shutdown() {

		topicType = null;
		subjectIdentifierConstraint = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestC0() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType,
				subjectIdentifierConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestC0() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectIdentifierConstraints().size();
		list = new ArrayList<SubjectIdentifierConstraint>(topicType
				.getSubjectIdentifierConstraints());

		command.execute();
		id = topicType.getSubjectIdentifierConstraints().get(size).getId();
		subjectIdentifierConstraint.setId(id);
		list.add(subjectIdentifierConstraint);

		Assert.assertTrue((size + 1) == topicType
				.getSubjectIdentifierConstraints().size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(list,
				topicType.getSubjectIdentifierConstraints()));
	}

	@Test
	public void executeTestC1() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType,
				subjectIdentifierConstraint);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectIdentifierConstraints().size();
		list = new ArrayList<SubjectIdentifierConstraint>(topicType
				.getSubjectIdentifierConstraints());
		list.add(subjectIdentifierConstraint);

		command.execute();

		Assert.assertTrue((size + 1) == topicType
				.getSubjectIdentifierConstraints().size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(list,
				topicType.getSubjectIdentifierConstraints()));

	}

	@Test
	public void canUndoTestC0() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType,
				subjectIdentifierConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestC0() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectIdentifierConstraints().size();
		list = new ArrayList<SubjectIdentifierConstraint>(topicType
				.getSubjectIdentifierConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == topicType.getSubjectIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(list,
				topicType.getSubjectIdentifierConstraints()));
	}

	@Test
	public void undoTestC1() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType,
				subjectIdentifierConstraint);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectIdentifierConstraints().size();
		list = new ArrayList<SubjectIdentifierConstraint>(topicType
				.getSubjectIdentifierConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == topicType.getSubjectIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(list,
				topicType.getSubjectIdentifierConstraints()));

	}

	@Test
	public void redoTestC0() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getSubjectIdentifierConstraints().size();
		list = new ArrayList<SubjectIdentifierConstraint>(topicType
				.getSubjectIdentifierConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getSubjectIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(list,
				topicType.getSubjectIdentifierConstraints()));
	}

	@Test
	public void redoTestC1() {

		command = new CreateSubjectIdentifierConstraintCommand(topicType,
				subjectIdentifierConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getSubjectIdentifierConstraints().size();
		list = new ArrayList<SubjectIdentifierConstraint>(topicType
				.getSubjectIdentifierConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getSubjectIdentifierConstraints()
				.size());
		Assert.assertTrue(Tools.subjectIdentifierConstraintListCompare(list,
				topicType.getSubjectIdentifierConstraints()));

	}

}
