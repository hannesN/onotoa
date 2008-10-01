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
 * A representation of the model object '<em><b>Association Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getScope <em>Scope</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getAssociationType <em>Association Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getRoleTypeConstraints <em>Role Type Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationTypeConstraint()
 * @model
 * @generated
 */
public interface AssociationTypeConstraint extends EObject {
	/**
	 * Returns the value of the '<em><b>Scope</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope</em>' reference.
	 * @see #setScope(ScopeType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationTypeConstraint_Scope()
	 * @model
	 * @generated
	 */
	ScopeType getScope();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getScope <em>Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Scope</em>' reference.
	 * @see #getScope()
	 * @generated
	 */
	void setScope(ScopeType value);

	/**
	 * Returns the value of the '<em><b>Association Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type</em>' reference.
	 * @see #setAssociationType(AssociationsType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationTypeConstraint_AssociationType()
	 * @model
	 * @generated
	 */
	AssociationsType getAssociationType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getAssociationType <em>Association Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Type</em>' reference.
	 * @see #getAssociationType()
	 * @generated
	 */
	void setAssociationType(AssociationsType value);

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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationTypeConstraint_RoleTypeConstraints()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<RoleTypeConstraints> getRoleTypeConstraints();

} // AssociationTypeConstraint
