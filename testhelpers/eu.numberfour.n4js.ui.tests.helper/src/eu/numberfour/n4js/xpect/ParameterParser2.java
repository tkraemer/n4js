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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.util.Strings;
import org.eclipse.xtext.util.formallang.FollowerFunctionImpl;
import org.eclipse.xtext.util.formallang.Nfa;
import org.eclipse.xtext.util.formallang.NfaUtil;
import org.eclipse.xtext.util.formallang.NfaUtil.BacktrackHandler;
import org.eclipse.xtext.util.formallang.StringProduction;
import org.eclipse.xtext.util.formallang.StringProduction.ProdElement;
import org.xpect.XjmXpectMethod;
import org.xpect.XpectInvocation;
import org.xpect.parameter.AbstractOffsetProvider;
import org.xpect.parameter.IParameterParser.IMultiParameterParser;
import org.xpect.parameter.IParameterParser.IParsedParameterProvider;
import org.xpect.parameter.IParameterParser.MultiParameterParser;
import org.xpect.parameter.IParameterProvider;
import org.xpect.parameter.ParameterParser;
import org.xpect.parameter.ParameterParser.AssignedProduction;
import org.xpect.parameter.ParameterParser.Token;
import org.xpect.parameter.ParsedIntegerProvider;
import org.xpect.parameter.ParsedParameterProvider;
import org.xpect.state.StateContainer;
import org.xpect.text.IRegion;
import org.xpect.text.Region;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

/**
 * Provides same functionality as {@link ParameterParser}. Main reason for this annotation is to provide a different
 * implementation of ParameterParserImpl to support selection-markers:
 *
 * <pre>
 * <|> - CursorPosition
 * <[> - Selection start
 * <]> - Selection end.
 * </pre>
 */
@SuppressWarnings("restriction")
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@MultiParameterParser(ParameterParser2.ParameterParserImpl.class)
public @interface ParameterParser2 { /* */

	/**
	 * Cannot silently ovrride ParameterParserImpl: Annotations are not part of an inheritance hierarchy, so we have
	 * nothing to pass into the super-constructor of ParameterParserImpl(ParameterParser) as we only get
	 * ParameterParser2 Solution: copied class from {@code org.xpect.parameter.ParameterParser.ParameterParserImpl}
	 */
	@SuppressWarnings("javadoc")
	public static class ParameterParserImpl implements IMultiParameterParser {

		protected static class BacktrackItem {
			protected int offset;
			protected ProdElement token;
			protected String value;

			public BacktrackItem(int offset) {
				super();
				this.offset = offset;
			}

			public BacktrackItem(int offset, ProdElement token, String value) {
				super();
				this.offset = offset;
				this.token = token;
				this.value = value;
			}

			@Override
			public String toString() {
				return token + ":" + value;
			}
		}

		protected static final Pattern WS = Pattern.compile("^[\\s]+");

		private final ParameterParser2 annotation;

		public ParameterParserImpl(ParameterParser2 annotation) {
			this.annotation = annotation;
		}

		protected List<IParsedParameterProvider> mapNameToIndex(XpectInvocation invocation,
				Map<String, IParsedParameterProvider> params) {
			XjmXpectMethod method = invocation.getMethod();
			IParsedParameterProvider[] result = new IParsedParameterProvider[method.getParameterCount()];
			for (int i = 0; i < result.length; i++)
				result[i] = params.get("arg" + i);
			return Arrays.asList(result);
		}

		@Override
		public IRegion claimRegion(XpectInvocation invocation) {
			INode node = NodeModelUtils.getNode(invocation);
			int start = node.getOffset() + node.getLength();
			int end = invocation.getFile().getDocument().indexOf(annotation.endToken(), start);
			if (end < 0)
				end = invocation.getFile().getDocument().length();
			return new Region(start, end - start);
		}

		protected IParsedParameterProvider convertValue(XpectInvocation invocation, Token token, String value,
				IRegion claim, int offset) {
			Region semantic = new Region(offset, value.length());
			switch (token) {
			case OFFSET:
				// TODO overriden here:
				// return new MatchingOffsetProvider(invocation, value, claim, semantic);
				// usually we want two values out of here. First the offset=cursorpos and second
				// the active selection
				return new ParameterParser2.MatchingOffsetProvider2(invocation, value, claim,
						semantic);
			case INT:
				return new ParsedIntegerProvider(value, claim, semantic);
			case ID:
			case STRING:
			case TEXT:
				return new ParsedParameterProvider(new String(value), claim, semantic);
			}
			throw new RuntimeException();
		}

		@SuppressWarnings("unused")
		protected IRegion findParameterRegion(XpectInvocation invocation, List<IClaimedRegion> claims) {
			int start = 0, end = 0;
			List<IClaimedRegion> otherClaims = Lists.newArrayList();
			for (IClaimedRegion claim : claims)
				if (claim.getClaminer() == this) {
					start = claim.getOffset();
					end = start + claim.getLength();
				} else
					otherClaims.add(claim);
			for (IClaimedRegion claim : otherClaims)
				if (end > claim.getOffset())
					end = claim.getOffset();
			return new Region(start, end - start);
		}

		public ParameterParser2 getAnnotation() {
			return annotation;
		}

		protected Nfa<ProdElement> getParameterNfa(String syntax) {
			AssignedProduction prod = new AssignedProduction(syntax);
			FollowerFunctionImpl<ProdElement, String> ff = new FollowerFunctionImpl<>(prod);
			ProdElement start = prod.new ProdElement(StringProduction.ElementType.TOKEN);
			ProdElement stop = prod.new ProdElement(StringProduction.ElementType.TOKEN);
			Nfa<ProdElement> result = new NfaUtil().create(prod, ff, start, stop);
			return result;
		}

		protected Map<String, IParsedParameterProvider> parseParams(String paramSyntax, XpectInvocation invocation,
				IRegion claim) {
			if (Strings.isEmpty(paramSyntax))
				return Collections.emptyMap();
			String document = invocation.getFile().getDocument();
			final String text = document.substring(claim.getOffset(), claim.getOffset() + claim.getLength());
			Nfa<ProdElement> nfa = getParameterNfa(paramSyntax);
			Matcher ws = WS.matcher(text);
			List<BacktrackItem> trace = new NfaUtil().backtrack(nfa, new BacktrackItem(ws.find() ? ws.end() : 0),
					new BacktrackHandler<ProdElement, BacktrackItem>() {
						@SuppressWarnings("hiding")
						@Override
						public BacktrackItem handle(ProdElement state, BacktrackItem previous) {
							if (Strings.isEmpty(state.getValue()))
								return new BacktrackItem(previous.offset, state, state.getValue());
							if (Strings.isEmpty(state.getName())) {
								if (text.regionMatches(previous.offset, state.getValue(), 0, state.getValue().length())) {
									int newOffset = previous.offset + state.getValue().length();
									Matcher ws = WS.matcher(text).region(newOffset, text.length());
									int childOffset = ws.find() ? ws.end() : newOffset;
									return new BacktrackItem(childOffset, state, state.getValue());
								}
							} else {
								Token t = Token.valueOf(state.getValue());
								Matcher matcher = t.pattern.matcher(text).region(previous.offset, text.length());
								if (matcher.find()) {
									Matcher ws = WS.matcher(text).region(matcher.end(), text.length());
									int childOffset = ws.find() ? ws.end() : matcher.end();
									String value = matcher.groupCount() > 0 && matcher.group(1) != null ? matcher
											.group(1) : matcher
											.group(0);
									return new BacktrackItem(childOffset, state, value);
								}
							}
							return null;
						}

				@Override
						public boolean isSolution(BacktrackItem result) {
							return true;
						}

				@Override
						public Iterable<ProdElement> sortFollowers(BacktrackItem result, Iterable<ProdElement> followers) {
							return followers;
						}
					}); // END of BacktrackItem / backtrack()

			Map<String, IParsedParameterProvider> result = Maps.newHashMap();
			int offset = 0;
			if (trace != null && !trace.isEmpty()) {
				for (BacktrackItem item : trace) {
					if (item.token != null && item.token.getName() != null) {
						String key = item.token.getName();
						Token token = Token.valueOf(item.token.getValue());
						result.put(key, convertValue(invocation, token, item.value, claim, claim.getOffset() + offset));
					}
					offset = item.offset;
				}
				return result;
			}
			throw new RuntimeException("could not parse '" + text + "' with grammar '" + paramSyntax + "'");
		}

		@Override
		public List<IParsedParameterProvider> parseRegion(XpectInvocation invocation, List<IClaimedRegion> claims) {
			IRegion claim = findParameterRegion(invocation, claims);
			Map<String, IParsedParameterProvider> parsedParams = parseParams(annotation.syntax(), invocation, claim);
			return mapNameToIndex(invocation, parsedParams);
		}
	}

	/**
	 * Copied from {@link org.xpect.parameter.ParameterParser.MatchingOffsetProvider}
	 */
	@SuppressWarnings("javadoc")
	public static class MatchingOffsetProvider2 extends AbstractOffsetProvider implements IParsedParameterProvider {
		protected final XpectInvocation invocation;
		protected final String value;
		protected final IRegion claim;
		protected final IRegion semantic;

		public MatchingOffsetProvider2(XpectInvocation invocation, String value, IRegion claim, IRegion semantic) {
			super();
			this.invocation = invocation;
			this.value = value;
			this.claim = claim;
			this.semantic = semantic;
		}

		public XpectInvocation getInvocation() {
			return invocation;
		}

		public String getStringValue() {
			return value;
		}

		@Override
		public boolean canProvide(Class<?> expectedType) {
			return expectedType == RegionWithCursor.class ||
					super.canProvide(expectedType);
		}

		@SuppressWarnings("unchecked")
		@Override
		public <T> T get(Class<T> expectedType, StateContainer context) {
			if (expectedType == RegionWithCursor.class)
				return (T) getSelection();
			return super.get(expectedType, context);
		}

		final CursorMarkerHelper cmh = new CursorMarkerHelper();

		@Override
		public int getOffset() {
			String val = value;
			StringBuffer sb = new StringBuffer(val);

			@SuppressWarnings("unused")
			boolean hasCursor = CursorMarkerHelper.exists(val, cmh.markerCursor);
			boolean hasEndSelection = CursorMarkerHelper.exists(val, cmh.markerSelectionEnd);
			boolean hasStartSelection = CursorMarkerHelper.exists(val, cmh.markerSelectionStart);

			int cursorOffset = CursorMarkerHelper.deleteMarker(sb, cmh.markerCursor);
			int selStartOffset = CursorMarkerHelper.deleteMarker(sb, cmh.markerSelectionStart);
			int selEndOffset = CursorMarkerHelper.deleteMarker(sb, cmh.markerSelectionEnd);
			val = sb.toString();

			// reduce cursor offset if selection markers were inserted in front of.
			if (hasStartSelection && selStartOffset < cursorOffset)
				cursorOffset -= cmh.markerSelectionStart.length();
			if (hasEndSelection && selEndOffset < cursorOffset)
				cursorOffset -= cmh.markerSelectionEnd.length();

			int offset = findEndOfConfiguration();

			String text = invocation.getFile().getDocument();
			int result = text.indexOf(val, offset);
			if (result >= 0) {
				int off = result + cursorOffset;
				return off;
			} else
				throw new RuntimeException("OFFSET '" + val + "' not found");
		}

		public RegionWithCursor getSelection() {
			String val = value;
			StringBuffer sb = new StringBuffer(val);

			@SuppressWarnings("unused")
			boolean hasCursor = CursorMarkerHelper.exists(val, cmh.markerCursor);
			boolean hasEndSelection = CursorMarkerHelper.exists(val, cmh.markerSelectionEnd);
			boolean hasStartSelection = CursorMarkerHelper.exists(val, cmh.markerSelectionStart);

			int cursorOffset = CursorMarkerHelper.deleteMarker(sb, cmh.markerCursor);
			int selStartOffset = CursorMarkerHelper.deleteMarker(sb, cmh.markerSelectionStart);
			int selEndOffset = CursorMarkerHelper.deleteMarker(sb, cmh.markerSelectionEnd);
			val = sb.toString();

			// reduce cursor offset if selection markers were inserted in front of.
			if (hasStartSelection && selStartOffset < cursorOffset)
				cursorOffset -= cmh.markerSelectionStart.length();
			if (hasEndSelection && selEndOffset < cursorOffset)
				cursorOffset -= cmh.markerSelectionEnd.length();

			int offset = findEndOfConfiguration();

			String text = invocation.getFile().getDocument();
			int result = text.indexOf(val, offset);
			if (result < 0)
				throw new RuntimeException("OFFSET '" + val + "' not found");

			int globalCursorOffset = result + cursorOffset;
			if (hasEndSelection) {
				if (hasStartSelection) {
					return new RegionWithCursor(result + selStartOffset, selEndOffset - selStartOffset,
							globalCursorOffset);
				} else {
					// no startselection, so cursor is startselecction.

					if (selEndOffset < cursorOffset)
						throw new RuntimeException("OFFSET '" + value + " has no selection start("
								+ cmh.markerSelectionStart + ").");
					else
						return new RegionWithCursor(globalCursorOffset, selEndOffset - cursorOffset,
								globalCursorOffset);
				}
			} else {
				// No end Selection
				if (!hasStartSelection) {
					// not start & no end -> empty
					return new RegionWithCursor(globalCursorOffset);
				} else {
					// start but no end selection -> error
					throw new RuntimeException("OFFSET '" + value + " has no end selection marker ("
							+ cmh.markerSelectionEnd + ").");
				}
			}
		}

		/**
		 * @return offset of last Xpect-Invocation-Parameter.
		 */
		private int findEndOfConfiguration() {
			INode node = NodeModelUtils.getNode(invocation);
			int offset = node.getOffset() + node.getLength();
			// finding end of XPect-stmt and beginning of testcode
			for (IParameterProvider param : invocation.getParameters())
				if (param instanceof IParsedParameterProvider) {
					IRegion claimedRegion = ((IParsedParameterProvider) param).getClaimedRegion();
					int regionEnd = claimedRegion.getOffset() + claimedRegion.getLength();
					if (regionEnd > offset)
						offset = regionEnd;
				}
			return offset;
		}

		@Override
		public IRegion getClaimedRegion() {
			return claim;
		}

		@Override
		public List<IRegion> getSemanticRegions() {
			return Collections.singletonList(semantic);
		}

	}

	/**
	 * @return anno-parameter for ending the parameter-section
	 */
	String endToken() default "\n";

	/**
	 *
	 * @return ebnf form of parameter syntax
	 */
	String syntax() default "";

}
