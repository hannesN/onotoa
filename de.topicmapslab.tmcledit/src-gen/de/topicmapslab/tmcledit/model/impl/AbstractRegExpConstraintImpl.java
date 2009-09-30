/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractRegExpConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Reg Exp Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AbstractRegExpConstraintImpl#getRegexp <em>Regexp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractRegExpConstraintImpl extends TMCLConstructImpl implements AbstractRegExpConstraint {
	/**
	 * The default value of the '{@link #getRegexp() <em>Regexp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegexp()
	 * @generated
	 * @ordered
	 */
	protected static final String REGEXP_EDEFAULT = ".*";

	/**
	 * The cached value of the '{@link #getRegexp() <em>Regexp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegexp()
	 * @generated
	 * @ordered
	 */
	protected String regexp = REGEXP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractRegExpConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ABSTRACT_REG_EXP_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRegexp() {
		return regexp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRegexp(String newRegexp) {
		String oldRegexp = regexp;
		regexp = newRegexp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ABSTRACT_REG_EXP_CONSTRAINT__REGEXP, oldRegexp, regexp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ABSTRACT_REG_EXP_CONSTRAINT__REGEXP:
				return getRegexp();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.ABSTRACT_REG_EXP_CONSTRAINT__REGEXP:
				setRegexp((String)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case ModelPackage.ABSTRACT_REG_EXP_CONSTRAINT__REGEXP:
				setRegexp(REGEXP_EDEFAULT);
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case ModelPackage.ABSTRACT_REG_EXP_CONSTRAINT__REGEXP:
				return REGEXP_EDEFAULT == null ? regexp != null : !REGEXP_EDEFAULT.equals(regexp);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (regexp: ");
		result.append(regexp);
		result.append(')');
		return result.toString();
	}

} //AbstractRegExpConstraintImpl
