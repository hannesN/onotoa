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
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.NameTypeConstraint;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;
import de.topicmapslab.tmcledit.model.SubjectLocatorConstraint;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.compare.ReifiableTopicTypeComparator;

import junit.framework.Assert;

public class ReifiableTopicTypeTest extends TopicTypeTest {

	private ReifiableTopicType testObject1;
	private ReifiableTopicType testObject2;
	private ReifiableTopicTypeComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createReifiableTopicType();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createReifiableTopicType();

		comp = new ReifiableTopicTypeComparator();
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
	 * Test compares objects with different and equal ID types. Default ID type:
	 * identifier.
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
	 * Test compares objects with different and equal names.
	 */

	@Test
	public void nameTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setName("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setName("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Test compares two objects with different and equal abstract booleans.
	 */

	@Test
	public void abstractTest() {

		testObject1.setId(testObject2.getId());

		testObject1.setAbstract(true);
		testObject2.setAbstract(false);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject1.setAbstract(false);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Test compares objects with different and equal kindOf types. Default =
	 * Topic_Type
	 */

	@Test
	public void kindOfTest() {

		testObject1.setId(testObject2.getId());

		KindOfTopicType kindOfTopicType1 = KindOfTopicType.ASSOCIATION_TYPE;
		KindOfTopicType kindOfTopicType2 = KindOfTopicType.NAME_TYPE;

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setKind(kindOfTopicType1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setKind(kindOfTopicType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setKind(kindOfTopicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares objects depending on their isa lists.
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
	 * Test compares objects depending on their ako lists.
	 */

	@Test
	public void akoTest() {

		testObject1.setId(testObject2.getId());

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		// add same entry to both list
		testObject1.getAko().add(topicType1);
		testObject2.getAko().add(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// add 2 new entries to list 1
		testObject1.getAko().add(topicType2);
		testObject1.getAko().add(topicType3);
		// testObject1.getAko().add(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both lists the same
		testObject2.getAko().add(topicType2);
		// testObject2.getAko().add(topicType3);
		testObject2.getAko().add(topicType4);
		topicType4.setId(topicType3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit name of list entry #2
		topicType3.setName("TMCL");
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
	 * Test compares objects depending on their overlap lists.
	 */

	@Test
	public void overlapTest() {

		testObject1.setId(testObject2.getId());

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType3 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType4 = ModelFactory.eINSTANCE.createTopicType();

		// add same entry to both list
		testObject1.getOverlap().add(topicType1);
		testObject2.getOverlap().add(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// add 2 new entries to list 1
		testObject1.getOverlap().add(topicType2);
		testObject1.getOverlap().add(topicType3);
		// testObject1.getOverlap().add(topicType4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both lists the same
		testObject2.getOverlap().add(topicType2);
		// testObject2.getOverlap().add(topicType3);
		testObject2.getOverlap().add(topicType4);
		topicType4.setId(topicType3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit name of list entry #2
		topicType3.setName("TMCL");
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
	 * Test compares two objects depending on their locator list
	 */

	@Test
	public void locatorTest() {

		testObject1.setId(testObject2.getId());

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getLocators().add("TMCL");
		testObject1.getLocators().add("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getLocators().add("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getLocators().add("TMQL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects depending on their identifier list
	 */

	@Test
	public void identifierTest() {

		testObject1.setId(testObject2.getId());

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getIdentifiers().add("TMCL");
		testObject1.getIdentifiers().add("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getIdentifiers().add("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getIdentifiers().add("TMQL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	@Test
	public void occurrenceConstraintTest() {

		testObject1.setId(testObject2.getId());

		OccurrenceTypeConstraint oTypeConstraint1 = ModelFactory.eINSTANCE
				.createOccurrenceTypeConstraint();
		OccurrenceTypeConstraint oTypeConstraint2 = ModelFactory.eINSTANCE
				.createOccurrenceTypeConstraint();
		OccurrenceTypeConstraint oTypeConstraint3 = ModelFactory.eINSTANCE
				.createOccurrenceTypeConstraint();
		OccurrenceTypeConstraint oTypeConstraint4 = ModelFactory.eINSTANCE
				.createOccurrenceTypeConstraint();

		/*
		 * add the same OccurrenceTypeConstraint into both lists, which is not
		 * possible.
		 */
		testObject1.getOccurrenceConstraints().add(oTypeConstraint1);
		testObject2.getOccurrenceConstraints().add(oTypeConstraint1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal occurrence into list #1
		testObject1.getOccurrenceConstraints().add(oTypeConstraint2);
		oTypeConstraint2.setId(oTypeConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getOccurrenceConstraints().add(oTypeConstraint3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getOccurrenceConstraints().add(oTypeConstraint4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		oTypeConstraint4.setId(oTypeConstraint3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMax of list entry #2
		oTypeConstraint3.setCardMax("5");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		oTypeConstraint4.setCardMax("5");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMin of list entry #2
		oTypeConstraint3.setCardMin("1");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		oTypeConstraint4.setCardMin("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit see_Also of list entry #2
		oTypeConstraint3.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		oTypeConstraint4.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set topic types of list entry #2
		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();

		oTypeConstraint3.setType(topicType1);
		oTypeConstraint4.setType(topicType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		oTypeConstraint4.setType(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit annotation list for list entry #2
		Annotation annotation1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation2 = ModelFactory.eINSTANCE.createAnnotation();

		oTypeConstraint3.getAnnotations().add(annotation1);
		oTypeConstraint4.getAnnotations().add(annotation2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		annotation2.setId(annotation1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	@Test
	public void nameConstraintTest() {

		testObject1.setId(testObject2.getId());

		NameTypeConstraint nTypeConstraint1 = ModelFactory.eINSTANCE
				.createNameTypeConstraint();
		NameTypeConstraint nTypeConstraint2 = ModelFactory.eINSTANCE
				.createNameTypeConstraint();
		NameTypeConstraint nTypeConstraint3 = ModelFactory.eINSTANCE
				.createNameTypeConstraint();
		NameTypeConstraint nTypeConstraint4 = ModelFactory.eINSTANCE
				.createNameTypeConstraint();

		/*
		 * add the same NameTypeConstraint into both lists, which is not
		 * possible.
		 */
		testObject1.getNameConstraints().add(nTypeConstraint1);
		testObject2.getNameConstraints().add(nTypeConstraint1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal NameTypeConstraint into list #1
		testObject1.getNameConstraints().add(nTypeConstraint2);
		nTypeConstraint2.setId(nTypeConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getNameConstraints().add(nTypeConstraint3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getNameConstraints().add(nTypeConstraint4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		nTypeConstraint4.setId(nTypeConstraint3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMax of list entry #2
		nTypeConstraint3.setCardMax("5");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		nTypeConstraint4.setCardMax("5");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMin of list entry #2
		nTypeConstraint3.setCardMin("1");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		nTypeConstraint4.setCardMin("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit see_Also of list entry #2
		nTypeConstraint3.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		nTypeConstraint4.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set topic types of list entry #2
		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();

		nTypeConstraint3.setType(topicType1);
		nTypeConstraint4.setType(topicType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		nTypeConstraint4.setType(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit annotation list for list entry #2
		Annotation annotation1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation2 = ModelFactory.eINSTANCE.createAnnotation();

		nTypeConstraint3.getAnnotations().add(annotation1);
		nTypeConstraint4.getAnnotations().add(annotation2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		annotation2.setId(annotation1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	@Test
	public void subjectIdentifierConstraintTest() {

		testObject1.setId(testObject2.getId());

		SubjectIdentifierConstraint sIdentifierConstraint1 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		SubjectIdentifierConstraint sIdentifierConstraint2 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		SubjectIdentifierConstraint sIdentifierConstraint3 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();
		SubjectIdentifierConstraint sIdentifierConstraint4 = ModelFactory.eINSTANCE
				.createSubjectIdentifierConstraint();

		/*
		 * add the same SubjectIdentifierConstraint into both lists, which is
		 * not possible.
		 */
		testObject1.getSubjectIdentifierConstraints().add(
				sIdentifierConstraint1);
		testObject2.getSubjectIdentifierConstraints().add(
				sIdentifierConstraint1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal SubjectIdentifierConstraint into list #1
		testObject1.getSubjectIdentifierConstraints().add(
				sIdentifierConstraint2);
		sIdentifierConstraint2.setId(sIdentifierConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getSubjectIdentifierConstraints().add(
				sIdentifierConstraint3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getSubjectIdentifierConstraints().add(
				sIdentifierConstraint4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		sIdentifierConstraint4.setId(sIdentifierConstraint3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMax of list entry #2
		sIdentifierConstraint3.setCardMax("5");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sIdentifierConstraint4.setCardMax("5");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMin of list entry #2
		sIdentifierConstraint3.setCardMin("1");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sIdentifierConstraint4.setCardMin("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit see_Also of list entry #2
		sIdentifierConstraint3.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sIdentifierConstraint4.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set regular Expression for list entry #2
		sIdentifierConstraint3.setRegexp("TMCL");
		sIdentifierConstraint4.setRegexp("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sIdentifierConstraint4.setRegexp("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit annotation list for list entry #2
		Annotation annotation1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation2 = ModelFactory.eINSTANCE.createAnnotation();

		sIdentifierConstraint3.getAnnotations().add(annotation1);
		sIdentifierConstraint4.getAnnotations().add(annotation2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		annotation2.setId(annotation1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	@Test
	public void subjectLocatorConstraintTest() {

		testObject1.setId(testObject2.getId());

		SubjectLocatorConstraint sLocatorConstraint1 = ModelFactory.eINSTANCE
				.createSubjectLocatorConstraint();
		SubjectLocatorConstraint sLocatorConstraint2 = ModelFactory.eINSTANCE
				.createSubjectLocatorConstraint();
		SubjectLocatorConstraint sLocatorConstraint3 = ModelFactory.eINSTANCE
				.createSubjectLocatorConstraint();
		SubjectLocatorConstraint sLocatorConstraint4 = ModelFactory.eINSTANCE
				.createSubjectLocatorConstraint();

		/*
		 * add the same SubjectLocatorConstraint into both lists, which is not
		 * possible.
		 */
		testObject1.getSubjectLocatorConstraints().add(sLocatorConstraint1);
		testObject2.getSubjectLocatorConstraints().add(sLocatorConstraint1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal SubjectLocatorConstraint into list #1
		testObject1.getSubjectLocatorConstraints().add(sLocatorConstraint2);
		sLocatorConstraint2.setId(sLocatorConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getSubjectLocatorConstraints().add(sLocatorConstraint3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getSubjectLocatorConstraints().add(sLocatorConstraint4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		sLocatorConstraint4.setId(sLocatorConstraint3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMax of list entry #2
		sLocatorConstraint3.setCardMax("5");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sLocatorConstraint4.setCardMax("5");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMin of list entry #2
		sLocatorConstraint3.setCardMin("1");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sLocatorConstraint4.setCardMin("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit see_Also of list entry #2
		sLocatorConstraint3.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sLocatorConstraint4.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set regular Expression for list entry #2
		sLocatorConstraint3.setRegexp("TMCL");
		sLocatorConstraint4.setRegexp("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		sLocatorConstraint4.setRegexp("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit annotation list for list entry #2
		Annotation annotation1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation2 = ModelFactory.eINSTANCE.createAnnotation();

		sLocatorConstraint3.getAnnotations().add(annotation1);
		sLocatorConstraint4.getAnnotations().add(annotation2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		annotation2.setId(annotation1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	@Test
	public void topicReifiesConstraintTest() {

		testObject1.setId(testObject2.getId());

		TopicReifiesConstraint topicReifiesConstraint1 = ModelFactory.eINSTANCE
				.createTopicReifiesConstraint();
		TopicReifiesConstraint topicReifiesConstraint2 = ModelFactory.eINSTANCE
				.createTopicReifiesConstraint();
		TopicReifiesConstraint topicReifiesConstraint3 = ModelFactory.eINSTANCE
				.createTopicReifiesConstraint();
		TopicReifiesConstraint topicReifiesConstraint4 = ModelFactory.eINSTANCE
				.createTopicReifiesConstraint();

		/*
		 * add the same OccurrenceTypeConstraint into both lists, which is not
		 * possible.
		 */
		testObject1.getTopicReifiesConstraints().add(topicReifiesConstraint1);
		testObject2.getTopicReifiesConstraints().add(topicReifiesConstraint1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal occurrence into list #1
		testObject1.getTopicReifiesConstraints().add(topicReifiesConstraint2);
		topicReifiesConstraint2.setId(topicReifiesConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getTopicReifiesConstraints().add(topicReifiesConstraint3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getTopicReifiesConstraints().add(topicReifiesConstraint4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		topicReifiesConstraint4.setId(topicReifiesConstraint3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMax of list entry #2
		topicReifiesConstraint3.setCardMax("5");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicReifiesConstraint4.setCardMax("5");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMin of list entry #2
		topicReifiesConstraint3.setCardMin("1");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicReifiesConstraint4.setCardMin("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit see_Also of list entry #2
		topicReifiesConstraint3.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicReifiesConstraint4.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set topic types of list entry #2
		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();

		topicReifiesConstraint3.setType(topicType1);
		topicReifiesConstraint4.setType(topicType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		topicReifiesConstraint4.setType(topicType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit annotation list for list entry #2
		Annotation annotation1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation annotation2 = ModelFactory.eINSTANCE.createAnnotation();

		topicReifiesConstraint3.getAnnotations().add(annotation1);
		topicReifiesConstraint4.getAnnotations().add(annotation2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		annotation2.setId(annotation1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares objects with different and equal reifierConstraints.
	 */

	@Test
	public void reifierConstraintTest() {

		testObject1.setId(testObject2.getId());

		ReifierConstraint rConstraint1 = ModelFactory.eINSTANCE
				.createReifierConstraint();
		ReifierConstraint rConstraint2 = ModelFactory.eINSTANCE
				.createReifierConstraint();

		// set different and equal ReifierConstraints
		testObject1.setReifierConstraint(rConstraint1);
		testObject2.setReifierConstraint(rConstraint2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rConstraint2.setId(rConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit some values of the ReifierConstraints
		rConstraint1.setCardMax("1");
		rConstraint2.setCardMax("2");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rConstraint2.setCardMax("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		rConstraint1.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rConstraint2.setComment("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rConstraint2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		rConstraint1.setDescription("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rConstraint2.setDescription("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rConstraint2.setDescription("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
	}

} // ReifiableTopicTypeTest
