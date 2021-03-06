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

import com.google.inject.Inject
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4ClassDefinition
import eu.numberfour.n4js.n4JS.N4ClassifierDefinition
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4MemberAnnotationList
import eu.numberfour.n4js.n4JS.N4MemberDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.N4SetterDeclaration
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.utils.ContainerTypesHelper
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4js.validation.IssueCodes
import eu.numberfour.n4js.validation.JavaScriptVariant
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRefStructural
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.types.FieldAccessor
import eu.numberfour.n4js.ts.types.IdentifiableElement
import eu.numberfour.n4js.ts.types.MemberAccessModifier
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TInterface
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.VoidType
import java.util.List
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.util.Tuples
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static eu.numberfour.n4js.AnnotationDefinition.*
import static eu.numberfour.n4js.n4JS.N4JSPackage.Literals.*
import static eu.numberfour.n4js.validation.IssueCodes.*
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef

/**
 * Validation of rules that apply to individual members of a classifier.<p>
 *
 * Validation of rules about members:
 * <ul>
 * <li>if the rules require to take into account the other owned or inherited members of the
 *     containing classifier, then the validation is contained in {@link N4JSClassifierValidator},
 * <li>if they can be checked by only looking at each member individually, then the validation
 *     is contained here.
 * </ul>
 */
class N4JSMemberValidator extends AbstractN4JSDeclarativeValidator {

	@Inject ContainerTypesHelper containerTypesHelper;

	/**
	 * NEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	override register(EValidatorRegistrar registrar) {
		// nop
	}

	@Check
	def void checkN4MemberDeclaration(N4MemberDeclaration n4Member) {
		if (n4Member instanceof N4MemberAnnotationList) {

			// parent already checked
			return
		}
		val it = n4Member.definedTypeElement
		if (it === null) {
			return
		}
		internalCheckNameStartsWithDollar
		internalCheckAbstractAndFinal
		internalCheckPrivateOrProjectWithInternalAnnotation(n4Member, it)
	}

	@Check
	def void checkN4FieldDeclaration(N4FieldDeclaration n4Field) {
		if (n4Field instanceof N4MemberAnnotationList) {

			// parent already checked
			return
		}
		val TMember member = n4Field.definedTypeElement
		if (member === null) {
			return
		}

		holdsMinimalMemberAccessModifier(member);
		holdsDeclaredTypeRefDoesNotReferToNull(n4Field);
	}

	@Check
	def void checkN4MethodDeclaration(N4MethodDeclaration n4Method) {
		if (n4Method instanceof N4MemberAnnotationList) {

			// parent already checked
			return
		}

		holdsCallableConstructorConstraints(n4Method)

		// wrong parsed
		if (n4Method.definedTypeElement === null) {
			return
		}

		val tmethod = n4Method.definedTypeElement as TMethod

		holdsAbstractAndBodyPropertiesOfMethod(tmethod)
		holdsConstructorConstraints(tmethod)
	}

	@Check
	def void checkN4GetterDeclaration(N4GetterDeclaration n4Getter) {
		if (n4Getter instanceof N4MemberAnnotationList) {

			// parent already checked
			return
		}

		// wrong parsed
		if (n4Getter.definedGetter === null) {
			return
		}
		val it = n4Getter.definedGetter

		holdsAbstractAndBodyPropertiesOfMethod(it)

		// same for getters, setters:
		internalCheckAccessorType
	}

	@Check
	def void checkN4SetterDeclaration(N4SetterDeclaration n4Setter) {
		if (n4Setter instanceof N4MemberAnnotationList) {

			// parent already checked
			return
		}
		if (n4Setter.definedSetter === null) {
			return
		}
		val it = n4Setter.definedSetter

		holdsAbstractAndBodyPropertiesOfMethod(it)

		// same for methods, getters, setters:
		// same for getters, setters:
		internalCheckAccessorType
	}

	def boolean holdsAbstractAndBodyPropertiesOfMethod(TMember accessorOrMethod) {
		return //
		holdsAbstractOrHasBody(accessorOrMethod)//
		&& holdsAbstractMethodMustHaveNoBody(accessorOrMethod) //
		&& holdsAbstractMethodMustNotBeStatic(accessorOrMethod) //
		&& holdsAbstractMemberContainedInAbstractClassifier(accessorOrMethod)  //
		&& holdsMinimalMemberAccessModifier(accessorOrMethod) //
	}

	def private internalCheckNameStartsWithDollar(TMember member) {

		// don't validate this in external (i.e. n4jd) files
		if (JavaScriptVariant.getVariant(member) != JavaScriptVariant.n4js) {
			return
		}

		val name = member?.name;

		// name may be null (invalid file), we do not need an NPE here
		if (name !== null && name.startsWith('$')) {
			val message = IssueCodes.getMessageForCLF_NAME_DOLLAR()
			addIssue(message, member.astElement, PROPERTY_NAME_OWNER__NAME, CLF_NAME_DOLLAR)
		}
	}

	def private internalCheckAbstractAndFinal(TMember member) {
		if (member.final) {
			if (member.abstract) {
				val message = IssueCodes.getMessageForCLF_ABSTRACT_FINAL(member.keyword)
				addIssue(message, member.astElement, PROPERTY_NAME_OWNER__NAME, IssueCodes.CLF_ABSTRACT_FINAL)
			} else if (member.containingType instanceof TInterface && !(member instanceof TMethod)) {
				val message = IssueCodes.getMessageForCLF_NO_FINAL_INTERFACE_MEMBER()
				addIssue(message, member.astElement, PROPERTY_NAME_OWNER__NAME,
					IssueCodes.CLF_NO_FINAL_INTERFACE_MEMBER)
			}
		}
	}

	def private boolean holdsConstructorConstraints(TMethod method) {
		if (method.constructor) {
			if (!holdsConstructorNotInInterface(method)) {
				return false;
			}
			if (!holdsConstructorNoReturnType(method)) {
				return false;
			}
			var result = holdsConstructorModifiers(method);
			return holdsRequiredExplicitSuperCallIsFound(method) && result;
		}
		return true;
	}

	/**
	 * N4JS spec constraints 51.1
	 */
	private def holdsConstructorModifiers(TMethod constructor) {
		if (constructor.abstract || constructor.static || constructor.final ||
			constructor.hasIllegalOverride ) {
			val message = getMessageForCLF_CTOR_ILLEGAL_MODIFIER
			addIssue(message, constructor.astElement, PROPERTY_NAME_OWNER__NAME, CLF_CTOR_ILLEGAL_MODIFIER)
			return false;
		}
		return true;
	}

	/** @return true if not a static polyfill but has a {@code @Override} annotation. */
	private def boolean getHasIllegalOverride(TMethod constructor) {
		(! constructor.containingType.isStaticPolyfill) &&
		AnnotationDefinition.OVERRIDE.hasAnnotation(constructor.astElement as N4MethodDeclaration)
	}

	/**
	 * N4JS spec constraints 51.4
	 */
	private def boolean holdsConstructorNotInInterface(TMethod constructor) {
		if (constructor.containingType instanceof TInterface) {
			addIssue(getMessageForITF_NO_CONSTRUCTOR, constructor.astElement, PROPERTY_NAME_OWNER__NAME,
				ITF_NO_CONSTRUCTOR);
			return false;
		}
		return true;
	}

	private def boolean holdsConstructorNoReturnType(TMethod constructor) {
		val constructorDecl = constructor.astElement as N4MethodDeclaration;
		if (constructorDecl.returnTypeRef!==null) {
			addIssue(getMessageForCLF_CTOR_RETURN_TYPE, constructorDecl, FUNCTION_DEFINITION__RETURN_TYPE_REF, CLF_CTOR_RETURN_TYPE);
			return false;
		}
		return true;
	}

	/**
	 * N4JS spec constraints 44.3
	 */
	private def boolean holdsRequiredExplicitSuperCallIsFound(TMethod constructor) {
		if ((constructor.astElement as N4MemberDeclaration).body !== null) { // otherwise another validation will complain
			val type = constructor.eContainer;
			if (type instanceof TClass) { // otherwise another validation will complain
				val G = RuleEnvironmentExtensions.newRuleEnvironment(constructor);
				val superClass = RuleEnvironmentExtensions.getDeclaredOrImplicitSuperType(G, type)
				val ctor = containerTypesHelper.fromContext(constructor).findConstructor(superClass);
				if (ctor !== null) {
					if (ctor.fpars.size > 0) {
						val existsSuperCall = (constructor.astElement as N4MethodDeclaration).existsExplicitSuperCall();
						if (! existsSuperCall) {
							val className = (ctor.eContainer as IdentifiableElement).name;
							addIssue(
								getMessageForKEY_SUP_REQUIRE_EXPLICIT_SUPERCTOR_CALL(className),
								constructor.astElement,
								PROPERTY_NAME_OWNER__NAME,
								KEY_SUP_REQUIRE_EXPLICIT_SUPERCTOR_CALL
							)
							return false;
						} // else: ok so far, more checks on super call are performed in N4JSExpressionValidator
					}
				}
			}
		}
		return true;
	}

	def private boolean holdsCallableConstructorConstraints(N4MethodDeclaration method) {
		if(method.isCallableConstructor) {
			// constraint: only in classes
			if(!(method.eContainer instanceof N4ClassDefinition)) {
				addIssue(getMessageForCLF_CTOR_CALLABLE_ONLY_IN_CLASS, method, CLF_CTOR_CALLABLE_ONLY_IN_CLASS);
				return false;
			}
			// constraint: only in .n4jsd files
			if(!JavaScriptVariant.external.isActive(method)) {
				addIssue(getMessageForCLF_CTOR_CALLABLE_ONLY_IN_N4JSD, method, CLF_CTOR_CALLABLE_ONLY_IN_N4JSD);
				return false;
			}
			// constraint: not more than one callable constructor per class
			if((method.eContainer as N4ClassifierDefinition).ownedMembersRaw.filter[isCallableConstructor].size>=2) {
				addIssue(getMessageForCLF_CTOR_CALLABLE_DUPLICATE, method, CLF_CTOR_CALLABLE_DUPLICATE);
			}
		}
		return true;
	}

	def private boolean holdsAbstractOrHasBody(TMember member) {
		val requireCheckForMissingBody = JavaScriptVariant.getVariant(member) != JavaScriptVariant.external;
		val memberIsAbstract = switch (member) {
			TMethod: member.isAbstract()
			FieldAccessor: member.isAbstract()
			default: false
		};
		if (requireCheckForMissingBody && !memberIsAbstract && (member.astElement as N4MemberDeclaration).body === null) {
			if (member.isConstructor) {
				addIssue(messageForCLF_MISSING_CTOR_BODY, member.astElement, PROPERTY_NAME_OWNER__NAME,
					IssueCodes.CLF_MISSING_CTOR_BODY)
			} else {
				val message = IssueCodes.getMessageForCLF_MISSING_BODY(member.keyword, member.name)
				addIssue(message, member.astElement, PROPERTY_NAME_OWNER__NAME, IssueCodes.CLF_MISSING_BODY)
			}
			return false;
		}
		return true;
	}

	/**
	 * Constraints 49: abstract methods/getters/setters must not be static and vice versa.
	 */
	def private boolean holdsAbstractMethodMustNotBeStatic(TMember member) {
		if (member.abstract && member.static) {
			addIssue(getMessageForCLF_STATIC_ABSTRACT(member.keyword, member.name), member.astElement,
				PROPERTY_NAME_OWNER__NAME, CLF_STATIC_ABSTRACT)
			return false;
		}
		return true;
	}

	def private boolean holdsAbstractMethodMustHaveNoBody(TMember member) {
		if (member.abstract && (member.astElement as N4MemberDeclaration).body !== null) {
			val message = IssueCodes.getMessageForCLF_ABSTRACT_BODY
			addIssue(message, member.astElement, PROPERTY_NAME_OWNER__NAME, IssueCodes.CLF_ABSTRACT_BODY)
			return false;
		}
		return true;
	}

	def private boolean holdsAbstractMemberContainedInAbstractClassifier(TMember member) {
		if (member.abstract) {
			val classifier = EcoreUtil2.getContainerOfType(member, TClassifier)
			if (classifier !== null && !classifier.abstract) {
				val message = IssueCodes.getMessageForCLF_ABSTRACT_MISSING(member.keyword, member.name, classifier.name)
				addIssue(message, member.astElement, PROPERTY_NAME_OWNER__NAME, IssueCodes.CLF_ABSTRACT_MISSING)
				return false;
			}
		}
		return true;
	}

	/**
	 * Internally, internal project and internal private do not exist. (IDEBUG-658)
	 */
	def private void internalCheckPrivateOrProjectWithInternalAnnotation(N4MemberDeclaration n4Member, TMember tmember) {
		if (AnnotationDefinition.INTERNAL.hasAnnotation(n4Member)) {
			val memberAccessModifier = tmember.memberAccessModifier
			val hasPrivateModifier = (memberAccessModifier === MemberAccessModifier.PRIVATE)
			val hasProjectModifier = (memberAccessModifier === MemberAccessModifier.PROJECT)
			if (hasPrivateModifier || hasProjectModifier) {
				val message = IssueCodes.getMessageForCLF_INTERNAL_BAD_WITH_PRIVATE_OR_PROJECT();
				addIssue(message, tmember.astElement, PROPERTY_NAME_OWNER__NAME,
					IssueCodes.CLF_INTERNAL_BAD_WITH_PRIVATE_OR_PROJECT);
			}
		}
	}

	/**
	 * Constraint 44.3: Members of an interface must not be declared private or project
	 */
	private def boolean holdsMinimalMemberAccessModifier(TMember member) {
		if (member.containingType instanceof TInterface) {
			val memberAccessModifier = member.memberAccessModifier
			if (memberAccessModifier === MemberAccessModifier.PRIVATE) {
				val message = IssueCodes.getMessageForCLF_MINIMAL_ACCESSIBILITY_IN_INTERFACES();
				addIssue(message, member.astElement, PROPERTY_NAME_OWNER__NAME,
					IssueCodes.CLF_MINIMAL_ACCESSIBILITY_IN_INTERFACES);
				return false;
			}
		}
		return true;
	}

	/** IDEBUG-779  Type Annotations without type but just an optional modifier are not allowed.
	 */
    private def boolean holdsDeclaredTypeRefDoesNotReferToNull(N4FieldDeclaration n4Field) {
    	val declTypeRef = n4Field.declaredTypeRef
    	if( declTypeRef !== null ) {
    		if( declTypeRef instanceof ParameterizedTypeRef ){
	    		if( declTypeRef.declaredType === null )
	    		{
	    			val message = IssueCodes.getMessageForCLF_FIELD_MODIFIER_WITHOUT_TYPE(n4Field.name);
	    			addIssue(message, n4Field, N4JSPackage.Literals.TYPED_ELEMENT__DECLARED_TYPE_REF,
	    				IssueCodes.CLF_FIELD_MODIFIER_WITHOUT_TYPE
	    			);
	    			return false;
	    		}
    		}
    	}
    	return true;
    }



	def private internalCheckAccessorType(FieldAccessor accessorDeclaration) {
		val accessorType = accessorDeclaration.declaredTypeRef?.declaredType;
		if (accessorType !== null && accessorType instanceof VoidType) {
			val message = IssueCodes.messageForCLF_VOID_ACCESSOR
			addIssue(message, accessorDeclaration.astElement, PROPERTY_NAME_OWNER__NAME, IssueCodes.CLF_VOID_ACCESSOR)
		}
	}


	@Check
	def checkDuplicateFieldsIn(N4ClassDeclaration n4ClassDeclaration) {
		val tClass = n4ClassDeclaration.definedType
		if (tClass instanceof TClass) {
			iterateConstructorHierarchy(tClass, tClass, newArrayList)
		}
	}

	private def void iterateConstructorHierarchy(TClass tClass, TClass superClass, List<TClass> alreadyVisited) {
		val superType = superClass.superClassRef?.declaredType
		if (superType instanceof TClass) {
			if (alreadyVisited.contains(superType)) {
				return
			} else {
				alreadyVisited += superType
			}
			val constructor = superType.ownedMembers.filter[constructor].head as TMethod
			if (constructor !== null) {
				val isInSameClass = false
				handleConstructor(constructor, tClass, isInSameClass)
			} else {
				iterateConstructorHierarchy(tClass, superType, alreadyVisited)
			}
		}
	}

	private def handleConstructor(TMethod constructor, TClass tClass, boolean isInSameClass) {
		val thisTypeRefStructural = constructor.fpars.map[typeRef].filter(ThisTypeRefStructural).head
		if (thisTypeRefStructural !== null) {
			internalCheckDuplicateFieldsIn(tClass, thisTypeRefStructural, isInSameClass)
		}
	}

	@Check
	def checkDuplicateFieldsIn(ThisTypeRefStructural thisTypeRefStructural) {
		val n4ClassifierDefinition = EcoreUtil2.getContainerOfType(thisTypeRefStructural, N4ClassifierDefinition)
		if (n4ClassifierDefinition !== null) {
			val tClass = n4ClassifierDefinition.definedType
			if (tClass instanceof TClass) {
				val isInSameClass = true
				internalCheckDuplicateFieldsIn(tClass, thisTypeRefStructural, isInSameClass)
			}
		}
	}

	private def internalCheckDuplicateFieldsIn(TClass tclass, ThisTypeRefStructural thisTypeRefStructural,
		boolean isInSameClass) {
		val members = LazyOverrideAwareMemberCollector.collectAllMembers(tclass)
		val membersByNameAndStatic = members.groupBy[Tuples.pair(name, static)];
		val structuralMembersByNameAndStatic = thisTypeRefStructural.structuralMembers.groupBy[Tuples.pair(name, static)];
		structuralMembersByNameAndStatic.keySet.forEach [
			if (membersByNameAndStatic.containsKey(it)) {
				val structuralFieldDuplicate = structuralMembersByNameAndStatic.get(it).head
				val existingClassifierMember = membersByNameAndStatic.get(it).head
				if (existingClassifierMember.memberAccessModifier == MemberAccessModifier.PUBLIC) {
					if (isInSameClass) {
						val message = getMessageForCLF_DUP_MEMBER(structuralFieldDuplicate.descriptionWithLine,
							existingClassifierMember.descriptionWithLine);
						val index = thisTypeRefStructural.structuralMembers.indexOf(structuralFieldDuplicate)
						addIssue(message, thisTypeRefStructural,
							TypeRefsPackage.Literals.STRUCTURAL_TYPE_REF__AST_STRUCTURAL_MEMBERS, index, CLF_DUP_MEMBER)
					} else {
						val message = getMessageForCLF_DUP_WITH_MEMBER(existingClassifierMember.description)
						addIssue(message, existingClassifierMember.astElement,
							N4JSPackage.Literals.PROPERTY_NAME_OWNER__NAME, CLF_DUP_WITH_MEMBER)
					}
				}
			}
		]
	}

	/**
	 * A list of annotations that requires {@code @Test} annotation as well.
	 * Currently used by {@link #checkFixmeUsedWithTestAnnotation(N4MethodDeclaration)} validation method.
	 */
	val static ANNOTATIONS_REQUIRE_TEST = #[TEST_FIXME, TEST_IGNORE];

	/**
	 * Raises an issue if a method has any arbitrary annotation that requires {@Test} annotation as well but it does not have it.
	 * Currently:
	 * <ul>
	 * <li>{@code @Ignore} and</li>
	 * <li>{@code @Fixme}</li>
	 * </ul>
	 * requires {@code @Test} annotation.
	 */
	@Check
	def checkFixmeUsedWithTestAnnotation(N4MethodDeclaration methodDecl) {
		ANNOTATIONS_REQUIRE_TEST.forEach[ annotation |
			if (annotation.hasAnnotation(methodDecl) && !TEST_METHOD.hasAnnotation(methodDecl)) {
				addIssue(
					getMessageForANN_REQUIRES_TEST('''@«annotation.name»'''),
					methodDecl.annotations.findFirst[name == annotation.name],
					N4JSPackage.eINSTANCE.annotation_Name,
					ANN_REQUIRES_TEST);
			}
		];
	}

}
