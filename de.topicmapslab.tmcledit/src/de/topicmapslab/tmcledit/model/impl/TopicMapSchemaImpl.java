/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.AssociationTypeConstraint;
import de.topicmapslab.tmcledit.model.Diagram;
import de.topicmapslab.tmcledit.model.MappingElement;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.RoleTypeConstraints;
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
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getIncludes <em>Includes</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getDiagram <em>Diagram</em>}</li>
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
	 * The cached value of the '{@link #getAssociationTypeConstraints() <em>Association Type Constraints</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getAssociationTypeConstraints()
	 * @generated
	 * @ordered
	 */
	protected EList<AssociationTypeConstraint> associationTypeConstraints;

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
	 * The cached value of the '{@link #getIncludes() <em>Includes</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getIncludes()
	 * @generated
	 * @ordered
	 */
	protected EList<String> includes;

	/**
	 * The cached value of the '{@link #getDiagram() <em>Diagram</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDiagram()
	 * @generated
	 * @ordered
	 */
	protected Diagram diagram;

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
	public EList<RoleTypeConstraints> getRoleTypeConstraints() {
		if (roleTypeConstraints == null) {
			roleTypeConstraints = new EObjectContainmentEList<RoleTypeConstraints>(RoleTypeConstraints.class, this, ModelPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS);
		}
		return roleTypeConstraints;
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
			mappings = new EObjectResolvingEList<MappingElement>(MappingElement.class, this, ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS);
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
	public Diagram getDiagram() {
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetDiagram(Diagram newDiagram, NotificationChain msgs) {
		Diagram oldDiagram = diagram;
		diagram = newDiagram;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM, oldDiagram, newDiagram);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDiagram(Diagram newDiagram) {
		if (newDiagram != diagram) {
			NotificationChain msgs = null;
			if (diagram != null)
				msgs = ((InternalEObject)diagram).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM, null, msgs);
			if (newDiagram != null)
				msgs = ((InternalEObject)newDiagram).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM, null, msgs);
			msgs = basicSetDiagram(newDiagram, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM, newDiagram, newDiagram));
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
			case ModelPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				return ((InternalEList<?>)getRoleTypeConstraints()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return ((InternalEList<?>)getAssociationTypeConstraints()).basicRemove(otherEnd, msgs);
			case ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM:
				return basicSetDiagram(null, msgs);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				return getRoleTypeConstraints();
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return getAssociationTypeConstraints();
			case ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				return getMappings();
			case ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES:
				return getIncludes();
			case ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM:
				return getDiagram();
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
			case ModelPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				getRoleTypeConstraints().clear();
				getRoleTypeConstraints().addAll((Collection<? extends RoleTypeConstraints>)newValue);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM:
				setDiagram((Diagram)newValue);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				getRoleTypeConstraints().clear();
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
			case ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM:
				setDiagram((Diagram)null);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__ROLE_TYPE_CONSTRAINTS:
				return roleTypeConstraints != null && !roleTypeConstraints.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				return associationTypeConstraints != null && !associationTypeConstraints.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__MAPPINGS:
				return mappings != null && !mappings.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES:
				return includes != null && !includes.isEmpty();
			case ModelPackage.TOPIC_MAP_SCHEMA__DIAGRAM:
				return diagram != null;
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
		result.append(')');
		return result.toString();
	}

} //TopicMapSchemaImpl
