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
package eu.numberfour.n4js.antlr.delimiters;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Collections;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xpand2.XpandExecutionContext;
import org.eclipse.xtext.Grammar;
import org.eclipse.xtext.formatting.IFormatter;
import org.eclipse.xtext.formatting.ILineSeparatorInformation;
import org.eclipse.xtext.formatting.impl.FormattingConfig;
import org.eclipse.xtext.generator.Generator;
import org.eclipse.xtext.generator.parser.antlr.debug.SimpleAntlrRuntimeModule;
import org.eclipse.xtext.generator.parser.antlr.debug.SimpleAntlrStandaloneSetup;
import org.eclipse.xtext.generator.parser.antlr.debug.formatting.SimpleAntlrFormatter;
import org.eclipse.xtext.resource.SaveOptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.util.StringInputStream;

import com.google.common.io.Files;
import com.google.inject.Binder;
import com.google.inject.Guice;
import com.google.inject.Injector;

import eu.numberfour.n4js.antlr.generator.NewlineNormalizer;

/**
 * A {@link org.eclipse.xtext.generator.parser.antlr.DebugAntlrGeneratorFragment} with support for encoding and custom
 * line breaks.
 */
@SuppressWarnings("restriction")
public class DebugAntlrGeneratorFragment extends org.eclipse.xtext.generator.parser.antlr.DebugAntlrGeneratorFragment {

	@Override
	public void generate(Grammar grammar, XpandExecutionContext ctx) {
		super.generate(grammar, ctx);
		String srcGenPath = ctx.getOutput().getOutlet(Generator.SRC_GEN).getPath();
		String encoding = getEncoding(ctx, Generator.SRC_GEN);
		String absoluteGrammarFileName = srcGenPath + "/" + getGrammarFileName(grammar, getNaming()).replace('.', '/')
				+ ".g";
		prettyPrint(absoluteGrammarFileName, encoding);
	}

	@SuppressWarnings("resource")
	private void prettyPrint(String absoluteGrammarFileName, String encoding) {
		try {
			String content = Files.toString(new File(absoluteGrammarFileName), Charset.forName(encoding));
			final ILineSeparatorInformation unixLineSeparatorInformation = new ILineSeparatorInformation() {
				@Override
				public String getLineSeparator() {
					return NewlineNormalizer.UNIX_LINE_DELIMITER;
				}
			};
			Injector injector = new SimpleAntlrStandaloneSetup() {
				@Override
				public Injector createInjector() {
					return Guice.createInjector(new SimpleAntlrRuntimeModule() {
						@Override
						public void configure(Binder binder) {
							super.configure(binder);
							binder.bind(ILineSeparatorInformation.class).toInstance(unixLineSeparatorInformation);
						}

						@Override
						public Class<? extends IFormatter> bindIFormatter() {
							return PlatformIndependantFormatter.class;
						}
					});
				}
			}.createInjectorAndDoEMFRegistration();
			XtextResource resource = injector.getInstance(XtextResource.class);
			resource.setURI(URI.createFileURI(absoluteGrammarFileName));

			// no need to close StringInputStream since it doesn't allocate resources
			resource.load(new StringInputStream(content, encoding),
					Collections.singletonMap(XtextResource.OPTION_ENCODING, encoding));
			if (!resource.getErrors().isEmpty()) {
				throw new RuntimeException(resource.getErrors().toString());
			}
			ByteArrayOutputStream outputStream = new ByteArrayOutputStream(content.length());
			resource.save(outputStream, SaveOptions.newBuilder().format().getOptions().toOptionsMap());
			String postprocessContent = new NewlineNormalizer().toUnixLineDelimiter(
					new String(outputStream.toByteArray(), encoding));
			Files.write(postprocessContent, new File(absoluteGrammarFileName), Charset.forName(encoding));
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	static class PlatformIndependantFormatter extends SimpleAntlrFormatter {
		@Override
		protected void configureFormatting(FormattingConfig c) {
			super.configureFormatting(c);
			c.setLinewrap().between(getGrammarAccess().getML_COMMENTRule(),
					getGrammarAccess().getAntlrGrammarAccess().getGrammarKeyword_0());
		}
	}

	@Override
	protected String getTemplate() {
		return org.eclipse.xtext.generator.parser.antlr.DebugAntlrGeneratorFragment.class.getName().replaceAll(
				"\\.", "::");
	}

}
