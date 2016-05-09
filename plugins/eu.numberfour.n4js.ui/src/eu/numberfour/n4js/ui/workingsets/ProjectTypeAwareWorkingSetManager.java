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
package eu.numberfour.n4js.ui.workingsets;

import static com.google.common.base.Strings.nullToEmpty;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Lists.newArrayList;
import static eu.numberfour.n4js.n4mf.ProjectType.API;
import static org.eclipse.xtext.util.Strings.toFirstUpper;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.emf.common.util.URI;

import com.google.inject.Inject;

import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;

/**
 * N4JS project type aware working set manager implementation.
 */
public class ProjectTypeAwareWorkingSetManager extends WorkingSetManagerImpl {

	@Inject
	private IN4JSCore core;

	@Override
	public String getLabel() {
		return "N4JS Project Type";
	}

	@Override
	protected List<WorkingSet> initializeWorkingSets() {
		final Collection<ProjectType> types = newArrayList(ProjectType.values());
		types.add(null); // For 'Others'.
		return newArrayList(from(types)
				.transform(type -> new ProjectTypeWorkingSet(type, core, ProjectTypeAwareWorkingSetManager.this)));
	}

	private static final class ProjectTypeWorkingSet implements WorkingSet {

		private final ProjectType type;
		private final IN4JSCore core;
		private final WorkingSetManager manager;

		private ProjectTypeWorkingSet(/* nullable */ final ProjectType type, final IN4JSCore core,
				final WorkingSetManager manager) {

			this.type = type;
			this.core = core;
			this.manager = manager;
		}

		@Override
		public String getName() {

			if (null == type) {
				return OTHERS_WORKING_SET_LABEL;
			}

			return API.equals(type)
					? API.getLiteral()
					: toFirstUpper(nullToEmpty(type.getLiteral()).replaceAll("_", " ").toLowerCase());
		}

		@Override
		public IAdaptable[] getElements() {
			final IProject[] projects = ResourcesPlugin.getWorkspace().getRoot().getProjects();
			final IProject[] elements = new IProject[projects.length];
			int elementCount = 0;
			for (int i = 0, size = projects.length; i < size; i++) {
				final IProject project = projects[i];
				final IN4JSProject n4Project = core.findProject(toUri(project)).orNull();
				if (type == null) { // Others
					if (n4Project == null || !n4Project.exists()) {
						elements[elementCount++] = project;
					}
				} else {
					if (n4Project != null && n4Project.exists() && type.equals(n4Project.getProjectType())) {
						elements[elementCount++] = project;
					}
				}
			}
			return Arrays.copyOfRange(elements, 0, elementCount);
		}

		@Override
		public WorkingSetManager getWorkingSetManager() {
			return manager;
		}

		private URI toUri(final IProject project) {
			return URI.createPlatformResourceURI(project.getName(), true);
		}

		@Override
		public String toString() {
			return null == type ? OTHERS_WORKING_SET_LABEL : getName();
		}

	}

}
