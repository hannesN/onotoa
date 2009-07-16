/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.ReifiableTopicType;
import de.topicmapslab.tmcledit.model.ReifierConstraint;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Reifiable Topic Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.ReifiableTopicTypeImpl#getReifierConstraint <em>Reifier Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ReifiableTopicTypeImpl extends TopicTypeImpl implements ReifiableTopicType {
	/**
	 * The cached value of the '{@link #getReifierConstraint() <em>Reifier Constraint</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getReifierConstraint()
	 * @generated
	 * @ordered
	 */
	protected ReifierConstraint reifierConstraint;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ReifiableTopicTypeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.REIFIABLE_TOPIC_TYPE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReifierConstraint getReifierConstraint() {
		return reifierConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetReifierConstraint(ReifierConstraint newReifierConstraint, NotificationChain msgs) {
		ReifierConstraint oldReifierConstraint = reifierConstraint;
		reifierConstraint = newReifierConstraint;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, oldReifierConstraint, newReifierConstraint);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setReifierConstraint(ReifierConstraint newReifierConstraint) {
		if (newReifierConstraint != reifierConstraint) {
			NotificationChain msgs = null;
			if (reifierConstraint != null)
				msgs = ((InternalEObject)reifierConstraint).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, null, msgs);
			if (newReifierConstraint != null)
				msgs = ((InternalEObject)newReifierConstraint).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, null, msgs);
			msgs = basicSetReifierConstraint(newReifierConstraint, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT, newReifierConstraint, newReifierConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				return basicSetReifierConstraint(null, msgs);
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
			case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				return getReifierConstraint();
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
			case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)newValue);
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
			case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				setReifierConstraint((ReifierConstraint)null);
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
			case ModelPackage.REIFIABLE_TOPIC_TYPE__REIFIER_CONSTRAINT:
				return reifierConstraint != null;
		}
		return super.eIsSet(featureID);
	}

} //ReifiableTopicTypeImpl
