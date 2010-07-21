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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.File;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.commands.CreateEdgeCommand;

public class CreateEdgeCommandTest {

	private File file;
	private Diagram diagram;
	private Node sourceTypeNode;
	private Node sourceAssocNode;
	private Node targetNode;
	private CreateEdgeCommand command;
	private Edge edge;
	
	@Before
	public void init() {
		ModelFactory fac = ModelFactory.eINSTANCE;
		
		file = fac.createFile();
		file.setTopicMapSchema(fac.createTopicMapSchema());
		
		diagram = fac.createDiagram();
		diagram.setName("Test");
		
		sourceTypeNode = fac.createTypeNode();
		diagram.getNodes().add(sourceTypeNode);
		sourceAssocNode = fac.createAssociationNode();
		diagram.getNodes().add(sourceAssocNode);
		targetNode = fac.createTypeNode();
		diagram.getNodes().add(targetNode);
		
		edge = fac.createEdge();
		command = new CreateEdgeCommand(edge, diagram);
	}
	
	@Test
	public void testCanExecute() {
		// no source and no target
		assertFalse(command.canExecute());
		
		// no target
		edge.setSource(sourceTypeNode);
		assertFalse(command.canExecute());
		
		// with source and target
		edge.setTarget(targetNode);
		assertTrue(command.canExecute());
		
		// clear source/target
		edge.setSource(null);
		edge.setTarget(null);
		assertFalse(command.canExecute());
		
		// test with assoc source
		// no target
		edge.setSource(sourceAssocNode);
		assertFalse(command.canExecute());
		
		// with source and target
		edge.setTarget(targetNode);
		assertTrue(command.canExecute());
	}
	
}
