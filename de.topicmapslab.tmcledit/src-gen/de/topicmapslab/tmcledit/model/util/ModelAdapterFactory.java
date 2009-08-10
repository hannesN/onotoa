/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.util;

import de.topicmapslab.tmcledit.model.*;

import java.util.Map;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.model.ModelPackage
 * @generated
 */
public class ModelAdapterFactory extends AdapterFactoryImpl {
	/**
	 * The cached model package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected static ModelPackage modelPackage;

	/**
	 * Creates an instance of the adapter factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public ModelAdapterFactory() {
		if (modelPackage == null) {
			modelPackage = ModelPackage.eINSTANCE;
		}
	}

	/**
	 * Returns whether this factory is applicable for the type of the object.
	 * <!-- begin-user-doc -->
	 * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
	 * <!-- end-user-doc -->
	 * @return whether this factory is applicable for the type of the object.
	 * @generated
	 */
	@Override
	public boolean isFactoryForType(Object object) {
		if (object == modelPackage) {
			return true;
		}
		if (object instanceof EObject) {
			return ((EObject)object).eClass().getEPackage() == modelPackage;
		}
		return false;
	}

	/**
	 * The switch that delegates to the <code>createXXX</code> methods.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected ModelSwitch<Adapter> modelSwitch =
		new ModelSwitch<Adapter>() {
			@Override
			public Adapter caseTopicType(TopicType object) {
				return createTopicTypeAdapter();
			}
			@Override
			public Adapter caseAbstractRegExpConstraint(AbstractRegExpConstraint object) {
				return createAbstractRegExpConstraintAdapter();
			}
			@Override
			public Adapter caseOccurrenceTypeConstraint(OccurrenceTypeConstraint object) {
				return createOccurrenceTypeConstraintAdapter();
			}
			@Override
			public Adapter caseNameTypeConstraint(NameTypeConstraint object) {
				return createNameTypeConstraintAdapter();
			}
			@Override
			public Adapter caseRolePlayerConstraint(RolePlayerConstraint object) {
				return createRolePlayerConstraintAdapter();
			}
			@Override
			public Adapter caseTopicMapSchema(TopicMapSchema object) {
				return createTopicMapSchemaAdapter();
			}
			@Override
			public Adapter caseSubjectLocatorConstraint(SubjectLocatorConstraint object) {
				return createSubjectLocatorConstraintAdapter();
			}
			@Override
			public Adapter caseSubjectIdentifierConstraint(SubjectIdentifierConstraint object) {
				return createSubjectIdentifierConstraintAdapter();
			}
			@Override
			public Adapter caseAssociationTypeConstraint(AssociationTypeConstraint object) {
				return createAssociationTypeConstraintAdapter();
			}
			@Override
			public Adapter caseMappingElement(MappingElement object) {
				return createMappingElementAdapter();
			}
			@Override
			public Adapter caseNode(Node object) {
				return createNodeAdapter();
			}
			@Override
			public Adapter caseTypeNode(TypeNode object) {
				return createTypeNodeAdapter();
			}
			@Override
			public Adapter caseBendpoint(Bendpoint object) {
				return createBendpointAdapter();
			}
			@Override
			public Adapter caseEdge(Edge object) {
				return createEdgeAdapter();
			}
			@Override
			public Adapter caseAssociationNode(AssociationNode object) {
				return createAssociationNodeAdapter();
			}
			@Override
			public Adapter caseDiagram(Diagram object) {
				return createDiagramAdapter();
			}
			@Override
			public Adapter caseFile(File object) {
				return createFileAdapter();
			}
			@Override
			public Adapter caseScopeConstraint(ScopeConstraint object) {
				return createScopeConstraintAdapter();
			}
			@Override
			public Adapter caseAbstractCardinalityContraint(AbstractCardinalityContraint object) {
				return createAbstractCardinalityContraintAdapter();
			}
			@Override
			public Adapter caseLabelPos(LabelPos object) {
				return createLabelPosAdapter();
			}
			@Override
			public Adapter caseAbstractTypedConstraint(AbstractTypedConstraint object) {
				return createAbstractTypedConstraintAdapter();
			}
			@Override
			public Adapter caseScopedTopicType(ScopedTopicType object) {
				return createScopedTopicTypeAdapter();
			}
			@Override
			public Adapter caseAssociationType(AssociationType object) {
				return createAssociationTypeAdapter();
			}
			@Override
			public Adapter caseOccurrenceType(OccurrenceType object) {
				return createOccurrenceTypeAdapter();
			}
			@Override
			public Adapter caseRoleConstraint(RoleConstraint object) {
				return createRoleConstraintAdapter();
			}
			@Override
			public Adapter caseRoleType(RoleType object) {
				return createRoleTypeAdapter();
			}
			@Override
			public Adapter caseRoleCombinationConstraint(RoleCombinationConstraint object) {
				return createRoleCombinationConstraintAdapter();
			}
			@Override
			public Adapter caseNameType(NameType object) {
				return createNameTypeAdapter();
			}
			@Override
			public Adapter caseAbstractTypedCardinalityConstraint(AbstractTypedCardinalityConstraint object) {
				return createAbstractTypedCardinalityConstraintAdapter();
			}
			@Override
			public Adapter caseComment(Comment object) {
				return createCommentAdapter();
			}
			@Override
			public Adapter caseTMCLConstruct(TMCLConstruct object) {
				return createTMCLConstructAdapter();
			}
			@Override
			public Adapter caseReifierConstraint(ReifierConstraint object) {
				return createReifierConstraintAdapter();
			}
			@Override
			public Adapter caseReifiableTopicType(ReifiableTopicType object) {
				return createReifiableTopicTypeAdapter();
			}
			@Override
			public Adapter caseScopedReifiableTopicType(ScopedReifiableTopicType object) {
				return createScopedReifiableTopicTypeAdapter();
			}
			@Override
			public Adapter caseEStringToEStringMap(Map.Entry<String, String> object) {
				return createEStringToEStringMapAdapter();
			}
			@Override
			public Adapter caseAbstractRegExpTopicType(AbstractRegExpTopicType object) {
				return createAbstractRegExpTopicTypeAdapter();
			}
			@Override
			public Adapter caseAbstractConstraint(AbstractConstraint object) {
				return createAbstractConstraintAdapter();
			}
			@Override
			public Adapter defaultCase(EObject object) {
				return createEObjectAdapter();
			}
		};

	/**
	 * Creates an adapter for the <code>target</code>.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param target the object to adapt.
	 * @return the adapter for the <code>target</code>.
	 * @generated
	 */
	@Override
	public Adapter createAdapter(Notifier target) {
		return modelSwitch.doSwitch((EObject)target);
	}


	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.TopicType <em>Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.TopicType
	 * @generated
	 */
	public Adapter createTopicTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AbstractRegExpConstraint <em>Abstract Reg Exp Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AbstractRegExpConstraint
	 * @generated
	 */
	public Adapter createAbstractRegExpConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AbstractConstraint <em>Abstract Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AbstractConstraint
	 * @generated
	 */
	public Adapter createAbstractConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint <em>Occurrence Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.OccurrenceTypeConstraint
	 * @generated
	 */
	public Adapter createOccurrenceTypeConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.NameTypeConstraint <em>Name Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.NameTypeConstraint
	 * @generated
	 */
	public Adapter createNameTypeConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.RolePlayerConstraint <em>Role Player Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.RolePlayerConstraint
	 * @generated
	 */
	public Adapter createRolePlayerConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.TopicMapSchema <em>Topic Map Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema
	 * @generated
	 */
	public Adapter createTopicMapSchemaAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.SubjectLocatorConstraint <em>Subject Locator Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.SubjectLocatorConstraint
	 * @generated
	 */
	public Adapter createSubjectLocatorConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint <em>Subject Identifier Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.SubjectIdentifierConstraint
	 * @generated
	 */
	public Adapter createSubjectIdentifierConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint <em>Association Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint
	 * @generated
	 */
	public Adapter createAssociationTypeConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.MappingElement <em>Mapping Element</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.MappingElement
	 * @generated
	 */
	public Adapter createMappingElementAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.Node
	 * @generated
	 */
	public Adapter createNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.TypeNode <em>Type Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.TypeNode
	 * @generated
	 */
	public Adapter createTypeNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.Bendpoint <em>Bendpoint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.Bendpoint
	 * @generated
	 */
	public Adapter createBendpointAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.Edge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.Edge
	 * @generated
	 */
	public Adapter createEdgeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AssociationNode <em>Association Node</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AssociationNode
	 * @generated
	 */
	public Adapter createAssociationNodeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.Diagram
	 * @generated
	 */
	public Adapter createDiagramAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.File <em>File</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.File
	 * @generated
	 */
	public Adapter createFileAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.ScopeConstraint <em>Scope Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.ScopeConstraint
	 * @generated
	 */
	public Adapter createScopeConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AbstractCardinalityContraint <em>Abstract Cardinality Contraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AbstractCardinalityContraint
	 * @generated
	 */
	public Adapter createAbstractCardinalityContraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.LabelPos <em>Label Pos</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.LabelPos
	 * @generated
	 */
	public Adapter createLabelPosAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AbstractTypedConstraint <em>Abstract Typed Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AbstractTypedConstraint
	 * @generated
	 */
	public Adapter createAbstractTypedConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.ScopedTopicType <em>Scoped Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.ScopedTopicType
	 * @generated
	 */
	public Adapter createScopedTopicTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AssociationType <em>Association Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AssociationType
	 * @generated
	 */
	public Adapter createAssociationTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.OccurrenceType <em>Occurrence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.OccurrenceType
	 * @generated
	 */
	public Adapter createOccurrenceTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.RoleConstraint <em>Role Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.RoleConstraint
	 * @generated
	 */
	public Adapter createRoleConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.RoleType <em>Role Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.RoleType
	 * @generated
	 */
	public Adapter createRoleTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.RoleCombinationConstraint <em>Role Combination Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.RoleCombinationConstraint
	 * @generated
	 */
	public Adapter createRoleCombinationConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.NameType <em>Name Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.NameType
	 * @generated
	 */
	public Adapter createNameTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint <em>Abstract Typed Cardinality Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AbstractTypedCardinalityConstraint
	 * @generated
	 */
	public Adapter createAbstractTypedCardinalityConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.Comment <em>Comment</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.Comment
	 * @generated
	 */
	public Adapter createCommentAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.TMCLConstruct <em>TMCL Construct</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.TMCLConstruct
	 * @generated
	 */
	public Adapter createTMCLConstructAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.ReifierConstraint <em>Reifier Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.ReifierConstraint
	 * @generated
	 */
	public Adapter createReifierConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.ReifiableTopicType <em>Reifiable Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.ReifiableTopicType
	 * @generated
	 */
	public Adapter createReifiableTopicTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.ScopedReifiableTopicType <em>Scoped Reifiable Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.ScopedReifiableTopicType
	 * @generated
	 */
	public Adapter createScopedReifiableTopicTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>EString To EString Map</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see java.util.Map.Entry
	 * @generated
	 */
	public Adapter createEStringToEStringMapAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AbstractRegExpTopicType <em>Abstract Reg Exp Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AbstractRegExpTopicType
	 * @generated
	 */
	public Adapter createAbstractRegExpTopicTypeAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for the default case.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @generated
	 */
	public Adapter createEObjectAdapter() {
		return null;
	}

} //ModelAdapterFactory
