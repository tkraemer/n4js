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
import java.util.Objects;

import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.postprocessing.ASTMetaInfoCache;
import eu.numberfour.n4js.ts.types.TField;

/**
 * The value of a constant expression as returned by {@link ASTMetaInfoCache#getEvaluationResult(Expression)} and
 * computed by {@code ConstantExpressionProcessor#computeValueIfConstantExpression()}.
 */
public abstract class ConstantValue {

	public static final ConstantValue UNDEFINED = new ConstantValue("undefined") {
		// no adjustments required
	};

	public static final ConstantValue NULL = new ConstantValue("null") {
		// no adjustments required
	};

	public static final ValueBoolean TRUE = new ValueBoolean(true);

	public static final ValueBoolean FALSE = new ValueBoolean(false);

	public static final class ValueBoolean extends ConstantValue {
		private ValueBoolean(Boolean value) {
			super(value);
		}

		@Override
		public Boolean getValue() {
			return (Boolean) super.getValue();
		}

		public ValueBoolean negate() {
			return of(!getValue());
		}
	}

	public static final class ValueString extends ConstantValue {
		private ValueString(String value) {
			super(value);
		}

		@Override
		public String getValue() {
			return (String) super.getValue();
		}
	}

	public static final class ValueNumber extends ConstantValue {
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
			return of(getValue().negate());
		}
	}

	public static final class ValueSymbol extends ConstantValue {
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

	private final Object value;

	private ConstantValue(Object value) {
		Objects.requireNonNull(value);
		this.value = value;
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
		return obj != null && obj.getClass() == getClass() && value.equals(((ConstantValue) obj).value);
	}

	@Override
	public String toString() {
		return value.toString();
	}

	// ################################################################################################################
	// STATIC UTILITY METHODS FOR HANDLING CONSTANT VALUES

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
		return null;
	}

	public static ValueBoolean of(Boolean value) {
		return value != null ? (value ? TRUE : FALSE) : null;
	}

	public static ValueString of(String value) {
		return value != null ? new ValueString(value) : null;
	}

	public static ValueNumber of(Number value) {
		return value != null ? of(BigDecimal.valueOf(value.doubleValue())) : null;
	}

	public static ValueNumber of(BigDecimal value) {
		return value != null ? new ValueNumber(value) : null;
	}

	public static ValueSymbol of(TField value) {
		return value != null ? new ValueSymbol(value.getName()) : null;
	}

	public static ConstantValue and(ConstantValue... values) {
		if (values != null && values.length > 0) {
			boolean result = true;
			for (int i = 0; i < values.length; i++) {
				final ConstantValue v = values[i];
				if (!(v instanceof ValueBoolean)) {
					return null;
				}
				result = result && ((ValueBoolean) v).getValue().booleanValue();
				// note: do not return early in case of result==false!
			}
			return ConstantValue.of(result);
		}
		return null;
	}

	public static ConstantValue or(ConstantValue... values) {
		if (values != null && values.length > 0) {
			boolean result = false;
			for (int i = 0; i < values.length; i++) {
				final ConstantValue v = values[i];
				if (!(v instanceof ValueBoolean)) {
					return null;
				}
				result = result || ((ValueBoolean) v).getValue().booleanValue();
				// note: do not return early in case of result==false!
			}
			return ConstantValue.of(result);
		}
		return null;
	}

	public static ConstantValue negate(ConstantValue value) {
		if (value instanceof ValueBoolean) {
			return ((ValueBoolean) value).negate();
		} else if (value instanceof ValueNumber) {
			return ((ValueNumber) value).negate();
		}
		return null;
	}

	public static ConstantValue add(ConstantValue valueLeft, ConstantValue valueRight) {
		if (valueLeft instanceof ValueNumber && valueRight instanceof ValueNumber) {
			return of(((ValueNumber) valueLeft).getValue().add(((ValueNumber) valueRight).getValue()));
		} else if (valueLeft != null && valueRight != null
				&& (valueLeft instanceof ValueString || valueRight instanceof ValueString)) {
			return of(valueLeft.toString() + valueRight.toString());
		}
		return null;
	}

	public static ConstantValue subtract(ConstantValue valueLeft, ConstantValue valueRight) {
		if (valueLeft instanceof ValueNumber && valueRight instanceof ValueNumber) {
			return of(((ValueNumber) valueLeft).getValue().subtract(((ValueNumber) valueRight).getValue()));
		}
		return null;
	}

	public static ConstantValue multiply(ConstantValue valueLeft, ConstantValue valueRight) {
		if (valueLeft instanceof ValueNumber && valueRight instanceof ValueNumber) {
			return of(((ValueNumber) valueLeft).getValue().multiply(((ValueNumber) valueRight).getValue()));
		}
		return null;
	}

	public static ConstantValue divide(ConstantValue valueLeft, ConstantValue valueRight) {
		if (valueLeft instanceof ValueNumber && valueRight instanceof ValueNumber) {
			if (((ValueNumber) valueRight).isZero()) {
				return null; // division by zero
			}
			return of(((ValueNumber) valueLeft).getValue().divide(((ValueNumber) valueRight).getValue()));
		}
		return null;
	}

	public static ConstantValue remainder(ConstantValue valueLeft, ConstantValue valueRight) {
		if (valueLeft instanceof ValueNumber && valueRight instanceof ValueNumber) {
			return of(((ValueNumber) valueLeft).getValue().remainder(((ValueNumber) valueRight).getValue()));
		}
		return null;
	}

	// FIXME reconsider serialization / deserialization
	public static String serialize(ConstantValue value) {
		if (value == null) {
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
