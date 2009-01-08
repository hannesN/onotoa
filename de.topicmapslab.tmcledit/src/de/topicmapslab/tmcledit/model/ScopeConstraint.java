/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scope Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.ScopeConstraint#getType <em>Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.ScopeConstraint#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.ScopeConstraint#getCardMax <em>Card Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getScopeConstraint()
 * @model
 * @generated
 */
public interface ScopeConstraint extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getScopeConstraint_Type()
	 * @model required="true"
	 * @generated
	 */
	TopicType getType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.ScopeConstraint#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TopicType value);

	/**
	 * Returns the value of the '<em><b>Card Min</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Card Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Card Min</em>' attribute.
	 * @see #setCardMin(int)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getScopeConstraint_CardMin()
	 * @model
	 * @generated
	 */
	int getCardMin();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.ScopeConstraint#getCardMin <em>Card Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Card Min</em>' attribute.
	 * @see #getCardMin()
	 * @generated
	 */
	void setCardMin(int value);

	/**
	 * Returns the value of the '<em><b>Card Max</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Card Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Card Max</em>' attribute.
	 * @see #setCardMax(int)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getScopeConstraint_CardMax()
	 * @model
	 * @generated
	 */
	int getCardMax();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.ScopeConstraint#getCardMax <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Card Max</em>' attribute.
	 * @see #getCardMax()
	 * @generated
	 */
	void setCardMax(int value);

} // ScopeConstraint
