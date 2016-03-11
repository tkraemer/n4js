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
package eu.numberfour.n4js.utils;

import java.io.IOException;
import java.io.Writer;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.io.IOContext;

/**
 * Factory for prettier JSON output than the default one.
 *
 * @see JsonPrettyPrinter
 */
public class JsonPrettyPrinterFactory extends JsonFactory {

	@Override
	protected JsonGenerator _createJsonGenerator(final Writer out, final IOContext ctxt) throws IOException {
		return super._createJsonGenerator(out, ctxt).setPrettyPrinter(new JsonPrettyPrinter());
	}

}
