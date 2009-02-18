/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationNode;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationNodeImpl#getAssociationConstraint <em>Association Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationNodeImpl extends NodeImpl implements AssociationNode {
	/**
	 * The cached value of the '{@link #getAssociationConstraint() <em>Association Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationConstraint()
	 * @generated
	 * @ordered
	 */
	protected AssociationTypeConstraint associationConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AssociationNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ASSOCIATION_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationTypeConstraint getAssociationConstraint() {
		if (associationConstraint != null && associationConstraint.eIsProxy()) {
			InternalEObject oldAssociationConstraint = (InternalEObject)associationConstraint;
			associationConstraint = (AssociationTypeConstraint)eResolveProxy(oldAssociationConstraint);
			if (associationConstraint != oldAssociationConstraint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT, oldAssociationConstraint, associationConstraint));
			}
		}
		return associationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationTypeConstraint basicGetAssociationConstraint() {
		return associationConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationConstraint(AssociationTypeConstraint newAssociationConstraint) {
		AssociationTypeConstraint oldAssociationConstraint = associationConstraint;
		associationConstraint = newAssociationConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT, oldAssociationConstraint, associationConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT:
				if (resolve) return getAssociationConstraint();
				return basicGetAssociationConstraint();
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
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT:
				setAssociationConstraint((AssociationTypeConstraint)newValue);
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
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT:
				setAssociationConstraint((AssociationTypeConstraint)null);
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
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_CONSTRAINT:
				return associationConstraint != null;
		}
		return super.eIsSet(featureID);
	}

} //AssociationNodeImpl
