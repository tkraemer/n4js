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
package eu.numberfour.n4js.xpect.ui;

import eu.numberfour.n4js.xpect.ui.results.XpectBugReportUtil;

/**
 * Generates xpect report with JIRA formatting.
 */
public class GenerateJiraBugReportShortcut extends GenerateXpectReportShortcut {

	/**
	 * Generate bug report using JIRA formatting.
	 */
	@Override
	protected void generateAndDisplayReport(String name, String content) {
		XpectBugReportUtil.displayGeneratedJiraBugConsole(name, content);
	}

}
