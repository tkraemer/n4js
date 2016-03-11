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
package eu.numberfour.n4js.xpect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

import org.eclipse.xtend2.lib.StringConcatenation;
import org.junit.ComparisonFailure;
import org.xpect.XpectInvocation;
import org.xpect.expectation.AbstractExpectation;
import org.xpect.expectation.AbstractExpectationParser;
import org.xpect.expectation.AbstractExpectationProvider;
import org.xpect.expectation.IExpectationRegion;
import org.xpect.expectation.IStringExpectation;
import org.xpect.expectation.TargetSyntaxSupport;
import org.xpect.parameter.IParameterParser.ISingleParameterParser;
import org.xpect.parameter.IParameterParser.SingleParameterParser;

import eu.numberfour.n4js.xpect.LineFeedAwareStringExpectation.LineFeedAwareStringExpectationParser;

/**
 * *** NOTE *** NOTE *** NOTE ***
 *
 * There exists an exact copy of this file at:
 * /eu.numberfour.n4js.xpect.ui/src/eu/numberfour/n4js/xpect/ui/xpectmethods/LineFeedAwareStringExpectation.java
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@SingleParameterParser(LineFeedAwareStringExpectationParser.class)
public @interface LineFeedAwareStringExpectation {

	/***/
	public class LineFeedAwareStringExpectationImpl extends AbstractExpectation implements IStringExpectation {

		private final LineFeedAwareStringExpectation annotation;

		/***/
		public LineFeedAwareStringExpectationImpl(LineFeedAwareStringExpectation annotation,
				TargetSyntaxSupport targetSyntax,
				IExpectationRegion region) {
			super(targetSyntax, region);
			this.annotation = annotation;
		}

		@Override
		public void assertEquals(Object string) {
			if (string == null)
				throw new NullPointerException("Object is null");
			String actual = string.toString();
			String escapedActual = getTargetSyntaxLiteral().escape(actual);
			String originalExpectation = getExpectation();
			String migratedExpectation = originalExpectation;
			if (getAnnotation().ignoreLineEndings()) {
				{
					StringConcatenation concatenation = new StringConcatenation("\n");
					concatenation.append(migratedExpectation);
					migratedExpectation = concatenation.toString();
				}
				{
					StringConcatenation concatenation = new StringConcatenation("\n");
					concatenation.append(escapedActual);
					escapedActual = concatenation.toString();
				}
			}
			if (!migratedExpectation.equals(escapedActual)) {
				String expDoc = replaceInDocument(originalExpectation);
				String actDoc = replaceInDocument(escapedActual);
				throw new ComparisonFailure("", expDoc, actDoc);
			}
		}

		/***/
		public LineFeedAwareStringExpectation getAnnotation() {
			return annotation;
		}

	}

	/***/
	public class LineFeedAwareStringExpectationParser extends AbstractExpectationParser implements
			ISingleParameterParser {
		private final LineFeedAwareStringExpectation annotation;

		/***/
		public LineFeedAwareStringExpectationParser(LineFeedAwareStringExpectation cfg) {
			super();
			this.annotation = cfg;
		}

		/***/
		public LineFeedAwareStringExpectation getAnnotation() {
			return annotation;
		}

		@Override
		public IParsedParameterProvider parseRegion(XpectInvocation invocation, int paramIndex,
				List<IClaimedRegion> claims) {
			IExpectationRegion region = claimRegion(invocation, paramIndex);
			if (region != null)
				return new LineFeedAwareStringExpectationProvider(annotation, region);
			return null;
		}
	}

	/***/
	public class LineFeedAwareStringExpectationProvider extends AbstractExpectationProvider<IStringExpectation> {

		private final LineFeedAwareStringExpectation annotation;

		/***/
		public LineFeedAwareStringExpectationProvider(LineFeedAwareStringExpectation cfg, IExpectationRegion region) {
			super(region);
			this.annotation = cfg;
		}

		@Override
		protected IStringExpectation createExpectation(TargetSyntaxSupport targetSyntax) {
			return new LineFeedAwareStringExpectationImpl(annotation, targetSyntax, getClaimedRegion());
		}

		/***/
		public LineFeedAwareStringExpectation getAnnotation() {
			return annotation;
		}
	}

	/***/
	boolean ignoreLineEndings() default false;

}
