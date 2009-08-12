/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Reg Exp Topic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AbstractRegExpTopicTypeImpl#getRegExp <em>Reg Exp</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractRegExpTopicTypeImpl extends TopicTypeImpl implements AbstractRegExpTopicType {
	/**
	 * The default value of the '{@link #getRegExp() <em>Reg Exp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegExp()
	 * @generated
	 * @ordered
	 */
	protected static final String REG_EXP_EDEFAULT = ".*";

	/**
	 * The cached value of the '{@link #getRegExp() <em>Reg Exp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegExp()
	 * @generated
	 * @ordered
	 */
	protected String regExp = REG_EXP_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractRegExpTopicTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ABSTRACT_REG_EXP_TOPIC_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getRegExp() {
		return regExp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRegExp(String newRegExp) {
		String oldRegExp = regExp;
		regExp = newRegExp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP, oldRegExp, regExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP:
				return getRegExp();
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
			case ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP:
				setRegExp((String)newValue);
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
			case ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP:
				setRegExp(REG_EXP_EDEFAULT);
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
			case ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP:
				return REG_EXP_EDEFAULT == null ? regExp != null : !REG_EXP_EDEFAULT.equals(regExp);
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
		result.append(" (regExp: ");
		result.append(regExp);
		result.append(')');
		return result.toString();
	}

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = super.hashCode();
	    result = prime * result + ((regExp == null) ? 0 : regExp.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (!super.equals(obj))
		    return false;
	    if (!(obj instanceof AbstractRegExpTopicTypeImpl))
		    return false;
	    AbstractRegExpTopicTypeImpl other = (AbstractRegExpTopicTypeImpl) obj;
	    if (regExp == null) {
		    if (other.regExp != null)
			    return false;
	    } else if (!regExp.equals(other.regExp))
		    return false;
	    return true;
    }

	
} //AbstractRegExpTopicTypeImpl
