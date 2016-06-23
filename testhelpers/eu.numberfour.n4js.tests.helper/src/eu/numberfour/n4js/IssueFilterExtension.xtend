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
package eu.numberfour.n4js

import java.util.Collections
import java.util.List
import org.eclipse.xtext.validation.Issue

/**
 * Extension to filter lists of {@link Issue}s
 */
class IssueFilterExtension {
	/**
	 * Returns the list of issues without the given ignored issue codes.
	 */
	public def List<Issue> ignore(List<Issue> issues, List<String> ignoredIssueCodes) {
		if (issues === null) {
			return Collections.emptyList();
		}
		return issues.filter[!ignoredIssueCodes.contains(it.code)].toList
	}
}