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

import java.util.Collection
import org.eclipse.xtext.resource.IResourceDescription
import org.eclipse.xtext.resource.IResourceDescription.Delta
import org.eclipse.xtext.resource.IResourceDescriptions
import org.eclipse.xtext.resource.impl.DefaultResourceDescriptionManager

import static eu.numberfour.n4js.n4mf.utils.N4MFConstants.N4MF_MANIFEST

/**
 * Resource description manager for N4 manifests. Considers a {@link Delta change} as relevant to the current
 * candidate if both are pointing to a manifest resource.
 */
class N4MFResourceDescriptionManager extends DefaultResourceDescriptionManager {

	@Override
	override boolean isAffected(Collection<Delta> deltas, IResourceDescription candidate, IResourceDescriptions context) {
		candidate.isManifest && deltas.exists[isManifest];
	}

	private def isManifest(Delta it) {
		null !== it && N4MF_MANIFEST.equals(uri.lastSegment);
	}

	private def isManifest(IResourceDescription it) {
		null !== it && N4MF_MANIFEST.equals(URI.lastSegment);
	}

}
