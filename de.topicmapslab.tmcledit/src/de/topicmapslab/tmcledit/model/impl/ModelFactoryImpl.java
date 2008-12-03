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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";

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
			case ModelPackage.OCCURENCE_TYPE_CONSTRAINT: return createOccurenceTypeConstraint();
			case ModelPackage.NAME_TYPE_CONSTRAINT: return createNameTypeConstraint();
			case ModelPackage.ROLE_TYPE_CONSTRAINTS: return createRoleTypeConstraints();
			case ModelPackage.TOPIC_MAP_SCHEMA: return createTopicMapSchema();
			case ModelPackage.SUBJECT_LOCATOR_CONSTRAINT: return createSubjectLocatorConstraint();
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT: return createSubjectIdentifierConstraint();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT: return createAssociationTypeConstraint();
			case ModelPackage.MAPPING_ELEMENT: return createMappingElement();
			case ModelPackage.NODE: return createNode();
			case ModelPackage.TYPE_NODE: return createTypeNode();
			case ModelPackage.BENDPOINTS: return createBendpoints();
			case ModelPackage.EDGE: return createEdge();
			case ModelPackage.ASSOCIATION_NODE: return createAssociationNode();
			case ModelPackage.DIAGRAM: return createDiagram();
			case ModelPackage.FILE: return createFile();
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
			case ModelPackage.EDGE_TYPE:
				return createEdgeTypeFromString(eDataType, initialValue);
			case ModelPackage.KIND_OF_TOPIC_TYPE:
				return createKindOfTopicTypeFromString(eDataType, initialValue);
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
			case ModelPackage.EDGE_TYPE:
				return convertEdgeTypeToString(eDataType, instanceValue);
			case ModelPackage.KIND_OF_TOPIC_TYPE:
				return convertKindOfTopicTypeToString(eDataType, instanceValue);
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
	public SubjectLocatorConstraint createSubjectLocatorConstraint() {
		SubjectLocatorConstraintImpl subjectLocatorConstraint = new SubjectLocatorConstraintImpl();
		return subjectLocatorConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public SubjectIdentifierConstraint createSubjectIdentifierConstraint() {
		SubjectIdentifierConstraintImpl subjectIdentifierConstraint = new SubjectIdentifierConstraintImpl();
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
	public Node createNode() {
		NodeImpl node = new NodeImpl();
		return node;
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
	public Bendpoints createBendpoints() {
		BendpointsImpl bendpoints = new BendpointsImpl();
		return bendpoints;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Edge createEdge() {
		EdgeImpl edge = new EdgeImpl();
		return edge;
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
	public Diagram createDiagram() {
		DiagramImpl diagram = new DiagramImpl();
		return diagram;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public File createFile() {
		FileImpl file = new FileImpl();
		return file;
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
	public EdgeType createEdgeTypeFromString(EDataType eDataType, String initialValue) {
		EdgeType result = EdgeType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertEdgeTypeToString(EDataType eDataType, Object instanceValue) {
		return instanceValue == null ? null : instanceValue.toString();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public KindOfTopicType createKindOfTopicTypeFromString(EDataType eDataType, String initialValue) {
		KindOfTopicType result = KindOfTopicType.get(initialValue);
		if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
		return result;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertKindOfTopicTypeToString(EDataType eDataType, Object instanceValue) {
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
