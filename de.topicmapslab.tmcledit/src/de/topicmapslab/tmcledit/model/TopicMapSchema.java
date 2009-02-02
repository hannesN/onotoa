/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Topic Map Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getTopicTypes <em>Topic Types</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints <em>Association Type Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getMappings <em>Mappings</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getIncludes <em>Includes</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveTopicTypeConstraint <em>Active Topic Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveScopeTypeConstraint <em>Active Scope Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveRoleTypeConstraint <em>Active Role Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveNameTypeConstraint <em>Active Name Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveAssociationTypeConstraint <em>Active Association Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveOccurenceTypeConstraint <em>Active Occurence Type Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema()
 * @model
 * @generated
 */
public interface TopicMapSchema extends EObject {
	/**
	 * Returns the value of the '<em><b>Topic Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.TopicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topic Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic Types</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_TopicTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<TopicType> getTopicTypes();

	/**
	 * Returns the value of the '<em><b>Association Type Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_AssociationTypeConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<AssociationTypeConstraint> getAssociationTypeConstraints();

	/**
	 * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.MappingElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mappings</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_Mappings()
	 * @model containment="true"
	 * @generated
	 */
	EList<MappingElement> getMappings();

	/**
	 * Returns the value of the '<em><b>Includes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Includes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Includes</em>' attribute list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_Includes()
	 * @model
	 * @generated
	 */
	EList<String> getIncludes();

	/**
	 * Returns the value of the '<em><b>Active Topic Type Constraint</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Topic Type Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Topic Type Constraint</em>' attribute.
	 * @see #setActiveTopicTypeConstraint(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_ActiveTopicTypeConstraint()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isActiveTopicTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveTopicTypeConstraint <em>Active Topic Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Topic Type Constraint</em>' attribute.
	 * @see #isActiveTopicTypeConstraint()
	 * @generated
	 */
	void setActiveTopicTypeConstraint(boolean value);

	/**
	 * Returns the value of the '<em><b>Active Scope Type Constraint</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Scope Type Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Scope Type Constraint</em>' attribute.
	 * @see #setActiveScopeTypeConstraint(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_ActiveScopeTypeConstraint()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isActiveScopeTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveScopeTypeConstraint <em>Active Scope Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Scope Type Constraint</em>' attribute.
	 * @see #isActiveScopeTypeConstraint()
	 * @generated
	 */
	void setActiveScopeTypeConstraint(boolean value);

	/**
	 * Returns the value of the '<em><b>Active Role Type Constraint</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Role Type Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Role Type Constraint</em>' attribute.
	 * @see #setActiveRoleTypeConstraint(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_ActiveRoleTypeConstraint()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isActiveRoleTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveRoleTypeConstraint <em>Active Role Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Role Type Constraint</em>' attribute.
	 * @see #isActiveRoleTypeConstraint()
	 * @generated
	 */
	void setActiveRoleTypeConstraint(boolean value);

	/**
	 * Returns the value of the '<em><b>Active Name Type Constraint</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Name Type Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Name Type Constraint</em>' attribute.
	 * @see #setActiveNameTypeConstraint(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_ActiveNameTypeConstraint()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isActiveNameTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveNameTypeConstraint <em>Active Name Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Name Type Constraint</em>' attribute.
	 * @see #isActiveNameTypeConstraint()
	 * @generated
	 */
	void setActiveNameTypeConstraint(boolean value);

	/**
	 * Returns the value of the '<em><b>Active Association Type Constraint</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Association Type Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Association Type Constraint</em>' attribute.
	 * @see #setActiveAssociationTypeConstraint(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_ActiveAssociationTypeConstraint()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isActiveAssociationTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveAssociationTypeConstraint <em>Active Association Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Association Type Constraint</em>' attribute.
	 * @see #isActiveAssociationTypeConstraint()
	 * @generated
	 */
	void setActiveAssociationTypeConstraint(boolean value);

	/**
	 * Returns the value of the '<em><b>Active Occurence Type Constraint</b></em>' attribute.
	 * The default value is <code>"true"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Active Occurence Type Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Active Occurence Type Constraint</em>' attribute.
	 * @see #setActiveOccurenceTypeConstraint(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_ActiveOccurenceTypeConstraint()
	 * @model default="true" required="true"
	 * @generated
	 */
	boolean isActiveOccurenceTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#isActiveOccurenceTypeConstraint <em>Active Occurence Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Active Occurence Type Constraint</em>' attribute.
	 * @see #isActiveOccurenceTypeConstraint()
	 * @generated
	 */
	void setActiveOccurenceTypeConstraint(boolean value);

} // TopicMapSchema
