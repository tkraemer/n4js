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
package eu.numberfour.n4js.n4mf.resource

import eu.numberfour.n4js.n4mf.N4mfPackage
import java.util.Collection
import org.eclipse.emf.common.util.URI
import org.eclipse.xtext.resource.IResourceDescription
import org.eclipse.xtext.resource.IResourceDescription.Delta
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager

import static eu.numberfour.n4js.n4mf.utils.N4MFConstants.N4MF_MANIFEST
import static extension eu.numberfour.n4js.n4mf.resource.N4MFResourceDescriptionStrategy.*

/**
 * Resource description manager for N4 manifests. Considers a {@link Delta change} as relevant to the current
 * candidate if both are pointing to a manifest resource.
 */
class N4MFResourceDescriptionManager extends DefaultResourceDescriptionManager {

	@Override
	override boolean isAffected(Collection<Delta> deltas, IResourceDescription candidate, IResourceDescriptions context) {

		if (candidate.manifest) {
			// Contains only those project IDs those are affected via the N4 manifest.
			val affectedProjectIds = deltas.map[uri].filter[isManifest].map[segment(segmentCount - 2)].toSet;

			// Collect all referenced project IDs of the candidate.
			val referencedProjectIds = newLinkedList;
			candidate.getExportedObjectsByType(N4mfPackage.eINSTANCE.projectDescription).forEach[
				referencedProjectIds.addAll(testedProjectIds);
				referencedProjectIds.addAll(implementedProjectIds);
				referencedProjectIds.addAll(projectDependencyIds);
				referencedProjectIds.addAll(providedRuntimeLibraryIds);
				referencedProjectIds.addAll(requiredRuntimeLibraryIds);
				val extRuntimeEnvironmentId = extendedRuntimeEnvironmentId;
				if (!extRuntimeEnvironmentId.nullOrEmpty) {
					referencedProjectIds.add(extRuntimeEnvironmentId);
				}
			];
			
			for (referencedProjectId : referencedProjectIds) {
				if (affectedProjectIds.contains(referencedProjectId)) {
					return true;
				}
			}
		}
		
		return false;
	}

	private def isManifest(URI it) {
		null !== it && N4MF_MANIFEST == lastSegment;
	}

	private def isManifest(IResourceDescription it) {
		null !== it && N4MF_MANIFEST.equals(URI.lastSegment);
	}

}
