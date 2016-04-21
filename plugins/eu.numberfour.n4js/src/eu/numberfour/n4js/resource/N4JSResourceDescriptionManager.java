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

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.builder.clustering.CurrentDescriptions;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.DerivedStateAwareResourceDescriptionManager;
import org.eclipse.xtext.resource.FileExtensionProvider;
import org.eclipse.xtext.resource.IDefaultResourceDescriptionStrategy;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescription;
import org.eclipse.xtext.resource.IResourceDescription.Delta;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.impl.EObjectDescriptionLookUp;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.projectModel.IN4JSCore;
import eu.numberfour.n4js.projectModel.IN4JSProject;
import eu.numberfour.n4js.ts.scoping.builtin.N4Scheme;
import eu.numberfour.n4js.ts.utils.TypeHelper;

/**
 * Only differences to super class method are that a {@link N4JSResourceDescription} is created as well the call to
 * getCache() instead of directly accessing the property cache. The cast to {@link N4JSResourceDescriptionStrategy} is
 * only a double check that the correct resource description strategy is bound in the runtime module.
 */
@Singleton
@SuppressWarnings("restriction")
public class N4JSResourceDescriptionManager extends DerivedStateAwareResourceDescriptionManager implements N4Scheme {

	@Inject
	private IQualifiedNameProvider qualifiedNameProvider;

	@Inject
	private TypeHelper typeHelper;

	@Inject
	private N4JSCrossReferenceComputer crossReferenceComputer;

	@Inject
	private FileExtensionProvider fileExtensionProvider;

	@Inject
	private IN4JSCore n4jsCore;

	@Override
	protected IResourceDescription createResourceDescription(final Resource resource,
			IDefaultResourceDescriptionStrategy strategy) {
		return new N4JSResourceDescription(crossReferenceComputer, typeHelper,
				qualifiedNameProvider, resource,
				(N4JSResourceDescriptionStrategy) strategy,
				getCache()) {
			@Override
			protected EObjectDescriptionLookUp getLookUp() {
				if (lookup == null)
					lookup = new EObjectDescriptionLookUp(computeExportedObjects());
				return lookup;
			}
		};
	}

	/**
	 * {@inheritDoc}
	 *
	 * This marks {@code n4js} as affected if the manifest of the project changes. In turn, they will be revalidated and
	 * taken into consideration for the code generation step.
	 */
	@Override
	public boolean isAffected(Collection<IResourceDescription.Delta> deltas, IResourceDescription candidate,
			IResourceDescriptions context) {
		boolean result = basicIsAffected(deltas, candidate, context);
		if (!result) {
			for (IResourceDescription.Delta delta : deltas) {
				URI uri = delta.getUri();
				if (IN4JSProject.N4MF_MANIFEST.equalsIgnoreCase(uri.lastSegment())) {
					URI prefixURI = uri.trimSegments(1).appendSegment("");
					if (candidate.getURI().replacePrefix(prefixURI, prefixURI) != null) {
						return true;
					}
				}
			}
		}
		return result;
	}

	private boolean basicIsAffected(Collection<Delta> deltas, IResourceDescription candidate,
			IResourceDescriptions context) {
		// The super implementation DefaultResourceDescriptionManager#isAffected is based on a tradeoff / some
		// assumptions which do not hold for n4js wrt to manifest changes
		Collection<QualifiedName> importedNames = null;
		for (IResourceDescription.Delta delta : deltas) {
			if (delta.haveEObjectDescriptionsChanged() &&
					fileExtensionProvider.isValid(delta.getUri().fileExtension())) {

				if (null == importedNames) {
					importedNames = getImportedNames(candidate);
				}

				if (isAffected(importedNames, delta.getNew()) || isAffected(importedNames, delta.getOld())) {
					if (hasDependencyTo(candidate, delta)) {
						return true;
					} else {
						// Otherwise do nothing since we are not in the middle of an auto/incremental build.
						if (context instanceof CurrentDescriptions) {
							Iterable<URI> queuedURIs = ((CurrentDescriptions) context).getBuildData()
									.getAllRemainingURIs();
							for (URI queuedURI : queuedURIs) {
								IResourceDescription queuedDescription = context.getResourceDescription(queuedURI);
								if (fileExtensionProvider.isValid(queuedDescription.getURI().fileExtension())
										&& hasDependencyTo(queuedDescription.getURI(), delta.getUri())
										&& hasDependencyTo(candidate.getURI(), queuedDescription.getURI())) {
									return true;
								}
							}
						}
					}
				}
			}
		}
		return false;
	}

	/**
	 * Returns true iff project containing the 'candidate' has a direct dependency to the project containing the
	 * 'delta'.
	 */
	private boolean hasDependencyTo(IResourceDescription candidate, IResourceDescription.Delta delta) {
		return hasDependencyTo(candidate.getURI(), delta.getUri());
	}

	/**
	 * Returns true iff project containing the 'candidate' has a direct dependency to the project containing the
	 * 'delta'.
	 */
	private boolean hasDependencyTo(URI fromUri, URI toUri) {
		final IN4JSProject fromProject = n4jsCore.findProject(fromUri).orNull();
		final IN4JSProject toProject = n4jsCore.findProject(toUri).orNull();

		if (null != fromProject && null != toProject) { // Consider libraries. TODO: implement it at #findProject(URI)

			if (Objects.equals(fromProject, toProject)) {
				return true;
			}

			for (IN4JSProject fromProjectDependency : fromProject.getDependenciesAndImplementedApis()) {

				// Never mark a resource as effected when trying to resolve its dependency from an external to a
				// workspace one and/or vice versa.
				if (Objects.equals(fromProjectDependency, toProject)
						&& fromProjectDependency.isExternal() == fromProject.isExternal()) {
					return true;
				}
			}
		}

		return false;
	}

	@Override
	protected void addExportedNames(Set<QualifiedName> names, IResourceDescription resourceDescriptor) {
		if (resourceDescriptor == null)
			return;
		Iterable<IEObjectDescription> iterable = resourceDescriptor.getExportedObjects();
		for (IEObjectDescription ieObjectDescription : iterable) {
			names.add(ieObjectDescription.getName());
		}
	}

	/**
	 * Overrides super implementation to replace case insensitive comparison logic by case sensitive comparison of
	 * names.
	 */
	@Override
	protected boolean isAffected(Collection<QualifiedName> importedNames, IResourceDescription description) {
		if (description != null) {
			for (IEObjectDescription desc : description.getExportedObjects())
				if (importedNames.contains(desc.getName()))
					return true;
		}
		return false;
	}

	@Override
	public Delta createDelta(IResourceDescription oldDescription, IResourceDescription newDescription) {
		return new N4JSResourceDescriptionDelta(oldDescription, newDescription);
	}
}
