/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicId;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.compare.EdgeComparator;

import junit.framework.Assert;

public class EdgeTest extends OnoObjectTest {

	private Edge testObject1;
	private Edge testObject2;
	private EdgeComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createEdge();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createEdge();

		comp = new EdgeComparator();
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
	 * Test compares two objects with different and same sources.
	 */

	@Test
	public void sourceTest() {

		testObject1.setId(testObject2.getId());

		Node node1 = ModelFactory.eINSTANCE.createNode();
		Node node2 = ModelFactory.eINSTANCE.createNode();

		node1.setPosX(1);
		node1.setPosY(1);
		node1.setId(1);
		node2.setPosX(2);
		node2.setPosY(2);
		node2.setId(2);

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setSource(node1);
		testObject2.setSource(node2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		node2.setPosX(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node2.setPosY(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node2.setId(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same targets.
	 */

	@Test
	public void targetTest() {

		testObject1.setId(testObject2.getId());

		Node node1 = ModelFactory.eINSTANCE.createNode();
		Node node2 = ModelFactory.eINSTANCE.createNode();

		node1.setPosX(1);
		node1.setPosY(1);
		node1.setId(1);
		node2.setPosX(2);
		node2.setPosY(2);
		node2.setId(2);

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setTarget(node1);
		testObject2.setTarget(node2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		node2.setPosX(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node2.setPosY(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node2.setId(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same edge types
	 */

	@Test
	public void edgeTypeTest() {

		testObject1.setId(testObject2.getId());
		EdgeType edgeType1 = EdgeType.ROLE_CONSTRAINT_TYPE;
		EdgeType edgeType2 = EdgeType.AKO_TYPE;

		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setType(edgeType1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setType(edgeType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setType(edgeType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same lists of label
	 * positions
	 */

	@Test
	public void labelPositionTest() {

		testObject1.setId(testObject2.getId());

		LabelPos labelPos1 = ModelFactory.eINSTANCE.createLabelPos();
		LabelPos labelPos2 = ModelFactory.eINSTANCE.createLabelPos();
		LabelPos labelPos3 = ModelFactory.eINSTANCE.createLabelPos();
		LabelPos labelPos4 = ModelFactory.eINSTANCE.createLabelPos();

		/*
		 * add the same labelPos into both lists, which is not possible.
		 */
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getLabelPositions().add(labelPos1);
		testObject2.getLabelPositions().add(labelPos1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal labelPos into list #1
		testObject1.getLabelPositions().add(labelPos2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		labelPos2.setId(labelPos1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getLabelPositions().add(labelPos3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getLabelPositions().add(labelPos4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		labelPos4.setId(labelPos3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit posX of list entry #2
		labelPos3.setPosX(1);
		labelPos4.setPosX(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		labelPos4.setPosX(labelPos3.getPosX());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit posY of list entry #2
		labelPos3.setPosY(1);
		labelPos4.setPosY(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		labelPos4.setPosY(labelPos3.getPosX());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same lists of label
	 * bendpoints
	 */

	@Test
	public void bendpointTest() {

		testObject1.setId(testObject2.getId());

		Bendpoint bendpoint1 = ModelFactory.eINSTANCE.createBendpoint();
		Bendpoint bendpoint2 = ModelFactory.eINSTANCE.createBendpoint();
		Bendpoint bendpoint3 = ModelFactory.eINSTANCE.createBendpoint();
		Bendpoint bendpoint4 = ModelFactory.eINSTANCE.createBendpoint();

		/*
		 * add the same bendpoint into both lists, which is not possible.
		 */
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getBendpoints().add(bendpoint1);
		testObject2.getBendpoints().add(bendpoint1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal bendpoint into list #1
		testObject1.getBendpoints().add(bendpoint2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		bendpoint2.setId(bendpoint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getBendpoints().add(bendpoint3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getBendpoints().add(bendpoint4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make list entry #2 equal
		bendpoint4.setId(bendpoint3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit posX of list entry #2
		bendpoint3.setPosX(1);
		bendpoint4.setPosX(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		bendpoint4.setPosX(bendpoint3.getPosX());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit posY of list entry #2
		bendpoint3.setPosY(1);
		bendpoint4.setPosY(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		bendpoint4.setPosY(bendpoint3.getPosX());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	@Test
	public void constraintTest() {

		testObject1.setId(testObject2.getId());

		RolePlayerConstraint rolePlayerConstraint1 = ModelFactory.eINSTANCE
				.createRolePlayerConstraint();
		RolePlayerConstraint rolePlayerConstraint2 = ModelFactory.eINSTANCE
				.createRolePlayerConstraint();

		// set same and different rolePlayerConstraint
		testObject1.setRoleConstraint(rolePlayerConstraint1);
		testObject2.setRoleConstraint(rolePlayerConstraint1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setRoleConstraint(rolePlayerConstraint2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rolePlayerConstraint2.setId(rolePlayerConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set cardMax
		rolePlayerConstraint1.setCardMax("1");
		rolePlayerConstraint2.setCardMax("2");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rolePlayerConstraint1.setCardMax("2");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set cardMin
		rolePlayerConstraint1.setCardMin("0");
		rolePlayerConstraint2.setCardMin("1");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rolePlayerConstraint1.setCardMin("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set comment
		rolePlayerConstraint1.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rolePlayerConstraint2.setComment("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rolePlayerConstraint2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set description
		rolePlayerConstraint1.setDescription("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rolePlayerConstraint2.setDescription("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		rolePlayerConstraint2.setDescription("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set player
		playerTest(rolePlayerConstraint1, rolePlayerConstraint2);

		// set role
		RoleConstraint roleConstraint1 = ModelFactory.eINSTANCE
				.createRoleConstraint();
		RoleConstraint roleConstraint2 = ModelFactory.eINSTANCE
				.createRoleConstraint();

		// set different role constraints
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		rolePlayerConstraint1.setRole(roleConstraint1);
		rolePlayerConstraint2.setRole(roleConstraint2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both role constraints the same
		roleConstraint2.setId(roleConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMax
		roleConstraint1.setCardMax("1");
		roleConstraint2.setCardMax("2");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		roleConstraint2.setCardMax("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set comment
		roleConstraint1.setComment("TMCL");
		roleConstraint2.setComment("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		roleConstraint2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set topic type
		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();

		// set different topic types
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		roleConstraint1.setType(topicType1);
		roleConstraint2.setType(topicType2);
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

	}

	private void roleTest(RolePlayerConstraint rolePlayerConstraint1,
			RolePlayerConstraint rolePlayerConstraint2) {
		RoleConstraint roleConstraint1 = ModelFactory.eINSTANCE
				.createRoleConstraint();
		RoleConstraint roleConstraint2 = ModelFactory.eINSTANCE
				.createRoleConstraint();

		// set different role constraints
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		rolePlayerConstraint1.setRole(roleConstraint1);
		rolePlayerConstraint2.setRole(roleConstraint2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make both role constraints the same
		roleConstraint2.setId(roleConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit cardMax
		roleConstraint1.setCardMax("1");
		roleConstraint2.setCardMax("2");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		roleConstraint2.setCardMax("1");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set comment
		roleConstraint1.setComment("TMCL");
		roleConstraint2.setComment("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		roleConstraint2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set topic type
		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();

		// set different topic types
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		roleConstraint1.setType(topicType2);
		roleConstraint2.setType(topicType2);
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
	}

	private void playerTest(RolePlayerConstraint rolePlayerConstraint1,
			RolePlayerConstraint rolePlayerConstraint2) {

		TopicType topicType1 = ModelFactory.eINSTANCE.createTopicType();
		TopicType topicType2 = ModelFactory.eINSTANCE.createTopicType();

		// set different topic types
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		rolePlayerConstraint1.setPlayer(topicType1);
		rolePlayerConstraint2.setPlayer(topicType2);
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
	}

} // EdgeTest
