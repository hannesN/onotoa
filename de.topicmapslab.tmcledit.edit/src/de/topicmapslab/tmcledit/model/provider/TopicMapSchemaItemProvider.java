/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.provider;


import de.topicmapslab.tmcledit.model.ModelFactory;
import de.topicmapslab.tmcledit.model.ModelPackage;
import de.topicmapslab.tmcledit.model.TopicMapSchema;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.ResourceLocator;

import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link de.topicmapslab.tmcledit.model.TopicMapSchema} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class TopicMapSchemaItemProvider
	extends ItemProviderAdapter
	implements
		IEditingDomainItemProvider,
		IStructuredItemContentProvider,
		ITreeItemContentProvider,
		IItemLabelProvider,
		IItemPropertySource {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

	/**
	 * This constructs an instance from a factory and a notifier.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicMapSchemaItemProvider(AdapterFactory adapterFactory) {
		super(adapterFactory);
	}

	/**
	 * This returns the property descriptors for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object) {
		if (itemPropertyDescriptors == null) {
			super.getPropertyDescriptors(object);

			addMappingsPropertyDescriptor(object);
			addIncludesPropertyDescriptor(object);
			addActiveTopicTypeConstraintPropertyDescriptor(object);
			addActiveScopeTypeConstraintPropertyDescriptor(object);
			addActiveRoleTypeConstraintPropertyDescriptor(object);
			addActiveNameTypeConstraintPropertyDescriptor(object);
			addActiveAssociationTypeConstraintPropertyDescriptor(object);
			addActiveOccurenceTypeConstraintPropertyDescriptor(object);
		}
		return itemPropertyDescriptors;
	}

	/**
	 * This adds a property descriptor for the Mappings feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addMappingsPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_mappings_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_mappings_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__MAPPINGS,
				 true,
				 false,
				 true,
				 null,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Includes feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addIncludesPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_includes_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_includes_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__INCLUDES,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Active Topic Type Constraint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActiveTopicTypeConstraintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_activeTopicTypeConstraint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_activeTopicTypeConstraint_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Active Scope Type Constraint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActiveScopeTypeConstraintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_activeScopeTypeConstraint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_activeScopeTypeConstraint_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Active Role Type Constraint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActiveRoleTypeConstraintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_activeRoleTypeConstraint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_activeRoleTypeConstraint_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Active Name Type Constraint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActiveNameTypeConstraintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_activeNameTypeConstraint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_activeNameTypeConstraint_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Active Association Type Constraint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActiveAssociationTypeConstraintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_activeAssociationTypeConstraint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_activeAssociationTypeConstraint_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This adds a property descriptor for the Active Occurence Type Constraint feature.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected void addActiveOccurenceTypeConstraintPropertyDescriptor(Object object) {
		itemPropertyDescriptors.add
			(createItemPropertyDescriptor
				(((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
				 getResourceLocator(),
				 getString("_UI_TopicMapSchema_activeOccurenceTypeConstraint_feature"),
				 getString("_UI_PropertyDescriptor_description", "_UI_TopicMapSchema_activeOccurenceTypeConstraint_feature", "_UI_TopicMapSchema_type"),
				 ModelPackage.Literals.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT,
				 true,
				 false,
				 false,
				 ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
				 null,
				 null));
	}

	/**
	 * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
	 * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
	 * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object) {
		if (childrenFeatures == null) {
			super.getChildrenFeatures(object);
			childrenFeatures.add(ModelPackage.Literals.TOPIC_MAP_SCHEMA__TOPIC_TYPES);
			childrenFeatures.add(ModelPackage.Literals.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS);
		}
		return childrenFeatures;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EStructuralFeature getChildFeature(Object object, Object child) {
		// Check the type of the specified child object and return the proper feature to use for
		// adding (see {@link AddCommand}) it as a child.

		return super.getChildFeature(object, child);
	}

	/**
	 * This returns TopicMapSchema.gif.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object getImage(Object object) {
		return overlayImage(object, getResourceLocator().getImage("full/obj16/TopicMapSchema"));
	}

	/**
	 * This returns the label text for the adapted class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String getText(Object object) {
		TopicMapSchema topicMapSchema = (TopicMapSchema)object;
		return getString("_UI_TopicMapSchema_type") + " " + topicMapSchema.isActiveNameTypeConstraint();
	}

	/**
	 * This handles model notifications by calling {@link #updateChildren} to update any cached
	 * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void notifyChanged(Notification notification) {
		updateChildren(notification);

		switch (notification.getFeatureID(TopicMapSchema.class)) {
			case ModelPackage.TOPIC_MAP_SCHEMA__INCLUDES:
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_TOPIC_TYPE_CONSTRAINT:
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_SCOPE_TYPE_CONSTRAINT:
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ROLE_TYPE_CONSTRAINT:
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_NAME_TYPE_CONSTRAINT:
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_ASSOCIATION_TYPE_CONSTRAINT:
			case ModelPackage.TOPIC_MAP_SCHEMA__ACTIVE_OCCURENCE_TYPE_CONSTRAINT:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
				return;
			case ModelPackage.TOPIC_MAP_SCHEMA__TOPIC_TYPES:
			case ModelPackage.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS:
				fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
				return;
		}
		super.notifyChanged(notification);
	}

	/**
	 * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
	 * that can be created under this object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object) {
		super.collectNewChildDescriptors(newChildDescriptors, object);

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.TOPIC_MAP_SCHEMA__TOPIC_TYPES,
				 ModelFactory.eINSTANCE.createTopicType()));

		newChildDescriptors.add
			(createChildParameter
				(ModelPackage.Literals.TOPIC_MAP_SCHEMA__ASSOCIATION_TYPE_CONSTRAINTS,
				 ModelFactory.eINSTANCE.createAssociationTypeConstraint()));
	}

	/**
	 * Return the resource locator for this item provider's resources.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public ResourceLocator getResourceLocator() {
		return TmcleditEditPlugin.INSTANCE;
	}

}
