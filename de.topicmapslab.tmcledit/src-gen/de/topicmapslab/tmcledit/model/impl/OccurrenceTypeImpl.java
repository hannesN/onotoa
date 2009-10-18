/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractRegExpTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OccurrenceType;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.ScopedReifiableTopicType;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Occurrence Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl#getReifierConstraint <em>Reifier Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl#getRegExp <em>Reg Exp</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OccurrenceTypeImpl#getDataType <em>Data Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OccurrenceTypeImpl extends ScopedTopicTypeImpl implements OccurrenceType {
	/**
	 * The cached value of the '{@link #getReifierConstraint() <em>Reifier Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReifierConstraint()
	 * @generated
	 * @ordered
	 */
	protected ReifierConstraint reifierConstraint;

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
	 * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected static final boolean UNIQUE_EDEFAULT = false;

	/**
	 * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isUnique()
	 * @generated
	 * @ordered
	 */
	protected boolean unique = UNIQUE_EDEFAULT;

	/**
	 * The default value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected static final String DATA_TYPE_EDEFAULT = "xsd:anyType";

	/**
	 * The cached value of the '{@link #getDataType() <em>Data Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDataType()
	 * @generated
	 * @ordered
	 */
	protected String dataType = DATA_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OccurrenceTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.OCCURRENCE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReifierConstraint getReifierConstraint() {
		return reifierConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReifierConstraint(ReifierConstraint newReifierConstraint, NotificationChain msgs) {
		ReifierConstraint oldReifierConstraint = reifierConstraint;
		reifierConstraint = newReifierConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, newReifierConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReifierConstraint(ReifierConstraint newReifierConstraint) {
		if (newReifierConstraint != reifierConstraint) {
			NotificationChain msgs = null;
			if (reifierConstraint != null)
				msgs = ((InternalEObject)reifierConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT, null, msgs);
			if (newReifierConstraint != null)
				msgs = ((InternalEObject)newReifierConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT, null, msgs);
			msgs = basicSetReifierConstraint(newReifierConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT, newReifierConstraint, newReifierConstraint));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OCCURRENCE_TYPE__REG_EXP, oldRegExp, regExp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isUnique() {
		return unique;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setUnique(boolean newUnique) {
		boolean oldUnique = unique;
		unique = newUnique;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OCCURRENCE_TYPE__UNIQUE, oldUnique, unique));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getDataType() {
		return dataType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDataType(String newDataType) {
		String oldDataType = dataType;
		dataType = newDataType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OCCURRENCE_TYPE__DATA_TYPE, oldDataType, dataType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT:
				return basicSetReifierConstraint(null, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT:
				return getReifierConstraint();
			case ModelPackage.OCCURRENCE_TYPE__REG_EXP:
				return getRegExp();
			case ModelPackage.OCCURRENCE_TYPE__UNIQUE:
				return isUnique();
			case ModelPackage.OCCURRENCE_TYPE__DATA_TYPE:
				return getDataType();
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
			case ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)newValue);
				return;
			case ModelPackage.OCCURRENCE_TYPE__REG_EXP:
				setRegExp((String)newValue);
				return;
			case ModelPackage.OCCURRENCE_TYPE__UNIQUE:
				setUnique((Boolean)newValue);
				return;
			case ModelPackage.OCCURRENCE_TYPE__DATA_TYPE:
				setDataType((String)newValue);
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
			case ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)null);
				return;
			case ModelPackage.OCCURRENCE_TYPE__REG_EXP:
				setRegExp(REG_EXP_EDEFAULT);
				return;
			case ModelPackage.OCCURRENCE_TYPE__UNIQUE:
				setUnique(UNIQUE_EDEFAULT);
				return;
			case ModelPackage.OCCURRENCE_TYPE__DATA_TYPE:
				setDataType(DATA_TYPE_EDEFAULT);
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
			case ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT:
				return reifierConstraint != null;
			case ModelPackage.OCCURRENCE_TYPE__REG_EXP:
				return REG_EXP_EDEFAULT == null ? regExp != null : !REG_EXP_EDEFAULT.equals(regExp);
			case ModelPackage.OCCURRENCE_TYPE__UNIQUE:
				return unique != UNIQUE_EDEFAULT;
			case ModelPackage.OCCURRENCE_TYPE__DATA_TYPE:
				return DATA_TYPE_EDEFAULT == null ? dataType != null : !DATA_TYPE_EDEFAULT.equals(dataType);
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eBaseStructuralFeatureID(int derivedFeatureID, Class<?> baseClass) {
		if (baseClass == ReifiableTopicType.class) {
			switch (derivedFeatureID) {
				case ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT: return ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT;
				default: return -1;
			}
		}
		if (baseClass == ScopedReifiableTopicType.class) {
			switch (derivedFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AbstractRegExpTopicType.class) {
			switch (derivedFeatureID) {
				case ModelPackage.OCCURRENCE_TYPE__REG_EXP: return ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP;
				default: return -1;
			}
		}
		return super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public int eDerivedStructuralFeatureID(int baseFeatureID, Class<?> baseClass) {
		if (baseClass == ReifiableTopicType.class) {
			switch (baseFeatureID) {
				case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT: return ModelPackage.OCCURRENCE_TYPE__REIFIER_CONSTRAINT;
				default: return -1;
			}
		}
		if (baseClass == ScopedReifiableTopicType.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		if (baseClass == AbstractRegExpTopicType.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_REG_EXP_TOPIC_TYPE__REG_EXP: return ModelPackage.OCCURRENCE_TYPE__REG_EXP;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(", unique: ");
		result.append(unique);
		result.append(", dataType: ");
		result.append(dataType);
		result.append(')');
		return result.toString();
	}

} //OccurrenceTypeImpl
