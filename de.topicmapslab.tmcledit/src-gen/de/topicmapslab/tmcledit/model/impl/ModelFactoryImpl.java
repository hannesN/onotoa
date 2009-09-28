/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
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
			case ModelPackage.OCCURRENCE_TYPE_CONSTRAINT: return createOccurrenceTypeConstraint();
			case ModelPackage.NAME_TYPE_CONSTRAINT: return createNameTypeConstraint();
			case ModelPackage.ROLE_PLAYER_CONSTRAINT: return createRolePlayerConstraint();
			case ModelPackage.TOPIC_MAP_SCHEMA: return createTopicMapSchema();
			case ModelPackage.SUBJECT_LOCATOR_CONSTRAINT: return createSubjectLocatorConstraint();
			case ModelPackage.SUBJECT_IDENTIFIER_CONSTRAINT: return createSubjectIdentifierConstraint();
			case ModelPackage.ASSOCIATION_TYPE_CONSTRAINT: return createAssociationTypeConstraint();
			case ModelPackage.MAPPING_ELEMENT: return createMappingElement();
			case ModelPackage.NODE: return createNode();
			case ModelPackage.TYPE_NODE: return createTypeNode();
			case ModelPackage.BENDPOINT: return createBendpoint();
			case ModelPackage.EDGE: return createEdge();
			case ModelPackage.ASSOCIATION_NODE: return createAssociationNode();
			case ModelPackage.DIAGRAM: return createDiagram();
			case ModelPackage.FILE: return createFile();
			case ModelPackage.SCOPE_CONSTRAINT: return createScopeConstraint();
			case ModelPackage.LABEL_POS: return createLabelPos();
			case ModelPackage.ASSOCIATION_TYPE: return createAssociationType();
			case ModelPackage.OCCURRENCE_TYPE: return createOccurrenceType();
			case ModelPackage.ROLE_CONSTRAINT: return createRoleConstraint();
			case ModelPackage.ROLE_TYPE: return createRoleType();
			case ModelPackage.ROLE_COMBINATION_CONSTRAINT: return createRoleCombinationConstraint();
			case ModelPackage.NAME_TYPE: return createNameType();
			case ModelPackage.ABSTRACT_TYPED_CARDINALITY_CONSTRAINT: return createAbstractTypedCardinalityConstraint();
			case ModelPackage.COMMENT: return createComment();
			case ModelPackage.TMCL_CONSTRUCT: return createTMCLConstruct();
			case ModelPackage.REIFIER_CONSTRAINT: return createReifierConstraint();
			case ModelPackage.REIFIABLE_TOPIC_TYPE: return createReifiableTopicType();
			case ModelPackage.SCOPED_REIFIABLE_TOPIC_TYPE: return createScopedReifiableTopicType();
			case ModelPackage.ANNOTATION: return createAnnotation();
			case ModelPackage.TOPIC_REIFIES_CONSTRAINT: return createTopicReifiesConstraint();
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
	public OccurrenceTypeConstraint createOccurrenceTypeConstraint() {
		OccurrenceTypeConstraintImpl occurrenceTypeConstraint = new OccurrenceTypeConstraintImpl();
		return occurrenceTypeConstraint;
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
	public RolePlayerConstraint createRolePlayerConstraint() {
		RolePlayerConstraintImpl rolePlayerConstraint = new RolePlayerConstraintImpl();
		return rolePlayerConstraint;
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
	public Bendpoint createBendpoint() {
		BendpointImpl bendpoint = new BendpointImpl();
		return bendpoint;
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
	public ScopeConstraint createScopeConstraint() {
		ScopeConstraintImpl scopeConstraint = new ScopeConstraintImpl();
		return scopeConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LabelPos createLabelPos() {
		LabelPosImpl labelPos = new LabelPosImpl();
		return labelPos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public AssociationType createAssociationType() {
		AssociationTypeImpl associationType = new AssociationTypeImpl();
		return associationType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public OccurrenceType createOccurrenceType() {
		OccurrenceTypeImpl occurrenceType = new OccurrenceTypeImpl();
		return occurrenceType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RoleConstraint createRoleConstraint() {
		RoleConstraintImpl roleConstraint = new RoleConstraintImpl();
		return roleConstraint;
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
	public RoleCombinationConstraint createRoleCombinationConstraint() {
		RoleCombinationConstraintImpl roleCombinationConstraint = new RoleCombinationConstraintImpl();
		return roleCombinationConstraint;
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
	public AbstractTypedCardinalityConstraint createAbstractTypedCardinalityConstraint() {
		AbstractTypedCardinalityConstraintImpl abstractTypedCardinalityConstraint = new AbstractTypedCardinalityConstraintImpl();
		return abstractTypedCardinalityConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Comment createComment() {
		CommentImpl comment = new CommentImpl();
		return comment;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TMCLConstruct createTMCLConstruct() {
		TMCLConstructImpl tmclConstruct = new TMCLConstructImpl();
		return tmclConstruct;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReifierConstraint createReifierConstraint() {
		ReifierConstraintImpl reifierConstraint = new ReifierConstraintImpl();
		return reifierConstraint;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ReifiableTopicType createReifiableTopicType() {
		ReifiableTopicTypeImpl reifiableTopicType = new ReifiableTopicTypeImpl();
		return reifiableTopicType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ScopedReifiableTopicType createScopedReifiableTopicType() {
		ScopedReifiableTopicTypeImpl scopedReifiableTopicType = new ScopedReifiableTopicTypeImpl();
		return scopedReifiableTopicType;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Annotation createAnnotation() {
		AnnotationImpl annotation = new AnnotationImpl();
		return annotation;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public TopicReifiesConstraint createTopicReifiesConstraint() {
		TopicReifiesConstraintImpl topicReifiesConstraint = new TopicReifiesConstraintImpl();
		return topicReifiesConstraint;
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
