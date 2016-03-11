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
package eu.numberfour.n4js.xpect

import com.google.common.base.Function
import com.google.common.base.Predicate
import java.util.Collection
import org.eclipse.xtext.util.Pair
import org.junit.ComparisonFailure
import org.xpect.expectation.AbstractExpectation
import org.xpect.expectation.ActualCollection
import org.xpect.expectation.ActualCollection.ActualItem
import org.xpect.expectation.ActualCollection.ToString
import org.xpect.expectation.CommaSeparatedValuesExpectation
import org.xpect.expectation.CommaSeparatedValuesExpectation.CommaSeparatedValuesExpectationImpl
import org.xpect.expectation.ExpectationCollection
import org.xpect.expectation.ExpectationCollection.ExpectationItem
import org.xpect.expectation.ICommaSeparatedValuesExpectation
import org.xpect.text.Text

/**
 * Configurable CommaSeparatedValuesExpectation. Since the original implementations is tightly coupled to
 * an Annotation, which cannot change values dynamically, this class wraps around  a CSVE and makes the
 * parameters changeable.
 *
 * All properties of the Annotation are stored in a Config-Object and modifiable.
 */
class NCSVExpectation extends AbstractExpectation implements ICommaSeparatedValuesExpectation {

	public static class Config{
		public boolean caseSensitive =  true;
		public Class<? extends Function<Object, String>> itemFormatter = ToString;
		public int maxItemsPerLine = -1;
		public int maxLineWidth = 80;
		public boolean ordered = false;
		public boolean quoted = false;
		public boolean whitespaceSensitive = false;
		new(){}
		new(CommaSeparatedValuesExpectation a) {
			caseSensitive = a.caseSensitive
			itemFormatter = a.itemFormatter
			maxItemsPerLine = a.maxItemsPerLine;
			maxLineWidth = a.maxLineWidth
			ordered = a.ordered
			quoted = a.quoted
			whitespaceSensitive = a.whitespaceSensitive
		}

		public () => ExpectationCollection expectationCollectionProvider = [ return new ExpectationCollection() ]

	}

	public CommaSeparatedValuesExpectationImpl csvEImpl
	public NCSVExpectation.Config annotationCfg;

	/** Wrapping Constructor
	 * @param csvEImpl wrapped CSV Instance.
	 * */
	new(CommaSeparatedValuesExpectationImpl csvEImpl) {
		super(csvEImpl.targetSyntax, csvEImpl.region)
		this.csvEImpl = csvEImpl
		// copy stuff from annotation
		annotationCfg = new NCSVExpectation.Config(csvEImpl.annotation)
	}
	protected def String str(int length) {
			val StringBuilder b = new StringBuilder();
			for (var int i = 0; i < length; i++)
				b.append(" ");
			return b.toString();
	}
	override assertEquals(Iterable<?> string) {
		assertEquals(string, null)
	}

	override assertEquals(Predicate<String> predicate) {
		assertEquals(null, predicate)
	}

	override assertEquals(Iterable<?> actual, Predicate<String> predicate) {
			val ExpectationCollection exp = annotationCfg.expectationCollectionProvider.apply();
			exp.setCaseSensitive(annotationCfg.caseSensitive);
			exp.setOrdered(annotationCfg.ordered);
			exp.setQuoted(annotationCfg.quoted);
			exp.setSeparator(',');
			exp.setWhitespaceSensitive(annotationCfg.whitespaceSensitive);
			exp.init(getExpectation());

			val ActualCollection act = new ActualCollection();
			act.setTargetLiteralSupport(getTargetSyntaxLiteral());
			act.setCaseSensitive(annotationCfg.caseSensitive);
			act.setOrdered(annotationCfg.ordered);
			act.setQuoted(annotationCfg.quoted);
			act.setSeparator(',');
			act.setWhitespaceSensitive(annotationCfg.whitespaceSensitive);
			if (actual !== null && predicate !== null) {
				if (exp.isWildcard())
				{	act.init(exp.applyPredicate(predicate), annotationCfg.itemFormatter);}
				else
				{	act.init(actual, annotationCfg.itemFormatter);}
			} else if (predicate !== null)
			{	act.init(exp.applyPredicate(predicate), annotationCfg.itemFormatter);}
			else if (actual !== null)
			{	act.init(actual, annotationCfg.itemFormatter);}
			else
			{	throw new NullPointerException();}

			val String nl = new Text(this.getRegion().getDocument()).getNL();
			if (!exp.matches(act)) {
				var StringBuilder expString = new StringBuilder();
				var StringBuilder actString = new StringBuilder();
				var boolean expWrap = false;
				var boolean expEmpty = false;
				var boolean actWrap = false;
				var int lineLength = 0
				var int lineCount = 0;
				for (Pair<Collection<ExpectationItem>, ActualItem> pair : exp.map(act)) {
					var String expItem = null;
					var String actItem = null;
					if (pair.getFirst() !== null && !pair.getFirst().isEmpty()) {
						if (pair.getSecond() !== null)
							{expItem = pair.getSecond().getEscaped();}
						else
							{expItem = pair.getFirst().iterator().next().getEscaped();}
					} else {
						if(pair.getSecond()!== null) {
							expItem = str(pair.getSecond().getEscaped().length());
						}
					}
					if (pair.getSecond() !== null) {
						actItem = pair.getSecond().getEscaped();
						lineCount++;
						lineLength += actItem.length() + 2;
						val boolean count = annotationCfg.maxItemsPerLine > 0 && lineCount > annotationCfg.maxItemsPerLine;
						val boolean width = annotationCfg.maxLineWidth > 0 && lineLength > annotationCfg.maxLineWidth;
						if (count || width)
						{	expWrap = actWrap = true;}
					}
					if (expItem !== null && expString.length() > 0) {
						if (expWrap) {
							expString.append( if( expEmpty ) nl else ("," + nl) );
							expWrap = false;
						} else
						{	expString.append( if( expEmpty ) "  " else ", ");}
					}
					if (actItem !== null && actString.length() > 0) {
						if (actWrap) {
							actString.append("," + nl);
							actWrap = false;
							lineCount = 0;
							lineLength = 0;
						} else
							actString.append(", ");
					}
					if (expItem !== null) {
						expString.append(expItem);
						expEmpty = expItem.trim().length() == 0;
					}
					if (actItem !== null)
						actString.append(actItem);
				}
				val String expDoc = replaceInDocument(expString.toString());
				val String actDoc = replaceInDocument(actString.toString());
				throw new ComparisonFailure("", expDoc, actDoc);
			}
		}
}
