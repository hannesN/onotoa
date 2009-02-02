/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.KindOfTopicType;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.OtherRolePlayerConstraint;
import de.topicmapslab.tmcledit.model.RoleType;
import de.topicmapslab.tmcledit.model.TopicType;

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
 * An implementation of the model object '<em><b>Role Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl#getCardMax <em>Card Max</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl#getPlayer <em>Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl#getOtherPlayer <em>Other Player</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl#getOtherRole <em>Other Role</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeImpl#getOtherRoles <em>Other Roles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleTypeImpl extends TopicTypeImpl implements RoleType {
	/**
	 * The default value of the '{@link #getCardMin() <em>Card Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardMin()
	 * @generated
	 * @ordered
	 */
	protected static final String CARD_MIN_EDEFAULT = "0";

	/**
	 * The cached value of the '{@link #getCardMin() <em>Card Min</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardMin()
	 * @generated
	 * @ordered
	 */
	protected String cardMin = CARD_MIN_EDEFAULT;

	/**
	 * The default value of the '{@link #getCardMax() <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardMax()
	 * @generated
	 * @ordered
	 */
	protected static final String CARD_MAX_EDEFAULT = "1";

	/**
	 * The cached value of the '{@link #getCardMax() <em>Card Max</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getCardMax()
	 * @generated
	 * @ordered
	 */
	protected String cardMax = CARD_MAX_EDEFAULT;

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
	 * The cached value of the '{@link #getOtherRoles() <em>Other Roles</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getOtherRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<OtherRolePlayerConstraint> otherRoles;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated NOT
	 */
	protected RoleTypeImpl() {
		super();
		setKind(KindOfTopicType.ROLE_TYPE);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ROLE_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCardMin() {
		return cardMin;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCardMin(String newCardMin) {
		String oldCardMin = cardMin;
		cardMin = newCardMin;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TYPE__CARD_MIN, oldCardMin, cardMin));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getCardMax() {
		return cardMax;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setCardMax(String newCardMax) {
		String oldCardMax = cardMax;
		cardMax = newCardMax;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TYPE__CARD_MAX, oldCardMax, cardMax));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_TYPE__PLAYER, oldPlayer, player));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TYPE__PLAYER, oldPlayer, player));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_TYPE__OTHER_PLAYER, oldOtherPlayer, otherPlayer));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TYPE__OTHER_PLAYER, oldOtherPlayer, otherPlayer));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_TYPE__OTHER_ROLE, oldOtherRole, otherRole));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TYPE__OTHER_ROLE, oldOtherRole, otherRole));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<OtherRolePlayerConstraint> getOtherRoles() {
		if (otherRoles == null) {
			otherRoles = new EObjectContainmentEList<OtherRolePlayerConstraint>(OtherRolePlayerConstraint.class, this, ModelPackage.ROLE_TYPE__OTHER_ROLES);
		}
		return otherRoles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.ROLE_TYPE__OTHER_ROLES:
				return ((InternalEList<?>)getOtherRoles()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.ROLE_TYPE__CARD_MIN:
				return getCardMin();
			case ModelPackage.ROLE_TYPE__CARD_MAX:
				return getCardMax();
			case ModelPackage.ROLE_TYPE__PLAYER:
				if (resolve) return getPlayer();
				return basicGetPlayer();
			case ModelPackage.ROLE_TYPE__OTHER_PLAYER:
				if (resolve) return getOtherPlayer();
				return basicGetOtherPlayer();
			case ModelPackage.ROLE_TYPE__OTHER_ROLE:
				if (resolve) return getOtherRole();
				return basicGetOtherRole();
			case ModelPackage.ROLE_TYPE__OTHER_ROLES:
				return getOtherRoles();
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
			case ModelPackage.ROLE_TYPE__CARD_MIN:
				setCardMin((String)newValue);
				return;
			case ModelPackage.ROLE_TYPE__CARD_MAX:
				setCardMax((String)newValue);
				return;
			case ModelPackage.ROLE_TYPE__PLAYER:
				setPlayer((TopicType)newValue);
				return;
			case ModelPackage.ROLE_TYPE__OTHER_PLAYER:
				setOtherPlayer((TopicType)newValue);
				return;
			case ModelPackage.ROLE_TYPE__OTHER_ROLE:
				setOtherRole((RoleType)newValue);
				return;
			case ModelPackage.ROLE_TYPE__OTHER_ROLES:
				getOtherRoles().clear();
				getOtherRoles().addAll((Collection<? extends OtherRolePlayerConstraint>)newValue);
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
			case ModelPackage.ROLE_TYPE__CARD_MIN:
				setCardMin(CARD_MIN_EDEFAULT);
				return;
			case ModelPackage.ROLE_TYPE__CARD_MAX:
				setCardMax(CARD_MAX_EDEFAULT);
				return;
			case ModelPackage.ROLE_TYPE__PLAYER:
				setPlayer((TopicType)null);
				return;
			case ModelPackage.ROLE_TYPE__OTHER_PLAYER:
				setOtherPlayer((TopicType)null);
				return;
			case ModelPackage.ROLE_TYPE__OTHER_ROLE:
				setOtherRole((RoleType)null);
				return;
			case ModelPackage.ROLE_TYPE__OTHER_ROLES:
				getOtherRoles().clear();
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
			case ModelPackage.ROLE_TYPE__CARD_MIN:
				return CARD_MIN_EDEFAULT == null ? cardMin != null : !CARD_MIN_EDEFAULT.equals(cardMin);
			case ModelPackage.ROLE_TYPE__CARD_MAX:
				return CARD_MAX_EDEFAULT == null ? cardMax != null : !CARD_MAX_EDEFAULT.equals(cardMax);
			case ModelPackage.ROLE_TYPE__PLAYER:
				return player != null;
			case ModelPackage.ROLE_TYPE__OTHER_PLAYER:
				return otherPlayer != null;
			case ModelPackage.ROLE_TYPE__OTHER_ROLE:
				return otherRole != null;
			case ModelPackage.ROLE_TYPE__OTHER_ROLES:
				return otherRoles != null && !otherRoles.isEmpty();
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
		if (baseClass == AbstractCardinalityContraint.class) {
			switch (derivedFeatureID) {
				case ModelPackage.ROLE_TYPE__CARD_MIN: return ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN;
				case ModelPackage.ROLE_TYPE__CARD_MAX: return ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX;
				default: return -1;
			}
		}
		if (baseClass == OtherRolePlayerConstraint.class) {
			switch (derivedFeatureID) {
				case ModelPackage.ROLE_TYPE__PLAYER: return ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER;
				case ModelPackage.ROLE_TYPE__OTHER_PLAYER: return ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER;
				case ModelPackage.ROLE_TYPE__OTHER_ROLE: return ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE;
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
		if (baseClass == AbstractCardinalityContraint.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN: return ModelPackage.ROLE_TYPE__CARD_MIN;
				case ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX: return ModelPackage.ROLE_TYPE__CARD_MAX;
				default: return -1;
			}
		}
		if (baseClass == OtherRolePlayerConstraint.class) {
			switch (baseFeatureID) {
				case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__PLAYER: return ModelPackage.ROLE_TYPE__PLAYER;
				case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_PLAYER: return ModelPackage.ROLE_TYPE__OTHER_PLAYER;
				case ModelPackage.OTHER_ROLE_PLAYER_CONSTRAINT__OTHER_ROLE: return ModelPackage.ROLE_TYPE__OTHER_ROLE;
				default: return -1;
			}
		}
		return super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);
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
		result.append(" (cardMin: ");
		result.append(cardMin);
		result.append(", cardMax: ");
		result.append(cardMax);
		result.append(')');
		return result.toString();
	}

} //RoleTypeImpl
