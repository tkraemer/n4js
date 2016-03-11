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
package eu.numberfour.n4js.hlc.tests.utils;

import static com.google.common.base.Strings.nullToEmpty;

/**
 * Mutable representation of a test case.
 */
/* default */ class TestCase {

	String name;
	String status;
	String message;

	@Override
	public String toString() {
		return nullToEmpty(status) + ": " + nullToEmpty(name) + " -> " + nullToEmpty(message);
	}

}
