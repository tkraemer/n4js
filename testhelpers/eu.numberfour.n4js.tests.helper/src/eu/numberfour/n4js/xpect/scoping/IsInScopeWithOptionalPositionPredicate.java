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
package eu.numberfour.n4js.xpect.scoping;

import static eu.numberfour.n4js.xpect.scoping.EObjectDescriptionToNameWithPositionMapper.descriptionToNameWithPosition;
import static eu.numberfour.n4js.xpect.scoping.EObjectDescriptionToNameWithPositionMapper.getNameFromNameWithPosition;
import static eu.numberfour.n4js.xpect.scoping.EObjectDescriptionToNameWithPositionMapper.getPositionFromNameWithPosition;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.xtext.naming.IQualifiedNameConverter;
import org.eclipse.xtext.naming.QualifiedName;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScope;
import org.eclipse.xtext.util.Pair;
import org.eclipse.xtext.util.Tuples;

import com.google.common.base.Predicate;
import com.google.common.base.Strings;

/**
 * Internally used by {@link ScopeXpectMethod}, replaces internal {@code ScopingTest.IsInScope}
 * class.
 */
class IsInScopeWithOptionalPositionPredicate implements Predicate<String> {
	private final IQualifiedNameConverter converter;
	private final IScope scope;
	private List<Pair<String, String>> allElementsInScopeWithFQNtoNameWithPos = null;
	private final URI currentURI;
	private final boolean withLineNumber;

	public IsInScopeWithOptionalPositionPredicate(IQualifiedNameConverter converter, URI currentURI,
			boolean withLineNumber, IScope scope) {
		super();
		this.converter = converter;
		this.scope = scope;
		this.currentURI = currentURI;
		this.withLineNumber = withLineNumber;

		allElementsInScopeWithFQNtoNameWithPos = null;
	}

	@Override
	public boolean apply(String nameWithPosition) {
		if (allElementsInScopeWithFQNtoNameWithPos == null) {
			allElementsInScopeWithFQNtoNameWithPos = new ArrayList<>();
			for (IEObjectDescription elem : scope.getAllElements()) {
				allElementsInScopeWithFQNtoNameWithPos.add(Tuples.create(elem.getQualifiedName().toString(),
						descriptionToNameWithPosition(currentURI, withLineNumber, elem)));
			}
		}
		String name = getNameFromNameWithPosition(nameWithPosition);
		String position = getPositionFromNameWithPosition(nameWithPosition);
		QualifiedName qualifiedName = converter.toQualifiedName(name);
		String fqn = qualifiedName.toString();
		for (Pair<String, String> entry : allElementsInScopeWithFQNtoNameWithPos) {
			String fqnInScope = entry.getFirst();
			if (fqnInScope.equals(fqn)) {
				if (!Strings.isNullOrEmpty(position)) {
					String positionOfScopeElement = getPositionFromNameWithPosition(entry.getSecond());
					if (position.equals(positionOfScopeElement)) {
						return true;
					}
				} else {
					return true;
				}
			}
		}
		return false;
	}
}
