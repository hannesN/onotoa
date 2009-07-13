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
 * A representation of the model object '<em><b>Topic Map Schema</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getTopicTypes <em>Topic Types</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getAssociationTypeConstraints <em>Association Type Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getMappings <em>Mappings</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getIncludes <em>Includes</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getBaseLocator <em>Base Locator</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema()
 * @model
 * @generated
 */
public interface TopicMapSchema extends TMCLConstruct {
	/**
	 * Returns the value of the '<em><b>Topic Types</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.TopicType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topic Types</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic Types</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_TopicTypes()
	 * @model containment="true"
	 * @generated
	 */
	EList<TopicType> getTopicTypes();

	/**
	 * Returns the value of the '<em><b>Association Type Constraints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Type Constraints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Type Constraints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_AssociationTypeConstraints()
	 * @model containment="true"
	 * @generated
	 */
	EList<AssociationTypeConstraint> getAssociationTypeConstraints();

	/**
	 * Returns the value of the '<em><b>Mappings</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.MappingElement}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Mappings</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Mappings</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_Mappings()
	 * @model containment="true"
	 * @generated
	 */
	EList<MappingElement> getMappings();

	/**
	 * Returns the value of the '<em><b>Includes</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Includes</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Includes</em>' attribute list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_Includes()
	 * @model
	 * @generated
	 */
	EList<String> getIncludes();

	/**
	 * Returns the value of the '<em><b>Base Locator</b></em>' attribute.
	 * The default value is <code>""</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Base Locator</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Base Locator</em>' attribute.
	 * @see #setBaseLocator(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_BaseLocator()
	 * @model default="" required="true"
	 * @generated
	 */
	String getBaseLocator();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getBaseLocator <em>Base Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Base Locator</em>' attribute.
	 * @see #getBaseLocator()
	 * @generated
	 */
	void setBaseLocator(String value);

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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicMapSchema_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TopicMapSchema#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

} // TopicMapSchema
