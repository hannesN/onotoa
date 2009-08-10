/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint;
import de.topicmapslab.tmcledit.model.AbstractTypedConstraint;
import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RolePlayerConstraint;
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
 * An implementation of the model object '<em><b>Association Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getCardMax <em>Card Max</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getPlayerConstraints <em>Player Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationTypeConstraintImpl extends AbstractRegExpConstraintImpl implements AssociationTypeConstraint {
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
	protected static final String CARD_MAX_EDEFAULT = "*";

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
	 * The cached value of the '{@link #getType() <em>Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getType()
	 * @generated
	 * @ordered
	 */
	protected TopicType type;

	/**
	 * The cached value of the '{@link #getPlayerConstraints() <em>Player Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getPlayerConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<RolePlayerConstraint> playerConstraints;

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
		return ModelPackage.Literals.ASSOCIATION_TYPE_CONSTRAINT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN, oldCardMin, cardMin));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX, oldCardMax, cardMax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getType() {
		if (type != null && type.eIsProxy()) {
			InternalEObject oldType = (InternalEObject)type;
			type = (TopicType)eResolveProxy(oldType);
			if (type != oldType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE, oldType, type));
			}
		}
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType basicGetType() {
		return type;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setType(TopicType newType) {
		TopicType oldType = type;
		type = newType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RolePlayerConstraint> getPlayerConstraints() {
		if (playerConstraints == null) {
			playerConstraints = new EObjectContainmentEList<RolePlayerConstraint>(RolePlayerConstraint.class, this, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS);
		}
		return playerConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				return ((InternalEList<?>)getPlayerConstraints()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				return getCardMin();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				return getCardMax();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				return getPlayerConstraints();
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				setCardMin((String)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				setCardMax((String)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE:
				setType((TopicType)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				getPlayerConstraints().clear();
				getPlayerConstraints().addAll((Collection<? extends RolePlayerConstraint>)newValue);
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				setCardMin(CARD_MIN_EDEFAULT);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				setCardMax(CARD_MAX_EDEFAULT);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE:
				setType((TopicType)null);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				getPlayerConstraints().clear();
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				return CARD_MIN_EDEFAULT == null ? cardMin != null : !CARD_MIN_EDEFAULT.equals(cardMin);
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				return CARD_MAX_EDEFAULT == null ? cardMax != null : !CARD_MAX_EDEFAULT.equals(cardMax);
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE:
				return type != null;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__PLAYER_CONSTRAINTS:
				return playerConstraints != null && !playerConstraints.isEmpty();
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
				case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN: return ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN;
				case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX: return ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX;
				default: return -1;
			}
		}
		if (baseClass == AbstractTypedConstraint.class) {
			switch (derivedFeatureID) {
				case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE: return ModelPackage.ABSTRACT_TYPED_CONSTRAINT__TYPE;
				default: return -1;
			}
		}
		if (baseClass == AbstractTypedCardinalityConstraint.class) {
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
		if (baseClass == AbstractCardinalityContraint.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN: return ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN;
				case ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX: return ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX;
				default: return -1;
			}
		}
		if (baseClass == AbstractTypedConstraint.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_TYPED_CONSTRAINT__TYPE: return ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__TYPE;
				default: return -1;
			}
		}
		if (baseClass == AbstractTypedCardinalityConstraint.class) {
			switch (baseFeatureID) {
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

} //AssociationTypeConstraintImpl
