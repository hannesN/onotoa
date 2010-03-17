/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ReifierConstraint;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Reifier Constraint</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReifierConstraintTest extends AbstractTypedCardinalityConstraintTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(ReifierConstraintTest.class);
	}

	/**
	 * Constructs a new Reifier Constraint test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReifierConstraintTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Reifier Constraint test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected ReifierConstraint getFixture() {
		return (ReifierConstraint)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createReifierConstraint());
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

} //ReifierConstraintTest
