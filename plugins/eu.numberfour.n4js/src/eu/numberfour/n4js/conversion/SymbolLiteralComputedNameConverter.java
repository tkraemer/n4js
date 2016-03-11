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
package eu.numberfour.n4js.conversion;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.Keyword;
import org.eclipse.xtext.XtextPackage;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.QualifiedNameValueConverter;
import org.eclipse.xtext.nodemodel.INode;

import com.google.inject.Inject;

import eu.numberfour.n4js.services.N4JSGrammarAccess;
import eu.numberfour.n4js.ts.conversions.ComputedPropertyNameValueConverter;

/**
 */
public class SymbolLiteralComputedNameConverter extends QualifiedNameValueConverter {

	private String delegateRuleName;
	private Keyword dotKeyword;

	@Override
	protected String getDelegateRuleName() {
		return delegateRuleName;
	}

	@Inject
	private void setGrammarAccess(N4JSGrammarAccess grammarAccess) {
		delegateRuleName = grammarAccess.getBindingIdentifierRule().getName();
		dotKeyword = grammarAccess.getSymbolLiteralComputedNameAccess().getFullStopKeyword_1_0();
	}

	@Override
	public String toValue(String string, INode node) throws ValueConverterException {
		String result = super.toValue(string, node);
		int dot = result.indexOf('.');
		if (dot >= 0) {
			// argument 'string' matches "<ident-1>.<ident-2>"
			// convert it to "#<ident-2>"
			return ComputedPropertyNameValueConverter.SYMBOL_IDENTIFIER_PREFIX
					+ string.substring(dot + 1, string.length());
		}
		return result;
	}

	@Override
	protected boolean isDelegateRuleCall(EObject grammarElement) {
		return dotKeyword != grammarElement && grammarElement.eClass() != XtextPackage.Literals.TERMINAL_RULE;
	}

}
