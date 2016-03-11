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
package eu.numberfour.n4js.transpiler.es.transform

import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.transpiler.Transformation
import eu.numberfour.n4js.transpiler.TransformationDependency.Requires

import static eu.numberfour.n4js.transpiler.TranspilerBuilderBlocks.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 */
@Requires(ArrowFunction_Part1_Transformation)
class ArrowFunction_Part2_Transformation extends Transformation {


	override analyze() {

	}

	override assertPreConditions() {

	}

	override assertPostConditions() {
		"No arrow-function left in IM".assertTrue( collectNodes(state.im, ArrowFunction, true).isEmpty );
	}

	override transform() {
		collectNodes(state.im, ArrowFunction, true).toList.forEach[transformArrowFunction];
	}

	/** replace arrow-function by function-expression  */
	private def void transformArrowFunction(ArrowFunction arrowFunc ) {


		// PART 2
		val fe = _FunExpr(arrowFunc.async, arrowFunc.name, arrowFunc.fpars, arrowFunc.body  )

		val thisBinder = _CallExpr(
				_PropertyAccessExpr(
					_Parenthesis( fe )	,
					getSymbolTableEntryForMember(state.G.functionType, "bind", false, false, true)
				),
				_ThisLiteral
			) // end Call function*()
			;

		replace(arrowFunc, thisBinder, fe);

	}


}
