/**
 * (C) 2008 Hannes Niederhause, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Scoped Topic Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.ScopedTopicType#getScope <em>Scope</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getScopedTopicType()
 * @model abstract="true"
 * @generated
 */
public interface ScopedTopicType extends TopicType {
	/**
	 * Returns the value of the '<em><b>Scope</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.ScopeConstraint}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Scope</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Scope</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getScopedTopicType_Scope()
	 * @model containment="true"
	 * @generated
	 */
	EList<ScopeConstraint> getScope();

} // ScopedTopicType
