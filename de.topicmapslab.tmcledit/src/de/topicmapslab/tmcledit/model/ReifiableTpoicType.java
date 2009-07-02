/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reifiable Tpoic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.ReifiableTpoicType#getReifierConstraint <em>Reifier Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getReifiableTpoicType()
 * @model
 * @generated
 */
public interface ReifiableTpoicType extends TopicType {
	/**
	 * Returns the value of the '<em><b>Reifier Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reifier Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reifier Constraint</em>' reference.
	 * @see #setReifierConstraint(ReifierConstraint)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getReifiableTpoicType_ReifierConstraint()
	 * @model
	 * @generated
	 */
	ReifierConstraint getReifierConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.ReifiableTpoicType#getReifierConstraint <em>Reifier Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reifier Constraint</em>' reference.
	 * @see #getReifierConstraint()
	 * @generated
	 */
	void setReifierConstraint(ReifierConstraint value);

} // ReifiableTpoicType
