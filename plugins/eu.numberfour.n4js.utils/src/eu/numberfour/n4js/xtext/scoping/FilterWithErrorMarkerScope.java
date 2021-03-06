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
package eu.numberfour.n4js.xtext.scoping;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.scoping.impl.AbstractScope;
import org.eclipse.xtext.scoping.impl.FilteringScope;

import com.google.common.collect.Iterables;

/**
 * Base class for scopes filtering out elements from the parent scope; the filtered elements may be wrapped into an
 * {@link IEObjectDescriptionWithError}. If they are wrapped the user get better feedback (instead of just an
 * unresolvable element a nice error message is created stating that the element is there but not accessible from the
 * current context). This wrapping is done in {@link #wrapFilteredDescription(IEObjectDescription)}, which may return
 * <code>null</code> if filtered elements are to be completely removed.
 * <p>
 * Implementation note: Since the encapsulated parent scope is only an instance of {@link IScope}, we cannot make any
 * assumption on this parent. Thus it makes no sense to inherit this filtering scope from {@link AbstractScope}, since
 * we cannot correctly delegate to the parent anyway (this has been done in predecessor versions of this
 * implementation).
 */
public abstract class FilterWithErrorMarkerScope implements IScope {

	/** The encapsulated parent scope */
	protected final IScope parent;

	/**
	 * Creates this filter as a proxy around the given parent.
	 */
	public FilterWithErrorMarkerScope(IScope parent) {
		this.parent = parent;
	}

	/**
	 * Wraps the original description into an description indicating the error, or returns null if filtered descriptions
	 * are to be ignored completely. Note that this method does not perform any filtering.
	 * <p>
	 * Note: If a subclass always returns null here, it might be better to subclass {@link FilteringScope}. Actually, it
	 * is recommended to not return null but instead decorate the original description: This helps the user to fix the
	 * problem because he will get a nice error message instead of a simple "cannot resolve element".
	 *
	 * @param originalDescr
	 *            the original description to be wrapped
	 */
	protected abstract IEObjectDescriptionWithError wrapFilteredDescription(IEObjectDescription originalDescr);

	/**
	 * Returns true if given description is to be passed unmodified. That is, if it returns false, the description is
	 * filtered out (and possibly wrapped via {@link #wrapFilteredDescription(IEObjectDescription)}.
	 */
	protected abstract boolean isAccepted(IEObjectDescription originalDescr);

	/**
	 * Returns the first not filtered element found in the parent scope; if no such element is found, an
	 * {@link IEObjectDescriptionWithError} is created for the element originally found in the parent scope by that
	 * name.
	 * <p>
	 * Note that the extended behavior of looking for possibly shadowed elements makes only sense if the parent's
	 * {@link IScope#getElements(QualifiedName)} returns more than one element and if
	 * {@link IScope#getSingleElement(EObject)} is only an optimization in case only one element is present.
	 */
	@Override
	public IEObjectDescription getSingleElement(QualifiedName name) {
		IEObjectDescription result = parent.getSingleElement(name);
		if (result == null || isAccepted(result)) {
			return result;
		}
		Iterable<IEObjectDescription> allElements = parent.getElements(name);
		for (IEObjectDescription description : allElements) {
			if (isAccepted(description)) {
				return description;
			}
		}
		return wrapFilteredDescription(result);
	}

	@Override
	public IEObjectDescription getSingleElement(EObject object) {
		IEObjectDescription result = parent.getSingleElement(object);
		if (result == null || isAccepted(result)) {
			return result;
		}
		return wrapFilteredDescription(result);
	}

	@Override
	public Iterable<IEObjectDescription> getElements(QualifiedName name) {
		return decorateWithErrorIfFiltered(parent.getElements(name));
	}

	@Override
	public Iterable<IEObjectDescription> getElements(EObject object) {
		return decorateWithErrorIfFiltered(parent.getElements(object));
	}

	@Override
	public Iterable<IEObjectDescription> getAllElements() {
		return decorateWithErrorIfFiltered(parent.getAllElements());
	}

	/**
	 * Decorates all descriptions which are filtered with error markers. Since
	 * {@link #wrapFilteredDescription(IEObjectDescription)} may return null, null values are filtered out.
	 *
	 * @param originalDescriptions
	 *            the original unfiltered descriptions.
	 */
	protected Iterable<IEObjectDescription> decorateWithErrorIfFiltered(
			Iterable<IEObjectDescription> originalDescriptions) {
		// warning produced here is a bug, see https://bugs.eclipse.org/bugs/show_bug.cgi?id=445465
		// explicitly declaring type of element fixes the warning
		Iterable<IEObjectDescription> filteredResult =
				Iterables.transform(originalDescriptions, (IEObjectDescription it) -> {
					if (it == null || isAccepted(it)) {
						return it;
					} else {
						return wrapFilteredDescription(it);
					}
				});
		return Iterables.filter(filteredResult, it -> it != null);
	}

	@Override
	public String toString() {
		return StreamSupport.stream(getAllElements().spliterator(), false).map(descr -> descr.toString())
				.collect(Collectors.joining(", "));
	}

}
