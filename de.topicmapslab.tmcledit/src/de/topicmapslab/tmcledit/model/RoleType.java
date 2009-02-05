/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Role Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleType#getOtherRoles <em>Other Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleType()
 * @model
 * @generated
 */
public interface RoleType extends TopicType {
	/**
	 * Returns the value of the '<em><b>Other Roles</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Other Roles</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Other Roles</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleType_OtherRoles()
	 * @model containment="true"
	 * @generated
	 */
	EList<OtherRolePlayerConstraint> getOtherRoles();

} // RoleType
