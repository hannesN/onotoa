/**
 * <copyright>
 * </copyright>
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
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getRoleTypeConstraints <em>Role Type Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints <em>Association Type Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getMappings <em>Mappings</em>}</li>
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
	 * Returns the value of the '<em><b>Role Type Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.RoleTypeConstraints}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Type Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Type Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_RoleTypeConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<RoleTypeConstraints> getRoleTypeConstraints();

	/**
	 * Returns the value of the '<em><b>Association Type Constraints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type Constraints</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type Constraints</em>' containment reference.
	 * @see #setAssociationTypeConstraints(AssociationTypeConstraint)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_AssociationTypeConstraints()
	 * @model containment="true"
	 * @generated
	 */
	AssociationTypeConstraint getAssociationTypeConstraints();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints <em>Association Type Constraints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Type Constraints</em>' containment reference.
	 * @see #getAssociationTypeConstraints()
	 * @generated
	 */
	void setAssociationTypeConstraints(AssociationTypeConstraint value);

	/**
	 * Returns the value of the '<em><b>Mappings</b></em>' reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.MappingElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mappings</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mappings</em>' reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_Mappings()
	 * @model
	 * @generated
	 */
	EList<MappingElement> getMappings();

} // TopicMapSchema
