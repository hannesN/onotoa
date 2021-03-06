/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleCombinationConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Combination Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl#getPlayer <em>Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl#getOtherPlayer <em>Other Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl#getOtherRole <em>Other Role</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleCombinationConstraintImpl#getRole <em>Role</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleCombinationConstraintImpl extends TMCLConstructImpl implements RoleCombinationConstraint {
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
	 * The cached value of the '{@link #getOtherPlayer() <em>Other Player</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOtherPlayer()
	 * @generated
	 * @ordered
	 */
	protected TopicType otherPlayer;

	/**
	 * The cached value of the '{@link #getOtherRole() <em>Other Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOtherRole()
	 * @generated
	 * @ordered
	 */
	protected TopicType otherRole;

	/**
	 * The cached value of the '{@link #getRole() <em>Role</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRole()
	 * @generated
	 * @ordered
	 */
	protected TopicType role;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleCombinationConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ROLE_COMBINATION_CONSTRAINT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_COMBINATION_CONSTRAINT__PLAYER, oldPlayer, player));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_COMBINATION_CONSTRAINT__PLAYER, oldPlayer, player));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getOtherPlayer() {
		if (otherPlayer != null && otherPlayer.eIsProxy()) {
			InternalEObject oldOtherPlayer = (InternalEObject)otherPlayer;
			otherPlayer = (TopicType)eResolveProxy(oldOtherPlayer);
			if (otherPlayer != oldOtherPlayer) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER, oldOtherPlayer, otherPlayer));
			}
		}
		return otherPlayer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType basicGetOtherPlayer() {
		return otherPlayer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOtherPlayer(TopicType newOtherPlayer) {
		TopicType oldOtherPlayer = otherPlayer;
		otherPlayer = newOtherPlayer;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER, oldOtherPlayer, otherPlayer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getOtherRole() {
		if (otherRole != null && otherRole.eIsProxy()) {
			InternalEObject oldOtherRole = (InternalEObject)otherRole;
			otherRole = (TopicType)eResolveProxy(oldOtherRole);
			if (otherRole != oldOtherRole) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE, oldOtherRole, otherRole));
			}
		}
		return otherRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType basicGetOtherRole() {
		return otherRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOtherRole(TopicType newOtherRole) {
		TopicType oldOtherRole = otherRole;
		otherRole = newOtherRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE, oldOtherRole, otherRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getRole() {
		if (role != null && role.eIsProxy()) {
			InternalEObject oldRole = (InternalEObject)role;
			role = (TopicType)eResolveProxy(oldRole);
			if (role != oldRole) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_COMBINATION_CONSTRAINT__ROLE, oldRole, role));
			}
		}
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType basicGetRole() {
		return role;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRole(TopicType newRole) {
		TopicType oldRole = role;
		role = newRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_COMBINATION_CONSTRAINT__ROLE, oldRole, role));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__PLAYER:
				if (resolve) return getPlayer();
				return basicGetPlayer();
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER:
				if (resolve) return getOtherPlayer();
				return basicGetOtherPlayer();
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE:
				if (resolve) return getOtherRole();
				return basicGetOtherRole();
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__ROLE:
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
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__PLAYER:
				setPlayer((TopicType)newValue);
				return;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER:
				setOtherPlayer((TopicType)newValue);
				return;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE:
				setOtherRole((TopicType)newValue);
				return;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__ROLE:
				setRole((TopicType)newValue);
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
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__PLAYER:
				setPlayer((TopicType)null);
				return;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER:
				setOtherPlayer((TopicType)null);
				return;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE:
				setOtherRole((TopicType)null);
				return;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__ROLE:
				setRole((TopicType)null);
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
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__PLAYER:
				return player != null;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_PLAYER:
				return otherPlayer != null;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__OTHER_ROLE:
				return otherRole != null;
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT__ROLE:
				return role != null;
		}
		return super.eIsSet(featureID);
	}

} //RoleCombinationConstraintImpl
