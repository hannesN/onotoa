/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.AssociationNode#getAssociationTypeConstraint <em>Association Type Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getAssociationNode()
 * @model
 * @generated
 */
public interface AssociationNode extends Node {
	/**
	 * Returns the value of the '<em><b>Association Type Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type Constraint</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type Constraint</em>' attribute.
	 * @see #setAssociationTypeConstraint(AssociationTypeConstraint)
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getAssociationNode_AssociationTypeConstraint()
	 * @model dataType="de.topicmapslab.tmcledit.diagram.model.AssociationTypeConstraint" required="true"
	 * @generated
	 */
	AssociationTypeConstraint getAssociationTypeConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.diagram.model.AssociationNode#getAssociationTypeConstraint <em>Association Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Type Constraint</em>' attribute.
	 * @see #getAssociationTypeConstraint()
	 * @generated
	 */
	void setAssociationTypeConstraint(AssociationTypeConstraint value);

} // AssociationNode
