/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.compare.EdgeComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Edge</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class EdgeTest extends OnoObjectTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(EdgeTest.class);
	}

	/**
	 * Constructs a new Edge test case with the given name. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public EdgeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Edge test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Edge getFixture() {
		return (Edge) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createEdge());
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

	public void testEdge() {

		Edge testObject1 = ModelFactory.eINSTANCE.createEdge();
		Edge testObject2 = ModelFactory.eINSTANCE.createEdge();

		EdgeComparator comp = new EdgeComparator();

		// NULL test
		nullTest(testObject1, comp);

		// set id
		idTest(testObject1, testObject2, comp);
		

	}

} // EdgeTest
