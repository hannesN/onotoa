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
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.compare.OccurrenceTypeConstraintComparator;
import junit.framework.Assert;

public class OccurrenceTypeConstraintTest {

	private OccurrenceTypeConstraint testObject1;
	private OccurrenceTypeConstraint testObject2;
	private OccurrenceTypeConstraintComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE
					.createOccurrenceTypeConstraint();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE
					.createOccurrenceTypeConstraint();

		comp = new OccurrenceTypeConstraintComparator();
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
	 * Test compares two objects with differnet and equal see_Also values.
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
	}

	/**
	 * Test compares two objects with different and same cardMin values. Default
	 * = 0
	 */

	@Test
	public void cardMinTest() {

		testObject1.setId(testObject2.getId());

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setCardMin("1");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setCardMin("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same cardMax values. Default
	 * = *
	 */

	@Test
	public void cardMaxTest() {

		testObject1.setId(testObject2.getId());

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setCardMax("2");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setCardMax("2");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same topic types
	 */

	@Test
	public void topicTypetest() {

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		testObject1.setId(testObject2.getId());

		// set different topic types
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setType(topicType1);
		testObject2.setType(topicType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both topic types the same
		topicType1.setId(topicType2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// change some values of the topic types
		topicType1.setAbstract(true);
		topicType2.setAbstract(false);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType2.setAbstract(true);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		topicType1.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType2.setName("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType2.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		TopicId topicId1 = TopicId.SUBJECT_IDENTIFIER;
		TopicId topicId2 = TopicId.ITEM_IDENTIFIER;

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		topicType1.setIdType(topicId1);
		topicType2.setIdType(topicId2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType1.setIdType(topicId2);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

} // OccurrenceTypeConstraintTest
