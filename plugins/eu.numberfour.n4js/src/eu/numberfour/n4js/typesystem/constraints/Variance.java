/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.typesystem.constraints;

/**
 * Type variance.
 */
public enum Variance {
	/** Literal denoting covariance. */
	CO,
	/** Literal denoting contravariance. */
	CONTRA,
	/** Literal denoting two types are invariant. */
	INV;

	/**
	 * Inverse operation for variance.
	 */
	public Variance inverse() {
		switch (this) {
		case CO:
			return CONTRA;
		case CONTRA:
			return CO;
		case INV:
			return INV;
		default:
			throw new UnsupportedOperationException("unsupported literal: " + this);
		}
	}

	/**
	 * Multiplication operation for variance.
	 */
	public Variance mult(Variance other) {
		if (this == INV || other == INV)
			return INV;
		if (this == CONTRA && other == CONTRA)
			return CO;
		if (this == CONTRA || other == CONTRA)
			return CONTRA;
		if (this == CO && other == CO)
			return CO;
		throw new UnsupportedOperationException("unsupported literals: " + this + ", " + other);
	}

	/**
	 * Returns an operator-like representation of this literal.
	 */
	public String getRelationString() {
		if (this == CO)
			return "<:";
		if (this == CONTRA)
			return ":>";
		return "=";
	}
}
