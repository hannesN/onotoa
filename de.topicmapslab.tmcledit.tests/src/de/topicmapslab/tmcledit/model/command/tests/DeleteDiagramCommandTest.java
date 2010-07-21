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
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.DeleteDiagramCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteDiagramCommandTest {

	private Diagram diagram0;
	private Diagram diagram1;
	private DeleteDiagramCommand command;
	private File file;
	private List<Diagram> list;
	private int size;

	@Before
	public void prepare() {

		if (diagram0 == null)
			diagram0 = ModelFactory.eINSTANCE.createDiagram();

		if (diagram1 == null)
			diagram1 = ModelFactory.eINSTANCE.createDiagram();

		if (file == null) {

			file = ModelFactory.eINSTANCE.createFile();
			file.getDiagrams().add(diagram0);
			file.getDiagrams().add(diagram1);

		}

		if (command == null)
			command = new DeleteDiagramCommand(diagram0);

	}

	@After
	public void shutdown() {

		list = null;
		file = null;
		diagram0 = null;
		command = null;

	}

	@Test
	public void canExecute() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		Assert.assertTrue(file.getDiagrams().contains(diagram0));

		size = file.getDiagrams().size();
		list = new ArrayList<Diagram>(file.getDiagrams());
		list.remove(diagram0);

		command.execute();

		Assert.assertTrue((size - 1) == file.getDiagrams().size());
		Assert.assertFalse(file.getDiagrams().contains(diagram0));
		Assert.assertTrue(Tools.diagramListCompare(list, file.getDiagrams()));

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

		size = file.getDiagrams().size();
		list = new ArrayList<Diagram>(file.getDiagrams());

		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();

		Assert.assertTrue(size == file.getDiagrams().size());
		Assert.assertTrue(file.getDiagrams().contains(diagram0));
		Assert.assertTrue(Tools.diagramListCompare(list, file.getDiagrams()));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();

		size = file.getDiagrams().size();
		list = new ArrayList<Diagram>(file.getDiagrams());

		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(size == file.getDiagrams().size());
		Assert.assertFalse(file.getDiagrams().contains(diagram0));
		Assert.assertTrue(Tools.diagramListCompare(list, file.getDiagrams()));

	}

}
