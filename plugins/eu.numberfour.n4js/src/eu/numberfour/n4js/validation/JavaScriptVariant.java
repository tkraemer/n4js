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
package eu.numberfour.n4js.validation;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.xtext.EcoreUtil2;

import eu.numberfour.n4js.n4JS.Block;
import eu.numberfour.n4js.n4JS.Expression;
import eu.numberfour.n4js.n4JS.ExpressionStatement;
import eu.numberfour.n4js.n4JS.FunctionDefinition;
import eu.numberfour.n4js.n4JS.Script;
import eu.numberfour.n4js.n4JS.StringLiteral;

/**
 * JavaScript variant to adjust validation. See ECMAScript Spec, 10.2.1 Strict Mode Code.
 * <p>
 * Note that the variants are mutually exclusive. There is a precedence of the variants: n4js precedes strict precedes
 * unrestricted. That is, even if a script in a file with extension ".n4js" contains strict mode literals ("use strict")
 * the mode is always n4js.
 *
 *
 * @see <a
 *      href="https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Functions_and_function_scope/Strict_mode">MDN</a>
 */
public enum JavaScriptVariant {

	/** Default non-strict JavaScript variant */
	unrestricted,
	/** Strict mode */
	strict,
	/** N4JS mode */
	n4js,
	/** external mode */
	external;

	/**
	 * Literal value to indicate strict mode: "use strict" (or 'use strict')
	 */
	public final static String STRICT_MODE_LITERAL_VALUE = "use strict";

	/**
	 * Returns the variant at the given code element.
	 */
	public static JavaScriptVariant getVariant(EObject eobj) {
		String fileExt = fileExtension(eobj);
		boolean bFoundN4JS = fileExt.equals("n4js") ||
				(fileExt.equals("xt") && fileName(eobj).endsWith(".n4js.xt"));
		if (bFoundN4JS) {
			return n4js;
		}
		boolean bStrict = isContainedInStrictFunctionOrScript(eobj);
		if (bStrict) {
			return strict;
		}
		boolean bFoundN4JSD = fileExt.equals("n4jsd") ||
				(fileExt.equals("xt") && fileName(eobj).endsWith(".n4jsd.xt"));
		if (bFoundN4JSD) {
			return external;
		}
		return unrestricted;
	}

	/**
	 * Returns true, if the variant is active for the given model element. Note that the variants are mutually exclusive
	 * and that there is a variant precedence defined.
	 */
	public boolean isActive(EObject eobj) {
		return getVariant(eobj) == this;
	}

	private static String fileName(EObject eobj) {
		if (eobj != null) {
			Resource res = eobj.eResource();
			if (res != null) {
				URI uri = res.getURI();
				String filename = uri.lastSegment();
				if (filename != null) {
					return filename;
				}
			}
		}
		return "";
	}

	private static String fileExtension(EObject eobj) {
		if (eobj != null) {
			Resource res = eobj.eResource();
			if (res != null) {
				URI uri = res.getURI();
				String ext = uri.fileExtension();
				if (ext != null) {
					return ext.toLowerCase();
				}
			}
		}
		return "";
	}

	private static boolean isContainedInStrictFunctionOrScript(EObject eobj) {
		if (eobj == null) {
			return false;
		}
		FunctionDefinition functionDef = EcoreUtil2.getContainerOfType(eobj, FunctionDefinition.class);
		if (functionDef != null) {
			Block block = functionDef.getBody();
			if (block != null && startsWithStrictMode(block.getStatements())) {
				return true;
			}
			return isContainedInStrictFunctionOrScript(functionDef.eContainer());
		}
		Script script = EcoreUtil2.getContainerOfType(eobj, Script.class);
		if (script != null) {
			if (startsWithStrictMode(script.getScriptElements())) {
				return true;
			}
		}
		return false;
	}

	private static boolean startsWithStrictMode(EList<? extends EObject> eList) {
		if (eList == null || eList.isEmpty()) {
			return false;
		}
		EObject first = eList.get(0);
		if (first instanceof ExpressionStatement) {
			Expression expr = ((ExpressionStatement) first).getExpression();
			if (expr instanceof StringLiteral) {
				boolean equalsStrictLiteral = STRICT_MODE_LITERAL_VALUE.equals(((StringLiteral) expr).getValue());
				return equalsStrictLiteral;
			}
		}
		return false;
	}

	/**
	 * Returns true, if current variant is either unrestricted or strict, that is, if no N4JS mode is active.
	 */
	public boolean isECMAScript() {
		return this == strict || this == unrestricted;
	}
}
