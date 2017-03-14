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
import eu.numberfour.n4js.n4JS.ArrowFunction
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
import eu.numberfour.n4js.n4JS.LiteralOrComputedPropertyName
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4MemberDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.NewExpression
import eu.numberfour.n4js.n4JS.NumericLiteral
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.PostfixExpression
import eu.numberfour.n4js.n4JS.PromisifyExpression
import eu.numberfour.n4js.n4JS.PropertyAssignment
import eu.numberfour.n4js.n4JS.RelationalExpression
import eu.numberfour.n4js.n4JS.RelationalOperator
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.SuperLiteral
import eu.numberfour.n4js.n4JS.ThisArgProvider
import eu.numberfour.n4js.n4JS.ThisLiteral
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.n4JS.UnaryOperator
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.n4JS.extensions.ExpressionExtensions
import eu.numberfour.n4js.postprocessing.ASTMetaInfoCacheHelper
import eu.numberfour.n4js.scoping.accessModifiers.MemberVisibilityChecker
import eu.numberfour.n4js.scoping.accessModifiers.VisibilityAwareCtorScope
import eu.numberfour.n4js.scoping.members.MemberScopingHelper
import eu.numberfour.n4js.scoping.members.TypingStrategyAwareMemberScope
import eu.numberfour.n4js.ts.conversions.ComputedPropertyNameValueConverter
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.ExistentialTypeRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.IntersectionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsFactory
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.typeRefs.UnionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef
import eu.numberfour.n4js.ts.typeRefs.Wildcard
import eu.numberfour.n4js.ts.types.BuiltInType
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.FieldAccessor
import eu.numberfour.n4js.ts.types.MemberAccessModifier
import eu.numberfour.n4js.ts.types.ModuleNamespaceVirtualType
import eu.numberfour.n4js.ts.types.PrimitiveType
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TExportableElement
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TInterface
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TN4Classifier
import eu.numberfour.n4js.ts.types.TObjectPrototype
import eu.numberfour.n4js.ts.types.TSetter
import eu.numberfour.n4js.ts.types.TStructuralType
import eu.numberfour.n4js.ts.types.TVariable
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.types.TypeDefs
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.TypingStrategy
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.utils.CompileTimeEvaluator.UnresolvedPropertyAccessError
import eu.numberfour.n4js.utils.CompileTimeValue
import eu.numberfour.n4js.utils.CompileTimeValue.EvalError
import eu.numberfour.n4js.utils.CompileTimeValue.ValueInvalid
import eu.numberfour.n4js.utils.ContainerTypesHelper
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import eu.numberfour.n4js.utils.PromisifyHelper
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4js.validation.IssueCodes
import eu.numberfour.n4js.validation.JavaScriptVariantHelper
import eu.numberfour.n4js.validation.N4JSElementKeywordProvider
import eu.numberfour.n4js.validation.ValidatorMessageHelper
import eu.numberfour.n4js.validation.helper.N4JSLanguageConstants
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

import static eu.numberfour.n4js.validation.IssueCodes.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

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

	@Inject private JavaScriptVariantHelper jsVariantHelper;

	@Inject private ASTMetaInfoCacheHelper astMetaInfoCacheHelper;

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
		
		if( awaitExpression.getExpression() === null ){
			// broken AST.
			return;
		}

		internalCheckAwaitingAPromise(awaitExpression);
	}

	private def void internalCheckAwaitingAPromise(AwaitExpression awaitExpression) {
		val Expression subExpr = awaitExpression.getExpression();
		if(subExpr===null) {
			return; // broken AST
		}
		val TypeRef typeRef = ts.tau(subExpr, awaitExpression);
		if(typeRef===null) {
			return; // some error (probably broken AST)
		}
		val G = RuleEnvironmentExtensions.newRuleEnvironment(awaitExpression);
		val scope = G.predefinedTypes.builtInTypeScope;
		val einst = TypeRefsFactory.eINSTANCE;

		val union1 = einst.createUnionTypeExpression;
		union1.typeRefs.add(scope.getVoidTypeRef());
		union1.typeRefs.add(scope.getAnyTypeRef());
		val union2 = einst.createUnionTypeExpression;
		union2.typeRefs.add(scope.getVoidTypeRef());
		union2.typeRefs.add(scope.getAnyTypeRef());
		val promUni = TypeUtils.createPromiseTypeRef(scope, union1, union2);
		val boolean stUnions = ts.subtypeSucceeded(G, typeRef, promUni);

		val boolean isUndef = typeRef.declaredType == G.undefinedType;
		val boolean isNull = typeRef.declaredType == G.nullType;
		if (!stUnions) {
			val message = IssueCodes.getMessageForEXP_AWAIT_NON_ASYNC();
			addIssue(message, awaitExpression, IssueCodes.EXP_AWAIT_NON_ASYNC);
		}
		if (isUndef || isNull) {
			val message = IssueCodes.getMessageForEXP_AWAIT_NON_ASYNC_SPECIAL(typeRef.declaredType.name);
			addIssue(message, awaitExpression, IssueCodes.EXP_AWAIT_NON_ASYNC_SPECIAL);
		}
	}

	@Check
	def void checkPropertyAccesssExpression(ParameterizedPropertyAccessExpression propAccessExpression) {
		if (propAccessExpression?.target === null || propAccessExpression.property === null)
			return; // invalid AST

		// check type arguments
		val prop = propAccessExpression.property;
		val typeVars = if (prop instanceof Type) prop.typeVars else #[]; // else-case required for TField, TGetter, TSetter
		internalCheckTypeArguments(typeVars, propAccessExpression.typeArgs, true, prop, propAccessExpression,
			N4JSPackage.eINSTANCE.parameterizedPropertyAccessExpression_Property);

		internalCheckTargetSubtypeOfDeclaredThisType(propAccessExpression);

		// check methods aren't assigned to variables or parameters
		internalCheckMethodReference(propAccessExpression)

		// check access to static members of interfaces
		internalCheckAccessToStaticMemberOfInterface(propAccessExpression)
	}

	def private void internalCheckTargetSubtypeOfDeclaredThisType(ParameterizedPropertyAccessExpression propAccessExpr) {
		val prop = propAccessExpr.property;
		if(prop.eIsProxy) {
			return; // unresolved reference
		}

		val declThisTypeRef = switch(prop) {
			TMethod: prop.declaredThisType
			FieldAccessor: prop.declaredThisType
		};

		if(declThisTypeRef!==null) {
			val G = propAccessExpr.newRuleEnvironment;
			val targetTypeRef = ts.type(G, propAccessExpr.target).value;
			if(targetTypeRef!==null) {
				if(!ts.subtypeSucceeded(G, targetTypeRef, declThisTypeRef)) {
					val msg = IssueCodes.getMessageForEXP_ACCESS_INVALID_TYPE_OF_TARGET(prop.description,
						targetTypeRef.typeRefAsString, declThisTypeRef.typeRefAsString);
					addIssue(msg, propAccessExpr, N4JSPackage.eINSTANCE.parameterizedPropertyAccessExpression_Property,
						IssueCodes.TYS_NO_SUBTYPE);
				}
			}
		}
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
	 * @see N4JSSpec, 5.2.1
	 * 
	 */
	private def void internalCheckMethodReference(ParameterizedPropertyAccessExpression propAccessExpression) {
		if (!jsVariantHelper.checkMethodReference(propAccessExpression)) {
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
		
		if ( !method.static && "constructor"==method.name ) {
			return; // constructor cannot be detached, cf. GH-224
		}
		
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

	private def boolean isMethodEffectivelyFinal(TMethod method) {
		if (method.isFinal || method.getMemberAccessModifier() == MemberAccessModifier.PRIVATE) {
			return true;
		}
		val containingType = method.containingType;
		// If the containing type is final all its method are assumed final too
		// Attention: containing type may be a UnionUypeExpression and thus the "containingType" method will return null
		if (containingType!==null && containingType.isFinal) {
			return true; 
		}
		return false;
	}

	/**
	 * Static members of interfaces may only be accessed directly via the type name of the containing interface.
	 * This is required, because there is no inheritance of static members of interfaces.
	 */
	private def void internalCheckAccessToStaticMemberOfInterface(
		ParameterizedPropertyAccessExpression propAccessExpr) {
		val prop = propAccessExpr.property;
		if (prop instanceof TMember) {
			if (prop !== null && prop.static && prop.eContainer instanceof TInterface) {
				val target = propAccessExpr.target;
				val targetIdRef = if (target instanceof IdentifierRef) target else null;
				val isExceptionCase = target instanceof ThisLiteral; // avoid duplicate error messages
				if (targetIdRef?.id !== prop.eContainer && !isExceptionCase) {
					val message = IssueCodes.getMessageForCLF_INVALID_ACCESS_OF_STATIC_MEMBER_OF_INTERFACE;
					addIssue(message, propAccessExpr,
						N4JSPackage.eINSTANCE.parameterizedPropertyAccessExpression_Target,
						IssueCodes.CLF_INVALID_ACCESS_OF_STATIC_MEMBER_OF_INTERFACE);
				}
			}
		}
	}

	@Check
	def checkCallExpression(ParameterizedCallExpression callExpression) {
		if (!jsVariantHelper.checkCallExpression(callExpression)) {
			return; // cf. 13.1
		}
		if (callExpression?.target === null)
			return; // invalid AST
		val typeRef = ts.tau(callExpression.target);
		if (typeRef === null)
			return; // invalid AST
		if (typeRef instanceof UnknownTypeRef)
			return; // suppress error message in case of UnknownTypeRef
		// make sure target can be invoked
		val G = callExpression.newRuleEnvironment;
		if (!(callExpression.target instanceof SuperLiteral) && !tsh.isCallable(G, typeRef)) {
			if (tsh.isClassConstructorFunction(G, typeRef) || isClassifierTypeRefToAbstractClass(G, typeRef)) {
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
			switch (trgt) {
				IdentifierRef: {
					internalCheckNameRestrictionInMethodBodies(
						trgt,
						[ String message, EObject source, EStructuralFeature feature, String issueCode |
							addIssue(message, source, feature, issueCode)
						]
					)
				}
			}
		}
	}

	def private boolean isClassifierTypeRefToAbstractClass(RuleEnvironment G, TypeRef typeRef) {
		if (typeRef instanceof TypeTypeRef) {
			val staticType = tsh.getStaticType(G, typeRef);
			if (staticType instanceof TClass) {
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
	def internalCheckCallingAsyncFunWithoutAwaitingForIt(FunctionTypeExprOrRef fteor,
		ParameterizedCallExpression callExpression) {
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
		val isPromiseExplict = if (container instanceof VariableDeclaration) {
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
	private def boolean isArgumentToPromiseUtilityMethod(ParameterizedCallExpression asyncInvocation,
		EObject container, RuleEnvironment G) {
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
					val isDirectArg = utilityCall.arguments.exists[arg|arg.expression === asyncInvocation]
					if (isDirectArg) {
						return true
					}
					val name = utilityAccess.property.name
					if (isArrayElem && (name == 'all' || name == 'race')) {
						// let's see if 'callExpression' occurs as arg in 'Promise.{all/race}([...,asyncInvocation,...])'
						val argOccursInArray = utilityCall.arguments.exists [arg|
							arg.expression === container.eContainer
						]
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
	private def boolean isPromiseUtilityPropertyAccess(ParameterizedPropertyAccessExpression utilityAccess,
		RuleEnvironment G) {
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
					if (tr instanceof TypeTypeRef) {
						val str = tr.getTypeArg
						val isReceiverPromise = if (str instanceof TypeRef) TypeUtils.isPromise(str, tscope) else false;
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
	def static void internalCheckNameRestrictionInMethodBodies(IdentifierRef trgt,
		(String, EObject, EStructuralFeature, String)=>void g) {
		if (trgt.id instanceof TFunction && !(trgt.id instanceof TMethod)) {
			if (trgt.id.name.endsWith(N4JSLanguageConstants.METHOD_STACKTRACE_SUFFIX)) {
				// Find container:
				val containingMethod = EcoreUtil2.getContainerOfType(trgt, N4MethodDeclaration)
				if (containingMethod !== null) {
					// add issue:
					val msg = IssueCodes.messageForCLF_METHOD_BODY_FORBIDDEN_REFERENCE_NAME
					// wrapped in g:
					// addIssue(msg, trgt, N4JSPackage.eINSTANCE.identifierRef_Id, IssueCodes.CLF_METHOD_BODY_FORBIDDEN_REFERENCE_NAME )
					g.apply(msg, trgt, N4JSPackage.eINSTANCE.identifierRef_Id,
						IssueCodes.CLF_METHOD_BODY_FORBIDDEN_REFERENCE_NAME)
				}
			}
		}
	}

	@Check
	def checkNew(NewExpression newExpression) {
		if (!jsVariantHelper.requireCheckNewExpression(newExpression)) {
			return; // cf. 13.1
		}
		if (newExpression?.callee === null)
			return; // invalid AST
		val callee = newExpression.callee;
		val typeRef = ts.tau(callee);
		if (typeRef === null)
			return; // invalid AST
		if (typeRef instanceof UnknownTypeRef)
			return; // suppress error message in case of UnknownTypeRef

		// not even a TypeTypeRef?
		if (! (typeRef instanceof TypeTypeRef)) {
			issueNotACtor(typeRef, newExpression);
			return;
		}
		// at least we have a TypeTypeRef, but there are still many cases in which
		// 'new' is not allowed --> check for those error cases now, showing more specific error messages ...
		val G = newExpression.newRuleEnvironment;
		val TypeTypeRef classifierTypeRef = typeRef as TypeTypeRef;
		val typeArg = classifierTypeRef.typeArg;
		val staticType = tsh.getStaticType(G, classifierTypeRef).changeToCovariantUpperBoundIfTypeVar;
		if (staticType !== null && staticType.eIsProxy) {
			return;
		}
		val isCtor = classifierTypeRef.isConstructorRef;
		val isDirectRef = callee instanceof IdentifierRef && (callee as IdentifierRef).id === staticType;
		val isConcreteOrCovariant =
			!(typeArg instanceof Wildcard || typeArg instanceof ExistentialTypeRef || typeArg instanceof ThisTypeRef)
			|| (staticType instanceof TClassifier && N4JSLanguageUtils.hasCovariantConstructor(staticType as TClassifier));
		if (staticType === G.symbolObjectType) {
			// error case #1: new Symbol()
			val message = IssueCodes.messageForBIT_SYMBOL_NOT_A_CTOR;
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.BIT_SYMBOL_NOT_A_CTOR);
			return;
		} else if (!isCtor && staticType instanceof TInterface && isDirectRef) {
			// error case #2: trying to instantiate an interface
			val message = IssueCodes.
				getMessageForEXP_NEW_CANNOT_INSTANTIATE(staticType.keyword, staticType.name);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_CANNOT_INSTANTIATE);
			return;
		} else if (!isCtor && staticType instanceof TClass && (staticType as TClass).abstract && isDirectRef) {
			// error case #3: trying to instantiate an abstract class
			val message = IssueCodes.getMessageForEXP_NEW_CANNOT_INSTANTIATE("abstract class", staticType.name);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_CANNOT_INSTANTIATE);
			return;
		} else if (isCtor && !isConcreteOrCovariant && staticType instanceof TClassifier) {
			// error case #4: trying to instantiate "constructor{? extends C}", with C not having @CovariantConstructor
			val message = IssueCodes.getMessageForEXP_NEW_WILDCARD_NO_COVARIANT_CTOR(typeArg.typeRefAsString, staticType.typeAsString);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_WILDCARD_NO_COVARIANT_CTOR);
			return;
		} else if (staticType instanceof TEnum) {
			// error case #5: trying to instantiate an enum
			val message = IssueCodes.getMessageForEXP_NEW_CANNOT_INSTANTIATE("enum", staticType.name);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_CANNOT_INSTANTIATE);
			return;
		} else if (staticType instanceof TypeVariable ) {
			// error case #6: trying to instantiate a type variable
			val message = IssueCodes.getMessageForEXP_NEW_CANNOT_INSTANTIATE("type variable", staticType.name);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_CANNOT_INSTANTIATE);
			return;
		} else if (staticType === null || !isCtor || !isConcreteOrCovariant) {
			// remaining cases
			val name = classifierTypeRef.typeRefAsString;
			val message = IssueCodes.getMessageForEXP_NEW_WILDCARD_OR_TYPEVAR(name);
			addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee,
				IssueCodes.EXP_NEW_WILDCARD_OR_TYPEVAR);
			return;
		}

		// success case; but perform some further checks
		val TypeTypeRef ctorTypeRef = typeRef as TypeTypeRef;
		internalCheckCtorVisibility(newExpression, ctorTypeRef, staticType)
		internalCheckTypeArguments(staticType.typeVars, newExpression.typeArgs, false, staticType, newExpression,
			N4JSPackage.eINSTANCE.newExpression_Callee);
		internalCheckNewParameters(newExpression, staticType as TClassifier);
	}

	private def Type changeToCovariantUpperBoundIfTypeVar(Type type) {
		if(type instanceof TypeVariable) {
			val ub = type.getDeclaredUpperBound();
			if(ub instanceof ParameterizedTypeRef) {
				val declType = ub.declaredType;
				if(declType instanceof TClassifier) {
					// but only if declType has a covariant constructor:
					if(N4JSLanguageUtils.hasCovariantConstructor(declType)) {
						return declType;
					}
				}
			}
		};
		return type;
	}

	/** Helper to issue the error case of having a new-expression on a non-constructor element */
	private def issueNotACtor(TypeRef typeRef, NewExpression newExpression) {
		val message = IssueCodes.getMessageForEXP_NEW_NOT_A_CTOR(typeRef.typeRefAsString);
		addIssue(message, newExpression, N4JSPackage.eINSTANCE.newExpression_Callee, IssueCodes.EXP_NEW_NOT_A_CTOR)
	}

	/**
	 * Checks visibility of the cTor.
	 * Cf. Spec: "Table 3.2.: Member Access Control"
	 */
	def internalCheckCtorVisibility(NewExpression expression, TypeTypeRef ref, Type staticType) {

		if (staticType instanceof TypeVariable) {
			/* cannot check, back out */
			// TODO is it possible to create an accessibility-check here ?
			return;
		}

		val Type ctorClassifier = staticType
		if (ctorClassifier instanceof TClassifier) {

			val usedCtor = containerTypesHelper.fromContext(expression).findConstructor(ctorClassifier)

			if (usedCtor === null) {
				// case of broken AST / Typesystem
				return;
			}

			val memberScope = memberScopingHelper.createMemberScopeFor(TypeUtils.createTypeRef(ctorClassifier),
				expression, false, false); // always non-static
			val vacs = new VisibilityAwareCtorScope(memberScope, memberVisibilityChecker, ref, staticType, expression);
			val scope = new TypingStrategyAwareMemberScope(vacs, ref, expression);

			val ele = scope.getSingleElement(usedCtor)
			if (IEObjectDescriptionWithError.isErrorDescription(ele)) {
				val errDescr = IEObjectDescriptionWithError.getDescriptionWithError(ele)
				addIssue(errDescr.message, expression, N4JSPackage.eINSTANCE.newExpression_Callee,
					errDescr.issueCode)
			}
		}
	}

	/**
	 * Checks instanceof in combination with structural typing, other checks see
	 * eu.numberfour.n4js.xsemantics.N4JSTypeSystem.expectedTypeInRelationalExpression
	 */
	@Check
	def checkRelationalExpression(RelationalExpression relationalExpression) {
		if (relationalExpression.rhs !== null && relationalExpression.op === RelationalOperator.INSTANCEOF) {
			val typeRef = ts.tau(relationalExpression.rhs)
			if (typeRef instanceof TypeTypeRef) {
				val G = relationalExpression.newRuleEnvironment;
				val staticType = tsh.getStaticType(G, typeRef);
				if (staticType instanceof TN4Classifier) {
					if (staticType.typingStrategy !== TypingStrategy.DEFAULT) {
						val message = IssueCodes.
							getMessageForTYS_INSTANCEOF_NOT_SUPPORTED_FOR_STRUCTURAL_TYPES(staticType.name);
						addIssue(message, relationalExpression, N4JSPackage.eINSTANCE.relationalExpression_Rhs,
							IssueCodes.TYS_INSTANCEOF_NOT_SUPPORTED_FOR_STRUCTURAL_TYPES);
					} else if (staticType instanceof TInterface &&
						EcoreUtil.getRootContainer(staticType) instanceof TypeDefs) {
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
	 *     Getter is bound to the property-field in PropertyAccessExpression, the existence of a setter needs to be validated.
	 */
	@Check
	def checkPostfixExpression(PostfixExpression postfixExpression) {

		val expression = postfixExpression.expression;
		holdsWritabelePropertyAccess(expression) && holdsWritableIdentifier(expression) &&
			holdsLefthandsideNotConst(expression);
	}

	/** IDE-731 / IDE-768 unary expressions of type ++ or -- need both of getter/setter.
	 * Cf. Constraint 69
	 */
	@Check
	def checkUnaryExpressionWithWriteAccess(UnaryExpression unaryExpression) {
		if (UnaryOperator.DEC === unaryExpression.op || UnaryOperator.INC === unaryExpression.op) {
			holdsWritabelePropertyAccess(unaryExpression.expression) &&
				holdsWritableIdentifier(unaryExpression.expression) &&
				holdsLefthandsideNotConst(unaryExpression.expression);
		}
	}

	private def boolean holdsWritabelePropertyAccess(Expression expression) {
		if (expression instanceof ParameterizedPropertyAccessExpression) {
			val property = expression.property
			if (property instanceof TGetter) {

				// access through getter --> a matching setter is required:
				val propertyTargetType = ts.tau(expression.target)
				val declaredType = propertyTargetType?.declaredType
				if (declaredType instanceof TClassifier) {
					val setterExists = containerTypesHelper.fromContext(expression).members(declaredType).filter(
						TSetter).exists[name.equals(property.name)]
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
		if (expression instanceof IdentifierRef) {
			val id = expression.id;
			switch (id) {
				TExportableElement /* includes TClass, TVariable */ : {
					val module = EcoreUtil2.getContainerOfType(expression, Script).module;
					if (id.containingModule != module) {
						// imported variable, class, etc.
						addIssue(IssueCodes.getMessageForIMP_IMPORTED_ELEMENT_READ_ONLY(expression.idAsText),
							expression, IssueCodes.IMP_IMPORTED_ELEMENT_READ_ONLY);
						return false;
					}
				}
			}
		} else if (expression instanceof ParenExpression) {
			// resolve parent-expressions wrapping simple identifiers:
			return holdsWritableIdentifier(expression.expression);
		} else if (expression instanceof ParameterizedPropertyAccessExpression) {
			val target = expression.target;
			// guard against broken models:
			if (expression.property !== null && !expression.property.eIsProxy) {
				if (target instanceof IdentifierRef) {
					val id = target.id;
					// handle namespace imports:
					if (id instanceof ModuleNamespaceVirtualType) {
						if (id.module != EcoreUtil2.getContainerOfType(expression, Script).module) {
							// naive approach for reporting : "target.idAsText+"."+expression.property.name;" results
							// in revealing the name of the default-exported element, but the user can only see 'default' in the validated file
							// so we pick the actual written expression for the error-message generation from the AST:
							val importedElmentText = NodeModelUtils.getTokenText(
								NodeModelUtils.findActualNodeFor(expression));

							addIssue(IssueCodes.getMessageForIMP_IMPORTED_ELEMENT_READ_ONLY(importedElmentText),
								expression, IssueCodes.IMP_IMPORTED_ELEMENT_READ_ONLY);
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
		if (!jsVariantHelper.checkCallExpression(callExpression)) {
			return; // cf. 13.1
		}

		val target = callExpression.target
		if (target !== null) {
			val targetTypeRef = ts.tau(target); // no context, we only need the number of fpars
			if (targetTypeRef instanceof FunctionTypeExprOrRef) {

				// obtain fpars from invoked function/method
				var fpars = new ArrayList(targetTypeRef.fpars);

				// special case: invoking a promisified function
				// note: being very liberal in next lines to avoid duplicate error messages
				val parent = callExpression.eContainer;
				val isPromisified = (parent instanceof AwaitExpression &&
					promisifyHelper.isAutoPromisify(parent as AwaitExpression)) ||
					parent instanceof PromisifyExpression;
				if (isPromisified) {
					fpars.remove(fpars.size - 1);
				}

				// check for correct number of arguments
				internalCheckNumberOfArguments(fpars, callExpression.arguments, callExpression);
			}
		}
	}

	def private internalCheckNewParameters(NewExpression newExpression, TClassifier staticType) {
		val maybeConstructor = containerTypesHelper.fromContext(newExpression).findConstructor(staticType);
		if (maybeConstructor !== null) {
			internalCheckNumberOfArguments((maybeConstructor as TFunction).fpars, newExpression.arguments,
				newExpression)
			return;
		}
	}

	def private void internalCheckNumberOfArguments(List<TFormalParameter> fpars, List<Argument> args,
		Expression expr) {
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

			val tlhs = ts.tau(ae.lhs)
			if (tlhs === null) {
				return; // corrupt AST (e.g., while editing)
			}
			if (tlhs.declaredType === bits.nullType || tlhs.declaredType === bits.undefinedType)
				issueNotANumberType(tlhs.declaredType.name, ae.lhs);

			val trhs = ts.tau(ae.rhs)
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
				addIssue(
					IssueCodes.
						getMessageForEXP_WARN_CONSTANT_EQUALITY_TEST(warningNameOf(tdLhs), tdRhs.warningNameOf,
							ee.op === EqualityOperator::NSAME), ee, IssueCodes.EXP_WARN_CONSTANT_EQUALITY_TEST);
			}
		}

	}

	private def boolean isExtendable(Set<Type> types) {
		types.exists[extendable]
	}

	private def boolean isExtendable(Type t) {
		!(isNotExtendable(t) )
	}

	private def boolean isNotExtendable(Type t) {
		t instanceof PrimitiveType || t instanceof TEnum || t instanceof BuiltInType || t instanceof TFunction
	}

// @SuppressWarnings("unused")
// private def DEBUGPrint(Set<Type> tdLhs, Boolean leftSubOfRight, Boolean rightSubOfLeft, Set<Type> tdRhs,
// TypeRef tlhs, TypeRef trhs, EqualityExpression ee) {
// var leftText = NodeModelUtils.getNode(ee.lhs).text.lastLine
// var rightText = NodeModelUtils.getNode(ee.rhs).text.lastLine
//
// println(
// tdLhs.warningNameOf + (if (leftSubOfRight) " <: " else "") + "   öööö   " +
// (if (rightSubOfLeft) " :> " else "") + tdRhs.warningNameOf + "  . . . . . .  " + tlhs.typeRefAsString +
// "  äää  " + trhs.typeRefAsString + " << " + leftText + " ??? " + rightText + " >> ")
// }
// private def String lastLine(String s) {
// if (!s.contains('\n')) return s;
// '''...''' + s.substring(Math.min(s.lastIndexOf('\n') + 1, s.length))
// }
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
			val retSet = <Type>newTreeSet([a, b|
				if (a === null)
					1
				else if (b === null)
					-1
				else {
					Comparator.nullsLast(Comparator.<String>naturalOrder).compare(a?.typeAsString,
						b?.typeAsString)
				}
			])
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
			val theType = ts.tau(e)?.declaredType
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

		val declaredT = ts.tau(expressionToCheck)?.declaredType

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
		addIssue(IssueCodes.getMessageForEXP_WARN_DISPENSABLE_CONDITIONAL_EXPRESSION(
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

			// false: +0, -0, or NaN;
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
		val S = ts.tau(castExpression.expression, castExpression);
		val T = castExpression.targetTypeRef

		// avoid consequential errors
		if (S === null || T === null || T instanceof UnknownTypeRef || S instanceof UnknownTypeRef) return;
		val G = RuleEnvironmentExtensions.newRuleEnvironment(castExpression)

		if (ts.subtypeSucceeded(G, S, T)) { // Constraint 81.2 (Cast Validation At Compile-Time): 1
			addIssue(IssueCodes.getMessageForEXP_CAST_UNNECESSARY(S.typeRefAsString, T.typeRefAsString),
				castExpression, IssueCodes.EXP_CAST_UNNECESSARY);
		} else {
			if (!(T.declaredType instanceof ContainerType<?>) && !(T.declaredType instanceof TEnum) &&
				!(T.declaredType instanceof TypeVariable) && !(T instanceof FunctionTypeExpression) &&
				!(T instanceof TypeTypeRef) && !(T instanceof UnionTypeExpression) &&
				!(T instanceof IntersectionTypeExpression)
		) { // Constraint 78 (Cast Validation At Compile-Time): 2
					addIssue(IssueCodes.getMessageForEXP_CAST_INVALID_TARGET(), castExpression,
						IssueCodes.EXP_CAST_INVALID_TARGET);
				} else {
					internalCheckCastExpression(G, S, T, castExpression, true, false);
				}
		}
	}

	/**
	 * 5.5.1. Type Cast, Constraints 78 (Cast Validation At Compile-Time): 3 and 4
	 */
	private def boolean internalCheckCastExpression(RuleEnvironment G, TypeRef S, TypeRef T,
		CastExpression castExpression, boolean addIssues, boolean actualSourceTypeIsCPOE) {
		if (T instanceof UnionTypeExpression) {
			if (! T.typeRefs.exists [
				internalCheckCastExpression(G, S, it, castExpression, false, actualSourceTypeIsCPOE)
			]) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		} else if (T instanceof IntersectionTypeExpression) {
			if (! T.typeRefs.forall [
				internalCheckCastExpression(G, S, it, castExpression, false, actualSourceTypeIsCPOE)
			]) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		} else if (S instanceof ComposedTypeRef) { // Constraint 78 (Cast Validation At Compile-Time): 5
			if (! S.typeRefs.exists [
				internalCheckCastExpression(G, it, T, castExpression, false,
					actualSourceTypeIsCPOE || (S instanceof IntersectionTypeExpression && S.typeRefs.exists [
						isCPOE(G, it)
					]))
			] && ! S.typeRefs.exists[ts.subtypeSucceeded(G, it, T)] // one type in composed is a subtype of target
			) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		} else if (canCheck(G, S, T, actualSourceTypeIsCPOE)) { // Constraint 81.3 (Cast Validation At Compile-Time):
			var castOK = ts.subtypeSucceeded(G, T, S);
			if (! castOK && (T instanceof ParameterizedTypeRef && S instanceof ParameterizedTypeRef)) {
				val ptrT = T as ParameterizedTypeRef;
				val ptrS = S as ParameterizedTypeRef;
				if (ptrS.declaredType == ptrT.declaredType) {
					val to = ptrS.typeArgs.size;
					if (to === ptrT.typeArgs.size) {
						var int i = 0;
						while (i < to && (
								
							ts.subtypeSucceeded(G, ptrT.typeArgs.get(i), ptrS.typeArgs.get(i))
							)
							) {
							i++;
						}
						if (i == to) {
							castOK = true;
						} else {
							i = 0;
							while (i < to &&
								ts.subtypeSucceeded(G, ptrS.typeArgs.get(i), ptrT.typeArgs.get(i))) {
								i++;
							}
							castOK = i == to;
						}

					}
				}
			}
			if (!castOK) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		} else if (T.declaredType instanceof TypeVariable &&
			(T.declaredType as TypeVariable).declaredUpperBound!==null) {
			val typeVariable = T.declaredType as TypeVariable
			if (!internalCheckCastExpression(G, S, typeVariable.declaredUpperBound, castExpression, false,
				actualSourceTypeIsCPOE
			)) {
				if (addIssues) {
					addIssue(IssueCodes.getMessageForEXP_CAST_FAILED(S.typeRefAsString, T.typeRefAsString),
						castExpression, IssueCodes.EXP_CAST_FAILED);
				}
				return false;
			}
		}
		return true;
	}

	/**
	 * @param in case of an intersection type, S may be part of an intersection in which another element is a CPOE, i.e. concrete
	 */
	private def boolean canCheck(RuleEnvironment G, TypeRef S, TypeRef T, boolean actualSourceTypeIsCPOE) {
		return ((isCPOE(G, S) || actualSourceTypeIsCPOE) && isCPOE(G, T)) ||
			( (S.declaredType instanceof TInterface) && T.actuallyFinal) ||
			(S.actuallyFinal && (T.declaredType instanceof TInterface)) ||
			(S instanceof ParameterizedTypeRef && T instanceof ParameterizedTypeRef &&
				TypeUtils.isRawSuperType(T.declaredType, S.declaredType)) ||
			T instanceof FunctionTypeExpression;
	}

	private def boolean isCPOE(RuleEnvironment G, TypeRef T) {
		if (T instanceof ParameterizedTypeRef) {
			val d = T.declaredType;
			return d instanceof TClass || d instanceof TEnum || d instanceof PrimitiveType ||
				d instanceof TObjectPrototype;
		};
		if (T instanceof TypeTypeRef) {
			val d = tsh.getStaticType(G, T);
			val concreteMetaType = d instanceof TClass || d instanceof TEnum ||
				d instanceof PrimitiveType || d instanceof TObjectPrototype;
			return concreteMetaType;
		}
		return false;

	}

	private def boolean isActuallyFinal(TypeRef typeRef) {
		// IDEBUG-310: excluding TypeVariables here, because #isFinalByType() will return true for them but we
		// cannot treat them as "final" because we do not know what kind of type will be substituted for them
		return typeRef.finalByType && !(typeRef.declaredType instanceof TypeVariable);
	}

	/**
	 * 7.1.7. Property Accessors, Constraints 69 (Index Access).
	 */
	@Check
	def void checkIndexedAccessExpression(IndexedAccessExpression indexedAccess) {
		if (!jsVariantHelper.requireCheckIndexedAccessExpression(indexedAccess)) {
			return
		}

		// prepare target and index
		val target = indexedAccess.target;
		val index = indexedAccess.index;
		if (target===null || index===null) {
			return; // broken AST
		}
		if (index instanceof IdentifierRef) {
			if(index.id===null || index.id.eIsProxy) {
				return; // avoid duplicate error messages
			}
		}
		if (index instanceof ParameterizedPropertyAccessExpression) {
			if(index.property===null || index.property.eIsProxy) {
				return; // avoid duplicate error messages
			}
		}
		if (target instanceof SuperLiteral) {
			return; // avoid duplicate error messages
		}

		// prepare types of target and index
		val G = indexedAccess.newRuleEnvironment;
		val targetTypeRefRaw = ts.type(G, target).value;
		if (targetTypeRefRaw === null || targetTypeRefRaw instanceof UnknownTypeRef) {
			return; // saw an UnknownTypeRef -> so we are expected to suppress all follow-up errors
		}
		val targetTypeRef = ts.resolveType(G, targetTypeRefRaw);
		val indexTypeRef = ts.type(G, index).value;
		if (indexTypeRef === null || indexTypeRef instanceof UnknownTypeRef) {
			return; // saw an UnknownTypeRef -> so we are expected to suppress all follow-up errors
		}

		// prepare some information about the particular form of index access we have
		val accessedBuiltInSymbol = N4JSLanguageUtils.getAccessedBuiltInSymbol(G, index);
		val accessedStaticType = if(targetTypeRef instanceof TypeTypeRef) tsh.getStaticType(G, targetTypeRef);
		val indexIsNumeric = ts.subtypeSucceeded(G, indexTypeRef, G.numberTypeRef);
		val indexValue = astMetaInfoCacheHelper.getCompileTimeValue(index);

		// create issues depending on the collected information
		if (targetTypeRef.dynamic) {
			// allowed: indexing into dynamic receiver
		} else if (G.objectType === targetTypeRef.declaredType && !(targetTypeRef.useSiteStructuralTyping)) {
			// allowed: index into exact-type Object instance (not subtype thereof)
		} else if (accessedStaticType instanceof TEnum) { // Constraints 69.2
			// disallowed: index access into an enum
			addIssue(messageForEXP_INDEXED_ACCESS_ENUM, indexedAccess, EXP_INDEXED_ACCESS_ENUM);
		} else if (indexIsNumeric && targetTypeRef.isArrayLike) { // Constraints 69.3
			// allowed: index into array-like with a numeric index
		} else if (accessedBuiltInSymbol !== null) {
			// we have something like: myObj[Symbol.iterator]
			// -> delegate to special method
			internalCheckIndexedAccessWithSymbol(G, indexedAccess, targetTypeRef, accessedBuiltInSymbol);
		} else {
			// all other cases:
			// treat this as an ordinary member access where the member name is given as a compile-time expression
			if (indexValue.valid) {
				internalCheckComputedIndexedAccess(G, indexedAccess, targetTypeRef, indexValue, indexIsNumeric)
			} else {
				createIssuesForEvalErrors((indexValue as ValueInvalid).errors);
			}
		}
	}

	/**
	 * In general computed-names are not allowed as index, unless it denotes a visible member by means of a string-literal.
	 * 
	 * @return true if allowed, false otherwise.
	 */
	def private void internalCheckComputedIndexedAccess(RuleEnvironment G, IndexedAccessExpression indexedAccess,
		TypeRef receiverTypeRef, CompileTimeValue indexValue, boolean indexIsNumeric) {

		val memberName = N4JSLanguageUtils.derivePropertyNameFromCompileTimeValue(indexValue);
		if (ComputedPropertyNameValueConverter.SYMBOL_ITERATOR_MANGLED == memberName) {
			// Implementation restriction: member name clashes with compiler-internal, synthetic, mangled name.
			addIssue(getMessageForEXP_INDEXED_ACCESS_IMPL_RESTRICTION(), indexedAccess,
				EXP_INDEXED_ACCESS_IMPL_RESTRICTION);
			return
		}
		if (receiverTypeRef.dynamic) {
			// allowed: indexing into dynamic receiver, both via dot and indexing notations.
			return
		}
		val checkVisibility = true
		val staticAccess = (receiverTypeRef instanceof TypeTypeRef)
		val scope = memberScopingHelper.createMemberScopeFor(receiverTypeRef, indexedAccess,
			checkVisibility, staticAccess)
		if (memberScopingHelper.isNonExistentMember(scope, memberName, staticAccess)) {
			if (indexIsNumeric) {
				addIssue(messageForEXP_INDEXED_ACCESS_FORBIDDEN, indexedAccess, EXP_INDEXED_ACCESS_FORBIDDEN);
			} else {
				addIssue(getMessageForEXP_INDEXED_ACCESS_COMPUTED_NOTFOUND(memberName), indexedAccess,
					EXP_INDEXED_ACCESS_COMPUTED_NOTFOUND);
			}
			return
		}
		val erroneous = memberScopingHelper.getErrorsForMember(scope, memberName, staticAccess)
		erroneous.forEach[d|addIssue(d.message, indexedAccess, d.issueCode)]
	}

	def private boolean internalCheckIndexedAccessWithSymbol(RuleEnvironment G,
		IndexedAccessExpression indexedAccess, TypeRef receiverTypeRef, TField accessedBuiltInSymbol) {
		// check valid symbol (currently only 'iterator')
		if (accessedBuiltInSymbol !== G.symbolObjectType.ownedMembers.findFirst[name == "iterator"]) {
			addIssue(messageForEXP_INDEXED_ACCESS_SYMBOL_INVALID, indexedAccess,
				EXP_INDEXED_ACCESS_SYMBOL_INVALID);
			return false;
		}
		// check valid receiver type (currently only for instance of Iterable and immediate(!) instances of Object and dynamic types)
		val isIterable = ts.subtypeSucceeded(G, receiverTypeRef,
			G.iterableTypeRef(TypeRefsFactory.eINSTANCE.createWildcard));
		val isObjectImmediate = receiverTypeRef.declaredType === G.objectType &&
			receiverTypeRef.typingStrategy === TypingStrategy.NOMINAL;
		val isDynamic = receiverTypeRef.dynamic;
		if (!(isIterable || isObjectImmediate || isDynamic)) {
			addIssue(messageForEXP_INDEXED_ACCESS_SYMBOL_WRONG_TYPE, indexedAccess,
				EXP_INDEXED_ACCESS_SYMBOL_WRONG_TYPE);
			return false;
		}
		// check valid access (currently read-only, except for immediate(!) instances of Object and dynamic types)
		if (!(isObjectImmediate || isDynamic)) {
			val boolean writeAccess = ExpressionExtensions.isLeftHandSide(indexedAccess);
			if (writeAccess) {
				addIssue(messageForEXP_INDEXED_ACCESS_SYMBOL_READONLY, indexedAccess,
					EXP_INDEXED_ACCESS_SYMBOL_READONLY);
				return false;
			}
		}
		return true;
	}

	@Check
	def checkAssignmentExpression(AssignmentExpression assExpr) {
		val lhs = assExpr.lhs;
		// GH-119 imported elements
		holdsWritableIdentifier(lhs) && holdsLefthandsideNotConst(lhs);

		val rhs = assExpr.rhs
		if (rhs instanceof IdentifierRef) {
			val id = rhs.id;
			switch (id) {
				TMethod: { /* nop */
				}
				TFunction: {
					internalCheckNameRestrictionInMethodBodies(
						rhs,
						[ String message, EObject source, EStructuralFeature feature, String issueCode |
							addIssue(message, source, feature, issueCode)
						]
					)
				}
			}
		}
	}

	/** @return true if nothing was issued  */
	private def boolean holdsLefthandsideNotConst(Expression lhs) {

		if (lhs instanceof ParenExpression) {
			return holdsLefthandsideNotConst(lhs.expression);
		} else if (lhs instanceof IdentifierRef) {
			return holdsLefthandsideNotConst(lhs);
		}
		return true;
	}

	/** @return true if nothing was issued  */
	private def boolean holdsLefthandsideNotConst(IdentifierRef lhs) {
		val id = lhs.id;
		switch (id) {
			VariableDeclaration case id.const: {
				addIssue(getMessageForEXP_ASSIGN_CONST_VARIABLE(id.name), lhs,
					EXP_ASSIGN_CONST_VARIABLE);
				return false;
			}
			TVariable case id.const: {
				addIssue(getMessageForEXP_ASSIGN_CONST_VARIABLE(id.name), lhs,
					EXP_ASSIGN_CONST_VARIABLE);
				return false;
			}
			TField case !id.writeable: {
				// note: this case can happen only when referring to globals in GlobalObject (see file global.n4ts);
				// in all other cases of referencing a field, 'lhs' will be a PropertyAccessExpression (those cases
				// will be handled in class AbstractMemberScope as part of scoping)
				addIssue(
					getMessageForVIS_WRONG_READ_WRITE_ACCESS("built-in constant", id.name, "read-only"),
					lhs, VIS_WRONG_READ_WRITE_ACCESS);
				return false;
			}
		}
		return true;
	}

	@Check
	def checkPromisify(PromisifyExpression promiExpr) {
		if (!promisifyHelper.isPromisifiableExpression(promiExpr.expression)) {
			addIssue(getMessageForEXP_PROMISIFY_INVALID_USE, promiExpr, EXP_PROMISIFY_INVALID_USE);
		}
	}

	/**
	 * Ensures that 'this' literals are located at a valid location.
	 */
	@Check
	def checkThisLiteral(ThisLiteral thisLiteral) {
		var context = EcoreUtil2.getContainerOfType(thisLiteral, ThisArgProvider);
		
		// cf. GH-348
		while (context instanceof ArrowFunction) {
			context = EcoreUtil2.getContainerOfType(context.eContainer, ThisArgProvider);
		} 
		
		// 1) not in static members of interfaces
		if(context instanceof N4MemberDeclaration) {
			val tMember = context.definedTypeElement;
			if(tMember?.containingType instanceof TInterface && tMember.static) {
				val msg = getMessageForCLF_NO_THIS_IN_STATIC_MEMBER_OF_INTERFACE
				addIssue(msg, thisLiteral, CLF_NO_THIS_IN_STATIC_MEMBER_OF_INTERFACE);
				return;
			}
		}
		if(context instanceof N4FieldDeclaration) {
			val tField = context.definedTypeElement;
			// 2) not in initializers of data fields in interfaces
			if(tField?.containingType instanceof TInterface) {
				val msg = getMessageForCLF_NO_THIS_IN_FIELD_OF_INTERFACE
				addIssue(msg, thisLiteral, CLF_NO_THIS_IN_FIELD_OF_INTERFACE);
				return;
			}
			// 3) not in initializers of static(!) data fields in classes
			if(tField?.containingType instanceof TClass) {
				if(tField.static) {
					val msg = getMessageForCLF_NO_THIS_IN_STATIC_FIELD
					addIssue(msg, thisLiteral, CLF_NO_THIS_IN_STATIC_FIELD);
					return;
				}
			}
		}
	}

	@Check
	def void checkMandatoryCompileTimeExpression(Expression expr) {
		if(expr.eContainer instanceof IndexedAccessExpression) {
			// special case: index access expressions
			// -> this is handled in a more fine-grained manner above by method #checkIndexedAccessExpression()
			return;
		}
		if(N4JSLanguageUtils.isRequiredToBeCompileTimeExpression(expr)) {
			val evalResult = astMetaInfoCacheHelper.getCompileTimeValue(expr);
			if(evalResult instanceof ValueInvalid) {
				if(isExpressionOfComputedPropertyNameInObjectLiteral(expr)) {
					// special case: in object literals, anything goes
					// (but show a warning)
					addIssue(IssueCodes.getMessageForEXP_COMPUTED_PROP_NAME_DISCOURAGED, expr,
						IssueCodes.EXP_COMPUTED_PROP_NAME_DISCOURAGED);
					return;
				}
				createIssuesForEvalErrors(evalResult.errors);
			}
		}
	}
	def private boolean isExpressionOfComputedPropertyNameInObjectLiteral(Expression expr) {
		val exprParent = expr.eContainer;
		return exprParent instanceof LiteralOrComputedPropertyName
			&& exprParent.eContainer instanceof PropertyAssignment
			&& exprParent.eContainer.eContainer instanceof ObjectLiteral;
	}
	def private void createIssuesForEvalErrors(EvalError... errors) {
		for(error : errors) {
			createIssueForEvalError(error);
		}
	}
	def private void createIssueForEvalError(EvalError error) {
		val message = if(error instanceof UnresolvedPropertyAccessError) {
			// special case:
			// property of a ParameterizedPropertyAccessExpression was not found while evaluating the compile-time
			// expression (see location in CompileTimeExpressionProcessor where UnresolvedPropertyAccessError is created)
			// -> in this case, CompileTimeExpressionProcessor could not provide a detailed error message, so we have
			// to come up with our own message:
			val propAccessExpr = error.astNodeCasted;
			val prop = propAccessExpr.property;
			if(prop===null || prop.eIsProxy) {
				// property does not exist, which will cause the usual "Couldn't resolve ..." error
				// -> no additional error message required, here
				null
			} else {
				// at this point, still quite a few cases are left, but to distinguish between them would require
				// additional information in the TModule, which is not worth it; so we go with a fairly generic
				// message, here:
				"reference must point to a directly owned field (i.e. not inherited, consumed, or polyfilled) and the field must not have a computed name"
			}
		} else {
			// standard case:
			// -> CompileTimeExpressionProcessor provided an error message
			error.message
		};
		val astNode = error.astNode;
		val feature = error.feature;
		if(message!==null && astNode!==null) { // feature may be null, that is ok!
			val msgFull = getMessageForEXP_COMPILE_TIME_MANDATORY(message);
			addIssue(msgFull, astNode, feature, IssueCodes.EXP_COMPILE_TIME_MANDATORY);
		}
	}
}

enum ConstBoolean {
	CTrue,
	CFalse,
	NotPrecomputable
}
