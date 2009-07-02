/*******************************************************************************
 * Copyright (c) 2008, 2009 Topic Maps Lab and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors:
 *     Hannes Niederhausen - initial API and implementation
 *******************************************************************************/
/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
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
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getNameContraints <em>Name Contraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectIdentifierConstraints <em>Subject Identifier Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getSubjectLocatorConstraint <em>Subject Locator Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getKind <em>Kind</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getExclusive <em>Exclusive</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getName <em>Name</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicType#getLocators <em>Locators</em>}</li>
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
	 * Returns the value of the '<em><b>Subject Locator Constraint</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.SubjectLocatorConstraint}.
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
	EList<SubjectLocatorConstraint> getSubjectLocatorConstraint();

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
	 * Returns the value of the '<em><b>Exclusive</b></em>' reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.TopicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Exclusive</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Exclusive</em>' reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicType_Exclusive()
	 * @model
	 * @generated
	 */
	EList<TopicType> getExclusive();

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

} // TopicType
