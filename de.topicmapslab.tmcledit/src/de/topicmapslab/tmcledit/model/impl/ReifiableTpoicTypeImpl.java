/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTpoicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reifiable Tpoic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.ReifiableTpoicTypeImpl#getReifierConstraint <em>Reifier Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReifiableTpoicTypeImpl extends TopicTypeImpl implements ReifiableTpoicType {
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
	protected ReifiableTpoicTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.REIFIABLE_TPOIC_TYPE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.REIFIABLE_TPOIC_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, reifierConstraint));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REIFIABLE_TPOIC_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, reifierConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.REIFIABLE_TPOIC_TYPE__REIFIER_CONSTRAINT:
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
			case ModelPackage.REIFIABLE_TPOIC_TYPE__REIFIER_CONSTRAINT:
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
			case ModelPackage.REIFIABLE_TPOIC_TYPE__REIFIER_CONSTRAINT:
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
			case ModelPackage.REIFIABLE_TPOIC_TYPE__REIFIER_CONSTRAINT:
				return reifierConstraint != null;
		}
		return super.eIsSet(featureID);
	}

} //ReifiableTpoicTypeImpl
