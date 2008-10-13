/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.model.ModelPackage
 * @generated
 */
public interface ModelFactory extends EFactory {
	/**
	 * The singleton instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelFactory eINSTANCE = de.topicmapslab.tmcledit.model.impl.ModelFactoryImpl.init();

	/**
	 * Returns a new object of class '<em>Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Topic Type</em>'.
	 * @generated
	 */
	TopicType createTopicType();

	/**
	 * Returns a new object of class '<em>Occurence Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Occurence Type Constraint</em>'.
	 * @generated
	 */
	OccurenceTypeConstraint createOccurenceTypeConstraint();

	/**
	 * Returns a new object of class '<em>Name Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name Type Constraint</em>'.
	 * @generated
	 */
	NameTypeConstraint createNameTypeConstraint();

	/**
	 * Returns a new object of class '<em>Role Type Constraints</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Type Constraints</em>'.
	 * @generated
	 */
	RoleTypeConstraints createRoleTypeConstraints();

	/**
	 * Returns a new object of class '<em>Topic Map Schema</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Topic Map Schema</em>'.
	 * @generated
	 */
	TopicMapSchema createTopicMapSchema();

	/**
	 * Returns a new object of class '<em>subject Locator Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>subject Locator Constraint</em>'.
	 * @generated
	 */
	subjectLocatorConstraint createsubjectLocatorConstraint();

	/**
	 * Returns a new object of class '<em>subject Identifier Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>subject Identifier Constraint</em>'.
	 * @generated
	 */
	subjectIdentifierConstraint createsubjectIdentifierConstraint();

	/**
	 * Returns a new object of class '<em>Association Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Association Type Constraint</em>'.
	 * @generated
	 */
	AssociationTypeConstraint createAssociationTypeConstraint();

	/**
	 * Returns a new object of class '<em>Mapping Element</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Mapping Element</em>'.
	 * @generated
	 */
	MappingElement createMappingElement();

	/**
	 * Returns a new object of class '<em>Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Node</em>'.
	 * @generated
	 */
	Node createNode();

	/**
	 * Returns a new object of class '<em>Type Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Type Node</em>'.
	 * @generated
	 */
	TypeNode createTypeNode();

	/**
	 * Returns a new object of class '<em>Bendpoints</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bendpoints</em>'.
	 * @generated
	 */
	Bendpoints createBendpoints();

	/**
	 * Returns a new object of class '<em>Edge</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Edge</em>'.
	 * @generated
	 */
	Edge createEdge();

	/**
	 * Returns a new object of class '<em>Association Node</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Association Node</em>'.
	 * @generated
	 */
	AssociationNode createAssociationNode();

	/**
	 * Returns a new object of class '<em>Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Diagram</em>'.
	 * @generated
	 */
	Diagram createDiagram();

	/**
	 * Returns a new object of class '<em>File</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>File</em>'.
	 * @generated
	 */
	File createFile();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
