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
import eu.numberfour.n4js.n4JS.CatchBlock
import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ForStatement
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.N4ClassifierDeclaration
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4SetterDeclaration
import eu.numberfour.n4js.n4JS.NamedImportSpecifier
import eu.numberfour.n4js.n4JS.PropertyGetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyMethodDeclaration
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.PropertySetterDeclaration
import eu.numberfour.n4js.n4JS.SetterDeclaration
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.resource.N4JSPostProcessor
import eu.numberfour.n4js.resource.N4JSResource
import eu.numberfour.n4js.ts.types.TypableElement
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import eu.numberfour.n4js.utils.languages.N4LanguageUtils
import it.xsemantics.runtime.RuleEnvironment
import java.util.ArrayList
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.resource.XtextResource
import org.eclipse.xtext.util.CancelIndicator

import static extension eu.numberfour.n4js.utils.N4JSLanguageUtils.*

/**
 * Main processor used during {@link N4JSPostProcessor post-processing} of N4JS resources. It controls the overall
 * work flow of processing the AST, but does not do any actual work; instead, it delegates to the other processors:
 * <ul>
 * <li>{@link TypeProcessor}, which delegates further to
 *     <ul>
 *     <li>{@link PolyProcessor}, which delegates further to
 *         <ul>
 *         <li>{@link PolyProcessor_ArrayLiteral}
 *         <li>{@link PolyProcessor_ObjectLiteral}
 *         <li>{@link PolyProcessor_FunctionExpression}
 *         <li>{@link PolyProcessor_CallExpression}
 *         </ul>
 *     <li>{@link DestructureProcessor}
 *     </ul>
 * <li>{@code TypeExpectedProcessor} (coming soon!)
 * <li>{@link TypeDeferredProcessor}
 * </ul>
 */
@Singleton
public class ASTProcessor extends AbstractProcessor {

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private ASTMetaInfoCacheHelper astMetaInfoCacheHelper;
	@Inject
	private TypeProcessor typeProcessor;
	@Inject
	private TypeDeferredProcessor typeDeferredProcessor;
	@Inject
	private ArrowFunctionProcessor arrowFunctionProcessor;

	/**
	 * Called from N4JSPostProcessor.
	 * Will throw IllegalStateException if called more than once per N4JSResource.
	 * 
	 * @param resource  may not be null.
	 * @param cancelIndicator  may be null.
	 */
	def public void processAST(N4JSResource resource, CancelIndicator cancelIndicator) {
		if (resource === null)
			throw new IllegalArgumentException("resource may not be null");

		// the following is required, because typing may have been initiated by resolution of a proxy
		// -> when traversing the AST, we will sooner or later try to resolve this same proxy, which would be
		// interpreted as a cyclic proxy resolution by method LazyLinkingResource#getEObject(String,Triple)
		resource.clearResolving();

		val G = RuleEnvironmentExtensions.newRuleEnvironment(resource);
		processAST(G, resource, cancelIndicator); // will throw exception if called more than once per resource
	}

	/**
	 * Initiates processing of the entire AST in the given resource.
	 * Will throw IllegalStateException if called more than once per N4JSResource.
	 * 
	 * @param resource  may not be null.
	 * @param cancelIndicator  may be null.
	 */
	def private void processAST(RuleEnvironment G, N4JSResource resource, CancelIndicator cancelIndicator) {

		log(0, "### processing resource: " + resource.URI);

		val cache = astMetaInfoCacheHelper.getOrCreate(resource);
		cache.startProcessing(cancelIndicator); // will throw exception if processing already in progress or completed
		try {
			// step 1: main processing
			val script = resource.script;
			processSubtree(G, script, cache, 0);
			// step 2: processing of postponed subtrees (only Blocks, so far)
			var Block block;
			while ((block = cache.postponedSubTrees.poll) !== null) {
				// note: we need to allow adding more postponed subtrees inside this loop!
				processSubtree(G, block, cache, 0);
			}
		} finally {
			if (cache.canceled) {
				log(0, "CANCELED by cancelIndicator");
			}

			// note: doesn't matter if processing succeeded, failed or was canceled
			// (even if it failed or was canceled, we do not want to try again)
			cache.endProcessing();

			if (isDEBUG_LOG_RESULT) {
				log(0, "### result for " + resource.URI);
				log(4, resource.script, cache);
			}
			log(0, "### done: " + resource.URI);
		}
	}

	/**
	 * Process given node and all of its direct and indirect children.
	 * 
	 * @param node  the root of the subtree to process; must be an AST node.
	 */
	def package void processSubtree(RuleEnvironment G, EObject node, ASTMetaInfoCache cache, int indentLevel) {
		assertTrueIfRigid(cache, "argument 'node' must be an AST node", node.isASTNode);

		log(indentLevel, "processing: " + node.objectInfo);

		if (cache.canceled) {
			return;
		}

		// already done as part of a forward processing?
		if (cache.forwardProcessedSubTrees.contains(node)) {
			if (isDEBUG_LOG) {
				log(indentLevel, "(subtree already processed as a forward reference)");
				if(node instanceof TypableElement) {
					log(indentLevel, cache.getTypeFailSafe(node));
				}
			}
			return;
		}

		if (!cache.astNodesCurrentlyBeingTyped.add(node)) {
			// this subtree is currently being processed
			// (can happen, for example, if we are processing a member (e.g. field) and during that processing we
			// encounter a reference to the containing class (e.g. in the initializer expression))
			if (isDEBUG_LOG) {
				log(indentLevel, "(subtree currently in progress - skipping)");
			}
			return;
		}
		try {

			// process node itself - part 1 (before child processing)
			processNode_preChildren(G, node, cache, indentLevel);

			// process the children
			val children = childrenToBeProcessed(G, node);
			for(child : children) {
				if(child instanceof Block && (
						child.eContainer instanceof FunctionExpression
						|| child.eContainer instanceof PropertyGetterDeclaration
						|| child.eContainer instanceof PropertySetterDeclaration
						|| child.eContainer instanceof PropertyMethodDeclaration)) {
					// postpone
					cache.postponedSubTrees.add(child as Block);
				} else {
					// process now
					processSubtree(G, child, cache, indentLevel + 1);
					if (cache.canceled) {
						return;
					}
				}
			}

			// process node itself - part 2 (after child processing)
			processNode_postChildren(G, node, cache, indentLevel);

			// we're done with this node, but make sure that all proxies have actually been resolved
			// (this is important mainly for two reasons: (1) post-processing is often triggered by a call to
			// N4JSResource#resolveLazyCrossReferences(CancelIndicator), so we have to guarantee that all lazy
			// cross-references are actually resolved; (2) the type system may not resolve all proxies and some
			// nodes are not typed at all (i.e. isTypableNode() returns false), so we have to enforce this here.
			 
			// We also perform all processing, related to outgoing references from the current node at this point.	
			resolveAndProcessReferencesInNode(node, cache);

		} finally {
			cache.astNodesCurrentlyBeingTyped.remove(node);
		}
	}

	/**
	 * Forward-process given node and all of its direct and indirect children.
	 * <p>
	 * Via this method, other processors can request a forward processing of some subtree. Does nothing if the given
	 * node was processed already, either as part of a forward reference or during normal processing.
	 * 
	 * @return <code>true</code> iff the forward processing is legal, <code>false</code> otherwise.
	 */
	def package boolean processSubtree_forwardReference(RuleEnvironment G, TypableElement node, ASTMetaInfoCache cache) {
		assertTrueIfRigid(cache, "argument 'node' must be an AST node", node.isASTNode);

		// is node a valid target for a forward reference (i.e. an identifiable subtree)?
		val valid = node.isIdentifiableSubtree || node.isExceptionCaseOfForwardReferencableSubtree;
		if (!valid) {
			val resource = node.eResource as XtextResource
			if (resource !== null) {
				assertTrueIfRigid(cache,
					"forward reference only allowed to identifiable subtrees; but was: " + node + " in\n" +
						resource.parseResult.rootNode.text, valid)
			} else {
				assertTrueIfRigid(cache, "forward reference only allowed to identifiable subtrees; but was: " + node, valid);
			}
		}

		val fromCache = cache.getTypeFailSafe(node);
		if (fromCache !== null) {
			// already processed, nothing else to do
			// note: this is not an error, we may have many forward references to the same identifiable subtree
			return true;
		}

		if (cache.astNodesCurrentlyBeingTyped.contains(node)) {
			// cyclic forward reference
			// legal cases of a cyclic reference
			val isCyclicForwardReference = cache.astNodesCurrentlyBeingTyped.contains(node);
			if (isCyclicForwardReference && (
				node instanceof VariableDeclaration
				|| node instanceof N4ClassifierDeclaration
				|| node instanceof N4FieldDeclaration
				|| node instanceof FunctionDefinition // includes methods
				|| node instanceof N4GetterDeclaration || node instanceof N4SetterDeclaration
				|| (node instanceof PropertyNameValuePair && (node as PropertyNameValuePair).expression instanceof FunctionExpression)
				|| node instanceof PropertyGetterDeclaration || node instanceof PropertySetterDeclaration
			)) {
				return true;
			}

			// illegal cyclic node inference
			val msg = "*#*#*#*#*#* illegal cyclic forward reference to " + node.objectInfo + " (resource: "
				+ node.eResource?.URI + ")";
			logErr(msg);
			return false;
		} else if (isSemiCyclicForwardReferenceInForLoop(node, cache)) {
			// semi-cyclic forward reference
			// (this is deemed legal for the same reason why 'var x = 1+x;' is treated as a legal forward reference)
			return true;
		}

		if (cache.forwardProcessedSubTrees.contains(node)) {
			// we saw above that the cache does not contain anything for node, so this is an error
			throw new IllegalStateException
		}

		// actually perform the forward processing
		log(0, "===START of identifiable sub-tree below " + node.objectInfo);
		val G_fresh = RuleEnvironmentExtensions.newRuleEnvironment(G); // use a new, empty environment here
		processSubtree(G_fresh, node, cache, 0); // note how we reset the indent level
		cache.forwardProcessedSubTrees.add(node);
		log(0, "===END of identifiable sub-tree below " + node.objectInfo);

		return true;
	}


	// ---------------------------------------------------------------------------------------------------------------


	/**
	 * Top-down processing of AST nodes happens here, i.e. this method will see all AST nodes in a top-down order.
	 */
	def private void processNode_preChildren(RuleEnvironment G, EObject node, ASTMetaInfoCache cache, int indentLevel) {

		if (node instanceof FunctionDefinition) {
			handleAsyncFunctionDefinition(G, node, cache);
			handleGeneratorFunctionDefinition(G, node, cache);
		}

		typeDeferredProcessor.handleDeferredTypeRefs_preChildren(G, node, cache);
	}

	/**
	 * Bottom-up processing of AST nodes happens here, i.e. this method will see all AST nodes in a bottom-up order.
	 */
	def private void processNode_postChildren(RuleEnvironment G, EObject node, ASTMetaInfoCache cache, int indentLevel) {

		typeDeferredProcessor.handleDeferredTypeRefs_postChildren(G, node, cache);

		typeProcessor.typeNode(G, node, cache, indentLevel);

		// references to other files via import statements:
		if (node instanceof NamedImportSpecifier) {
			val elem = node.importedElement;
			if(elem!==null) {
				// make sure to use the correct type system for the other file (using our type system as a fall back)
				val tsCorrect = N4LanguageUtils.getServiceForContext(elem, N4JSTypeSystem).orElse(ts);
				// we're not interested in the type here, but invoking the type system will let us reuse
				// all the logic from method #xsemantics_type() above for handling references to other resources
				tsCorrect.type(G, elem);
			}
		}

		arrowFunctionProcessor.tweakArrowFunctions(G, node, cache);
	}


	// ---------------------------------------------------------------------------------------------------------------


	def private List<EObject> childrenToBeProcessed(RuleEnvironment G, EObject obj) {
		// order in return value is important!
		return switch (obj) {
			SetterDeclaration: {
				// process formal parameter before body
				obj.eContents.bringToFront(obj.fpar)
			}
			FunctionDefinition: {
				// process formal parameters before body
				obj.eContents.bringToFront(obj.fpars)
			}
			CatchBlock: {
				// process catch variable before block
				obj.eContents.bringToFront(obj.catchVariable)
			}
			ForStatement: {
				// process expression before varDeclOrBindings
				obj.eContents.bringToFront(obj.expression)
			}
			default: {
				obj.eContents
			}
		};
	}


	// ---------------------------------------------------------------------------------------------------------------


	/**
	 * Normally, forward references are allowed only to {@link N4JSLanguageUtils#isIdentifiableSubtree(EObject)
	 * identifiable subtrees}. However, there are exception cases that are also allowed and this method returns
	 * <code>true</code> for those cases.
	 */
	def private static boolean isExceptionCaseOfForwardReferencableSubtree(EObject astNode) {
		isExpressionInForOf(astNode)
	}
	def private static boolean isExpressionInForOf(EObject astNode) {
		astNode instanceof Expression && astNode.eContainer instanceof ForStatement
			&& (astNode.eContainer as ForStatement).isForOf
			&& astNode.eContainingFeature===N4JSPackage.eINSTANCE.iterationStatement_Expression;
	}

	/**
	 * Returns true if we have a semi-cyclic reference to a variable declaration in a for in/of loop.
	 * For example:
	 * <pre>
	 * for(var x of foo(x)) {}
	 * </pre>
	 */
	def package boolean isSemiCyclicForwardReferenceInForLoop(EObject node, ASTMetaInfoCache cache) {
		if (node instanceof VariableDeclaration) {
			val parent = node.eContainer;
			if (parent instanceof ForStatement) {
				return (parent.forIn || parent.forOf) && cache.astNodesCurrentlyBeingTyped.contains(parent.expression);
			}
		}
		return false;
	}

	def private void resolveAndProcessReferencesInNode(EObject astNode, ASTMetaInfoCache cache) {
		for(eRef : astNode.eClass.EReferences) {
			if(!eRef.isContainment) { // only cross-references have proxies (in our case)
				val node = astNode.eGet(eRef, true);
				
				if (node instanceof EObject) {
					recordReferencesToLocalVariables(eRef, astNode, node, cache);
				}
			}
		}
	}

	def private recordReferencesToLocalVariables(EReference reference, EObject sourceNode, EObject targetNode,
		ASTMetaInfoCache cache) {

		// If targetNode is still a proxy its resolution failed, 
		// therefore it should be skipped.
		if (targetNode.eIsProxy) {
			return;
		}
		// skip non-local references		
		if (sourceNode.eResource !== targetNode.eResource) {
			return;
		}
		if (targetNode instanceof VariableDeclaration) {
			// don't save references to exported variable declarations
			if (targetNode instanceof ExportedVariableDeclaration) {
				return;
			}
			
			cache.storeLocalVariableReference(targetNode, sourceNode);
		}
	}

	def private <T> List<T> bringToFront(List<T> l, T... elements) {
		val result = new ArrayList(l);
		val elemSanitized = elements.filterNull.toList;
		result.removeAll(elemSanitized);
		result.addAll(0, elemSanitized);
		return result;
	}
}
