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
package eu.numberfour.n4js.integration;

import com.google.common.base.Supplier;

import eu.numberfour.n4js.integration.IntegrationTestsUtils.TestType;

/**
 * Representation of a {@link TestType test type} provider.
 */
public interface TestTypeProvider extends Supplier<TestType> {

	/**
	 * Returns with the {@link TestType test type} for the current test suite. This test type contains the logic about
	 * the cloned local repository location, the branch that should be used for setting up the workspace by ensuring the
	 * proper test data existence.
	 *
	 * @return the test type of the suite.
	 */
	@Override
	TestType get();

}
