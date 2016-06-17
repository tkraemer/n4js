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
package eu.numberfour.n4js.xpect.validation.suppression

import org.eclipse.xtext.resource.XtextResource
import org.xpect.setup.XpectSetupFactory
import java.util.Arrays
import eu.numberfour.n4js.validation.IssueCodes
import org.xpect.xtext.lib.setup.ThisResource
import org.xpect.setup.ISetupInitializer

/**
 * An xpect setup factory which suppresses unused local variable warnings.
 */
@XpectSetupFactory
class SuppressUnusedVariableWarningSetup extends SuppressIssuesSetup {
	new(@ThisResource XtextResource resource,
		ISetupInitializer<SuppressIssuesSetupRoot> setupInitializer
	) {
		super(resource, setupInitializer,
			Arrays.asList(IssueCodes.AST_LOCAL_VAR_UNUSED)
		);
	}
}