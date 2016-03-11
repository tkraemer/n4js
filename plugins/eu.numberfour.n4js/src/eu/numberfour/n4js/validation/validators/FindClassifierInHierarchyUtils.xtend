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
package eu.numberfour.n4js.validation.validators

import eu.numberfour.n4js.n4JS.N4ClassDefinition
import eu.numberfour.n4js.n4JS.N4ClassifierDefinition
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TInterface
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.Type

/**
 * Helper class extracted from N4JSAllMemberValidator
 */
class FindClassifierInHierarchyUtils {


//	def static findSuperTypesWithLowerVisibility(N4ClassifierDefinition classifier) {
//		val criteria = [Iterable<? extends ParameterizedTypeRef> list, Class<? extends TClassifier> clazz|
//			findTypeWithModifierOfLowerVisibility(classifier, list, clazz)]
//		classifier.findSuperTypesWithCriteria(criteria)
//	}

	def static findSuperTypesWithMember(N4ClassifierDefinition classifier, TMember member) {
		val criteria = [Iterable<? extends ParameterizedTypeRef> list, Class<? extends TClassifier> clazz|
			findTypeWithMember(classifier, list, clazz, member)]
		classifier.findSuperTypesWithCriteria(criteria)
	}

	def private static findSuperTypesWithCriteria(N4ClassifierDefinition it,
		(Iterable<? extends ParameterizedTypeRef>, Class<? extends TClassifier>)=>Iterable<ParameterizedTypeRef> criteria) {
		switch (it) {
			N4ClassDefinition: {
				val list = <ParameterizedTypeRef>newArrayList
				list += findSuperClassWithCriteria(criteria)
				list += findConsumedRoleWithCriteria(criteria)
				list
			}
			N4InterfaceDeclaration: {
				val list = <ParameterizedTypeRef>newArrayList
				list += findConsumedRoleWithCriteria(criteria)
				list
			}
		}
	}

	def private static findSuperClassWithCriteria(N4ClassDefinition it,
		(Iterable<? extends ParameterizedTypeRef>, Class<? extends TClassifier>)=>Iterable<ParameterizedTypeRef> criteria) {
		criteria.apply(if (superClassRef === null) #[] else #[superClassRef], TClass)
	}

	def private static findConsumedRoleWithCriteria(N4ClassDefinition it,
		(Iterable<? extends ParameterizedTypeRef>, Class<? extends TClassifier>)=>Iterable<ParameterizedTypeRef> criteria) {
		criteria.apply(implementedInterfaceRefs, TClass)
	}

	def private static findConsumedRoleWithCriteria(N4InterfaceDeclaration it,
		(Iterable<? extends ParameterizedTypeRef>, Class<? extends TClassifier>)=>Iterable<ParameterizedTypeRef> criteria) {
		criteria.apply(superInterfaceRefs, TInterface)
	}

	def private static <T extends TClassifier> Iterable<ParameterizedTypeRef> findTypeWithMember(N4ClassifierDefinition it,
		Iterable<? extends ParameterizedTypeRef> list, Class<T> clazz, TMember tMember) {
		list.map[it -> declaredType].filter[clazz.isAssignableFrom(value.class)].filter(
			Pair<ParameterizedTypeRef, Type> pair|(pair.value as TClassifier).ownedMembers.contains(tMember)
		).map(Pair<ParameterizedTypeRef, Type> pair|pair.key)
	}

}
