/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelPackage;
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

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Topic Map Schema</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getTopicTypes <em>Topic Types</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getAssociationTypeConstraints <em>Association Type Constraints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getMappings <em>Mappings</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getIncludes <em>Includes</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#isActiveTopicTypeConstraint <em>Active Topic Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#isActiveScopeTypeConstraint <em>Active Scope Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#isActiveRoleTypeConstraint <em>Active Role Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#isActiveNameTypeConstraint <em>Active Name Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#isActiveAssociationTypeConstraint <em>Active Association Type Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#isActiveOccurenceTypeConstraint <em>Active Occurence Type Constraint</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopicMapSchemaImpl extends EObjectImpl implements TopicMapSchema {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

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
	 * The cached value of the '{@link #getAssociationTypeConstraints() <em>Association Type Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationTypeConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<AssociationTypeConstraint> associationTypeConstraints;

	/**
	 * The cached value of the '{@link #getMappings() <em>Mappings</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMappings()
	 * @generated
	 * @ordered
	 */
	protected EList<MappingElement> mappings;

	/**
	 * The cached value of the '{@link #getIncludes() <em>Includes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> includes;

	/**
	 * The default value of the '{@link #isActiveTopicTypeConstraint() <em>Active Topic Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveTopicTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_TOPIC_TYPE_CONSTRAINT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActiveTopicTypeConstraint() <em>Active Topic Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveTopicTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected boolean activeTopicTypeConstraint = ACTIVE_TOPIC_TYPE_CONSTRAINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isActiveScopeTypeConstraint() <em>Active Scope Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveScopeTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_SCOPE_TYPE_CONSTRAINT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActiveScopeTypeConstraint() <em>Active Scope Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveScopeTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected boolean activeScopeTypeConstraint = ACTIVE_SCOPE_TYPE_CONSTRAINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isActiveRoleTypeConstraint() <em>Active Role Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveRoleTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_ROLE_TYPE_CONSTRAINT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActiveRoleTypeConstraint() <em>Active Role Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveRoleTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected boolean activeRoleTypeConstraint = ACTIVE_ROLE_TYPE_CONSTRAINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isActiveNameTypeConstraint() <em>Active Name Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveNameTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_NAME_TYPE_CONSTRAINT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActiveNameTypeConstraint() <em>Active Name Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveNameTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected boolean activeNameTypeConstraint = ACTIVE_NAME_TYPE_CONSTRAINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isActiveAssociationTypeConstraint() <em>Active Association Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveAssociationTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActiveAssociationTypeConstraint() <em>Active Association Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveAssociationTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected boolean activeAssociationTypeConstraint = ACTIVE_ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT;

	/**
	 * The default value of the '{@link #isActiveOccurenceTypeConstraint() <em>Active Occurence Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveOccurenceTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected static final boolean ACTIVE_OCCURENCE_TYPE_CONSTRAINT_EDEFAULT = true;

	/**
	 * The cached value of the '{@link #isActiveOccurenceTypeConstraint() <em>Active Occurence Type Constraint</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #isActiveOccurenceTypeConstraint()
	 * @generated
	 * @ordered
	 */
	protected boolean activeOccurenceTypeConstraint = ACTIVE_OCCURENCE_TYPE_CONSTRAINT_EDEFAULT;

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
		return ModelPackage.Literals.TOPIC_MAP_SCHEMA;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<TopicType> getTopicTypes() {
		if (topicTypes == null) {
			topicTypes = new EObjectContainmentEList<TopicType>(TopicType.class, this, ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES);
		}
		return topicTypes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<AssociationTypeConstraint> getAssociationTypeConstraints() {
		if (associationTypeConstraints == null) {
			associationTypeConstraints = new EObjectContainmentEList<AssociationTypeConstraint>(AssociationTypeConstraint.class, this, ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS);
		}
		return associationTypeConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<MappingElement> getMappings() {
		if (mappings == null) {
			mappings = new EObjectContainmentEList<MappingElement>(MappingElement.class, this, ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS);
		}
		return mappings;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getIncludes() {
		if (includes == null) {
			includes = new EDataTypeUniqueEList<String>(String.class, this, ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES);
		}
		return includes;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActiveTopicTypeConstraint() {
		return activeTopicTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveTopicTypeConstraint(boolean newActiveTopicTypeConstraint) {
		boolean oldActiveTopicTypeConstraint = activeTopicTypeConstraint;
		activeTopicTypeConstraint = newActiveTopicTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT, oldActiveTopicTypeConstraint, activeTopicTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActiveScopeTypeConstraint() {
		return activeScopeTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveScopeTypeConstraint(boolean newActiveScopeTypeConstraint) {
		boolean oldActiveScopeTypeConstraint = activeScopeTypeConstraint;
		activeScopeTypeConstraint = newActiveScopeTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT, oldActiveScopeTypeConstraint, activeScopeTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActiveRoleTypeConstraint() {
		return activeRoleTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveRoleTypeConstraint(boolean newActiveRoleTypeConstraint) {
		boolean oldActiveRoleTypeConstraint = activeRoleTypeConstraint;
		activeRoleTypeConstraint = newActiveRoleTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT, oldActiveRoleTypeConstraint, activeRoleTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActiveNameTypeConstraint() {
		return activeNameTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveNameTypeConstraint(boolean newActiveNameTypeConstraint) {
		boolean oldActiveNameTypeConstraint = activeNameTypeConstraint;
		activeNameTypeConstraint = newActiveNameTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT, oldActiveNameTypeConstraint, activeNameTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActiveAssociationTypeConstraint() {
		return activeAssociationTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveAssociationTypeConstraint(boolean newActiveAssociationTypeConstraint) {
		boolean oldActiveAssociationTypeConstraint = activeAssociationTypeConstraint;
		activeAssociationTypeConstraint = newActiveAssociationTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT, oldActiveAssociationTypeConstraint, activeAssociationTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public boolean isActiveOccurenceTypeConstraint() {
		return activeOccurenceTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActiveOccurenceTypeConstraint(boolean newActiveOccurenceTypeConstraint) {
		boolean oldActiveOccurenceTypeConstraint = activeOccurenceTypeConstraint;
		activeOccurenceTypeConstraint = newActiveOccurenceTypeConstraint;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT, oldActiveOccurenceTypeConstraint, activeOccurenceTypeConstraint));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				return ((InternalEList<?>)getTopicTypes()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return ((InternalEList<?>)getAssociationTypeConstraints()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				return ((InternalEList<?>)getMappings()).basicRemove(otherEnd, msgs);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				return getTopicTypes();
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return getAssociationTypeConstraints();
			case ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				return getMappings();
			case ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES:
				return getIncludes();
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT:
				return isActiveTopicTypeConstraint() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT:
				return isActiveScopeTypeConstraint() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT:
				return isActiveRoleTypeConstraint() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT:
				return isActiveNameTypeConstraint() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT:
				return isActiveAssociationTypeConstraint() ? Boolean.TRUE : Boolean.FALSE;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT:
				return isActiveOccurenceTypeConstraint() ? Boolean.TRUE : Boolean.FALSE;
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
			case ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				getTopicTypes().clear();
				getTopicTypes().addAll((Collection<? extends TopicType>)newValue);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				getAssociationTypeConstraints().clear();
				getAssociationTypeConstraints().addAll((Collection<? extends AssociationTypeConstraint>)newValue);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				getMappings().clear();
				getMappings().addAll((Collection<? extends MappingElement>)newValue);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES:
				getIncludes().clear();
				getIncludes().addAll((Collection<? extends String>)newValue);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT:
				setActiveTopicTypeConstraint(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT:
				setActiveScopeTypeConstraint(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT:
				setActiveRoleTypeConstraint(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT:
				setActiveNameTypeConstraint(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT:
				setActiveAssociationTypeConstraint(((Boolean)newValue).booleanValue());
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT:
				setActiveOccurenceTypeConstraint(((Boolean)newValue).booleanValue());
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
			case ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				getTopicTypes().clear();
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				getAssociationTypeConstraints().clear();
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				getMappings().clear();
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES:
				getIncludes().clear();
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT:
				setActiveTopicTypeConstraint(ACTIVE_TOPIC_TYPE_CONSTRAINT_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT:
				setActiveScopeTypeConstraint(ACTIVE_SCOPE_TYPE_CONSTRAINT_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT:
				setActiveRoleTypeConstraint(ACTIVE_ROLE_TYPE_CONSTRAINT_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT:
				setActiveNameTypeConstraint(ACTIVE_NAME_TYPE_CONSTRAINT_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT:
				setActiveAssociationTypeConstraint(ACTIVE_ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT:
				setActiveOccurenceTypeConstraint(ACTIVE_OCCURENCE_TYPE_CONSTRAINT_EDEFAULT);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
				return topicTypes != null && !topicTypes.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return associationTypeConstraints != null && !associationTypeConstraints.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				return mappings != null && !mappings.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES:
				return includes != null && !includes.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT:
				return activeTopicTypeConstraint != ACTIVE_TOPIC_TYPE_CONSTRAINT_EDEFAULT;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT:
				return activeScopeTypeConstraint != ACTIVE_SCOPE_TYPE_CONSTRAINT_EDEFAULT;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT:
				return activeRoleTypeConstraint != ACTIVE_ROLE_TYPE_CONSTRAINT_EDEFAULT;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT:
				return activeNameTypeConstraint != ACTIVE_NAME_TYPE_CONSTRAINT_EDEFAULT;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT:
				return activeAssociationTypeConstraint != ACTIVE_ASSOCIATION_TYPE_CONSTRAINT_EDEFAULT;
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT:
				return activeOccurenceTypeConstraint != ACTIVE_OCCURENCE_TYPE_CONSTRAINT_EDEFAULT;
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
		result.append(" (includes: ");
		result.append(includes);
		result.append(", activeTopicTypeConstraint: ");
		result.append(activeTopicTypeConstraint);
		result.append(", activeScopeTypeConstraint: ");
		result.append(activeScopeTypeConstraint);
		result.append(", activeRoleTypeConstraint: ");
		result.append(activeRoleTypeConstraint);
		result.append(", activeNameTypeConstraint: ");
		result.append(activeNameTypeConstraint);
		result.append(", activeAssociationTypeConstraint: ");
		result.append(activeAssociationTypeConstraint);
		result.append(", activeOccurenceTypeConstraint: ");
		result.append(activeOccurenceTypeConstraint);
		result.append(')');
		return result.toString();
	}

} //TopicMapSchemaImpl
