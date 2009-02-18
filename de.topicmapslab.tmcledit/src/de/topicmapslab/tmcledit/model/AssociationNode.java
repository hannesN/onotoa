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
 * A representation of the model object '<em><b>Association Node</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AssociationNode#getAssociationConstraint <em>Association Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationNode()
 * @model
 * @generated
 */
public interface AssociationNode extends Node {
	/**
	 * Returns the value of the '<em><b>Association Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Association Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Association Constraint</em>' reference.
	 * @see #setAssociationConstraint(AssociationTypeConstraint)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAssociationNode_AssociationConstraint()
	 * @model required="true"
	 * @generated
	 */
	AssociationTypeConstraint getAssociationConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AssociationNode#getAssociationConstraint <em>Association Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Association Constraint</em>' reference.
	 * @see #getAssociationConstraint()
	 * @generated
	 */
	void setAssociationConstraint(AssociationTypeConstraint value);

} // AssociationNode
