/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Player Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint#getPlayer <em>Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint#getRole <em>Role</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRolePlayerConstraint()
 * @model
 * @generated
 */
public interface RolePlayerConstraint extends AbstractCardinalityContraint {
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRolePlayerConstraint_Player()
	 * @model required="true"
	 * @generated
	 */
	TopicType getPlayer();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint#getPlayer <em>Player</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Player</em>' reference.
	 * @see #getPlayer()
	 * @generated
	 */
	void setPlayer(TopicType value);

	/**
	 * Returns the value of the '<em><b>Role</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role</em>' reference.
	 * @see #setRole(RoleConstraint)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRolePlayerConstraint_Role()
	 * @model required="true"
	 * @generated
	 */
	RoleConstraint getRole();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint#getRole <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role</em>' reference.
	 * @see #getRole()
	 * @generated
	 */
	void setRole(RoleConstraint value);

} // RolePlayerConstraint
