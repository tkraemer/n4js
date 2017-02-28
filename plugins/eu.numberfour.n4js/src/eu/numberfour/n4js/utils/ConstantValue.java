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

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.postprocessing.ASTMetaInfoCache;
import eu.numberfour.n4js.ts.types.TField;

/**
 * The value of a compile-time expression as returned by {@link ASTMetaInfoCache#getEvaluationResult(Expression)} and
 * computed by {@code ConstantExpressionProcessor#computeValueIfConstantExpression()}.
 */
public abstract class ConstantValue {

	public static final ConstantValue UNDEFINED = new ValueValid("undefined") {
		// no adjustments required
	};

	public static final ConstantValue NULL = new ValueValid("null") {
		// no adjustments required
	};

	public static final ValueBoolean TRUE = new ValueBoolean(true);

	public static final ValueBoolean FALSE = new ValueBoolean(false);

	public static final class EvalError {
		public final String message;
		public final EObject astNode;
		public final EStructuralFeature feature;

		public EvalError(String message, EObject astNode) {
			this(message, astNode, null);
		}

		public EvalError(String message, EObject astNode, EStructuralFeature feature) {
			this.message = message;
			this.astNode = astNode;
			this.feature = feature;
		}
	}

	public static final class ValueInvalid extends ConstantValue {
		private final EvalError[] errors;

		public ValueInvalid(EvalError... errors) {
			Objects.requireNonNull(errors);
			this.errors = Arrays.copyOf(errors, errors.length);
		}

		public List<EvalError> getErrors() {
			return Collections.unmodifiableList(Arrays.asList(errors));
		}

		@Override
		public boolean isValid() {
			return false;
		}
	}

	public static abstract class ValueValid extends ConstantValue {
		private final Object value;

		private ValueValid(Object value) {
			Objects.requireNonNull(value);
			this.value = value;
		}

		@Override
		public boolean isValid() {
			return true;
		}

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

	public static final class ValueBoolean extends ValueValid {
		private ValueBoolean(Boolean value) {
			super(value);
		}

		@Override
		public Boolean getValue() {
			return (Boolean) super.getValue();
		}

		public ValueBoolean invert() {
			return (ValueBoolean) of(!getValue());
		}
	}

	public static final class ValueString extends ValueValid {
		private ValueString(String value) {
			super(value);
		}

		@Override
		public String getValue() {
			return (String) super.getValue();
		}
	}

	public static final class ValueNumber extends ValueValid {
		private ValueNumber(BigDecimal value) {
			// important: need to normalize the BigDecimal here, otherwise 2.0 != 2.00
			// (cannot use #compareTo() in an equals() method, because then hash codes would be incorrect)
			super(value.stripTrailingZeros());
		}

		@Override
		public BigDecimal getValue() {
			return (BigDecimal) super.getValue();
		}

		public boolean isZero() {
			return getValue().compareTo(BigDecimal.ZERO) == 0;
		}

		public ValueNumber negate() {
			return (ValueNumber) of(getValue().negate());
		}
	}

	public static final class ValueSymbol extends ValueValid {
		private ValueSymbol(String symbolName) {
			super(symbolName);
		}

		/** Returns the symbol's plain name (without {@link N4JSLanguageUtils#SYMBOL_IDENTIFIER_PREFIX}). */
		public String getSymbolName() {
			return super.toString();
		}

		/** Returns the symbol's name prefixed by {@link N4JSLanguageUtils#SYMBOL_IDENTIFIER_PREFIX}. */
		@Override
		public String toString() {
			return N4JSLanguageUtils.SYMBOL_IDENTIFIER_PREFIX + super.toString();
		}
	}

	public abstract boolean isValid();

	// ################################################################################################################
	// STATIC UTILITY METHODS FOR HANDLING COMPILE-TIME VALUES

	public static ValueInvalid error() {
		return new ValueInvalid();
	}

	public static ValueInvalid error(String message, EObject astNode) {
		return new ValueInvalid(new EvalError(message, astNode));
	}

	public static ConstantValue of(Object value) {
		if (value instanceof Boolean) {
			return of((Boolean) value);
		} else if (value instanceof String) {
			return of((String) value);
		} else if (value instanceof Number) {
			return of((Number) value);
		} else if (value instanceof TField) {
			return of((TField) value);
		} else if (value instanceof ConstantValue) {
			return (ConstantValue) value;
		}
		return error();
	}

	public static ConstantValue of(Boolean value) {
		return value != null ? (value ? TRUE : FALSE) : error();
	}

	public static ConstantValue of(String value) {
		return value != null ? new ValueString(value) : error();
	}

	public static ConstantValue of(Number value) {
		return value != null ? of(BigDecimal.valueOf(value.doubleValue())) : error();
	}

	public static ConstantValue of(BigDecimal value) {
		return value != null ? new ValueNumber(value) : error();
	}

	public static ConstantValue of(TField value) {
		return value != null ? new ValueSymbol(value.getName()) : error();
	}

	public static ConstantValue invert(ConstantValue value, EObject astNode) {
		final ValueInvalid error = requireValueType(value, ValueBoolean.class, "operand must be a boolean", astNode);
		if (error != null) {
			return error;
		}
		return ((ValueBoolean) value).invert();
	}

	public static ConstantValue and(ConstantValue valueLeft, ConstantValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueBoolean.class, "left operand must be a boolean", astNodeLeft),
				requireValueType(valueRight, ValueBoolean.class, "right operand must be a boolean", astNodeRight));
		if (error != null) {
			return error;
		}
		return ConstantValue.of(
				((ValueBoolean) valueLeft).getValue() && ((ValueBoolean) valueRight).getValue());
	}

	public static ConstantValue or(ConstantValue valueLeft, ConstantValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueBoolean.class, "left operand must be a boolean", astNodeLeft),
				requireValueType(valueRight, ValueBoolean.class, "right operand must be a boolean", astNodeRight));
		if (error != null) {
			return error;
		}
		return ConstantValue.of(
				((ValueBoolean) valueLeft).getValue() || ((ValueBoolean) valueRight).getValue());
	}

	public static ConstantValue negate(ConstantValue value, EObject astNode) {
		final ValueInvalid error = requireValueType(value, ValueNumber.class, "operand must be a number", astNode);
		if (error != null) {
			return error;
		}
		return ((ValueNumber) value).negate();
	}

	public static ConstantValue add(ConstantValue valueLeft, ConstantValue valueRight, EObject astNode) {
		if (valueLeft==null || !valueLeft.isValid() || valueRight==null || !valueRight.isValid()) {
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

	public static ConstantValue subtract(ConstantValue valueLeft, ConstantValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		return of(((ValueNumber) valueLeft).getValue().subtract(((ValueNumber) valueRight).getValue()));
	}

	public static ConstantValue multiply(ConstantValue valueLeft, ConstantValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		return of(((ValueNumber) valueLeft).getValue().multiply(((ValueNumber) valueRight).getValue()));
	}

	public static ConstantValue divide(ConstantValue valueLeft, ConstantValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		if (((ValueNumber) valueRight).isZero()) {
			return ConstantValue.error("division by zero not allowed in compile-time expressions", astNodeRight);
		}
		return of(((ValueNumber) valueLeft).getValue().divide(((ValueNumber) valueRight).getValue()));
	}

	public static ConstantValue remainder(ConstantValue valueLeft, ConstantValue valueRight,
			EObject astNodeLeft, EObject astNodeRight) {
		final ValueInvalid error = combineErrors(
				requireValueType(valueLeft, ValueNumber.class, "left operand must be a number", astNodeLeft),
				requireValueType(valueRight, ValueNumber.class, "right operand must be a number", astNodeRight));
		if (error != null) {
			return error;
		}
		return of(((ValueNumber) valueLeft).getValue().remainder(((ValueNumber) valueRight).getValue()));
	}

	/**
	 * Combines all invalid values in the given values into a single invalid value or returns <code>null</code> if the
	 * given values did not contain an invalid value. Ignores <code>null</code> values.
	 */
	public static ValueInvalid combineErrors(ConstantValue... values) {
		boolean foundInvalid = false;
		final List<EvalError> errors = new LinkedList<>();
		for (ConstantValue currValue : values) {
			if (currValue != null && !currValue.isValid()) {
				foundInvalid = true;
				errors.addAll(((ValueInvalid) currValue).getErrors());
			}
		}
		return foundInvalid ? new ValueInvalid(errors.toArray(new EvalError[errors.size()])) : null;
	}

	public static ValueInvalid requireValueType(ConstantValue value, Class<? extends ValueValid> type,
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

	// FIXME reconsider serialization / deserialization
	public static String serialize(ConstantValue value) {
		if (value == null || !value.isValid()) {
			return null;
		} else if (value == UNDEFINED || value == NULL) {
			return value.toString();
		} else if (value instanceof ValueBoolean) {
			return "?" + value;
		} else if (value instanceof ValueString) {
			return "\"" + value;
		} else if (value instanceof ValueNumber) {
			return "#" + value;
		} else if (value instanceof ValueSymbol) {
			return "$" + ((ValueSymbol) value).getSymbolName();
		} else {
			throw new UnsupportedOperationException("unknown subclass of ConstantValue: " + value);
		}
	}

	public static ConstantValue deserialize(String str) {
		if (str == null) {
			return null;
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
			throw new UnsupportedOperationException("cannot deserialize ConstantValue from string: " + str);
		}
	}
}
