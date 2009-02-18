/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleConstraint;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Player Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RolePlayerConstraintImpl#getPlayer <em>Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RolePlayerConstraintImpl#getRole <em>Role</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RolePlayerConstraintImpl extends AbstractCardinalityContraintImpl implements RolePlayerConstraint {
	/**
	 * The cached value of the '{@link #getPlayer() <em>Player</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlayer()
	 * @generated
	 * @ordered
	 */
	protected TopicType player;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected RoleConstraint role;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RolePlayerConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ROLE_PLAYER_CONSTRAINT;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getPlayer() {
		if (player != null && player.eIsProxy()) {
			InternalEObject oldPlayer = (InternalEObject)player;
			player = (TopicType)eResolveProxy(oldPlayer);
			if (player != oldPlayer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_PLAYER_CONSTRAINT__PLAYER, oldPlayer, player));
			}
		}
		return player;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType basicGetPlayer() {
		return player;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setPlayer(TopicType newPlayer) {
		TopicType oldPlayer = player;
		player = newPlayer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_PLAYER_CONSTRAINT__PLAYER, oldPlayer, player));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleConstraint getRole() {
		if (role != null && role.eIsProxy()) {
			InternalEObject oldRole = (InternalEObject)role;
			role = (RoleConstraint)eResolveProxy(oldRole);
			if (role != oldRole) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE, oldRole, role));
			}
		}
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleConstraint basicGetRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRole(RoleConstraint newRole) {
		RoleConstraint oldRole = role;
		role = newRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE, oldRole, role));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__PLAYER:
				if (resolve) return getPlayer();
				return basicGetPlayer();
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE:
				if (resolve) return getRole();
				return basicGetRole();
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
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__PLAYER:
				setPlayer((TopicType)newValue);
				return;
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE:
				setRole((RoleConstraint)newValue);
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
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__PLAYER:
				setPlayer((TopicType)null);
				return;
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE:
				setRole((RoleConstraint)null);
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
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__PLAYER:
				return player != null;
			case ModelPackage.ROLE_PLAYER_CONSTRAINT__ROLE:
				return role != null;
		}
		return super.eIsSet(featureID);
	}

} //RolePlayerConstraintImpl
