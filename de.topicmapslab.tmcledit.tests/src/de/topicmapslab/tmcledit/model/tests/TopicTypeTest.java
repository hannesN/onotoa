/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.compare.TopicTypeComparator;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Topic Type</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class TopicTypeTest  {

	private TopicType testObject1;
	private TopicType testObject2;
	private TopicTypeComparator comp;

	@Before
	public void startUp() {
		testObject1 = ModelFactory.eINSTANCE.createTopicType();
		testObject2 = ModelFactory.eINSTANCE.createTopicType();
		
		testObject1.setId(testObject2.getId());
		comp = new TopicTypeComparator();
	}
	
	public void testTopicType() {

		TopicType testObject1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType testObject2 = ModelFactory.eINSTANCE.createTopicType();

		TopicTypeComparator comp = new TopicTypeComparator();

//		super.allTests(testObject1, testObject2, comp);

		// set abstract
		testAbstract(testObject1, testObject2, comp);

		// set name
		testName(testObject1, testObject2, comp);

		// set id type
		idTypeTest(testObject1, testObject2, comp);

		// set isa
//		isaTest(testObject1, testObject2, comp);
		
		// set 

	}

	/**
	 * Method compares objects depending on their isa lists.
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */
	@Test
	public void isaTest() {

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		testObject1.getIsa().add(topicType1);
		testObject2.getIsa().add(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		testObject1.getIsa().add(topicType2);
		testObject1.getIsa().add(topicType3);
		// testObject1.getIsa().add(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		testObject2.getIsa().add(topicType2);
		// testObject2.getIsa().add(topicType3);
		testObject2.getIsa().add(topicType4);
		topicType4.setId(topicType3.getId());
		Assert.assertTrue("Checked isa changes", comp.equals(testObject1, testObject2));

		topicType3.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Method compares objects with different and equal id types.
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */

	protected void idTypeTest(TopicType testObject1, TopicType testObject2,
			TopicTypeComparator comp) {

		TopicId topicId1 = TopicId.IDENTIFIER;
		TopicId topicId2 = TopicId.ITEM_IDENTIFIER;

		// null <-> null test
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		// null <-> id test
		testObject1.setIdType(topicId1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		// id1 <-> id2 test
		testObject2.setIdType(topicId2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		// id1 <-> id1 test
		testObject1.setIdType(topicId2);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Method compares objects with different and equal names.
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */

	protected void testName(TopicType testObject1, TopicType testObject2,
			TopicTypeComparator comp) {
		testObject1.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Method compares two objects with different and equal abstract booleans.
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */

	protected void testAbstract(TopicType testObject1, TopicType testObject2,
			TopicTypeComparator comp) {
		testObject1.setAbstract(true);
		testObject2.setAbstract(false);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject1.setAbstract(false);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

} // TopicTypeTest
