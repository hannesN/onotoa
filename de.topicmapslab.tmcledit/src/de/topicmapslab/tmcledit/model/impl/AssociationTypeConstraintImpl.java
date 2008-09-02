/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.AssociationsType;
import de.topicmapslab.tmcledit.model.ScopeType;
import de.topicmapslab.tmcledit.model.TMPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getAssociationType <em>Association Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationTypeConstraintImpl extends EObjectImpl implements AssociationTypeConstraint {
	/**
	 * The cached value of the '{@link #getScope() <em>Scope</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScope()
	 * @generated
	 * @ordered
	 */
	protected ScopeType scope;

	/**
	 * The cached value of the '{@link #getAssociationType() <em>Association Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationType()
	 * @generated
	 * @ordered
	 */
	protected AssociationsType associationType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationTypeConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TMPackage.Literals.ASSOCIATION_TYPE_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeType getScope() {
		if (scope != null && scope.eIsProxy()) {
			InternalEObject oldScope = (InternalEObject)scope;
			scope = (ScopeType)eResolveProxy(oldScope);
			if (scope != oldScope) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TMPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE, oldScope, scope));
			}
		}
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeType basicGetScope() {
		return scope;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setScope(ScopeType newScope) {
		ScopeType oldScope = scope;
		scope = newScope;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TMPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE, oldScope, scope));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationsType getAssociationType() {
		if (associationType != null && associationType.eIsProxy()) {
			InternalEObject oldAssociationType = (InternalEObject)associationType;
			associationType = (AssociationsType)eResolveProxy(oldAssociationType);
			if (associationType != oldAssociationType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, TMPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE, oldAssociationType, associationType));
			}
		}
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationsType basicGetAssociationType() {
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationType(AssociationsType newAssociationType) {
		AssociationsType oldAssociationType = associationType;
		associationType = newAssociationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TMPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE, oldAssociationType, associationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				if (resolve) return getScope();
				return basicGetScope();
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				if (resolve) return getAssociationType();
				return basicGetAssociationType();
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
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				setScope((ScopeType)newValue);
				return;
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				setAssociationType((AssociationsType)newValue);
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
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				setScope((ScopeType)null);
				return;
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				setAssociationType((AssociationsType)null);
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
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				return scope != null;
			case TMPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				return associationType != null;
		}
		return super.eIsSet(featureID);
	}

} //AssociationTypeConstraintImpl
