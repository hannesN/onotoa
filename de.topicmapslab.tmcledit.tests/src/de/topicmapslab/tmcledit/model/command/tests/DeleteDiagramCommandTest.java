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

import org.eclipse.emf.common.command.AbstractCommand;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.DeleteDiagramCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class DeleteDiagramCommandTest {

	private Diagram dia;
	private DeleteDiagramCommand delDia;
	private File file;
	private int size;

	@Before
	public void prepare() {

		if (dia == null) {

			dia = ModelFactory.eINSTANCE.createDiagram();
			file = ModelFactory.eINSTANCE.createFile();
			file.getDiagrams().add(dia);

		}

		if (delDia == null) {

			delDia = new DeleteDiagramCommand(dia);

		}

	}

	@After
	public void shutdown() {

		dia = null;
		delDia = null;

	}

	@Test
	public void canExecute() {

		Assert.assertTrue(delDia.canExecute());

	}

	@Test
	public void executeTest() {

		size = file.getDiagrams().size();
		delDia.execute();
		Assert.assertTrue((size - 1) == file.getDiagrams().size());
		Assert.assertFalse(file.getDiagrams().contains(dia));
		 size = file.getDiagrams().size();
		 delDia.undo();
		 Assert.assertTrue((size + 1) == file.getDiagrams().size());
		 Assert.assertTrue(file.getDiagrams().contains(dia));

	}

//	@Test
//	public void undoTest() {
//
//		size = file.getDiagrams().size();
//		delDia.undo();
//		Assert.assertTrue((size + 1) == file.getDiagrams().size());
//		Assert.assertTrue(file.getDiagrams().contains(dia));
//
//	}

	// private final File file;
	// private final Diagram diagram;
	// private final int index;
	//	
	//	
	// public DeleteDiagramCommandTest(Diagram diagram) {
	// super();
	// this.diagram = diagram;
	// this.file = (File) diagram.eContainer();
	// this.index = file.getDiagrams().indexOf(diagram);
	// }
	//
	// public void execute() {
	// file.getDiagrams().remove(diagram);
	// }
	//
	// @Override
	// public void undo() {
	// file.getDiagrams().add(index, diagram);
	// }
	//
	// public void redo() {
	// execute();
	// }
	//	
	// public int getIndex() {
	// return index;
	// }
	//
	// @Override
	// protected boolean prepare() {
	// return true;
	// }
}
