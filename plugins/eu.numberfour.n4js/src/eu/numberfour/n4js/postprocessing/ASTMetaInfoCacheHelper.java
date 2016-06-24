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
package eu.numberfour.n4js.postprocessing;

import java.util.List;

import org.eclipse.xtext.util.IResourceScopeCache;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import eu.numberfour.n4js.n4JS.ParameterizedCallExpression;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.types.TypableElement;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Front-end for accessing the {@link ASTMetaInfoCache}.
 */
@Singleton
public final class ASTMetaInfoCacheHelper {

	@Inject
	private IResourceScopeCache resourceScopeCacheHelper;

	/**
	 * <b>IMPORTANT:</b> most clients should use {@link N4JSTypeSystem#type(RuleEnvironment, TypableElement)} instead!
	 * <p>
	 * Convenience method for {@link ASTMetaInfoCache#getTypeFailSafe(TypableElement)}.
	 */
	public TypeRef getTypeFailSafe(TypableElement astNode) {
		final Result<TypeRef> result = getOrCreate((N4JSResource) astNode.eResource()).getTypeFailSafe(astNode);
		return result != null ? result.getValue() : null;
	}

	/**
	 * Convenience method for {@link ASTMetaInfoCache#getInferredTypeArgs(ParameterizedCallExpression)}.
	 */
	public List<TypeRef> getInferredTypeArgs(ParameterizedCallExpression callExpr) {
		return getOrCreate((N4JSResource) callExpr.eResource()).getInferredTypeArgs(callExpr);
	}

	/**
	 * Returns the {@link ASTMetaInfoCache} of the given resource, optionally creating it if it does not exist already.
	 */
	public ASTMetaInfoCache getOrCreate(N4JSResource res) {
		return resourceScopeCacheHelper.get(ASTMetaInfoCache.class, res, () -> {
			final ASTMetaInfoCache newCache = new ASTMetaInfoCache(res);

			// use the following debug code to track cache creation/deletion
			// println("!! creating new cache "+Integer.toHexString(newCache.hashCode)+" (on resource
			// "+Integer.toHexString(res.hashCode)+"; URI: "+res.URI+")");
			// (mainCacheHelper as OnChangeEvictingCache).getOrCreate(res).addCacheListener[cacheAdapter|
			// if(!newCache.actualTypes.empty) {
			// println("!!!!! clearing non-empty cache "+Integer.toHexString(newCache.hashCode));
			// } else {
			// println("!!!!! clearing empty cache "+Integer.toHexString(newCache.hashCode));
			// }
			// if(newCache.isTypingInProgress) {
			// println("!!!!! WARNING suspicious cache clear (cache "+Integer.toHexString(newCache.hashCode)+")");
			// }
			// ];

			return newCache;
		});
	}
}
