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
package eu.numberfour.n4js.validation.validators;

import static eu.numberfour.n4js.AnnotationDefinition.FINAL;
import static eu.numberfour.n4js.AnnotationDefinition.GLOBAL;
import static eu.numberfour.n4js.n4JS.N4JSPackage.Literals.N4_TYPE_DECLARATION__NAME;
import static eu.numberfour.n4js.utils.N4JSLanguageUtils.isContainedInStaticPolyfillAware;
import static eu.numberfour.n4js.utils.N4JSLanguageUtils.isContainedInStaticPolyfillModule;
import static eu.numberfour.n4js.utils.N4JSLanguageUtils.isPolyfill;
import static eu.numberfour.n4js.utils.N4JSLanguageUtils.isStaticPolyfill;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_DIFFERENT_GLOBALS;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_DIFFERENT_MODIFIER;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_DIFFERENT_MODULE_SPECIFIER;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_DIFFERENT_NAME;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_DIFFERENT_TYPEPARS;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_EXTEND_MISSING;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_FILLED_NOT_PROVIDEDBYRUNTIME;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_NOT_PROVIDEDBYRUNTIME;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_NO_IMPLEMENTS_OR_CONSUMES;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_STATIC_FILLED_TYPE_NOT_AWARE;
import static eu.numberfour.n4js.validation.IssueCodes.CLF_POLYFILL_TYPEPARS_DIFFER_TYPEARGS;
import static eu.numberfour.n4js.validation.IssueCodes.POLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_DIFFERENT_GLOBALS;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_DIFFERENT_MODIFIER;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_DIFFERENT_MODULE_SPECIFIER;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_DIFFERENT_NAME;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_DIFFERENT_TYPEPARS;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_EXTEND_MISSING;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_FILLED_NOT_PROVIDEDBYRUNTIME;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_NOT_PROVIDEDBYRUNTIME;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_NO_IMPLEMENTS_OR_CONSUMES;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_STATIC_FILLED_TYPE_NOT_AWARE;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForCLF_POLYFILL_TYPEPARS_DIFFER_TYPEARGS;
import static eu.numberfour.n4js.validation.IssueCodes.getMessageForPOLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.naming.IQualifiedNameProvider;
import org.eclipse.xtext.resource.IContainer;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.resource.IResourceDescriptions;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.impl.ResourceDescriptionsProvider;

import com.google.common.base.Joiner;
import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.ListMultimap;
import com.google.inject.Inject;

import eu.numberfour.n4js.n4JS.N4ClassDeclaration;
import eu.numberfour.n4js.n4JS.N4JSPackage;
import eu.numberfour.n4js.ts.scoping.N4TSQualifiedNameProvider;
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef;
import eu.numberfour.n4js.ts.typeRefs.TypeArgument;
import eu.numberfour.n4js.ts.types.TClass;
import eu.numberfour.n4js.ts.types.TClassifier;
import eu.numberfour.n4js.ts.types.TMember;
import eu.numberfour.n4js.ts.types.TModule;
import eu.numberfour.n4js.ts.types.Type;
import eu.numberfour.n4js.ts.types.TypeVariable;
import eu.numberfour.n4js.ts.types.TypesPackage;
import eu.numberfour.n4js.validation.IssueCodes;
import eu.numberfour.n4js.validation.N4JSElementKeywordProvider;

/**
 * Validates polyfill declaration, used by {@link N4JSClassValidator}. Some modifications related to polyfills and
 * member redefinition are found in {@link N4JSMemberRedefinitionValidator}. Also the {@link N4JSDeclaredNameValidator}
 * must be aware of polyfills in order to enable polyfills having same name as imported type.
 */
public class PolyfillValidatorFragment {

	private static final String PREFIX_LIST = ", ";
	@Inject
	N4JSElementKeywordProvider keywordProvider;

	@Inject
	ResourceDescriptionsProvider resourceDescriptionsProvider;

	@Inject
	IContainer.Manager containerManager;

	@Inject
	IQualifiedNameProvider qualifiedNameProvider;

	/**
	 * Value object used internally to avoid passing around all these objects separately.
	 */
	private static class PolyfillValidationState {
		N4JSClassValidator host;
		N4ClassDeclaration n4Class;
		TClass polyType;
		TClassifier filledType;
		String name;
	}

	private void addIssue(PolyfillValidationState state, String msg, String issueCode) {
		state.host.addIssue(msg, state.n4Class, N4_TYPE_DECLARATION__NAME, issueCode);
	}

	/**
	 * Checks polyfill constraints on given class declaration using validator to issue errors. Constraints (Polyfill
	 * Class) 156: Polyfill
	 */
	public boolean holdsPolyfill(N4JSClassValidator validator, N4ClassDeclaration n4Class) {
		boolean isStaticPolyFill = isStaticPolyfill(n4Class);
		if (isStaticPolyFill || isPolyfill(n4Class)) {
			PolyfillValidationState state = new PolyfillValidationState();
			state.host = validator;
			state.n4Class = n4Class;
			state.name = n4Class.getName();

			if (state.name == null || !(n4Class.getDefinedType() instanceof TClass)) {
				return true; // consequential error, AST corrupt
			}
			state.polyType = (TClass) n4Class.getDefinedType();
			if (state.polyType == null || state.name == null) {
				return true; // consequential error
			}

			if (!holdsExpliciteExtends(state)) {
				return false;
			}

			final Type superType = n4Class.getSuperClassRef().getDeclaredType();
			if (!(superType instanceof TClassifier)) { // TClass or TObjectPrototype
				return true; // consequential error
			}
			state.filledType = (TClassifier) superType;

			// Different rules for static/non-static polyfills:
			if (!isStaticPolyFill) {
				if (!(holdPolyfillName(state) //
						&& holdsProvidedByRuntime(state) //
						&& holdsNoImplementsOrConsumes(state) //
						&& holdsEqualModifiers(state) //
						&& holdsEqualTypeVariables(state) //
						&& holdsSinglePolyfillSource(state))) //
				{
					return false;
				}
			} else {
				// static polyfill case, IDE-1735
				if (!(holdPolyfillName(state) //
						// && holdsProvidedByRuntime(state) //
						// && holdsNoImplementsOrConsumes(state) //
						&& holdsFilledClassIsStaticPolyfillAware(state)
						&& holdsEqualModifiers(state) //
						&& holdsEqualTypeVariables(state) //
						&& holdsSinglePolyfillSource(state))) //
				{
					return false;
				}
			}

		}

		// § 140.1 only polyfills are allowed in StaticPolyfillModule.
		if (!isStaticPolyFill && isContainedInStaticPolyfillModule(n4Class)) {
			// n4Class is toplevel by default
			validator.addIssue(getMessageForPOLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES(), n4Class,
					N4_TYPE_DECLARATION__NAME, POLY_STATIC_POLYFILL_MODULE_ONLY_FILLING_CLASSES);
			return false;
		}

		return true;
	}

	/**
	 * Constraint 139.5
	 */
	private boolean holdsFilledClassIsStaticPolyfillAware(PolyfillValidationState state) {

		if (!(isContainedInStaticPolyfillAware(state.filledType))) { // (Static Polyfill) 139.5
			final String msg = getMessageForCLF_POLYFILL_STATIC_FILLED_TYPE_NOT_AWARE(state.name);
			addIssue(state, msg, CLF_POLYFILL_STATIC_FILLED_TYPE_NOT_AWARE);
			return false;
		}

		return true;
	}

	/**
	 * Constraint (Polyfill Class) 156.1
	 */
	private boolean holdsExpliciteExtends(PolyfillValidationState state) {
		final ParameterizedTypeRef filledTypeRef = state.n4Class.getSuperClassRef();
		if (filledTypeRef == null) { // (Polyfill Class) 156.1
			final String msg = getMessageForCLF_POLYFILL_EXTEND_MISSING(state.name);
			addIssue(state, msg, CLF_POLYFILL_EXTEND_MISSING);
			return false;
		}
		return true;
	}

	/**
	 * Constraints (Polyfill Class) 156.2 polyfill name and module
	 */
	private boolean holdPolyfillName(PolyfillValidationState state) {
		if (!state.name.equals(state.filledType.getName())) { // (Polyfill Class) 156.2
			final String msg = getMessageForCLF_POLYFILL_DIFFERENT_NAME(state.name,
					state.filledType.getName());
			addIssue(state, msg, CLF_POLYFILL_DIFFERENT_NAME);
			return false;
		}
		final boolean isGlobalFilled = GLOBAL.hasAnnotation(state.filledType);
		final boolean isGlobalPoly = GLOBAL.hasAnnotation(state.polyType);
		if (isGlobalFilled != isGlobalPoly) { // (Polyfill Class) 156.2
			final String msg = getMessageForCLF_POLYFILL_DIFFERENT_GLOBALS(
					state.name, isGlobalPoly ? "global" : "not global", isGlobalFilled ? "global" : "not global");
			addIssue(state, msg, CLF_POLYFILL_DIFFERENT_GLOBALS);
			return false;
		}
		if (!isGlobalFilled) {
			final TModule polyModule = state.polyType.getContainingModule();
			final TModule filledModule = state.filledType.getContainingModule();
			if (polyModule != null && filledModule != null) { // avoid consequential errors
				if (!polyModule.getModuleSpecifier().equals(filledModule.getModuleSpecifier())) { // (Polyfill Class)
																									// 156.2
					final String msg = getMessageForCLF_POLYFILL_DIFFERENT_MODULE_SPECIFIER(state.name,
							polyModule.getModuleSpecifier(),
							filledModule.getModuleSpecifier());
					addIssue(state, msg, CLF_POLYFILL_DIFFERENT_MODULE_SPECIFIER);
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Constraint (Polyfill Class) 156.3
	 */
	private boolean holdsProvidedByRuntime(PolyfillValidationState state) {
		if (!state.polyType.isProvidedByRuntime()) {
			final String msg = getMessageForCLF_POLYFILL_NOT_PROVIDEDBYRUNTIME(state.name);
			addIssue(state, msg, CLF_POLYFILL_NOT_PROVIDEDBYRUNTIME);
			return false;
		}
		if (!state.filledType.isProvidedByRuntime()) {
			final String msg = getMessageForCLF_POLYFILL_FILLED_NOT_PROVIDEDBYRUNTIME(state.name);
			addIssue(state, msg, CLF_POLYFILL_FILLED_NOT_PROVIDEDBYRUNTIME);
			return false;
		}

		return true;
	}

	/**
	 * Constraint (Polyfill Class) 156.4
	 */
	private boolean holdsNoImplementsOrConsumes(PolyfillValidationState state) {
		if (!state.n4Class.getImplementedInterfaceRefs().isEmpty()) {
			final String msg = getMessageForCLF_POLYFILL_NO_IMPLEMENTS_OR_CONSUMES(state.name);
			addIssue(state, msg, CLF_POLYFILL_NO_IMPLEMENTS_OR_CONSUMES);
			return false;
		}
		return true;
	}

	/**
	 * Constraint (Polyfill Class) 156.5
	 */
	private boolean holdsEqualModifiers(PolyfillValidationState state) {
		boolean result = true;
		if (state.polyType.getTypeAccessModifier() != state.filledType.getTypeAccessModifier()) {
			final String msg = getMessageForCLF_POLYFILL_DIFFERENT_MODIFIER(state.name,
					keywordProvider.keyword(state.polyType.getTypeAccessModifier()),
					keywordProvider.keyword(state.filledType.getTypeAccessModifier()));
			addIssue(state, msg, CLF_POLYFILL_DIFFERENT_MODIFIER);
			result = false;
		}
		result &= holdsEqualModifier(state, "abstract", state.polyType.isAbstract(), state.filledType.isAbstract());
		result &= holdsEqualModifier(state, "@" + FINAL.name, state.polyType.isFinal(), state.filledType.isFinal());
		return result;
	}

	private boolean holdsEqualModifier(PolyfillValidationState state, String modifierName, boolean poly,
			boolean filled) {
		if (poly != filled) {
			final String msg = getMessageForCLF_POLYFILL_DIFFERENT_MODIFIER(state.name,
					(poly ? "" : "non-") + modifierName, (filled ? "" : "non-") + modifierName);
			addIssue(state, msg, CLF_POLYFILL_DIFFERENT_MODIFIER);
			return false;
		}
		return true;
	}

	/**
	 * Constraint (Polyfill Class) 156.7
	 */
	private boolean holdsEqualTypeVariables(PolyfillValidationState state) {
		// simple string compare instead of type compare, we need identity anyway
		final String typeVars1 = Joiner.on(',').join(
				state.polyType.getTypeVars().stream().map(v -> v.getTypeAsString()).toArray());
		final String typeVars2 = Joiner.on(',').join(
				state.filledType.getTypeVars().stream().map(v -> v.getTypeAsString()).toArray());
		if (!typeVars1.equals(typeVars2)) {
			final String msg = getMessageForCLF_POLYFILL_DIFFERENT_TYPEPARS(state.name);
			addIssue(state, msg, CLF_POLYFILL_DIFFERENT_TYPEPARS);
			return false;
		}

		EList<TypeArgument> args = state.n4Class.getSuperClassRef().getTypeArgs();
		if (args.size() != state.polyType.getTypeVars().size()) {
			return true; // consequential error
		}
		for (int i = state.polyType.getTypeVars().size() - 1; i >= 0; i--) {
			TypeArgument arg = args.get(i);
			TypeVariable par = state.polyType.getTypeVars().get(i);
			String argString = arg.getTypeRefAsString();
			String parString = par.getName();
			if (!argString.equals(parString)) {
				final String msg = getMessageForCLF_POLYFILL_TYPEPARS_DIFFER_TYPEARGS(state.name, parString, argString);
				addIssue(state, msg, CLF_POLYFILL_TYPEPARS_DIFFER_TYPEARGS);
				return false;
			}

		}

		return true;
	}

	/**
	 * Constraints 129 (Applying Polyfills) No member must be filled by more than one polyfill.
	 */
	private boolean holdsSinglePolyfillSource(PolyfillValidationState state) {

		EList<TMember> myPolyMember = state.polyType.getOwnedMembers();

		// a) find references to the filled type
		// b) check, that they are in the same Project
		// c) search for clashing contributions.

		XtextResource res = (XtextResource) state.polyType.eResource();
		IResourceDescriptions index = resourceDescriptionsProvider.getResourceDescriptions(res);
		// a+b) all polyfills to same calssifier in same project:
		IContainer container = containerManager.getContainer(res.getResourceServiceProvider()
				.getResourceDescriptionManager()
				.getResourceDescription(res), index);
		// Iterable over all exported Polyfills
		Iterable<IEObjectDescription> iterEObj = container.getExportedObjects(TypesPackage.Literals.TCLASSIFIER,
				N4TSQualifiedNameProvider.getPolyfillFQN(state.filledType, qualifiedNameProvider), false);

		// collection of involved TModules for each Member.
		ListMultimap<TMember, TModule> clashProviders = LinkedListMultimap.create();

		for (IEObjectDescription pivotObjectDescription : iterEObj) {

			EObject eob = pivotObjectDescription.getEObjectOrProxy();
			// Resolve
			if (eob.eIsProxy()) {
				eob = EcoreUtil.resolve(eob, res);
			}

			if (eob == state.polyType) {
				// saw myself .-.
				continue;
			}

			EList<TMember> pivotPolyMember = ((TClassifier) eob).getOwnedMembers();

			ListMultimap<TMember, TMember> clashing = findClashingMembersByName(myPolyMember, pivotPolyMember);

			for (TMember myMember : clashing.keySet()) {
				// only interested in the module, so first is sufficient
				clashProviders.put(myMember, clashing.get(myMember).get(0).getContainingModule());
			}

		}

		List<TMember> sortedMembers = clashProviders.keySet().stream().sorted().collect(Collectors.toList());
		for (TMember myMember : sortedMembers) {
			// Combine list of Modules involved in the polyfill clash.
			String uris = Stream
					.concat(Stream.of(myMember.getContainingModule()), clashProviders.get(myMember).stream())
					.map(u -> u.getQualifiedName().toString()).sorted()
					.reduce("", (a, b) -> a + PREFIX_LIST + b);
			if (uris.startsWith(PREFIX_LIST))
				uris = uris.substring(PREFIX_LIST.length());
			int lastPrefix_idx = uris.lastIndexOf(PREFIX_LIST);
			if (lastPrefix_idx >= 0) {
				StringBuffer sb = new StringBuffer(uris);
				uris = sb.replace(lastPrefix_idx, lastPrefix_idx + PREFIX_LIST.length(), " and ").toString();
			}

			// give Qualified name filled in Property.
			String memberAxis = myMember.getContainingType().getName() + "." + myMember.getName();

			// Issue on filled Member-name declaration:
			String msg = IssueCodes.getMessageForCLF_POLYFILL_MULTIPOLYFILLS_MEMBER_CONFLICT(uris, memberAxis);
			state.host.addIssue(msg, myMember.getAstElement(), N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME,
					IssueCodes.CLF_POLYFILL_MULTIPOLYFILLS_MEMBER_CONFLICT);
		}

		return true;
	}

	/**
	 * Find clashes by name.
	 *
	 * @param myPolyMember
	 *            current validated Polyfill
	 * @param pivotPolyMember
	 *            other polyfill in same project
	 * @return pairs of contrandicting or same polyfills.
	 */
	private ListMultimap<TMember, TMember> findClashingMembersByName(EList<TMember> myPolyMember,
			EList<TMember> pivotPolyMember) {
		ListMultimap<TMember, TMember> ret = LinkedListMultimap.create();
		for (TMember my : myPolyMember) {
			String myName = my.getName();
			if (myName == null)
				continue; // broken AST
			for (TMember other : pivotPolyMember) {
				String otherName = other.getName();
				if (myName.equals(otherName)) {
					ret.put(my, other);
				}
			}
		}
		return ret;
	}

}
