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

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.n4JS.AwaitExpression
import eu.numberfour.n4js.n4JS.Block
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.FunctionOrFieldAccessor
import eu.numberfour.n4js.transpiler.Transformation
import eu.numberfour.n4js.transpiler.es.assistants.BlockAssistant

import static eu.numberfour.n4js.transpiler.TranspilerBuilderBlocks.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 */
class BlockTransformation extends Transformation {

	/** Name for capturing local-arguments-environment in distinct variable on entering a block.*/
	public static final String $CAPTURE_ARGS = "$capturedArgs";

	@Inject BlockAssistant blockAssistant;


	override analyze() {

	}

	override assertPreConditions() {

	}

	override assertPostConditions() {
		"No async function-likes left in IM".assertTrue(
		collectNodes(state.im, Block, true).map[it.eContainer]
											.filter(FunctionDefinition)
											.filter[it.isAsync()]
											.toList.size == 0);
	}

	override transform() {
		collectNodes(state.im, FunctionOrFieldAccessor, true).toList.forEach[transformArguments];
		collectNodes(state.im, Block, true).forEach[transformBlockAsync];
	}

	/** capture arguments-variable to be accessible in re-written arrow-functions  */
	private def void transformArguments(FunctionOrFieldAccessor funcOrAccess ) {

		val loc_Arg = funcOrAccess._lok // if null, never used.
		if(loc_Arg===null) {
			// no references to this local arguments variable -> no capturing required
			return;
		}

		// 1. rename old IdentifierRef_IM pointing to --> arguments with fresh name.
		// 2. introduce new IdentifierRef_IM with (new) name 'arguments'
		// 3. wire up freshname to new arguments in first line of block.
		val newName = $CAPTURE_ARGS;

		val arguments_STE = findSymbolTableEntryForElement(loc_Arg, false);
		if(arguments_STE===null || arguments_STE.referencingElements.empty) {
			// no references to this local arguments variable -> no capturing required
			return;
		}

		// arguments_STE.referencingElements.empty // + also need to check if we contain a arrow-function accessing arguments.

		// 1.) RENAME
		rename( arguments_STE, newName );

		// skip ArrowFunctions: (this is the main reason for the capturing here :-)
		if( funcOrAccess instanceof ArrowFunction ) return;
		// skip empty bodies:
		if( funcOrAccess.body.statements.empty ) return;

		// 2 + 3.) CAPTURE arguments:

		val bodyFirstStatement = funcOrAccess.body.statements.get(0);
		// note, there must be something in the body otherwise the 'arguments' variable could not have been accessed!

		// new SymbolTableEntry for 'real' arguments ( the other was renamed above )
		val argumentsSTE = steFor_arguments() ;

		insertBefore( bodyFirstStatement,
			_VariableStatement( _VariableDeclaration(
				newName, _IdentRef( argumentsSTE )
			))
		);
	}

	/** handle async functions. Includes processing of await -> yield. */
	private def void transformBlockAsync(Block block) {

		// TODO do we support asynchronous getter / setter ?
		if( ! ( block.eContainer instanceof FunctionOrFieldAccessor) ) return;
		val eConFA = block.eContainer as FunctionOrFieldAccessor;

		if( ! eConFA.isAsync ) return;

		if (eConFA instanceof FunctionDefinition) {
			eConFA.declaredAsync = false;
		}

		// Dealing with async:
		// fallOff can not happen in Setter (no return) nor Getter (must have returns)
		val fallOffCase = (eConFA instanceof FunctionDefinition)
				&& blockAssistant.hasBodyWhereExecutionFallsOff(eConFA as FunctionDefinition)

		// case 1: return;   		 =>   yield undefined; return;
		// case 2: return eXpr; 	 =>   yield expr; return;
		// case 3: return awaitExpr; =>   exprStmt(awaitExpr); return;      (2nd trafw turns await)=> yield expr;

		// all existing returns must be replaced with
		val existingRets = block.allReturnStatements.toList
		existingRets.forEach[
			val retExpr = if( it.expression === null ) undefinedRef else expression;
			val firstStmt = if( retExpr instanceof AwaitExpression ) {
			  // case 3:
			  _ExprStmnt( retExpr );
			} else {
			  // case 1+2:
			  _ExprStmnt(_YieldExpr( retExpr ));
			}
			it.insertBefore(firstStmt)
		]

		val ret = _ReturnStmnt( _CallExpr(
			_IdentRef( steFor_$spawn),
			_CallExpr(
				_PropertyAccessExpr(
					_FunExpr(false)=>[
						it.generator = true;
						it.body.statements += block.statements
						if( fallOffCase ) {
							it.body.statements += _ExprStmnt( _YieldExpr(  undefinedRef ) );
							it.body.statements += _ReturnStmnt();
						}
					],
					getSymbolTableEntryForMember(state.G.functionType, "bind", false, false, true)
				),
				_ThisLiteral
			) // end Call function*()
			) // end Call $spawn
		);

		block.statements += ret;

		// now replace "await expression" with "yield expression"
		val allAwaits = collectNodes( block, AwaitExpression, true).toList;

		allAwaits.forEach[
			replace( it, _YieldExpr(it.expression) )
		]
		// Done
	}

}
