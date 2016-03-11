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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * An append-only list of constraints. The only mutator methods are those for adding a constraint; and the clear()
 * method.
 */
public class ConstraintBuffer {

	/**
	 * An append-only list of constraints that may also be cleared.
	 */
	private final List<TypeConstraint> buffer = new ArrayList<>();

	/**
	 * Append the argument to the append-only list of constraints.
	 */
	public void addConstraint(TypeConstraint constraint) {
		buffer.add(constraint);
	}

	private void log(final InferenceContext ic, final String message) {
		ic.log(message);
	}

	/**
	 * Log each constraint in insertion order.
	 */
	public void log(final InferenceContext ic) {
		buffer.stream().forEachOrdered(c -> log(ic, c.toString()));
	}

	/**
	 * A read-only list of the underlying constraints.
	 */
	public List<TypeConstraint> asReadOnlyList() {
		return Collections.unmodifiableList(buffer);
	}

	/**
	 * This is the only method that makes a {@link ConstraintBuffer} lose information. It should be used only after such
	 * information is tracked somewhere else, eg in a {@link BoundSet}
	 */
	public void clear() {
		buffer.clear();
	}

}
