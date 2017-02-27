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
package eu.numberfour.n4js.scoping.members;

import eu.numberfour.n4js.ts.types.TMember;

/**
 *
 */
public interface ComposedMemberDescriptor {

	/***/
	public boolean isEmpty();

	/***/
	public boolean isValid();

	/***/
	public TMember create(String name);

}
