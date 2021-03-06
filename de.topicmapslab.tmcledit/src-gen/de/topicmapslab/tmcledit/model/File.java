/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>File</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link de.topicmapslab.tmcledit.model.File#getDiagrams <em>Diagrams</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.File#getTopicMapSchema <em>Topic Map Schema</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.File#getFilename <em>Filename</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.File#isDirty <em>Dirty</em>}</li>
 *   <li>{@link de.topicmapslab.tmcledit.model.File#getNotes <em>Notes</em>}</li>
 * </ul>
 * </p>
 *
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getFile()
 * @model
 * @generated
 */
public interface File extends OnoObject {
	/**
	 * Returns the value of the '<em><b>Diagrams</b></em>' containment reference list.
	 * The list contents are of type {@link de.topicmapslab.tmcledit.model.Diagram}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Diagrams</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Diagrams</em>' containment reference list.
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getFile_Diagrams()
	 * @model containment="true"
	 * @generated
	 */
	EList<Diagram> getDiagrams();

	/**
	 * Returns the value of the '<em><b>Topic Map Schema</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Topic Map Schema</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Topic Map Schema</em>' containment reference.
	 * @see #setTopicMapSchema(TopicMapSchema)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getFile_TopicMapSchema()
	 * @model containment="true" required="true"
	 * @generated
	 */
	TopicMapSchema getTopicMapSchema();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.File#getTopicMapSchema <em>Topic Map Schema</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Topic Map Schema</em>' containment reference.
	 * @see #getTopicMapSchema()
	 * @generated
	 */
	void setTopicMapSchema(TopicMapSchema value);

	/**
	 * Returns the value of the '<em><b>Filename</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Filename</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Filename</em>' attribute.
	 * @see #setFilename(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getFile_Filename()
	 * @model transient="true"
	 * @generated
	 */
	String getFilename();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.File#getFilename <em>Filename</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Filename</em>' attribute.
	 * @see #getFilename()
	 * @generated
	 */
	void setFilename(String value);

	/**
	 * Returns the value of the '<em><b>Dirty</b></em>' attribute.
	 * The default value is <code>"false"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Dirty</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Dirty</em>' attribute.
	 * @see #setDirty(boolean)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getFile_Dirty()
	 * @model default="false" required="true" transient="true"
	 * @generated
	 */
	boolean isDirty();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.File#isDirty <em>Dirty</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Dirty</em>' attribute.
	 * @see #isDirty()
	 * @generated
	 */
	void setDirty(boolean value);

	/**
	 * Returns the value of the '<em><b>Notes</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Notes</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Notes</em>' attribute.
	 * @see #setNotes(String)
	 * @see de.topicmapslab.tmcledit.model.ModelPackage#getFile_Notes()
	 * @model
	 * @generated
	 */
	String getNotes();

	/**
	 * Sets the value of the '{@link de.topicmapslab.tmcledit.model.File#getNotes <em>Notes</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Notes</em>' attribute.
	 * @see #getNotes()
	 * @generated
	 */
	void setNotes(String value);

} // File
