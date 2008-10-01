/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model.impl;

import de.topicmapslab.tmcledit.diagram.model.AssociationNode;
import de.topicmapslab.tmcledit.diagram.model.ModelPackage;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.impl.AssociationNodeImpl#getAssociationTypeConstraint <em>Association Type Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationNodeImpl extends NodeImpl implements AssociationNode {
	/**
	 * The default value of the '{@link #getAssociationTypeConstraint() <em>Association Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final AssociationTypeConstraint ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getAssociationTypeConstraint() <em>Association Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected AssociationTypeConstraint associationTypeConstraint = ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT;

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
	public AssociationTypeConstraint getAssociationTypeConstraint() {
		return associationTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationTypeConstraint(AssociationTypeConstraint newAssociationTypeConstraint) {
		AssociationTypeConstraint oldAssociationTypeConstraint = associationTypeConstraint;
		associationTypeConstraint = newAssociationTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_NODE__ASSOCIATION_TYPE_CONSTRAINT, oldAssociationTypeConstraint, associationTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_TYPE_CONSTRAINT:
				return getAssociationTypeConstraint();
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
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_TYPE_CONSTRAINT:
				setAssociationTypeConstraint((AssociationTypeConstraint)newValue);
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
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_TYPE_CONSTRAINT:
				setAssociationTypeConstraint(ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT);
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
			case ModelPackage.ASSOCIATION_NODE__ASSOCIATION_TYPE_CONSTRAINT:
				return ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT == null ? associationTypeConstraint != null : !ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT.equals(associationTypeConstraint);
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
		result.append(" (associationTypeConstraint: ");
		result.append(associationTypeConstraint);
		result.append(')');
		return result.toString();
	}

} //AssociationNodeImpl
