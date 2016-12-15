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
package eu.numberfour.n4js.typesystem

import com.google.inject.Inject
import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.GetterDeclaration
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.utils.TypeUtils
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.xtext.EcoreUtil2

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import eu.numberfour.n4js.n4JS.YieldExpression
import eu.numberfour.n4js.ts.typeRefs.TypeArgument

/**
 * Contains helper methods used by the rules of the 'expectedTypeIn' judgment.
 * Main reason for factoring out this code is the same logic is used by several rules.
 */
@Singleton
class ExpectedTypeComputer extends TypeSystemHelperStrategy {

	@Inject
	private N4JSTypeSystem ts;

	/**
	 * Returns the expected type of an expression which be used to compute the return value of its containing function.
	 * In the standard case, the expression should be an expression in a return statement and the expected type will
	 * be the return type of its containing function. However, in case of a single-expression arrow function, the
	 * given expression need not be the child of a return statement.
	 * <p>
	 * This method will not check that the given expression is actually an expression that will, at runtime, provide
	 * the return value of a function. This has to be ensured by the client code.
	 * 
	 * @return the expected type or <code>null</code> if there is no type expectation or some error occurred (e.g. broken AST).
	 */
	def TypeRef getExpectedTypeOfReturnValueExpression(RuleEnvironment G, Expression returnValueExpr) {
		val funDef = EcoreUtil2.getContainerOfType(returnValueExpr?.eContainer, FunctionDefinition);

		val G2 = G.wrap;
		val myThisTypeRef = ts.thisTypeRef(G, returnValueExpr).value;
		G2.addThisType(myThisTypeRef); // takes the real-this type even if it is a type{this} reference.

		if (funDef !== null) {
			if (funDef.isAsync) {
		        return getExpectedTypeOfReturnValueExpressionForAsyncFunction(G, funDef);
				
		    } else if (funDef.isGenerator()) {
		        return getExpectedTypeOfReturnValueExpressionForGeneratorFunction(G, funDef);
				
		    } else {
		        // this is the normal case
				val fType = ts.type(G2, funDef).value;
				if (fType instanceof FunctionTypeExprOrRef) {
					return ts.substTypeVariablesInTypeRef(G2, fType.returnTypeRef);
				}
		    }
			
		} else {
			// funDef === null, so maybe we are in a getter:
			val getterDef = EcoreUtil2.getContainerOfType(returnValueExpr, GetterDeclaration);
			return getterDef?.definedGetter?.declaredTypeRef;
		}
		return null; // null means: no type expectation
	}
	
	private def TypeRef getExpectedTypeOfReturnValueExpressionForAsyncFunction(RuleEnvironment G, FunctionDefinition funDef) {
		// we have an async function:
		// in this case, N4JSFunctionDefinitionTypesBuilder sets funDef.definedType.returnTypeRef
		// to Promise<R,?>, where R can be based on funDef.returnTypeRef
		val tFun = funDef.definedType;
		if (tFun instanceof TFunction) {
			val actualReturnTypeRef = tFun.returnTypeRef;
			if (TypeUtils.isPromise(actualReturnTypeRef, G.getPredefinedTypes().builtInTypeScope)) {
				val firstTypeArg = actualReturnTypeRef.typeArgs.head;
				return ts.upperBound(G, firstTypeArg).value; // take upper bound to get rid of Wildcard, etc.
			}
		}
		
		if (funDef.returnTypeRef !== null) {
			return funDef.returnTypeRef;
		}
		
		return null;
	}
	
	private def TypeRef getExpectedTypeOfReturnValueExpressionForGeneratorFunction(RuleEnvironment G, FunctionDefinition funDef) {
		// we have a generator function:
		// in this case, N4JSFunctionDefinitionTypesBuilder sets funDef.definedType.returnTypeRef
		// to Generator<TYield,TResult,TNext>, where TYield can be based on funDef.returnTypeRef
		val tFun = funDef.definedType;
		if (tFun instanceof TFunction) {
			val actualReturnTypeRef = tFun.returnTypeRef;
			if (TypeUtils.isGenerator(actualReturnTypeRef, G.getPredefinedTypes().builtInTypeScope)) {
				val secondTypeArg = actualReturnTypeRef.typeArgs.get(1);
				return ts.upperBound(G, secondTypeArg).value; // take upper bound to get rid of Wildcard, etc.
			}
		}
		
		if (funDef.returnTypeRef !== null) {
			return funDef.returnTypeRef;
		}
		
		return null;
	}
	
	def TypeRef getExpectedTypeOfYieldValueExpression(RuleEnvironment G, YieldExpression yieldExpr, Expression expression) {
		val funDef = EcoreUtil2.getContainerOfType(expression?.eContainer, FunctionDefinition);

		val G2 = G.wrap;
		val myThisTypeRef = ts.thisTypeRef(G, expression).value;
		G2.addThisType(myThisTypeRef); // takes the real-this type even if it is a type{this} reference.

		if (funDef === null || !funDef.isGenerator) 
			return null; // yields only occur in generator functions
		
		val tFun = funDef.definedType;
		if (tFun instanceof TFunction) {
			val actualReturnTypeRef = tFun.returnTypeRef;
			val scope = G.getPredefinedTypes().builtInTypeScope;
			if (TypeUtils.isGenerator(actualReturnTypeRef, scope)) {
				val yieldTypeArg = actualReturnTypeRef.typeArgs.get(0);
				val yieldTypeRef = ts.upperBound(G, yieldTypeArg).value; // take upper bound to get rid of Wildcard, etc.
				if (yieldExpr.isMany()) {
					val nextTypeArg = actualReturnTypeRef.typeArgs.get(2);
					val nextTypeRef = ts.upperBound(G, nextTypeArg).value; // take upper bound to get rid of Wildcard, etc.
					val superNext = TypeUtils.createWildcardSuper(nextTypeRef);
					val extendsYield = TypeUtils.createWildcardExtends(yieldTypeRef);
					val tReturn = scope.getAnyTypeRef();
					// the return type does not matter since it is not returned in a yield* statement
					val recursiveGeneratorSuperType = TypeUtils.createGeneratorTypeRef(scope, extendsYield, tReturn, superNext);
					return recursiveGeneratorSuperType;
				} else {
				    return yieldTypeRef;
				}
			}
		}
		
		return null; // null means: no type expectation
	}
	
}
