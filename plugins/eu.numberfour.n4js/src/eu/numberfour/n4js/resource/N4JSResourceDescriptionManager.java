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
import java.util.Optional;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
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
import eu.numberfour.n4js.utils.languages.N4LanguageUtils;

/**
 * Only differences to super class method are that a {@link N4JSResourceDescription} is created as well the call to
 * getCache() instead of directly accessing the property cache. The cast to {@link N4JSResourceDescriptionStrategy} is
 * only a double check that the correct resource description strategy is bound in the runtime module.
 */
@Singleton
public class N4JSResourceDescriptionManager extends DerivedStateAwareResourceDescriptionManager implements N4Scheme {

	@Inject
	private IQualifiedNameProvider qualifiedNameProvider;

	@Inject
	private TypeHelper typeHelper;

	@Inject
	private N4JSCrossReferenceComputer crossReferenceComputer;

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
		boolean result = basicIsAffected(deltas, candidate);
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

	/**
	 * Computes if a candidate is affected by any change, aka delta. It is affected, if
	 */
	private boolean basicIsAffected(Collection<Delta> deltas, final IResourceDescription candidate) {
		// The super implementation DefaultResourceDescriptionManager#isAffected is based on a tradeoff / some
		// assumptions which do not hold for n4js wrt to manifest changes

		// computed the first time we need it, do not compute eagerly
		Collection<QualifiedName> namesImportedByCandidate = null;

		for (IResourceDescription.Delta delta : deltas) {
			if (delta.haveEObjectDescriptionsChanged() && isValid(delta.getUri())) {

				if (null == namesImportedByCandidate) {
					// note: this does not only contain the explicitly imported names, but indirectly
					// imported names as well!
					namesImportedByCandidate = getImportedNames(candidate);
				}

				if (isAffected(namesImportedByCandidate, delta.getNew()) // we may added a new exported name!
						|| isAffected(namesImportedByCandidate, delta.getOld())) { // we may removed an exported name
					if (hasDependencyTo(candidate, delta)) { // isAffected does not compare project names
						return true;
					}
				}
			}
		}

		return false;
	}

	/**
	 * Contextual if provided URI is a valid file. It is a bit abstract check as based on the provided URI we get
	 * {@link FileExtensionProvider} that will validate that URI. For URIs obtained from Xtext resources, this will
	 * always be true. This is slightly different semantics from original code, that was checking if provided URI
	 * belonged to {@link N4JSResource}, but with addition of new languages (e.g. {@code N4JSX}) that logic is invalid.
	 *
	 * We add this method temporary to fix IDE-2501, but we treat it as placeholder for solution of IDE-2509.
	 */
	private boolean isValid(URI uri) {
		Optional<FileExtensionProvider> contextualFileExtensionProvider = N4LanguageUtils
				.getServiceForContext(uri, FileExtensionProvider.class);

		// TODO IDE-2509 clarify semantics of 'valid' uri
		return contextualFileExtensionProvider.isPresent()
				? contextualFileExtensionProvider.get().isValid(uri.fileExtension()) : false;
	}

	/**
	 * Returns true iff project containing the 'candidate' has a direct dependency to the project containing the
	 * 'delta'.
	 */
	private boolean hasDependencyTo(IResourceDescription candidate, IResourceDescription.Delta delta) {
		return hasDependencyTo(candidate.getURI(), delta.getUri());
	}

	/**
	 * Returns true iff the project containing the 'fromUri' has a direct dependency to the project containing the
	 * 'toUri'.
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
	 * <p>
	 * It returns true, if there is a dependency (i.e. name imported by a candidate) to any name exported by the
	 * description from a delta. That is, it computes if a candidate (with given importedNames) is affected by a change
	 * represented by the description from the delta.
	 */
	@Override
	protected boolean isAffected(Collection<QualifiedName> namesImportedByCandidate,
			IResourceDescription descriptionFromDelta) {
		if (descriptionFromDelta != null) {
			for (IEObjectDescription desc : descriptionFromDelta.getExportedObjects())
				if (namesImportedByCandidate.contains(desc.getName()))
					return true;
		}
		return false;
	}
}
