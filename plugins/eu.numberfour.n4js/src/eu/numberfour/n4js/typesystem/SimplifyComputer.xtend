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
package eu.numberfour.n4js.typesystem

import com.google.inject.Inject
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.utils.TypeCompareHelper
import eu.numberfour.n4js.ts.utils.TypeUtils
import it.xsemantics.runtime.RuleEnvironment
import java.util.Set
import java.util.TreeSet
import org.eclipse.emf.ecore.EClass
import org.eclipse.emf.ecore.util.EcoreUtil

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import static extension java.util.Collections.*

/**
 * Type System Helper Strategy for creating simplified composed types, i.e. union
 * and intersection types.
 */
class SimplifyComputer extends TypeSystemHelperStrategy {

	@Inject
	extension TypeCompareHelper;

	/**
	 * Creates a simplified union type containing the given types; since it is simplified, the result is not necessarily a union type.
	 * The result may be contained in another container, so clients may have to use Ecore2.cloneIfNecessary(EObject).
	 */
	def TypeRef createUnionType(RuleEnvironment G, TypeRef... elements) {
		simplify(G, TypeUtils.createNonSimplifiedUnionType(elements));
	}

	/**
	 * Creates a simplified intersection type containing the given types; since it is simplified, the result is not necessarily an intersection type.
	 * The result may be contained in another container, so clients may have to use Ecore2.cloneIfNecessary(EObject).
	 */
	def TypeRef createIntersectionType(RuleEnvironment G, TypeRef... elements) {
		simplify(G, TypeUtils.createNonSimplifiedIntersectionType(elements));
	}

	/**
	 * Returns a simplified copy of a given composed type, i.e. union or intersection type.
	 * The returned type may be one of the elements, without cloning it.
	 * So clients need to clone the result if necessary.
	 * @see [N4JS Spec], 4.13 Intersection Type
	 */
	def <T extends ComposedTypeRef> TypeRef simplify(RuleEnvironment G, T composedType) {
		if (composedType === null) {
			return null;
		}
		val eClass = composedType.eClass

		// unique elements
		val Set<TypeRef> set = new TreeSet<TypeRef>(getTypeRefComparator);

		// flatten
		if (composedType.typeRefs.exists [
			eClass.isInstance(it)
		]) {
			set.addAll(flattenComposedTypes(eClass, composedType));
		} else {
			set.addAll(composedType.typeRefs);
		}

		// simplify singleton
		if (set.size() == 1) {
			return set.head
		}

		val simplified = EcoreUtil.create(eClass) as ComposedTypeRef

		val typeRefs = simplified.typeRefs

		val undefinedTypeRef = G.undefinedTypeRef
		val nullTypeRef = G.nullTypeRef
		for (e : set) {
			if (compare(e, undefinedTypeRef) != 0 &&
				compare(e, nullTypeRef) != 0) {
				typeRefs.add(TypeUtils.copyIfContained(e));
			}
		}

		switch (typeRefs.size()) {
			case 0:
				return undefinedTypeRef
			case 1:
				return typeRefs.head
			default: {
				simplified.setNullModifier(composedType.nullModifier)
				simplified.setUndefModifier(composedType.undefModifier)
				return simplified;
			}
		}

	}

	private def Iterable<TypeRef> flattenComposedTypes(EClass eClass, TypeRef typeRef) {
		if (eClass.isInstance(typeRef)) {
			(typeRef as ComposedTypeRef).typeRefs.map[flattenComposedTypes(eClass, it)].flatten
		} else {
			typeRef.singleton()
		};
	}
}
