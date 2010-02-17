/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
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
	 * Returns a new object of class '<em>Occurrence Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Occurrence Type Constraint</em>'.
	 * @generated
	 */
	OccurrenceTypeConstraint createOccurrenceTypeConstraint();

	/**
	 * Returns a new object of class '<em>Name Type Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name Type Constraint</em>'.
	 * @generated
	 */
	NameTypeConstraint createNameTypeConstraint();

	/**
	 * Returns a new object of class '<em>Role Player Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Player Constraint</em>'.
	 * @generated
	 */
	RolePlayerConstraint createRolePlayerConstraint();

	/**
	 * Returns a new object of class '<em>Topic Map Schema</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Topic Map Schema</em>'.
	 * @generated
	 */
	TopicMapSchema createTopicMapSchema();

	/**
	 * Returns a new object of class '<em>Subject Locator Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subject Locator Constraint</em>'.
	 * @generated
	 */
	SubjectLocatorConstraint createSubjectLocatorConstraint();

	/**
	 * Returns a new object of class '<em>Subject Identifier Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Subject Identifier Constraint</em>'.
	 * @generated
	 */
	SubjectIdentifierConstraint createSubjectIdentifierConstraint();

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
	 * Returns a new object of class '<em>Bendpoint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Bendpoint</em>'.
	 * @generated
	 */
	Bendpoint createBendpoint();

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
	 * Returns a new object of class '<em>Scope Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scope Constraint</em>'.
	 * @generated
	 */
	ScopeConstraint createScopeConstraint();

	/**
	 * Returns a new object of class '<em>Label Pos</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Label Pos</em>'.
	 * @generated
	 */
	LabelPos createLabelPos();

	/**
	 * Returns a new object of class '<em>Association Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Association Type</em>'.
	 * @generated
	 */
	AssociationType createAssociationType();

	/**
	 * Returns a new object of class '<em>Occurrence Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Occurrence Type</em>'.
	 * @generated
	 */
	OccurrenceType createOccurrenceType();

	/**
	 * Returns a new object of class '<em>Role Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Constraint</em>'.
	 * @generated
	 */
	RoleConstraint createRoleConstraint();

	/**
	 * Returns a new object of class '<em>Role Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Type</em>'.
	 * @generated
	 */
	RoleType createRoleType();

	/**
	 * Returns a new object of class '<em>Role Combination Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Role Combination Constraint</em>'.
	 * @generated
	 */
	RoleCombinationConstraint createRoleCombinationConstraint();

	/**
	 * Returns a new object of class '<em>Name Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Name Type</em>'.
	 * @generated
	 */
	NameType createNameType();

	/**
	 * Returns a new object of class '<em>Abstract Typed Cardinality Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Typed Cardinality Constraint</em>'.
	 * @generated
	 */
	AbstractTypedCardinalityConstraint createAbstractTypedCardinalityConstraint();

	/**
	 * Returns a new object of class '<em>Comment</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Comment</em>'.
	 * @generated
	 */
	Comment createComment();

	/**
	 * Returns a new object of class '<em>TMCL Construct</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>TMCL Construct</em>'.
	 * @generated
	 */
	TMCLConstruct createTMCLConstruct();

	/**
	 * Returns a new object of class '<em>Reifier Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reifier Constraint</em>'.
	 * @generated
	 */
	ReifierConstraint createReifierConstraint();

	/**
	 * Returns a new object of class '<em>Reifiable Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Reifiable Topic Type</em>'.
	 * @generated
	 */
	ReifiableTopicType createReifiableTopicType();

	/**
	 * Returns a new object of class '<em>Scoped Reifiable Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Scoped Reifiable Topic Type</em>'.
	 * @generated
	 */
	ScopedReifiableTopicType createScopedReifiableTopicType();

	/**
	 * Returns a new object of class '<em>Annotation</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Annotation</em>'.
	 * @generated
	 */
	Annotation createAnnotation();

	/**
	 * Returns a new object of class '<em>Topic Reifies Constraint</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Topic Reifies Constraint</em>'.
	 * @generated
	 */
	TopicReifiesConstraint createTopicReifiesConstraint();

	/**
	 * Returns a new object of class '<em>Domain Diagram</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Domain Diagram</em>'.
	 * @generated
	 */
	DomainDiagram createDomainDiagram();

	/**
	 * Returns a new object of class '<em>Abstract Unique Value Topic Type</em>'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return a new object of class '<em>Abstract Unique Value Topic Type</em>'.
	 * @generated
	 */
	AbstractUniqueValueTopicType createAbstractUniqueValueTopicType();

	/**
	 * Returns the package supported by this factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the package supported by this factory.
	 * @generated
	 */
	ModelPackage getModelPackage();

} //ModelFactory
