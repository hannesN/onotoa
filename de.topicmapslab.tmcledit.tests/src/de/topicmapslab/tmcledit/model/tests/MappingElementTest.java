/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.MappingElementComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Mapping Element</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class MappingElementTest extends OnoObjectTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(MappingElementTest.class);
	}

	/**
	 * Constructs a new Mapping Element test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public MappingElementTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Mapping Element test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected MappingElement getFixture() {
		return (MappingElement) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createMappingElement());
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

	public void testMappingElement() {

		MappingElement testObject1 = ModelFactory.eINSTANCE
				.createMappingElement();
		MappingElement testObject2 = ModelFactory.eINSTANCE
				.createMappingElement();

		MappingElementComparator comp = new MappingElementComparator();

		// NULL test
		super.nullTest(testObject1, comp);

		// set id
		super.idTest(testObject1, testObject2, comp);

		// set key
		testObject1.setKey("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setKey("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set value
		testObject1.setValue("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setValue("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

	}

} // MappingElementTest
