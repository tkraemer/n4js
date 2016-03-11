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

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.IValueConverterService;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;

import com.google.inject.Inject;

import eu.numberfour.n4js.services.N4JSGrammarAccess;

/**
 * Converts the module specifier used in import declarations into qualified module names.
 */
public class ModuleSpecifierValueConverter implements IValueConverter<String> {

	@Inject
	private IValueConverterService delegateService;
	@Inject
	private N4JSGrammarAccess grammarAccess;

	@Override
	public String toValue(String string, INode node) throws ValueConverterException {
		String withDots = string.replace('/', '.');
		return (String) delegateService.toValue(withDots, grammarAccess.getSTRINGRule().getName(), node);
	}

	@Override
	public String toString(String value) throws ValueConverterException {
		String withSlashes = value.replace('.', '/');
		return delegateService.toString(withSlashes, grammarAccess.getSTRINGRule().getName());
	}
}
