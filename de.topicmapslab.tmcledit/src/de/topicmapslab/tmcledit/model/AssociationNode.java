/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationNode#getAssociationConstraint <em>Association Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationNode()
 * @model
 * @generated
 */
public interface AssociationNode extends Node {
	/**
	 * Returns the value of the '<em><b>Association Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Constraint</em>' reference.
	 * @see #setAssociationConstraint(AssociationTypeConstraint)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationNode_AssociationConstraint()
	 * @model
	 * @generated
	 */
	AssociationTypeConstraint getAssociationConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AssociationNode#getAssociationConstraint <em>Association Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Constraint</em>' reference.
	 * @see #getAssociationConstraint()
	 * @generated
	 */
	void setAssociationConstraint(AssociationTypeConstraint value);

} // AssociationNode
