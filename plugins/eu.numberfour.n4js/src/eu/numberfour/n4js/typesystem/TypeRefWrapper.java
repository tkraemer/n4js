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

import eu.numberfour.n4js.ts.typeRefs.TypeArgument;

public interface TypeRefWrapper {
	public <T extends TypeArgument> T resolveVersion(T typeRef, int contextVersion);
}
