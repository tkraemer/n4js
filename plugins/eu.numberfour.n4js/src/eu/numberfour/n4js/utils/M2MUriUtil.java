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

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.resource.UserdataMapper;

/**
 * Utility methods for handling <em>module-to-module URIs</em> (a.k.a. "m2m URIs"). These URIs are created by the
 * {@link UserdataMapper} after deserializing a TModule from the index to allow some special handling when resolving
 * references from the deserialized module to another TModule in a different resource. This special handling is located
 * in method {@link N4JSResource#getEObjectFromM2MUri(String, boolean)}.
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
	 * If and only if the given EObject is a proxy, its proxy URI will be converted to a
	 * {@link N4JSGlobals#URI_FRAGMENT_PREFIX_M2M m2m URI}.
	 *
	 * @param baseUri
	 *            resource containing the <code>potentialProxy</code>.
	 * @param potentialProxy
	 *            the EObject to convert, in case it is a proxy.
	 */
	public static final void convertProxyUriToM2M(URI baseUri, EObject potentialProxy) {
		if (potentialProxy.eIsProxy()) {
			final URI proxyUri = ((BasicEObjectImpl) potentialProxy).eProxyURI();
			if (proxyUri != null && !proxyUri.isRelative() && proxyUri.hasFragment() && !isM2MUri(proxyUri)) {
				// make sure proxy points to another resource (required because links from TModule to AST of same
				// resource will be represented as proxies and we do not want to convert those!)
				if (!proxyUri.trimFragment().equals(baseUri)) {
					final URI newUri = M2MUriUtil.convertToM2M(baseUri, proxyUri);
					((BasicEObjectImpl) potentialProxy).eSetProxyURI(newUri);
				}
			}
		}
	}
}
