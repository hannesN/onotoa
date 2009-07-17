/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Abstract Constraint</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.AbstractConstraint#getRegexp <em>Regexp</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractConstraint()
 * @model abstract="true"
 * @generated
 */
public interface AbstractConstraint extends AbstractCardinalityContraint {
	/**
	 * Returns the value of the '<em><b>Regexp</b></em>' attribute.
	 * The default value is <code>".*"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Regexp</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Regexp</em>' attribute.
	 * @see #setRegexp(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getAbstractConstraint_Regexp()
	 * @model default=".*" required="true"
	 * @generated
	 */
	String getRegexp();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.AbstractConstraint#getRegexp <em>Regexp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Regexp</em>' attribute.
	 * @see #getRegexp()
	 * @generated
	 */
	void setRegexp(String value);

} // AbstractConstraint
