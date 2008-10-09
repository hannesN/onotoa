/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.TypeNode#getTopicType <em>Topic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTypeNode()
 * @model
 * @generated
 */
public interface TypeNode extends Node {
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTypeNode_TopicType()
	 * @model required="true"
	 * @generated
	 */
	TopicType getTopicType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TypeNode#getTopicType <em>Topic Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topic Type</em>' reference.
	 * @see #getTopicType()
	 * @generated
	 */
	void setTopicType(TopicType value);

} // TypeNode
