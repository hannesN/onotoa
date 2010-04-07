/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.AssociationNodeComparator;

import junit.framework.Assert;

public class AssociationNodeTest extends NodeTest {

	private AssociationNode testObject1;
	private AssociationNode testObject2;
	private AssociationNodeComparator comp;

	@Before
	public void prepare() {

		if (testObject1 == null)
			testObject1 = ModelFactory.eINSTANCE.createAssociationNode();
		if (testObject2 == null)
			testObject2 = ModelFactory.eINSTANCE.createAssociationNode();

		comp = new AssociationNodeComparator();
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
	 * Test compares two objects with different and same
	 * AssociationTypeConstraints
	 */

	@Test
	public void associationTypeConstraintTest() {

		testObject1.setId(testObject2.getId());

		AssociationTypeConstraint aTypeConstraint1 = ModelFactory.eINSTANCE
				.createAssociationTypeConstraint();
		AssociationTypeConstraint aTypeConstraint2 = ModelFactory.eINSTANCE
				.createAssociationTypeConstraint();

		// set different AssociationTypeConstraints
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		testObject1.setAssociationConstraint(aTypeConstraint1);
		testObject2.setAssociationConstraint(aTypeConstraint2);
		Assert.assertFalse(comp.equals(testObject1, testObject2));

		// make AssociationTypeConstraints the same
		aTypeConstraint2.setId(aTypeConstraint1.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// edit some values of the AssociationTypeConstraint
		aTypeConstraint1.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		aTypeConstraint2.setComment("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		aTypeConstraint2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		aTypeConstraint1.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		aTypeConstraint2.setSee_also("TMQL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		aTypeConstraint2.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

} // AssociationNodeTest
