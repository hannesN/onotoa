/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

import de.topicmapslab.tmcledit.model.AbstractConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Abstract Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AbstractConstraintImpl#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AbstractConstraintImpl#getCardMax <em>Card Max</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AbstractConstraintImpl#getRegexp <em>Regexp</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AbstractConstraintImpl#getScope <em>Scope</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AbstractConstraintImpl#getName <em>Name</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class AbstractConstraintImpl extends EObjectImpl implements AbstractConstraint {
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
	 * The cached value of the '{@link #getScope() <em>Scope</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getScope()
	 * @generated
	 * @ordered
	 */
	protected EList<TopicType> scope;

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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected AbstractConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ABSTRACT_CONSTRAINT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ABSTRACT_CONSTRAINT__CARD_MIN, oldCardMin, cardMin));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ABSTRACT_CONSTRAINT__CARD_MAX, oldCardMax, cardMax));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ABSTRACT_CONSTRAINT__REGEXP, oldRegexp, regexp));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopicType> getScope() {
		if (scope == null) {
			scope = new EObjectResolvingEList<TopicType>(TopicType.class, this, ModelPackage.ABSTRACT_CONSTRAINT__SCOPE);
		}
		return scope;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ABSTRACT_CONSTRAINT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MIN:
				return getCardMin();
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MAX:
				return getCardMax();
			case ModelPackage.ABSTRACT_CONSTRAINT__REGEXP:
				return getRegexp();
			case ModelPackage.ABSTRACT_CONSTRAINT__SCOPE:
				return getScope();
			case ModelPackage.ABSTRACT_CONSTRAINT__NAME:
				return getName();
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
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MIN:
				setCardMin((String)newValue);
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MAX:
				setCardMax((String)newValue);
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__REGEXP:
				setRegexp((String)newValue);
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__SCOPE:
				getScope().clear();
				getScope().addAll((Collection<? extends TopicType>)newValue);
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__NAME:
				setName((String)newValue);
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
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MIN:
				setCardMin(CARD_MIN_EDEFAULT);
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MAX:
				setCardMax(CARD_MAX_EDEFAULT);
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__REGEXP:
				setRegexp(REGEXP_EDEFAULT);
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__SCOPE:
				getScope().clear();
				return;
			case ModelPackage.ABSTRACT_CONSTRAINT__NAME:
				setName(NAME_EDEFAULT);
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
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MIN:
				return CARD_MIN_EDEFAULT == null ? cardMin != null : !CARD_MIN_EDEFAULT.equals(cardMin);
			case ModelPackage.ABSTRACT_CONSTRAINT__CARD_MAX:
				return CARD_MAX_EDEFAULT == null ? cardMax != null : !CARD_MAX_EDEFAULT.equals(cardMax);
			case ModelPackage.ABSTRACT_CONSTRAINT__REGEXP:
				return REGEXP_EDEFAULT == null ? regexp != null : !REGEXP_EDEFAULT.equals(regexp);
			case ModelPackage.ABSTRACT_CONSTRAINT__SCOPE:
				return scope != null && !scope.isEmpty();
			case ModelPackage.ABSTRACT_CONSTRAINT__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
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
		result.append(" (cardMin: ");
		result.append(cardMin);
		result.append(", cardMax: ");
		result.append(cardMax);
		result.append(", regexp: ");
		result.append(regexp);
		result.append(", name: ");
		result.append(name);
		result.append(')');
		return result.toString();
	}

} //AbstractConstraintImpl
