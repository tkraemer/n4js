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
package eu.numberfour.n4js.utils;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.resource.IEObjectDescription;

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.resource.UserdataMapper;
import eu.numberfour.n4js.ts.types.TModule;
import eu.numberfour.n4js.ts.utils.TypeUtils;

/**
 * Utility methods for handling <em>module-to-module URIs</em> (a.k.a. "m2m URIs"). These URIs are created by
 * <ol>
 * <li>the {@link UserdataMapper#getDeserializedModuleFromDescription(IEObjectDescription, URI) UserdataMapper} after
 * deserializing a TModule from the index, and
 * <li>the {@link N4JSResource#unload() N4JSResource} after unloading an N4JS resource
 * </ol>
 * to allow some special handling when resolving references from one TModule to another TModule in a different resource.
 * This special handling is located in method {@link N4JSResource#getEObjectFromM2MUri(String, boolean)}.
 * <p>
 * For example, assuming we are deserializing the TModule of a resource B.n4js having a reference to another resource
 * C.n4js in form of a proxy with an original URI of
 *
 * <pre>
 * platform:/resource/TestProject/src/C.n4js#/1/@topLevelTypes.0
 * </pre>
 *
 * then the above URI will be converted to:
 *
 * <pre>
 * platform:/resource/TestProject/src/B.n4js#m2m!platform:/resource/TestProject/src/C.n4js%23/1/@topLevelTypes.0
 * </pre>
 *
 * When this proxy will later be resolved, the first URI would cause EMF to load <code>C.n4js</code> (always directly
 * from disk!) and then call {@link Resource#getEObject(String)} on <code>C.n4js</code> to obtain the target object. The
 * second URI will instead cause EMF to invoke <code>#getEObject(String)</code> on <u><code>B.n4js</code></u> (without
 * loading anything because <code>B.n4js</code> is already loaded), which then has the chance to perform some special
 * handling:
 * <ol>
 * <li>load <code>C.n4js</code> from the index instead from disk (if index contains type information for this resource).
 * <li>trigger post-processing of <code>C.n4js</code> iff <code>B.n4js</code> is currently being post-processed or has
 * been post-processed (will only have an effect if <code>C.n4js</code> was loaded from disk).
 * </ol>
 * For details, see the code of method {@link N4JSResource#getEObjectFromM2MUri(String, boolean)}.
 * <p>
 * The above example would apply accordingly if we were <em>unloading</em> resource <code>C.n4js</code> in a resource
 * set already containing above resources <code>B.n4js</code> and <code>C.n4js</code> (with all links resolved).
 */
public class M2MUriUtil {

	private static final int PREFIX_LEN = N4JSGlobals.URI_FRAGMENT_PREFIX_M2M.length();

	/** Tells if given URI is a {@link M2MUriUtil m2m URI}. */
	public static final boolean isM2MUri(URI uri) {
		return uri != null && isM2MUriFragment(uri.fragment());
	}

	/** Tells if given fragment is a fragment of a {@link M2MUriUtil m2m URI}. */
	public static final boolean isM2MUriFragment(String fragment) {
		return fragment != null && fragment.startsWith(N4JSGlobals.URI_FRAGMENT_PREFIX_M2M);
	}

	/** Convert given URI to a {@link M2MUriUtil m2m URI}. */
	public static final URI convertToM2M(URI baseUri, URI uri) {
		return baseUri.appendFragment(URI.encodeFragment(
				N4JSGlobals.URI_FRAGMENT_PREFIX_M2M + uri.toString(), false));
	}

	/**
	 * Convert given {@link M2MUriUtil m2m URI} back to a normal EMF proxy URI.
	 * <p>
	 * Should only be called for URI fragments for which {@link #isM2MUriFragment(String)} returns true, but this is not
	 * checked by this method.
	 */
	public static final URI convertFromM2M(String m2mUriFragment) {
		return URI.createURI(URI.decode(m2mUriFragment.substring(PREFIX_LEN)));
	}

	/**
	 * Same as {@link #convertProxyUriToM2M(URI, EObject, boolean)} with argument <code>copy</code> set to false.
	 */
	public static final void convertProxyUriToM2M(URI baseUri, EObject potentialProxy) {
		convertProxyUriToM2M(baseUri, potentialProxy, false);
	}

	/**
	 * If and only if the given EObject is a proxy, its proxy URI will be converted to a
	 * {@link N4JSGlobals#URI_FRAGMENT_PREFIX_M2M m2m URI}.
	 *
	 * @param baseUri
	 *            resource containing the <code>potentialProxy</code>.
	 * @param potentialProxy
	 *            the EObject to convert, in case it is a proxy.
	 * @param copy
	 *            if <code>true</code>, the given potential proxy will be copied before changing its URI; if
	 *            <code>false</code>, the URI will be directly changed in the given instance.
	 * @return the given proxy with a converted URI or a copy of the given proxy with a converted URI or
	 *         <code>null</code> if the URI need not or cannot be converted.
	 */
	public static final EObject convertProxyUriToM2M(URI baseUri, EObject potentialProxy, boolean copy) {
		if (potentialProxy.eIsProxy()) {
			final URI oldUri = ((BasicEObjectImpl) potentialProxy).eProxyURI();
			if (oldUri != null && !oldUri.isRelative() && oldUri.hasFragment() && !isM2MUri(oldUri)) {
				// make sure proxy points to another resource (required because links from TModule to AST of same
				// resource will be represented as proxies and we do not want to convert those!)
				if (!oldUri.trimFragment().equals(baseUri)) {
					final URI newUri = M2MUriUtil.convertToM2M(baseUri, oldUri);
					if (copy) {
						final EObject newProxy = TypeUtils.copyWithProxies(potentialProxy);
						((BasicEObjectImpl) newProxy).eSetProxyURI(newUri);
						return newProxy;
					} else {
						((BasicEObjectImpl) potentialProxy).eSetProxyURI(newUri);
						return potentialProxy;
					}
				}
			}
		}
		return null;
	}

	/**
	 * For all proxies in in the given {@link TModule}, convert their URIs to {@link N4JSGlobals#URI_FRAGMENT_PREFIX_M2M
	 * m2m URIs}.
	 *
	 * @param module
	 *            the module to process.
	 * @param sourceUri
	 *            URI of the resource the given {@code TModule} belongs to. Provided as argument to allow processing
	 *            modules that are not (yet) contained in a resource.
	 * @param targetUriToProcess
	 *            if non-<code>null</code>, will limit conversion to proxies that point to a resource with this URI.
	 * @param copy
	 *            if <code>true</code>, proxies will be copied before changing their URI; if <code>false</code>, the URI
	 *            will be directly changed in the proxy instances.
	 */
	public static final void convertAllProxiesToM2M(TModule module, URI sourceUri, URI targetUriToProcess,
			boolean copy) {
		if (sourceUri == null || sourceUri.hasFragment())
			throw new IllegalArgumentException("sourceUri must be non-null and must not have a fragment");
		if (targetUriToProcess != null && targetUriToProcess.hasFragment())
			throw new IllegalArgumentException("targetUriToProcess must not have a fragment");
		EcoreUtilN4.handleProxyCrossReferences(module, (source, eReference, oldProxy) -> {
			final URI oldUri = oldProxy.eProxyURI();
			final boolean mustProcessOldProxy = targetUriToProcess == null
					|| (oldUri != null && oldUri.trimFragment().equals(targetUriToProcess));
			if (mustProcessOldProxy) {
				final EObject newProxy = convertProxyUriToM2M(sourceUri, oldProxy, copy);
				if (copy) {
					if (newProxy != null && newProxy != oldProxy) {
						EcoreUtilN4.doWithDeliver(false, () -> {
							EcoreUtil.replace(source, eReference, oldProxy, newProxy);
						}, source);
					}
				}
			}
		});
	}

	/**
	 * For all proxies in all {@link TModule}s contained in the given resource set, convert their URIs to
	 * {@link N4JSGlobals#URI_FRAGMENT_PREFIX_M2M m2m URIs}. Similar to
	 * {@link #convertAllProxiesToM2M(ResourceSet, URI)}, but copying proxies is mandatory in this case.
	 *
	 * @param targetUriToProcess
	 *            if non-<code>null</code>, will limit conversion to proxies that point to a resource with this URI.
	 */
	public static final void convertAllProxiesToM2M(ResourceSet resourceSet, URI targetUriToProcess) {
		for (Resource currResource : resourceSet.getResources()) {
			if (currResource instanceof N4JSResource) {
				final URI currUri = currResource.getURI();
				final String currFileExt = currUri.fileExtension();
				if (N4JSGlobals.N4JS_FILE_EXTENSION.equals(currFileExt)
						|| N4JSGlobals.N4JSD_FILE_EXTENSION.equals(currFileExt)) {
					final TModule currModule = ((N4JSResource) currResource).getModule();
					if (currModule != null) {
						// note: always have to set argument 'copy' to true in next line, because different resources
						// might point to the identical proxy but will then need different m2m URIs!
						convertAllProxiesToM2M(currModule, currUri, targetUriToProcess, true);
					}
				}
			}
		}
	}
}
