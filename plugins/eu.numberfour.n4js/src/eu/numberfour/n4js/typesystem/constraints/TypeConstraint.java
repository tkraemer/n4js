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

import org.eclipse.emf.ecore.EObject;

import eu.numberfour.n4js.n4JS.NamedElement;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.IdentifiableElement;
import eu.numberfour.n4js.ts.types.TFormalParameter;
import eu.numberfour.n4js.ts.types.TFunction;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.util.Variance;

/**
 */
@SuppressWarnings("javadoc")
public class TypeConstraint {

	private static final String PREFIX = "\u27e8 ";
	private static final String SUFFIX = " \u27e9";

	public static final TypeConstraint TRUE = new TypeConstraint(null, null, Variance.CO);
	public static final TypeConstraint FALSE = new TypeConstraint(null, null, Variance.CONTRA);

	public final TypeArgument left;
	public final TypeArgument right;
	public final Variance variance;

	/**
	 *
	 */
	public TypeConstraint(TypeArgument left, TypeArgument right, Variance variance) {
		this.left = left;
		this.right = right;
		this.variance = variance;
	}

	public Type leftDeclaredType() {
		return (left instanceof TypeRef) ? ((TypeRef) left).getDeclaredType() : null;
	}

	public Type rightDeclaredType() {
		return (right instanceof TypeRef) ? ((TypeRef) right).getDeclaredType() : null;
	}

	@Override
	public String toString() {
		if (this == FALSE)
			return PREFIX + "FALSE" + SUFFIX;
		if (this == TRUE)
			return PREFIX + "TRUE" + SUFFIX;
		return toString(left, right, variance);
	}

	public static final String toString(EObject left, EObject right, Variance variance) {
		return PREFIX + toString(left) + " " + variance.getRelationString() + " " + toString(right) + SUFFIX;
	}

	public static final String toString(EObject obj) {
		if (obj == null)
			return "null";
		if (obj instanceof TypeArgument)
			return ((TypeArgument) obj).getTypeRefAsString();
		if (obj instanceof TFunction)
			return ((TFunction) obj).getFunctionAsString();
		if (obj instanceof TFormalParameter)
			return ((TFormalParameter) obj).getFormalParameterAsString();
		if (obj instanceof Type)
			return ((Type) obj).getTypeAsString();
		if (obj instanceof NamedElement)
			return ((NamedElement) obj).getName();
		if (obj instanceof IdentifiableElement)
			return ((IdentifiableElement) obj).getName();
		return obj.eClass().getName();
	}
}
