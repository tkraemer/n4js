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
package eu.numberfour.n4js.internal;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.resource.containers.ResourceSetBasedAllContainersState;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;
import com.google.inject.Inject;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;

/**
 */
public class N4FilebasedWorkspaceResourceSetContainerState extends ResourceSetBasedAllContainersState {

	@Inject
	private IN4JSCore in4jscore;

	@Inject
	private N4JSModel n4jsmodel;

	/**
	 */
	public N4FilebasedWorkspaceResourceSetContainerState() {
	}

	/**
	 * @param handle
	 *            uri for the current project prefixed with {@code FileBasedWorkspace#N4FBPRJ}
	 * @return a list of visible projects in form of handles.
	 */
	@Override
	public List<String> getVisibleContainerHandles(String handle) {

		URI containerURI = FileBasedWorkspace.uriFrom(handle);

		List<String> visiContainers = new ArrayList<>();
		// add self
		visiContainers.add(handle);

		Optional<? extends IN4JSProject> project = in4jscore.findProject(containerURI);

		if (!project.isPresent()) {
			throw new IllegalStateException("No project with handle '" + handle + "' known in current In4jscore.");
		}

		ImmutableList<? extends IN4JSProject> dps = n4jsmodel.getDependencies((N4JSProject) project.get());
		// map uri to handle-form and add.
		dps.forEach(d -> visiContainers.add(FileBasedWorkspace.handleFrom(d.getLocation())));

		return visiContainers;
	}
}
