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

import de.topicmapslab.tmcledit.model.Bendpoints;
import de.topicmapslab.tmcledit.model.Edge;
import de.topicmapslab.tmcledit.model.EdgeType;
import de.topicmapslab.tmcledit.model.LabelPos;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.Node;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl#getSource <em>Source</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl#getTarget <em>Target</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl#getRoleConstraint <em>Role Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.EdgeImpl#getLabelPositions <em>Label Positions</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EdgeImpl extends EObjectImpl implements Edge {
	/**
	 * The cached value of the '{@link #getBendpoints() <em>Bendpoints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBendpoints()
	 * @generated
	 * @ordered
	 */
	protected EList<Bendpoints> bendpoints;

	/**
	 * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSource()
	 * @generated
	 * @ordered
	 */
	protected Node source;

	/**
	 * The cached value of the '{@link #getTarget() <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTarget()
	 * @generated
	 * @ordered
	 */
	protected Node target;

	/**
	 * The default value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected static final EdgeType TYPE_EDEFAULT = EdgeType.IS_ATYPE;

	/**
	 * The cached value of the '{@link #getType() <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected EdgeType type = TYPE_EDEFAULT;

	/**
	 * The cached value of the '{@link #getRoleConstraint() <em>Role Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleConstraint()
	 * @generated
	 * @ordered
	 */
	protected RolePlayerConstraint roleConstraint;

	/**
	 * The cached value of the '{@link #getLabelPositions() <em>Label Positions</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLabelPositions()
	 * @generated
	 * @ordered
	 */
	protected EList<LabelPos> labelPositions;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected EdgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.EDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Bendpoints> getBendpoints() {
		if (bendpoints == null) {
			bendpoints = new EObjectContainmentEList<Bendpoints>(Bendpoints.class, this, ModelPackage.EDGE__BENDPOINTS);
		}
		return bendpoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getSource() {
		if (source != null && source.eIsProxy()) {
			InternalEObject oldSource = (InternalEObject)source;
			source = (Node)eResolveProxy(oldSource);
			if (source != oldSource) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.EDGE__SOURCE, oldSource, source));
			}
		}
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetSource() {
		return source;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSource(Node newSource) {
		Node oldSource = source;
		source = newSource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EDGE__SOURCE, oldSource, source));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node getTarget() {
		if (target != null && target.eIsProxy()) {
			InternalEObject oldTarget = (InternalEObject)target;
			target = (Node)eResolveProxy(oldTarget);
			if (target != oldTarget) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.EDGE__TARGET, oldTarget, target));
			}
		}
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node basicGetTarget() {
		return target;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTarget(Node newTarget) {
		Node oldTarget = target;
		target = newTarget;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EDGE__TARGET, oldTarget, target));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EdgeType getType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(EdgeType newType) {
		EdgeType oldType = type;
		type = newType == null ? TYPE_EDEFAULT : newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EDGE__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RolePlayerConstraint getRoleConstraint() {
		if (roleConstraint != null && roleConstraint.eIsProxy()) {
			InternalEObject oldRoleConstraint = (InternalEObject)roleConstraint;
			roleConstraint = (RolePlayerConstraint)eResolveProxy(oldRoleConstraint);
			if (roleConstraint != oldRoleConstraint) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.EDGE__ROLE_CONSTRAINT, oldRoleConstraint, roleConstraint));
			}
		}
		return roleConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RolePlayerConstraint basicGetRoleConstraint() {
		return roleConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRoleConstraint(RolePlayerConstraint newRoleConstraint) {
		RolePlayerConstraint oldRoleConstraint = roleConstraint;
		roleConstraint = newRoleConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.EDGE__ROLE_CONSTRAINT, oldRoleConstraint, roleConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<LabelPos> getLabelPositions() {
		if (labelPositions == null) {
			labelPositions = new EObjectContainmentEList<LabelPos>(LabelPos.class, this, ModelPackage.EDGE__LABEL_POSITIONS);
		}
		return labelPositions;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.EDGE__BENDPOINTS:
				return ((InternalEList<?>)getBendpoints()).basicRemove(otherEnd, msgs);
			case ModelPackage.EDGE__LABEL_POSITIONS:
				return ((InternalEList<?>)getLabelPositions()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.EDGE__BENDPOINTS:
				return getBendpoints();
			case ModelPackage.EDGE__SOURCE:
				if (resolve) return getSource();
				return basicGetSource();
			case ModelPackage.EDGE__TARGET:
				if (resolve) return getTarget();
				return basicGetTarget();
			case ModelPackage.EDGE__TYPE:
				return getType();
			case ModelPackage.EDGE__ROLE_CONSTRAINT:
				if (resolve) return getRoleConstraint();
				return basicGetRoleConstraint();
			case ModelPackage.EDGE__LABEL_POSITIONS:
				return getLabelPositions();
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
			case ModelPackage.EDGE__BENDPOINTS:
				getBendpoints().clear();
				getBendpoints().addAll((Collection<? extends Bendpoints>)newValue);
				return;
			case ModelPackage.EDGE__SOURCE:
				setSource((Node)newValue);
				return;
			case ModelPackage.EDGE__TARGET:
				setTarget((Node)newValue);
				return;
			case ModelPackage.EDGE__TYPE:
				setType((EdgeType)newValue);
				return;
			case ModelPackage.EDGE__ROLE_CONSTRAINT:
				setRoleConstraint((RolePlayerConstraint)newValue);
				return;
			case ModelPackage.EDGE__LABEL_POSITIONS:
				getLabelPositions().clear();
				getLabelPositions().addAll((Collection<? extends LabelPos>)newValue);
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
			case ModelPackage.EDGE__BENDPOINTS:
				getBendpoints().clear();
				return;
			case ModelPackage.EDGE__SOURCE:
				setSource((Node)null);
				return;
			case ModelPackage.EDGE__TARGET:
				setTarget((Node)null);
				return;
			case ModelPackage.EDGE__TYPE:
				setType(TYPE_EDEFAULT);
				return;
			case ModelPackage.EDGE__ROLE_CONSTRAINT:
				setRoleConstraint((RolePlayerConstraint)null);
				return;
			case ModelPackage.EDGE__LABEL_POSITIONS:
				getLabelPositions().clear();
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
			case ModelPackage.EDGE__BENDPOINTS:
				return bendpoints != null && !bendpoints.isEmpty();
			case ModelPackage.EDGE__SOURCE:
				return source != null;
			case ModelPackage.EDGE__TARGET:
				return target != null;
			case ModelPackage.EDGE__TYPE:
				return type != TYPE_EDEFAULT;
			case ModelPackage.EDGE__ROLE_CONSTRAINT:
				return roleConstraint != null;
			case ModelPackage.EDGE__LABEL_POSITIONS:
				return labelPositions != null && !labelPositions.isEmpty();
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
		result.append(" (type: ");
		result.append(type);
		result.append(')');
		return result.toString();
	}

} //EdgeImpl
