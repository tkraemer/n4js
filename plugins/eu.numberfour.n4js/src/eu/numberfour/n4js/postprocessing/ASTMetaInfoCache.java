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

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.util.CancelIndicator;

import eu.numberfour.n4js.n4JS.Block;
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression;
import eu.numberfour.n4js.resource.N4JSResource;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory;
import eu.numberfour.n4js.ts.types.TypableElement;
import eu.numberfour.n4js.typesystem.N4JSTypeSystem;
import eu.numberfour.n4js.utils.UtilN4;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * The <em>AST meta-info cache</em> is created and filled with information during post-processing of an N4JS resource
 * and contains meta information on the AST, such as types of AST nodes, links from declarations to all places where the
 * declared element is used, etc.
 * <p>
 * It is created only for {@link N4JSResource}s and only for such N4JS resources that are <u>loaded from source code</u>
 * and therefore have an AST (not those that are loaded from the Xtext index / TModule).
 */
public final class ASTMetaInfoCache {

	// ################################################################################################################
	// main content of the cache
	// (add new properties here; getters should be public, setters should have package visibility)

	private final N4JSResource resource;
	private final Map<TypableElement, Result<TypeRef>> actualTypes = new HashMap<>();
	private final Map<ParameterizedCallExpression, List<TypeRef>> inferredTypeArgs = new HashMap<>();

	ASTMetaInfoCache(N4JSResource resource) {
		this.resource = resource;
	}

	/**
	 * Returns the {@link N4JSResource} the receiving cache belongs to.
	 */
	public N4JSResource getResource() {
		return resource;
	}

	/**
	 * <b>IMPORTANT:</b> most clients should use {@link N4JSTypeSystem#type(RuleEnvironment, TypableElement)} instead!
	 * <p>
	 * Returns the actual type of the given astNode as stored in the cache, or <code>null</code> if not available.
	 */
	public Result<TypeRef> getTypeFailSafe(TypableElement astNode) {
		return actualTypes.get(astNode);
	}

	/**
	 * <b>IMPORTANT:</b> most clients should use {@link N4JSTypeSystem#type(RuleEnvironment, TypableElement)} instead!
	 * <p>
	 * Returns the actual type of the given astNode as stored in the cache. Throws exception if not available.
	 */
	public Result<TypeRef> getType(TypableElement astNode) {
		final Result<TypeRef> result = getTypeFailSafe(astNode);
		if (result == null) {
			if (isCanceled()) {
				return new Result<>(TypeRefsFactory.eINSTANCE.createUnknownTypeRef());
			} else {
				throw UtilN4.reportError(new IllegalStateException(
						"cache miss: no actual type in cache for AST node: " + astNode
								+ " in resource: " + resource.getURI()));
			}
		}
		return result;
	}

	void storeType(TypableElement astNode, TypeRef actualType) {
		storeType(astNode, new Result<>(actualType));
	}

	void storeType(TypableElement astNode, Result<TypeRef> actualType) {
		if (!isProcessingInProgress()) {
			throw new IllegalStateException();
		}
		if (actualType == null) {
			throw new IllegalArgumentException("actualType may not be null");
		}
		if (actualTypes.put(astNode, actualType) != null) {
			throw UtilN4.reportError(new IllegalStateException(
					"cache collision: multiple actual types put into cache for AST node: " + astNode +
							" in resource: " + resource.getURI()));
		}
	}

	/**
	 * Returns the inferred type arguments of the given generic, non-parameterized call expression or <code>null</code>
	 * if the given call expression is not generic or is parameterized (i.e. type arguments given in the expression) or
	 * type argument inference has not taken place yet.
	 */
	public List<TypeRef> getInferredTypeArgs(ParameterizedCallExpression callExpr) {
		return inferredTypeArgs.get(callExpr);
	}

	void storeInferredTypeArgs(ParameterizedCallExpression callExpr, List<TypeRef> typeArgs) {
		if (!isProcessingInProgress()) {
			throw new IllegalStateException();
		}
		inferredTypeArgs.put(callExpr, Collections.unmodifiableList(new ArrayList<>(typeArgs)));
	}

	// ################################################################################################################
	// helper variables used *only* by post-processors in package eu.numberfour.n4js.postprocessing

	private boolean isProcessingInProgress = false;
	private boolean isFullyProcessed = false;
	CancelIndicator cancelIndicator = null;

	final Set<EObject> forwardProcessedSubTrees = new LinkedHashSet<>();
	final Set<EObject> astNodesCurrentlyBeingTyped = new LinkedHashSet<>();
	final Queue<Block> postponedSubTrees = new LinkedList<>(); // using LinkedList as FIFO queue, here

	/* package */ boolean isProcessingInProgress() {
		return isProcessingInProgress;
	}

	/* package */ boolean isFullyProcessed() {
		return isFullyProcessed;
	}

	/* package */ boolean isCanceled() {
		return cancelIndicator != null && cancelIndicator.isCanceled();
	}

	/* package */ boolean isEmpty() {
		// only used for debugging to spot a suspicious cache clear (see ASTMetaInfoCacheHelper)
		return actualTypes.isEmpty() && inferredTypeArgs.isEmpty();
	}

	/* package */ void startProcessing(@SuppressWarnings("hiding") CancelIndicator cancelIndicator) {
		if (isProcessingInProgress || isFullyProcessed) {
			// this method should never be called more than once per N4JSResource
			throw new IllegalStateException("multiple invocation of method ASTMetaInfoCache#startProcessing()");
		}
		isProcessingInProgress = true;
		this.cancelIndicator = cancelIndicator; // may be null
	}

	/* package */ void endProcessing() {
		if (!isProcessingInProgress) {
			throw new IllegalStateException("invalid invocation of method ASTMetaInfoCache#endProcessing()");
		}
		isFullyProcessed = true;
		isProcessingInProgress = false;
		cancelIndicator = null;
		forwardProcessedSubTrees.clear();
		astNodesCurrentlyBeingTyped.clear();
		postponedSubTrees.clear();
	}
}
