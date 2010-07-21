package de.topicmapslab.tmcledit.model.command.tests;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameCommand;

public class RenameCommandTest {

	private TopicType topicType;
	private RenameCommand command;

	@Before
	public void prepare() {

		if (topicType == null) {
			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.setName("oldName");
		}

		if (command == null)
			command = new RenameCommand(topicType, "newName");
	}

	@After
	public void shutdown() {

		topicType = null;
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
		Assert.assertEquals("newName", topicType.getName());

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
		Assert.assertEquals("oldName", topicType.getName());

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertEquals("newName", topicType.getName());

	}

}
