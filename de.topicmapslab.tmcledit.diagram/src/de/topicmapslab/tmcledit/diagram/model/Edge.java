/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.Edge#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.Edge#getType <em>Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.Edge#getSource <em>Source</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.diagram.model.Edge#getTarget <em>Target</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getEdge()
 * @model
 * @generated
 */
public interface Edge extends EObject {
	/**
	 * Returns the value of the '<em><b>Bendpoints</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bendpoints</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bendpoints</em>' containment reference.
	 * @see #setBendpoints(BendPoint)
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getEdge_Bendpoints()
	 * @model containment="true"
	 * @generated
	 */
	BendPoint getBendpoints();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getBendpoints <em>Bendpoints</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Bendpoints</em>' containment reference.
	 * @see #getBendpoints()
	 * @generated
	 */
	void setBendpoints(BendPoint value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.topicmapslab.tmcledit.diagram.model.EdgeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.topicmapslab.tmcledit.diagram.model.EdgeType
	 * @see #setType(EdgeType)
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getEdge_Type()
	 * @model required="true"
	 * @generated
	 */
	EdgeType getType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.topicmapslab.tmcledit.diagram.model.EdgeType
	 * @see #getType()
	 * @generated
	 */
	void setType(EdgeType value);

	/**
	 * Returns the value of the '<em><b>Source</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Source</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Source</em>' reference.
	 * @see #setSource(Node)
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getEdge_Source()
	 * @model required="true"
	 * @generated
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getSource <em>Source</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Source</em>' reference.
	 * @see #getSource()
	 * @generated
	 */
	void setSource(Node value);

	/**
	 * Returns the value of the '<em><b>Target</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Target</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Target</em>' reference.
	 * @see #setTarget(Node)
	 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getEdge_Target()
	 * @model required="true"
	 * @generated
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.diagram.model.Edge#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);

} // Edge
