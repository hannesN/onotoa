/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Occurrence Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class OccurrenceTypeConstraintTest extends AbstractTypedCardinalityConstraintTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(OccurrenceTypeConstraintTest.class);
	}

	/**
	 * Constructs a new Occurrence Type Constraint test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccurrenceTypeConstraintTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Occurrence Type Constraint test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected OccurrenceTypeConstraint getFixture() {
		return (OccurrenceTypeConstraint)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createOccurrenceTypeConstraint());
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

} //OccurrenceTypeConstraintTest
