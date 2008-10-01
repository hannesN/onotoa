/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model;

import de.topicmapslab.tmcledit.model.TopicMapSchema;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Diagram</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.Diagram#getNodes <em>Nodes</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.Diagram#getEdges <em>Edges</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.Diagram#getTopicMapSchema <em>Topic Map Schema</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getDiagram()
 * @model
 * @generated
 */
public interface Diagram extends EObject {
	/**
	 * Returns the value of the '<em><b>Nodes</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.diagram.model.Node}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Nodes</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Nodes</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getDiagram_Nodes()
	 * @model containment="true"
	 * @generated
	 */
	EList<Node> getNodes();

	/**
	 * Returns the value of the '<em><b>Edges</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.diagram.model.Edge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Edges</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Edges</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getDiagram_Edges()
	 * @model containment="true"
	 * @generated
	 */
	EList<Edge> getEdges();

	/**
	 * Returns the value of the '<em><b>Topic Map Schema</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topic Map Schema</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic Map Schema</em>' attribute.
	 * @see #setTopicMapSchema(TopicMapSchema)
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getDiagram_TopicMapSchema()
	 * @model dataType="de.topicmapslab.tmcledit.diagram.model.TopicMapSchema"
	 * @generated
	 */
	TopicMapSchema getTopicMapSchema();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.diagram.model.Diagram#getTopicMapSchema <em>Topic Map Schema</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topic Map Schema</em>' attribute.
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	void setTopicMapSchema(TopicMapSchema value);

} // Diagram
