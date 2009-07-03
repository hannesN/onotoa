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
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationType;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;
import de.topicmapslab.tmcledit.model.RoleConstraint;

import de.topicmapslab.tmcledit.model.ScopedReifiableTopicType;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeImpl#getReifierConstraint <em>Reifier Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeImpl#getRoles <em>Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationTypeImpl extends ScopedTopicTypeImpl implements AssociationType {
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
	 * The cached value of the '{@link #getRoles() <em>Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleConstraint> roles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected AssociationTypeImpl() {
		super();
		setKind(KindOfTopicType.ASSOCIATION_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ASSOCIATION_TYPE;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, reifierConstraint));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, reifierConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RoleConstraint> getRoles() {
		if (roles == null) {
			roles = new EObjectContainmentEList<RoleConstraint>(RoleConstraint.class, this, ModelPackage.ASSOCIATION_TYPE__ROLES);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.ASSOCIATION_TYPE__ROLES:
				return ((InternalEList<?>)getRoles()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT:
				if (resolve) return getReifierConstraint();
				return basicGetReifierConstraint();
			case ModelPackage.ASSOCIATION_TYPE__ROLES:
				return getRoles();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection<? extends RoleConstraint>)newValue);
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
			case ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)null);
				return;
			case ModelPackage.ASSOCIATION_TYPE__ROLES:
				getRoles().clear();
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
			case ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT:
				return reifierConstraint != null;
			case ModelPackage.ASSOCIATION_TYPE__ROLES:
				return roles != null && !roles.isEmpty();
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
				case ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT: return ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT;
				default: return -1;
			}
		}
		if (baseClass == ScopedReifiableTopicType.class) {
			switch (derivedFeatureID) {
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
				case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT: return ModelPackage.ASSOCIATION_TYPE__REIFIER_CONSTRAINT;
				default: return -1;
			}
		}
		if (baseClass == ScopedReifiableTopicType.class) {
			switch (baseFeatureID) {
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
	}

} //AssociationTypeImpl
