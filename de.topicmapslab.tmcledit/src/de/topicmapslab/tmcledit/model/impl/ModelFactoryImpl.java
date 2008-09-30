/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.impl;

import de.topicmapslab.tmcledit.model.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ModelFactoryImpl extends EFactoryImpl implements ModelFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static ModelFactory init() {
		try {
			ModelFactory theModelFactory = (ModelFactory)EPackage.Registry.INSTANCE.getEFactory("http://topicmapslab.de/tmceledit/1.0"); 
			if (theModelFactory != null) {
				return theModelFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new ModelFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case ModelPackage.TOPIC_TYPE: return createTopicType();
			case ModelPackage.OCCURENCE_TYPE: return createOccurenceType();
			case ModelPackage.NAME_TYPE: return createNameType();
			case ModelPackage.SCOPE_TYPE: return createScopeType();
			case ModelPackage.OCCURENCE_TYPE_CONSTRAINT: return createOccurenceTypeConstraint();
			case ModelPackage.NAME_TYPE_CONSTRAINT: return createNameTypeConstraint();
			case ModelPackage.ASSOCIATIONS_TYPE: return createAssociationsType();
			case ModelPackage.ROLE_TYPE: return createRoleType();
			case ModelPackage.ROLE_TYPE_CONSTRAINTS: return createRoleTypeConstraints();
			case ModelPackage.TOPIC_MAP_SCHEMA: return createTopicMapSchema();
			case ModelPackage.SUBJECT_LOCATOR_CONSTRAINT: return createsubjectLocatorConstraint();
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT: return createsubjectIdentifierConstraint();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT: return createAssociationTypeConstraint();
			case ModelPackage.MAPPING_ELEMENT: return createMappingElement();
			case ModelPackage.BEND_POINT: return createBendPoint();
			case ModelPackage.DIAGRAM: return createDiagram();
			case ModelPackage.NODE: return createNode();
			case ModelPackage.EDGES: return createEdges();
			case ModelPackage.TYPE_NODE: return createTypeNode();
			case ModelPackage.ASSOCIATION_NODE: return createAssociationNode();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.TOPIC_ID:
				return createTopicIdFromString(eDataType, initialValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case ModelPackage.TOPIC_ID:
				return convertTopicIdToString(eDataType, instanceValue);
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicType createTopicType() {
		TopicTypeImpl topicType = new TopicTypeImpl();
		return topicType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccurenceType createOccurenceType() {
		OccurenceTypeImpl occurenceType = new OccurenceTypeImpl();
		return occurenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameType createNameType() {
		NameTypeImpl nameType = new NameTypeImpl();
		return nameType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopeType createScopeType() {
		ScopeTypeImpl scopeType = new ScopeTypeImpl();
		return scopeType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccurenceTypeConstraint createOccurenceTypeConstraint() {
		OccurenceTypeConstraintImpl occurenceTypeConstraint = new OccurenceTypeConstraintImpl();
		return occurenceTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NameTypeConstraint createNameTypeConstraint() {
		NameTypeConstraintImpl nameTypeConstraint = new NameTypeConstraintImpl();
		return nameTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationsType createAssociationsType() {
		AssociationsTypeImpl associationsType = new AssociationsTypeImpl();
		return associationsType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleType createRoleType() {
		RoleTypeImpl roleType = new RoleTypeImpl();
		return roleType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleTypeConstraints createRoleTypeConstraints() {
		RoleTypeConstraintsImpl roleTypeConstraints = new RoleTypeConstraintsImpl();
		return roleTypeConstraints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicMapSchema createTopicMapSchema() {
		TopicMapSchemaImpl topicMapSchema = new TopicMapSchemaImpl();
		return topicMapSchema;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public subjectLocatorConstraint createsubjectLocatorConstraint() {
		subjectLocatorConstraintImpl subjectLocatorConstraint = new subjectLocatorConstraintImpl();
		return subjectLocatorConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public subjectIdentifierConstraint createsubjectIdentifierConstraint() {
		subjectIdentifierConstraintImpl subjectIdentifierConstraint = new subjectIdentifierConstraintImpl();
		return subjectIdentifierConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationTypeConstraint createAssociationTypeConstraint() {
		AssociationTypeConstraintImpl associationTypeConstraint = new AssociationTypeConstraintImpl();
		return associationTypeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MappingElement createMappingElement() {
		MappingElementImpl mappingElement = new MappingElementImpl();
		return mappingElement;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public BendPoint createBendPoint() {
		BendPointImpl bendPoint = new BendPointImpl();
		return bendPoint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Diagram createDiagram() {
		DiagramImpl diagram = new DiagramImpl();
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Edges createEdges() {
		EdgesImpl edges = new EdgesImpl();
		return edges;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TypeNode createTypeNode() {
		TypeNodeImpl typeNode = new TypeNodeImpl();
		return typeNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationNode createAssociationNode() {
		AssociationNodeImpl associationNode = new AssociationNodeImpl();
		return associationNode;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicId createTopicIdFromString(EDataType eDataType, String initialValue) {
		TopicId result = TopicId.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertTopicIdToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelPackage getModelPackage() {
		return (ModelPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static ModelPackage getPackage() {
		return ModelPackage.eINSTANCE;
	}

} //ModelFactoryImpl
