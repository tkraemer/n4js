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
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.AssignmentExpression
import eu.numberfour.n4js.n4JS.BindingElement
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ForStatement
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.VariableBinding
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.postprocessing.TypingCacheHelper.TypingCache
import eu.numberfour.n4js.ts.typeRefs.DeferredTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.utils.EcoreUtilN4
import it.xsemantics.runtime.Result
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.util.EcoreUtil

/**
 * Deals with destructuring patterns during post processing of an N4JS resource (only the destructuring pattern;
 * the value to be destructured is handled normally by the other processors).
 * <p>
 * TODO clean up handling of destructuring patterns during AST traversal, IDE-1714
 */
class DestructureProcessor extends AbstractProcessor {

	@Inject
	private ASTProcessor astProcessor;
	@Inject
	private PolyProcessor polyProcessor;

	/**
	 * Temporary handling of destructuring patterns while typing the AST.
	 */
	def void typeDestructuringPattern(RuleEnvironment G, EObject node, TypingCache cache, int indentLevel) {
		// ArrayLiteral or ObjectLiteral, but plays role of a destructuring pattern
		// -> does not really have a type, but use UnknownTypeRef to avoid having
		// to deal with this special case whenever asking for type of an expression
		cache.put(node, new Result(TypeRefsFactory.eINSTANCE.createUnknownTypeRef))
		// for object literals, some additional hacks are required ...
		if(node instanceof ObjectLiteral) {
			// poly expressions in property name/value pairs expect to be processed as part of the outer poly expression
			// -> invoke poly processor for them
			node.propertyAssignments
			.filter(PropertyNameValuePair)
			.map[expression]
			.filterNull
			.filter[polyProcessor.isResponsibleFor(it) && !polyProcessor.isEntryPoint(it)]
			.forEach[
				polyProcessor.inferType(G,it,cache.cancelIndicator);
			];
			// the defined type of the object literal may still have some DeferredTypeRefs -> remove them
			node.definedType.eAllContents.filter(DeferredTypeRef).forEach[dtr|
				EcoreUtilN4.doWithDeliver(false,[
					EcoreUtil.replace(dtr,TypeRefsFactory.eINSTANCE.createUnknownTypeRef);
				],dtr.eContainer);
			]
			// add types for property assignments
			node.propertyAssignments.forEach[
				cache.put(it, new Result(TypeRefsFactory.eINSTANCE.createUnknownTypeRef));
			]
		}
		// here we basically turn off the fail-fast approach within the destructuring pattern
		node.eAllContents
		.filter[it instanceof ObjectLiteral || it instanceof ArrayLiteral]
		.filter[cache.getFailSafe(it)===null]
		.forEach[
			cache.put(it, new Result(TypeRefsFactory.eINSTANCE.createUnknownTypeRef));
		]
	}

	/**
	 * Temporary handling of forward references within destructuring patterns.
	 */
	def Result<TypeRef> handleForwardReferenceWhileTypingDestructuringPattern(RuleEnvironment G, EObject node, TypingCache cache) {
		log(0, "===START of other identifiable sub-tree");
		val G_fresh = RuleEnvironmentExtensions.wrap(G); // don't use a new, empty environment here (required for recursion guards)
		astProcessor.processSubtree(G_fresh, node, cache, 0); // note how we reset the indent level
		cache.forwardProcessedSubTrees.add(node);
		log(0, "===END of other identifiable sub-tree");
		return cache.get(node);
	}

	def boolean isForwardReferenceWhileTypingDestructuringPattern(EObject obj) {
		if(obj instanceof Expression) {
			val parent = obj.eContainer;
			if(parent instanceof ForStatement) {
				return N4JSASTUtils.isDestructuringForStatement(parent);
			}
			if(parent instanceof AssignmentExpression) {
				return N4JSASTUtils.isDestructuringAssignment(parent)
			}
			return parent instanceof VariableBinding
					|| parent instanceof BindingElement
					|| (parent instanceof VariableDeclaration && parent.eContainer instanceof BindingElement)
		}
		return false;
	}
}
