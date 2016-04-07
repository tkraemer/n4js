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
package eu.numberfour.n4js.resource;

import com.google.common.base.Equivalence;
import com.google.common.base.Objects;

import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.utils.DependencyCollector;

/**
 * Collector for recursively gathering all transitive dependencies of an N4JS project.
 */
public class N4JSProjectDependencyCollector extends DependencyCollector<IN4JSProject> {

	/**
	 * Creates a new collector instance with the given N4JS project.
	 *
	 * @param project
	 *            the project which dependencies we are looking for.
	 */
	public N4JSProjectDependencyCollector(final IN4JSProject project) {
		super(project, IN4JSProjectEquivalence.INSTANCE, p -> p.getDependenciesAndImplementedApis());
	}

	/**
	 * {@link Equivalence} implementation for {@link IN4JSProject} instances. Considers only the URI of the location
	 * property.
	 */
	private static class IN4JSProjectEquivalence extends Equivalence<IN4JSProject> {

		public static final IN4JSProjectEquivalence INSTANCE = new IN4JSProjectEquivalence();

		@Override
		public boolean doEquivalent(final IN4JSProject left, final IN4JSProject right) {
			return Objects.equal(left, right);
		}

		@Override
		public int doHash(final IN4JSProject project) {
			return Objects.hashCode(project);
		}

	}

}
