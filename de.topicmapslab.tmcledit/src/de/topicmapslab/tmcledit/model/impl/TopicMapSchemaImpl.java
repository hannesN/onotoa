/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
import de.topicmapslab.tmcledit.model.TMPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;
import de.topicmapslab.tmcledit.model.TopicType;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topic Map Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getTopicTypes <em>Topic Types</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getRoleTypeConstraints <em>Role Type Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getAssociationTypeConstraints <em>Association Type Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getMappings <em>Mappings</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopicMapSchemaImpl extends EObjectImpl implements TopicMapSchema {
	/**
	 * The cached value of the '{@link #getTopicTypes() <em>Topic Types</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getTopicTypes()
	 * @generated
	 * @ordered
	 */
	protected EList<TopicType> topicTypes;

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
	 * The cached value of the '{@link #getAssociationTypeConstraints() <em>Association Type Constraints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationTypeConstraints()
	 * @generated
	 * @ordered
	 */
	protected AssociationTypeConstraint associationTypeConstraints;

	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<MappingElement> mappings;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected TopicMapSchemaImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return TMPackage.Literals.TOPIC_MAP_SCHEMA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopicType> getTopicTypes() {
		if (topicTypes == null) {
			topicTypes = new EObjectContainmentEList<TopicType>(TopicType.class, this, TMPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES);
		}
		return topicTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RoleTypeConstraints> getRoleTypeConstraints() {
		if (roleTypeConstraints == null) {
			roleTypeConstraints = new EObjectContainmentEList<RoleTypeConstraints>(RoleTypeConstraints.class, this, TMPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS);
		}
		return roleTypeConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationTypeConstraint getAssociationTypeConstraints() {
		return associationTypeConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetAssociationTypeConstraints(AssociationTypeConstraint newAssociationTypeConstraints, NotificationChain msgs) {
		AssociationTypeConstraint oldAssociationTypeConstraints = associationTypeConstraints;
		associationTypeConstraints = newAssociationTypeConstraints;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS, oldAssociationTypeConstraints, newAssociationTypeConstraints);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setAssociationTypeConstraints(AssociationTypeConstraint newAssociationTypeConstraints) {
		if (newAssociationTypeConstraints != associationTypeConstraints) {
			NotificationChain msgs = null;
			if (associationTypeConstraints != null)
				msgs = ((InternalEObject)associationTypeConstraints).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS, null, msgs);
			if (newAssociationTypeConstraints != null)
				msgs = ((InternalEObject)newAssociationTypeConstraints).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS, null, msgs);
			msgs = basicSetAssociationTypeConstraints(newAssociationTypeConstraints, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS, newAssociationTypeConstraints, newAssociationTypeConstraints));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MappingElement> getMappings() {
		if (mappings == null) {
			mappings = new EObjectResolvingEList<MappingElement>(MappingElement.class, this, TMPackage.TOPIC_MAP_SCHEMA__MAPPINGS);
		}
		return mappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case TMPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				return ((InternalEList<?>)getTopicTypes()).basicRemove(otherEnd, msgs);
			case TMPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				return ((InternalEList<?>)getRoleTypeConstraints()).basicRemove(otherEnd, msgs);
			case TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return basicSetAssociationTypeConstraints(null, msgs);
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
			case TMPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				return getTopicTypes();
			case TMPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				return getRoleTypeConstraints();
			case TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return getAssociationTypeConstraints();
			case TMPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				return getMappings();
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
			case TMPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				getTopicTypes().clear();
				getTopicTypes().addAll((Collection<? extends TopicType>)newValue);
				return;
			case TMPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				getRoleTypeConstraints().clear();
				getRoleTypeConstraints().addAll((Collection<? extends RoleTypeConstraints>)newValue);
				return;
			case TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				setAssociationTypeConstraints((AssociationTypeConstraint)newValue);
				return;
			case TMPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				getMappings().clear();
				getMappings().addAll((Collection<? extends MappingElement>)newValue);
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
			case TMPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				getTopicTypes().clear();
				return;
			case TMPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				getRoleTypeConstraints().clear();
				return;
			case TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				setAssociationTypeConstraints((AssociationTypeConstraint)null);
				return;
			case TMPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				getMappings().clear();
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
			case TMPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				return topicTypes != null && !topicTypes.isEmpty();
			case TMPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				return roleTypeConstraints != null && !roleTypeConstraints.isEmpty();
			case TMPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return associationTypeConstraints != null;
			case TMPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				return mappings != null && !mappings.isEmpty();
		}
		return super.eIsSet(featureID);
	}

} //TopicMapSchemaImpl
