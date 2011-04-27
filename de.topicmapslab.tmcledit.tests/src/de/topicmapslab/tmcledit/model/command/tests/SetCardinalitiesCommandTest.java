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

import de.topicmapslab.tmcledit.model.AbstractCardinalityConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.commands.SetCardinalitiesCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetCardinalitiesCommandTest {

	private SetCardinalitiesCommand command;
	private AbstractCardinalityConstraint cardinalityContraint;
	private String newMax = "2";
	private String newMin = "1";
	private String oldMin = "0";
	private String oldMax = "*";
	private boolean exception = false;

	@Before
	public void prepare() {

		if (cardinalityContraint == null) {

			cardinalityContraint = ModelFactory.eINSTANCE
					.createAbstractTypedCardinalityConstraint();
			cardinalityContraint.setCardMin(oldMin);
			cardinalityContraint.setCardMax(oldMax);
		}

		if (command == null)
			command = new SetCardinalitiesCommand(cardinalityContraint, newMin,
					newMax);

	}

	@After
	public void shutdown() {

		oldMax = null;
		newMax = null;
		oldMin = null;
		newMin = null;
		cardinalityContraint = null;
		command = null;

	}

	@Test
	public void canExecuteTest() {

		Assert.assertTrue(command.canExecute());

		// old == new
		command = new SetCardinalitiesCommand(cardinalityContraint, oldMin,
				oldMax);
		Assert.assertFalse(command.canExecute());

		// min == max == *
		command = new SetCardinalitiesCommand(cardinalityContraint, "*", "*");
		Assert.assertFalse(command.canExecute());

		// ExecetionTest
		try {
			command = new SetCardinalitiesCommand(cardinalityContraint, "min",
					"max");
			command.canExecute();
		} catch (Exception e) {
			exception = true;
		}
		Assert.assertTrue(exception);

	}

	@Test
	public void executeTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(newMax)
				&& cardinalityContraint.getCardMin().equals(newMin));

		command = new SetCardinalitiesCommand(cardinalityContraint, "2", "1");
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals("1")
				&& cardinalityContraint.getCardMin().equals("1"));

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

		Assert.assertTrue(cardinalityContraint.getCardMax().equals(oldMax)
				&& cardinalityContraint.getCardMin().equals(oldMin));

	}

	@Test
	public void redoTest() {

		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();

		Assert.assertTrue(cardinalityContraint.getCardMax().equals(newMax)
				&& cardinalityContraint.getCardMin().equals(newMin));

	}

}
