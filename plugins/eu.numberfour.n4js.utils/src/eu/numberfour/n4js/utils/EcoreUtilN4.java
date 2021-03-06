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

import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.BasicEObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.xbase.lib.Procedures.Procedure0;

import com.google.common.base.Predicate;
import com.google.common.collect.Iterators;

/**
 */
public class EcoreUtilN4 {

	/**
	 * Returns the ResourceSet containing the first given object. If it is not contained in a ResourceSet, the other
	 * objects are searched for a containing ResourceSet in the given order. Returns null if none of the objects is
	 * contained in a ResourceSet.
	 */
	public static ResourceSet getResourceSet(EObject obj, EObject... otherObjs) {
		ResourceSet resSet = obj != null && obj.eResource() != null ? obj.eResource().getResourceSet() : null;
		if (resSet != null)
			return resSet;
		for (EObject currObj : otherObjs) {
			resSet = currObj != null && currObj.eResource() != null ? currObj.eResource().getResourceSet() : null;
			if (resSet != null)
				return resSet;
		}
		return null;
	}

	/**
	 * Returns the Resource containing the first given object. If it is not contained in a Resource, the other objects
	 * are searched for a containing Resource in the given order. Returns null if none of the objects is contained in a
	 * Resource.
	 */
	public static Resource getResource(EObject obj, EObject... otherObjs) {
		Resource res = obj != null ? obj.eResource() : null;
		if (res != null)
			return res;
		for (EObject currObj : otherObjs) {
			res = currObj != null ? currObj.eResource() : null;
			if (res != null)
				return res;
		}
		return null;
	}

	/**
	 * Returns all content filtered by given predicate. Children of filtered elements are omitted as well. Filtered
	 * means that the element does not satisfy the predicate. The given object itself is neither added to the result nor
	 * is it tested against the predicate.
	 *
	 * @param eobj
	 *            the root object, may be null
	 * @return the tree iterator, may be an empty iterator but never null
	 */
	public static TreeIterator<EObject> getAllContentsFiltered(EObject eobj, final Predicate<EObject> predicate) {
		if (eobj == null) {
			return emptyTreeIterator();
		}
		return new AbstractTreeIterator<EObject>(eobj, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<EObject> getChildren(Object element) {
				return Iterators.filter(((EObject) element).eContents().iterator(), predicate);
			}
		};
	}

	/**
	 * Returns all content of a given type, ignoring all elements which are not of the given type. This filters out also
	 * elements of the given type, if their container has a different type. The given object itself is neither added to
	 * the result nor is it tested against the predicate.
	 *
	 * @param eobj
	 *            the root object, may be null
	 * @return the tree iterator, may be an empty iterator but never null
	 */
	@SuppressWarnings("unchecked")
	public static <T> TreeIterator<T> getAllDirectlyFoundContentsOfType(EObject eobj, final Class<T> type) {
		if (eobj == null) {
			return emptyTreeIterator();
		}
		return (TreeIterator<T>) new AbstractTreeIterator<EObject>(eobj, false) {
			private static final long serialVersionUID = 1L;

			@Override
			public Iterator<? extends EObject> getChildren(Object element) {
				return (Iterator<? extends EObject>) Iterators.filter(((EObject) element).eContents().iterator(), type);
			}
		};
	}

	/**
	 * Invokes the given procedure after setting the EMF eDeliver property of all given objects to the value 'deliver'
	 * and afterwards restores the old delivery values. Objects that are neither an {@link EObject} nor a
	 * {@link Resource} are ignored.
	 */
	public static void doWithDeliver(boolean deliver, Procedure0 procedure, Object... eObjects) {
		final int len = eObjects.length;
		// set the delivery provided as argument
		final boolean[] oldDeliver = new boolean[len];
		for (int i = 0; i < len; i++) {
			final Object obj = eObjects[i];
			if (obj instanceof EObject) {
				oldDeliver[i] = ((EObject) obj).eDeliver();
				((EObject) obj).eSetDeliver(deliver);
			} else if (obj instanceof Resource) {
				oldDeliver[i] = ((Resource) obj).eDeliver();
				((Resource) obj).eSetDeliver(deliver);
			}
		}
		try {
			// do it!
			procedure.apply();
		} finally {
			// restore the old delivery
			for (int i = 0; i < len; i++) {
				final Object obj = eObjects[i];
				if (obj instanceof EObject) {
					((EObject) obj).eSetDeliver(oldDeliver[i]);
				} else if (obj instanceof Resource) {
					((Resource) obj).eSetDeliver(oldDeliver[i]);
				}
			}
		}
	}

	/**
	 * Empty tree iterator.
	 */
	public final static TreeIterator<?> EMPTY_TREE_ITERATOR = new TreeIterator<Object>() {

		@Override
		public boolean hasNext() {
			return false;
		}

		@Override
		public Object next() {
			throw new NoSuchElementException("empty iterator does not have next()");
		}

		@Override
		public void prune() { // does nothing
		}

	};

	/**
	 * Returns an empty tree iterator.
	 */
	@SuppressWarnings("unchecked")
	public static <T> TreeIterator<T> emptyTreeIterator() {
		return (TreeIterator<T>) EMPTY_TREE_ITERATOR;
	}

	/**
	 * Interface for handlers that perform some functionality on proxies (one at a time).
	 */
	public static interface ProxyHandler {
		/**
		 * Will be invoked for each proxy.
		 */
		public void handle(InternalEObject source, EReference eReference, BasicEObjectImpl targetProxy);
	}

	private static final class ProxyTraverser extends EcoreUtil.ProxyCrossReferencer {

		private final ProxyHandler handler;

		private ProxyTraverser(Collection<?> emfObjects, ProxyHandler handler) {
			super(emfObjects);
			this.handler = handler;
		}

		/**
		 * Will be called by super class once for each proxy. Super class will ensure that
		 * <code>crossReferencedEObject</code> is a proxy.
		 */
		@Override
		protected void add(InternalEObject eObject, EReference eReference, EObject crossReferencedEObject) {
			handler.handle(eObject, eReference, (BasicEObjectImpl) crossReferencedEObject);
		}

		private void handleProxyCrossReferences() {
			findProxyCrossReferences();
		}
	}

	/** Apply given proxy handler to all proxies in the given resource set. Nothing will be resolved. */
	public static final void handleProxyCrossReferences(ResourceSet resourceSet, ProxyHandler handler) {
		new ProxyTraverser(Collections.singleton(resourceSet), handler).handleProxyCrossReferences();
	}

	/** Apply given proxy handler to all proxies in the given resource. Nothing will be resolved. */
	public static final void handleProxyCrossReferences(Resource resource, ProxyHandler handler) {
		new ProxyTraverser(Collections.singleton(resource), handler).handleProxyCrossReferences();
	}

	/** Apply given proxy handler to all proxies in the given object and its contents. Nothing will be resolved. */
	public static final void handleProxyCrossReferences(EObject root, ProxyHandler handler) {
		new ProxyTraverser(Collections.singleton(root), handler).handleProxyCrossReferences();
	}

	/**
	 * Apply given proxy handler to all proxies in the given EMF objects. Nothing will be resolved.
	 *
	 * @param roots
	 *            must be "EMF objects", i.e. one of ResourceSet, Resource, or EObject.
	 */
	public static final void convertProxyCrossReferences(Collection<?> roots, ProxyHandler handler) {
		new ProxyTraverser(roots, handler).handleProxyCrossReferences();
	}

	/**
	 * Utility that detects unresolved proxies.
	 *
	 * @see EcoreUtilN4#hasUnresolvedProxies(EObject)
	 */
	private static class BailOutCrossReferencer extends EcoreUtil.ProxyCrossReferencer {

		private static class UnresolvedProxyException extends RuntimeException {
			// nothing to do
		}

		protected BailOutCrossReferencer(EObject eObject) {
			super(eObject);
		}

		/**
		 * Rather than recording the proxy, throw an exception that is handled internally.
		 */
		@Override
		protected void add(InternalEObject eObject, EReference eReference, EObject crossReferencedEObject) {
			throw new UnresolvedProxyException();
		}

		/**
		 * Returns true if the object or any of its children has unresolved proxies.
		 */
		protected static boolean hasUnresolvedProxies(EObject object) {
			try {
				new BailOutCrossReferencer(object).findProxyCrossReferences();
			} catch (UnresolvedProxyException e) {
				return true;
			}
			return false;
		}
	}

	/**
	 * Returns true if the object or any of its children has unresolved proxies.
	 */
	public static boolean hasUnresolvedProxies(EObject object) {
		return BailOutCrossReferencer.hasUnresolvedProxies(object);
	}
}
