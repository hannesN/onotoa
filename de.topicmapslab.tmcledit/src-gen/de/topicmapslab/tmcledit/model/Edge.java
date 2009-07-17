/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Edge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edge#getBendpoints <em>Bendpoints</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edge#getSource <em>Source</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edge#getTarget <em>Target</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edge#getType <em>Type</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edge#getRoleConstraint <em>Role Constraint</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.Edge#getLabelPositions <em>Label Positions</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdge()
 * @model
 * @generated
 */
public interface Edge extends EObject {
	/**
	 * Returns the value of the '<em><b>Bendpoints</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.Bendpoints}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Bendpoints</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Bendpoints</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdge_Bendpoints()
	 * @model containment="true"
	 * @generated
	 */
	EList<Bendpoints> getBendpoints();

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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdge_Source()
	 * @model required="true"
	 * @generated
	 */
	Node getSource();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.Edge#getSource <em>Source</em>}' reference.
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
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdge_Target()
	 * @model required="true"
	 * @generated
	 */
	Node getTarget();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.Edge#getTarget <em>Target</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Target</em>' reference.
	 * @see #getTarget()
	 * @generated
	 */
	void setTarget(Node value);

	/**
	 * Returns the value of the '<em><b>Type</b></em>' attribute.
	 * The literals are from the enumeration {@link de.topicmapslab.tmcledit.model.EdgeType}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Type</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Type</em>' attribute.
	 * @see de.topicmapslab.tmcledit.model.EdgeType
	 * @see #setType(EdgeType)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdge_Type()
	 * @model required="true"
	 * @generated
	 */
	EdgeType getType();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.Edge#getType <em>Type</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Type</em>' attribute.
	 * @see de.topicmapslab.tmcledit.model.EdgeType
	 * @see #getType()
	 * @generated
	 */
	void setType(EdgeType value);

	/**
	 * Returns the value of the '<em><b>Role Constraint</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Role Constraint</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Role Constraint</em>' reference.
	 * @see #setRoleConstraint(RolePlayerConstraint)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdge_RoleConstraint()
	 * @model
	 * @generated
	 */
	RolePlayerConstraint getRoleConstraint();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.Edge#getRoleConstraint <em>Role Constraint</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Role Constraint</em>' reference.
	 * @see #getRoleConstraint()
	 * @generated
	 */
	void setRoleConstraint(RolePlayerConstraint value);

	/**
	 * Returns the value of the '<em><b>Label Positions</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.LabelPos}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Label Positions</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Label Positions</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getEdge_LabelPositions()
	 * @model containment="true" upper="2"
	 * @generated
	 */
	EList<LabelPos> getLabelPositions();

} // Edge
