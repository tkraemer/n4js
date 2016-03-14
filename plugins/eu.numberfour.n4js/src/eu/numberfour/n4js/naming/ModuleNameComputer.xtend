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
package eu.numberfour.n4js.naming

import com.google.inject.Inject
import eu.numberfour.n4js.projectModel.IN4JSCore
import org.eclipse.emf.common.util.URI
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.naming.QualifiedName
import eu.numberfour.n4js.utils.ResourceType

/**
 * Utility class to calculate the qualified name of the resource depending on the project configuration.
 * The project configuration is provided by a manifest.n4mf file in the root of the project.
 * This file contains the definition which folders of the current project should be handled as src rsp.
 * src-test folders. So if a resource is placed under MyProject/src/my/pack/MyResource.n4js, the calculated
 * qualified name would be my.pack.MyResource.
 *
 * Client code should usually use {@code TModule.getName()} or {@code Script.getModule().getName()} to access the
 * module's name.
 */
class ModuleNameComputer {

	@Inject
	private extension IN4JSCore core


	/**
	 * Returns the qualified module name which is implicitly defined by the given resource.
	 * <p>
	 * Please note there is also a special treatment for Xpect test files that may have a file extension
	 * like {@code ".n4js.xt"}. The calculation will handle this as a hole file extension, so {@code ".n4js"} will be pruned, too.
	 */
	def QualifiedName getQualifiedModuleName(Resource resource) {
		val uri = resource.getURI()
		val ResourceType resourceType = ResourceType.getResourceType(uri);

		val maybeSourceContainer = findN4JSSourceContainer(uri)
		if (maybeSourceContainer.present) {
			val sourceContainer = maybeSourceContainer.get
			val location = sourceContainer.location
			if(uri.uriStartsWith(location)) {
				var relativeURI = uri.deresolve(location.appendSegment(""))
				if (ResourceType.xtHidesOtherExtension(resourceType)) { // support Xpect tests with files having additional file extension .xt
					relativeURI = relativeURI.trimFileExtension.trimFileExtension
				} else {
					relativeURI = relativeURI.trimFileExtension
				}
				return QualifiedName.create(relativeURI.segments)
			}
		}
		return uri.createDefaultQualifiedName
	}

	def private createDefaultQualifiedName(URI uri) {
		var segmentList = uri.trimFileExtension.segmentsList
		val srcFolder = Math.max(segmentList.indexOf('src'), segmentList.indexOf('src-test'))
		if (srcFolder != -1) {
			segmentList = segmentList.subList(srcFolder + 1, segmentList.size)
		}
		return QualifiedName.create(segmentList)
	}

	private def boolean uriStartsWith(URI resourceLocation, URI containerLocation) {
		val maxSegments = containerLocation.segmentCount();
		(resourceLocation.segmentCount() >= maxSegments) && !(0 ..< maxSegments).exists(j | !resourceLocation.segment(j).equals(containerLocation.segment(j)))
	}
}
