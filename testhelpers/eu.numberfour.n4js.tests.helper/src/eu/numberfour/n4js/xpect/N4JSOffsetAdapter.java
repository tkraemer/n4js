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

import java.util.Collections;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.resource.XtextResource;
import org.xpect.parameter.AbstractOffsetProvider;
import org.xpect.parameter.IParameterParser.IParsedParameterProvider;
import org.xpect.parameter.IParameterProvider;
import org.xpect.parameter.XpectParameterAdapter;
import org.xpect.state.StateContainer;
import org.xpect.text.IRegion;
import org.xpect.xtext.lib.setup.ThisResource;
import org.xpect.xtext.lib.util.XtextOffsetAdapter;

/**
 * Extension of default parameter adapter providing support for {@link IEObjectCoveringRegion}, which basically is an
 * offset adapter taking the length of the location into account.
 *
 * This adapter needs to be added to a test via annotation {@link org.xpect.parameter.XpectParameterAdapter}, i.e.
 * {@code @XpectParameterAdapter(N4JSOffsetAdapter.class)}.
 */
@XpectParameterAdapter
public class N4JSOffsetAdapter extends XtextOffsetAdapter {

	@Override
	public IParameterProvider adapt(IParameterProvider provider, Class<?> expectedType) {
		if (provider instanceof AbstractOffsetProvider) {
			AbstractOffsetProvider delegate = (AbstractOffsetProvider) provider;
			if (expectedType == IEObjectCoveringRegion.class)
				return new EObjectCoveringRegionProvider(delegate);
		}
		return super.adapt(provider, expectedType);
	}

	@Override
	protected boolean canAdapt(Class<?> expectedType) {
		return expectedType == IEObjectCoveringRegion.class ||
				super.canAdapt(expectedType);
	}

	@Override
	public boolean canAdapt(IParameterProvider provider, Class<?> expectedType) {
		return provider instanceof AbstractOffsetProvider && canAdapt(expectedType);
	}

	/**
	 * Provides an AST element of type {@link EObject}, previously found in the AST via
	 * {@link EObjectCoveringRegionProvider}. The AST related node covers the complete region defined by the offset of
	 * the specified location and the length of the specified location. That is, for a given test class with a parameter
	 * 'of' as OFFSET (via {@code @ParameterParser(syntax = "('of' arg1=OFFSET)?")}), the following two tests <br/>
	 * {@code // XPECT ... of 'a as B'} <br/>
	 * and <br/>
	 * {@code // XPECT ... of 'a'} <br/>
	 * will return different objects (CastExpression vs. IdentifierRef). If no region is defined, this test will return
	 * the next element after the test line.
	 */
	public static interface IEObjectCoveringRegion extends IEObjectOwner {
		// no new fields
	}

	/***/
	public static class EObjectCoveringRegion implements IEObjectCoveringRegion {
		final EObject eObj;

		/***/
		public EObjectCoveringRegion(EObject eObj) {
			this.eObj = eObj;
		}

		@Override
		public EObject getEObject() {
			return eObj;
		}
	}

	/**
	 * Provides an {@link IEObjectCoveringRegion} parameter, activated by {@link N4JSOffsetAdapter}.
	 */
	public static class EObjectCoveringRegionProvider implements IParsedParameterProvider {

		private final AbstractOffsetProvider delegate;

		/***/
		public EObjectCoveringRegionProvider(AbstractOffsetProvider delegate) {
			this.delegate = delegate;
		}

		@Override
		public boolean canProvide(Class<?> expectedType) {
			return EObject.class.isAssignableFrom(IEObjectCoveringRegion.class);
		}

		@Override
		@SuppressWarnings("unchecked")
		public <T> T get(Class<T> expectedType, StateContainer context) {
			XtextResource xtextResource = context.get(XtextResource.class, ThisResource.class).get();
			EObject eObj = find(xtextResource);
			return (T) new EObjectCoveringRegion(eObj);
		}

		/***/
		protected EObject find(XtextResource res) {
			int offset = delegate.getOffset();
			int length = (this.getSemanticRegions().isEmpty()) ? 0 : this.getSemanticRegions().get(0).getLength();
			int endOffset = offset + length;
			EObject semanticObject = null;

			INode node = NodeModelUtils.findLeafNodeAtOffset(res.getParseResult().getRootNode(), offset);
			while (node != null) {
				EObject actualObject = NodeModelUtils.findActualSemanticObjectFor(node);
				if (actualObject != null) {
					int nodeEndOffset = node.getEndOffset();
					if (nodeEndOffset <= endOffset || semanticObject == null) {
						semanticObject = actualObject;
					}
					if (nodeEndOffset >= endOffset) {
						break;
					}
				}
				node = node.getParent();
			}
			return semanticObject;

		}

		@Override
		public IRegion getClaimedRegion() {
			if (delegate instanceof IParsedParameterProvider) {
				return ((IParsedParameterProvider) delegate).getClaimedRegion();
			} else {
				return null;
			}
		}

		@Override
		public List<IRegion> getSemanticRegions() {
			if (delegate instanceof IParsedParameterProvider) {
				return ((IParsedParameterProvider) delegate).getSemanticRegions();
			} else {
				return Collections.emptyList();
			}
		}

	}

}
