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
import eu.numberfour.n4js.n4JS.AdditiveExpression
import eu.numberfour.n4js.n4JS.AdditiveOperator
import eu.numberfour.n4js.n4JS.Argument
import eu.numberfour.n4js.n4JS.ArrayElement
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.AssignmentExpression
import eu.numberfour.n4js.n4JS.AwaitExpression
import eu.numberfour.n4js.n4JS.BinaryLogicalExpression
import eu.numberfour.n4js.n4JS.BooleanLiteral
import eu.numberfour.n4js.n4JS.CastExpression
import eu.numberfour.n4js.n4JS.ConditionalExpression
import eu.numberfour.n4js.n4JS.EqualityExpression
import eu.numberfour.n4js.n4JS.EqualityOperator
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.IndexedAccessExpression
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.NewExpression
import eu.numberfour.n4js.n4JS.NumericLiteral
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.PostfixExpression
import eu.numberfour.n4js.n4JS.PromisifyExpression
import eu.numberfour.n4js.n4JS.RelationalExpression
import eu.numberfour.n4js.n4JS.RelationalOperator
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.SuperLiteral
import eu.numberfour.n4js.n4JS.ThisLiteral
import eu.numberfour.n4js.n4JS.TypeDefiningElement
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.n4JS.UnaryOperator
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.n4JS.extensions.ExpressionExtensions
import eu.numberfour.n4js.scoping.accessModifiers.MemberVisibilityChecker
import eu.numberfour.n4js.scoping.accessModifiers.VisibilityAwareCtorScope
import eu.numberfour.n4js.scoping.members.MemberScopingHelper
import eu.numberfour.n4js.scoping.members.TypingStrategyAwareMemberScope
import eu.numberfour.n4js.ts.conversions.ComputedPropertyNameValueConverter
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef
import eu.numberfour.n4js.ts.typeRefs.EnumTypeRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef
import eu.numberfour.n4js.ts.types.BuiltInType
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.MemberAccessModifier
import eu.numberfour.n4js.ts.types.PrimitiveType
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TInterface
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TN4Classifier
import eu.numberfour.n4js.ts.types.TSetter
import eu.numberfour.n4js.ts.types.TStructuralType
import eu.numberfour.n4js.ts.types.TVariable
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.types.TypeDefs
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.TypingStrategy
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.utils.ContainerTypesHelper
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import eu.numberfour.n4js.utils.PromisifyHelper
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4js.validation.IssueCodes
import eu.numberfour.n4js.validation.JavaScriptVariant
import eu.numberfour.n4js.validation.N4JSElementKeywordProvider
import eu.numberfour.n4js.validation.ValidatorMessageHelper
import eu.numberfour.n4js.validation.helper.N4JSLanguageConstants
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import eu.numberfour.n4js.xtext.scoping.IEObjectDescriptionWithError
import it.xsemantics.runtime.RuleEnvironment
import it.xsemantics.runtime.validation.XsemanticsValidatorErrorGenerator
import java.util.ArrayList
import java.util.Collections
import java.util.Comparator
import java.util.List
import java.util.Set
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.emf.ecore.util.EcoreUtil
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static eu.numberfour.n4js.ts.utils.TypeUtils.*
import static eu.numberfour.n4js.validation.IssueCodes.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import eu.numberfour.n4js.ts.types.TExportableElement
import eu.numberfour.n4js.ts.types.ModuleNamespaceVirtualType

/**
 */
class N4JSExpressionValidator extends AbstractN4JSDeclarativeValidator {

	@Inject
	protected N4JSTypeSystem ts;
	@Inject
	protected TypeSystemHelper tsh;
	@Inject
	protected XsemanticsValidatorErrorGenerator errorGenerator;
	@Inject extension N4JSElementKeywordProvider;
	@Inject extension ValidatorMessageHelper;

	@Inject ContainerTypesHelper containerTypesHelper;

	@Inject private MemberScopingHelper memberScopingHelper;
	@Inject private MemberVisibilityChecker memberVisibilityChecker

	@Inject private PromisifyHelper promisifyHelper;


	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	override register(EValidatorRegistrar registrar) {
		// nop
	}

	@Check
	def checkAwaitExpression(AwaitExpression awaitExpression) {

		val FunctionDefinition containerFunDef = EcoreUtil2.getContainerOfType(awaitExpression, FunctionDefinition);
		if (containerFunDef === null || containerFunDef.isAsync() === false) {
			val message = IssueCodes.getMessageForEXP_MISPLACED_AWAIT_EXPRESSION("await", "async");
			addIssue(message, awaitExpression, IssueCodes.EXP_MISPLACED_AWAIT_EXPRESSION);
		}
	}
	@Check
	def checkPropertyAccesssExpression(ParameterizedPropertyAccessExpression propAccessExpression) {
		if (propAccessExpression?.target === null)
			return; // invalid AST

		// check type arguments
		val prop = propAccessExpression.property;
		val typeVars = if (prop instanceof Type) prop.typeVars else #[]; // else-case required for TField, TGetter, TSetter
		internalCheckTypeArguments(typeVars, propAccessExpression.typeArgs, true, prop, propAccessExpression,
			N4JSPackage.eINSTANCE.parameterizedPropertyAccessExpression_Property);

		// check methods aren't assigned to variables or parameters
		internalCheckMethodReference(propAccessExpression)

		// check access to static members of interfaces
		internalCheckAccessToStaticMemberOfInterface(propAccessExpression)
	}

	/**
	 * Fixes IDE-1048, Method Assignment: Methods can't be assigned to variables.
	 *
	 * <p>
	 * If allowed, that variable could be used later
	 * to invoke the function it holds with a wrong this-instance
	 * (for example, with an instance of a superclass while the function,
	 * defined in a subclass, requires members private to that subclass).
	 *
	 * <p>
	 * To be safe, we warn on expressions out of which a method reference might escape
	 * and become assigned to a variable. An example where a method reference is consumed
	 * before escaping is <code>typeof method-ref-expr</code>, for which no warning is raised.
	 *
	 */
	private def internalCheckMethodReference(ParameterizedPropertyAccessExpression propAccessExpression) {
		if (JavaScriptVariant.getVariant(propAccessExpression) !== JavaScriptVariant.n4js) {
			return
		}
		val prop = propAccessExpression.property;
	    if (!(prop instanceof TMethod)) {
	    	/*
	    	 * Other kinds of members (fields, getters; similarly for setters) need not be checked because
	    	 * when used in a property access they return the underlying value as opposed to a "member-reference".
	    	 */
	    	return
	    }
	    val TMethod method = prop as TMethod
		val enclosing = propAccessExpression.eContainer
		/*
		 * Unless we find a good reason not to, we'll warn.
		 * Each such "good reason" is whitelisted (for example, 'typeof method-ref-expr').
		 * The list isn't exhaustive, additions can be discussed in a new ticket that links to IDE-1048.
		 */
		val shouldWarn = switch enclosing {
			ParameterizedCallExpression: enclosing.target !== propAccessExpression
			ParameterizedPropertyAccessExpression: false
			UnaryExpression: enclosing.op !== UnaryOperator.TYPEOF
			EqualityExpression: false
			ExpressionStatement: false
			default: true
		}
		if (!shouldWarn) {
			return
		}
		/*
		 * We're set to warn. However, we've left an (expensive) check for the end,
		 * which might overturn the decision so far. In case we can determine
		 * the body of the method being referred to (because it's final) and
		 * such body contains neither 'this' nor 'super' usages,
		 * ie in effect such method puts no requirements on the invocation context,
		 * then that method reference is allowed to escape.
		 */
		if (isMethodEffectivelyFinal(method) && method.lacksThisOrSuperUsage) {
			return
		}
		// no more whitelist checks
		val message = IssueCodes.getMessageForEXP_METHOD_REF_UNATTACHED_FROM_RECEIVER(method.name);
		val source = propAccessExpression
		val feature = N4JSPackage.eINSTANCE.parameterizedPropertyAccessExpression_Property
		warning(message, source, feature, IssueCodes.EXP_METHOD_REF_UNATTACHED_FROM_RECEIVER);
	}

	private def isMethodEffectivelyFinal(TMethod method) {
		method.isFinal ||
		method.getMemberAccessModifier() == MemberAccessModifier.PRIVATE ||
		method.containingType.isFinal // if the containing type is final all its method are assumed final too
	}

	/**
	 * Static members of interfaces may only be accessed directly via the type name of the containing interface.
	 * This is required, because there is no inheritance of static members of interfaces.
	 */
	private def void internalCheckAccessToStaticMemberOfInterface(ParameterizedPropertyAccessExpression propAccessExpr) {
		val prop = propAccessExpr.property;
		if(prop instanceof TMember) {
			if(prop!==null && prop.static && prop.eContainer instanceof TInterface) {
				val target = propAccessExpr.target;
				val targetIdRef = if(target instanceof IdentifierRef) target else null;
				val isExceptionCase = target instanceof ThisLiteral; // avoid duplicate error messages
				if(targetIdRef?.id !== prop.eContainer && !isExceptionCase) {
					val message = IssueCodes.getMessageForCLF_INVALID_ACCESS_OF_STATIC_MEMBER_OF_INTERFACE;
					addIssue(message, propAccessExpr, N4JSPackage.eINSTANCE.parameterizedPropertyAccessExpression_Target,
						IssueCodes.CLF_INVALID_ACCESS_OF_STATIC_MEMBER_OF_INTERFACE);
				}
			}
		}
	}

	@Check
	def checkCallExpression(ParameterizedCallExpression callExpression) {
		if (JavaScriptVariant.getVariant(callExpression).isECMAScript()) {
			return; // cf. 13.1
		}
		if (callExpression?.target === null)
			return; // invalid AST

		val typeRef = typeInferencer.tau(callExpression.target);
		if (typeRef === null)
			return; // invalid AST
		if (typeRef instanceof UnknownTypeRef)
			return; // suppress error message in case of UnknownTypeRef

		// make sure target can be invoked
		if (!(callExpression.target instanceof SuperLiteral)
			&& !tsh.isCallable(typeRef, callExpression.eResource)) {

			if(tsh.isClassConstructorFunction(typeRef) || isClassifierTypeRefToAbstractClass(typeRef)) {
				val message = IssueCodes.getMessageForEXP_CALL_CLASS_CTOR;
				addIssue(message, callExpression.target, null, IssueCodes.EXP_CALL_CLASS_CTOR);
			} else {
				val message = IssueCodes.getMessageForEXP_CALL_NOT_A_FUNCTION(typeRef.typeRefAsString);
				addIssue(message, callExpression.target, null, IssueCodes.EXP_CALL_NOT_A_FUNCTION);
			}
			return;
		}

		if (typeRef instanceof FunctionTypeExprOrRef) {
			// check type arguments
			internalCheckTypeArguments(typeRef.typeVars, callExpression.typeArgs, true, typeRef.declaredType,
				callExpression, N4JSPackage.eINSTANCE.parameterizedCallExpression_Target);

			// check Calling async functions with missing await
			internalCheckCallingAsyncFunWithoutAwaitingForIt(typeRef, callExpression)

			// Constraints 51 (Name restriction in method-bodies):
			val trgt = callExpression.target
			switch(trgt){
				IdentifierRef:
				{
					internalCheckNameRestrictionInMethodBodies(trgt,
						[ String message, EObject source, EStructuralFeature feature, String issueCode |
							addIssue(message,source,feature,issueCode)
						]
					)
				}
			}
		}
	}

	def private boolean isClassifierTypeRefToAbstractClass(TypeRef typeRef) {
		if(typeRef instanceof ClassifierTypeRef) {
			val staticType = typeRef.staticType;
			if(staticType instanceof TClass) {
				return staticType.isAbstract;
			}
		}
		return false;
	}

	/**
	 * If the given function-type is async and not awaited-for, issue a warning unless the return-type (Promise) is made explicit.
	 * "Made explicit" either by:
	 * <ul>
	 * <li>the invocation is the RHS of a variable (declaration or assignment) where the LHS makes explicit the Promise type.</li>
	 * <li>the invocation is made at the top-level for its side-effects.</li>
	 * <li>the invocation is given as argument to {@code Promise.all()}, {@code Promise.race()}, or {@code Promise.resolve()}.</li>
	 * </ul>
	 * To clarify, a not-awaited-for call is perfectly valid, after all sometimes only the promise is of interest, but more commonly an await was forgotten.
	 */
	def internalCheckCallingAsyncFunWithoutAwaitingForIt(FunctionTypeExprOrRef fteor, ParameterizedCallExpression callExpression) {
		val G = RuleEnvironmentExtensions.newRuleEnvironment(callExpression);
		if (!N4JSLanguageUtils.isAsync(fteor, G)) {
			return
		}
		var container = callExpression.eContainer
		while (container instanceof ParenExpression) {
			// related: ExpressionExtensions.isPotentialEvalResult()
			container = container.eContainer
		}
		val isAwaitedFor = (container instanceof AwaitExpression);
		val isTopLevel = (container instanceof ExpressionStatement && container.eContainer instanceof Script);
		if (isAwaitedFor || isTopLevel) {
			return
		}
		val isPromiseExplict =
			if (container instanceof VariableDeclaration) {
				(container.expression === callExpression) && (container.declaredTypeRef !== null)
			} else if (container instanceof AssignmentExpression) {
				(container.rhs === callExpression)
			} else if (isArgumentToPromiseUtilityMethod(callExpression, container, G)) {
				true
			} else {
				false
			}
		val shouldWarn = !isPromiseExplict;
		if (shouldWarn) {
			val message = IssueCodes.getMessageForEXP_MISSNG_AWAIT_FOR_ASYNC_TARGET();
			addIssue(message, callExpression.target, IssueCodes.EXP_MISSNG_AWAIT_FOR_ASYNC_TARGET);
		}
	}

	/**
	 * Does the given AST-node occur as argument to {@code Promise.all()}, {@code Promise.race()}, or {@code Promise.resolve()} ?
	 */
	private def boolean isArgumentToPromiseUtilityMethod(ParameterizedCallExpression asyncInvocation, EObject container, RuleEnvironment G) {
		var EObject utilityCall = container
		val isArrayElem = (container instanceof ArrayElement && container.eContainer instanceof ArrayLiteral);
		if (isArrayElem) {
			utilityCall = container.eContainer.eContainer
		}
		if (utilityCall instanceof Argument) {
			utilityCall = utilityCall.eContainer;
		}
		// let's see if 'container' stands for 'Promise.{all/race/resolve}(...,asyncInvocation,...)'
		if (utilityCall instanceof ParameterizedCallExpression) {
			if (utilityCall.target instanceof ParameterizedPropertyAccessExpression) {
				// let's see if 'utilityAccess' stands for 'Promise.{all/race/resolve}'
				val utilityAccess = utilityCall.target as ParameterizedPropertyAccessExpression;
				if (isPromiseUtilityPropertyAccess(utilityAccess, G)) {
					val isDirectArg = utilityCall.arguments.exists[arg| arg.expression === asyncInvocation]
					if (isDirectArg) {
						return true
					}
					val name = utilityAccess.property.name
					if (isArrayElem && (name == 'all' || name == 'race')) {
						// let's see if 'callExpression' occurs as arg in 'Promise.{all/race}([...,asyncInvocation,...])'
						val argOccursInArray = utilityCall.arguments.exists[arg| arg.expression === container.eContainer]
						return argOccursInArray
					}
				}
			}
		}
		return false
	}

	/**
	 * Does 'utilityAccess' stand for 'Promise.{all/race/resolve}' ?
	 */
	private def boolean isPromiseUtilityPropertyAccess(ParameterizedPropertyAccessExpression utilityAccess, RuleEnvironment G) {
		val invokedUtility = utilityAccess.property
		if (invokedUtility instanceof TMethod) {
			val isStaticUtility = invokedUtility.isStatic
			val hasNameOfInterest = (#['all', 'race', 'resolve'].contains(invokedUtility.name))
			if (isStaticUtility && hasNameOfInterest) {
				// let's see if utilityAccess.target denotes Promise
				val tscope = RuleEnvironmentExtensions.getPredefinedTypes(G).builtInTypeScope
				val tresult = ts.type(G, utilityAccess.target)
				if (!tresult.failed) {
					val tr = tresult.value
					if (tr instanceof ConstructorTypeRef) {
						val str = tr.staticTypeRef
						val isReceiverPromise = TypeUtils.isPromise(str, tscope)
						return isReceiverPromise
					}
				}
			}
		}
		return false
	}

	/**
	 * Constraints 51 (Name restriction in method-bodies):
	 *
	 * checks, that in case the trgt refers to a plain function (not a method) and ends with "___n4",
	 * it will not be contained in Method.
	 */
	def static void internalCheckNameRestrictionInMethodBodies(IdentifierRef trgt, (String , EObject , EStructuralFeature , String  )=>void g ) {
		if( trgt.id instanceof TFunction && !(trgt.id instanceof TMethod)){
			if( trgt.id.name.endsWith(N4JSLanguageConstants.METHOD_STACKTRACE_SUFFIX) ) {
				// Find container:
				val containingMethod = EcoreUtil2.getContainerOfType(trgt, N4MethodDeclaration )
				if( containingMethod !== null ) {
					// add issue:
					val msg = IssueCodes.messageForCLF_METHOD_BODY_FORBIDDEN_REFERENCE_NAME
					// wrapped in g:
					// addIssue(msg, trgt, N4JSPackage.eINSTANCE.identifierRef_Id, IssueCodes.CLF_METHOD_BODY_FORBIDDEN_REFERENCE_NAME )
					g.apply(msg, trgt, N4JSPackage.eINSTANCE.identifierRef_Id, IssueCodes.CLF_METHOD_BODY_FORBIDDEN_REFERENCE_NAME )
				}
			}
		}
	}

	@Check
	def checkNew(NewExpression newExpression) {
		if (JavaScriptVariant.getVariant(newExpression).isECMAScript()) {
			return; // cf. 13.1
		}
		if (newExpression?.callee === null)
			return; // invalid AST

		val typeRef = typeInferencer.tau(newExpression.callee)
		if (typeRef === null)
			return; // invalid AST
		if (typeRef instanceof UnknownTypeRef)
			return; // suppress error message in case of UnknownTypeRef

		val staticType = if (typeRef instanceof ClassifierTypeRef) typeRef.staticType else null;
		if (staticType === null) {
			issueNotACtor(typeRef,newExpression);
			return;
		}
		if (staticType.eIsProxy) {
			return;
		}

		if (staticType === newExpression.newRuleEnvironment.symbolObjectType) {
			// error case #1: new Symbol()
			val message = IssueCodes.messageForBIT_SYMBOL_NOT_A_CTOR;
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.BIT_SYMBOL_NOT_A_CTOR);

		} else if (typeRef instanceof ConstructorTypeRef) {
			// success case; but perform some further checks
			internalCheckCtorVisibility(newExpression, typeRef)
			internalCheckTypeArguments(staticType.typeVars, newExpression.typeArgs, false, staticType, newExpression,
				N4JSPackage.eINSTANCE.newExpression_Callee);

		} else if (staticType instanceof TInterface) {
			// error case #2: trying to instantiate an interface
			val message = IssueCodes.getMessageForEXP_NEW_CANNOT_INSTANTIATE(staticType.keyword, staticType.name);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_CANNOT_INSTANTIATE);

		} else if (staticType instanceof TClass && (staticType as TClass).abstract) {
			// error case #3: trying to instantiate an abstract class
			val message = IssueCodes.getMessageForEXP_NEW_CANNOT_INSTANTIATE("abstract class", staticType.name);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_CANNOT_INSTANTIATE);

		} else {
			// error case #4: not a ConstructorTypeRef
			issueNotACtor(typeRef, newExpression);
		}
	}

	/** Helper to issue the error case of having a new-expression on a non-constructor element */
	private def issueNotACtor(TypeRef typeRef, NewExpression newExpression) {
		val message = IssueCodes.getMessageForEXP_NEW_NOT_A_CTOR(typeRef.typeRefAsString);
		addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
			IssueCodes.EXP_NEW_NOT_A_CTOR)
	}

	/**
	 * Checks visibility of the cTor.
	 * Cf. Spec:  "Table 3.2.: Member Access Control"
	 */
	def internalCheckCtorVisibility(NewExpression expression, ConstructorTypeRef ref) {

		if (ref.staticType instanceof TypeVariable) {
			/* cannot check, back out */
			// TODO is it possible to create an accessibility-check here ?
			return;
		}

		val TClassifier ctorClassifier = ref.staticType as TClassifier

		val usedCtor = containerTypesHelper.fromContext(expression).findConstructor(ctorClassifier)

		if( usedCtor === null ) {
			// case of broken AST / Typesystem
			return;
		}

		val memberScope = memberScopingHelper.createMemberScopeFor(TypeUtils.createTypeRef(ctorClassifier), expression, false, false); // always non-static
		val scope = new TypingStrategyAwareMemberScope(new VisibilityAwareCtorScope(memberScope, memberVisibilityChecker, ref, expression), ref);

		val ele = scope.getSingleElement(usedCtor)
		if( IEObjectDescriptionWithError.isErrorDescription(ele) ) {
			val errDescr = IEObjectDescriptionWithError.getDescriptionWithError(ele)
			addIssue(errDescr.message, expression, N4JSPackage.eINSTANCE.newExpression_Callee, errDescr.issueCode )
		}

	}

	/**
	 * Checks instanceof in combination with structural typing, other checks see
	 * eu.numberfour.n4js.xsemantics.N4JSTypeSystem.expectedTypeInRelationalExpression
	 */
	@Check
	def checkRelationalExpression(RelationalExpression relationalExpression) {
		if (relationalExpression.rhs !== null && relationalExpression.op === RelationalOperator.INSTANCEOF) {
			val typeRef = typeInferencer.tau(relationalExpression.rhs)
			if (typeRef instanceof ClassifierTypeRef) {
				val staticType = typeRef.staticType
				if (staticType instanceof TN4Classifier) {
					if (staticType.typingStrategy !== TypingStrategy.DEFAULT) {
						val message = IssueCodes.
							getMessageForTYS_INSTANCEOF_NOT_SUPPORTED_FOR_STRUCTURAL_TYPES(staticType.name);
						addIssue(message, relationalExpression, N4JSPackage.eINSTANCE.relationalExpression_Rhs,
							IssueCodes.TYS_INSTANCEOF_NOT_SUPPORTED_FOR_STRUCTURAL_TYPES);
					}
					else if(staticType instanceof TInterface && EcoreUtil.getRootContainer(staticType) instanceof TypeDefs) {
						val message = IssueCodes.
							getMessageForTYS_INSTANCEOF_NOT_SUPPORTED_FOR_BUILT_IN_INTERFACES(staticType.name);
						addIssue(message, relationalExpression, N4JSPackage.eINSTANCE.relationalExpression_Rhs,
							IssueCodes.TYS_INSTANCEOF_NOT_SUPPORTED_FOR_BUILT_IN_INTERFACES);
					}
				}
			}
		}
	}

	/** IDE-737: properties in postfixExpressions need both of getter/setter.
	    Getter is bound to the property-field in PropertyAccessExpression, the existence of a setter needs to be validated.
	*/
	@Check
	def checkPostfixExpression(PostfixExpression postfixExpression) {

		val expression = postfixExpression.expression;
		holdsWritabelePropertyAccess(expression)
		&& holdsWritableIdentifier(expression)
		&& holdsLefthandsideNotConst(expression);
	}

	/** IDE-731 / IDE-768 unary expressions of type ++ or -- need both of getter/setter.
	 * Cf. Constraint 69
	 */
	@Check
	def checkUnaryExpressionWithWriteAccess(UnaryExpression unaryExpression) {
		if (UnaryOperator.DEC === unaryExpression.op || UnaryOperator.INC === unaryExpression.op) {
			holdsWritabelePropertyAccess(unaryExpression.expression)
			&& holdsWritableIdentifier(unaryExpression.expression)
			&& holdsLefthandsideNotConst(unaryExpression.expression);
		}
	}

	private def boolean holdsWritabelePropertyAccess(Expression expression) {
		if (expression instanceof ParameterizedPropertyAccessExpression) {
			val property = expression.property
			if (property instanceof TGetter) {

				// access through getter --> a matching setter is required:
				val propertyTargetType = typeInferencer.tau(expression.target)
				val declaredType = propertyTargetType?.declaredType
				if (declaredType instanceof TClassifier) {
					val setterExists = containerTypesHelper.fromContext(expression).members(declaredType).filter(TSetter).exists[
						name.equals(property.name)]
					if (!setterExists) {
						val msg = IssueCodes.getMessageForTYS_PROPERTY_HAS_NO_SETTER(property.name)
						addIssue(msg, expression,
							N4JSPackage.Literals.PARAMETERIZED_PROPERTY_ACCESS_EXPRESSION__PROPERTY,
							IssueCodes.TYS_PROPERTY_HAS_NO_SETTER);
						return false;	
					}
				}
			}
		}
		return true;
	}
	
	/**
	 * Ensures that imported elements get not reassigned any value.
	 * @returns true if validation hold, false if some issue was generated.
	 */
	private def boolean holdsWritableIdentifier(Expression expression) {
		if( expression instanceof IdentifierRef ) {
			val id = expression.id;
			switch(id) {
				TExportableElement /* includes TClass, TVariable */
				: {
					val module = EcoreUtil2.getContainerOfType(expression,Script).module;
					if( id.containingModule != module ) {
						// imported variable, class, etc.
						addIssue(IssueCodes.getMessageForIMP_IMPORTED_ELEMENT_READ_ONLY(expression.idAsText), expression, IssueCodes.IMP_IMPORTED_ELEMENT_READ_ONLY	);
						return false;
					}
				}
			} 	
		} else if ( expression instanceof ParenExpression ) {
			// resolve parent-expressions wrapping simple identifiers:
			return holdsWritableIdentifier( expression.expression );
		} else if ( expression instanceof ParameterizedPropertyAccessExpression ) {
			val target = expression.target;
			// guard against broken models:
			if( expression.property !== null && !expression.property.eIsProxy) {
				if( target instanceof IdentifierRef ) {
					val id = target.id;
					// handle namespace imports:
					if( id instanceof ModuleNamespaceVirtualType) {
						if( id.module !=  EcoreUtil2.getContainerOfType(expression,Script).module )	{
							// naive approach for reporting : "target.idAsText+"."+expression.property.name;" results
							// in revealing the name of the default-exported element, but the user can only see 'default' in the validated file
							// so we pick the actual written expression for the error-message generation from the AST:
							val importedElmentText = NodeModelUtils.getTokenText( NodeModelUtils.findActualNodeFor(expression));
							
							addIssue(IssueCodes.getMessageForIMP_IMPORTED_ELEMENT_READ_ONLY( importedElmentText ), expression, IssueCodes.IMP_IMPORTED_ELEMENT_READ_ONLY	);
							return false;
						}
					}
				}
			}
		}
		return true;
	}
	

	@Check
	def checkCallExpressionParameters(ParameterizedCallExpression callExpression) {
		if (JavaScriptVariant.getVariant(callExpression).isECMAScript()) {
			return; // cf. 13.1
		}

		val target = callExpression.target
		if (target !== null) {
			val targetTypeRef = typeInferencer.tau(target); // no context, we only need the number of fpars

			if (targetTypeRef instanceof FunctionTypeExprOrRef) {

				// obtain fpars from invoked function/method
				var fpars = new ArrayList(targetTypeRef.fpars);

				// special case: invoking a promisified function
				// note: being very liberal in next lines to avoid duplicate error messages
				val parent = callExpression.eContainer;
				val isPromisified = (parent instanceof AwaitExpression && promisifyHelper.isAutoPromisify(parent as AwaitExpression))
					|| parent instanceof PromisifyExpression;
				if(isPromisified) {
					fpars.remove(fpars.size-1);
				}

				// check for correct number of arguments
				internalCheckNumberOfArguments(fpars, callExpression.arguments, callExpression);
			}
		}
	}

	@Check
	def checkNewParameters(NewExpression newExpression) {
		if (JavaScriptVariant.getVariant(newExpression).isECMAScript()) {
			return; // cf. 13.1
		}


		// wrong parsed
		if (newExpression.callee === null) {
			return
		}

		val typeRef = typeInferencer.tau(newExpression.callee)
		val staticType = if (typeRef instanceof ClassifierTypeRef) typeRef.staticType else null;

		if (staticType instanceof TClass) {
			val maybeConstructor = containerTypesHelper.fromContext(newExpression).findConstructor(staticType);
			if (maybeConstructor !== null) {
				internalCheckNumberOfArguments((maybeConstructor as TFunction).fpars, newExpression.arguments,
					newExpression)
				return;
			}
		}
	}

	def private void internalCheckNumberOfArguments(List<TFormalParameter> fpars, List<Argument> args, Expression expr) {
		val cmp = compareNumberOfArgsWithNumberOfFPars(fpars, args);
		if (cmp < 0) { // too few
			addIssue(IssueCodes.getMessageForEXP_NUM_OF_ARGS_TOO_FEW(fpars.size, args.size), expr,
				IssueCodes.EXP_NUM_OF_ARGS_TOO_FEW);
		} else if (cmp > 0) { // too many
			addIssue(IssueCodes.getMessageForEXP_NUM_OF_ARGS_TOO_MANY(fpars.size, args.size), expr,
				IssueCodes.EXP_NUM_OF_ARGS_TOO_MANY);
		}
	}

	/**
	 * Compares number of arguments with number of formal parameter, taking optional and variadic parameters into consideration.
	 * @return -1 if to few arguments are found, 1 if too many arguments are found, 0 if number of arguments is according to formal parameter list
	 */
	def private int compareNumberOfArgsWithNumberOfFPars(List<TFormalParameter> fpars, List<Argument> args) {
		val argCount = args.size
		val fparCount = fpars.size;

		if (fpars.size === args.size) {
			return 0;
		}
		if (argCount > fparCount) {
			if (fparCount === 0) {
				return 1; // too many
			}
			if (fpars.last.variadic) {
				return 0;
			}
			return 1; // too many
		}

		// argCount < fparCount (and fparCount>0)
		if (fpars.get(argCount).variadicOrOptional) {
			return 0; // the missing parameters are optional or variadic
		}
		return -1; // too few
	}

	/** IDE-731 / IDE-770
	 *  Cf. 6.1.14. Additive Expression, Constraint 73
	 */
	@Check
	def checkAdditiveExpressionForNonADDs(AdditiveExpression ae) {

		if (ae.rhs === null || ae.lhs === null) {
			return; // corrupt AST (e.g., while editing)
		}

		if (ae.op !== AdditiveOperator.ADD) {

			// The types of the operands must be subtypes of number if the operator is not ’+’
			val bits = BuiltInTypeScope.get(ae.eResource.resourceSet)

			val tlhs = typeInferencer.tau(ae.lhs)
			if (tlhs === null) {
				return; // corrupt AST (e.g., while editing)
			}
			if (tlhs.declaredType === bits.nullType || tlhs.declaredType === bits.undefinedType)
				issueNotANumberType(tlhs.declaredType.name, ae.lhs);

			val trhs = typeInferencer.tau(ae.rhs)
			if (trhs === null) {
				return; // corrupt AST (e.g., while editing)
			}

			if (trhs.declaredType === bits.nullType || trhs.declaredType === bits.undefinedType)
				issueNotANumberType(trhs.declaredType.name, ae.rhs);

		}

	}

	def issueNotANumberType(String typeString, Expression expression) {
		addIssue(IssueCodes.getMessageForEXP_IS_NOT_A_VALID_NUMBER(typeString), expression,
			IssueCodes.EXP_IS_NOT_A_VALID_NUMBER);
	}

	/**
	 * IDE-731 / IDE-773
	 * Cf. 6.1.17. Equality Expression
	 *
	 * In N4JS mode, a warning is created, if for a given expression lhs(’===’|’!==’) rhs,
	 * neither Γ |- upper(lhs) <: upper(rhs) nor Γ |- upper(rhs) <: upper(lhs), as the result is constant in these cases.
	 */
	@Check
	def checkEqualityExpressionForConstantValues(EqualityExpression ee) {

		if (ee.op === EqualityOperator.SAME || ee.op === EqualityOperator.NSAME) {

			val G = RuleEnvironmentExtensions.newRuleEnvironment(ee)

			var tlhs = ts.type(G, ee.lhs).value
			var trhs = ts.type(G, ee.rhs).value
			if (tlhs === null || trhs === null) return;

			// we are only interested in upper bound here, cf. IDEBUG-260
			tlhs = ts.upperBound(G, tlhs).value
			trhs = ts.upperBound(G, trhs).value

			val leftSubOfRight = ts.subtypeSucceeded(G, tlhs, trhs)
			val rightSubOfLeft = ts.subtypeSucceeded(G, trhs, tlhs)

			val tdLhs = tlhs.computeDeclaredTypeS
			val tdRhs = trhs.computeDeclaredTypeS

			// DEBUGPrint(tdLhs, leftSubOfRight, rightSubOfLeft, tdRhs, tlhs, trhs, ee)
			// Cases for comparison:
			// * both sides with interface/roles --> out, nothing to deduce
			// * one side with interfaces/roles --> check Primitives, Enum, Function,
			// * no side with interfaces --> check for subtype-relationship otherwise issue warning.
			val leftROI = tdLhs.hasInterface
			val rightROI = tdRhs.hasInterface
			if (leftROI && rightROI) {
				// if one side is interface we cannot deduce any constant value.
				return;
			}
			if (leftROI || rightROI) {
				// one side is interface,
				// so only types with no possibility to subclass can be checked.
				// subclasses are allowed for
				if (leftROI) {
					// look at right side:
					if (isExtendable(tdRhs)) {
						// no statement possible.
						return;
					}
				} else {
					// look at left side:
					if (isExtendable(tdLhs)) {
						// no statement possible.
						return;
					}
				}
			}

			if (! (leftSubOfRight || rightSubOfLeft)) {
				// no subtype-relationship found, issue warning:
				addIssue(IssueCodes.getMessageForEXP_WARN_CONSTANT_EQUALITY_TEST(warningNameOf(tdLhs), tdRhs.warningNameOf, ee.op === EqualityOperator::NSAME), ee,
					IssueCodes.EXP_WARN_CONSTANT_EQUALITY_TEST);
			}
		}

	}

	private def boolean isExtendable(Set<Type> types) {
		types.exists[extendable]
	}

	private def boolean isExtendable(Type t) {
		! ( isNotExtendable(t) )
	}

	private def boolean isNotExtendable(Type t) {
		t instanceof PrimitiveType || t instanceof TEnum || t instanceof BuiltInType || t instanceof TFunction
	}

	//	@SuppressWarnings("unused")
	//	private def DEBUGPrint(Set<Type> tdLhs, Boolean leftSubOfRight, Boolean rightSubOfLeft, Set<Type> tdRhs,
	//		TypeRef tlhs, TypeRef trhs, EqualityExpression ee) {
	//		var leftText = NodeModelUtils.getNode(ee.lhs).text.lastLine
	//		var rightText = NodeModelUtils.getNode(ee.rhs).text.lastLine
	//
	//		println(
	//			tdLhs.warningNameOf + (if (leftSubOfRight) " <: " else "") + "   öööö   " +
	//				(if (rightSubOfLeft) " :> " else "") + tdRhs.warningNameOf + "  . . . . . .  " + tlhs.typeRefAsString +
	//				"  äää  " + trhs.typeRefAsString + " << " + leftText + " ??? " + rightText + " >> ")
	//	}
	//	private def String lastLine(String s) {
	//		if (!s.contains('\n')) return s;
	//		'''...''' + s.substring(Math.min(s.lastIndexOf('\n') + 1, s.length))
	//	}
	private def boolean hasInterface(Set<Type> types) {
		types.exists[hasInterface]
	}

	/**
	 * true if type is a subclass of TInterface
	 */
	private def boolean hasInterface(Type type) {
		return switch type {
			TInterface: true
			default: false
		}
	}

	private def String warningNameOf(Set<Type> tset) {
		if (tset.size === 1)
			tset.iterator.next.warningNameOf
		else '''{«FOR s : tset SEPARATOR ','» «s.warningNameOf» «ENDFOR»}'''
	}

	private def String warningNameOf(Type t) {
		val repr = (if (t === null) "<type null>" else t.name) ?: t.toString

		switch t {
			TStructuralType: "'structural type'"
			TFunction: "function " + repr
			default: repr
		}

	}

	private def Set<Type> computeDeclaredTypeS(TypeRef tref) {

		if (tref instanceof ComposedTypeRef) {

			// TODO beware of recursion !
			val retSet = <Type>newTreeSet([a, b|if(a === null) 1 else if(b === null) -1 else {
				Comparator.nullsLast(Comparator.<String>naturalOrder).compare(a?.typeAsString, b?.typeAsString)
			}])
			tref.typeRefs.forEach[retSet.addAll(it.computeDeclaredTypeS)]
			return retSet;
		}
		if (tref instanceof BoundThisTypeRef) {
			return Collections.singleton(tref.actualThisTypeRef.declaredType)
		} else {
			return Collections.singleton(tref.declaredType)
		}

	}

	/**
	 * Checking Constraints 79: <br>
	 * Constraints 79 (Binary Logical Expression Constraints):
	 * For a given binary logical expression e with e.lhs.type : L
	 * and e.rhs.type : R the following constraints must hold:
	 *
	 * <li> In N4JS mode L must not be undefined or null.
	 * IDE-775
	 */
	@Check
	def checkBinaryLogicalExpression(BinaryLogicalExpression e) {

		// wrong parsed
		if (e.lhs === null) {
			return
		}

		val G = newRuleEnvironment(e)

		doCheckForbiddenType(e.lhs, G.nullType, "null")

		doCheckForbiddenType(e.lhs, G.undefinedType, "undefined")
	}

	private def doCheckForbiddenType(Expression e, Type forbidden, String typeName) {
		if (forbidden !== null) {
			val theType = typeInferencer.tau(e)?.declaredType
			if (theType === forbidden) {
				addIssue(
					IssueCodes.getMessageForEXP_FORBIDDEN_TYPE_IN_BINARY_LOGICAL_EXPRESSION(typeName),
					e,
					IssueCodes.EXP_FORBIDDEN_TYPE_IN_BINARY_LOGICAL_EXPRESSION
				);
			}
		}
	}

	/**
	 * Checking Constraint 80: <br>
	 *
	 * In N4JS mode a warning will be issued if e.expression evaluates to a constant value,
	 * that is e.expression in { false, true, null, undefined} or C in {void, undefined}
	 *
	 * IDE-776
	 */
	@Check
	def checkConditionalExpression(ConditionalExpression e) {
		val expressionToCheck = e.expression

		// wrong parsed
		if (expressionToCheck === null) {
			return
		}

		val G = newRuleEnvironment(e)

		val declaredT = typeInferencer.tau(expressionToCheck)?.declaredType

		var ConstBoolean cboolValue = ConstBoolean.NotPrecomputable

		if (declaredT === G.nullType || declaredT === G.voidType || declaredT === G.undefinedType) {

			// False-Way.
			cboolValue = ConstBoolean.CFalse

		} else {
			cboolValue = expressionToCheck.evalConstantBooleanExpression
		}

		// if not Precomputed back out:
		if (cboolValue === ConstBoolean.NotPrecomputable) return;

		var String msg1 = "?!?";
		var String msg2 = "?!?";
		if (cboolValue === ConstBoolean.CTrue) {
			msg1 = "true";
			msg2 = "left-hand";
		} else {
			msg1 = "false";
			msg2 = "right-hand";
		}
		addIssue(
			IssueCodes.getMessageForEXP_WARN_DISPENSABLE_CONDITIONAL_EXPRESSION(
				NodeModelUtils.findActualNodeFor(expressionToCheck).text.trim,
				msg1,
				msg2
			), expressionToCheck, IssueCodes.EXP_WARN_DISPENSABLE_CONDITIONAL_EXPRESSION);

	}

	/**
	 * Checks the Expression to be always constant in evaluation with
	 * ECMA-Script 5.1, 2011, §9.2, p.42  ToBooleanValue(e)
	 */
	private def ConstBoolean evalConstantBooleanExpression(Expression e) {
		if (e instanceof BooleanLiteral) {
			if (e.^true) return ConstBoolean.CTrue else return ConstBoolean.CFalse
		} else if (e instanceof NumericLiteral) {

			//false: +0, -0, or NaN;
			val v = e.value
			if (v == 0) return ConstBoolean.CFalse else return ConstBoolean.CTrue
		} else if (e instanceof IdentifierRef) {
			if (e?.id?.name == "NaN") {

				// It's a Not a Number:
				return ConstBoolean.CFalse
			}
		} else if (e instanceof StringLiteral) {
			if (e.value.empty) return ConstBoolean.CFalse else return ConstBoolean.CTrue
		} else if (e instanceof ObjectLiteral) {

			// Object is always true:
			return ConstBoolean.CTrue
		}

		// some simple simplification could be carried out like Parenthesis, BinaryLogicalExpression, ..... is it worth the hassle?
		// Nothing known?
		return ConstBoolean.NotPrecomputable
	}

	/**
	 *  5.5.1. Type Cast, Constraints 61
	 *  updated with IDE-928 (IDEBUG-56): Casting to TypeVars
	 */
	@Check
	def checkCastExpression(CastExpression castExpression) {
		val Type typeContext = EcoreUtil2.getContainerOfType(castExpression, TypeDefiningElement)?.definedType;
		val context = if (typeContext === null) null else createTypeRef(typeContext);
		val S = typeInferencer.tau(castExpression.expression, context);

		val T = castExpression.targetTypeRef

		// avoid consequential errors
		if (S === null || T === null || T instanceof UnknownTypeRef || S instanceof UnknownTypeRef) return;
		val G = RuleEnvironmentExtensions.newRuleEnvironment(castExpression)

		if (ts.subtypeSucceeded(G, S, T)) { // Constraint 78 (Cast Validation At Compile-Time): 1
			addIssue(IssueCodes.getMessageForEXP_CAST_UNNECESSARY(S.typeRefAsString, T.typeRefAsString),
				castExpression, IssueCodes.EXP_CAST_UNNECESSARY);
		} else {
			if (!(T.declaredType instanceof ContainerType<?>) && !(T.declaredType instanceof TEnum) &&
				!(T.declaredType instanceof TypeVariable) && !(T instanceof FunctionTypeExpression) &&
				!(T instanceof ClassifierTypeRef)) { // Constraint 78 (Cast Validation At Compile-Time): 2
				addIssue(IssueCodes.getMessageForEXP_CAST_INVALID_TARGET(), castExpression,
					IssueCodes.EXP_CAST_INVALID_TARGET);
			} else {
				internalCheckCastExpression(G, S, T, castExpression, true);
			}
		}
	}

	/**
	 * 5.5.1. Type Cast, Constraints 78 (Cast Validation At Compile-Time): 3 and 4
	 */
	private def boolean internalCheckCastExpression(RuleEnvironment G, TypeRef S, TypeRef T, CastExpression castExpression,
		boolean addIssues) {
		if (S instanceof ComposedTypeRef) { // Constraint 78 (Cast Validation At Compile-Time): 5
			if (! S.typeRefs.exists[internalCheckCastExpression(G, it, T, castExpression, false)]) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		} else if (canCheck(S, T)) { // Constraint 78 (Cast Validation At Compile-Time):
			if (!ts.subtypeSucceeded(G, T, S)) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		} else if (T.declaredType instanceof TypeVariable && !(T.declaredType as TypeVariable).declaredUpperBounds.empty) {
			val typeVariable = T.declaredType as TypeVariable
			if (! typeVariable.declaredUpperBounds.forall[internalCheckCastExpression(G, S, it, castExpression, false)]) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		}
		return true;
	}

	private def boolean canCheck(TypeRef S, TypeRef T) {
		return (S.treeHierarchyType && T.treeHierarchyType) || ((S.declaredType instanceof TInterface) && T.actuallyFinal) ||
			(S.actuallyFinal && (T.declaredType instanceof TInterface)) || T instanceof FunctionTypeExpression
	}

	private def boolean isActuallyFinal(TypeRef typeRef) {
		// IDEBUG-310: excluding TypeVariables here, because #isFinalByType() will return true for them but we
		// cannot treat them as "final" because we do not know what kind of type will be substituted for them
		return typeRef.finalByType && !(typeRef.declaredType instanceof TypeVariable);
	}



	/**
	 * 7.1.7. Property Accessors, Constraints 69 (Index Access).
	 *
	 */
	@Check
	def void checkIndexedAccessExpression(IndexedAccessExpression indexedAccess) {
		if (JavaScriptVariant.getVariant(indexedAccess) !== JavaScriptVariant.n4js) {
			return
		}
		if (indexedAccess.target instanceof SuperLiteral) {
			return; // avoid duplicate error messages
		}
		val G = indexedAccess.newRuleEnvironment;
		val receiverTypeRef = ts.type(G, indexedAccess.target).value;
		if (receiverTypeRef === null || receiverTypeRef instanceof UnknownTypeRef) {
			return; // error otherwise or corrupt AST
		}
		val isComputedName = (indexedAccess.index instanceof StringLiteral)
		val accessedBuiltInSymbol = G.getAccessedBuiltInSymbol(indexedAccess.index);
		if (accessedBuiltInSymbol !== null && (
				receiverTypeRef.declaredType instanceof ContainerType<?>
				|| receiverTypeRef instanceof ThisTypeRef)) {
			// we have something like: myObj[Symbol.iterator]
			internalCheckIndexedAccessWithSymbol(G,indexedAccess,receiverTypeRef,accessedBuiltInSymbol);
		} else if (receiverTypeRef.declaredType instanceof TN4Classifier) { // Constraints 69.1
			if (isComputedName) {
				// custom error message for computed-name access
				internalCheckComputedIndexedAccess(indexedAccess, receiverTypeRef)
			} else {
				addIssue(getMessageForEXP_INDEXED_ACCESS_N4CLASSIFIER(receiverTypeRef.declaredType.keyword),
						indexedAccess, EXP_INDEXED_ACCESS_N4CLASSIFIER);
			}
			return
		} else if (receiverTypeRef instanceof EnumTypeRef) { // Constraints 69.2
			addIssue(messageForEXP_INDEXED_ACCESS_ENUM, indexedAccess, EXP_INDEXED_ACCESS_ENUM);
		} else if (receiverTypeRef.dynamic) {
			// allowed: indexing into dynamic receiver
			return
		} else if (#[G.arrayType, G.argumentsType].contains(receiverTypeRef.declaredType)) { // Constraints 69.3
			// allowed: index into array-like provided index is numeric
			val foundIndexType = getInvalidIndexType(G, indexedAccess)
			if (null !== foundIndexType) {
				addIssue(
					getMessageForEXP_INDEXED_ACCESS_ARRAY(receiverTypeRef.declaredType.name, foundIndexType),
					indexedAccess.index,
					EXP_INDEXED_ACCESS_ARRAY
				);
			}
		} else if (ts.subtypeSucceeded(G, receiverTypeRef, G.stringTypeRef) ||
			ts.subtypeSucceeded(G, receiverTypeRef, G.stringObjectTypeRef)) { // Constraints 69.4, IDE-837
			// allowed: index into string-like provided index is numeric
			val foundIndexType = getInvalidIndexType(G, indexedAccess)
			if (null !== foundIndexType) {
				addIssue(
					getMessageForEXP_INDEXED_ACCESS_STRING(receiverTypeRef.declaredType.name, foundIndexType),
					indexedAccess.index,
					EXP_INDEXED_ACCESS_STRING
				);
			}
		} else if (G.objectType === receiverTypeRef.declaredType && !(receiverTypeRef.useSiteStructuralTyping)) {
			// allowed: index into exact-type Object instance (not subtype thereof)
			return
		} else if (isComputedName) {
			internalCheckComputedIndexedAccess(indexedAccess, receiverTypeRef)
			return
		} else {
			addIssue(messageForEXP_INDEXED_ACCESS_FORBIDDEN, indexedAccess, EXP_INDEXED_ACCESS_FORBIDDEN);
		}
	}

	/**
	 * In general computed-names are not allowed as index, unless it denotes a visible member by means of a string-literal.
	 *
	 * @return true if allowed, false otherwise.
	 */
	private def void internalCheckComputedIndexedAccess(IndexedAccessExpression indexedAccess, TypeRef receiverTypeRef) {
		val memberName = (indexedAccess.index as StringLiteral).value;
		if (ComputedPropertyNameValueConverter.SYMBOL_ITERATOR_MANGLED == memberName) {
			// Implementation restriction: member name clashes with compiler-internal, synthetic, mangled name.
			addIssue(getMessageForEXP_INDEXED_ACCESS_IMPL_RESTRICTION(), indexedAccess, EXP_INDEXED_ACCESS_IMPL_RESTRICTION);
			return
		}
		if (receiverTypeRef.dynamic) {
			// allowed: indexing into dynamic receiver, both via dot and indexing notations.
			return
		}
		val checkVisibility = true
		val staticAccess = (receiverTypeRef instanceof ClassifierTypeRef)
		val scope = memberScopingHelper.createMemberScopeFor(receiverTypeRef, indexedAccess, checkVisibility, staticAccess)
		if (memberScopingHelper.isNonExistentMember(scope, memberName, staticAccess)) {
			addIssue(getMessageForEXP_INDEXED_ACCESS_COMPUTED_NOTFOUND(memberName), indexedAccess, EXP_INDEXED_ACCESS_COMPUTED_NOTFOUND);
			return
		}
		val erroneous = memberScopingHelper.getErrorsForMember(scope, memberName, staticAccess)
		erroneous.forEach[d| addIssue(d.message, indexedAccess, d.issueCode) ]
	}

	/**
	 * In case the index-expression can be determined to be non-numeric, return its type; null otherwise.
	 */
	private def String getInvalidIndexType(RuleEnvironment G, IndexedAccessExpression indexedAccess) {
		val indexType = ts.type(G, indexedAccess.index).value
		if (indexType !== null) {
			val rs = ts.subtype(G, indexType, G.numberTypeRef);
			if (rs.failed) {
				return trimTypesystemMessage(rs)
			}
		}
		return null
	}

	def boolean internalCheckIndexedAccessWithSymbol(RuleEnvironment G, IndexedAccessExpression indexedAccess,
			TypeRef receiverTypeRef, TField accessedBuiltInSymbol) {
		// check valid symbol (currently only 'iterator')
		if(accessedBuiltInSymbol !== G.symbolObjectType.ownedMembers.findFirst[name=="iterator"]) {
			addIssue(messageForEXP_INDEXED_ACCESS_SYMBOL_INVALID, indexedAccess, EXP_INDEXED_ACCESS_SYMBOL_INVALID);
			return false;
		}
		// check valid receiver type (currently only for instance of Iterable and immediate(!) instances of Object and dynamic types)
		val isIterable = ts.subtypeSucceeded(G, receiverTypeRef, G.iterableTypeRef(TypeRefsFactory.eINSTANCE.createWildcard));
		val isObjectImmediate = receiverTypeRef.declaredType === G.objectType && receiverTypeRef.typingStrategy===TypingStrategy.NOMINAL;
		val isDynamic = receiverTypeRef.dynamic;
		if(!(isIterable || isObjectImmediate || isDynamic)) {
			addIssue(messageForEXP_INDEXED_ACCESS_SYMBOL_WRONG_TYPE, indexedAccess, EXP_INDEXED_ACCESS_SYMBOL_WRONG_TYPE);
			return false;
		}
		// check valid access (currently read-only, except for immediate(!) instances of Object and dynamic types)
		if(!(isObjectImmediate || isDynamic)) {
			val boolean writeAccess = ExpressionExtensions.isLeftHandSide(indexedAccess);
			if(writeAccess) {
				addIssue(messageForEXP_INDEXED_ACCESS_SYMBOL_READONLY, indexedAccess, EXP_INDEXED_ACCESS_SYMBOL_READONLY);
				return false;
			}
		}
		return true;
	}

	@Check
	def checkAssignmentExpression(AssignmentExpression assExpr) { 
		val lhs = assExpr.lhs;
		// GH-119 imported elements
		holdsWritableIdentifier(lhs)
		&& holdsLefthandsideNotConst(lhs);

		val rhs = assExpr.rhs
		if( rhs instanceof IdentifierRef ){
			val id = rhs.id;
			switch(id) {
				TMethod : { /* nop */}
				TFunction : {
					internalCheckNameRestrictionInMethodBodies(rhs,
						[ String message, EObject source, EStructuralFeature feature, String issueCode |
							addIssue(message,source,feature,issueCode)
						]
					)
				}
			}
		}
	}
	
	/** @return true if nothing was issued  */
	private def boolean holdsLefthandsideNotConst(Expression lhs) {
		
		if( lhs instanceof ParenExpression ) {
			return holdsLefthandsideNotConst( lhs.expression );
		} else if ( lhs instanceof IdentifierRef) {
			return holdsLefthandsideNotConst( lhs );
		} 
		return true;
	}	

	/** @return true if nothing was issued  */
	private def boolean holdsLefthandsideNotConst(IdentifierRef lhs) {
		val id = lhs.id;
		switch(id) {
			VariableDeclaration case id.const:
			{
				addIssue(getMessageForEXP_ASSIGN_CONST_VARIABLE(id.name),lhs,EXP_ASSIGN_CONST_VARIABLE); 
				return false;
			}
			TVariable case id.const:
			{
				addIssue(getMessageForEXP_ASSIGN_CONST_VARIABLE(id.name),lhs,EXP_ASSIGN_CONST_VARIABLE);
				return false;
			}
			TField case !id.writeable:
			{
				// note: this case can happen only when referring to globals in GlobalObject (see file global.n4ts);
				// in all other cases of referencing a field, 'lhs' will be a PropertyAccessExpression (those cases
				// will be handled in class AbstractMemberScope as part of scoping)
				addIssue(getMessageForVIS_WRONG_READ_WRITE_ACCESS("built-in constant", id.name, "read-only"),
						lhs, VIS_WRONG_READ_WRITE_ACCESS);
				return false;
			}
		}
		return true;
	}


	@Check
	def checkPromisify(PromisifyExpression promiExpr) {
		if(!promisifyHelper.isPromisifiableExpression(promiExpr.expression)) {
			addIssue(getMessageForEXP_PROMISIFY_INVALID_USE, promiExpr, EXP_PROMISIFY_INVALID_USE);
		}
	}
}

enum ConstBoolean {
	CTrue,
	CFalse,
	NotPrecomputable
}
