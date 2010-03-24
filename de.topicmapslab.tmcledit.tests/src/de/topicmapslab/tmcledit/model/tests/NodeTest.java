/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.compare.NodeComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc --> A test case for the model object '
 * <em><b>Node</b></em>'. <!-- end-user-doc -->
 * 
 * @generated
 */
public class NodeTest extends OnoObjectTest {

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(NodeTest.class);
	}

	/**
	 * Constructs a new Node test case with the given name. <!-- begin-user-doc
	 * --> <!-- end-user-doc -->
	 * 
	 * @generated
	 */
	public NodeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Node test case. <!-- begin-user-doc --> <!--
	 * end-user-doc -->
	 * 
	 * @generated
	 */
	@Override
	protected Node getFixture() {
		return (Node) fixture;
	}

	/**
	 * <!-- begin-user-doc --> <!-- end-user-doc -->
	 * 
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createNode());
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

	public void testNode() {

		Node testObject1 = ModelFactory.eINSTANCE.createNode();
		Node testObject2 = ModelFactory.eINSTANCE.createNode();

		NodeComparator comp = new NodeComparator();

		this.allTests(testObject1, testObject2, comp);

	}

	/**
	 * Includes super class tests and posX and posY test.
	 * 
	 * @param testObject1
	 * @param testObject2
	 * @param comp
	 */

	protected void allTests(Node testObject1, Node testObject2,
			NodeComparator comp) {

		super.allTests(testObject1, testObject2, comp);

		// position test for X and Y value
		positionTest(testObject1, testObject2, comp);
	}

	/**
	 * Method inserts values for the X and Y position which are different from
	 * the default ones for assertion tests.
	 * 
	 * @param testObject1
	 *            Node #1
	 * @param testObject2
	 *            Node #2
	 * @param comp
	 *            specific comparator
	 */

	protected void positionTest(Node testObject1, Node testObject2,
			NodeComparator comp) {

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

} // NodeTest
