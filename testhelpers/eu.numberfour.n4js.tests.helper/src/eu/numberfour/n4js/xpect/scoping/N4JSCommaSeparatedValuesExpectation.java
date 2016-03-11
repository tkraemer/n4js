/*******************************************************************************
 * Copyright (c) 2012 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package eu.numberfour.n4js.xpect.scoping;

import static eu.numberfour.n4js.xpect.scoping.EObjectDescriptionToNameWithPositionMapper.descriptionToNameWithPosition;
import static eu.numberfour.n4js.xpect.scoping.EObjectDescriptionToNameWithPositionMapper.getNameFromNameWithPosition;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.util.Pair;
import org.junit.ComparisonFailure;
import org.xpect.XpectInvocation;
import org.xpect.expectation.AbstractExpectation;
import org.xpect.expectation.AbstractExpectationParser;
import org.xpect.expectation.AbstractExpectationProvider;
import org.xpect.expectation.ActualCollection;
import org.xpect.expectation.ActualCollection.ActualItem;
import org.xpect.expectation.ActualCollection.ToString;
import org.xpect.expectation.ExpectationCollection;
import org.xpect.expectation.ExpectationCollection.ExpectationItem;
import org.xpect.expectation.IExpectationRegion;
import org.xpect.expectation.TargetSyntaxSupport;
import org.xpect.parameter.IParameterParser.ISingleParameterParser;
import org.xpect.parameter.IParameterParser.SingleParameterParser;
import org.xpect.text.Text;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Sets;

import eu.numberfour.n4js.xpect.scoping.N4JSCommaSeparatedValuesExpectation.N4JSCommaSeparatedValuesExpectationParser;

/**
 * @author Moritz Eysholdt - Initial contribution and API
 * @author Jörg Reichert, Jens von Pilgrim <jens.von.pilgrim@numberfour.eu> - N4JS adaptations
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@SingleParameterParser(N4JSCommaSeparatedValuesExpectationParser.class)
public @interface N4JSCommaSeparatedValuesExpectation {

	/***/
	class N4JSExpectationCollection extends ExpectationCollection {

		public Collection<ExpectationItem> getItems() {
			return items;
		}
	}

	/***/
	public class N4JSCommaSeparatedValuesExpectationImpl extends AbstractExpectation implements
	IN4JSCommaSeparatedValuesExpectation {
		private final N4JSCommaSeparatedValuesExpectation annotation;

		/***/
		public N4JSCommaSeparatedValuesExpectationImpl(N4JSCommaSeparatedValuesExpectation annotation,
				TargetSyntaxSupport syntax,
				IExpectationRegion region) {
			super(syntax, region);
			this.annotation = annotation;
		}

		@Override
		public void assertEquals(Iterable<?> actual) {
			assertEquals(actual, null);
		}

		@Override
		public void assertEquals(Iterable<?> actual, Predicate<String> predicate) {
			N4JSExpectationCollection exp = new N4JSExpectationCollection();
			exp.setCaseSensitive(annotation.caseSensitive());
			exp.setOrdered(annotation.ordered());
			exp.setQuoted(annotation.quoted());
			exp.setSeparator(',');
			exp.setWhitespaceSensitive(annotation.whitespaceSensitive());
			exp.init(getExpectation());

			ActualCollection act = new ActualCollection();
			act.setTargetLiteralSupport(getTargetSyntaxLiteral());
			act.setCaseSensitive(annotation.caseSensitive());
			act.setOrdered(annotation.ordered());
			act.setQuoted(annotation.quoted());
			act.setSeparator(',');
			act.setWhitespaceSensitive(annotation.whitespaceSensitive());

			if (actual != null && predicate != null) {
				if (exp.isWildcard())
					act.init(exp.applyPredicate(predicate), annotation.itemFormatter());
				else
					act.init(actual, annotation.itemFormatter());
			} else if (predicate != null)
				act.init(exp.applyPredicate(predicate), annotation.itemFormatter());
			else if (actual != null)
				act.init(actual, annotation.itemFormatter());
			else
				throw new NullPointerException();

			adaptActual(actual, exp, act);

			String nl = new Text(this.getRegion().getDocument()).getNL();
			if (!exp.matches(act)) {
				StringBuilder expString = new StringBuilder();
				StringBuilder actString = new StringBuilder();
				boolean expWrap = false;
				boolean expEmpty = false;
				boolean actWrap = false;
				int lineLength = 0, lineCount = 0;
				for (Pair<Collection<ExpectationItem>, ActualItem> pair : exp.map(act)) {
					String expItem = null;
					String actItem = null;
					if (pair.getFirst() != null && !pair.getFirst().isEmpty()) {
						if (pair.getSecond() != null)
							expItem = pair.getSecond().getEscaped();
						else
							expItem = pair.getFirst().iterator().next().getEscaped();
					} else {
						if (pair.getSecond() != null)
							expItem = str(pair.getSecond().getEscaped().length());
					}
					if (pair.getSecond() != null) {
						actItem = pair.getSecond().getEscaped();
						lineCount++;
						lineLength += actItem.length() + 2;
						boolean count = annotation.maxItemsPerLine() > 0 && lineCount > annotation.maxItemsPerLine();
						boolean width = annotation.maxLineWidth() > 0 && lineLength > annotation.maxLineWidth();
						if (count || width)
							expWrap = actWrap = true;
					}
					if (expItem != null && expString.length() > 0) {
						if (expWrap) {
							expString.append(expEmpty ? nl : "," + nl);
							expWrap = false;
						} else
							expString.append(expEmpty ? "  " : ", ");
					}
					if (actItem != null && actString.length() > 0) {
						if (actWrap) {
							actString.append("," + nl);
							actWrap = false;
							lineCount = 0;
							lineLength = 0;
						} else
							actString.append(", ");
					}
					if (expItem != null) {
						expString.append(expItem);
						expEmpty = expItem.trim().length() == 0;
					}
					if (actItem != null)
						actString.append(actItem);
				}
				String expDoc = replaceInDocument(expString.toString());
				String actDoc = replaceInDocument(actString.toString());
				throw new ComparisonFailure("", expDoc, actDoc);
			}
		}

		/**
		 * Explicitly request all expected items, as they may appear in scope only on explicit request.
		 */
		private void adaptActual(Iterable<?> actual, N4JSExpectationCollection exp, ActualCollection act) {
			if (actual instanceof ScopeAwareIterable) {

				ScopeAwareIterable actualScopeAwareIterable = (ScopeAwareIterable) actual;
				Set<String> actualElementsWithPosition = Sets.newLinkedHashSet(actualScopeAwareIterable);

				IScope scope = actualScopeAwareIterable.getScope();
				URI currentURI = actualScopeAwareIterable.getCurrentURI();
				for (ExpectationItem item : exp.getItems()) {
					String expectedNameWithPosition = item.getPure();

					if (!actualElementsWithPosition.contains(expectedNameWithPosition)) {
						String expectedName = getNameFromNameWithPosition(expectedNameWithPosition);
						IEObjectDescription elementDescr = scope.getSingleElement(QualifiedName.create(expectedName));
						if (elementDescr != null) {
							actualElementsWithPosition.add(descriptionToNameWithPosition(currentURI,
									((ScopeAwareIterable) actual).withLineNumber, elementDescr));
						}
					}
				}
				String[] array = new String[actualElementsWithPosition.size()];
				act.init(actualElementsWithPosition.toArray(array));
			}
		}

		@Override
		public void assertEquals(Predicate<String> predicate) {
			assertEquals(null, predicate);
		}

		/***/
		public N4JSCommaSeparatedValuesExpectation getAnnotation() {
			return annotation;
		}

		/***/
		protected String str(int length) {
			StringBuilder b = new StringBuilder();
			for (int i = 0; i < length; i++)
				b.append(" ");
			return b.toString();
		}

	}

	/***/
	public class N4JSCommaSeparatedValuesExpectationParser extends AbstractExpectationParser implements
	ISingleParameterParser {
		private final N4JSCommaSeparatedValuesExpectation annotation;

		/***/
		public N4JSCommaSeparatedValuesExpectationParser(N4JSCommaSeparatedValuesExpectation cfg) {
			super();
			this.annotation = cfg;
		}

		/***/
		public N4JSCommaSeparatedValuesExpectation getAnnotation() {
			return annotation;
		}

		@Override
		public IParsedParameterProvider parseRegion(XpectInvocation invocation, int paramIndex,
				List<IClaimedRegion> claims) {
			IExpectationRegion region = claimRegion(invocation, paramIndex);
			if (region != null)
				return new N4JSCommaSeparatedValuesExpectationProvider(annotation, region);
			return null;
		}
	}

	/***/
	public class N4JSCommaSeparatedValuesExpectationProvider extends
	AbstractExpectationProvider<IN4JSCommaSeparatedValuesExpectation> {
		private final N4JSCommaSeparatedValuesExpectation annotation;

		/***/
		public N4JSCommaSeparatedValuesExpectationProvider(N4JSCommaSeparatedValuesExpectation annotation,
				IExpectationRegion region) {
			super(region);
			this.annotation = annotation;
		}

		@Override
		protected IN4JSCommaSeparatedValuesExpectation createExpectation(TargetSyntaxSupport targetSyntax) {
			return new N4JSCommaSeparatedValuesExpectationImpl(annotation, targetSyntax, getClaimedRegion());
		}

		/***/
		public N4JSCommaSeparatedValuesExpectation getAnnotation() {
			return annotation;
		}
	}

	/***/
	boolean caseSensitive() default true;

	/***/
	Class<? extends Function<Object, String>> itemFormatter() default ToString.class;

	/***/
	int maxItemsPerLine() default -1;

	/***/
	int maxLineWidth() default 80;

	/***/
	boolean ordered() default false;

	/***/
	boolean quoted() default false;

	/***/
	boolean whitespaceSensitive() default false;

}
