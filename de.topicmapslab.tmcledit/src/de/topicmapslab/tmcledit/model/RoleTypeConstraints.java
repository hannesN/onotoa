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
 * A representation of the model object '<em><b>Role Type Constraints</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMax <em>Card Max</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getType <em>Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getTopicType <em>Topic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleTypeConstraints()
 * @model
 * @generated
 */
public interface RoleTypeConstraints extends EObject {
	/**
	 * Returns the value of the '<em><b>Card Min</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Card Min</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Card Min</em>' attribute.
	 * @see #setCardMin(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleTypeConstraints_CardMin()
	 * @model default="0" required="true"
	 * @generated
	 */
	String getCardMin();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMin <em>Card Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Card Min</em>' attribute.
	 * @see #getCardMin()
	 * @generated
	 */
	void setCardMin(String value);

	/**
	 * Returns the value of the '<em><b>Card Max</b></em>' attribute.
	 * The default value is <code>"1"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Card Max</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Card Max</em>' attribute.
	 * @see #setCardMax(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleTypeConstraints_CardMax()
	 * @model default="1" required="true"
	 * @generated
	 */
	String getCardMax();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getCardMax <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Card Max</em>' attribute.
	 * @see #getCardMax()
	 * @generated
	 */
	void setCardMax(String value);

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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleTypeConstraints_Type()
	 * @model required="true"
	 * @generated
	 */
	TopicType getType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getType <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' reference.
	 * @see #getType()
	 * @generated
	 */
	void setType(TopicType value);

	/**
	 * Returns the value of the '<em><b>Topic Type</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topic Type</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic Type</em>' reference.
	 * @see #setTopicType(TopicType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRoleTypeConstraints_TopicType()
	 * @model
	 * @generated
	 */
	TopicType getTopicType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints#getTopicType <em>Topic Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topic Type</em>' reference.
	 * @see #getTopicType()
	 * @generated
	 */
	void setTopicType(TopicType value);

} // RoleTypeConstraints
