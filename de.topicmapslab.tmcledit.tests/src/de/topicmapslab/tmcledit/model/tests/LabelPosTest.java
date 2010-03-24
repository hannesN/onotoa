/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.LabelPosComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Label Pos</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class LabelPosTest extends OnoObjectTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(LabelPosTest.class);
	}

	/**
	 * Constructs a new Label Pos test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public LabelPosTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Label Pos test case. <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected LabelPos getFixture() {
		return (LabelPos) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createLabelPos());
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#tearDown()
	 * @generated
	 */
	@Override
	protected void tearDown() throws Exception {
		setFixture(null);
	}

	public void testLabelPos() {

		LabelPos testObject1 = ModelFactory.eINSTANCE.createLabelPos();
		LabelPos testObject2 = ModelFactory.eINSTANCE.createLabelPos();

		LabelPosComparator comp = new LabelPosComparator();

		this.allTests(testObject1, testObject2, comp);

	}

	/**
	 * Includes super class tests and posX and posY test.
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */

	protected void allTests(LabelPos testObject1, LabelPos testObject2,
			LabelPosComparator comp) {

		super.allTests(testObject1, testObject2, comp);

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

} // LabelPosTest
