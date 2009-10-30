/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Reg Exp Topic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AbstractRegExpTopicType#getRegExp <em>Reg Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractRegExpTopicType()
 * @model abstract="true"
 * @generated
 */
public interface AbstractRegExpTopicType extends TopicType {
	/**
	 * Returns the value of the '<em><b>Reg Exp</b></em>' attribute.
	 * The default value is <code>".*"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Reg Exp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Reg Exp</em>' attribute.
	 * @see #setRegExp(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractRegExpTopicType_RegExp()
	 * @model default=".*" required="true"
	 * @generated
	 */
	String getRegExp();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AbstractRegExpTopicType#getRegExp <em>Reg Exp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Reg Exp</em>' attribute.
	 * @see #getRegExp()
	 * @generated
	 */
	void setRegExp(String value);

} // AbstractRegExpTopicType
