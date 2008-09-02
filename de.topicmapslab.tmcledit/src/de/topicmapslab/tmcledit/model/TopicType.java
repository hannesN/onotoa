/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Topic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getId <em>Id</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getIdType <em>Id Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#isIsAbstract <em>Is Abstract</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getIsa <em>Isa</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getAko <em>Ako</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getOccurenceConstraints <em>Occurence Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getNameContraints <em>Name Contraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectIdentifierConstraints <em>Subject Identifier Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectLocatorConstraint <em>Subject Locator Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType()
 * @model
 * @generated
 */
public interface TopicType extends EObject {
	/**
	 * Returns the value of the '<em><b>Id</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id</em>' attribute.
	 * @see #setId(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Id()
	 * @model required="true"
	 * @generated
	 */
	String getId();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicType#getId <em>Id</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id</em>' attribute.
	 * @see #getId()
	 * @generated
	 */
	void setId(String value);

	/**
	 * Returns the value of the '<em><b>Id Type</b></em>' attribute.
	 * The default value is <code>"IDENTIFIER"</code>.
	 * The literals are from the enumeration {@link de.topicmapslab.tmcledit.model.TopicId}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Id Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Id Type</em>' attribute.
	 * @see de.topicmapslab.tmcledit.model.TopicId
	 * @see #setIdType(TopicId)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_IdType()
	 * @model default="IDENTIFIER" required="true"
	 * @generated
	 */
	TopicId getIdType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicType#getIdType <em>Id Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Id Type</em>' attribute.
	 * @see de.topicmapslab.tmcledit.model.TopicId
	 * @see #getIdType()
	 * @generated
	 */
	void setIdType(TopicId value);

	/**
	 * Returns the value of the '<em><b>Is Abstract</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Is Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Is Abstract</em>' attribute.
	 * @see #setIsAbstract(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_IsAbstract()
	 * @model
	 * @generated
	 */
	boolean isIsAbstract();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicType#isIsAbstract <em>Is Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Is Abstract</em>' attribute.
	 * @see #isIsAbstract()
	 * @generated
	 */
	void setIsAbstract(boolean value);

	/**
	 * Returns the value of the '<em><b>Isa</b></em>' reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.TopicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Isa</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Isa</em>' reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Isa()
	 * @model
	 * @generated
	 */
	EList<TopicType> getIsa();

	/**
	 * Returns the value of the '<em><b>Ako</b></em>' reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.TopicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ako</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ako</em>' reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Ako()
	 * @model
	 * @generated
	 */
	EList<TopicType> getAko();

	/**
	 * Returns the value of the '<em><b>Occurence Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occurence Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occurence Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_OccurenceConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<OccurenceTypeConstraint> getOccurenceConstraints();

	/**
	 * Returns the value of the '<em><b>Name Contraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.NameTypeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name Contraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Contraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_NameContraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<NameTypeConstraint> getNameContraints();

	/**
	 * Returns the value of the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.subjectIdentifierConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject Identifier Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Identifier Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_SubjectIdentifierConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<subjectIdentifierConstraint> getSubjectIdentifierConstraints();

	/**
	 * Returns the value of the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.subjectLocatorConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject Locator Constraint</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Locator Constraint</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_SubjectLocatorConstraint()
	 * @model containment="true"
	 * @generated
	 */
	EList<subjectLocatorConstraint> getSubjectLocatorConstraint();

} // TopicType
