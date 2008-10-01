/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model.impl;

import de.topicmapslab.tmcledit.diagram.model.ModelPackage;
import de.topicmapslab.tmcledit.diagram.model.TypeNode;

import de.topicmapslab.tmcledit.model.TopicType;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Node</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.impl.TypeNodeImpl#getTopicType <em>Topic Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TypeNodeImpl extends NodeImpl implements TypeNode {
	/**
	 * The default value of the '{@link #getTopicType() <em>Topic Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopicType()
	 * @generated
	 * @ordered
	 */
	protected static final TopicType TOPIC_TYPE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getTopicType() <em>Topic Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopicType()
	 * @generated
	 * @ordered
	 */
	protected TopicType topicType = TOPIC_TYPE_EDEFAULT;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TypeNodeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return ModelPackage.Literals.TYPE_NODE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType getTopicType() {
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TYPE_NODE__TOPIC_TYPE, oldTopicType, topicType));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case ModelPackage.TYPE_NODE__TOPIC_TYPE:
				return getTopicType();
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
			case ModelPackage.TYPE_NODE__TOPIC_TYPE:
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
			case ModelPackage.TYPE_NODE__TOPIC_TYPE:
				setTopicType(TOPIC_TYPE_EDEFAULT);
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
			case ModelPackage.TYPE_NODE__TOPIC_TYPE:
				return TOPIC_TYPE_EDEFAULT == null ? topicType != null : !TOPIC_TYPE_EDEFAULT.equals(topicType);
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
		result.append(" (topicType: ");
		result.append(topicType);
		result.append(')');
		return result.toString();
	}

} //TypeNodeImpl
