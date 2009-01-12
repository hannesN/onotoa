/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.TopicType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Role Type Constraints</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeConstraintsImpl#getType <em>Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.RoleTypeConstraintsImpl#getTopicType <em>Topic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RoleTypeConstraintsImpl extends CardinalityContraintImpl implements RoleTypeConstraints {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

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
	 * The cached value of the '{@link #getTopicType() <em>Topic Type</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopicType()
	 * @generated
	 * @ordered
	 */
	protected TopicType topicType;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RoleTypeConstraintsImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.ROLE_TYPE_CONSTRAINTS;
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_TYPE_CONSTRAINTS__TYPE, oldType, type));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TYPE_CONSTRAINTS__TYPE, oldType, type));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getTopicType() {
		if (topicType != null && topicType.eIsProxy()) {
			InternalEObject oldTopicType = (InternalEObject)topicType;
			topicType = (TopicType)eResolveProxy(oldTopicType);
			if (topicType != oldTopicType) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, ModelPackage.ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE, oldTopicType, topicType));
			}
		}
		return topicType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType basicGetTopicType() {
		return topicType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setTopicType(TopicType newTopicType) {
		TopicType oldTopicType = topicType;
		topicType = newTopicType;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE, oldTopicType, topicType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TYPE:
				if (resolve) return getType();
				return basicGetType();
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE:
				if (resolve) return getTopicType();
				return basicGetTopicType();
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
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TYPE:
				setType((TopicType)newValue);
				return;
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE:
				setTopicType((TopicType)newValue);
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
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TYPE:
				setType((TopicType)null);
				return;
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE:
				setTopicType((TopicType)null);
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
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TYPE:
				return type != null;
			case ModelPackage.ROLE_TYPE_CONSTRAINTS__TOPIC_TYPE:
				return topicType != null;
		}
		return super.eIsSet(featureID);
	}

} //RoleTypeConstraintsImpl
