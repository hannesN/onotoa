/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>TMCL Construct</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getSee_also <em>See also</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getComment <em>Comment</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getDescription <em>Description</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getExtension <em>Extension</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTMCLConstruct()
 * @model
 * @generated
 */
public interface TMCLConstruct extends EObject {
	/**
	 * Returns the value of the '<em><b>See also</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>See also</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>See also</em>' attribute.
	 * @see #setSee_also(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTMCLConstruct_See_also()
	 * @model
	 * @generated
	 */
	String getSee_also();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getSee_also <em>See also</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>See also</em>' attribute.
	 * @see #getSee_also()
	 * @generated
	 */
	void setSee_also(String value);

	/**
	 * Returns the value of the '<em><b>Comment</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Comment</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Comment</em>' attribute.
	 * @see #setComment(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTMCLConstruct_Comment()
	 * @model
	 * @generated
	 */
	String getComment();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getComment <em>Comment</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Comment</em>' attribute.
	 * @see #getComment()
	 * @generated
	 */
	void setComment(String value);

	/**
	 * Returns the value of the '<em><b>Description</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Description</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Description</em>' attribute.
	 * @see #setDescription(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTMCLConstruct_Description()
	 * @model
	 * @generated
	 */
	String getDescription();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.TMCLConstruct#getDescription <em>Description</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Description</em>' attribute.
	 * @see #getDescription()
	 * @generated
	 */
	void setDescription(String value);

	/**
	 * Returns the value of the '<em><b>Extension</b></em>' map.
	 * The key is of type {@link java.lang.String},
	 * and the value is of type {@link java.lang.String},
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Extension</em>' map isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Extension</em>' map.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTMCLConstruct_Extension()
	 * @model mapType="de.topicmapslab.tmcledit.model.EStringToEStringMap<org.eclipse.emf.ecore.EString, org.eclipse.emf.ecore.EString>"
	 * @generated
	 */
	EMap<String, String> getExtension();

} // TMCLConstruct
