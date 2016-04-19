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
package eu.numberfour.n4js.ui.projectModel;

import java.util.Collection;

import org.eclipse.e4.ui.internal.workbench.TopologicalSort;

import eu.numberfour.n4js.projectModel.IN4JSProject;

/**
 *
 */
@SuppressWarnings("restriction")
public class N4JSProjectTopologicalSort extends TopologicalSort<IN4JSProject, IN4JSProject> {

	@Override
	protected IN4JSProject getId(IN4JSProject object) {
		return object;
	}

	@Override
	protected Collection<IN4JSProject> getRequirements(IN4JSProject id) {
		return null;
	}

	@Override
	@SuppressWarnings("unchecked")
	protected Collection<IN4JSProject> getDependencies(IN4JSProject id) {
		return (Collection<IN4JSProject>) id.getDependenciesAndImplementedApis();
	}

}
