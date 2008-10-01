/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package de.topicmapslab.tmcledit.diagram.model;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Edge Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see de.topicmapslab.tmcledit.diagram.model.ModelPackage#getEdgeType()
 * @model
 * @generated
 */
public enum EdgeType implements Enumerator {
	/**
	 * The '<em><b>IS ATYPE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #IS_ATYPE_VALUE
	 * @generated
	 * @ordered
	 */
	IS_ATYPE(0, "IS_A_TYPE", "IS_A_TYPE"),

	/**
	 * The '<em><b>AKO TYPE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #AKO_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	AKO_TYPE(1, "AKO_TYPE", "AKO_TYPE"),

	/**
	 * The '<em><b>ROLE CONSTRAINT TYPE</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ROLE_CONSTRAINT_TYPE_VALUE
	 * @generated
	 * @ordered
	 */
	ROLE_CONSTRAINT_TYPE(2, "ROLE_CONSTRAINT_TYPE", "ROLE_CONSTRAINT_TYPE");

	/**
	 * The '<em><b>IS ATYPE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>IS ATYPE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #IS_ATYPE
	 * @model name="IS_A_TYPE"
	 * @generated
	 * @ordered
	 */
	public static final int IS_ATYPE_VALUE = 0;

	/**
	 * The '<em><b>AKO TYPE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>AKO TYPE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #AKO_TYPE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int AKO_TYPE_VALUE = 1;

	/**
	 * The '<em><b>ROLE CONSTRAINT TYPE</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of '<em><b>ROLE CONSTRAINT TYPE</b></em>' literal object isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @see #ROLE_CONSTRAINT_TYPE
	 * @model
	 * @generated
	 * @ordered
	 */
	public static final int ROLE_CONSTRAINT_TYPE_VALUE = 2;

	/**
	 * An array of all the '<em><b>Edge Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final EdgeType[] VALUES_ARRAY =
		new EdgeType[] {
			IS_ATYPE,
			AKO_TYPE,
			ROLE_CONSTRAINT_TYPE,
		};

	/**
	 * A public read-only list of all the '<em><b>Edge Type</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<EdgeType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Edge Type</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EdgeType get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EdgeType result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Edge Type</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EdgeType getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			EdgeType result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Edge Type</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static EdgeType get(int value) {
		switch (value) {
			case IS_ATYPE_VALUE: return IS_ATYPE;
			case AKO_TYPE_VALUE: return AKO_TYPE;
			case ROLE_CONSTRAINT_TYPE_VALUE: return ROLE_CONSTRAINT_TYPE;
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
	private EdgeType(int value, String name, String literal) {
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
	
} //EdgeType
