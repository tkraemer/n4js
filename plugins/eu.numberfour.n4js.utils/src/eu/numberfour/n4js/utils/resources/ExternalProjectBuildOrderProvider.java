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
package eu.numberfour.n4js.utils.resources;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.utils.Arrays2.transform;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedSet;
import java.util.Stack;
import java.util.TreeSet;

import org.eclipse.core.internal.resources.Project;
import org.eclipse.core.internal.resources.Workspace.ProjectBuildConfigOrder;
import org.eclipse.core.resources.IBuildConfiguration;
import org.eclipse.core.resources.IProject;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import eu.numberfour.n4js.utils.resources.ComputeProjectOrder.VertexOrder;

/**
 * Utility class for providing build order (as {@link IBuildConfiguration build configuration} instances) for external
 * projects.
 */
@SuppressWarnings("restriction")
public abstract class ExternalProjectBuildOrderProvider {

	/**
	 * Computes the build order among specified projects given as the argument.
	 *
	 * @param first
	 *            the first mandatory project.
	 * @param second
	 *            the second project.
	 * @param rest
	 *            the rest of the projects. Can be omitted but must not be {@code null}, or must not contain
	 *            {@code null} elements.
	 *
	 * @return the build order as an array of build configuration instances.
	 */
	public static IBuildConfiguration[] getBuildOrder(final ExternalProject first, final ExternalProject second,
			final ExternalProject... rest) {

		return getBuildOrder(newHashSet(Lists.asList(first, second, rest)));
	}

	/**
	 * Computes the build order among specified projects given as the argument.
	 *
	 * @param projects
	 *            the projects.
	 * @return the build order as an array of build configuration instances.
	 */
	public static IBuildConfiguration[] getBuildOrder(final Iterable<ExternalProject> projects) {
		return vertexOrderToProjectBuildConfigOrder(computeActiveBuildConfigOrder(projects)).buildConfigurations;
	}

	/**
	 * Computes the global total ordering of all open projects' active buildConfigs in the workspace based on build
	 * configuration references. If an existing and open project's build config P references another existing and open
	 * project's build config Q, then Q should come before P in the resulting ordering. If a build config references a
	 * non-active build config it is added to the resulting ordered list. Closed and non-existent projects/buildConfigs
	 * are ignored, and will not appear in the result. References to non-existent or closed projects/buildConfigs are
	 * also ignored, as are any self-references.
	 * <p>
	 * When there are choices, the choice is made in a reasonably stable way. For example, given an arbitrary choice
	 * between two project buildConfigs, the one with the lower collating project name and build config name will appear
	 * earlier in the list.
	 * </p>
	 * <p>
	 * When the build configuration reference graph contains cyclic references, it is impossible to honor all of the
	 * relationships. In this case, the result ignores as few relationships as possible. For example, if P2 references
	 * P1, P4 references P3, and P2 and P3 reference each other, then exactly one of the relationships between P2 and P3
	 * will have to be ignored. The outcome will be either [P1, P2, P3, P4] or [P1, P3, P2, P4]. The result also
	 * contains complete details of any cycles present.
	 * </p>
	 *
	 * @param projects
	 *            the projects to calculate the active build configuration order among them.
	 *
	 * @return result describing the global active build configuration order
	 */
	public static VertexOrder computeActiveBuildConfigOrder(final Iterable<ExternalProject> projects) {
		// Determine the full set of accessible active project buildConfigs in the workspace,
		// and all the accessible project buildConfigs that they reference. This forms a set
		// of all the project buildConfigs that will be returned.
		// Order the set in descending alphabetical order of project name then build config name,
		// as a secondary sort applied after sorting based on references, to achieve a stable
		// ordering.
		final SortedSet<IBuildConfiguration> allAccessibleBuildConfigs = new TreeSet<>(
				new BuildConfigurationComparator());

		// For each project's active build config, perform a depth first search in the reference graph
		// rooted at that build config.
		// This generates the required subset of the reference graph that is required to order all
		// the dependencies of the active project buildConfigs.
		final ExternalProject[] allProjects = Iterables.toArray(projects, ExternalProject.class);
		final List<IBuildConfiguration[]> edges = new ArrayList<>(allProjects.length);

		for (int i = 0; i < allProjects.length; i++) {
			final ExternalProject project = allProjects[i];
			// Ignore projects that are not accessible
			if (!project.isAccessible()) {
				continue;
			}

			// If the active build configuration hasn't already been explored
			// perform a depth first search rooted at it
			if (!allAccessibleBuildConfigs.contains(project.unsafeGetActiveBuildConfig())) {
				allAccessibleBuildConfigs.add(project.unsafeGetActiveBuildConfig());
				final Stack<IBuildConfiguration> stack = new Stack<>();
				stack.push(project.unsafeGetActiveBuildConfig());

				while (!stack.isEmpty()) {
					final IBuildConfiguration buildConfiguration = stack.pop();

					// Add all referenced buildConfigs from the current configuration
					// (it is guaranteed to be accessible as it was pushed onto the stack)
					final Project subProject = (Project) buildConfiguration.getProject();
					final IBuildConfiguration[] refs = subProject
							.internalGetReferencedBuildConfigs(buildConfiguration.getName(), false);
					for (int j = 0; j < refs.length; j++) {
						final IBuildConfiguration ref = refs[j];

						// Ignore self references and references to projects that are not accessible
						if (ref.equals(buildConfiguration)) {
							continue;
						}

						// Add the referenced accessible configuration
						edges.add(new IBuildConfiguration[] { buildConfiguration, ref });

						// If we have already explored the referenced configuration, don't explore it again
						if (allAccessibleBuildConfigs.contains(ref)) {
							continue;
						}

						allAccessibleBuildConfigs.add(ref);

						// Push the referenced configuration onto the stack so that it is explored by the depth first
						// search
						stack.push(ref);
					}
				}
			}
		}
		return ComputeProjectOrder.computeVertexOrder(allAccessibleBuildConfigs, edges);
	}

	/**
	 * Computes the global total ordering of all project buildConfigs in the workspace based on build config references.
	 * If an existing and open build config P references another existing and open project build config Q, then Q should
	 * come before P in the resulting ordering. Closed and non-existent projects/buildConfigs are ignored, and will not
	 * appear in the result. References to non-existent or closed projects/buildConfigs are also ignored, as are any
	 * self-references.
	 * <p>
	 * When there are choices, the choice is made in a reasonably stable way. For example, given an arbitrary choice
	 * between two project buildConfigs, the one with the lower collating project name and build config name will appear
	 * earlier in the list.
	 * </p>
	 * <p>
	 * When the build config reference graph contains cyclic references, it is impossible to honor all of the
	 * relationships. In this case, the result ignores as few relationships as possible. For example, if P2 references
	 * P1, P4 references P3, and P2 and P3 reference each other, then exactly one of the relationships between P2 and P3
	 * will have to be ignored. The outcome will be either [P1, P2, P3, P4] or [P1, P3, P2, P4]. The result also
	 * contains complete details of any cycles present.
	 * </p>
	 *
	 * @return result describing the global project build configuration order
	 */
	public static VertexOrder computeFullBuildConfigOrder(IBuildConfiguration[] preCalculatedConfigs) {
		// Compute the order for all accessible project buildConfigs
		SortedSet<IBuildConfiguration> allAccessibleBuildConfigurations = new TreeSet<>(
				new BuildConfigurationComparator());

		IProject[] allProjects = from(transform(preCalculatedConfigs, config -> config.getProject()))
				.toArray(IProject.class);
		List<IBuildConfiguration[]> edges = new ArrayList<>(allProjects.length);

		for (int i = 0; i < allProjects.length; i++) {
			Project project = (Project) allProjects[i];
			// Ignore projects that are not accessible
			if (!project.isAccessible())
				continue;

			IBuildConfiguration[] configs = project.internalGetBuildConfigs(false);
			for (int j = 0; j < configs.length; j++) {
				IBuildConfiguration config = configs[j];
				allAccessibleBuildConfigurations.add(config);
				IBuildConfiguration[] refs = project.internalGetReferencedBuildConfigs(config.getName(), false);
				for (int k = 0; k < refs.length; k++) {
					IBuildConfiguration ref = refs[k];

					// Ignore self references
					if (ref.equals(config))
						continue;

					// Add the reference to the set of reachable configs + add an edge
					allAccessibleBuildConfigurations.add(ref);
					edges.add(new IBuildConfiguration[] { config, ref });
				}
			}
		}
		return ComputeProjectOrder.computeVertexOrder(allAccessibleBuildConfigurations, edges);
	}

	/**
	 * Converts the {@link VertexOrder vertex order} into the appropriate {@link ProjectBuildConfigOrder build
	 * configuration order}.
	 *
	 * @param order
	 *            the order to convert.
	 * @return the converted vertex order as a build configuration order. The returning configuration can be consumed by
	 *         the Eclipse build framework.
	 */
	public static ProjectBuildConfigOrder vertexOrderToProjectBuildConfigOrder(final VertexOrder order) {
		final IBuildConfiguration[] buildConfigs = new IBuildConfiguration[order.vertexes.length];
		System.arraycopy(order.vertexes, 0, buildConfigs, 0, order.vertexes.length);
		final IBuildConfiguration[][] knots = new IBuildConfiguration[order.knots.length][];
		for (int i = 0; i < order.knots.length; i++) {
			knots[i] = new IBuildConfiguration[order.knots[i].length];
			System.arraycopy(order.knots[i], 0, knots[i], 0, order.knots[i].length);
		}
		return new ProjectBuildConfigOrder(buildConfigs, order.hasCycles, knots);
	}

	// Comparator used to provide a stable ordering of project buildConfigs
	private static class BuildConfigurationComparator implements Comparator<IBuildConfiguration> {

		@Override
		public int compare(final IBuildConfiguration px, final IBuildConfiguration py) {
			int cmp = py.getProject().getName().compareTo(px.getProject().getName());
			if (cmp == 0) {
				cmp = py.getName().compareTo(px.getName());
			}
			return cmp;
		}
	}

}
