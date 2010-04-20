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

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.CreateDiagramCommand;

public class CreateDiagramCommandTest {

	private String name = "myDiagram";
	private CreateDiagramCommand command;
	private File file;
	private int size;

	@Before
	public void prepare() {

		if (file == null)
			file = ModelFactory.eINSTANCE.createFile();

		if (command == null)
			command = new CreateDiagramCommand(name, file);

	}

	@After
	public void shutdown() {

		command = null;
		file = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTest() {

		size = file.getDiagrams().size();
		Assert.assertFalse(contains(file));
		assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue((size + 1) == file.getDiagrams().size());
		Assert.assertTrue(contains(file));

	}
	
	@Test
	public void canUndoTest(){
		
		Assert.assertTrue(command.canUndo());
		
	}

	@Test
	public void undoTest() {

		assertTrue(command.canExecute());
		command.execute();

	}

	@Test
	public void redoTest() {
		
		assertTrue(command.canExecute());
		command.execute();
		command.undo();

	}

	private boolean contains(File file) {

		Diagram dia;

		for (int i = 0; i < file.getDiagrams().size(); i++) {

			dia = file.getDiagrams().get(i);
			if (dia.getName() == this.name)
				return true;

		}

		return false;

	}

	// private final String name;
	// private final File file;
	//	
	// private Diagram diagram;
	//	
	//	
	//	
	// public CreateDiagramCommandTest(String name, File file) {
	// super();
	// this.name = name;
	// this.file = file;
	// }
	//	
	// public void execute() {
	// file.getDiagrams().add(diagram);
	// }
	//	
	// @Override
	// public void undo() {
	// file.getDiagrams().remove(diagram);
	// }
	//	
	// @Override
	// protected boolean prepare() {
	// diagram = ModelFactory.eINSTANCE.createDiagram();
	// diagram.setName(name);
	// return true;
	// }
	//	
	// public void redo() {
	// execute();
	// }
	//	
	// @Override
	// public String getLabel() {
	// return "Create new diagram";
	// }
	//	
	// public Diagram getDiagram() {
	// return diagram;
	// }
}
