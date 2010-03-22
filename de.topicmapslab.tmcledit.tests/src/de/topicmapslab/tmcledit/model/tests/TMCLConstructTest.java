/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.Annotation;
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

		this.allTests(testObject1, testObject2, comp);

	}

	/**
	 * Includes super class tests and comment, description, see_also and
	 * annotation test.
	 * 
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */

	protected void allTests(TMCLConstruct testObject1,
			TMCLConstruct testObject2, TMCLConstructComperator comp) {

		super.allTests(testObject1, testObject2, comp);

		// set comments
		commentTest(testObject1, testObject2, comp);

		// set descriptions
		descriptionTest(testObject1, testObject2, comp);

		// set see_also
		seeAlsoTest(testObject1, testObject2, comp);

		// set annotations
		annotationTest(testObject1, testObject2, comp);
	}

	protected void seeAlsoTest(TMCLConstruct testObject1,
			TMCLConstruct testObject2, TMCLConstructComperator comp) {
		testObject1.setSee_also("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setSee_also("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	protected void descriptionTest(TMCLConstruct testObject1,
			TMCLConstruct testObject2, TMCLConstructComperator comp) {
		testObject1.setDescription("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setDescription("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	protected void commentTest(TMCLConstruct testObject1,
			TMCLConstruct testObject2, TMCLConstructComperator comp) {
		testObject1.setComment("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setComment("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));
	}

	protected void annotationTest(TMCLConstruct testObject1,
			TMCLConstruct testObject2, TMCLConstructComperator comp) {

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
} // TMCLConstructTest
