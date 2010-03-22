/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicType;
import de.topicmapslab.tmcledit.model.TypeNode;
import de.topicmapslab.tmcledit.model.compare.TypeNodeComparator;

import junit.framework.Assert;
import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Type Node</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TypeNodeTest extends NodeTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TypeNodeTest.class);
	}

	/**
	 * Constructs a new Type Node test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeNodeTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Type Node test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected TypeNode getFixture() {
		return (TypeNode)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createTypeNode());
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

	public void testTypeNode(){
		
		TypeNode testObject1 = ModelFactory.eINSTANCE.createTypeNode();
		TypeNode testObject2 = ModelFactory.eINSTANCE.createTypeNode();
		
		TypeNodeComparator comp = new TypeNodeComparator();
		
		super.allTests(testObject1, testObject2, comp);
		
		// set image
		testObject1.setImage("TMCL");
		Assert.assertFalse(comp.equals(testObject1, testObject2));
		testObject2.setImage("TMCL");
		Assert.assertTrue(comp.equals(testObject1, testObject2));

		// TODO set Topic Type
		TopicType type = ModelFactory.eINSTANCE.createTopicType();
		
	}
} //TypeNodeTest
