/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.ScopedReifiableTopicType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Scoped Reifiable Topic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.ScopedReifiableTopicTypeImpl#getReifierConstraint <em>Reifier Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ScopedReifiableTopicTypeImpl extends ScopedTopicTypeImpl implements ScopedReifiableTopicType {
	/**
	 * The cached value of the '{@link #getReifierConstraint() <em>Reifier Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReifierConstraint()
	 * @generated
	 * @ordered
	 */
	protected ReifierConstraint reifierConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ScopedReifiableTopicTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SCOPED_REIFIABLE_TOPIC_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReifierConstraint getReifierConstraint() {
		if (reifierConstraint != null && reifierConstraint.eIsProxy()) {
			InternalEObject oldReifierConstraint = (InternalEObject)reifierConstraint;
			reifierConstraint = (ReifierConstraint)eResolveProxy(oldReifierConstraint);
			if (reifierConstraint != oldReifierConstraint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, reifierConstraint));
			}
		}
		return reifierConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReifierConstraint basicGetReifierConstraint() {
		return reifierConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReifierConstraint(ReifierConstraint newReifierConstraint) {
		ReifierConstraint oldReifierConstraint = reifierConstraint;
		reifierConstraint = newReifierConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, reifierConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				if (resolve) return getReifierConstraint();
				return basicGetReifierConstraint();
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
			case ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)newValue);
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
			case ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)null);
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
			case ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				return reifierConstraint != null;
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
				case ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT: return ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT;
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
				case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT: return ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //ScopedReifiableTopicTypeImpl
