/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model.util;

import de.topicmapslab.tmcledit.model.*;

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
			public Adapter caseOccurenceType(OccurenceType object) {
				return createOccurenceTypeAdapter();
			}
			@Override
			public Adapter caseNameType(NameType object) {
				return createNameTypeAdapter();
			}
			@Override
			public Adapter caseScopeType(ScopeType object) {
				return createScopeTypeAdapter();
			}
			@Override
			public Adapter caseAbstractConstraint(AbstractConstraint object) {
				return createAbstractConstraintAdapter();
			}
			@Override
			public Adapter caseOccurenceTypeConstraint(OccurenceTypeConstraint object) {
				return createOccurenceTypeConstraintAdapter();
			}
			@Override
			public Adapter caseNameTypeConstraint(NameTypeConstraint object) {
				return createNameTypeConstraintAdapter();
			}
			@Override
			public Adapter caseAssociationsType(AssociationsType object) {
				return createAssociationsTypeAdapter();
			}
			@Override
			public Adapter caseRoleType(RoleType object) {
				return createRoleTypeAdapter();
			}
			@Override
			public Adapter caseRoleTypeConstraints(RoleTypeConstraints object) {
				return createRoleTypeConstraintsAdapter();
			}
			@Override
			public Adapter caseTopicMapSchema(TopicMapSchema object) {
				return createTopicMapSchemaAdapter();
			}
			@Override
			public Adapter casesubjectLocatorConstraint(subjectLocatorConstraint object) {
				return createsubjectLocatorConstraintAdapter();
			}
			@Override
			public Adapter casesubjectIdentifierConstraint(subjectIdentifierConstraint object) {
				return createsubjectIdentifierConstraintAdapter();
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
			public Adapter caseBendpoints(Bendpoints object) {
				return createBendpointsAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.OccurenceType <em>Occurence Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.OccurenceType
	 * @generated
	 */
	public Adapter createOccurenceTypeAdapter() {
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.ScopeType <em>Scope Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.ScopeType
	 * @generated
	 */
	public Adapter createScopeTypeAdapter() {
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.OccurenceTypeConstraint <em>Occurence Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.OccurenceTypeConstraint
	 * @generated
	 */
	public Adapter createOccurenceTypeConstraintAdapter() {
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.AssociationsType <em>Associations Type</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.AssociationsType
	 * @generated
	 */
	public Adapter createAssociationsTypeAdapter() {
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.RoleTypeConstraints <em>Role Type Constraints</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.RoleTypeConstraints
	 * @generated
	 */
	public Adapter createRoleTypeConstraintsAdapter() {
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.subjectLocatorConstraint <em>subject Locator Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.subjectLocatorConstraint
	 * @generated
	 */
	public Adapter createsubjectLocatorConstraintAdapter() {
		return null;
	}

	/**
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.subjectIdentifierConstraint <em>subject Identifier Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.subjectIdentifierConstraint
	 * @generated
	 */
	public Adapter createsubjectIdentifierConstraintAdapter() {
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.Bendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.Bendpoints
	 * @generated
	 */
	public Adapter createBendpointsAdapter() {
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
