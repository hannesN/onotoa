/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Topic Id</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getTopicId()
 * @model
 * @generated
 */
public enum TopicId implements Enumerator {
	/**
	 * The '<em><b>SUBJECT LOCATOR</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUBJECT_LOCATOR_VALUE
	 * @generated
	 * @ordered
	 */
	SUBJECT_LOCATOR(0, "SUBJECT_LOCATOR", "SUBJECT_LOCATOR"),

	/**
	 * The '<em><b>SUBJECT IDENTIFIER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SUBJECT_IDENTIFIER_VALUE
	 * @generated
	 * @ordered
	 */
	SUBJECT_IDENTIFIER(1, "SUBJECT_IDENTIFIER", "SUBJECT_IDENTIFIER"),

	/**
	 * The '<em><b>ITEM IDENTIFIER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ITEM_IDENTIFIER_VALUE
	 * @generated
	 * @ordered
	 */
	ITEM_IDENTIFIER(2, "ITEM_IDENTIFIER", "ITEM_IDENTIFIER"),

	/**
	 * The '<em><b>IDENTIFIER</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IDENTIFIER_VALUE
	 * @generated
	 * @ordered
	 */
	IDENTIFIER(3, "IDENTIFIER", "IDENTIFIER");

	/**
	 * The '<em><b>SUBJECT LOCATOR</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SUBJECT LOCATOR</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUBJECT_LOCATOR
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUBJECT_LOCATOR_VALUE = 0;

	/**
	 * The '<em><b>SUBJECT IDENTIFIER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>SUBJECT IDENTIFIER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SUBJECT_IDENTIFIER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int SUBJECT_IDENTIFIER_VALUE = 1;

	/**
	 * The '<em><b>ITEM IDENTIFIER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ITEM IDENTIFIER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ITEM_IDENTIFIER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ITEM_IDENTIFIER_VALUE = 2;

	/**
	 * The '<em><b>IDENTIFIER</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IDENTIFIER</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IDENTIFIER
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int IDENTIFIER_VALUE = 3;

	/**
	 * An array of all the '<em><b>Topic Id</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final TopicId[] VALUES_ARRAY =
		new TopicId[] {
			SUBJECT_LOCATOR,
			SUBJECT_IDENTIFIER,
			ITEM_IDENTIFIER,
			IDENTIFIER,
		};

	/**
	 * A public read-only list of all the '<em><b>Topic Id</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<TopicId> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Topic Id</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TopicId get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TopicId result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Topic Id</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TopicId getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			TopicId result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Topic Id</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static TopicId get(int value) {
		switch (value) {
			case SUBJECT_LOCATOR_VALUE: return SUBJECT_LOCATOR;
			case SUBJECT_IDENTIFIER_VALUE: return SUBJECT_IDENTIFIER;
			case ITEM_IDENTIFIER_VALUE: return ITEM_IDENTIFIER;
			case IDENTIFIER_VALUE: return IDENTIFIER;
		}
		return null;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final int value;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String name;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private final String literal;

	/**
	 * Only this class can construct instances.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private TopicId(int value, String name, String literal) {
		this.value = value;
		this.name = name;
		this.literal = literal;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getValue() {
	  return value;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
	  return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getLiteral() {
	  return literal;
	}

	/**
	 * Returns the literal value of the enumerator, which is its string representation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		return literal;
	}
	
} //TopicId
