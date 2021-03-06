/**
 * (C) 2008 Hannes Niederhausen, Topic Maps Lab
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
 * A representation of the literals of the enumeration '<em><b>Kind Of Topic Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.model.ModelPackage#getKindOfTopicType()
 * @model
 * @generated
 */
public enum KindOfTopicType implements Enumerator {
	/**
	 * The '<em><b>Topic Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #TOPIC_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	TOPIC_TYPE(0, "TopicType", "TopicType"),

	/**
	 * The '<em><b>Occurrence Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #OCCURRENCE_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	OCCURRENCE_TYPE(1, "OccurrenceType", "OccurrenceType"),

	/**
	 * The '<em><b>Name Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NAME_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	NAME_TYPE(2, "NameType", "NameType"),

	/**
	 * The '<em><b>Role Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROLE_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	ROLE_TYPE(3, "RoleType", "RoleType"),

	/**
	 * The '<em><b>Association Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ASSOCIATION_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	ASSOCIATION_TYPE(4, "AssociationType", "AssociationType"),

	/**
	 * The '<em><b>Scope Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #SCOPE_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	SCOPE_TYPE(5, "ScopeType", "ScopeType"), /**
	 * The '<em><b>No Type</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NO_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	NO_TYPE(6, "NoType", "NoType");

	/**
	 * The '<em><b>Topic Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Topic Type</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #TOPIC_TYPE
	 * @model name="TopicType"
	 * @generated
	 * @ordered
	 */
	public static final int TOPIC_TYPE_VALUE = 0;

	/**
	 * The '<em><b>Occurrence Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Occurrence Type</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #OCCURRENCE_TYPE
	 * @model name="OccurrenceType"
	 * @generated
	 * @ordered
	 */
	public static final int OCCURRENCE_TYPE_VALUE = 1;

	/**
	 * The '<em><b>Name Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Name Type</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NAME_TYPE
	 * @model name="NameType"
	 * @generated
	 * @ordered
	 */
	public static final int NAME_TYPE_VALUE = 2;

	/**
	 * The '<em><b>Role Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Role Type</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROLE_TYPE
	 * @model name="RoleType"
	 * @generated
	 * @ordered
	 */
	public static final int ROLE_TYPE_VALUE = 3;

	/**
	 * The '<em><b>Association Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Association Type</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ASSOCIATION_TYPE
	 * @model name="AssociationType"
	 * @generated
	 * @ordered
	 */
	public static final int ASSOCIATION_TYPE_VALUE = 4;

	/**
	 * The '<em><b>Scope Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>Scope Type</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #SCOPE_TYPE
	 * @model name="ScopeType"
	 * @generated
	 * @ordered
	 */
	public static final int SCOPE_TYPE_VALUE = 5;

	/**
	 * The '<em><b>No Type</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>No Type</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #NO_TYPE
	 * @model name="NoType"
	 * @generated
	 * @ordered
	 */
	public static final int NO_TYPE_VALUE = 6;

	/**
	 * An array of all the '<em><b>Kind Of Topic Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final KindOfTopicType[] VALUES_ARRAY =
		new KindOfTopicType[] {
			TOPIC_TYPE,
			OCCURRENCE_TYPE,
			NAME_TYPE,
			ROLE_TYPE,
			ASSOCIATION_TYPE,
			SCOPE_TYPE,
			NO_TYPE,
		};

	/**
	 * A public read-only list of all the '<em><b>Kind Of Topic Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<KindOfTopicType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Kind Of Topic Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KindOfTopicType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			KindOfTopicType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Kind Of Topic Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KindOfTopicType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			KindOfTopicType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Kind Of Topic Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static KindOfTopicType get(int value) {
		switch (value) {
			case TOPIC_TYPE_VALUE: return TOPIC_TYPE;
			case OCCURRENCE_TYPE_VALUE: return OCCURRENCE_TYPE;
			case NAME_TYPE_VALUE: return NAME_TYPE;
			case ROLE_TYPE_VALUE: return ROLE_TYPE;
			case ASSOCIATION_TYPE_VALUE: return ASSOCIATION_TYPE;
			case SCOPE_TYPE_VALUE: return SCOPE_TYPE;
			case NO_TYPE_VALUE: return NO_TYPE;
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
	private KindOfTopicType(int value, String name, String literal) {
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
	
} //KindOfTopicType
