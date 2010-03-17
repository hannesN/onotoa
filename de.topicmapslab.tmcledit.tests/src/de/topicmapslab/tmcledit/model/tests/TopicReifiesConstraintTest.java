/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.TopicReifiesConstraint;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Topic Reifies Constraint</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class TopicReifiesConstraintTest extends AbstractTypedCardinalityConstraintTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(TopicReifiesConstraintTest.class);
	}

	/**
	 * Constructs a new Topic Reifies Constraint test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicReifiesConstraintTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Topic Reifies Constraint test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected TopicReifiesConstraint getFixture() {
		return (TopicReifiesConstraint)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createTopicReifiesConstraint());
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

} //TopicReifiesConstraintTest
