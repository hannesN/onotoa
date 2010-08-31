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
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.compare.TopicMapSchemaComparator;

import junit.framework.Assert;

public class TopicMapSchemaTest extends TMCLConstructTest {

	private TopicMapSchema testObject1;
	private TopicMapSchema testObject2;
	private TopicMapSchemaComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createTopicMapSchema();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createTopicMapSchema();

		comp = new TopicMapSchemaComparator();
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

	/**
	 * Test compares objects with different and equal base locator
	 */

	@Test
	public void baseLocatorTest() {

		testObject1.setId(testObject2.getId());

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setBaseLocator("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setBaseLocator("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setBaseLocator("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares objects with different and same name
	 */

	@Test
	public void nameTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setName("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares objects with different and same associationTypeConstraints
	 */

	@Test
	public void associationTypeConstraintTest() {

		testObject1.setId(testObject2.getId());

		AssociationTypeConstraint aTypeConstraint1 = ModelFactory.eINSTANCE
				.createAssociationTypeConstraint();
		AssociationTypeConstraint aTypeConstraint2 = ModelFactory.eINSTANCE
				.createAssociationTypeConstraint();
		AssociationTypeConstraint aTypeConstraint3 = ModelFactory.eINSTANCE
				.createAssociationTypeConstraint();
		AssociationTypeConstraint aTypeConstraint4 = ModelFactory.eINSTANCE
				.createAssociationTypeConstraint();

		// add the same AssociationTypeConstraints to both list, what is
		// impossible
		testObject1.getAssociationTypeConstraints().add(aTypeConstraint1);
		testObject2.getAssociationTypeConstraints().add(aTypeConstraint1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal AssociationTypeConstraints into list #1
		aTypeConstraint2.setId(aTypeConstraint1.getId());
		testObject1.getAssociationTypeConstraints().add(aTypeConstraint2);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getAssociationTypeConstraints().add(aTypeConstraint3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getAssociationTypeConstraints().add(aTypeConstraint4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		aTypeConstraint4.setId(aTypeConstraint3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit comment of list entry #2
		aTypeConstraint3.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		aTypeConstraint4.setComment("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		aTypeConstraint4.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set RolePlayerConstraint of list entry #2

		RolePlayerConstraint rPlayerConstraint1 = ModelFactory.eINSTANCE
				.createRolePlayerConstraint();
		RolePlayerConstraint rPlayerConstraint2 = ModelFactory.eINSTANCE
				.createRolePlayerConstraint();

		aTypeConstraint3.getPlayerConstraints().add(rPlayerConstraint1);
		aTypeConstraint4.getPlayerConstraints().add(rPlayerConstraint2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rPlayerConstraint2.setId(rPlayerConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		rPlayerConstraint1.setCardMax("1");
		rPlayerConstraint2.setCardMax("2");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rPlayerConstraint2.setCardMax("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();

		rPlayerConstraint1.setPlayer(topicType1);
		rPlayerConstraint2.setPlayer(topicType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType2.setId(topicType1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set TopicType of list entry #2
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		aTypeConstraint3.setType(topicType3);
		aTypeConstraint4.setType(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType3.setId(topicType4.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares objects with different and same includes
	 */

	@Test
	public void includeTest() {

		testObject1.setId(testObject2.getId());

		testObject1.getIncludes().add("TMCL");
		testObject1.getIncludes().add("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getIncludes().add("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getIncludes().add("TMQL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares objects with different and same mapping elements
	 */

	@Test
	public void mappingElementTest() {

		testObject1.setId(testObject2.getId());

		MappingElement mElement1 = ModelFactory.eINSTANCE
				.createMappingElement();
		MappingElement mElement2 = ModelFactory.eINSTANCE
				.createMappingElement();
		MappingElement mElement3 = ModelFactory.eINSTANCE
				.createMappingElement();
		MappingElement mElement4 = ModelFactory.eINSTANCE
				.createMappingElement();

		// add the same MappingElement to both list, what is
		// impossible
		testObject1.getMappings().add(mElement1);
		testObject2.getMappings().add(mElement1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal MappingElement into list #1
		testObject1.getMappings().add(mElement2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mElement2.setId(mElement1.getId());

		// test different list lengths and different entries
		testObject1.getMappings().add(mElement3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getMappings().add(mElement4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		mElement4.setId(mElement3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit key of list entry #2
		mElement3.setKey("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mElement4.setKey("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mElement4.setKey("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit value of list entry #2
		mElement3.setValue("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mElement4.setValue("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		mElement4.setValue("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same topic types
	 */

	@Test
	public void topicTypeTest() {

		testObject1.setId(testObject2.getId());

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		// add the same TopicType to both list, what is
		// impossible
		testObject1.getTopicTypes().add(topicType1);
		testObject2.getTopicTypes().add(topicType1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal MappingElement into list #1
		testObject1.getTopicTypes().add(topicType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType2.setId(topicType1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getTopicTypes().add(topicType3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getTopicTypes().add(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		topicType4.setId(topicType3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// change some values of the list entry #2
		topicType3.setAbstract(true);
		topicType4.setAbstract(false);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setAbstract(true);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		topicType3.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setName("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType4.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		TopicId topicId1 = TopicId.SUBJECT_IDENTIFIER;
		TopicId topicId2 = TopicId.ITEM_IDENTIFIER;

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		topicType3.setIdType(topicId1);
		topicType4.setIdType(topicId2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicType3.setIdType(topicId2);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

} // TopicMapSchemaTest
