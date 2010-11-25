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
import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.compare.TMCLConstructComparator;

public class TMCLConstructTest {

	private TMCLConstruct testObject1;
	private TMCLConstruct testObject2;
	private TMCLConstructComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createTMCLConstruct();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createTMCLConstruct();

		comp = new TMCLConstructComparator();
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
	 * Test compares two objects with different and equal see_Also values. 
	 */
	
	@Test
	public void seeAlsoTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Test compares two objects with different and same descriptions.
	 */
	
	@Test
	public void descriptionTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setDescription("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setDescription("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Test compares two objects with different and same comments.
	 */
	
	@Test
	public void commentTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Test compares objects with different and equal annotations. 
	 */
	
	@Test
	public void annotationTest() {

		testObject1.setId(testObject2.getId());
		Annotation annotation1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation2 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation3 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation4 = ModelFactory.eINSTANCE.createAnnotation();

		// add annotation to testObject1
		annotation1.setKey("TMCL");
		annotation1.setValue("TMCL");
		testObject1.getAnnotations().add(annotation1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add a different annotation to testObject2
		annotation2.setId(annotation1.getId());
		annotation2.setKey("TMC");
		annotation2.setValue("TMC");
		testObject2.getAnnotations().add(annotation2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both annotations the same
		annotation2.setKey("TMCL");
		annotation2.setValue("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// add the same annotation into both lists
		testObject1.getAnnotations().add(annotation3);
		testObject2.getAnnotations().add(annotation3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		
		// make list length equal, but at the first run not entry #1
		testObject1.getAnnotations().add(annotation4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		annotation4.setId(annotation3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
	}
} // TMCLConstructTest
