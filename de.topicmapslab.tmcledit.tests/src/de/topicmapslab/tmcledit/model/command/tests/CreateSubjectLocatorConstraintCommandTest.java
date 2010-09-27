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
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.CreateSubjectLocatorConstraintCommand;

public class CreateSubjectLocatorConstraintCommandTest {

	private CreateSubjectLocatorConstraintCommand command;
	private TopicType topicType;
	private SubjectLocatorConstraint subjectLocatorConstraint;
	private List<SubjectLocatorConstraint> list;
	private int size;
	private int id;

	@Before
	public void prepare() {

		if (topicType == null)
			topicType = ModelFactory.eINSTANCE.createTopicType();

		if (subjectLocatorConstraint == null)
			subjectLocatorConstraint = ModelFactory.eINSTANCE
					.createSubjectLocatorConstraint();

	}

	@After
	public void shutdown() {

		topicType = null;
		subjectLocatorConstraint = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTestC0() {

		command = new CreateSubjectLocatorConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void canExecuteTestC1() {

		command = new CreateSubjectLocatorConstraintCommand(topicType,
				subjectLocatorConstraint);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestC0() {

		command = new CreateSubjectLocatorConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectLocatorConstraints().size();
		list = new ArrayList<SubjectLocatorConstraint>(topicType
				.getSubjectLocatorConstraints());

		command.execute();
		id = topicType.getSubjectLocatorConstraints().get(size).getId();
		subjectLocatorConstraint.setId(id);
		list.add(subjectLocatorConstraint);

		Assert.assertTrue((size + 1) == topicType
				.getSubjectLocatorConstraints().size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(list,
				topicType.getSubjectLocatorConstraints()));
	}

	@Test
	public void executeTestC1() {

		command = new CreateSubjectLocatorConstraintCommand(topicType,
				subjectLocatorConstraint);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectLocatorConstraints().size();
		list = new ArrayList<SubjectLocatorConstraint>(topicType
				.getSubjectLocatorConstraints());
		list.add(subjectLocatorConstraint);

		command.execute();

		Assert.assertTrue((size + 1) == topicType
				.getSubjectLocatorConstraints().size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(list,
				topicType.getSubjectLocatorConstraints()));

	}

	@Test
	public void canUndoTestC0() {

		command = new CreateSubjectLocatorConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoTestC1() {

		command = new CreateSubjectLocatorConstraintCommand(topicType,
				subjectLocatorConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestC0() {

		command = new CreateSubjectLocatorConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectLocatorConstraints().size();
		list = new ArrayList<SubjectLocatorConstraint>(topicType
				.getSubjectLocatorConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == topicType.getSubjectLocatorConstraints()
				.size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(list,
				topicType.getSubjectLocatorConstraints()));
	}

	@Test
	public void undoTestC1() {

		command = new CreateSubjectLocatorConstraintCommand(topicType,
				subjectLocatorConstraint);
		Assert.assertTrue(command.canExecute());

		size = topicType.getSubjectLocatorConstraints().size();
		list = new ArrayList<SubjectLocatorConstraint>(topicType
				.getSubjectLocatorConstraints());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == topicType.getSubjectLocatorConstraints()
				.size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(list,
				topicType.getSubjectLocatorConstraints()));

	}

	@Test
	public void redoTestC0() {

		command = new CreateSubjectLocatorConstraintCommand(topicType);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getSubjectLocatorConstraints().size();
		list = new ArrayList<SubjectLocatorConstraint>(topicType
				.getSubjectLocatorConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getSubjectLocatorConstraints()
				.size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(list,
				topicType.getSubjectLocatorConstraints()));
	}

	@Test
	public void redoTestC1() {

		command = new CreateSubjectLocatorConstraintCommand(topicType,
				subjectLocatorConstraint);
		Assert.assertTrue(command.canExecute());
		command.execute();

		size = topicType.getSubjectLocatorConstraints().size();
		list = new ArrayList<SubjectLocatorConstraint>(topicType
				.getSubjectLocatorConstraints());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == topicType.getSubjectLocatorConstraints()
				.size());
		Assert.assertTrue(Tools.subjectLocatorConstraintListCompare(list,
				topicType.getSubjectLocatorConstraints()));

	}

}