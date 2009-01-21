/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.TopicType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Association Type Constraint</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getAssociationType <em>Association Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.AssociationTypeConstraintImpl#getRoleTypeConstraints <em>Role Type Constraints</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class AssociationTypeConstraintImpl extends ScopedConstraintImpl implements AssociationTypeConstraint {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

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
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ASSOCIATION_TYPE:
				return associationType != null;
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT__ROLE_TYPE_CONSTRAINTS:
				return roleTypeConstraints != null && !roleTypeConstraints.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //AssociationTypeConstraintImpl
