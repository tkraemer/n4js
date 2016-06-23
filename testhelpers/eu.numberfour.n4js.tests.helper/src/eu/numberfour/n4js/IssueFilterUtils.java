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
package eu.numberfour.n4js;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.eclipse.xtext.validation.Issue;

/**
 *
 */
public class IssueFilterUtils {
	/**
	 * Returns a list based on the given issues list excluding the issues with the given codes.
	 */
	public List<Issue> without(List<Issue> issues, String... codes) {
		List<String> codeList = Arrays.asList(codes);
		if (issues == null) {
			return Collections.emptyList();
		}
		return issues.stream().filter(issue -> !codeList.contains(issue.getCode())).collect(Collectors.toList());
	}
}
