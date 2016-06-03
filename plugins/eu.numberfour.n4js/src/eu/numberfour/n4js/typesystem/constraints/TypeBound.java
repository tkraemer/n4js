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

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.util.Variance;
import eu.numberfour.n4js.ts.utils.TypeCompareUtils;
import eu.numberfour.n4js.ts.utils.TypeUtils;

/**
 */
/* package */ final class TypeBound {
	public final TypeVariable left; // TypeVariableImpl inherits hashCode() from j.l.Object
	public final TypeRef right;
	public final Variance variance;

	private Integer hashCode = null;

	/**
	 *
	 */
	public TypeBound(TypeVariable left, TypeRef right, Variance variance) {
		this.left = left;
		this.right = right;
		this.variance = variance;
	}

	@Override
	public int hashCode() {
		if (hashCode == null) {
			hashCode = this.toString().hashCode(); // TODO find better way to compute hash code
		}
		return hashCode.intValue();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj instanceof TypeBound) {
			final TypeBound other = (TypeBound) obj;
			return other.left == this.left && TypeCompareUtils.isEqual(other.right, this.right)
					&& other.variance == this.variance;
		}
		return false;
	}

	@Override
	public String toString() {
		return String.valueOf(this.left.getTypeAsString()) + " " + this.variance.getRelationString() + " "
				+ this.right.getTypeRefAsString();
	}

	public void log(InferenceContext ic) {
		ic.log("    " + toString());
	}

	/**
	 * Is this bound of the form `alpha op alpha`?
	 */
	public boolean isTrivial() {
		return right.getDeclaredType() == left;
	}

	/**
	 * If 'right' is a {@link TypeUtils#isRawTypeRef(TypeRef) raw type reference}, this method will return a copy of the
	 * receiving type bound with 'right' being sanitized; otherwise, the receiving type bound will be returned.
	 */
	public TypeBound sanitizeRawTypeRef() {
		if (TypeUtils.isRawTypeRef(right)) {
			final TypeBound cpy = new TypeBound(left, TypeUtils.copy(right), variance);
			TypeUtils.sanitizeRawTypeRef(cpy.right);
			return cpy;
		}
		return this;
	}
}
