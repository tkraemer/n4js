/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package eu.numberfour.n4js.ts.types;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Null Modifier</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see eu.numberfour.n4js.ts.types.TypesPackage#getNullModifier()
 * @model
 * @generated
 */
public enum NullModifier implements Enumerator {
	/**
	 * The '<em><b>Na</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NA_VALUE
	 * @generated
	 * @ordered
	 */
	NA(0, "na", "na"),

	/**
	 * The '<em><b>Nullable</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NULLABLE_VALUE
	 * @generated
	 * @ordered
	 */
	NULLABLE(1, "nullable", "nullable"),

	/**
	 * The '<em><b>Notnull</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #NOTNULL_VALUE
	 * @generated
	 * @ordered
	 */
	NOTNULL(2, "notnull", "notnull"),

	/**
	 * The '<em><b>Isnull</b></em>' literal object.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #ISNULL_VALUE
	 * @generated
	 * @ordered
	 */
	ISNULL(3, "isnull", "isnull");

	/**
	 * The '<em><b>Na</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Not available: no null modifier available, actual value could be null or not, but no warnings are to be issued.
	 * This is the default value.
	 * <!-- end-model-doc -->
	 * @see #NA
	 * @model name="na"
	 * @generated
	 * @ordered
	 */
	public static final int NA_VALUE = 0;

	/**
	 * The '<em><b>Nullable</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Actual value could be null.
	 * <!-- end-model-doc -->
	 * @see #NULLABLE
	 * @model name="nullable"
	 * @generated
	 * @ordered
	 */
	public static final int NULLABLE_VALUE = 1;

	/**
	 * The '<em><b>Notnull</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Actual value must no be null.
	 * <!-- end-model-doc -->
	 * @see #NOTNULL
	 * @model name="notnull"
	 * @generated
	 * @ordered
	 */
	public static final int NOTNULL_VALUE = 2;

	/**
	 * The '<em><b>Isnull</b></em>' literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * <!-- begin-model-doc -->
	 * *
	 * Actual value must is null. This value could only be inferred and never declared explicitly.
	 * <!-- end-model-doc -->
	 * @see #ISNULL
	 * @model name="isnull"
	 * @generated
	 * @ordered
	 */
	public static final int ISNULL_VALUE = 3;

	/**
	 * An array of all the '<em><b>Null Modifier</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	private static final NullModifier[] VALUES_ARRAY =
		new NullModifier[] {
			NA,
			NULLABLE,
			NOTNULL,
			ISNULL,
		};

	/**
	 * A public read-only list of all the '<em><b>Null Modifier</b></em>' enumerators.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static final List<NullModifier> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

	/**
	 * Returns the '<em><b>Null Modifier</b></em>' literal with the specified literal value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param literal the literal.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NullModifier get(String literal) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NullModifier result = VALUES_ARRAY[i];
			if (result.toString().equals(literal)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Null Modifier</b></em>' literal with the specified name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param name the name.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NullModifier getByName(String name) {
		for (int i = 0; i < VALUES_ARRAY.length; ++i) {
			NullModifier result = VALUES_ARRAY[i];
			if (result.getName().equals(name)) {
				return result;
			}
		}
		return null;
	}

	/**
	 * Returns the '<em><b>Null Modifier</b></em>' literal with the specified integer value.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the integer value.
	 * @return the matching enumerator or <code>null</code>.
	 * @generated
	 */
	public static NullModifier get(int value) {
		switch (value) {
			case NA_VALUE: return NA;
			case NULLABLE_VALUE: return NULLABLE;
			case NOTNULL_VALUE: return NOTNULL;
			case ISNULL_VALUE: return ISNULL;
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
	private NullModifier(int value, String name, String literal) {
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
	
} //NullModifier
