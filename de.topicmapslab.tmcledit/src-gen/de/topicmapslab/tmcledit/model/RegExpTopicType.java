/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Reg Exp Topic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.RegExpTopicType#getRegExp <em>Reg Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRegExpTopicType()
 * @model abstract="true"
 * @generated
 */
public interface RegExpTopicType extends TopicType {
	/**
	 * Returns the value of the '<em><b>Reg Exp</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reg Exp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reg Exp</em>' attribute.
	 * @see #setRegExp(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getRegExpTopicType_RegExp()
	 * @model required="true"
	 * @generated
	 */
	String getRegExp();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.RegExpTopicType#getRegExp <em>Reg Exp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reg Exp</em>' attribute.
	 * @see #getRegExp()
	 * @generated
	 */
	void setRegExp(String value);

} // RegExpTopicType
