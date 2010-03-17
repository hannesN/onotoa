/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.tests;

import de.topicmapslab.tmcledit.model.DomainDiagram;
import de.topicmapslab.tmcledit.model.ModelFactory;

import junit.textui.TestRunner;

/**
 * <!-- begin-user-doc -->
 * A test case for the model object '<em><b>Domain Diagram</b></em>'.
 * <!-- end-user-doc -->
 * @generated
 */
public class DomainDiagramTest extends DiagramTest {

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static void main(String[] args) {
		TestRunner.run(DomainDiagramTest.class);
	}

	/**
	 * Constructs a new Domain Diagram test case with the given name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public DomainDiagramTest(String name) {
		super(name);
	}

	/**
	 * Returns the fixture for this Domain Diagram test case.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected DomainDiagram getFixture() {
		return (DomainDiagram)fixture;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see junit.framework.TestCase#setUp()
	 * @generated
	 */
	@Override
	protected void setUp() throws Exception {
		setFixture(ModelFactory.eINSTANCE.createDomainDiagram());
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

} //DomainDiagramTest
