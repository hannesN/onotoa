/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
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
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getBaseLocator <em>Base Locator</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getName <em>Name</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getVersion <em>Version</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.impl.TopicMapSchemaImpl#getSchemaResource <em>Schema Resource</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TopicMapSchemaImpl extends TMCLConstructImpl implements TopicMapSchema {
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
	 * The default value of the '{@link #getBaseLocator() <em>Base Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseLocator()
	 * @generated
	 * @ordered
	 */
	protected static final String BASE_LOCATOR_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getBaseLocator() <em>Base Locator</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getBaseLocator()
	 * @generated
	 * @ordered
	 */
	protected String baseLocator = BASE_LOCATOR_EDEFAULT;

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
	 * The default value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected static final String VERSION_EDEFAULT = "";

	/**
	 * The cached value of the '{@link #getVersion() <em>Version</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getVersion()
	 * @generated
	 * @ordered
	 */
	protected String version = VERSION_EDEFAULT;

	/**
	 * The default value of the '{@link #getSchemaResource() <em>Schema Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchemaResource()
	 * @generated
	 * @ordered
	 */
	protected static final String SCHEMA_RESOURCE_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getSchemaResource() <em>Schema Resource</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSchemaResource()
	 * @generated
	 * @ordered
	 */
	protected String schemaResource = SCHEMA_RESOURCE_EDEFAULT;

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
	public String getBaseLocator() {
		return baseLocator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setBaseLocator(String newBaseLocator) {
		String oldBaseLocator = baseLocator;
		baseLocator = newBaseLocator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__BASE_LOCATOR, oldBaseLocator, baseLocator));
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
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getVersion() {
		return version;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setVersion(String newVersion) {
		String oldVersion = version;
		version = newVersion;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__VERSION, oldVersion, version));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getSchemaResource() {
		return schemaResource;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setSchemaResource(String newSchemaResource) {
		String oldSchemaResource = schemaResource;
		schemaResource = newSchemaResource;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, ModelPackage.TOPIC_MAP_SCHEMA__SCHEMA_RESOURCE, oldSchemaResource, schemaResource));
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
			case ModelPackage.TOPIC_MAP_SCHEMA__BASE_LOCATOR:
				return getBaseLocator();
			case ModelPackage.TOPIC_MAP_SCHEMA__NAME:
				return getName();
			case ModelPackage.TOPIC_MAP_SCHEMA__VERSION:
				return getVersion();
			case ModelPackage.TOPIC_MAP_SCHEMA__SCHEMA_RESOURCE:
				return getSchemaResource();
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
			case ModelPackage.TOPIC_MAP_SCHEMA__BASE_LOCATOR:
				setBaseLocator((String)newValue);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__NAME:
				setName((String)newValue);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__VERSION:
				setVersion((String)newValue);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__SCHEMA_RESOURCE:
				setSchemaResource((String)newValue);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__BASE_LOCATOR:
				setBaseLocator(BASE_LOCATOR_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__NAME:
				setName(NAME_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__VERSION:
				setVersion(VERSION_EDEFAULT);
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__SCHEMA_RESOURCE:
				setSchemaResource(SCHEMA_RESOURCE_EDEFAULT);
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
			case ModelPackage.TOPIC_MAP_SCHEMA__BASE_LOCATOR:
				return BASE_LOCATOR_EDEFAULT == null ? baseLocator != null : !BASE_LOCATOR_EDEFAULT.equals(baseLocator);
			case ModelPackage.TOPIC_MAP_SCHEMA__NAME:
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case ModelPackage.TOPIC_MAP_SCHEMA__VERSION:
				return VERSION_EDEFAULT == null ? version != null : !VERSION_EDEFAULT.equals(version);
			case ModelPackage.TOPIC_MAP_SCHEMA__SCHEMA_RESOURCE:
				return SCHEMA_RESOURCE_EDEFAULT == null ? schemaResource != null : !SCHEMA_RESOURCE_EDEFAULT.equals(schemaResource);
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
		result.append(", baseLocator: ");
		result.append(baseLocator);
		result.append(", name: ");
		result.append(name);
		result.append(", version: ");
		result.append(version);
		result.append(", schemaResource: ");
		result.append(schemaResource);
		result.append(')');
		return result.toString();
	}

} //TopicMapSchemaImpl
