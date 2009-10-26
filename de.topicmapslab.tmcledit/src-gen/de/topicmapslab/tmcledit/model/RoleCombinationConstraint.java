/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Combination Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getPlayer <em>Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherPlayer <em>Other Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherRole <em>Other Role</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getRole <em>Role</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleCombinationConstraint()
 * @model
 * @generated
 */
public interface RoleCombinationConstraint extends AbstractConstraint {
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleCombinationConstraint_Player()
	 * @model required="true"
	 * @generated
	 */
	TopicType getPlayer();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getPlayer <em>Player</em>}' reference.
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleCombinationConstraint_OtherPlayer()
	 * @model required="true"
	 * @generated
	 */
	TopicType getOtherPlayer();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherPlayer <em>Other Player</em>}' reference.
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleCombinationConstraint_OtherRole()
	 * @model required="true"
	 * @generated
	 */
	RoleType getOtherRole();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getOtherRole <em>Other Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Other Role</em>' reference.
	 * @see #getOtherRole()
	 * @generated
	 */
	void setOtherRole(RoleType value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' reference.
	 * @see #setRole(RoleType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleCombinationConstraint_Role()
	 * @model required="true"
	 * @generated
	 */
	RoleType getRole();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint#getRole <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' reference.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(RoleType value);

} // RoleCombinationConstraint
