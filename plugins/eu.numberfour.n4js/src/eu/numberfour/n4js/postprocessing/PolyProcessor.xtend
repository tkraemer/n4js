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
import eu.numberfour.n4js.n4JS.Argument
import eu.numberfour.n4js.n4JS.ArrayElement
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.PropertyAssignment
import eu.numberfour.n4js.n4JS.RelationalExpression
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TypableElement
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.typesystem.constraints.InferenceContext
import eu.numberfour.n4js.typesystem.constraints.TypeConstraint
import eu.numberfour.n4js.validation.JavaScriptVariant
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.util.CancelIndicator

/**
 */
@Singleton
class PolyProcessor extends AbstractPolyProcessor {

	@Inject
	private PolyProcessor_ArrayLiteral arrayLiteralProcessor;
	@Inject
	private PolyProcessor_ObjectLiteral objectLiteralProcessor;
	@Inject
	private PolyProcessor_FunctionExpression functionExpressionProcessor;
	@Inject
	private PolyProcessor_CallExpression callExpressionProcessor;

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypeSystemHelper tsh;


	// ------------------------------------------------------------------------------------------------------------------------------


	def package boolean isResponsibleFor(TypableElement astNode) {
		astNode.isPoly
		|| (astNode instanceof Argument && astNode.eContainer instanceof ParameterizedCallExpression && astNode.eContainer.isPoly)
		|| (astNode instanceof FormalParameter && astNode.eContainer instanceof FunctionExpression && astNode.eContainer.isPoly)
		|| (astNode instanceof ArrayElement && astNode.eContainer instanceof ArrayLiteral && astNode.eContainer.isPoly)
		|| (astNode instanceof PropertyAssignment && astNode.eContainer instanceof ObjectLiteral && astNode.eContainer.isPoly)
		// note in previous line:
		// even if the PropertyAssignment itself is NOT poly, we claim responsibility for it if the containing ObjectLiteral is poly
	}

	def package boolean isEntryPoint(TypableElement astNode) {
		astNode.isRootPoly
	}


	// ------------------------------------------------------------------------------------------------------------------------------


	def public void inferType(RuleEnvironment G, Expression rootPoly, CancelIndicator cancelIndicator) {

		// create a new constraint system
		val InferenceContext infCtx = new InferenceContext(ts, tsh, cancelIndicator, G);

// in plain JS files, we want to avoid searching for a solution (to avoid performance problems in some JS files
// with extremely large array/object literals) but to avoid having to deal with this case with additional code,
// we still build a constraint system as usual (TEMPORARAY HACK)
// TODO find proper way to deal with extremely large array/object literals (also see compaction phase in InferenceContext)
if(JavaScriptVariant.getVariant(rootPoly).isECMAScript) {
	infCtx.addConstraint(TypeConstraint.FALSE);
}

		// we have to pass the expected type to the #getType() method, so retrieve it first
		// (until the expectedType judgment is integrated into AST traversal, we have to invoke this judgment here;
		// in case of not-well-behaving expectedType rules, we use 'null' as expected type, i.e. no expectation)
		// TODO integrate expectedType judgment into AST traversal and remove #isProblematicCaseOfExpectedType()
		val expectedTypeRef = if(!rootPoly.isProblematicCaseOfExpectedType) {
			ts.expectedTypeIn(G, rootPoly.eContainer(), rootPoly).getValue();
		};

		// call #getType() (this will recursively call #getType() on nested expressions)
		val typeRef = processExpr(G, infCtx, rootPoly, expectedTypeRef);

		// add constraint to ensure that type of 'rootPoly' is subtype of the expected type
		if(!TypeUtils.isVoid(typeRef)) {
			if(expectedTypeRef!==null) {
				infCtx.addConstraint(typeRef, expectedTypeRef, Variance.CO);
			}
		}

		// compute solution
		infCtx.solve;
		// note: we're not actually interested in the solution, here; we just want to make sure to trigger the
		// onSuccess/onFailure handlers registered by the #getType() methods
	}


	/**
	 * Key method for handling poly expressions. It has the following responsibilities:
	 * <ul>
	 * <li>if given expression is non-poly: simply return its type.
	 * <li>if given expression is poly:
	 *     <ol>
	 *     <li>add appropriate constraints to the constraint system of the given inference context.
	 *     <li>recursively invoke this method for nested expressions (poly or non-poly).
	 *     <li>add all required types for 'expr' and its non-expression children (e.g. fpars) to the typing cache.
	 *         This should usually be done in an onSuccess or onFailure handler added to the given inference context.
	 *     <li>return type of the given expression <code>expr</code>.
	 *     </ol>
	 * </ul>
	 * IMPORTANT: the type returned by this method may contain inference variables!
	 */
	def dispatch protected TypeRef processExpr(RuleEnvironment G, InferenceContext infCtx, Expression expr, TypeRef expectedTypeRef) {
		if(isPoly(expr)) {
			throw new IllegalArgumentException("missing dispatch method #getType() for poly expression: " + expr);
		}
		// never poly -> directly infer type via type system
		val result = ts.type(G, expr).getValue();
		// do not store in cache (TypeProcessor responsible for storing types of non-poly expressions in cache)

		return result;
	}
	def dispatch protected TypeRef processExpr(RuleEnvironment G, InferenceContext infCtx, ArrayLiteral arrLit, TypeRef expectedTypeRef) {
		return arrayLiteralProcessor.processArrayLiteral(G, infCtx, arrLit, expectedTypeRef);
	}
	def dispatch protected TypeRef processExpr(RuleEnvironment G, InferenceContext infCtx, ObjectLiteral objLit, TypeRef expectedTypeRef) {
		return objectLiteralProcessor.processObjectLiteral(G, infCtx, objLit, expectedTypeRef);
	}
	def dispatch protected TypeRef processExpr(RuleEnvironment G, InferenceContext infCtx, FunctionExpression funExpr, TypeRef expectedTypeRef) {
		return functionExpressionProcessor.processFunctionExpression(G, infCtx, funExpr, expectedTypeRef);
	}
	def dispatch protected TypeRef processExpr(RuleEnvironment G, InferenceContext infCtx, ParameterizedCallExpression callExpr, TypeRef expectedTypeRef) {
		return callExpressionProcessor.processCallExpression(G, infCtx, callExpr, expectedTypeRef);
	}


	/**
	 * Returns true if we are not allowed to ask for the expected type of 'node', because this would lead to illegal
	 * forward references (temporary).
	 */
	def private boolean isProblematicCaseOfExpectedType(EObject node) {
		return node?.eContainer instanceof RelationalExpression
	}
}
