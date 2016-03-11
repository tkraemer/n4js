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

import org.eclipse.xtext.util.internal.FormattingMigrator;
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

import eu.numberfour.n4js.xpect.PositionAwareStringExpectation.StringExpectationParser;

/**
 * Copied from StringExpectation due to hard-wired configuration.
 * */
@SuppressWarnings("restriction")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@SingleParameterParser(StringExpectationParser.class)
public @interface PositionAwareStringExpectation {

	/***/
	public class StringExpectationImpl extends AbstractExpectation implements IStringExpectation {

		private final PositionAwareStringExpectation annotation;
		CursorMarkerHelper cmh = null;

		/***/
		public StringExpectationImpl(PositionAwareStringExpectation annotation, TargetSyntaxSupport targetSyntax,
				IExpectationRegion region) {
			super(targetSyntax, region);
			this.annotation = annotation;
			cmh = new CursorMarkerHelper();
		}

		@Override
		public void assertEquals(Object string) {
			if (string == null)
				throw new NullPointerException("Object is null");
			String actual = string.toString();
			String escapedActual = getTargetSyntaxLiteral().escape(actual);
			String originalExpectation = getExpectation();
			String migratedExpectation;
			if (!annotation.whitespaceSensitive()) {
				FormattingMigrator migrator = new FormattingMigrator();
				migratedExpectation = migrator.migrate(escapedActual, originalExpectation);
			} else
				migratedExpectation = originalExpectation;
			if ((annotation.caseSensitive() && !migratedExpectation.equals(escapedActual))
					|| (!annotation.caseSensitive() && !migratedExpectation.equalsIgnoreCase(escapedActual))) {
				String expDoc = replaceInDocument(migratedExpectation);
				String actDoc = replaceInDocument(escapedActual);
				throw new ComparisonFailure("", expDoc, actDoc);
			}
		}

		@Override
		public String getExpectation() {
			StringBuffer exp = new StringBuffer(super.getExpectation());

			CursorMarkerHelper.deleteMarker(exp, cmh.markerCursor);
			CursorMarkerHelper.deleteMarker(exp, cmh.markerSelectionStart);
			CursorMarkerHelper.deleteMarker(exp, cmh.markerSelectionEnd);

			return exp.toString();
		}

		/***/
		public PositionAwareStringExpectation getAnnotation() {
			return annotation;
		}

	}

	/***/
	public class StringExpectationParser extends AbstractExpectationParser implements ISingleParameterParser {
		private final PositionAwareStringExpectation annotation;

		/***/
		public StringExpectationParser(PositionAwareStringExpectation cfg) {
			super();
			this.annotation = cfg;
		}

		/***/
		public PositionAwareStringExpectation getAnnotation() {
			return annotation;
		}

		@Override
		public IParsedParameterProvider parseRegion(XpectInvocation invocation, int paramIndex,
				List<IClaimedRegion> claims) {
			IExpectationRegion region = claimRegion(invocation, paramIndex);
			if (region != null)
				return new StringExpectationProvider(annotation, region);
			return null;
		}
	}

	/***/
	public class StringExpectationProvider extends AbstractExpectationProvider<IStringExpectation> {

		private final PositionAwareStringExpectation annotation;

		/***/
		public StringExpectationProvider(PositionAwareStringExpectation cfg, IExpectationRegion region) {
			super(region);
			this.annotation = cfg;
		}

		@Override
		protected IStringExpectation createExpectation(TargetSyntaxSupport targetSyntax) {
			return new StringExpectationImpl(annotation, targetSyntax, getClaimedRegion());
		}

		/***/
		public PositionAwareStringExpectation getAnnotation() {
			return annotation;
		}
	}

	/***/
	boolean caseSensitive() default true;

	/***/
	boolean whitespaceSensitive() default false;
}
