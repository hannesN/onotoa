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


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Cardinality Contraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMax <em>Card Max</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractCardinalityContraint()
 * @model abstract="true"
 * @generated
 */
public interface AbstractCardinalityContraint extends TMCLConstruct {
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractCardinalityContraint_CardMin()
	 * @model default="0"
	 * @generated
	 */
	String getCardMin();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMin <em>Card Min</em>}' attribute.
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractCardinalityContraint_CardMax()
	 * @model default="1"
	 * @generated
	 */
	String getCardMax();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint#getCardMax <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Card Max</em>' attribute.
	 * @see #getCardMax()
	 * @generated
	 */
	void setCardMax(String value);

} // AbstractCardinalityContraint
