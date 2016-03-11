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
package eu.numberfour.n4js.transpiler.es.assistants

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.ReturnStatement
import eu.numberfour.n4js.n4JS.Statement
import eu.numberfour.n4js.transpiler.TransformationAssistant
import eu.numberfour.n4js.typeinference.N4JSTypeInferencer
import org.eclipse.emf.common.util.EList

/**
 */
class BlockAssistant extends TransformationAssistant {

	@Inject
	N4JSTypeInferencer typeInferencer

	/**
	 * Some assertions related to arrow functions that apply to several transformations and are
	 * therefore factored out into this helper method.
	 */
	def public void assertArrowFunctionConditions() {
		assertTrue(
			"all arrow functions must have an original AST node "+
			"(i.e. not allowed to add arrow functions programmatically in a transformation)",
			state.im.eAllContents.filter(ArrowFunction).forall[
				state.tracer.getOriginalASTNodeOfSameType(it, false)!==null
			]);
	}

	/**
	 * Upon executing the body of the argument, does control flow reach past the last statement without encountering any
	 * return statement? In other words, does execution "fall off" past the last statement?
	 * <p>
	 * Returns true for methods that are executed until the end, and then execution "falls off". For those methods,
	 * provided they are async, appending "yield undefined" is necessary. This method takes into account
	 * transpiler-inserted returns for single-expression arrow functions (ie, for the body of such arrow functions,
	 * execution doesn't fall off).
	 */
	public final def boolean hasBodyWhereExecutionFallsOff(FunctionDefinition conFD) {
		val EList<Statement> stmts = conFD.getBody().getStatements();
		if (stmts.isEmpty()) {
			return true;
		}
		val Statement last = stmts.get(stmts.size() - 1);
		if (last instanceof ReturnStatement) {
			return false;
		}
		if (conFD instanceof ArrowFunction) {
			val boolean hasInsertedReturn = needsReturnInsertionForBody(conFD);
			return !hasInsertedReturn;
		} else {
			return true;
		}
	}

	def public final boolean needsReturnInsertionForBody(ArrowFunction arrowFuncInIM) {
		// unfortunately, we need a properly contained AST element for the helper method below
		// (see preconditions above)
		val origAST = state.tracer.getOriginalASTNodeOfSameType(arrowFuncInIM, true);
		return typeInferencer.needsReturnInsertionForBody( origAST );
	}
}
