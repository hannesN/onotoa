/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationType#getRoles <em>Roles</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationType#getRoleCombinations <em>Role Combinations</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationType()
 * @model
 * @generated
 */
public interface AssociationType extends ScopedTopicType, ScopedReifiableTopicType {
	/**
	 * Returns the value of the '<em><b>Roles</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.RoleConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationType_Roles()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<RoleConstraint> getRoles();

	/**
	 * Returns the value of the '<em><b>Role Combinations</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Combinations</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Combinations</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationType_RoleCombinations()
	 * @model containment="true"
	 * @generated
	 */
	EList<RoleCombinationConstraint> getRoleCombinations();

} // AssociationType
