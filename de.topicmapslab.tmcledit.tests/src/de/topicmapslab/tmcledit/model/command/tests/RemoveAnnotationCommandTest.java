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
import de.topicmapslab.tmcledit.model.commands.RemoveAnnotationCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class RemoveAnnotationCommandTest {

	private RemoveAnnotationCommand command;
	private TMCLConstruct construct;
	private Annotation annotation0;
	private Annotation annotation1;
	private Annotation falseAnnotation;
	private List<Annotation> list;
	private int size;

	@Before
	public void prepare() {

		if (annotation0 == null)
			annotation0 = ModelFactory.eINSTANCE.createAnnotation();

		if (annotation1 == null)
			annotation1 = ModelFactory.eINSTANCE.createAnnotation();

		if (falseAnnotation == null)
			falseAnnotation = ModelFactory.eINSTANCE.createAnnotation();

		if (construct == null) {

			construct = ModelFactory.eINSTANCE.createTMCLConstruct();
			construct.getAnnotations().add(annotation0);
			construct.getAnnotations().add(annotation1);

		}

		if (command == null)
			command = new RemoveAnnotationCommand(construct, annotation0);

	}

	@After
	public void shutdown() {

		construct = null;
		annotation0 = null;
		annotation1 = null;
		falseAnnotation = null;
		list = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());
		command = new RemoveAnnotationCommand(construct, falseAnnotation);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());

		size = construct.getAnnotations().size();
		list = new ArrayList<Annotation>(construct.getAnnotations());
		list.remove(annotation0);

		command.execute();

		Assert.assertTrue((size - 1) == construct.getAnnotations().size());
		Assert.assertTrue(Tools.annotationsListCompare(list, construct
				.getAnnotations()));

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
