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
package eu.numberfour.n4js.scoping.utils;

import java.util.Arrays;
import java.util.stream.Collectors;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;

import com.google.common.collect.Iterables;

/**
 * A scope that is composed of other scopes.
 * <p>
 * IMPORTANT:<br>
 * Usually, scopes should *not* be composed with this class, instead nesting should be used (one scope being a parent of
 * the other). This class is intended only for some tweaks related to content assist.
 */
public class CompositeScope implements IScope {

	/**
	 * The child scopes that together constitute this scope.
	 */
	protected final IScope[] childScopes;

	/**
	 * @see #create(IScope...)
	 */
	protected CompositeScope(IScope... scopes) {
		childScopes = scopes;
	}

	/**
	 * Creates a new {@link CompositeScope}.
	 */
	public static final CompositeScope create(IScope... scopes) {
		return new CompositeScope(scopes);
	}

	@Override
	public IEObjectDescription getSingleElement(QualifiedName name) {
		for (IScope currScope : childScopes) {
			final IEObjectDescription currResult = currScope.getSingleElement(name);
			if (currResult != null)
				return currResult;
		}
		return null;
	}

	@Override
	public IEObjectDescription getSingleElement(EObject object) {
		for (IScope currScope : childScopes) {
			final IEObjectDescription currResult = currScope.getSingleElement(object);
			if (currResult != null)
				return currResult;
		}
		return null;
	}

	@Override
	public Iterable<IEObjectDescription> getElements(QualifiedName name) {
		return Iterables.concat(Arrays.stream(childScopes).map(currScope -> currScope.getElements(name))
				.collect(Collectors.toList()));
	}

	@Override
	public Iterable<IEObjectDescription> getElements(EObject object) {
		return Iterables.concat(Arrays.stream(childScopes).map(currScope -> currScope.getElements(object))
				.collect(Collectors.toList()));
	}

	@Override
	public Iterable<IEObjectDescription> getAllElements() {
		return Iterables.concat(Arrays.stream(childScopes).map(currScope -> currScope.getAllElements())
				.collect(Collectors.toList()));
	}
}
