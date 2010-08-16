/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.Comment;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.compare.DiagramComparator;

import junit.framework.Assert;

public class DiagramTest extends OnoObjectTest {

	private Diagram testObject1;
	private Diagram testObject2;
	private DiagramComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createDiagram();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createDiagram();

		comp = new DiagramComparator();
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
	 * Test compares two objects with different and same names.
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
	 * Test compares two objects with different and same comments.
	 */

	@Test
	public void commentTest() {

		testObject1.setId(testObject2.getId());

		Comment comment1 = ModelFactory.eINSTANCE.createComment();
		Comment comment2 = ModelFactory.eINSTANCE.createComment();
		Comment comment3 = ModelFactory.eINSTANCE.createComment();
		Comment comment4 = ModelFactory.eINSTANCE.createComment();

		// add the same comment which is not possible
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getComments().add(comment1);
		testObject2.getComments().add(comment1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal comment into list #1
		testObject1.getComments().add(comment2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		comment2.setId(comment1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getComments().add(comment3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getComments().add(comment4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		comment4.setId(comment3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit some values from entry #2
		comment3.setContent("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		comment4.setContent("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		comment4.setContent("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		comment3.setHeight(1);
		comment4.setHeight(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		comment4.setHeight(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		comment3.setPosX(1);
		comment4.setPosX(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		comment4.setPosX(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	/**
	 * Test compares two objects with different and same edges.
	 */

	@Test
	public void edgeTest() {

		testObject1.setId(testObject2.getId());

		Edge edge1 = ModelFactory.eINSTANCE.createEdge();
		Edge edge2 = ModelFactory.eINSTANCE.createEdge();
		Edge edge3 = ModelFactory.eINSTANCE.createEdge();
		Edge edge4 = ModelFactory.eINSTANCE.createEdge();

		// add the same edge which is not possible
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getEdges().add(edge1);
		testObject2.getEdges().add(edge1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal edge into list #1
		testObject1.getEdges().add(edge2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		edge2.setId(edge1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getEdges().add(edge3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getEdges().add(edge4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		edge4.setId(edge3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit some values from entry #2
		Node node1 = ModelFactory.eINSTANCE.createNode();
		Node node2 = ModelFactory.eINSTANCE.createNode();

		edge3.setSource(node1);
		edge4.setSource(node2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node2.setId(node1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		node1.setPosX(1);
		node2.setPosX(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node2.setPosX(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		EdgeType edType1 = EdgeType.ROLE_CONSTRAINT_TYPE;
		EdgeType edType2 = EdgeType.AKO_TYPE;

		edge3.setType(edType1);
		edge4.setType(edType2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		edge4.setType(edType1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

	/**
	 * Test compares two objects with different and same nodes.
	 */

	@Test
	public void nodeTest() {

		testObject1.setId(testObject2.getId());

		Node node1 = ModelFactory.eINSTANCE.createNode();
		Node node2 = ModelFactory.eINSTANCE.createNode();
		Node node3 = ModelFactory.eINSTANCE.createNode();
		Node node4 = ModelFactory.eINSTANCE.createNode();

		// add the same nodes which is not possible
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.getNodes().add(node1);
		testObject2.getNodes().add(node1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// add an equal node into list #1
		testObject1.getNodes().add(node2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node2.setId(node1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// test different list lengths and different entries
		testObject1.getNodes().add(node3);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.getNodes().add(node4);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node4.setId(node3.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit some values from entry #2
		node3.setPosX(1);
		node4.setPosX(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node4.setPosX(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		node3.setPosY(1);
		node4.setPosY(2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		node4.setPosY(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

} // DiagramTest
