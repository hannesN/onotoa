/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationType;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Other Role Player Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OtherRolePlayerConstraintImpl#getPlayer <em>Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OtherRolePlayerConstraintImpl#getOtherPlayer <em>Other Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OtherRolePlayerConstraintImpl#getOtherRole <em>Other Role</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.OtherRolePlayerConstraintImpl#getAssociationType <em>Association Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class OtherRolePlayerConstraintImpl extends AbstractCardinalityContraintImpl implements OtherRolePlayerConstraint {
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
	protected RoleType otherRole;

	/**
	 * The cached value of the '{@link #getAssociationType() <em>Association Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationType()
	 * @generated
	 * @ordered
	 */
	protected AssociationType associationType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected OtherRolePlayerConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.OTHER_ROLE_PLAYER_CONSTRAINT;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER, oldPlayer, player));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER, oldPlayer, player));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER, oldOtherPlayer, otherPlayer));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER, oldOtherPlayer, otherPlayer));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleType getOtherRole() {
		if (otherRole != null && otherRole.eIsProxy()) {
			InternalEObject oldOtherRole = (InternalEObject)otherRole;
			otherRole = (RoleType)eResolveProxy(oldOtherRole);
			if (otherRole != oldOtherRole) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE, oldOtherRole, otherRole));
			}
		}
		return otherRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleType basicGetOtherRole() {
		return otherRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setOtherRole(RoleType newOtherRole) {
		RoleType oldOtherRole = otherRole;
		otherRole = newOtherRole;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE, oldOtherRole, otherRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationType getAssociationType() {
		if (associationType != null && associationType.eIsProxy()) {
			InternalEObject oldAssociationType = (InternalEObject)associationType;
			associationType = (AssociationType)eResolveProxy(oldAssociationType);
			if (associationType != oldAssociationType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__ASSOCIATION_TYPE, oldAssociationType, associationType));
			}
		}
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationType basicGetAssociationType() {
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationType(AssociationType newAssociationType) {
		AssociationType oldAssociationType = associationType;
		associationType = newAssociationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__ASSOCIATION_TYPE, oldAssociationType, associationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER:
				if (resolve) return getPlayer();
				return basicGetPlayer();
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER:
				if (resolve) return getOtherPlayer();
				return basicGetOtherPlayer();
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE:
				if (resolve) return getOtherRole();
				return basicGetOtherRole();
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__ASSOCIATION_TYPE:
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
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER:
				setPlayer((TopicType)newValue);
				return;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER:
				setOtherPlayer((TopicType)newValue);
				return;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE:
				setOtherRole((RoleType)newValue);
				return;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__ASSOCIATION_TYPE:
				setAssociationType((AssociationType)newValue);
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
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER:
				setPlayer((TopicType)null);
				return;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER:
				setOtherPlayer((TopicType)null);
				return;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE:
				setOtherRole((RoleType)null);
				return;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__ASSOCIATION_TYPE:
				setAssociationType((AssociationType)null);
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
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER:
				return player != null;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER:
				return otherPlayer != null;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE:
				return otherRole != null;
			case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__ASSOCIATION_TYPE:
				return associationType != null;
		}
		return super.eIsSet(featureID);
	}

} //OtherRolePlayerConstraintImpl
