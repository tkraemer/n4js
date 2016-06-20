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
package eu.numberfour.n4js.postprocessing

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.Block
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import it.xsemantics.runtime.Result
import java.util.ArrayList
import java.util.Collections
import java.util.List
import java.util.Map
import java.util.Queue
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.resource.Resource
import org.eclipse.xtext.util.CancelIndicator
import org.eclipse.xtext.util.IResourceScopeCache

import static eu.numberfour.n4js.utils.UtilN4.reportError

/**
 * FIXME
 */
@Singleton
public class TypingCacheHelper {

	@Inject
	private IResourceScopeCache mainCacheHelper;


	public static final class TypingCache {
		public final Resource resource;
		private final Map<EObject, Result<TypeRef>> actualTypes = newHashMap;
		private final Map<ParameterizedCallExpression, List<TypeRef>> inferredTypeArgs = newHashMap;

		public boolean isTypingInProgress = false;
		public boolean isFullyTyped = false;

		public CancelIndicator cancelIndicator;

		/** While walking the AST, this set will contain AST nodes that have been processed as part of a forward reference. */
		public final Set<EObject> forwardProcessedSubTrees = newLinkedHashSet; // TODO make this non-null only while isTypingInProgress===true
		public final Set<EObject> astNodesCurrentlyBeingTyped = newLinkedHashSet;
		public final Queue<Block> postponedSubTrees = newLinkedList; // using LinkedList as FIFO queue, here

		new(Resource resource) {
			this.resource = resource;
		}

		def void put(EObject astNode, Result<TypeRef> actualType) {
			if (actualType === null) {
				throw new IllegalArgumentException("actualType may not be null");
			}
			if (actualTypes.put(astNode, actualType) !== null) {
				throw reportError(new IllegalStateException(
					"cache collision: multiple actual types put into cache for AST node: " + astNode +
						" in resource: " + resource.URI));
			}
		}

		def Result<TypeRef> get(EObject astNode) {
			val result = getFailSafe(astNode);
			if (result === null) {
				throw reportError(new IllegalStateException(
					"cache miss: no actual type in cache for AST node: " + astNode + " in resource: " + resource.URI));
			}
			return result;
		}

		def Result<TypeRef> getFailSafe(EObject astNode) {
			return actualTypes.get(astNode);
		}

		def boolean isCanceled() {
			return cancelIndicator !== null && cancelIndicator.isCanceled;
		}
	}


	/**
	 * Only {@link ASTProcessor} and maybe some of the other processors should use {@link TypingCache} directly, all
	 * other code should only use the methods in {@link TypingCacheHelper}, e.g. {@link #typeOf(EObject)}.
	 */
	def package TypingCache getOrCreate(Resource res) {
		mainCacheHelper.get(TypingCache, res, [
			val newCache = new TypingCache(res);

			// use the following debug code to track cache creation/deletion
//			println("!! creating new cache "+Integer.toHexString(newCache.hashCode)+" (on resource "+Integer.toHexString(res.hashCode)+"; URI: "+res.URI+")");
//			(mainCacheHelper as OnChangeEvictingCache).getOrCreate(res).addCacheListener[cacheAdapter|
//				if(!newCache.actualTypes.empty) {
//					println("!!!!! clearing non-empty cache "+Integer.toHexString(newCache.hashCode));
//				} else {
//					println("!!!!! clearing empty cache "+Integer.toHexString(newCache.hashCode));
//				}
//				if(newCache.isTypingInProgress) {
//					println("!!!!! WARNING suspicious cache clear (cache "+Integer.toHexString(newCache.hashCode)+")");
//				}
//			];

			return newCache;
		]);
	}

	def package void store(EObject astNode, Result<TypeRef> actualType) {
		getOrCreate(astNode.eResource()).put(astNode, actualType);

		// use the following debug code to track leaked inference variables
//		for(tv : TypeUtils.getReferencedTypeVars(actualType.value)) {
//			val ch=if(tv.name!==null && tv.name.length>0) tv.name.charAt(0) else 0;
//			if(!((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))) {
//				System.err.println("!!!!! leaked inference variable: "+actualType.value.typeRefAsString+"  on: "+astNode);
//			}
//		}
	}

	/**
	 * Returns the actual type of the given astNode or <code>null</code> if not available.
	 * <p>
	 * IMPORTANT: for the time being, most client code should continue to use {@link N4JSTypeSystem#type(it.xsemantics.runtime.RuleEnvironment, eu.numberfour.n4js.ts.model.TypableElement) N4JSTypeSystem#type()}
	 * instead.
	 */
	def TypeRef typeOf(EObject astNode) {
		val Result<TypeRef> result = resultOf(astNode);
		return if (result !== null) result.getValue() else null;
	}

	/**
	 * Returns the type inference {@link Result} of the given astNode or <code>null</code> if not available.
	 * <p>
	 * IMPORTANT: for the time being, most client code should continue to use {@link N4JSTypeSystem#type(it.xsemantics.runtime.RuleEnvironment, eu.numberfour.n4js.ts.model.TypableElement) N4JSTypeSystem#type()}
	 * instead.
	 */
	def Result<TypeRef> resultOf(EObject astNode) {
		return getOrCreate(astNode.eResource()).getFailSafe(astNode);
	}

	def package void storeInferredTypeArgs(ParameterizedCallExpression callExpr, List<TypeRef> typeArgs) {
		getOrCreate(callExpr.eResource()).inferredTypeArgs.put(callExpr,
			Collections.unmodifiableList(new ArrayList(typeArgs)));
	}

	/**
	 * Returns the inferred type arguments of the given generic, non-parameterized call expression or <code>null</code>
	 * if the given call expression is not generic or is parameterized or type argument inference has not taken place
	 * yet.
	 */
	def List<TypeRef> getInferredTypeArgs(ParameterizedCallExpression callExpr) {
		return getOrCreate(callExpr.eResource()).inferredTypeArgs.get(callExpr);
	}
}
