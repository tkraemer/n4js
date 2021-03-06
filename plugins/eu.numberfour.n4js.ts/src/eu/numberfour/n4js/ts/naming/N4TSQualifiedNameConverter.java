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
package eu.numberfour.n4js.ts.naming;

import org.eclipse.xtext.naming.IQualifiedNameConverter;

/**
 * Changes separator for string representations of qualified names to "/".
 */
public class N4TSQualifiedNameConverter extends IQualifiedNameConverter.DefaultImpl {

	/** Delimiter used in string representations of fully-qualified names. */
	public static final String DELIMITER = "/";

	@Override
	public String getDelimiter() {
		return DELIMITER;
	}
}
