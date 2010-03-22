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

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OnoObject;
import de.topicmapslab.tmcledit.model.compare.OnoObjectComparator;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Ono Object</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class OnoObjectTest  {

	/**
	 * The fixture for this Ono Object test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	protected OnoObject fixture = null;
	private OnoObject testObject1;
	private OnoObject testObject2;
	private OnoObjectComparator comp;

	/**
	 * Constructs a new Ono Object test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public OnoObjectTest() {
		super();
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

	@Before
	public void prepare() {

		if (testObject1==null)
			testObject1 = ModelFactory.eINSTANCE.createTMCLConstruct();
		if (testObject2==null)
			testObject2 = ModelFactory.eINSTANCE.createTMCLConstruct();

		comp = new OnoObjectComparator();
	}

	@After
	public void shutdown() {
		testObject1=null;
		testObject2=null;
	}

	protected void setTestObject1(OnoObject testObject1) {
		this.testObject1 = testObject1;
	}
	
	protected void setTestObject2(OnoObject testObject2) {
		this.testObject2 = testObject2;
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
	@Test
	public void nullTest() {

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
	@Test
	public void idTest() {

		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject1.setId(testObject2.getId());
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

} // OnoObjectTest
