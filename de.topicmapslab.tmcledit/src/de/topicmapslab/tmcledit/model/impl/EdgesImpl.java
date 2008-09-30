/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.BendPoint;
import de.topicmapslab.tmcledit.model.Edges;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edges</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgesImpl#getBendPoints <em>Bend Points</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgesImpl#getRoleTypeConstraint <em>Role Type Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgesImpl extends EObjectImpl implements Edges {
	/**
	 * The cached value of the '{@link #getBendPoints() <em>Bend Points</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBendPoints()
	 * @generated
	 * @ordered
	 */
	protected BendPoint bendPoints;

	/**
	 * The cached value of the '{@link #getRoleTypeConstraint() <em>Role Type Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected RoleTypeConstraints roleTypeConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgesImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.EDGES;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BendPoint getBendPoints() {
		if (bendPoints != null && bendPoints.eIsProxy()) {
			InternalEObject oldBendPoints = (InternalEObject)bendPoints;
			bendPoints = (BendPoint)eResolveProxy(oldBendPoints);
			if (bendPoints != oldBendPoints) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.EDGES__BEND_POINTS, oldBendPoints, bendPoints));
			}
		}
		return bendPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BendPoint basicGetBendPoints() {
		return bendPoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBendPoints(BendPoint newBendPoints) {
		BendPoint oldBendPoints = bendPoints;
		bendPoints = newBendPoints;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EDGES__BEND_POINTS, oldBendPoints, bendPoints));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleTypeConstraints getRoleTypeConstraint() {
		if (roleTypeConstraint != null && roleTypeConstraint.eIsProxy()) {
			InternalEObject oldRoleTypeConstraint = (InternalEObject)roleTypeConstraint;
			roleTypeConstraint = (RoleTypeConstraints)eResolveProxy(oldRoleTypeConstraint);
			if (roleTypeConstraint != oldRoleTypeConstraint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.EDGES__ROLE_TYPE_CONSTRAINT, oldRoleTypeConstraint, roleTypeConstraint));
			}
		}
		return roleTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleTypeConstraints basicGetRoleTypeConstraint() {
		return roleTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleTypeConstraint(RoleTypeConstraints newRoleTypeConstraint) {
		RoleTypeConstraints oldRoleTypeConstraint = roleTypeConstraint;
		roleTypeConstraint = newRoleTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EDGES__ROLE_TYPE_CONSTRAINT, oldRoleTypeConstraint, roleTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.EDGES__BEND_POINTS:
				if (resolve) return getBendPoints();
				return basicGetBendPoints();
			case ModelPackage.EDGES__ROLE_TYPE_CONSTRAINT:
				if (resolve) return getRoleTypeConstraint();
				return basicGetRoleTypeConstraint();
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
			case ModelPackage.EDGES__BEND_POINTS:
				setBendPoints((BendPoint)newValue);
				return;
			case ModelPackage.EDGES__ROLE_TYPE_CONSTRAINT:
				setRoleTypeConstraint((RoleTypeConstraints)newValue);
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
			case ModelPackage.EDGES__BEND_POINTS:
				setBendPoints((BendPoint)null);
				return;
			case ModelPackage.EDGES__ROLE_TYPE_CONSTRAINT:
				setRoleTypeConstraint((RoleTypeConstraints)null);
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
			case ModelPackage.EDGES__BEND_POINTS:
				return bendPoints != null;
			case ModelPackage.EDGES__ROLE_TYPE_CONSTRAINT:
				return roleTypeConstraint != null;
		}
		return super.eIsSet(featureID);
	}

} //EdgesImpl
