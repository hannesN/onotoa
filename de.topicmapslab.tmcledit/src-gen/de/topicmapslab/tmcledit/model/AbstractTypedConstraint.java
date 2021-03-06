/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Typed Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AbstractTypedConstraint#getType <em>Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractTypedConstraint()
 * @model abstract="true"
 * @generated
 */
public interface AbstractTypedConstraint extends AbstractConstraint {
	/**
	 * Returns the value of the '<em><b>Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' reference.
	 * @see #setType(TopicType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractTypedConstraint_Type()
	 * @model required="true"
	 * @generated
	 */
	TopicType getType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AbstractTypedConstraint#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TopicType value);

} // AbstractTypedConstraint
