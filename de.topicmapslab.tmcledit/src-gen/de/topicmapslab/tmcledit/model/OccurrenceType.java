/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Occurrence Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.OccurrenceType#isUnique <em>Unique</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.OccurrenceType#getDataType <em>Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOccurrenceType()
 * @model
 * @generated
 */
public interface OccurrenceType extends ScopedTopicType, ScopedReifiableTopicType, AbstractRegExpTopicType {
	/**
	 * Returns the value of the '<em><b>Data Type</b></em>' attribute.
	 * The default value is <code>"xsd:anyType"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Data Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Data Type</em>' attribute.
	 * @see #setDataType(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOccurrenceType_DataType()
	 * @model default="xsd:anyType" required="true"
	 * @generated
	 */
	String getDataType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.OccurrenceType#getDataType <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Data Type</em>' attribute.
	 * @see #getDataType()
	 * @generated
	 */
	void setDataType(String value);

	/**
	 * Returns the value of the '<em><b>Unique</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Unique</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Unique</em>' attribute.
	 * @see #setUnique(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getOccurrenceType_Unique()
	 * @model default="false"
	 * @generated
	 */
	boolean isUnique();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.OccurrenceType#isUnique <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Unique</em>' attribute.
	 * @see #isUnique()
	 * @generated
	 */
	void setUnique(boolean value);

} // OccurrenceType
