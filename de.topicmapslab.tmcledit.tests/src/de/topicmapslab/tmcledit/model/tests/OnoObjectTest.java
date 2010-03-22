/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OnoObject;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.compare.OnoObjectComparator;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Ono Object</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public abstract class OnoObjectTest extends TestCase {

	/**
	 * The fixture for this Ono Object test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected OnoObject fixture = null;

	/**
	 * Constructs a new Ono Object test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OnoObjectTest(String name) {
		super(name);
	}

	/**
	 * Sets the fixture for this Ono Object test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected void setFixture(OnoObject fixture) {
		this.fixture = fixture;
	}

	/**
	 * Returns the fixture for this Ono Object test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	protected OnoObject getFixture() {
		return fixture;
	}

	public void testOnoObject() {

		OnoObject testObject1 = ModelFactory.eINSTANCE.createTMCLConstruct();
		OnoObject testObject2 = ModelFactory.eINSTANCE.createTMCLConstruct();

		OnoObjectComparator comp = new OnoObjectComparator();

		this.allTests(testObject1, testObject2, comp);

	}

	/**
	 * Includes id and null object test.
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */

	protected void allTests(OnoObject testObject1, OnoObject testObject2,
			OnoObjectComparator comp) {

		// NULL test
		nullTest(testObject1, comp);

		// set id
		idTest(testObject1, testObject2, comp);
	}

	/**
	 * Method compares Object with NULL and proves the equality of two NULL
	 * Objects
	 * 
	 * @param testObject1
	 *            Object #1
	 * @param comp
	 *            specific comparator
	 */

	protected void nullTest(OnoObject testObject1, OnoObjectComparator comp) {

		Assert.assertFalse(comp.equals(testObject1, null));
		Assert.assertFalse(comp.equals(null, testObject1));
		Assert.assertTrue(comp.equals(null, null));
	}

	/**
	 * Method compares two objects with different IDs in the first run and set
	 * equal IDs in the second.
	 * 
	 * @param testObject1
	 *            OnoObject #1
	 * @param testObject2
	 *            OnoObject #2
	 * @param comp
	 *            specific comparator
	 */

	protected void idTest(OnoObject testObject1, OnoObject testObject2,
			OnoObjectComparator comp) {

		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject1.setId(testObject2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

} // OnoObjectTest
