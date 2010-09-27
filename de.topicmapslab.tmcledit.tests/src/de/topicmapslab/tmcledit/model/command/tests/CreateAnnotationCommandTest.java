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

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.commands.CreateAnnotationCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CreateAnnotationCommandTest {

	private TMCLConstruct construct;
	private CreateAnnotationCommand command;
	private Annotation annotation;
	private List<Annotation> list;
	private int size;
	private String key = "TMCL";
	private String value = "TMQL";

	@Before
	public void prepare() {

		if (construct == null) {

			construct = ModelFactory.eINSTANCE.createTMCLConstruct();

		}

		if (command == null) {

			command = new CreateAnnotationCommand(construct, key, value);

		}

		if (annotation == null) {

			annotation = ModelFactory.eINSTANCE.createAnnotation();
			annotation.setKey(key);
			annotation.setValue(value);
		}

	}

	@After
	public void shutdown() {

		annotation = null;
		construct = null;
		key = null;
		value = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		size = construct.getAnnotations().size();
		list = new ArrayList<Annotation>(construct.getAnnotations());

		command.execute();

		annotation.setId(construct.getAnnotations().get(size).getId());
		list.add(annotation);

		Assert.assertTrue((size + 1) == construct.getAnnotations().size());
		Assert.assertTrue(Tools.annotationsListCompare(list, construct
				.getAnnotations()));

	}

	@Test
	public void canUndoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTest() {

		Assert.assertTrue(command.canExecute());

		size = construct.getAnnotations().size();
		list = new ArrayList<Annotation>(construct.getAnnotations());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == construct.getAnnotations().size());
		Assert.assertTrue(Tools.annotationsListCompare(list, construct
				.getAnnotations()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		size = construct.getAnnotations().size();
		list = new ArrayList<Annotation>(construct.getAnnotations());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == construct.getAnnotations().size());
		Assert.assertTrue(Tools.annotationsListCompare(list, construct
				.getAnnotations()));

	}

}
