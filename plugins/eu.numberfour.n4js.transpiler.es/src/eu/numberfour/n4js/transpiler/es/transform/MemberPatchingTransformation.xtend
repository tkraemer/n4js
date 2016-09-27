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
package eu.numberfour.n4js.transpiler.es.transform

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4ClassifierDeclaration
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration
import eu.numberfour.n4js.transpiler.Transformation
import eu.numberfour.n4js.transpiler.assistants.TypeAssistant
import eu.numberfour.n4js.transpiler.es.assistants.DelegationAssistant
import eu.numberfour.n4js.transpiler.im.DelegatingMember
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod

import static eu.numberfour.n4js.transpiler.TranspilerBuilderBlocks.*

/**
 * Handles some special cases where code has to be emitted for non-owned members, e.g. for members consumed by an
 * interface, <b>EXCEPT</b> for {@link StaticPolyfillTransformation static polyfills} and
 * {@link ApiImplStubGenerationTransformation API/Impl stubs} (those cases are handled in other transformations).
 * <p>
 * This transformation will add new "full" members or {@link DelegatingMember}s to the <code>ownedMembersRaw</code>
 * property of {@link ContainerType}s in the intermediate model.
 */
class MemberPatchingTransformation extends Transformation {
	@Inject private DelegationAssistant delegationAssistant;
	@Inject private TypeAssistant typeAssistant;


	override assertPreConditions() {
		typeAssistant.assertClassifierPreConditions();
	}
	override assertPostConditions() {
		// true
	}

	override analyze() {
		// ignore
	}

	override transform() {
		collectNodes(state.im, N4ClassifierDeclaration, false).forEach[transformClassifierDecl];
	}

	def private dispatch void transformClassifierDecl(N4InterfaceDeclaration ifcDecl) {
		val tIfc = state.info.getOriginalDefinedType(ifcDecl);
		val cmoft = typeAssistant.getOrCreateCMOFT(tIfc);

		// for interfaces we ALWAYS add delegates to ALL inherited members (except fields)
		for(TMember m : cmoft.ownedAndMixedInConcreteMembers) {
			if(!(m instanceof TField)) {
				val isInherited = m.containingType!==tIfc;
				if(isInherited){
					val delegator = delegationAssistant.createDelegatingMember(tIfc, m);
					state.info.markAsConsumedFromInterface(delegator);
					ifcDecl.ownedMembersRaw += delegator;
				}
			} else {
				// note: it is slightly inconsistent that we ignore fields here (also compare with method for classes);
				// but not required, because this is handled by the field initializer function created here:
				// InterfaceDeclarationTransformation#createInstanceFieldInitializationFunction(N4InterfaceDeclaration, SymbolTableEntry)
			}
		}
	}

	def private dispatch void transformClassifierDecl(N4ClassDeclaration classDecl) {
		val tClass = state.info.getOriginalDefinedType(classDecl);
		val cmoft = typeAssistant.getOrCreateCMOFT(tClass);

		// add delegates to methods consumed from an interface
		val consumedMethods = cmoft.ownedAndMixedInConcreteMembers.filter(TMethod).filter[m|m.eContainer!==tClass].toList;
		for(m : consumedMethods) {
			val member = delegationAssistant.createDelegatingMember(tClass, m);
			state.info.markAsConsumedFromInterface(member);
			classDecl.ownedMembersRaw += member;
		}

		// add delegates to getters/setters consumed from an interface
		for(accTuple : cmoft.concreteAccessorTuples) {
			if(accTuple.getter!==null && accTuple.getter.containingType!==tClass && accTuple.inheritedGetter===null) {
				val g = accTuple.getter;
				val member = delegationAssistant.createDelegatingMember(tClass, g);
				state.info.markAsConsumedFromInterface(member);
				classDecl.ownedMembersRaw += member;
			}
			if(accTuple.setter!==null && accTuple.setter.containingType!==tClass && accTuple.inheritedSetter===null) {
				val s = accTuple.setter;
				val member = delegationAssistant.createDelegatingMember(tClass, s);
				state.info.markAsConsumedFromInterface(member);
				classDecl.ownedMembersRaw += member;
			}
		}

		// add fields consumed from an interface
		for(field : cmoft.fieldsPurelyMixedInNotOverridingAccessor) {
			val member = _N4MemberDecl(field);
			classDecl.ownedMembersRaw += member;
			state.info.setOriginalDefinedMember(member, field);
			state.info.markAsConsumedFromInterface(member);
		}
	}
}
