/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.Bendpoint;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.BendpointComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Bendpoint</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class BendpointTest extends OnoObjectTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(BendpointTest.class);
	}

	/**
	 * Constructs a new Bendpoint test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BendpointTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Bendpoint test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected Bendpoint getFixture() {
		return (Bendpoint)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createBendpoint());
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

public void testNode(){
		
		Bendpoint testObject1 = ModelFactory.eINSTANCE.createBendpoint();
		Bendpoint testObject2 = ModelFactory.eINSTANCE.createBendpoint();
		
		BendpointComparator comp = new BendpointComparator();
		
		// NULL test
		super.nullTest(testObject1, comp);

		// set id
		super.idTest(testObject1, testObject2, comp);
		
		// set posX DEFAULT = 0
		testObject1.setPosX(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setPosX(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
		// set posY DEFAULT = 0
		testObject1.setPosY(1);
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setPosY(1);
		Assert.assertTrue(comp.equals(testObject1, testObject2));
		
	}

	
} //BendpointTest
