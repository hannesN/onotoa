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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.ModifyAnnotationValueCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ModifyAnnotationValueCommandTest {

	private ModifyAnnotationValueCommand command;
	private Annotation annotation;
	private String newValue;
	private String oldValue;

	@Before
	public void prepare() {

		if (newValue == null)
			newValue = "newValue";

		if (oldValue == null)
			oldValue = "oldValue";

		if (annotation == null) {

			annotation = ModelFactory.eINSTANCE.createAnnotation();
			annotation.setValue(oldValue);

		}

		if (command == null)
			command = new ModifyAnnotationValueCommand(annotation, newValue);

	}

	@After
	public void shutdown() {

		newValue = null;
		oldValue = null;
		annotation = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		annotation.setValue(newValue);
		command = new ModifyAnnotationValueCommand(annotation, newValue);
		Assert.assertFalse(command.canExecute());

		annotation.setValue(null);
		command = new ModifyAnnotationValueCommand(annotation, newValue);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertEquals(newValue, annotation.getValue());

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
		command.execute();
		command.undo();
		Assert.assertEquals(oldValue, annotation.getValue());

	}

	@Test
	public void redoTest() {

		command.redo();
		Assert.assertEquals(newValue, annotation.getValue());

	}

}
