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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.UpdatePrefixCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class UpdatePrefixCommandTest {

	private UpdatePrefixCommand command;
	private MappingElement me;
	private String oldKey;
	private String newKey;
	private String oldValue;
	private String newValue;

	@Before
	public void prepare() {

		if (oldKey == null)
			oldKey = "oldKey";

		if (oldValue == null)
			oldValue = "oldValue";

		if (newKey == null)
			newKey = "newKey";

		if (newValue == null)
			newValue = "newValue";

		if (me == null) {

			me = ModelFactory.eINSTANCE.createMappingElement();
			me.setKey(oldKey);
			me.setValue(oldValue);

		}

		if (command == null)
			command = new UpdatePrefixCommand(me, newKey, newValue);

	}

	@After
	public void shutdown() {

		oldKey = null;
		oldValue = null;
		newKey = null;
		newValue = null;
		me = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());
		
		command = new UpdatePrefixCommand(me, oldKey, null);
		Assert.assertTrue(command.canExecute());
		
		command = new UpdatePrefixCommand(me, newKey, null);
		Assert.assertTrue(command.canExecute());
		

		command = new UpdatePrefixCommand(me, null, oldValue);
		Assert.assertTrue(command.canExecute());
		
		command = new UpdatePrefixCommand(me, null, newValue);
		Assert.assertTrue(command.canExecute());
		
		command = new UpdatePrefixCommand(me, null, null);
		Assert.assertTrue(command.canExecute());
		
		command = new UpdatePrefixCommand(me, oldKey, oldValue);
		Assert.assertFalse(command.canExecute());
		
		me.setKey(null);
		me.setValue(null);
		command = new UpdatePrefixCommand(me, null, null);
		Assert.assertFalse(command.canExecute());
		
		command = new UpdatePrefixCommand(me, oldKey, null);
		Assert.assertTrue(command.canExecute());
		
		command = new UpdatePrefixCommand(me, newKey, null);
		Assert.assertTrue(command.canExecute());
		

		command = new UpdatePrefixCommand(me, null, oldValue);
		Assert.assertTrue(command.canExecute());
		
		command = new UpdatePrefixCommand(me, null, newValue);
		
		

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		
		Assert.assertTrue(me.getValue().equals(newValue));
		Assert.assertTrue(me.getKey().equals(newKey));

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
		Assert.assertTrue(command.canUndo());
		command.undo();
		
		Assert.assertTrue(me.getValue().equals(oldValue));
		Assert.assertTrue(me.getKey().equals(oldKey));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		
		Assert.assertTrue(me.getValue().equals(newValue));
		Assert.assertTrue(me.getKey().equals(newKey));

	}
}
