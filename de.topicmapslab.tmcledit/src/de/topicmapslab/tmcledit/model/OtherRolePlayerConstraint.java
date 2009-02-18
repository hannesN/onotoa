/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Other Role Player Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getPlayer <em>Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getOtherPlayer <em>Other Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getOtherRole <em>Other Role</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getAssociationType <em>Association Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOtherRolePlayerConstraint()
 * @model
 * @generated
 */
public interface OtherRolePlayerConstraint extends AbstractCardinalityContraint {
	/**
	 * Returns the value of the '<em><b>Player</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Player</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Player</em>' reference.
	 * @see #setPlayer(TopicType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOtherRolePlayerConstraint_Player()
	 * @model required="true"
	 * @generated
	 */
	TopicType getPlayer();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getPlayer <em>Player</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Player</em>' reference.
	 * @see #getPlayer()
	 * @generated
	 */
	void setPlayer(TopicType value);

	/**
	 * Returns the value of the '<em><b>Other Player</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Other Player</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Other Player</em>' reference.
	 * @see #setOtherPlayer(TopicType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOtherRolePlayerConstraint_OtherPlayer()
	 * @model required="true"
	 * @generated
	 */
	TopicType getOtherPlayer();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getOtherPlayer <em>Other Player</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Other Player</em>' reference.
	 * @see #getOtherPlayer()
	 * @generated
	 */
	void setOtherPlayer(TopicType value);

	/**
	 * Returns the value of the '<em><b>Other Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Other Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Other Role</em>' reference.
	 * @see #setOtherRole(RoleType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOtherRolePlayerConstraint_OtherRole()
	 * @model required="true"
	 * @generated
	 */
	RoleType getOtherRole();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getOtherRole <em>Other Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Other Role</em>' reference.
	 * @see #getOtherRole()
	 * @generated
	 */
	void setOtherRole(RoleType value);

	/**
	 * Returns the value of the '<em><b>Association Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type</em>' reference.
	 * @see #setAssociationType(AssociationType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOtherRolePlayerConstraint_AssociationType()
	 * @model required="true"
	 * @generated
	 */
	AssociationType getAssociationType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint#getAssociationType <em>Association Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Type</em>' reference.
	 * @see #getAssociationType()
	 * @generated
	 */
	void setAssociationType(AssociationType value);

} // OtherRolePlayerConstraint
