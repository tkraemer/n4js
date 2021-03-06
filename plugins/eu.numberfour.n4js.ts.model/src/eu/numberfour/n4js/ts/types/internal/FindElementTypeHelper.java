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
package eu.numberfour.n4js.ts.types.internal;

import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.ContainerType;
import eu.numberfour.n4js.ts.types.PrimitiveType;
import eu.numberfour.n4js.ts.types.TObjectPrototype;
import eu.numberfour.n4js.ts.types.util.AbstractHierachyTraverser;

/**
 * Implements the logic that traverses a type hierarchy until it finds a type that declared an element type, e.g.
 * {@code Array<T>} declared element type {@code T}.
 */
public class FindElementTypeHelper extends AbstractHierachyTraverser<TypeRef> {

	private TypeRef result;

	/**
	 * @param type
	 *            the initial type that should be processed.
	 */
	public FindElementTypeHelper(ContainerType<?> type) {
		super(type);
	}

	@Override
	protected TypeRef doGetResult() {
		return result;
	}

	@Override
	protected boolean process(ContainerType<?> currentType) {
		if (currentType instanceof PrimitiveType) {
			result = ((PrimitiveType) currentType).getDeclaredElementType();
		}
		else if (currentType instanceof TObjectPrototype) {
			result = ((TObjectPrototype) currentType).getDeclaredElementType();
		}
		return result != null;
	}

}
