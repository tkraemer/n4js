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

import com.google.common.annotations.VisibleForTesting
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.ts.utils.TypeUtils
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.xtext.xbase.lib.Functions.Function1

import static extension java.util.Collections.*
import com.google.inject.Inject
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem

/**
 * Type System Helper Strategy computing the meet of a given collection of types.
 * <p>
 * Definition from [Pierce02a, pp. 218]:
 * <pre>
 * Meet M = S ^ T, if
 *     M <: S, M <: T,                    common sub type
 *     forall L: L<:T and L<:S => L<:M    least
 * </pre>
 */
class MeetComputer extends TypeSystemHelperStrategy {

	@Inject
	private N4JSTypeSystem ts

	/**
  	 * Returns the meet (first common sub type) of the given types, maybe an intersection type, if types have no declared meet.
  	 * This may be an intersection type, but not a union type. This method considers generics, but it does not
  	 * compute lower or upper bounds of them.
  	 * See class description for details.
	 * @return The meet which might be an intersection type.
	 * This method only returns null if all input type refs are null.
	 * Note that the returned type ref is a newly created type ref which may be inserted into any container.
	 */
	def meet(RuleEnvironment G, Iterable<? extends TypeRef> typeRefs) {
		if (typeRefs.nullOrEmpty) {
			return null;
		}
		val flatTypeRefs = typeRefs.map[it.flattenTypeRefs].flatten

		// we have to copy the reference, as it might be inserted into an intersection type
		var TypeRef meet = null;
		for (tr : flatTypeRefs) {
			if (meet === null) {
				meet = TypeUtils.copyIfContained(tr);
			} else {
				if (ts.subtypeSucceeded(G, tr, meet)) {
					meet = TypeUtils.copyIfContained(tr);
				} else if (! ts.subtypeSucceeded(G, meet, tr)) { //?
					meet = intersectRelaxed(G, meet, tr)
				} // else meet is a subtype of tr and nothing is left to do
			}
		}
		return meet;
	}

	private def dispatch Iterable<? extends TypeRef> flattenTypeRefs(TypeRef ref) {
		return singleton(ref);
	}

	private def dispatch Iterable<? extends TypeRef> flattenTypeRefs(ComposedTypeRef ref) {
		ref.typeRefs;
	}

	/**
	 * Creates the intersection according to [N4JS, 4.13 Intersection Type], but does not check for uniqueness
	 * of class in the typerefs of the intersection.
	 * The given type ref instances are only copied when added to the intersection
	 * if they have a container, otherwise the method assumes that the caller has newly created the typerefs which can be added directly.
	 * If one of the given type refs is any, then this any ref is returned; if the intersection contains only a single type, then this single type ios returned.
	 */
	// TODO see IDE-142/IDE-385
	@VisibleForTesting
	def TypeRef intersectRelaxed(RuleEnvironment G, TypeRef... typeRefs) {
		val flattenedTypeRefs = typeRefs.map[flattenIntersectionTypes].flatten;
		val containedAny = flattenedTypeRefs.findFirst[it !== null && topType];
		if (containedAny !== null) {
			return containedAny
		}
		val intersection = TypeRefsFactory.eINSTANCE.createIntersectionTypeExpression();
		for (s : flattenedTypeRefs) {
			if (! intersection.typeRefs.exists[ts.subtypeSucceeded(G, it, s)]) {

				// see https://code.google.com/p/guava-libraries/issues/detail?id=1596
				// Iterables.removeIf(intersection.typeRefs, [ts.subtypeTypeRefSucceeded(G, s, it)]);
				retainIf(intersection.typeRefs, [! ts.subtypeSucceeded(G, s, it)]);
				intersection.typeRefs.add(TypeUtils.copyIfContained((s)));
			}
		}
		if (intersection.typeRefs.size() == 1) {
			return intersection.typeRefs.remove(0);
		}
		return intersection
	}

	private def Iterable<TypeRef> flattenIntersectionTypes(TypeRef typeRef) {
		switch typeRef {
			IntersectionTypeExpression: typeRef.typeRefs.map[flattenIntersectionTypes(it)].flatten
			default: typeRef.singleton()
		};
	}


	private def static <T> void retainIf(Iterable<T> iterable, Function1<? super T, Boolean> predicate) {
		val iter = iterable.iterator;
		while (iter.hasNext()) {
			val it = iter.next;
			if (! predicate.apply(it)) {
				iter.remove()
			}
		}
	}
}
