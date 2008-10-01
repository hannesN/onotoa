/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.diagram.model.ModelFactory
 * @model kind="package"
 * @generated
 */
public interface ModelPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "model";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://tmcldiagram/1.0";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "tmcldiagram";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	ModelPackage eINSTANCE = de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl.init();

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.DiagramImpl <em>Diagram</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.DiagramImpl
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getDiagram()
	 * @generated
	 */
	int DIAGRAM = 0;

	/**
	 * The feature id for the '<em><b>Nodes</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__NODES = 0;

	/**
	 * The feature id for the '<em><b>Edges</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__EDGES = 1;

	/**
	 * The feature id for the '<em><b>Topic Map Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM__TOPIC_MAP_SCHEMA = 2;

	/**
	 * The number of structural features of the '<em>Diagram</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DIAGRAM_FEATURE_COUNT = 3;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.NodeImpl <em>Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.NodeImpl
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getNode()
	 * @generated
	 */
	int NODE = 1;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__POS_Y = 0;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE__POS_X = 1;

	/**
	 * The number of structural features of the '<em>Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int NODE_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.TypeNodeImpl <em>Type Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.TypeNodeImpl
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getTypeNode()
	 * @generated
	 */
	int TYPE_NODE = 2;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE__POS_Y = NODE__POS_Y;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE__POS_X = NODE__POS_X;

	/**
	 * The feature id for the '<em><b>Topic Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE__TOPIC_TYPE = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Type Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int TYPE_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.AssociationNodeImpl <em>Association Node</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.AssociationNodeImpl
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getAssociationNode()
	 * @generated
	 */
	int ASSOCIATION_NODE = 3;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE__POS_Y = NODE__POS_Y;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE__POS_X = NODE__POS_X;

	/**
	 * The feature id for the '<em><b>Association Type Constraint</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE__ASSOCIATION_TYPE_CONSTRAINT = NODE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Association Node</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ASSOCIATION_NODE_FEATURE_COUNT = NODE_FEATURE_COUNT + 1;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.BendPointImpl <em>Bend Point</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.BendPointImpl
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getBendPoint()
	 * @generated
	 */
	int BEND_POINT = 4;

	/**
	 * The feature id for the '<em><b>Pos X</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEND_POINT__POS_X = 0;

	/**
	 * The feature id for the '<em><b>Pos Y</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEND_POINT__POS_Y = 1;

	/**
	 * The number of structural features of the '<em>Bend Point</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEND_POINT_FEATURE_COUNT = 2;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.EdgeImpl <em>Edge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.EdgeImpl
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getEdge()
	 * @generated
	 */
	int EDGE = 5;

	/**
	 * The feature id for the '<em><b>Bendpoints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__BENDPOINTS = 0;

	/**
	 * The feature id for the '<em><b>Type</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TYPE = 1;

	/**
	 * The feature id for the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__SOURCE = 2;

	/**
	 * The feature id for the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE__TARGET = 3;

	/**
	 * The number of structural features of the '<em>Edge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int EDGE_FEATURE_COUNT = 4;

	/**
	 * The meta object id for the '{@link de.topicmapslab.tmcledit.diagram.model.EdgeType <em>Edge Type</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.diagram.model.EdgeType
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getEdgeType()
	 * @generated
	 */
	int EDGE_TYPE = 6;

	/**
	 * The meta object id for the '<em>Topic Type</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.TopicType
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getTopicType()
	 * @generated
	 */
	int TOPIC_TYPE = 7;

	/**
	 * The meta object id for the '<em>Association Type Constraint</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getAssociationTypeConstraint()
	 * @generated
	 */
	int ASSOCIATION_TYPE_CONSTRAINT = 8;


	/**
	 * The meta object id for the '<em>Topic Map Schema</em>' data type.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema
	 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getTopicMapSchema()
	 * @generated
	 */
	int TOPIC_MAP_SCHEMA = 9;


	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.diagram.model.Diagram <em>Diagram</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Diagram</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Diagram
	 * @generated
	 */
	EClass getDiagram();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.diagram.model.Diagram#getNodes <em>Nodes</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Nodes</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Diagram#getNodes()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Nodes();

	/**
	 * Returns the meta object for the containment reference list '{@link de.topicmapslab.tmcledit.diagram.model.Diagram#getEdges <em>Edges</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Edges</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Diagram#getEdges()
	 * @see #getDiagram()
	 * @generated
	 */
	EReference getDiagram_Edges();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.Diagram#getTopicMapSchema <em>Topic Map Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Topic Map Schema</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Diagram#getTopicMapSchema()
	 * @see #getDiagram()
	 * @generated
	 */
	EAttribute getDiagram_TopicMapSchema();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.diagram.model.Node <em>Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Node</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Node
	 * @generated
	 */
	EClass getNode();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.Node#getPosY <em>Pos Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos Y</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Node#getPosY()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_PosY();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.Node#getPosX <em>Pos X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos X</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Node#getPosX()
	 * @see #getNode()
	 * @generated
	 */
	EAttribute getNode_PosX();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.diagram.model.TypeNode <em>Type Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Type Node</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.TypeNode
	 * @generated
	 */
	EClass getTypeNode();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.TypeNode#getTopicType <em>Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.TypeNode#getTopicType()
	 * @see #getTypeNode()
	 * @generated
	 */
	EAttribute getTypeNode_TopicType();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.diagram.model.AssociationNode <em>Association Node</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Association Node</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.AssociationNode
	 * @generated
	 */
	EClass getAssociationNode();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.AssociationNode#getAssociationTypeConstraint <em>Association Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Association Type Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.AssociationNode#getAssociationTypeConstraint()
	 * @see #getAssociationNode()
	 * @generated
	 */
	EAttribute getAssociationNode_AssociationTypeConstraint();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.diagram.model.BendPoint <em>Bend Point</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Bend Point</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.BendPoint
	 * @generated
	 */
	EClass getBendPoint();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.BendPoint#getPosX <em>Pos X</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos X</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.BendPoint#getPosX()
	 * @see #getBendPoint()
	 * @generated
	 */
	EAttribute getBendPoint_PosX();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.BendPoint#getPosY <em>Pos Y</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Pos Y</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.BendPoint#getPosY()
	 * @see #getBendPoint()
	 * @generated
	 */
	EAttribute getBendPoint_PosY();

	/**
	 * Returns the meta object for class '{@link de.topicmapslab.tmcledit.diagram.model.Edge <em>Edge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Edge</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Edge
	 * @generated
	 */
	EClass getEdge();

	/**
	 * Returns the meta object for the containment reference '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getBendpoints <em>Bendpoints</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Bendpoints</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Edge#getBendpoints()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Bendpoints();

	/**
	 * Returns the meta object for the attribute '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getType <em>Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Type</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Edge#getType()
	 * @see #getEdge()
	 * @generated
	 */
	EAttribute getEdge_Type();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getSource <em>Source</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Source</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Edge#getSource()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Source();

	/**
	 * Returns the meta object for the reference '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getTarget <em>Target</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Target</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.Edge#getTarget()
	 * @see #getEdge()
	 * @generated
	 */
	EReference getEdge_Target();

	/**
	 * Returns the meta object for enum '{@link de.topicmapslab.tmcledit.diagram.model.EdgeType <em>Edge Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Edge Type</em>'.
	 * @see de.topicmapslab.tmcledit.diagram.model.EdgeType
	 * @generated
	 */
	EEnum getEdgeType();

	/**
	 * Returns the meta object for data type '{@link de.topicmapslab.tmcledit.model.TopicType <em>Topic Type</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Topic Type</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicType
	 * @model instanceClass="de.topicmapslab.tmcledit.model.TopicType"
	 * @generated
	 */
	EDataType getTopicType();

	/**
	 * Returns the meta object for data type '{@link de.topicmapslab.tmcledit.model.AssociationTypeConstraint <em>Association Type Constraint</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Association Type Constraint</em>'.
	 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint
	 * @model instanceClass="de.topicmapslab.tmcledit.model.AssociationTypeConstraint"
	 * @generated
	 */
	EDataType getAssociationTypeConstraint();

	/**
	 * Returns the meta object for data type '{@link de.topicmapslab.tmcledit.model.TopicMapSchema <em>Topic Map Schema</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for data type '<em>Topic Map Schema</em>'.
	 * @see de.topicmapslab.tmcledit.model.TopicMapSchema
	 * @model instanceClass="de.topicmapslab.tmcledit.model.TopicMapSchema"
	 * @generated
	 */
	EDataType getTopicMapSchema();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	ModelFactory getModelFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.DiagramImpl <em>Diagram</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.DiagramImpl
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getDiagram()
		 * @generated
		 */
		EClass DIAGRAM = eINSTANCE.getDiagram();

		/**
		 * The meta object literal for the '<em><b>Nodes</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__NODES = eINSTANCE.getDiagram_Nodes();

		/**
		 * The meta object literal for the '<em><b>Edges</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference DIAGRAM__EDGES = eINSTANCE.getDiagram_Edges();

		/**
		 * The meta object literal for the '<em><b>Topic Map Schema</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DIAGRAM__TOPIC_MAP_SCHEMA = eINSTANCE.getDiagram_TopicMapSchema();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.NodeImpl <em>Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.NodeImpl
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getNode()
		 * @generated
		 */
		EClass NODE = eINSTANCE.getNode();

		/**
		 * The meta object literal for the '<em><b>Pos Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__POS_Y = eINSTANCE.getNode_PosY();

		/**
		 * The meta object literal for the '<em><b>Pos X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute NODE__POS_X = eINSTANCE.getNode_PosX();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.TypeNodeImpl <em>Type Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.TypeNodeImpl
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getTypeNode()
		 * @generated
		 */
		EClass TYPE_NODE = eINSTANCE.getTypeNode();

		/**
		 * The meta object literal for the '<em><b>Topic Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute TYPE_NODE__TOPIC_TYPE = eINSTANCE.getTypeNode_TopicType();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.AssociationNodeImpl <em>Association Node</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.AssociationNodeImpl
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getAssociationNode()
		 * @generated
		 */
		EClass ASSOCIATION_NODE = eINSTANCE.getAssociationNode();

		/**
		 * The meta object literal for the '<em><b>Association Type Constraint</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ASSOCIATION_NODE__ASSOCIATION_TYPE_CONSTRAINT = eINSTANCE.getAssociationNode_AssociationTypeConstraint();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.BendPointImpl <em>Bend Point</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.BendPointImpl
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getBendPoint()
		 * @generated
		 */
		EClass BEND_POINT = eINSTANCE.getBendPoint();

		/**
		 * The meta object literal for the '<em><b>Pos X</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEND_POINT__POS_X = eINSTANCE.getBendPoint_PosX();

		/**
		 * The meta object literal for the '<em><b>Pos Y</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute BEND_POINT__POS_Y = eINSTANCE.getBendPoint_PosY();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.diagram.model.impl.EdgeImpl <em>Edge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.EdgeImpl
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getEdge()
		 * @generated
		 */
		EClass EDGE = eINSTANCE.getEdge();

		/**
		 * The meta object literal for the '<em><b>Bendpoints</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__BENDPOINTS = eINSTANCE.getEdge_Bendpoints();

		/**
		 * The meta object literal for the '<em><b>Type</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute EDGE__TYPE = eINSTANCE.getEdge_Type();

		/**
		 * The meta object literal for the '<em><b>Source</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__SOURCE = eINSTANCE.getEdge_Source();

		/**
		 * The meta object literal for the '<em><b>Target</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference EDGE__TARGET = eINSTANCE.getEdge_Target();

		/**
		 * The meta object literal for the '{@link de.topicmapslab.tmcledit.diagram.model.EdgeType <em>Edge Type</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.diagram.model.EdgeType
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getEdgeType()
		 * @generated
		 */
		EEnum EDGE_TYPE = eINSTANCE.getEdgeType();

		/**
		 * The meta object literal for the '<em>Topic Type</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.TopicType
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getTopicType()
		 * @generated
		 */
		EDataType TOPIC_TYPE = eINSTANCE.getTopicType();

		/**
		 * The meta object literal for the '<em>Association Type Constraint</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.AssociationTypeConstraint
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getAssociationTypeConstraint()
		 * @generated
		 */
		EDataType ASSOCIATION_TYPE_CONSTRAINT = eINSTANCE.getAssociationTypeConstraint();

		/**
		 * The meta object literal for the '<em>Topic Map Schema</em>' data type.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see de.topicmapslab.tmcledit.model.TopicMapSchema
		 * @see de.topicmapslab.tmcledit.diagram.model.impl.ModelPackageImpl#getTopicMapSchema()
		 * @generated
		 */
		EDataType TOPIC_MAP_SCHEMA = eINSTANCE.getTopicMapSchema();

	}

} //ModelPackage
