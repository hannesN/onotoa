/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.CommentComparator;

import junit.framework.Assert;

public class CommentTest extends NodeTest {

	private Comment testObject1;
	private Comment testObject2;
	private CommentComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createComment();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createComment();

		comp = new CommentComparator();
	}

	@After
	public void shutdown() {
		testObject1 = null;
		testObject2 = null;
	}

	/**
	 * Test compares Object with NULL and proves the equality of two NULL
	 * Objects
	 */

	@Test
	public void nullTest() {

		Assert.assertFalse(comp.equals(testObject1, null));
		Assert.assertFalse(comp.equals(null, testObject1));
		Assert.assertTrue(comp.equals(null, null));
	}

	/**
	 * Test compares two objects with different and same IDs.
	 */

	@Test
	public void idTest() {

		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject1.setId(testObject2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Test compares two objects with different and same contents
	 */

	@Test
	public void contentTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setContent("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setContent("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same vales for the x
	 * position. DEFAULT = 0
	 */

	@Test
	public void posXTEst() {

		testObject1.setId(testObject2.getId());

		testObject1.setPosX(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setPosX(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same vales for the y
	 * position. DEFAULT = 0
	 */

	@Test
	public void posYTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setPosY(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setPosY(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same heights. DEFAULT = 0
	 */

	@Test
	public void heightTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setHeight(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setHeight(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same widths. DEFAULT = 0
	 */

	@Test
	public void widthTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setWidth(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setWidth(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

} // CommentTest
