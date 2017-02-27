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
 * Interface for creating composed members.
 */
public interface ComposedMemberDescriptor {

	/** Returns true iff the composed members does not have any members. */
	public boolean isEmpty();

	/** Returns true iff the composition of members is valid. */
	public boolean isValid();

	/** Returns a new composed {@link TMember}. */
	public TMember create(String name);

}
