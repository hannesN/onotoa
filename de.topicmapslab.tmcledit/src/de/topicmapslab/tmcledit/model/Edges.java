/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edges</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edges#getBendPoints <em>Bend Points</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edges#getRoleTypeConstraint <em>Role Type Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdges()
 * @model
 * @generated
 */
public interface Edges extends EObject {
	/**
	 * Returns the value of the '<em><b>Bend Points</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bend Points</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bend Points</em>' reference.
	 * @see #setBendPoints(BendPoint)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdges_BendPoints()
	 * @model
	 * @generated
	 */
	BendPoint getBendPoints();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.Edges#getBendPoints <em>Bend Points</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bend Points</em>' reference.
	 * @see #getBendPoints()
	 * @generated
	 */
	void setBendPoints(BendPoint value);

	/**
	 * Returns the value of the '<em><b>Role Type Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Type Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Type Constraint</em>' reference.
	 * @see #setRoleTypeConstraint(RoleTypeConstraints)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdges_RoleTypeConstraint()
	 * @model required="true"
	 * @generated
	 */
	RoleTypeConstraints getRoleTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.Edges#getRoleTypeConstraint <em>Role Type Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Type Constraint</em>' reference.
	 * @see #getRoleTypeConstraint()
	 * @generated
	 */
	void setRoleTypeConstraint(RoleTypeConstraints value);

} // Edges
