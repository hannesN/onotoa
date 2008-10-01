/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model;

import de.topicmapslab.tmcledit.model.TopicType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Type Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.TypeNode#getTopicType <em>Topic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getTypeNode()
 * @model
 * @generated
 */
public interface TypeNode extends Node {
	/**
	 * Returns the value of the '<em><b>Topic Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topic Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic Type</em>' attribute.
	 * @see #setTopicType(TopicType)
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getTypeNode_TopicType()
	 * @model dataType="de.topicmapslab.tmcledit.diagram.model.TopicType" required="true"
	 * @generated
	 */
	TopicType getTopicType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.diagram.model.TypeNode#getTopicType <em>Topic Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topic Type</em>' attribute.
	 * @see #getTopicType()
	 * @generated
	 */
	void setTopicType(TopicType value);

} // TypeNode
