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
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.compare.TopicTypeComparator;

public class TopicTypeTest {

	private TopicType testObject1;
	private TopicType testObject2;
	private TopicTypeComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createTopicType();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createTopicType();

		comp = new TopicTypeComparator();
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
	 * Method compares objects with different and equal ID types. Default ID
	 * type: identifier.
	 */

	@Test
	public void idTypeTest() {

		testObject1.setId(testObject2.getId());

		TopicId topicId1 = TopicId.SUBJECT_IDENTIFIER;
		TopicId topicId2 = TopicId.ITEM_IDENTIFIER;

		// default <-> default test
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		// id1 <-> id2 test
		testObject1.setIdType(topicId1);
		testObject2.setIdType(topicId2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		// id1 <-> id1 test
		testObject1.setIdType(topicId2);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Method compares objects with different and equal names.
	 */

	@Test
	public void testName() {

		testObject1.setId(testObject2.getId());

		testObject1.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Method compares two objects with different and equal abstract booleans.
	 */

	@Test
	public void testAbstract() {

		testObject1.setId(testObject2.getId());

		testObject1.setAbstract(true);
		testObject2.setAbstract(false);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject1.setAbstract(false);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Method compares objects depending on their isa lists.
	 */

	@Test
	public void isaTest() {

		testObject1.setId(testObject2.getId());

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		// add same entry to both list
		testObject1.getIsa().add(topicType1);
		testObject2.getIsa().add(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// add 2 new entries to list 1
		testObject1.getIsa().add(topicType2);
		testObject1.getIsa().add(topicType3);
		// testObject1.getIsa().add(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both lists the same
		testObject2.getIsa().add(topicType2);
		// testObject2.getIsa().add(topicType3);
		testObject2.getIsa().add(topicType4);
		topicType4.setId(topicType3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit name of list entry #2
		topicType3.setName("TMCL");
		System.out.println(testObject1.getIsa().get(2).getName());
		System.out.println(testObject2.getIsa().get(2).getName());
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// edit ID type of list entry #2
		TopicId topicId = TopicId.SUBJECT_IDENTIFIER;
		topicType3.setIdType(topicId);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setIdType(topicId);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Method compares objects depending on their ako lists.
	 */

	@Test
	public void akoTest() {

		testObject1.setId(testObject2.getId());

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		testObject1.getAko().add(topicType1);
		testObject2.getAko().add(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		testObject1.getAko().add(topicType2);
		testObject1.getAko().add(topicType3);
		// testObject1.getAko().add(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		testObject2.getAko().add(topicType2);
		// testObject2.getAko().add(topicType3);
		testObject2.getAko().add(topicType4);
		topicType4.setId(topicType3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		topicType3.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		TopicId topicId = TopicId.SUBJECT_IDENTIFIER;
		topicType3.setIdType(topicId);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setIdType(topicId);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}
	
	/**
	 * Method compares objects depending on their overlap lists.
	 */

	@Test
	public void overlapTest() {

		testObject1.setId(testObject2.getId());

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		testObject1.getOverlap().add(topicType1);
		testObject2.getOverlap().add(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		testObject1.getOverlap().add(topicType2);
		testObject1.getOverlap().add(topicType3);
		// testObject1.getOverlap().add(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		testObject2.getOverlap().add(topicType2);
		// testObject2.getOverlap().add(topicType3);
		testObject2.getOverlap().add(topicType4);
		topicType4.setId(topicType3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		topicType3.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		TopicId topicId = TopicId.SUBJECT_IDENTIFIER;
		topicType3.setIdType(topicId);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setIdType(topicId);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}


} // TopicTypeTest
