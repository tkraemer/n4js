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
package eu.numberfour.n4js.xpect.methods;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.EcoreUtil2;
import org.xpect.XpectImport;
import org.xpect.expectation.IStringExpectation;
import org.xpect.expectation.StringExpectation;
import org.xpect.parameter.ParameterParser;
import org.xpect.runner.Xpect;

import eu.numberfour.n4js.n4JS.ExportDeclaration;
import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.FunctionDeclaration;
import eu.numberfour.n4js.n4JS.N4MemberDeclaration;
import eu.numberfour.n4js.n4JS.N4TypeDeclaration;
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression;
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression;
import eu.numberfour.n4js.n4JS.VariableDeclaration;
import eu.numberfour.n4js.n4JS.VariableStatement;
import eu.numberfour.n4js.ts.types.IdentifiableElement;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.xpect.common.N4JSOffsetAdapter;
import eu.numberfour.n4js.xpect.common.N4JSOffsetAdapter.IEObjectCoveringRegion;

/**
 */
@XpectImport(N4JSOffsetAdapter.class)
public class AccessModifierAxpectMethod {

	/***/
	@ParameterParser(syntax = "('at' arg1=OFFSET)?")
	@Xpect
	public void accessModifier(
			@StringExpectation IStringExpectation expectation,
			IEObjectCoveringRegion offset) {

		EObject context = offset.getEObject();
		String actual = calculateActual(context);
		expectation.assertEquals(actual);
	}

	private String calculateActual(EObject context) {
		String actual = null;
		if (context instanceof TMember) {
			TMember tMember = (TMember) context;
			actual = tMember.getMemberAccessModifier().getName();
		} else {
			FunctionDeclaration functionDeclaration = EcoreUtil2.getContainerOfType(context, FunctionDeclaration.class);
			if (functionDeclaration != null) {
				actual = functionDeclaration.getDefinedType().getTypeAccessModifier().getName();
			} else {
				VariableStatement variableStatement = EcoreUtil2.getContainerOfType(context, VariableStatement.class);
				if (variableStatement != null) {
					context = variableStatement.getVarDecl().get(0);
					if (context instanceof ExportedVariableDeclaration) {
						actual = ((ExportedVariableDeclaration) context).getDefinedVariable().getTypeAccessModifier()
								.getName();
					} else if (context instanceof VariableDeclaration) {
						actual = "private";
					}
				} else if (context instanceof ExportDeclaration) {
					context = ((ExportDeclaration) context).getExportedElement();
					actual = calculateActual(context);
				} else if (context instanceof ParameterizedPropertyAccessExpression) {
					ParameterizedPropertyAccessExpression ppae = (ParameterizedPropertyAccessExpression) context;
					IdentifiableElement ie = ppae.getProperty();
					actual = calculateActual(ie);
				} else if (context instanceof ParameterizedCallExpression) {
					ParameterizedCallExpression pce = (ParameterizedCallExpression) context;
					Expression targetExpr = pce.getTarget();
					actual = calculateActual(targetExpr);
				} else {
					N4MemberDeclaration member = EcoreUtil2.getContainerOfType(context, N4MemberDeclaration.class);
					N4TypeDeclaration type = EcoreUtil2.getContainerOfType(context, N4TypeDeclaration.class);
					if (type == null && member == null) {
						actual = "no element with access modifier found";
					} else if (type != null && (member == null || EcoreUtil.isAncestor(member, type))) {
						actual = type.getDefinedType().getTypeAccessModifier().getName();
					} else {
						actual = member.getDefinedTypeElement().getMemberAccessModifier().getName();
					}
				}
			}
		}
		return actual;
	}
}
