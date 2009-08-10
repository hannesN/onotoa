/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Association Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint#getPlayerConstraints <em>Player Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationTypeConstraint()
 * @model
 * @generated
 */
public interface AssociationTypeConstraint extends AbstractRegExpConstraint, AbstractTypedCardinalityConstraint {
	/**
	 * Returns the value of the '<em><b>Player Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.RolePlayerConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Player Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Player Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationTypeConstraint_PlayerConstraints()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<RolePlayerConstraint> getPlayerConstraints();

} // AssociationTypeConstraint
