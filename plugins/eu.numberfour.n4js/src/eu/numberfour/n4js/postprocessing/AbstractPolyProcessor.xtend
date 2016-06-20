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
import eu.numberfour.n4js.n4JS.Argument
import eu.numberfour.n4js.n4JS.ArrayElement
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.PropertyAssignment
import eu.numberfour.n4js.n4JS.PropertyAssignmentAnnotationList
import eu.numberfour.n4js.n4JS.PropertyGetterDeclaration
import eu.numberfour.n4js.n4JS.PropertyMethodDeclaration
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.PropertySetterDeclaration
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.InferenceVariable
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TSetter
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.UndefModifier
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.typesystem.constraints.InferenceContext
import it.xsemantics.runtime.Result
import it.xsemantics.runtime.RuleEnvironment
import java.util.List
import java.util.Map
import org.eclipse.emf.ecore.EObject

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 */
package abstract class AbstractPolyProcessor extends AbstractProcessor {

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypingCacheHelper typingCacheHelper;

	def boolean isPoly(EObject obj) {
		return switch (obj) {
			Expression: obj.isPoly
			PropertyAssignment: obj.isPoly
			default: false
		}
	}

	/**
	 * Returns true iff obj is an AST node that requires constraint-based type inference.
	 */
	def boolean isPoly(Expression obj) {
		return switch (obj) {
			ParameterizedCallExpression: {
				val G = RuleEnvironmentExtensions.newRuleEnvironment(obj);
				val TypeRef targetTypeRef = ts.type(G, obj.target).value; // note: this is a backward reference!
				if (targetTypeRef instanceof FunctionTypeExprOrRef) {
					targetTypeRef.generic && obj.typeArgs.size < targetTypeRef.typeVars.size
				} else {
					false
				}
			}
			FunctionExpression:
				obj.fpars.exists[declaredTypeRef === null] // type of 1 or more fpars is undeclared
				|| obj.returnTypeRef === null // return type is undeclared
				// note: if the FunctionExpression is generic, this does *not* make it poly!
			ArrayLiteral:
				true
			ObjectLiteral:
				obj.propertyAssignments.exists[isPoly]
			default:
				false
		}
	}

	def private boolean isPoly(PropertyAssignment pa) {
		switch (pa) {
			PropertyNameValuePair:
				pa.expression !== null && pa.declaredTypeRef === null // FIXME requiring pa.expression!==null is inconsistent!
			PropertyGetterDeclaration:
				pa.declaredTypeRef === null
			PropertySetterDeclaration:
				pa.declaredTypeRef === null
			PropertyMethodDeclaration:
				false
			PropertyAssignmentAnnotationList:
				false
			default:
				throw new IllegalArgumentException("unsupported subclass of PropertyAssignment: " + pa.eClass.name)
		}
	}

	def boolean isRootPoly(EObject obj) {
		if (obj instanceof Expression) obj.isRootPoly else false
	}

	def boolean isRootPoly(Expression obj) {
		if (isPoly(obj)) {
			val p = getParentPolyCandidate(obj);
			return p === null || !isPoly(p);
		}
		return false;
	}

	def private EObject getParentPolyCandidate(Expression poly) {
		val directParent = poly?.eContainer;
		val grandParent = directParent?.eContainer;
		return switch (directParent) {
			Argument case grandParent instanceof ParameterizedCallExpression &&
				(grandParent as ParameterizedCallExpression).arguments.map[expression].contains(poly): // TODO what about the target expression? i.e.: || directParent.target===poly
				grandParent
			FunctionExpression:
				null // function expressions never have nested poly expressions (expression in the body are detached)
			ArrayElement case directParent.expression === poly:
				directParent.eContainer as ArrayLiteral // return the ArrayLiteral as parent (not the ArrayElement)
			PropertyNameValuePair case directParent.expression === poly:
				directParent // return the PropertyNameValuePair as parent (not the ObjectLiteral)
			PropertyGetterDeclaration:
				null // getters never have nested poly expressions
			PropertySetterDeclaration:
				null // setters never have nested poly expressions
		}
	}


	// ------------------------------------------------------------------------------------------------------------------------------


	/**
	 * Returns the type of a nested poly expression. The final type is returned, i.e. not the one created when preparing
	 * the constraint system that may contain inference variables.
	 * <p>
	 * Because final types are created and stored in the typing cache in the onSuccess/onFailure lambdas and those
	 * lambdas of nested poly expressions are registered before those of outer expression, we can here simply read the
	 * nested poly expression's type from the cache.
	 */
	def protected TypeRef getFinalResultTypeOfNestedPolyExpression(Expression nestedPolyExpression) {
		return restoreFromCache(nestedPolyExpression);
	}

	def protected TypeRef restoreFromCache(EObject astNode) {
		typingCacheHelper.typeOf(astNode);
	}

	def protected void storeInCache(EObject astNode, TypeRef actualType) {
		typingCacheHelper.store(astNode, new Result<TypeRef>(actualType));
	}

	def protected void storeInferredTypeArgsInCache(ParameterizedCallExpression callExpr, List<TypeRef> typeArgs) {
		typingCacheHelper.storeInferredTypeArgs(callExpr, typeArgs);
	}

	def protected TypeRef subst(TypeRef typeRef, RuleEnvironment G,
		Map<TypeVariable, ? extends TypeVariable> substitutions) {

		subst(typeRef, G, substitutions, false)
	}

	def protected TypeRef subst(TypeRef typeRef, RuleEnvironment G,
		Map<TypeVariable, ? extends TypeVariable> substitutions, boolean reverse) {

		val Gx = G.wrap;
		substitutions.entrySet.forEach [ e |
			if (reverse)
				Gx.add(e.value, TypeUtils.createTypeRef(e.key))
			else
				Gx.add(e.key, TypeUtils.createTypeRef(e.value))
		];
		val result = ts.substTypeVariables(Gx, typeRef);
		if (result.failed)
			throw new IllegalArgumentException("substitution failed", result.ruleFailedException);
		return result.value as TypeRef; // FIXME what about wildcards here??
	}

	def protected TypeRef applySolution(TypeRef typeRef, RuleEnvironment G, Map<InferenceVariable, TypeRef> solution) {
		if (typeRef === null || solution === null || solution.empty) {
			return typeRef; // note: returning 'null' if typeRef==null (broken AST, etc.)
		}
		// PART 1 OF TEMPORARY WORK-AROUND
		val UndefModifier[] modifiers = if (typeRef instanceof FunctionTypeExprOrRef) {
				typeRef.fpars.map[solution.get(it?.typeRef?.declaredType)?.undefModifier] +
					#[typeRef.returnTypeRef.undefModifier]
			} else {
				null
			};
		// END OF PART 1
		val Gx = G.wrap;
		solution.entrySet.forEach[e|Gx.add(e.key, e.value)];
		val result = ts.substTypeVariables(Gx, typeRef);
		if (result.failed)
			throw new IllegalArgumentException("substitution failed", result.ruleFailedException);
		// PART 2 OF TEMPORARY WORK-AROUND
		if (typeRef instanceof FunctionTypeExprOrRef) {
			val resultValue = result.value;
			if (resultValue instanceof FunctionTypeExprOrRef) {
				val iMod = modifiers.iterator;
				val iFpar = resultValue.fpars.iterator;
				while (iFpar.hasNext && iMod.hasNext) {
					mergeUndefModifier(iFpar.next.typeRef, iMod.next);
				}
				if (iMod.hasNext) {
					mergeUndefModifier(resultValue.returnTypeRef, iMod.next);
				}
			}
		}
		// END OF PART 2
		return result.value as TypeRef; // FIXME what about wildcards here??
	}

	def protected Map<InferenceVariable, TypeRef> createPseudoSolution(InferenceContext infCtx,
		TypeRef defaultTypeRef) {

		val pseudoSolution = newHashMap;
		for (iv : infCtx.getInferenceVariables) {
			pseudoSolution.put(iv, defaultTypeRef); // map all inference variables to the default
		}
		return pseudoSolution;
	}

	// FIXME move to a better place
	def protected boolean isReturningValue(FunctionDefinition fun) {
		return (fun.body !== null && fun.body.allReturnStatements.exists[expression !== null]) ||
			(if (fun instanceof ArrowFunction) fun.singleExprImplicitReturn else false); // TODO except call to void function!!
	}

	def protected TypeRef getTypeOfMember(TMember m) {
		switch (m) {
			TField:
				m.typeRef
			TGetter:
				m.declaredTypeRef
			TSetter:
				m?.fpar.typeRef
			TMethod:
				throw new IllegalArgumentException("this method should not be used for TMethod")
			default:
				throw new IllegalArgumentException("unknown subtype of TMember: " + m?.eClass?.name)
		}
	}

	def protected void setTypeOfMember(TMember m, TypeRef type) {
		switch (m) {
			TField:
				m.typeRef = type
			TGetter:
				m.declaredTypeRef = type
			TSetter:
				if (m.fpar !== null) m.fpar.typeRef = type
			TMethod:
				throw new IllegalArgumentException("this method should not be used for TMethod")
			default:
				throw new IllegalArgumentException("unknown subtype of TMember: " + m?.eClass?.name)
		}
	}

	def protected void mergeUndefModifier(TypeRef typeRef, UndefModifier modifier) {
		if (typeRef !== null && modifier !== null) {
			typeRef.undefModifier = modifier;
		}
	}

	def protected void setUndefModifier(TypeRef typeRef, UndefModifier modifier) {
		typeRef.undefModifier = modifier;
	}
}
