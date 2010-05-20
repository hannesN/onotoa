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
import de.topicmapslab.tmcledit.model.commands.ModifyAnnotationKeyCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class ModifyAnnotationKeyCommandTest {

	private ModifyAnnotationKeyCommand command;
	private Annotation annotation;
	private String newKey;
	private String oldKey;

	@Before
	public void prepare() {

		if (newKey == null)
			newKey = "newKey";

		if (oldKey == null)
			oldKey = "oldKey";

		if (annotation == null) {

			annotation = ModelFactory.eINSTANCE.createAnnotation();
			annotation.setKey(oldKey);
		}

		if (command == null)
			command = new ModifyAnnotationKeyCommand(annotation, newKey);
	}

	@After
	public void shutdown() {

		newKey = null;
		oldKey = null;
		annotation = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertEquals(newKey, annotation.getKey());

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
		Assert.assertEquals(oldKey, annotation.getKey());

	}

	@Test
	public void redoTest() {

		command.redo();
		Assert.assertEquals(newKey, annotation.getKey());

	}

}
