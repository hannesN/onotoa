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

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.CreateNewDiagramCommand;

public class CreateNewDiagramCommandTest {

	private CreateNewDiagramCommand command;
	private String name = "TMCL";
	private File file;
	private Diagram diagram;
	private List<Diagram> list;
	private int size;
	private int id;

	@Before
	public void prepare() {

		if (file == null)
			file = ModelFactory.eINSTANCE.createFile();

		if (diagram == null) {

			diagram = ModelFactory.eINSTANCE.createDiagram();
			diagram.setName(name);

		}

		if (command == null)
			command = new CreateNewDiagramCommand(name, file);

	}

	@After
	public void shutdown() {

		name = null;
		file = null;
		diagram = null;
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

		size = file.getDiagrams().size();
		list = new ArrayList<Diagram>(file.getDiagrams());

		command.execute();
		id = file.getDiagrams().get(size).getId();
		diagram.setId(id);
		list.add(diagram);

		Assert.assertTrue((size + 1) == file.getDiagrams().size());
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
		Assert.assertTrue(Tools.diagramListCompare(list, file.getDiagrams()));

	}

}
