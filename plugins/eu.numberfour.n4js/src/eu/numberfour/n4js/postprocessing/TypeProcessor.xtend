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
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.FieldAccessor
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.N4ClassExpression
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.n4JS.NewExpression
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.SetterDeclaration
import eu.numberfour.n4js.n4JS.ThisLiteral
import eu.numberfour.n4js.n4JS.TypeDefiningElement
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.postprocessing.TypingCacheHelper.TypingCache
import eu.numberfour.n4js.resource.N4JSResource
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.ts.types.SyntaxRelatedTElement
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.TStructMember
import eu.numberfour.n4js.ts.types.TypableElement
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import it.xsemantics.runtime.Result
import it.xsemantics.runtime.RuleApplicationTrace
import it.xsemantics.runtime.RuleEnvironment
import it.xsemantics.runtime.RuleFailedException
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import static extension eu.numberfour.n4js.utils.N4JSLanguageUtils.*
import eu.numberfour.n4js.typesystem.CustomInternalTypeSystem.RuleFailedExceptionWithoutStacktrace

/**
 * Processor for handling type inference during post-processing of an N4JS resource. Roughly corresponds to
 * 'type' judgment in Xsemantics, but handles also more complex cases, e.g. poly expressions.
 * <p>
 * Invoked from {@link ASTProcessor} and delegates to {@link PolyProcessor}s.
 */
@Singleton
public class TypeProcessor extends AbstractProcessor {

	@Inject
	private TypingCacheHelper typingCacheHelper;
	@Inject
	private ASTProcessor astProcessor;
	@Inject
	private PolyProcessor polyProcessor;
	@Inject
	private DestructureProcessor destructureProcessor;


	def void typeNode(RuleEnvironment G, EObject node, TypingCache cache, int indentLevel) {
		if (node instanceof TypableElement) {
			// we have a typable node
			if (N4JSASTUtils.isArrayOrObjectLiteralUsedAsDestructuringPattern(node)
				&& polyProcessor.isEntryPoint(node)) {
				// special case: array or object literal being used as a destructuring pattern
				log(indentLevel, "ignored (array or object literal being used as a destructuring pattern)")
				destructureProcessor.typeDestructuringPattern(G, node, cache, indentLevel);

			} else {
				// standard case
				typeNode(G, node, cache, indentLevel);
			}
		} else {
			// not a typable node
			log(indentLevel, "ignored (not a typable node: " + node?.eClass?.name + ")")
		}
	}

	def private void typeNode(RuleEnvironment G, TypableElement node, TypingCache cache, int indentLevel) {
		try {
			if (polyProcessor.isResponsibleFor(node)) {
				if (polyProcessor.isEntryPoint(node)) {
					log(indentLevel, "asking PolyComputer ...");
					polyProcessor.inferType(G, node as Expression, cache.cancelIndicator);
					// in this case, the polyComputer will store the type in the cache;
					// also, the poly computer is responsible for replacing all DeferredTypeRefs
					assertTrueIfRigid("poly computer did not replace DeferredTypeRef", [
						val typeModelElem = node.definedTypeModelElement;
						return typeModelElem === null || typeModelElem.eAllContents.filter(DeferredTypeRef).empty
					]);
				} else {
					// we have a poly expression, but one that is nested in another poly expression
					// -> ignore here, because polyComputer will deal with it when computing the parent poly expression
					log(indentLevel,
						"deferred (nested in poly expression --> will be inferred during inference of outer poly expression)");

					return; // return only required to avoid confusing logging of cache.getFailSafe(node) below
				}
			} else {
				// ordinary typing of typable AST nodes
				// -> simply ask Xsemantics
				log(indentLevel, "asking Xsemantics ...");
				val result = inferType(G, node);
				// in this case, we are responsible for storing the type in the cache
				// (Xsemantics does not know of the cache)
				cache.put(node, result);
			}
		} catch (RuleFailedException e) {
			cache.put(node, new Result(e));
		} catch (Throwable th) {
			th.printStackTrace
			cache.put(node,
				new Result(new RuleFailedException("error while asking Xsemantics: " + th.message, "YYY", th)));
		}

		log(indentLevel, cache.getFailSafe(node));
	}

	def private Result<TypeRef> inferType(RuleEnvironment G, TypableElement obj) {
		return inferType(G, null, obj);
	}

	def private Result<TypeRef> inferType(RuleEnvironment G, RuleApplicationTrace trace, TypableElement obj) {
		if (obj.eIsProxy) {
			return new Result(TypeRefsFactory.eINSTANCE.createUnknownTypeRef);
		}
		// special case:
		// "this" in the default initializer expression of fpars is not supported yet
		if (obj.isThisKeywordInFparDefaultExpression || obj.isFparRefInFparDefaultExpression) {
			// TODO IDE-1345 remove this work-around when fpar default initializers are properly supported
			// To see why the following work-around is required, remove it and check the following code:
			//
			//     var ol = {
			//         prop1: "p1",
			//         prop2: function(x = this.prop1) {}
			//     };
			//
			// or
			//
			//     var ol = {
			//         prop: function(x = 42, y = x) {}
			//     };
			//
			return new Result(G.undefinedTypeRef);
		}
		// special case:
		// TStructMembers are special in that they may be types (in case of TStructMethod) and appear as AST nodes
		// -> if we are dealing with an AST node, make sure to use the definedMember in the TModule
		val definedMember = if (obj instanceof TStructMember) obj.definedMember;
		if (definedMember !== null && obj.isASTNode) {
			return inferType(G, trace, definedMember);
		}
		// standard case:
		return askXsemanticsForType(G, trace, obj);
	}

	def private boolean isThisKeywordInFparDefaultExpression(TypableElement elem) {
		return elem instanceof ThisLiteral && elem.isContainedInFparDefaultExpression;
	}

	def private boolean isFparRefInFparDefaultExpression(TypableElement elem) {
		return elem instanceof IdentifierRef
			&& (
				(elem as IdentifierRef).id instanceof FormalParameter
				|| (elem as IdentifierRef).id instanceof TFormalParameter
			)
			&& elem.isContainedInFparDefaultExpression;
	}

	def private boolean isContainedInFparDefaultExpression(TypableElement elem) {
		val containingFunctionOrAccessor = N4JSASTUtils.getContainingFunctionOrAccessor(elem);
		val containingFunctionOrAccessorFpars = switch (containingFunctionOrAccessor) {
			FunctionDefinition: containingFunctionOrAccessor.fpars
			SetterDeclaration: #[containingFunctionOrAccessor.fpar]
			default: #[]
		};
		val containingFpar = EcoreUtil2.getContainerOfType(elem, FormalParameter);
		if (containingFpar !== null && containingFunctionOrAccessorFpars.contains(containingFpar)) {
			val containingFparInitializer = containingFpar.initializer;
			if (containingFparInitializer !== null && EcoreUtil.isAncestor(containingFparInitializer, elem)) {
				return true;
			}
		}
		return false;
	}


	// ---------------------------------------------------------------------------------------------------------------


	/**
	 * Will be called by Xsemantics when the type judgment is invoked, either from within Xsemantics or from the
	 * outside.
	 */
	def public Result<TypeRef> xsemantics_type(RuleEnvironment G, RuleApplicationTrace trace, TypableElement objRaw) {

		if (objRaw === null) {
			// failing safely here; otherwise we would need preemptive null-checks wherever type inference is applied
			return new Result(TypeRefsFactory.eINSTANCE.createUnknownTypeRef);
		}

		var obj = if (objRaw.eIsProxy) {
				val resSet = RuleEnvironmentExtensions.getContextResource(G).resourceSet;
				EcoreUtil.resolve(objRaw, resSet) as TypableElement;
			} else {
				objRaw
			};

		val res = obj.eResource;
		if (res instanceof N4JSResource) {

			if (res.isFullyProcessed && res.script.eIsProxy) {
				// special case: this is a resource loaded from the index!
				// -> for now, we entirely by-pass TypingCache and just directly wrap the type model element in a TypeRef
				if (!obj.isTypeModelElement) {
					throw new IllegalStateException("not a type model element: " + obj)
				}
				return inferType(G, obj); // obj is a type model element, so this will just wrap it in a TypeRef (no actual inference)
			}

			// make sure post-processing on the containing N4JS resource is initiated
			res.performPostProcessing

			if (obj.isTypeModelElement) {
				// for type model elements, we by-pass all caching ...
				// but: if the type model element corresponds to an AST node, we forward-process that AST node
				val astNodeToProcess = if (obj instanceof SyntaxRelatedTElement) {
						obj.astElement // NOTE: we've made sure above that we are *NOT* in a Resource loaded from the index!
					};
				if (astNodeToProcess !== null) {
					return getTypeOfForwardReference(G, astNodeToProcess, typingCacheHelper.getOrCreate(res));
				}

				return inferType(G, obj); // obj is a type model element, so this will just wrap it in a TypeRef (no actual inference)
			} else if (obj.isASTNode && obj.isTypableNode) {
				// here we read from the cache (if AST node 'obj' was already processed) or forward-process 'obj'
				val cache = typingCacheHelper.getOrCreate(res);
				if (!cache.isTypingInProgress && !cache.isFullyTyped) {
					// we have called #performPostProcessing() on the containing resource above, so this is "impossible"
					// (HINT: if you get an exception here, this often indicates an accidental cache clear; use the
					// debug code in TypingCacheHelper to track creation/deletion of typing caches to investigate this)
					val e = new IllegalStateException("typing of entire AST not initiated yet!!")
					e.printStackTrace // make sure we see this on the console (some clients eat up all exceptions!)
					throw e;
				} else if (cache.isTypingInProgress) {

					// while AST typing is in progress, just read from the cache we are currently filling
					val resultFromCache = cache.getFailSafe(obj);

					if (resultFromCache === null) {
						// cache does not contain type for 'obj' (i.e. not processed yet)
						// -> we have a forward reference!
						log(0, "***** forward reference to: " + obj);

						return getTypeOfForwardReference(G, obj, cache);
					} else {
						// cache contains a type for 'obj' (i.e. it was already processed)
						// -> simply read from cache
						return resultFromCache;
					}
				} else if (cache.isFullyTyped) {
					return cache.get(obj); // will throw exception in case of cache miss
				}
			} else {
				// a non-typable AST node OR some entity in the TModule for which obj.isTypeModelElement returns false
				return new Result(new RuleFailedExceptionWithoutStacktrace("cannot type object: " + obj));
			}
		} else {
			// obj not contained in an N4JSResource -> fall back to default behavior
			// can happen for:
			// - objects that are not contained in a Resource
			// - objects that are contained in a Resource but not an N4JSResource
			return inferType(G, trace, obj);
		}
	}

	def private Result<TypeRef> getTypeOfForwardReference(RuleEnvironment G, EObject node, TypingCache cache) {
		assertTrueIfRigid("argument 'node' must be an AST node", node.isASTNode);

		// TODO improve handling of destructuring patterns in ASTProcessor/TypeProcessor
		// (this is a temporary hack to avoid many illegal forward references within destructuring patterns)
		if (destructureProcessor.isForwardReferenceWhileTypingDestructuringPattern(node)) {
			return destructureProcessor.handleForwardReferenceWhileTypingDestructuringPattern(G, node, cache);
		}

		val isLegal = astProcessor.processSubtree_forwardReference(G, node, cache);
		if (isLegal) {
			val isCyclicForwardReference = cache.astNodesCurrentlyBeingTyped.contains(node);
			if (isCyclicForwardReference) {
				// in case of a legal cyclic forward reference, we cannot obtain the type of 'node' in the usual
				// way by fully processing 'node' and its subtree, so we have to "guess" a type
				if (node instanceof VariableDeclaration || node instanceof N4FieldDeclaration ||
					node instanceof PropertyNameValuePair) {

					val expr = node.expressionOfVFP;
					if (expr instanceof N4ClassExpression) {
						return askXsemanticsForType(G, expr);
					}
					if (expr instanceof NewExpression) {
						val callee = expr.callee;
						if (callee instanceof N4ClassExpression) {
							val calleeType = askXsemanticsForType(G, callee).value;
							return new Result(TypeUtils.createTypeRef((calleeType as ClassifierTypeRef).staticType));
						}
					}
					val declTypeRef = node.declaredTypeRefOfVFP;
					return if (declTypeRef !== null) {
						new Result(declTypeRef)
					} else {
						new Result(G.anyTypeRef)
					};
				} else if (node instanceof FieldAccessor) {
					val declTypeRef = node.declaredTypeRef;
					return if (declTypeRef !== null) {
						new Result(declTypeRef)
					} else {
						new Result(G.anyTypeRef)
					};
				} else if (node instanceof TypeDefiningElement) {
					return new Result(wrapTypeInTypeRef(G, node.definedType));
				} else {
					val e = new IllegalStateException(
						"handling of a legal case of cyclic forward references missing in TypeProcessor");
					e.printStackTrace;
					return new Result(e);
				}
			} else if (astProcessor.isSemiCyclicForwardReferenceInForLoop(node, cache)) {
				// semi-cyclic forward reference to a variable declaration in a for in/of loop:
				// -> similar to cyclic variable declarations, we have to "guess" a type.
				val declTypeRef = (node as VariableDeclaration).declaredTypeRef;
				return if (declTypeRef !== null) {
					new Result(declTypeRef)
				} else {
					new Result(G.anyTypeRef)
				};
			} else {
				// in case of a legal, *non*-cyclic forward reference, we can assume that the subtree below 'node'
				// has now been processed, which means node's type is now in the typing cache
				return cache.get(node);
			}
		} else {
			val msg = "*#*#*#*#*#* ILLEGAL FORWARD REFERENCE to " + node + " in " + node.eResource?.URI;
			logErr(msg);
			return new Result(new IllegalStateException(msg));
		}
	}


	// ---------------------------------------------------------------------------------------------------------------


	def private static Expression getExpressionOfVFP(EObject vfp) {
		switch (vfp) {
			VariableDeclaration:
				vfp.expression
			N4FieldDeclaration:
				vfp.expression
			PropertyNameValuePair:
				vfp.expression
		}
	}

	def private static TypeRef getDeclaredTypeRefOfVFP(EObject vfp) {
		switch (vfp) {
			VariableDeclaration:
				vfp.declaredTypeRef
			N4FieldDeclaration:
				vfp.declaredTypeRef
			PropertyNameValuePair:
				vfp.declaredTypeRef
		}
	}
}
