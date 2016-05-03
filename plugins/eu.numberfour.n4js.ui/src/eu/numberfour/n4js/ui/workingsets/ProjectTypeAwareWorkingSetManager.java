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
import static com.google.common.base.Suppliers.memoize;
import static com.google.common.collect.FluentIterable.from;
import static com.google.common.collect.Sets.newHashSet;
import static eu.numberfour.n4js.n4mf.ProjectType.API;
import static org.eclipse.xtext.ui.XtextProjectHelper.hasBuilder;
import static org.eclipse.xtext.ui.XtextProjectHelper.hasNature;
import static org.eclipse.xtext.util.Strings.toFirstUpper;

import java.util.Arrays;
import java.util.Collection;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.emf.common.util.URI;

import com.google.common.base.Supplier;
import com.google.inject.Inject;

import eu.numberfour.n4js.n4mf.ProjectType;
import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.utils.StatusHelper;

/**
 *
 */
public class ProjectTypeAwareWorkingSetManager implements WorkingSetManager {

	private final Supplier<Iterable<WorkingSet>> allWorkingSets = memoize(new Supplier<Iterable<WorkingSet>>() {

		@Override
		public Iterable<WorkingSet> get() {
			return from(Arrays.asList(ProjectType.values()))
					.transform(type -> new ProjectTypeFilterinWorkingSet(type, core));
		}

	});

	private final Collection<String> hiddenWorkingSets = newHashSet();

	@Inject
	private IN4JSCore core;

	@Inject
	private StatusHelper statusHelper;

	@Override
	public String getId() {
		return ProjectTypeAwareWorkingSetManager.class.getName();
	}

	@Override
	public String getLabel() {
		return "N4JS Working Set";
	}

	@Override
	public Iterable<WorkingSet> getWorkingSets() {
		return allWorkingSets.get();
	}

	@Override
	public Iterable<WorkingSet> getAllWorkingSets() {
		return allWorkingSets.get();
	}

	@Override
	public IStatus save(IProgressMonitor monitor) {
		return statusHelper.OK();
	}

	@Override
	public void select(Iterable<WorkingSet> workingSets) {
		hiddenWorkingSets.removeAll(from(workingSets).transform(ws -> ws.getLabel()).toList());
	}

	@Override
	public void unselect(Iterable<WorkingSet> workingSets) {
		hiddenWorkingSets.addAll(from(workingSets).transform(ws -> ws.getLabel()).toList());
	}

	@Override
	public void add(WorkingSet first, WorkingSet... others) {
		// Unsupported.
	}

	@Override
	public void remove(WorkingSet first, WorkingSet... others) {
		// Unsupported.
	}

	private static final class ProjectTypeFilterinWorkingSet implements WorkingSet {

		private final ProjectType type;
		private final IN4JSCore core;

		private ProjectTypeFilterinWorkingSet(ProjectType type, IN4JSCore core) {
			this.type = type;
			this.core = core;
		}

		@Override
		public String getLabel() {
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
				if (project.isAccessible() && hasNature(project) && hasBuilder(project)) {
					final IN4JSProject n4Project = core.findProject(toUri(project)).orNull();
					if (n4Project != null && type.equals(n4Project.getProjectType())) {
						elements[elementCount++] = project;
					}
				}
			}
			return Arrays.copyOfRange(elements, 0, elementCount);
		}

		private URI toUri(final IProject project) {
			return URI.createPlatformResourceURI(project.getName(), true);
		}

	}

}
