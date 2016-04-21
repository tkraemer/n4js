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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.URIHandlerImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.xtext.resource.IEObjectDescription;

import com.google.common.base.Charsets;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;

import eu.numberfour.n4js.ts.types.TModule;
import eu.numberfour.n4js.ts.utils.TypeUtils;
import eu.numberfour.n4js.utils.EcoreUtilN4;

/**
 * The user data for exported modules contains a serialized representation of the module's content. This allows to
 * restore the type model without parsing or linking the complete JS file.
 *
 * The {@link UserdataMapper} provides this serialized representation and the logic to recreate the {@link EObject
 * types} from that.
 */
public class UserdataMapper {
	/**
	 * Logger for this class
	 */
	private static final Logger LOGGER = Logger.getLogger(UserdataMapper.class);

	/**
	 * The key in the user data map of the module's description.
	 */
	public final static String USERDATA_KEY_SERIALIZED_SCRIPT = "serializedScript";
	/**
	 * The key in the user data map of static-polyfill contents-hash
	 */
	public final static String USERDATA_KEY_STATIC_POLYFILL_CONTENTHASH = "staticPolyfillContentHash";

	/**
	 * Flag indicating whether the string representation contains binary or human readable data.
	 */
	private final static Boolean BINARY = Boolean.TRUE;

	private final static String TRANSFORMATION_CHARSET_NAME = Charsets.UTF_8.name();

	private static class LocalResourceAwareURIHandler extends URIHandlerImpl {

		private final URI resourceURI;

		LocalResourceAwareURIHandler(URI resourceURI) {
			this.resourceURI = resourceURI;
		}

		@Override
		public URI deresolve(URI uri) {
			if (uri.trimFragment().equals(resourceURI)) {
				return super.deresolve(URI.createURI("#" + uri.fragment()));
			}
			return super.deresolve(uri);
		}

		@Override
		public URI resolve(URI uri) {
			if (uri.trimFragment().toString().isEmpty()) {
				return resourceURI.appendFragment(uri.fragment());
			}
			return super.resolve(uri);
		}
	}

	/**
	 * Serializes an exported script (or other {@link EObject}) stored in given resource content at index 1, and stores
	 * that in a map under key {@link #USERDATA_KEY_SERIALIZED_SCRIPT}.
	 */
	public Map<String, String> createUserData(final TModule exportedModule) throws IOException,
			UnsupportedEncodingException {
		if (exportedModule.isPreLinkingPhase()) {
			throw new AssertionError("Module may not be from the preLinkingPhase");
		}
		if (EcoreUtilN4.hasUnresolvedProxies(exportedModule)) {
			return createTimestampUserData(exportedModule);
		}
		final Resource originalResource = exportedModule.eResource();

		// resolve resource (i.e. resolve lazy cross-references, resolve ComputedTypeRefs, etc.)
		if (originalResource instanceof N4JSResource)
			((N4JSResource) originalResource).performPostProcessing();
		if (!exportedModule.isPreLinkingPhase()) {
			// TODO consider moving the following check into #performPostProcessing() or some similar place
			TypeUtils.assertNoDeferredTypeRefs(exportedModule);
		}

		// add copy -- EObjects can only be contained in a single resource, and
		// we do not want to
		// mess up the original resource
		URI resourceURI = originalResource.getURI();
		XMIResource resourceForUserData = new XMIResourceImpl(resourceURI);

		resourceForUserData.getContents().add(TypeUtils.copyWithProxies(exportedModule));

		ByteArrayOutputStream baos = new ByteArrayOutputStream();

		resourceForUserData.save(baos, getOptions(resourceURI, BINARY));

		String serializedScript = BINARY ? XMLTypeFactory.eINSTANCE.convertBase64Binary(baos.toByteArray())
				: baos.toString(TRANSFORMATION_CHARSET_NAME);

		final HashMap<String, String> ret = new HashMap<>();
		ret.put(USERDATA_KEY_SERIALIZED_SCRIPT, serializedScript);

		ret.put(N4JSResourceDescriptionStrategy.MAIN_MODULE_KEY, Boolean.toString(exportedModule.isMainModule()));

		// in case of filling file store fingerprint to keep filled type updated by the incremental builder.
		// required to trigger rebuilds even if only minor changes happened to the content.
		if (exportedModule.isStaticPolyfillModule() && originalResource instanceof N4JSResource) {
			N4JSResource n4jsres = (N4JSResource) originalResource;
			String contentHash = Integer.toHexString(n4jsres.getParseResult().getRootNode().hashCode());
			ret.put(USERDATA_KEY_STATIC_POLYFILL_CONTENTHASH, contentHash);
		}
		return ret;
	}

	/**
	 * Creates user data with the modification stamp of the resource. This is done to have a nice reaction on changed
	 * models that are otherwise broken, e.g no module will be written to the index. We still want to have change
	 * affection, though.
	 */
	public Map<String, String> createTimestampUserData(TModule module) {
		Resource resource = module.eResource();
		long timestamp = 0L;
		if (resource instanceof N4JSResource) {
			timestamp = ((N4JSResource) resource).getModificationStamp();
		} else {
			timestamp = System.currentTimeMillis();
		}
		return Collections.singletonMap("timestamp", String.valueOf(timestamp));
	}

	private Map<Object, Object> getOptions(URI resourceURI, Boolean binary) {
		return ImmutableMap.<Object, Object> of(XMLResource.OPTION_BINARY, binary, XMLResource.OPTION_URI_HANDLER,
				new LocalResourceAwareURIHandler(resourceURI));
	}

	/**
	 * Creates exported scripts (or other {@link EObject}s found (serialized) in the user data of given
	 * {@link IEObjectDescription}.
	 *
	 * @return deserialized types as EObject
	 */
	public List<EObject> getDeserializedModulesFromDescription(IEObjectDescription eObjectDescription, URI uri) {
		String serializedData = eObjectDescription.getUserData(USERDATA_KEY_SERIALIZED_SCRIPT);
		if (Strings.isNullOrEmpty(serializedData)) {
			return new ArrayList<>();
		}
		XMIResource xres = new XMIResourceImpl(uri);
		try {
			boolean binary = !serializedData.startsWith("<");
			ByteArrayInputStream bais = new ByteArrayInputStream(
					binary ? XMLTypeFactory.eINSTANCE.createBase64Binary(serializedData)
							: serializedData.getBytes(TRANSFORMATION_CHARSET_NAME));
			Map<Object, Object> options = getOptions(uri, binary);
			xres.load(bais, options);
		} catch (Exception e) {
			LOGGER.error("Error deserializing type", e); //$NON-NLS-1$
			throw new WrappedException(e); // TODO reconsider this
		}
		List<EObject> result = Lists.newArrayList(xres.getContents());
		xres.getContents().clear();
		// TModules are flattened before serialization (i.e. all DeferredTypeRefs resolved & removed),
		// but we double-check that at this point
		for (EObject currObj : result) {
			if (currObj instanceof TModule) {
				TModule currTModule = (TModule) currObj;
				if (!currTModule.isPreLinkingPhase()) {
					TypeUtils.assertNoDeferredTypeRefs(currTModule);
				}
			}
		}
		return result;
	}
}
