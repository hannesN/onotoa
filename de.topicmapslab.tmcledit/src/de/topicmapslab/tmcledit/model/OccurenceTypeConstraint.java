/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Occurence Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#isUnique <em>Unique</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOccurenceTypeConstraint()
 * @model
 * @generated
 */
public interface OccurenceTypeConstraint extends AbstractConstraint, AbstractTypedCardinalityConstraint {
	/**
	 * Returns the value of the '<em><b>Unique</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique</em>' attribute.
	 * @see #setUnique(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOccurenceTypeConstraint_Unique()
	 * @model
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint#isUnique <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique</em>' attribute.
	 * @see #isUnique()
	 * @generated
	 */
	void setUnique(boolean value);

} // OccurenceTypeConstraint
