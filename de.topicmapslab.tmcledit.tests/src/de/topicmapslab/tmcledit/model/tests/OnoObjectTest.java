/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import junit.framework.Assert;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OnoObject;
import de.topicmapslab.tmcledit.model.compare.OnoObjectComparator;

public class OnoObjectTest {

	private OnoObject testObject1;
	private OnoObject testObject2;
	private OnoObjectComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createTMCLConstruct();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createTMCLConstruct();

		comp = new OnoObjectComparator();
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

} // OnoObjectTest
