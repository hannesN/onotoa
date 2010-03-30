/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.AnnotationComparator;

import junit.framework.Assert;

public class AnnotationTest extends OnoObjectTest {

	private Annotation testObject1;
	private Annotation testObject2;
	private AnnotationComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createAnnotation();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createAnnotation();

		comp = new AnnotationComparator();
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
	 * Test compares two objects with different and same keys.
	 */

	@Test
	public void keyTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setKey("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setKey("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same values.
	 */

	@Test
	public void valueTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setValue("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setValue("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

} // AnnotationTest
