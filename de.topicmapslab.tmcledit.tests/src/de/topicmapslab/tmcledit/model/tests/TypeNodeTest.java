/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.compare.TypeNodeComparator;

import junit.framework.Assert;

public class TypeNodeTest extends NodeTest {

	private TypeNode testObject1;
	private TypeNode testObject2;
	private TypeNodeComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createTypeNode();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createTypeNode();

		comp = new TypeNodeComparator();
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
	 * Test compares two objects with different and same images
	 */

	@Test
	public void imageTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setImage("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setImage("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setImage("TMCL");
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
		testObject1.setTopicType(topicType1);
		testObject2.setTopicType(topicType2);
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

} // TypeNodeTest
