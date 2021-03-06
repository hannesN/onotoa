/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Topic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getIdentifiers <em>Identifiers</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getIdType <em>Id Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getIsa <em>Isa</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getAko <em>Ako</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getOccurrenceConstraints <em>Occurrence Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getNameConstraints <em>Name Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectIdentifierConstraints <em>Subject Identifier Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectLocatorConstraints <em>Subject Locator Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getKind <em>Kind</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getOverlap <em>Overlap</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getName <em>Name</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getLocators <em>Locators</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getTopicReifiesConstraints <em>Topic Reifies Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getItemIdentifierConstraints <em>Item Identifier Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType()
 * @model
 * @generated
 */
public interface TopicType extends TMCLConstruct {
	/**
	 * Returns the value of the '<em><b>Identifiers</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Identifiers</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Identifiers</em>' attribute list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Identifiers()
	 * @model
	 * @generated
	 */
	EList<String> getIdentifiers();

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
	 * Returns the value of the '<em><b>Abstract</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Abstract</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Abstract</em>' attribute.
	 * @see #setAbstract(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Abstract()
	 * @model default="false" required="true"
	 * @generated
	 */
	boolean isAbstract();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicType#isAbstract <em>Abstract</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Abstract</em>' attribute.
	 * @see #isAbstract()
	 * @generated
	 */
	void setAbstract(boolean value);

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
	 * Returns the value of the '<em><b>Occurrence Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Occurrence Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Occurrence Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_OccurrenceConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<OccurrenceTypeConstraint> getOccurrenceConstraints();

	/**
	 * Returns the value of the '<em><b>Name Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.NameTypeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_NameConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<NameTypeConstraint> getNameConstraints();

	/**
	 * Returns the value of the '<em><b>Subject Identifier Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint}.
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
	EList<SubjectIdentifierConstraint> getSubjectIdentifierConstraints();

	/**
	 * Returns the value of the '<em><b>Subject Locator Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.SubjectLocatorConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Subject Locator Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Subject Locator Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_SubjectLocatorConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<SubjectLocatorConstraint> getSubjectLocatorConstraints();

	/**
	 * Returns the value of the '<em><b>Kind</b></em>' attribute.
	 * The default value is <code>"TopicType"</code>.
	 * The literals are from the enumeration {@link de.topicmapslab.tmcledit.model.KindOfTopicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Kind</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Kind</em>' attribute.
	 * @see de.topicmapslab.tmcledit.model.KindOfTopicType
	 * @see #setKind(KindOfTopicType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Kind()
	 * @model default="TopicType" required="true"
	 * @generated
	 */
	KindOfTopicType getKind();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicType#getKind <em>Kind</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Kind</em>' attribute.
	 * @see de.topicmapslab.tmcledit.model.KindOfTopicType
	 * @see #getKind()
	 * @generated
	 */
	void setKind(KindOfTopicType value);

	/**
	 * Returns the value of the '<em><b>Overlap</b></em>' reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.TopicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Overlap</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Overlap</em>' reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Overlap()
	 * @model
	 * @generated
	 */
	EList<TopicType> getOverlap();

	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Name()
	 * @model required="true"
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicType#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Locators</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Locators</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Locators</em>' attribute list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Locators()
	 * @model
	 * @generated
	 */
	EList<String> getLocators();

	/**
	 * Returns the value of the '<em><b>Topic Reifies Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.TopicReifiesConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topic Reifies Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic Reifies Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_TopicReifiesConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<TopicReifiesConstraint> getTopicReifiesConstraints();

	/**
	 * Returns the value of the '<em><b>Item Identifier Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.ItemIdentifierConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Item Identifier Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Item Identifier Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_ItemIdentifierConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<ItemIdentifierConstraint> getItemIdentifierConstraints();

} // TopicType
