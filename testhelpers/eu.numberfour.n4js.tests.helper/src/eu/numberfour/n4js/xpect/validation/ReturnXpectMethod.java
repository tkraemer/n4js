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
package eu.numberfour.n4js.xpect.validation;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.xpect.expectation.IStringExpectation;
import org.xpect.expectation.StringExpectation;
import org.xpect.parameter.ParameterParser;
import org.xpect.runner.Xpect;
import org.xpect.xtext.lib.setup.ThisOffset;
import org.xpect.xtext.lib.setup.ThisResource;

import eu.numberfour.n4js.n4JS.Block;
import eu.numberfour.n4js.n4JS.FunctionDeclaration;
import eu.numberfour.n4js.n4JS.Statement;
import eu.numberfour.n4js.validation.validators.FunctionFullReport;
import eu.numberfour.n4js.validation.validators.ReturnMode;
import eu.numberfour.n4js.validation.validators.ReturnOrThrowAnalysis;

/**
 */
public class ReturnXpectMethod {

	// Is it possible to Inject this instance?
	private final ReturnOrThrowAnalysis rotAnalyzer = new ReturnOrThrowAnalysis();

	/**
	 *
	 * @param expectation
	 *            Parsed expected String
	 * @param resource
	 *            effected Resource of test
	 * @param offset
	 *            actual position
	 */
	@Xpect
	@ParameterParser(syntax = "('at' offset=OFFSET)?")
	public void returnOrThrow(@StringExpectation IStringExpectation expectation,
			@ThisResource XtextResource resource, @ThisOffset INode offset) { // gute Ergebnisse

		if (offset == null)
		{
			// System.out.println("*** ouch null im context ");
			return;
		}

		String actual = evaluateReturnBehaviour(offset);

		// System.out.println("  - - - > Computed    = " + actual);
		// System.out.println("  - - - > Expectation = " + ((AbstractExpectation) expectation).getExpectation());

		expectation.assertEquals(actual);

	}

	private String evaluateReturnBehaviour(INode offset) {

		EObject context = NodeModelUtils.findActualSemanticObjectFor(offset);

		// System.out.println("  Kontext (INode) ist   : " + context.eClass().getName());

		// Function can be evaluated on their own and should be self-contained
		if (context instanceof FunctionDeclaration) {
			FunctionDeclaration funDecl = (FunctionDeclaration) context;
			ReturnMode result = rotAnalyzer.exitBehaviour(funDecl.getBody().getStatements());

			return returnModeToString(result);

		} else if (context instanceof Statement) {
			// Other Snippets might encounter sope-issues in break/continue with lables.

			ReturnMode result = rotAnalyzer.evalSubstatement((Statement) context);
			return returnModeToString(result);

		} else {

			// System.out.println("*** Xpect 'returnOrThrow' called on wrong context: " + context);
		}

		return "";
	}

	/**
	 * @param result
	 *            computed result
	 * @return String representation of the Result, NPE for Null
	 */
	private String returnModeToString(ReturnMode result) {
		if (result == null)
			return "NPE";

		return result.toString();
	}

	/**
	 * Checks whether the dead code analysis produces the expected result: dead code found or no dead code found.
	 *
	 * @param expectation
	 *            "dead code" or "no dead code"
	 * @param offset
	 *            the offset where the XPECT comment is used
	 */
	@Xpect
	@ParameterParser(syntax = "('at' offset=OFFSET)?")
	public void deadCode(@StringExpectation IStringExpectation expectation,
			// @ThisResource XtextResource resource,
			@ThisOffset INode offset) {
		if (offset == null)
		{
			return;
		}

		String actual = evaluateDeadCode(offset);

		expectation.assertEquals(actual);

	}

	private String evaluateDeadCode(INode offset) {

		EObject context = NodeModelUtils.findActualSemanticObjectFor(offset);

		// Function can be evaluated on their own and should be self-contained
		if (context instanceof FunctionDeclaration) {
			FunctionDeclaration funDecl = (FunctionDeclaration) context;
			Block body = funDecl.getBody();
			FunctionFullReport result = rotAnalyzer.exitBehaviourWithFullReport(body == null ? null : body
					.getStatements());

			return reportDeadCode(result);

		} else if (context instanceof Statement) {

			FunctionFullReport result = rotAnalyzer.evalSubstatementF((Statement) context);
			return reportDeadCode(result);

		} else {
			// Don't know
		}

		return "";
	}

	private String reportDeadCode(FunctionFullReport result) {
		if (result == null)
			return "NPE";
		if (result.deadCode.isEmpty())
			return "no dead code";
		else
			return "dead code";
	}

	// private String evaluateReturnBehaviour(IEReferenceAndEObject offset) {
	//
	// EObject context = offset.getEObject();
	// EReference ref = offset.getEReference();
	// System.out.println("\n\n      + + + + > reference = " + ref.getName());
	//
	// return evaluateReturnBehaviour((IEObjectOwner) offset);
	// }
	//
	// private String evaluateReturnBehaviour(IEObjectOwner offset) {
	//
	// EObject context = offset.getEObject();
	//
	// System.out.println("  Kontext ist   : " + context.eClass().getName());
	//
	// if (context instanceof FunctionDeclaration) {
	// FunctionDeclaration funDecl = (FunctionDeclaration) context;
	// ReturnMode result = rotAnalyzer.containsReturnOrThrow(funDecl.getBody().getStatements());
	//
	// return returnModeToString(result);
	//
	// } else if (context instanceof Statement) {
	// ReturnMode result = rotAnalyzer.containsReturnOrThrow((Statement) context);
	// return returnModeToString(result);
	// } else {
	//
	// System.out.println("*** Xpect 'returnOrThrow' called on wrong context: " + context);
	// }
	//
	// return "";
	// }
	//
	// private String evaluateReturnBehaviour(IEAttributeAndEObject offset) {
	//
	// EObject context = offset.getEObject();
	// EAttribute attrib = offset.getEAttribute();
	//
	// System.out.println("  Kontext ist   : " + context.eClass().getName());
	// System.out.println("     Attribute: " + attrib.getName() + " = " + context.eGet(attrib));
	//
	// if (context instanceof FunctionDeclaration) {
	// FunctionDeclaration funDecl = (FunctionDeclaration) context;
	// ReturnMode result = rotAnalyzer.containsReturnOrThrow(funDecl.getBody().getStatements());
	//
	// return returnModeToString(result);
	//
	// } else if (context instanceof Statement) {
	// ReturnMode result = rotAnalyzer.containsReturnOrThrow((Statement) context);
	// return returnModeToString(result);
	// } else {
	//
	// System.out.println("*** Xpect 'returnOrThrow' called on wrong context: " + context);
	// }
	//
	// return "";
	// }
	//
	//
	// @Xpect
	// @ParameterParser(syntax = "('at' offset=OFFSET)?")
	// public void xpIERef(@StringExpectation IStringExpectation expectation,
	// @ThisResource XtextResource resource, @ThisOffset IEReferenceAndEObject offset) {
	// System.out.println("===========================");
	// System.out.println("xpIERef");
	// evaluateReturnBehaviour(offset);
	// }
	//
	// @Xpect
	// @ParameterParser(syntax = "('at' offset=OFFSET)?")
	// public void xpINode(@StringExpectation IStringExpectation expectation,
	// @ThisResource XtextResource resource, @ThisOffset INode offset) { // gute Ergebnisse
	// System.out.println("===========================");
	// System.out.println("xpINode");
	// evaluateReturnBehaviour(offset);
	// }
	//
	// @Xpect
	// @ParameterParser(syntax = "('at' offset=OFFSET)?")
	// public void xpIEAttrib(@StringExpectation IStringExpectation expectation,
	// @ThisResource XtextResource resource, @ThisOffset IEAttributeAndEObject offset) { // nur Elemente mit
	// System.out.println("===========================");
	// System.out.println("xpIEAttrib");
	// evaluateReturnBehaviour(offset);
	// }
	//
	// // Attribut wie name,value,... -> keine Blöcke.

}
