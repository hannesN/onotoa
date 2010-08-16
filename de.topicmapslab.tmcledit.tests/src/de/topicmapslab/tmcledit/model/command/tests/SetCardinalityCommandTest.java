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
import de.topicmapslab.tmcledit.model.commands.SetCardinalityCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class SetCardinalityCommandTest {

	private SetCardinalityCommand command;
	private AbstractCardinalityConstraint cardinalityContraint;
	private boolean exception = false;
	private String newValue;
	private String oldMax;
	private String oldMin;

	@Before
	public void prepare() {

		if (oldMax == null)
			oldMax = "3";

		if (oldMin == null)
			oldMin = "2";

		if (cardinalityContraint == null) {

			cardinalityContraint = ModelFactory.eINSTANCE
					.createAbstractTypedCardinalityConstraint();
			cardinalityContraint.setCardMax(oldMax);
			cardinalityContraint.setCardMin(oldMin);
		}

	}

	@After
	public void shutdown() {

		exception = false;
		oldMax = null;
		oldMin = null;
		newValue = null;
		cardinalityContraint = null;
		command = null;

	}

	@Test
	public void canExecuteTestMin() {

		newValue = "2";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertFalse(command.canExecute());

		newValue = "3";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());

		newValue = "4";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());

		newValue = "*";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertFalse(command.canExecute());

	}

	@Test
	public void canExecuteTestMax() {

		newValue = "1";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertFalse(command.canExecute());

		newValue = "2";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());

		newValue = "3";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertFalse(command.canExecute());

		newValue = "4";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());

		newValue = "*";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());

	}

	@Test
	public void executeTestMin() {

		// ExecetionTest
		try {
			command = new SetCardinalityCommand(cardinalityContraint, true,
					"TMCL");
			command.canExecute();
			command.execute();
		} catch (Exception e) {
			exception = true;
		}
		Assert.assertTrue(exception);

		newValue = "0";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(oldMax));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(newValue));

		newValue = "3";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(newValue));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(newValue));

		newValue = "4";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(newValue));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(newValue));

	}

	@Test
	public void executeTestMax() {

		// ExecetionTest
		try {
			command = new SetCardinalityCommand(cardinalityContraint, false,
					"TMCL");
			command.canExecute();
			command.execute();
		} catch (RuntimeException e) {
			exception = true;
		}
		Assert.assertTrue(exception);

		newValue = "2";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(newValue));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(oldMin));

		newValue = "4";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(newValue));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(oldMin));

	}

	@Test
	public void canUndoMin() {

		newValue = "1";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void canUndoMax() {

		newValue = "4";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());

	}

	@Test
	public void undoTestMin() {

		newValue = "1";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(oldMax));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(oldMin));

	}

	@Test
	public void undoTestMax() {

		newValue = "4";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(oldMax));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(oldMin));

	}

	@Test
	public void redoTestMin() {

		newValue = "1";
		command = new SetCardinalityCommand(cardinalityContraint, true,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(oldMax));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(newValue));

	}

	@Test
	public void redoTestMax() {

		newValue = "4";
		command = new SetCardinalityCommand(cardinalityContraint, false,
				newValue);
		Assert.assertTrue(command.canExecute());
		command.execute();
		Assert.assertTrue(command.canUndo());
		command.undo();
		command.redo();
		Assert.assertTrue(cardinalityContraint.getCardMax().equals(newValue));
		Assert.assertTrue(cardinalityContraint.getCardMin().equals(oldMin));

	}

}
