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
package eu.numberfour.n4js.ts.conversions;

import org.eclipse.xtext.conversion.IValueConverter;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.nodemodel.INode;

import eu.numberfour.n4js.ts.types.NullModifier;

/**
 * Converts explicit declared null modifiers to {@link NullModifier} and vice versa. Note that the converter is not
 * symmetrical. That is, {@code toValue(toString(NullModifier.ISNULL),null)} is converted to {@link NullModifier#NA}.
 */
public class NullModifierValueConverter implements IValueConverter<NullModifier> {

	static final String NULLABLE_TOKEN = "nullable";
	static final String NOTNULL_TOKEN = "notnull";

	/* (non-Javadoc)
	 * @see org.eclipse.xtext.conversion.IValueConverter#toValue(java.lang.String, org.eclipse.xtext.nodemodel.INode)
	 */
	@Override
	public NullModifier toValue(String string, INode node)
			throws ValueConverterException {
		if (NOTNULL_TOKEN.equals(string)) {
			return NullModifier.NOTNULL;
		}
		if (NULLABLE_TOKEN.equals(string)) {
			return NullModifier.NULLABLE;
		}
		return NullModifier.NA;

	}

	/* (non-Javadoc)
	 * @see org.eclipse.xtext.conversion.IValueConverter#toString(java.lang.Object)
	 */
	@Override
	public String toString(NullModifier value) throws ValueConverterException {
		switch (value) {
		case NOTNULL: return NOTNULL_TOKEN;
		case NULLABLE: return NULLABLE_TOKEN;
		default: return "";
		}
	}

}
