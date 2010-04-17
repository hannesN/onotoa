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

import org.junit.After;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.commands.CreateAnnotationCommand;

/**
 * @author Hannes Niederhausen
 * 
 */
public class CreateAnnotationCommandTest {

	private TMCLConstruct construct;
	private CreateAnnotationCommand createAnno;
	private int size;
	private String key = "TMCL";
	private String value = "TMQL";

	@Before
	public void prepare() {

		if (construct == null) {

			construct = ModelFactory.eINSTANCE.createTMCLConstruct();

		}

		if (createAnno == null) {

			createAnno = new CreateAnnotationCommand(construct, "TMCL", "TMQL");

		}

	}

	@After
	public void shutdown() {

		construct = null;

	}

	@Test
	public void tcanExecuteTest() {

		Assert.assertTrue(createAnno.canExecute());

	}

	@Test
	public void executeTest() {

		size = construct.getAnnotations().size();
		Assert.assertFalse(contains(construct));
		createAnno.execute();
		Assert.assertTrue((size + 1) == construct.getAnnotations().size());
		Assert.assertTrue(contains(construct));

	}

	@Test
	public void undoTest() {

		createAnno.execute();

		size = construct.getAnnotations().size();
		Assert.assertTrue(contains(construct));
		createAnno.undo();
		Assert.assertTrue((size - 1) == construct.getAnnotations().size());
		Assert.assertFalse(contains(construct));

	}

	@Test
	public void redoTest() {

		createAnno.execute();
		createAnno.undo();

		size = construct.getAnnotations().size();
		Assert.assertFalse(contains(construct));
		createAnno.redo();
		Assert.assertTrue((size + 1) == construct.getAnnotations().size());
		Assert.assertTrue(contains(construct));

	}

	private boolean contains(TMCLConstruct construct) {

		Annotation anno;

		for (int i = 0; i < construct.getAnnotations().size(); i++) {

			anno = construct.getAnnotations().get(i);
			if (anno.getKey() == this.key && anno.getValue() == this.value)
				return true;

		}

		return false;

	}

}
