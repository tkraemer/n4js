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
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4ClassExpression
import eu.numberfour.n4js.n4JS.N4FieldAccessor
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.Statement
import eu.numberfour.n4js.transpiler.Transformation
import eu.numberfour.n4js.transpiler.TransformationDependency.RequiresBefore
import eu.numberfour.n4js.transpiler.assistants.TypeAssistant
import eu.numberfour.n4js.transpiler.es.assistants.BootstrapCallAssistant
import eu.numberfour.n4js.transpiler.es.assistants.ClassConstructorAssistant
import eu.numberfour.n4js.transpiler.im.DelegatingMember
import eu.numberfour.n4js.transpiler.im.SymbolTableEntry
import eu.numberfour.n4js.ts.types.TField
import java.util.List
import javax.swing.text.html.HTML.Tag

import static eu.numberfour.n4js.transpiler.TranspilerBuilderBlocks.*

import static extension eu.numberfour.n4js.transpiler.utils.TranspilerUtils.*

/**
 * Transforms {@link N4ClassDeclaration}s into a constructor function and a <code>$makeClass</code> call.
 * <p>
 * Dependencies:
 * <ul>
 * <li>requiresBefore {@link MemberPatchingTransformation}:
 *     additional members must be in place before they are transformed within this transformation.
 * <li>requiresBefore {@link SuperLiteralTransformation}:
 *     requires the tag {@link Tag#explicitSuperCall}.
 * </ul>
 */
@RequiresBefore(MemberPatchingTransformation,SuperLiteralTransformation)
class ClassDeclarationTransformation extends Transformation {

	@Inject private ClassConstructorAssistant classConstructorAssistant;
	@Inject private BootstrapCallAssistant bootstrapCallAssistant;
	@Inject private TypeAssistant typeAssistant;


	override assertPreConditions() {
		typeAssistant.assertClassifierPreConditions();
		assertFalse("class expressions are not supported yet",
			state.im.eAllContents.exists[it instanceof N4ClassExpression]);
		assertFalse("only top-level classes are supported, for now",
			collectNodes(state.im, N4ClassDeclaration, false).exists[!typeAssistant.isTopLevel(it)]);
	}

	override assertPostConditions() {
		 assertFalse("there should not be any N4ClassDeclarations in the intermediate model",
			 state.im.eAllContents.exists[it instanceof N4ClassDeclaration]);
	}

	override analyze() {
		// ignore
	}

	override transform() {
		collectNodes(state.im, N4ClassDeclaration, false).forEach[transformClassDecl];
	}

	def private void transformClassDecl(N4ClassDeclaration classDecl) {
		val classSTE = classDecl.findSymbolTableEntryForElement(false);
		val superClassSTE = typeAssistant.getSuperClassSTE(classDecl);

		val ctorDecl = classConstructorAssistant.createCtorDecl(classDecl, superClassSTE);
		val makeClassCall = bootstrapCallAssistant.createMakeClassCall(classDecl, superClassSTE);
		val staticInits = createStaticInitialisers(classSTE, classDecl);

		state.tracer.copyTrace(classDecl, ctorDecl);
		state.tracer.copyTrace(classDecl, makeClassCall);
		// note: tracing for staticInits was already set in #createStaticInitialisers() and related methods!

		replace(classDecl, ctorDecl);
		insertAfter(ctorDecl.orContainingExportDeclaration, makeClassCall);

		insertAfter( makeClassCall, staticInits );

		state.info.markAsToHoist(ctorDecl);
	}

	// ################################################################################################################
	// STATIC INITIALIZERS

	/** returns pairs of a new {@code Statement} and it's associated initializer-procedure to
	 * establish tracing - once the statement was inserted into the IM-model*/
	def private List<Statement> createStaticInitialisers(SymbolTableEntry steClass, N4ClassDeclaration classDecl) {
		// apply only to static members
		val statements = classDecl.ownedMembers.filter[isStatic].map[createStaticInitialiserCode(steClass)].filterNull.toList;
		return statements;
	}

	def private dispatch Statement createStaticInitialiserCode(N4FieldDeclaration fieldDecl, SymbolTableEntry steClass) {

		if( ! fieldDecl.isStatic || fieldDecl.expression === null) return null;

		val tField = state.info.getOriginalDefinedMember(fieldDecl) as TField;

		val exprStmnt = _ExprStmnt( _AssignmentExpr( _PropertyAccessExpr=>[
				it.target = __NSSafe_IdentRef(steClass);
				it.property_IM = getSymbolTableEntryOriginal(
					tField,
					true
				);
			],  fieldDecl.expression // REUSE existing expression
		 ));
		state.tracer.copyTrace(fieldDecl, exprStmnt);

		return exprStmnt;

	}
	def private dispatch Statement createStaticInitialiserCode(N4FieldAccessor fieldDecl, SymbolTableEntry steClass) {
		// n.t.d.
	}
	def private dispatch Statement createStaticInitialiserCode(N4MethodDeclaration methodDecl, SymbolTableEntry steClass) {
		// n.t.d.
	}
	def private dispatch Statement createStaticInitialiserCode(DelegatingMember accDecl, SymbolTableEntry steClass) {
		// n.t.d.
	}
}
