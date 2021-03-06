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
package eu.numberfour.n4js.typesystem;

import it.xsemantics.runtime.StringRepresentation;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;

/**
 */
public class N4JSStringRepresenation extends StringRepresentation {

	/**
	 * Delegates to {@link TypeArgument#getTypeRefAsString()}
	 */
	protected String stringRep(TypeArgument typeArgument) {
		return typeArgument.getTypeRefAsString();
	}
}
