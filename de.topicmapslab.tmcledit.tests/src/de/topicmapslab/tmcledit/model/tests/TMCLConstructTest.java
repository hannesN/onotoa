/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TMCLConstruct;
import de.topicmapslab.tmcledit.model.compare.TMCLConstructComperator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>TMCL Construct</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class TMCLConstructTest extends OnoObjectTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TMCLConstructTest.class);
	}

	/**
	 * Constructs a new TMCL Construct test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public TMCLConstructTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this TMCL Construct test case. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected TMCLConstruct getFixture() {
		return (TMCLConstruct) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createTMCLConstruct());
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

	public void testTMCLConstruct() {

		TMCLConstruct testObject1 = ModelFactory.eINSTANCE
				.createTMCLConstruct();
		TMCLConstruct testObject2 = ModelFactory.eINSTANCE
				.createTMCLConstruct();

		TMCLConstructComperator comp = new TMCLConstructComperator();

		// NULL test
		super.nullTest(testObject1, comp);

		// set id
		super.idTest(testObject1, testObject2, comp);

		// set comments
		testObject1.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set descriptions
		testObject1.setDescription("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setDescription("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// set see_also
		testObject1.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// TODO set annotations
	}

} // TMCLConstructTest
