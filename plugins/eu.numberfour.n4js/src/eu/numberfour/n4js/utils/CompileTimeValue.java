/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.utils;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.postprocessing.ASTMetaInfoCache;
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope;
import eu.numberfour.n4js.ts.scoping.builtin.N4Scheme;
import eu.numberfour.n4js.ts.types.TField;
import eu.numberfour.n4js.ts.types.TObjectPrototype;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * The value of a compile-time expression as returned by {@link ASTMetaInfoCache#getCompileTimeValue(Expression)} and
 * computed by {@link CompileTimeEvaluator#evaluateCompileTimeExpression(RuleEnvironment, Expression)}.
 * <p>
 * Such a compile-time value can be valid or invalid, see {@link #isValid()}, represented by the subclasses
 * {@link ValueValid} and {@link ValueInvalid}, respectively. Valid values are represented by the concrete subclasses
 * <ul>
 * <li>{@link ValueBoolean}
 * <li>{@link ValueString}
 * <li>{@link ValueNumber}
 * <li>{@link ValueSymbol}
 * </ul>
 * which all inherit from {@code ValueValid}.
 * <p>
 * Operations on compile-time values are implemented by a number of static utility methods in this class, e.g
 * {@link #multiply(CompileTimeValue, CompileTimeValue, EObject, EObject)}.
 */
public abstract class CompileTimeValue {

	/** Representation of the Javascript value <code>undefined</code>. */
	public static final CompileTimeValue UNDEFINED = new ValueValid("undefined") {
		// no adjustments required
	};

	/** Representation of the Javascript value <code>null</code>. */
	public static final CompileTimeValue NULL = new ValueValid("null") {
		// no adjustments required
	};

	/** Representation of the Javascript value <code>true</code>. */
	public static final ValueBoolean TRUE = new ValueBoolean(true);

	/** Representation of the Javascript value <code>false</code>. */
	public static final ValueBoolean FALSE = new ValueBoolean(false);

	/**
	 * An error during evaluation of compile-time expressions.
	 */
	public static class EvalError {
		/** Message for this error; never <code>null</code>. */
		public final String message;
		/** Optional AST node where this error occurred; may be <code>null</code>. */
		public final EObject astNode;
		/** Optional {@link EStructuralFeature feature} where this error occurred; may be <code>null</code>. */
		public final EStructuralFeature feature;

		/**
		 * @param message
		 *            message for this error; must not be <code>null</code>.
		 * @param astNode
		 *            optional AST node where this error occurred; may be <code>null</code>.
		 * @param feature
		 *            optional {@link EStructuralFeature feature} where this error occurred; may be <code>null</code>.
		 */
		public EvalError(String message, EObject astNode, EStructuralFeature feature) {
			Objects.requireNonNull(message);
			this.message = message;
			this.astNode = astNode;
			this.feature = feature;
		}

		/**
		 * Returns this error's message with a suffix explaining where the error occurred if {@link #astNode} is given.
		 * This method ignores field {@link #feature}.
		 */
		public String getMessageWithLocation() {
			if (astNode == null) {
				return message;
			}
			final INode node = NodeModelUtils.findActualNodeFor(astNode);
			final String tokenText = node != null ? NodeModelUtils.getTokenText(node) : null;
			if (tokenText == null) {
				return message;
			}
			return message + " at \"" + tokenText + "\"";
		}
	}

	/** Represents an invalid {@link CompileTimeValue}. */
	public static final class ValueInvalid extends CompileTimeValue {
		private final EvalError[] errors;

		/**
		 * @param errors
		 *            zero or more evaluation errors, see {@link EvalError}.
		 */
		public ValueInvalid(EvalError... errors) {
			Objects.requireNonNull(errors);
			this.errors = Arrays.copyOf(errors, errors.length);
		}

		/**
		 * This method may return one or more {@link EvalError}s with details why the evaluated expression was not a
		 * valid compile-time expression. Providing such evaluation errors is, however, optional, so an empty list may
		 * be returned.
		 */
		public List<EvalError> getErrors() {
			return Collections.unmodifiableList(Arrays.asList(errors));
		}

		@Override
		public boolean isValid() {
			return false;
		}
	}

	/** Common base class for classes representing valid {@link CompileTimeValue}s. */
	public static abstract class ValueValid extends CompileTimeValue {
		private final Object value;

		private ValueValid(Object value) {
			Objects.requireNonNull(value);
			this.value = value;
		}

		@Override
		public boolean isValid() {
			return true;
		}

		/**
		 * Returns the corresponding JVM representation of this valid compile-time value; never returns
		 * <code>null</code>. For example, when invoked on an instance of a {@link CompileTimeValue} representing the
		 * Javascript string <code>'example'</code>, this method will return <code>"example"</code> as a JVM string
		 * instance. Numbers are represented as {@link BigDecimal}s.
		 * <p>
		 * IMPORTANT: not all Javascript values have a corresponding JVM representation, e.g. <code>undefined</code>! In
		 * these cases, this method will return an object that identifies the value only(!!) within the set of
		 * compile-time values of same type (i.e. same subclass). Therefore, client code should never use the value
		 * returned by this method without knowing beforehand which subclass of {@link ValueValid} it is dealing with.
		 */
		public Object getValue() {
			return value;
		}

		@Override
		public int hashCode() {
			return value.hashCode();
		}

		@Override
		public boolean equals(Object obj) {
			return obj != null && obj.getClass() == getClass() && value.equals(((ValueValid) obj).value);
		}

		@Override
		public String toString() {
			return value.toString();
		}
	}

	/** Represents a compile-time value of type boolean. */
	public static final class ValueBoolean extends ValueValid {
		private ValueBoolean(Boolean value) {
			super(value);
		}

		/** Returns the native {@link Boolean} corresponding to this boolean compile-time value. */
		@Override
		public Boolean getValue() {
			return (Boolean) super.getValue();
		}

		/** Returns inverted copy of this value. */
		public ValueBoolean invert() {
			return (ValueBoolean) of(!getValue());
		}
	}

	/** Represents a compile-time value of type string. */
	public static final class ValueString extends ValueValid {
		private ValueString(String value) {
			super(value);
		}

		/** Returns the native {@link String} corresponding to this compile-time value of type string. */
		@Override
		public String getValue() {
			return (String) super.getValue();
		}
	}

	/** Represents a compile-time value of type number. */
	public static final class ValueNumber extends ValueValid {
		private ValueNumber(BigDecimal value) {
			// important: need to normalize the BigDecimal here, otherwise 2.0 != 2.00
			// (cannot use #compareTo() in an equals() method, because then hash codes would be incorrect)
			super(value.stripTrailingZeros());
		}

		/** Returns the native {@link BigDecimal} corresponding to this compile-time value of type number. */
		@Override
		public BigDecimal getValue() {
			return (BigDecimal) super.getValue();
		}

		/** Tells if this value is equal to 0. */
		public boolean isZero() {
			return getValue().compareTo(BigDecimal.ZERO) == 0;
		}

		/** Returns negated copy of this value. */
		public ValueNumber negate() {
			return (ValueNumber) of(getValue().negate());
		}
	}

	/** Represents a compile-time value of type symbol. */
	public static final class ValueSymbol extends ValueValid {
		private ValueSymbol(String symbolName) {
			super(symbolName);
		}

		/** Returns the symbol's plain name (without {@link N4JSLanguageUtils#SYMBOL_IDENTIFIER_PREFIX}). */
		@Override
		public String getValue() {
			return (String) super.getValue();
		}

		/** Returns the symbol's name prefixed by {@link N4JSLanguageUtils#SYMBOL_IDENTIFIER_PREFIX}. */
		@Override
		public String toString() {
			return N4JSLanguageUtils.SYMBOL_IDENTIFIER_PREFIX + getValue();
		}
	}

	// private constructor to disallow sub-classing outside of this file
	private CompileTimeValue() {
	}

	/**
	 * Tells whether the receiving compile-time value is valid, i.e. if it represents the result of an evaluation of a
	 * valid compile-time expression. This is equivalent to an <code>instanceof</code>-check whether the receiving
	 * compile-time value is an instance of {@link ValueValid} / not an instance of {@link ValueInvalid}.
	 * <p>
	 * If this method returns <code>true</code>, method {@link ValueValid#getValue()} can be invoked to obtain the JVM
	 * representation of the value; if it returns <code>false</code>, method {@link ValueInvalid#getErrors()} can be
	 * invoked to obtain error messages.
	 */
	public abstract boolean isValid();

	// ################################################################################################################
	// STATIC UTILITY METHODS FOR HANDLING COMPILE-TIME VALUES

	/** Create an invalid compile-time value without any error information. */
	public static ValueInvalid error() {
		return new ValueInvalid();
	}

	/** Create an invalid compile-time value with the given error message. */
	public static ValueInvalid error(String message) {
		return error(message, null, null);
	}

	/** Create an invalid compile-time value with the given error message for the given location in the AST. */
	public static ValueInvalid error(String message, EObject astNode) {
		return error(message, astNode, null);
	}

	/** Create an invalid compile-time value with the given error message for the given location in the AST. */
	public static ValueInvalid error(String message, EObject astNode, EStructuralFeature feature) {
		Objects.requireNonNull(message);
		return error(new EvalError(message, astNode, feature));
	}

	/** Create an invalid compile-time value with the given errors. */
	public static ValueInvalid error(EvalError... errors) {
		return new ValueInvalid(errors);
	}

	/**
	 * Convenience method creating a compile-time value for the given JVM value.
	 */
	public static CompileTimeValue of(Object value) {
		if (value instanceof CompileTimeValue) {
			return (CompileTimeValue) value;
		} else if (value instanceof Boolean) {
			return of((Boolean) value);
		} else if (value instanceof String) {
			return of((String) value);
		} else if (value instanceof Number) {
			return of((Number) value);
		} else if (value instanceof BigDecimal) {
			return of((BigDecimal) value);
		} else if (value instanceof TField) {
			return of((TField) value);
		}
		return error();
	}

	/** Creates a compile-time value for the given {@link Boolean}. */
	public static CompileTimeValue of(Boolean value) {
		return value != null ? (value ? TRUE : FALSE) : error();
	}

	/** Creates a compile-time value for the given {@link String}. */
	public static CompileTimeValue of(String value) {
		return value != null ? new ValueString(value) : error();
	}

	/** Creates a compile-time value for the given {@link Number}. */
	public static CompileTimeValue of(Number value) {
		return value != null ? of(BigDecimal.valueOf(value.doubleValue())) : error();
	}

	/** Creates a compile-time value for the given {@link BigDecimal}. */
	public static CompileTimeValue of(BigDecimal value) {
		return value != null ? new ValueNumber(value) : error();
	}

	/**
	 * Creates a compile-time value representing a built-in symbol for the given {@link TField} which is expected to be
	 * a field of built-in object type <code>Symbol</code>, i.e. the type returned from
	 * {@link BuiltInTypeScope#getSymbolObjectType()}.
	 */
	public static CompileTimeValue of(TField value) {
		if (value != null) {
			final EObject parent = value.eContainer();
			if (!(parent instanceof TObjectPrototype
					&& "Symbol".equals(((TObjectPrototype) parent).getName())
					&& N4Scheme.isFromResourceWithN4Scheme(parent))) {
				throw new IllegalArgumentException("given TField does not represent a built-in symbol");
			}
			return new ValueSymbol(value.getName());
		}
		return error();
	}

	/**
	 * Inverts the given boolean value. Returns a {@link ValueInvalid} in case of errors. Never returns
	 * <code>null</code>.
	 *
	 * @param astNode
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue invert(CompileTimeValue value, EObject astNode) {
		final ValueInvalid error = requireValueType(value, ValueBoolean.class, "operand must be a boolean", astNode);
		if (error != null) {
			return error;
		}
		return ((ValueBoolean) value).invert();
	}

	/**
	 * Logical conjunction of the given boolean values. Returns a {@link ValueInvalid} in case of errors. Never returns
	 * <code>null</code>.
	 *
	 * @param astNodeLeft
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 * @param astNodeRight
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue and(CompileTimeValue valueLeft, CompileTimeValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueBoolean.class, "left operand must be a boolean", astNodeLeft),
				requireValueType(valueRight, ValueBoolean.class, "right operand must be a boolean", astNodeRight));
		if (error != null) {
			return error;
		}
		return CompileTimeValue.of(
				((ValueBoolean) valueLeft).getValue() && ((ValueBoolean) valueRight).getValue());
	}

	/**
	 * Logical disjunction of the given boolean values. Returns a {@link ValueInvalid} in case of errors. Never returns
	 * <code>null</code>.
	 *
	 * @param astNodeLeft
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 * @param astNodeRight
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue or(CompileTimeValue valueLeft, CompileTimeValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueBoolean.class, "left operand must be a boolean", astNodeLeft),
				requireValueType(valueRight, ValueBoolean.class, "right operand must be a boolean", astNodeRight));
		if (error != null) {
			return error;
		}
		return CompileTimeValue.of(
				((ValueBoolean) valueLeft).getValue() || ((ValueBoolean) valueRight).getValue());
	}

	/**
	 * Numeric negation of the given numeric value. Returns a {@link ValueInvalid} in case of errors. Never returns
	 * <code>null</code>.
	 *
	 * @param astNode
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue negate(CompileTimeValue value, EObject astNode) {
		final ValueInvalid error = requireValueType(value, ValueNumber.class, "operand must be a number", astNode);
		if (error != null) {
			return error;
		}
		return ((ValueNumber) value).negate();
	}

	/**
	 * Numeric addition (if both values are numeric) or string concatenation of the given values. Returns a
	 * {@link ValueInvalid} in case of errors. Never returns <code>null</code>.
	 *
	 * @param astNode
	 *            location of the addition in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue add(CompileTimeValue valueLeft, CompileTimeValue valueRight, EObject astNode) {
		if (valueLeft == null || !valueLeft.isValid() || valueRight == null || !valueRight.isValid()) {
			return combineErrors(
					error(), // required to pass in error(), because valueLeft and valueRight might both be 'null'
					valueLeft, valueRight);
		} else if (valueLeft instanceof ValueNumber && valueRight instanceof ValueNumber) {
			return of(((ValueNumber) valueLeft).getValue().add(((ValueNumber) valueRight).getValue()));
		} else if (valueLeft.isValid() && valueRight.isValid()
				&& (valueLeft instanceof ValueString || valueRight instanceof ValueString)) {
			return of(valueLeft.toString() + valueRight.toString());
		} else {
			return error("one of the operands must be a string or both must be a number", astNode);
		}
	}

	/**
	 * Subtraction of the given numeric values. Returns a {@link ValueInvalid} in case of errors. Never returns
	 * <code>null</code>.
	 *
	 * @param astNodeLeft
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 * @param astNodeRight
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue subtract(CompileTimeValue valueLeft, CompileTimeValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		return of(((ValueNumber) valueLeft).getValue().subtract(((ValueNumber) valueRight).getValue()));
	}

	/**
	 * Multiplication of the given numeric values. Returns a {@link ValueInvalid} in case of errors. Never returns
	 * <code>null</code>.
	 *
	 * @param astNodeLeft
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 * @param astNodeRight
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue multiply(CompileTimeValue valueLeft, CompileTimeValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		return of(((ValueNumber) valueLeft).getValue().multiply(((ValueNumber) valueRight).getValue()));
	}

	/**
	 * Division of the given numeric values. Returns a {@link ValueInvalid} in case of errors. Never returns
	 * <code>null</code>.
	 *
	 * @param astNodeLeft
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 * @param astNodeRight
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue divide(CompileTimeValue valueLeft, CompileTimeValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		if (((ValueNumber) valueRight).isZero()) {
			return CompileTimeValue.error("division by zero not allowed in compile-time expressions", astNodeRight);
		}
		return of(((ValueNumber) valueLeft).getValue().divide(((ValueNumber) valueRight).getValue()));
	}

	/**
	 * {@link BigDecimal#remainder(BigDecimal) Modulo} of the given numeric values. Returns a {@link ValueInvalid} in
	 * case of errors. Never returns <code>null</code>.
	 *
	 * @param astNodeLeft
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 * @param astNodeRight
	 *            location of the operand in the AST (for error messages) or <code>null</code> if not applicable.
	 */
	public static CompileTimeValue remainder(CompileTimeValue valueLeft, CompileTimeValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		if (((ValueNumber) valueRight).isZero()) {
			return CompileTimeValue.error("division by zero not allowed in compile-time expressions", astNodeRight);
		}
		return of(((ValueNumber) valueLeft).getValue().remainder(((ValueNumber) valueRight).getValue()));
	}

	/**
	 * Utility method to easily pass invalid values through a method implementing a computation on two or more
	 * compile-time values, by combining the errors of all "incoming" invalid values into a single invalid value.
	 * <p>
	 * More precisely: given an array of zero or more compile-time values, this method returns ...
	 * <ul>
	 * <li>a single invalid value containing all {@link EvalError}s from all invalid values among the given array of
	 * compile-time values, if that array contains at least one invalid value.
	 * <li><code>null</code>, in case none of the given compile-time values was invalid (including the case that no
	 * compile-time values were given).
	 * </ul>
	 * Ignores <code>null</code> values in the given array of compile-time values.
	 *
	 * @see #requireValueType(CompileTimeValue, Class, String, EObject)
	 */
	public static ValueInvalid combineErrors(CompileTimeValue... values) {
		boolean foundInvalid = false;
		final List<EvalError> errors = new LinkedList<>();
		for (CompileTimeValue currValue : values) {
			if (currValue != null && !currValue.isValid()) {
				foundInvalid = true;
				errors.addAll(((ValueInvalid) currValue).getErrors());
			}
		}
		return foundInvalid ? new ValueInvalid(errors.toArray(new EvalError[errors.size()])) : null;
	}

	/**
	 * Ensures that the given compile-time values if of a particular type. If the given value is valid AND is of correct
	 * type, <code>null</code> is returned; if the given value is already invalid, it is simply passed through;
	 * otherwise (i.e. given value is valid but of incorrect type), a new {@link ValueInvalid} is returned with the
	 * given error message and AST node.
	 * <p>
	 * This method is intended to be used together with {@link #combineErrors(CompileTimeValue...)}.
	 *
	 * @param value
	 *            the value to check.
	 * @param type
	 *            the expected type.
	 * @param message
	 *            an error message or <code>null</code>.
	 * @param astNode
	 *            an AST node denoting where the error should occur in the code or <code>null</code>.
	 * @return null if a non-null, valid compile-time value of correct type was given; a {@link ValueInvalid} otherwise.
	 *
	 * @see #combineErrors(CompileTimeValue...)
	 */
	public static ValueInvalid requireValueType(CompileTimeValue value, Class<? extends ValueValid> type,
			String message, EObject astNode) {
		if (value == null) {
			// probably due to broken AST
			return error("missing value", astNode); // in most cases astNode will be 'null', but that won't do any harm
		} else if (value instanceof ValueInvalid) {
			// value is already invalid -> simply pass through
			return (ValueInvalid) value;
		} else if (value.getClass() != type) {
			// value is of wrong type -> create new error
			return message != null && astNode != null ? error(message, astNode) : error();
		}
		// value is of correct type -> no error created
		return null;
	}

	private static final String INVALID_VALUE_PREFIX = "invalid\n";

	/**
	 * Serialize the given compile-time value into a string which can later be deserialized using method
	 * {@link #deserialize(String)}.
	 * <p>
	 * It is legal to (de)serialize {@link ValueInvalid invalid values}. However, in case of {@link EvalError}s with a
	 * defined AST node, the AST node will become part of the error message (because the code that will perform the
	 * deserialization will, in most cases, not have access to the AST anyway).
	 */
	public static String serialize(CompileTimeValue value) {
		if (value == null) {
			return null;
		} else if (!value.isValid()) {
			return INVALID_VALUE_PREFIX + ((ValueInvalid) value).getErrors().stream()
					.map(err -> err.getMessageWithLocation())
					.collect(Collectors.joining("\n"));
		} else if (value == UNDEFINED || value == NULL) {
			return value.toString();
		} else if (value instanceof ValueBoolean) {
			return "?" + value;
		} else if (value instanceof ValueString) {
			return "\"" + value;
		} else if (value instanceof ValueNumber) {
			return "#" + value;
		} else if (value instanceof ValueSymbol) {
			return "$" + ((ValueSymbol) value).getValue();
		} else {
			throw new UnsupportedOperationException("unknown subclass of CompileTimeValue: " + value);
		}
	}

	/**
	 * Deserialize the given string into a compile-time value. The given string is expected to be of the format produced
	 * by method {@link #serialize(CompileTimeValue)}.
	 * <p>
	 * For details about the serialization of invalid values, see {@link #serialize(CompileTimeValue)}.
	 */
	public static CompileTimeValue deserialize(String str) {
		if (str == null) {
			return error();
		} else if (str.startsWith(INVALID_VALUE_PREFIX)) {
			final String[] errorMessages = str.substring(INVALID_VALUE_PREFIX.length()).split("\n");
			final EvalError[] errors = new EvalError[errorMessages.length];
			for (int i = 0; i < errorMessages.length; i++) {
				errors[i] = new EvalError(errorMessages[i], null, null);
			}
			return error(errors);
		} else if (UNDEFINED.toString().equals(str)) {
			return UNDEFINED;
		} else if (NULL.toString().equals(str)) {
			return NULL;
		} else {
			if (str.length() > 0) {
				final char head = str.charAt(0);
				final String tail = str.substring(1);
				if (head == '?' && "true".equalsIgnoreCase(tail)) {
					return TRUE;
				} else if (head == '?' && "false".equalsIgnoreCase(tail)) {
					return FALSE;
				} else if (head == '"') {
					return of(tail);
				} else if (head == '#') {
					return new ValueNumber(new BigDecimal(tail));
				} else if (head == '$') {
					return new ValueSymbol(tail);
				}
			}
			throw new UnsupportedOperationException("cannot deserialize CompileTimeValue from string: " + str);
		}
	}
}
