package de.topicmapslab.tmcledit.model.command.tests;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.commands.RenameCommand;

public class RenameCommandTest {

	private TopicType topicType;
	private RenameCommand renameCommand;

	@Before
	public void prepare() {

		if (topicType == null) {
			topicType = ModelFactory.eINSTANCE.createTopicType();
			topicType.setName("oldName");
		}

		if (renameCommand == null)
			renameCommand = new RenameCommand(topicType, "newName");
	}

	@After
	public void shutdown() {

		topicType = null;
		renameCommand = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(renameCommand.canExecute());

	}

	@Test
	public void executeTest() {

		renameCommand.execute();
		Assert.assertEquals("newName", topicType.getName());

	}

	@Test
	public void canUndo() {

		Assert.assertTrue(renameCommand.canUndo());

	}

	@Test
	public void undoTest() {

		renameCommand.undo();
		Assert.assertEquals("oldName", topicType.getName());

	}

	@Test
	public void redoTest() {

		renameCommand.redo();
		Assert.assertEquals("newName", topicType.getName());

	}

}
