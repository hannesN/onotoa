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
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final String copyright = "(C) 2008 Hannes Niederhause, Topic Maps Lab";
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
			public Adapter caseRoleTypeConstraints(RoleTypeConstraints object) {
				return createRoleTypeConstraintsAdapter();
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
			public Adapter caseScopedConstraint(ScopedConstraint object) {
				return createScopedConstraintAdapter();
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
	 * Creates a new adapter for an object of class '{@link de.topicmapslab.tmcledit.model.ScopedConstraint <em>Scoped Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * This default implementation returns null so that we can easily ignore cases;
	 * it's useful to ignore a case when inheritance will catch all the cases anyway.
	 * <!-- end-user-doc -->
	 * @return the new adapter.
	 * @see de.topicmapslab.tmcledit.model.ScopedConstraint
	 * @generated
	 */
	public Adapter createScopedConstraintAdapter() {
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
