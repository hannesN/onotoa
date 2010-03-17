/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.Annotation;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.AnnotationComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Annotation</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class AnnotationTest extends OnoObjectTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(AnnotationTest.class);
	}

	/**
	 * Constructs a new Annotation test case with the given name. <!--
	 * begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public AnnotationTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Annotation test case. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Annotation getFixture() {
		return (Annotation) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createAnnotation());
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

	public void testAnnotation() {

		Annotation testObject1 = ModelFactory.eINSTANCE.createAnnotation();
		Annotation testObject2 = ModelFactory.eINSTANCE.createAnnotation();

		AnnotationComparator comp = new AnnotationComparator();

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

} // AnnotationTest
