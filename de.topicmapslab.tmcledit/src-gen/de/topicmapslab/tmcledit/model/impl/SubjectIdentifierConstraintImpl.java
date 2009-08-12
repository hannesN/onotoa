/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AbstractCardinalityContraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Subject Identifier Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.SubjectIdentifierConstraintImpl#getCardMin <em>Card Min</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.SubjectIdentifierConstraintImpl#getCardMax <em>Card Max</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class SubjectIdentifierConstraintImpl extends AbstractRegExpConstraintImpl implements SubjectIdentifierConstraint {
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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected SubjectIdentifierConstraintImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.SUBJECT_IDENTIFIER_CONSTRAINT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN, oldCardMin, cardMin));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX, oldCardMax, cardMax));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN:
				return getCardMin();
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX:
				return getCardMax();
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
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN:
				setCardMin((String)newValue);
				return;
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX:
				setCardMax((String)newValue);
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
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN:
				setCardMin(CARD_MIN_EDEFAULT);
				return;
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX:
				setCardMax(CARD_MAX_EDEFAULT);
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
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN:
				return CARD_MIN_EDEFAULT == null ? cardMin != null : !CARD_MIN_EDEFAULT.equals(cardMin);
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX:
				return CARD_MAX_EDEFAULT == null ? cardMax != null : !CARD_MAX_EDEFAULT.equals(cardMax);
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
				case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN: return ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN;
				case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX: return ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX;
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
				case ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MIN: return ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MIN;
				case ModelPackage.ABSTRACT_CARDINALITY_CONTRAINT__CARD_MAX: return ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT__CARD_MAX;
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

	@Override
    public int hashCode() {
	    final int prime = 31;
	    int result = super.hashCode();
	    result = prime * result + ((cardMax == null) ? 0 : cardMax.hashCode());
	    result = prime * result + ((cardMin == null) ? 0 : cardMin.hashCode());
	    return result;
    }

	@Override
    public boolean equals(Object obj) {
	    if (this == obj)
		    return true;
	    if (!super.equals(obj))
		    return false;
	    if (getClass() != obj.getClass())
		    return false;
	    SubjectIdentifierConstraintImpl other = (SubjectIdentifierConstraintImpl) obj;
	    if (cardMax == null) {
		    if (other.cardMax != null)
			    return false;
	    } else if (!cardMax.equals(other.cardMax))
		    return false;
	    if (cardMin == null) {
		    if (other.cardMin != null)
			    return false;
	    } else if (!cardMin.equals(other.cardMin))
		    return false;
	    return true;
    }

	
} //SubjectIdentifierConstraintImpl
