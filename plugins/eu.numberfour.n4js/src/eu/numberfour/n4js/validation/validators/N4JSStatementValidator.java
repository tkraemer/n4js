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
package eu.numberfour.n4js.validation.validators;

import static eu.numberfour.n4js.utils.N4JSLanguageUtils.isContainedInStaticPolyfillModule;
import static eu.numberfour.n4js.validation.IssueCodes.POLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES;
import static eu.numberfour.n4js.validation.IssueCodes.TYS_FOR_IN_VAR_STRING;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForPOLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForTYS_FOR_IN_VAR_STRING;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.validation.Check;
import org.eclipse.xtext.validation.EValidatorRegistrar;

import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.ForStatement;
import eu.numberfour.n4js.n4JS.FunctionDeclaration;
import eu.numberfour.n4js.n4JS.Script;
import eu.numberfour.n4js.n4JS.Statement;
import eu.numberfour.n4js.n4JS.VariableBinding;
import eu.numberfour.n4js.n4JS.VariableDeclaration;
import eu.numberfour.n4js.n4JS.VariableDeclarationOrBinding;
import eu.numberfour.n4js.n4JS.VariableStatement;
import eu.numberfour.n4js.n4JS.VariableStatementKeyword;
import eu.numberfour.n4js.ts.typeRefs.TypeRef;
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions;
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator;
import eu.numberfour.n4js.validation.JavaScriptVariant;
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem;
import it.xsemantics.runtime.Result;
import it.xsemantics.runtime.RuleEnvironment;

/**
 * Statement validation rules for N4JS.
 */
public class N4JSStatementValidator extends AbstractN4JSDeclarativeValidator {

	@Inject
	private N4JSTypeSystem typeSystem;

	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	@Override
	public void register(EValidatorRegistrar registrar) {
		// nop
	}

	/**
	 * Checks that all variable declarations contained in const statement (expressed by means of a variable statement)
	 * contain an initializer expression.
	 *
	 * IDEBUG-214
	 */
	@Check
	public void checkVariableStatement(VariableStatement variableStatement) {
		if (variableStatement.getVarStmtKeyword() == VariableStatementKeyword.CONST) {
			variableStatement.getVarDecl().stream().forEach(varDecl -> holdsConstHasInitializer(varDecl));
		}

	}

	private boolean holdsConstHasInitializer(VariableDeclaration varDecl) {
		if (JavaScriptVariant.external.isActive(varDecl)) {
			return true; // in .n4jsd --> anything goes
		}
		if (varDecl.getExpression() == null) {
			return false;
		}
		return true;
	}

	/**
	 * general top-level test: - invalid statements for StaticPolyfillModules. Constraints 140 (restrictions on
	 * static-polyfilling) IDE-1735
	 */
	@Check
	public void checkVariableStatement(Statement statement) {
		EObject con = statement.eContainer();
		if (con instanceof Script) { // top-level elements will be checked only.
			Script script = (Script) con;
			if (!isContainedInStaticPolyfillModule(script)) {
				return;
			}
			// FunctionDeclarations have finer grained check in own validator.
			if (statement instanceof FunctionDeclaration)
				return;

			// it is a static polyfill module
			addIssue(getMessageForPOLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES(), statement,
					POLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES);

		}
	}

	/**
	 * 9.1.4 Iteration Statements, Constraints 118 (For-In-Statement Constraints) Variable declaration must be a string
	 */
	@Check
	public void checkForInLoop(ForStatement forStatement) {
		if (forStatement.isForIn()) {
			TypeRef loopVarType = null;
			EObject location = null;
			RuleEnvironment G = (RuleEnvironment) getContext().get(RuleEnvironment.class);
			if (G == null)
				return; // wrongly configured test
			if (!forStatement.getVarDeclsOrBindings().isEmpty()) {
				VariableDeclarationOrBinding varDeclOrBinding = forStatement.getVarDeclsOrBindings().iterator().next();
				location = varDeclOrBinding;
				if (varDeclOrBinding instanceof VariableDeclaration) {
					loopVarType = ((VariableDeclaration) varDeclOrBinding).getDeclaredTypeRef();
				} else {
					VariableBinding varBinding = (VariableBinding) varDeclOrBinding;
					Result<TypeRef> res = typeSystem.type(G, varBinding.getExpression());
					if (!res.failed()) {
						loopVarType = res.getFirst();
					}
				}
			} else if (forStatement.getInitExpr() != null) {
				location = forStatement.getInitExpr();
				Result<TypeRef> res = typeSystem.type(G, forStatement.getInitExpr());
				if (!res.failed()) {
					loopVarType = res.getFirst();
				}

			}
			if (loopVarType != null) {
				Result<Boolean> res = typeSystem.subtype(G, RuleEnvironmentExtensions.stringTypeRef(G),
						loopVarType);

				if (res.failed() || !res.getFirst()) {
					addIssue(getMessageForTYS_FOR_IN_VAR_STRING(loopVarType.getTypeRefAsString()), location,
							TYS_FOR_IN_VAR_STRING);

				}
			}
		}
	}

}
