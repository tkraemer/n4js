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
package eu.numberfour.n4js.external;

import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Maps.newHashMap;
import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static org.eclipse.core.resources.ResourcesPlugin.getWorkspace;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.core.internal.resources.BuildConfiguration;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.internal.N4JSProject;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.utils.resources.ExternalProject;

/**
 * Service for collecting available {@link ExternalProject external project} instances based on the configured external
 * library locations.
 */
@SuppressWarnings("restriction")
@Singleton
public class ExternalProjectsCollector {

	@Inject
	private ExternalLibraryWorkspace externalLibraryWorkspace;

	@Inject
	private ExternalProjectProvider projectProvider;

	@Inject
	private IN4JSCore core;

	/**
	 * On demand collects and returns with all {@link ExternalProject external project} instances based on the
	 * configured external library locations. This method visits the configured external library locations in a priority
	 * order, checks the existence of the projects and collects those. If an external project is already collected when
	 * an other external project is being visited with the same artifact ID, then the latter visited one will be ignored
	 * at all.
	 *
	 * @return an iterable of all external projects that can be resolved from the configured external library paths.
	 */
	public Iterable<ExternalProject> collectExternalProjects() {
		return hookUpReferencedBuildConfigs(externalLibraryWorkspace.getProjects());
	}

	/**
	 * Hooks up the {@link IProjectDescription#getDynamicReferences() dynamic project references} among the given subset
	 * of external projects. If the dynamic projects are already set, then this method has no side effect.
	 * 
	 * @param externalProjects
	 *            an iterable of external projects to update with respect to their dynamic project references.
	 * @return an iterable of external projects with the updated/configured dynamic project references.
	 */
	public Iterable<ExternalProject> hookUpReferencedBuildConfigs(Iterable<? extends IProject> externalProjects) {

		final Map<String, N4JSExternalProject> visitedProjects = newHashMap();
		for (IProject project : externalLibraryWorkspace.getProjects()) {
			final N4JSExternalProject externalProject = projectProvider.get(project.getLocationURI()).orNull();
			if (null != externalProject && !visitedProjects.containsKey(externalProject.getName())) {
				visitedProjects.put(project.getName(), externalProject);
			}
		}

		hookUpReferencedBuildConfigs(visitedProjects);

		return from(visitedProjects.values()).filter(ExternalProject.class);

	}

	/**
	 * Returns with all {@link IProject project} instances that are available from the {@link IWorkspaceRoot workspace
	 * root} and has direct dependency to any external projects.
	 *
	 * <p>
	 * This method neither considers, nor modifies the {@link IProjectDescription#getDynamicReferences() dynamic
	 * references} of the workspace projects. Instead it gathers the dependency information from the {@link N4JSProject
	 * N4JS project} using the {@link IN4JSCore N4JS core} service.
	 *
	 * <p>
	 * No transitive dependencies will be considered. For instance if an accessible Eclipse workspace project B depends
	 * on another accessible Eclipse project A and project A depends on an external project X, then this method will
	 * return with only A and *NOT* A and B.
	 *
	 * @return an iterable of Eclipse workspace projects that has direct dependency any external projects.
	 */
	public Iterable<IProject> collectProjectsWithDirectExternalDependencies() {
		return collectProjectsWithDirectExternalDependencies(collectExternalProjects());
	}

	/**
	 * Sugar for collecting {@link IWorkspace Eclipse workspace} projects that have any direct dependency to any
	 * external projects. Same as {@link #collectProjectsWithDirectExternalDependencies()} but does not considers all
	 * the available projects but only those that are given as the argument.
	 *
	 * @param externalProjects
	 *            the external projects that has to be considered as a possible dependency of an Eclipse workspace based
	 *            project.
	 * @return an iterable of Eclipse workspace projects that has direct dependency to an external project given as the
	 *         argument.
	 */
	public Iterable<IProject> collectProjectsWithDirectExternalDependencies(
			final Iterable<? extends IProject> externalProjects) {

		if (!Platform.isRunning()) {
			return emptyList();
		}

		final Collection<String> externalIds = from(externalProjects).transform(p -> p.getName()).toSet();

		return from(asList(getWorkspace().getRoot().getProjects()))
				.filter(p -> Collections.disjoint(getDirectExternalDependencyIds(p), externalIds));

	}

	/**
	 * Returns with all external project dependency artifact ID for a particular non-external, accessible project.
	 */
	private Collection<String> getDirectExternalDependencyIds(final IProject project) {

		if (null == project || !project.isAccessible()) {
			return emptyList();
		}

		IN4JSProject n4Project = core.findProject(URI.createPlatformResourceURI(project.getName(), true)).orNull();
		if (null == n4Project || !n4Project.exists() || n4Project.isExternal()) {
			return emptyList();
		}

		return from(n4Project.getAllDirectDependencies())
				.filter(IN4JSProject.class)
				.filter(p -> p.exists() && p.isExternal())
				.transform(p -> p.getArtifactId())
				.toSet();

	}

	private void hookUpReferencedBuildConfigs(final Map<String, N4JSExternalProject> visitedProjects) {
		for (final N4JSExternalProject project : visitedProjects.values()) {
			final Iterable<String> artifactIds = project.getAllDirectDependencyIds();
			for (final String artifactId : artifactIds) {
				final N4JSExternalProject referencedProject = visitedProjects.get(artifactId);
				if (null != referencedProject) {
					project.add(new BuildConfiguration(referencedProject));
				}
			}
		}
	}

}
