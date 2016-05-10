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

import eu.numberfour.n4js.N4JSGlobals;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.validation.helper.N4JSLanguageConstants;

/**
 *
 */
public class M2MUriUtil {

	private static final int PREFIX_LEN = N4JSGlobals.URI_FRAGMENT_PREFIX_M2M.length();

	public static final boolean isM2MUri(URI uri) {
		return uri != null && isM2MUriFragment(uri.fragment());
	}

	public static final boolean isM2MUriFragment(String fragment) {
		return fragment != null && fragment.startsWith(N4JSGlobals.URI_FRAGMENT_PREFIX_M2M);
	}

	public static final URI convertToM2M(URI baseUri, URI uri) {
		return baseUri.appendFragment(URI.encodeFragment(
				N4JSGlobals.URI_FRAGMENT_PREFIX_M2M + uri.toString(), false));
	}

	public static final URI convertFromM2M(String m2mUriFragment) {
		return URI.createURI(URI.decode(m2mUriFragment.substring(PREFIX_LEN)));
	}

	/**
	 * If and only if the given EObject is a proxy, its proxy URI will be converted to a
	 * {@link N4JSLanguageConstants#URI_FRAGMENT_PREFIX_M2M m2m URI}.
	 * <p>
	 * For example, assuming we are deserializing the TModule of a resource B.n4js having a reference to another
	 * resource C.n4js in form of a proxy with an original URI of
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
	 * When this proxy is resolved, resource B.n4js will get the chance to do some special handling before resource
	 * C.n4js will be loaded, see method {@link N4JSResource#getEObjectFromTModuleOfRemoteResource(URI,boolean)}.
	 */
	@SuppressWarnings("javadoc")
	public static final void convertProxyUriToM2M(URI baseUri, EObject potentialProxy) {
		if (potentialProxy.eIsProxy()) {
			final URI proxyUri = ((BasicEObjectImpl) potentialProxy).eProxyURI();
			if (proxyUri != null && !proxyUri.isRelative() && proxyUri.hasFragment()) {
				// make sure proxy points to another resource (required because links from TModule to AST will be
				// represented as proxies within the same resource!)
				if (!proxyUri.trimFragment().equals(baseUri)) {
					final URI newUri = M2MUriUtil.convertToM2M(baseUri, proxyUri);
					((BasicEObjectImpl) potentialProxy).eSetProxyURI(newUri);
				}
			}
		}
	}
}
