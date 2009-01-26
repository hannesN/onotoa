/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
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

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.CardinalityContraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.ScopeConstraint;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getCardMax <em>Card Max</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getRegexp <em>Regexp</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getAssociationType <em>Association Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getRoleTypeConstraints <em>Role Type Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationTypeConstraintImpl extends EObjectImpl implements AssociationTypeConstraint {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

	/**
	 * The cached value of the '{@link #getScope() <em>Scope</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScope()
	 * @generated
	 * @ordered
	 */
	protected EList<ScopeConstraint> scope;

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
	 * The default value of the '{@link #getRegexp() <em>Regexp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegexp()
	 * @generated
	 * @ordered
	 */
	protected static final String REGEXP_EDEFAULT = ".*";

	/**
	 * The cached value of the '{@link #getRegexp() <em>Regexp</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRegexp()
	 * @generated
	 * @ordered
	 */
	protected String regexp = REGEXP_EDEFAULT;

	/**
	 * The cached value of the '{@link #getAssociationType() <em>Association Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationType()
	 * @generated
	 * @ordered
	 */
	protected TopicType associationType;

	/**
	 * The cached value of the '{@link #getRoleTypeConstraints() <em>Role Type Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoleTypeConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<RoleTypeConstraints> roleTypeConstraints;

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
	public EList<ScopeConstraint> getScope() {
		if (scope == null) {
			scope = new EObjectContainmentEList<ScopeConstraint>(ScopeConstraint.class, this, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE);
		}
		return scope;
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
	public String getRegexp() {
		return regexp;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setRegexp(String newRegexp) {
		String oldRegexp = regexp;
		regexp = newRegexp;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__REGEXP, oldRegexp, regexp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getAssociationType() {
		if (associationType != null && associationType.eIsProxy()) {
			InternalEObject oldAssociationType = (InternalEObject)associationType;
			associationType = (TopicType)eResolveProxy(oldAssociationType);
			if (associationType != oldAssociationType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE, oldAssociationType, associationType));
			}
		}
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType basicGetAssociationType() {
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationType(TopicType newAssociationType) {
		TopicType oldAssociationType = associationType;
		associationType = newAssociationType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE, oldAssociationType, associationType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RoleTypeConstraints> getRoleTypeConstraints() {
		if (roleTypeConstraints == null) {
			roleTypeConstraints = new EObjectContainmentEList<RoleTypeConstraints>(RoleTypeConstraints.class, this, ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ROLE_TYPE_CONSTRAINTS);
		}
		return roleTypeConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				return ((InternalEList<?>)getScope()).basicRemove(otherEnd, msgs);
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ROLE_TYPE_CONSTRAINTS:
				return ((InternalEList<?>)getRoleTypeConstraints()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__NAME:
				return getName();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				return getScope();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				return getCardMin();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				return getCardMax();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__REGEXP:
				return getRegexp();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				if (resolve) return getAssociationType();
				return basicGetAssociationType();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ROLE_TYPE_CONSTRAINTS:
				return getRoleTypeConstraints();
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				getScope().clear();
				getScope().addAll((Collection<? extends ScopeConstraint>)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				setCardMin((String)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				setCardMax((String)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__REGEXP:
				setRegexp((String)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				setAssociationType((TopicType)newValue);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ROLE_TYPE_CONSTRAINTS:
				getRoleTypeConstraints().clear();
				getRoleTypeConstraints().addAll((Collection<? extends RoleTypeConstraints>)newValue);
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				getScope().clear();
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				setCardMin(CARD_MIN_EDEFAULT);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				setCardMax(CARD_MAX_EDEFAULT);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__REGEXP:
				setRegexp(REGEXP_EDEFAULT);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				setAssociationType((TopicType)null);
				return;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ROLE_TYPE_CONSTRAINTS:
				getRoleTypeConstraints().clear();
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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__SCOPE:
				return scope != null && !scope.isEmpty();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN:
				return CARD_MIN_EDEFAULT == null ? cardMin != null : !CARD_MIN_EDEFAULT.equals(cardMin);
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX:
				return CARD_MAX_EDEFAULT == null ? cardMax != null : !CARD_MAX_EDEFAULT.equals(cardMax);
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__REGEXP:
				return REGEXP_EDEFAULT == null ? regexp != null : !REGEXP_EDEFAULT.equals(regexp);
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				return associationType != null;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ROLE_TYPE_CONSTRAINTS:
				return roleTypeConstraints != null && !roleTypeConstraints.isEmpty();
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
		if (baseClass == CardinalityContraint.class) {
			switch (derivedFeatureID) {
				case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN: return ModelPackage.CARDINALITY_CONTRAINT__CARD_MIN;
				case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX: return ModelPackage.CARDINALITY_CONTRAINT__CARD_MAX;
				default: return -1;
			}
		}
		if (baseClass == AbstractConstraint.class) {
			switch (derivedFeatureID) {
				case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__REGEXP: return ModelPackage.ABSTRACT_CONSTRAINT__REGEXP;
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
		if (baseClass == CardinalityContraint.class) {
			switch (baseFeatureID) {
				case ModelPackage.CARDINALITY_CONTRAINT__CARD_MIN: return ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MIN;
				case ModelPackage.CARDINALITY_CONTRAINT__CARD_MAX: return ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__CARD_MAX;
				default: return -1;
			}
		}
		if (baseClass == AbstractConstraint.class) {
			switch (baseFeatureID) {
				case ModelPackage.ABSTRACT_CONSTRAINT__REGEXP: return ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__REGEXP;
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
		result.append(" (name: ");
		result.append(name);
		result.append(", cardMin: ");
		result.append(cardMin);
		result.append(", cardMax: ");
		result.append(cardMax);
		result.append(", regexp: ");
		result.append(regexp);
		result.append(')');
		return result.toString();
	}

} //AssociationTypeConstraintImpl
