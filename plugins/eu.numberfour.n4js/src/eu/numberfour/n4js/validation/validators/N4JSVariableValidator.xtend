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
package eu.numberfour.n4js.validation.validators

import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.N4ClassExpression
import eu.numberfour.n4js.n4JS.NewExpression
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.ts.types.TVariable
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4js.validation.IssueCodes
import java.util.List
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

/**
 * Validations for variable declarations and variables.
 */
class N4JSVariableValidator extends AbstractN4JSDeclarativeValidator {


	/**
	 * NEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	override register(EValidatorRegistrar registrar) {
		// nop
	}


	@Check
	def void checkVariableDeclaration(VariableDeclaration varDecl) {
		if(varDecl.expression!==null) {
			val refs = varDecl.expression.collectIdentifierRefsTo(varDecl,newArrayList);
			for(IdentifierRef currRef : refs) {
				val message = IssueCodes.getMessageForAST_VAR_DECL_RECURSIVE(varDecl.name)
				addIssue(message, currRef, null, IssueCodes.AST_VAR_DECL_RECURSIVE)
			}
			// Validate Expression for Constraint 51, checking suffix of referenced functions.
			val expression = varDecl.expression
			if( expression instanceof IdentifierRef)
			{
				// pass addIssue() through lambda to void duplicate logic.
				N4JSExpressionValidator::internalCheckNameRestrictionInMethodBodies( expression,
						[ String message, EObject source, EStructuralFeature feature, String issueCode |
							addIssue(message,source,feature,issueCode)
						]
				)
			}
		}

	}


	def private static List<IdentifierRef> collectIdentifierRefsTo(EObject astNode, VariableDeclaration varDecl, List<IdentifierRef> result) {
		// exception cases:
		// do not inspect class expressions and function expressions because contained code will be executed later
		// and therefore self-reference is ok UNLESS the class is immediately instantiated / the function immediately called
		if(astNode instanceof N4ClassExpression && !astNode.isInstantiatedOrCalled)
			return result;  // add nothing
		if(astNode instanceof FunctionExpression && !astNode.isInstantiatedOrCalled)
			return result;  // add nothing

		// standard cases:
		if(astNode instanceof IdentifierRef)
			if(astNode.id===varDecl || (astNode.id instanceof TVariable && (astNode.id as TVariable).astElement===varDecl))
				result.add(astNode);
		for(child : astNode.eContents)
			child.collectIdentifierRefsTo(varDecl,result);
		return result;
	}
	def public static boolean containsIdentifierRefsTo(EObject astNode, VariableDeclaration varDecl) {
		return !astNode.collectIdentifierRefsTo(varDecl,newArrayList).empty
	}
	/**
	 * Tells if given astNode is an expression serving as target to a new or call expression. This provides <b>only a
	 * heuristic</b>; might produce false negatives (but no false positives).
	 */
	def private static boolean isInstantiatedOrCalled(EObject astNode) {
		var curr = astNode;
		while(curr.eContainer instanceof ParenExpression) {
			curr = curr.eContainer;
		}
		val parent = curr.eContainer;
		return switch(parent) {
			NewExpression: parent.callee===curr
			ParameterizedCallExpression: parent.target===curr
			default: false
		}
	}
}
