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
import eu.numberfour.n4js.n4JS.AssignmentExpression
import eu.numberfour.n4js.n4JS.AssignmentOperator
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ExpressionAnnotationList
import eu.numberfour.n4js.n4JS.GenericDeclaration
import eu.numberfour.n4js.n4JS.N4ClassifierDeclaration
import eu.numberfour.n4js.n4JS.N4InterfaceDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4MemberDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.n4JS.extensions.ExpressionExtensions
import eu.numberfour.n4js.scoping.N4JSScopeProvider
import eu.numberfour.n4js.scoping.utils.AbstractDescriptionWithError
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.BoundThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeRef
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.typeRefs.UnknownTypeRef
import eu.numberfour.n4js.ts.types.AnyType
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.PrimitiveType
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TInterface
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TSetter
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typeinference.N4JSTypeInferencer
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4js.utils.ContainerTypesHelper
import eu.numberfour.n4js.utils.N4JSLanguageUtils
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4js.validation.IssueCodes
import eu.numberfour.n4js.validation.JavaScriptVariant
import eu.numberfour.n4js.xsemantics.N4JSTypeSystem
import it.xsemantics.runtime.Result
import it.xsemantics.runtime.validation.XsemanticsValidatorErrorGenerator
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.naming.QualifiedName
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage.Literals.PARAMETERIZED_TYPE_REF__DECLARED_TYPE
import static eu.numberfour.n4js.validation.IssueCodes.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import eu.numberfour.n4js.n4JS.TypedElement

/**
 * Class for validating the N4JS types.
 */
class N4JSTypeValidator extends AbstractN4JSDeclarativeValidator {

	@Inject
	private XsemanticsValidatorErrorGenerator errorGenerator;

	@Inject
	private N4JSTypeInferencer typeInferencer;

	@Inject
	private N4JSTypeSystem ts;
	@Inject
	private TypeSystemHelper tsh;

	@Inject
	private N4JSScopeProvider n4jsScopeProvider;

	@Inject
	private ContainerTypesHelper containerTypesHelper;


	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	override register(EValidatorRegistrar registrar) {
		// nop
	}


	/**
	 * Validates all generic type variable declarations.
	 * Raises validation error for each type variable if any of their declared upper bound is a primitive type.
	 * <p>
	 * IDEBUG-185
	 *
	 * @param declaration the generic declaration to check the upper bound declarations of its type variables.
	 *
	 */
	@Check
	def checkGenericDeclarationType(GenericDeclaration declaration) {
		val functionType = newRuleEnvironment(declaration).functionType
		declaration.typeVars.filterNull.forEach [typeVar|
			typeVar.declaredUpperBounds.filterNull.forEach [ub|
				val declType = ub.declaredType;
				if (declType instanceof ContainerType<?> && declType.final) {
					if(declType === functionType) {
						// important exception (until function type expressions are supported as bounds):
						// class C<T extends Function> {} makes sense even though Function is final
						return;
					}
					val message = getMessageForCLF_UPPER_BOUND_FINAL(declType.name, typeVar.name);
					addIssue(message, ub, PARAMETERIZED_TYPE_REF__DECLARED_TYPE, CLF_UPPER_BOUND_FINAL);
				}
			];
		];
	}

	@Check
	def checkTModuleCreated(Script script) {
		if(script.module === null) {
			val rootNode = NodeModelUtils.getNode(script)
			if(rootNode !== null) {
				addIssue(IssueCodes.getMessageForTYS_MISSING, script, rootNode.offset, rootNode.length, TYS_MISSING);
			}
		}
	}


	@Check
	def checkParameterizedTypeRef(ParameterizedTypeRef paramTypeRef) {
		val declaredType = paramTypeRef.declaredType;
		if (declaredType === null || declaredType.eIsProxy) {
			return;
		}
		if (paramTypeRef.eContainer instanceof ClassifierTypeRef) {
			internalCheckValidTypeInClassifierTypeRef(paramTypeRef);
			return;
		}

		internalCheckTypeArguments(declaredType.typeVars, paramTypeRef.typeArgs, false,
			declaredType, paramTypeRef, TypeRefsPackage.eINSTANCE.parameterizedTypeRef_DeclaredType);
		internalCheckDynamic(paramTypeRef);
	}

	def internalCheckValidTypeInClassifierTypeRef(ParameterizedTypeRef paramTypeRef) {
		// IDE-785 uses ParamterizedTypeRefs in ClassifierTypeRefs. Currently Type Arguments are not supported in ClassifierTypeRefs, so
		// we actively forbid them here. Will be loosened for IDE-1310
		if( ! paramTypeRef.typeArgs.isEmpty ) {
			addIssue(IssueCodes.getMessageForAST_NO_TYPE_ARGS_IN_CLASSIFIERTYPEREF, paramTypeRef, AST_NO_TYPE_ARGS_IN_CLASSIFIERTYPEREF)
		} else if ( paramTypeRef instanceof FunctionTypeRef ) {
			addIssue(IssueCodes.getMessageForAST_NO_FUNCTIONTYPEREFS_IN_CLASSIFIERTYPEREF, paramTypeRef, AST_NO_FUNCTIONTYPEREFS_IN_CLASSIFIERTYPEREF)
		} else if ( paramTypeRef.declaredType instanceof TFunction ) {
			addIssue(IssueCodes.getMessageForAST_NO_FUNCTIONTYPEREFS_IN_CLASSIFIERTYPEREF, paramTypeRef, AST_NO_FUNCTIONTYPEREFS_IN_CLASSIFIERTYPEREF)
		}
	}

	@Check
	def checkThisTypeRef(ThisTypeRef thisTypeRef) {
		if (thisTypeRef instanceof BoundThisTypeRef) {
			// normally, BoundThisTypeRefs never appear in the AST; however, in certain special cases they might appear
			// as the type of a TMember contained in the 'cachedComposedMembers' property of a ComposedTypeRef
			// -> back off!
			return;
		}
		if (!(thisTypeRef.isUsedStructurallyAsFormalParametersInTheConstructor
			|| thisTypeRef.isUsedAtCovariantPositionInClassifierDeclaration
			|| thisTypeRef.isUsedInVariableWithSyntaxError)) {

			addIssue(IssueCodes.getMessageForAST_THIS_WRONG_PLACE, thisTypeRef, IssueCodes.AST_THIS_WRONG_PLACE);
		}
	}

	def private boolean isUsedStructurallyAsFormalParametersInTheConstructor(ThisTypeRef thisTypeRef) {
		if (thisTypeRef.useSiteStructuralTyping) {
			val methodOrConstructor = thisTypeRef?.eContainer?.eContainer;
			if (methodOrConstructor instanceof N4MethodDeclaration) {
				if (methodOrConstructor !== null && methodOrConstructor.isConstructor) {
					return true
				}
			}
		}
		return false
	}

	def private boolean isUsedAtCovariantPositionInClassifierDeclaration(ThisTypeRef thisTypeRef) {
		val classifierDecl = EcoreUtil2.getContainerOfType(thisTypeRef, N4ClassifierDeclaration);
		if(classifierDecl!==null) {
			// exception: disallow for static members of interfaces
			if(classifierDecl instanceof N4InterfaceDeclaration) {
				val memberDecl = EcoreUtil2.getContainerOfType(thisTypeRef, N4MemberDeclaration);
				if(memberDecl!==null && memberDecl.static) {
					return false;
				}
			}
			val varianceOfPos = N4JSLanguageUtils.getVarianceOfPosition(thisTypeRef);
			if(varianceOfPos!==null) {
				return varianceOfPos===Variance.CO;
			}
		}
		return false;
	}

	private def boolean isUsedInVariableWithSyntaxError(ThisTypeRef ref) {
		val container = ref.eContainer
		if (container instanceof VariableDeclaration) {
			return container.name === null
		}
		return false
	}

	@Check
	def checkSymbolReference(TypeRef typeRef) {
		var bits = BuiltInTypeScope.get(typeRef?.eResource.resourceSet);
		if(typeRef.declaredType === bits.symbolObjectType) {
			// we have a type reference to 'Symbol'
			// -> the only allowed case is as target/receiver of a property access (i.e.: Symbol.iterator)
			val parent = typeRef.eContainer;
			if(!(parent instanceof ParameterizedPropertyAccessExpression) ||
				(parent as ParameterizedPropertyAccessExpression).target!==typeRef) {
				addIssue(IssueCodes.getMessageForBIT_SYMBOL_INVALID_USE, typeRef, BIT_SYMBOL_INVALID_USE);
			}
		}
	}


	/*
	 * Constraints 08: primitive types except any must not be declared dynamic
	 */
	def internalCheckDynamic(ParameterizedTypeRef ref) {
		if (ref.dynamic) {
			val Type t = ref.declaredType;
			if (t instanceof PrimitiveType && ! (t instanceof AnyType)) {
				addIssue(IssueCodes.getMessageForTYS_PRIMITIVE_TYPE_DYNAMIC(t.name), ref, TYS_PRIMITIVE_TYPE_DYNAMIC);
			}
		}
	}

	@Check
	def checkTypeHiddenByTypeVariable(GenericDeclaration genDecl) {

		// TODO reconsider this warning or its implementation; re-generating the scope can become quite expensive
		// (but note that moving this to the scoping code is not trivial, because warning has to be generated also
		//  if not references to the type parameter are made!)
		if (!genDecl.typeVars.empty) {
			val staticAccess = genDecl instanceof N4MemberDeclaration && (genDecl as N4MemberDeclaration).static;
			val scope = n4jsScopeProvider.getTypeScope( // note: calling #getTypeScope() here, NOT #getScope()!
				genDecl.eContainer, // use container, because we do not want to see type variables we are currently validating
				TypeRefsPackage.eINSTANCE.parameterizedTypeRef_DeclaredType, // provide any reference that expects instances of Type as target objects
				staticAccess);
			genDecl.typeVars.forEach [
				if (!it.name.nullOrEmpty) {
					val hiddenTypeDscr = scope.getSingleElement(QualifiedName.create(it.name));
					val hiddenType = hiddenTypeDscr?.getEObjectOrProxy;
					if (hiddenType instanceof Type && !(AbstractDescriptionWithError.isErrorDescription_XTEND_MVN_BUG_HACK(hiddenTypeDscr))) {
						val message = getMessageForVIS_TYPE_PARAMETER_HIDES_TYPE(name, hiddenType.keyword);
						addIssue(message, it, VIS_TYPE_PARAMETER_HIDES_TYPE);
					}
				}
			]
		}
	}

	/**
	 * This handles a special case that is not checked by method {@link #checkTypeMatchesExpectedType(Expression)}.
	 * In a compound assignment like += or *=, the left-hand side is both read from and written to. So we have
	 * to check 1) that the type for write access is correct and 2) the type of read access is correct. Usually
	 * these two types are the same, but in case of a getter/setter pair they can be different and need to be
	 * checked individually. Case 1) is taken care of by method {@link #checkTypeMatchesExpectedType(Expression)}.
	 * Case 2) is checked here in this method.
	 */
	@Check
	def void checkCompoundAssignmentGetterSetterClashOnLhs(AssignmentExpression assExpr) {
		if (assExpr.op === null || assExpr.op === AssignmentOperator.ASSIGN)
			return; // following code is only required for compound assignments, i.e. +=, *=, etc.; not for =

		val Expression lhs = assExpr.lhs;
		if (lhs instanceof ParameterizedPropertyAccessExpression) {
			val prop = lhs.property;

			// in case of a getter/setter pair, scoping will here always give us the setter as property,
			// because we are on the left-hand side of an assignment (i.e. write access)
			if (prop instanceof TSetter) {

				// ok, we have the situation we are interested in (setter on LHS of compound assignment)
				// --> now check if there is a matching getter of correct type
				val TSetter setter = prop;
				val Type targetType = typeInferencer.tau(lhs.target)?.declaredType;
				if (targetType instanceof ContainerType<?>) {
					val TMember m = containerTypesHelper.fromContext(assExpr).findMember(targetType, setter.name, false, setter.static);
					if (m === null) {

						// no getter at all
						val message = messageForTYS_COMPOUND_MISSING_GETTER
						addIssue(message, assExpr.lhs, TYS_COMPOUND_MISSING_GETTER);
					} else if (m instanceof TGetter) {
						val TGetter getter = m;
						var G = assExpr.newRuleEnvironment;

						// must use .rhs in next line, because .lhs would give us the expected type for write access
						// (which is already checked by the generic method #checkTypeMatchesExpectedType()
						val expectedType = ts.expectedTypeIn(G, assExpr, assExpr.rhs).value;
						val TypeRef typeOfGetterRAW = TypeUtils.getMemberTypeRef(getter);
						if (expectedType !== null && typeOfGetterRAW !== null) {
							val TypeRef typeOfGetter = typeInferencer.substituteTypeVariables(G, typeOfGetterRAW);
							if (typeOfGetter !== null) {
								val result = ts.subtype(G, typeOfGetter, expectedType);
								createError(result, assExpr.lhs);
							}
						}
					}
				}
			}
		}
	}

	@Check
	def void checkInconsistentInterfaceImplementationOrExtension(N4ClassifierDeclaration classifierDecl) {
		val tClassifier = classifierDecl.definedType as TClassifier;
		if(tClassifier===null)
			return; // broken AST
		val G = newRuleEnvironment(classifierDecl);
		G.recordInconsistentSubstitutions;
		tClassifier.superClassifiers.forEach[tsh.addSubstitutions(G, it)];
		for(tv : G.getTypeMappingKeys()) {
			if(!tv.declaredCovariant && !tv.declaredContravariant) {
				val subst = ts.substTypeVariables(G, TypeUtils.createTypeRef(tv)).value;
				if(subst instanceof UnknownTypeRef) {
					val badSubst = G.getInconsistentSubstitutions(tv);
					if(!badSubst.empty) {
						if(!tsh.allEqualType(G, badSubst)) {
							val mode = if(tClassifier instanceof TClass) "implement" else "extend";
							val ifcName = (tv.eContainer as TInterface).name;
							val tvName = "invariant " + tv.name;
							val typeRefsStr = badSubst.map[typeRefAsString].join(", ");
							val message = getMessageForCLF_IMPLEMENT_EXTEND_SAME_INTERFACE_INCONSISTENTLY(mode, ifcName, tvName, typeRefsStr);
							addIssue(message, classifierDecl, N4JSPackage.eINSTANCE.n4TypeDeclaration_Name,
								CLF_IMPLEMENT_EXTEND_SAME_INTERFACE_INCONSISTENTLY);
						}
					}
				}
			}
		}
	}

	/**
	 * This tests ALL expressions, including expressions used on right hand side of assignments or property definitions. That is,
	 * there is no need to test field declarations or property declarations separately, as this will lead to duplicate error
	 * messages.
	 */
	@Check
	def void checkTypeMatchesExpectedType(Expression expression) {

		if(JavaScriptVariant.getVariant(expression) == JavaScriptVariant.unrestricted) {
			return;
		}

		// expressionAnnotationList occur on function- and class-expressions.
		// checking of the content is done in N4JSAnnotationValidation
		if (expression instanceof ExpressionAnnotationList) {
			return
		}

		var G = expression.newRuleEnvironment;
		val inferredType = ts.type(G, expression);
		if (createError(inferredType, expression)) {
			return;
		}

		// use a fresh environment for expectations
		G = newRuleEnvironment(expression);

		val expectedType = ts.expectedTypeIn(G, expression.eContainer, expression);


		if (expectedType.value !== null) {
			val boolean writeAccess = ExpressionExtensions.isLeftHandSide(expression);
			if (writeAccess) {

				// special case: write access
				val result = ts.subtype(G, expectedType.value, inferredType.value);

				if (result.failed) {
					// use custom error message, because otherwise it will be completely confusing
					val message = getMessageForTYS_NO_SUPERTYPE_WRITE_ACCESS(expectedType.value.typeRefAsString,
						inferredType.value.typeRefAsString);
					addIssue(message, expression, TYS_NO_SUPERTYPE_WRITE_ACCESS)
				}
			} else {

				// standard case: read access
				val result = ts.subtype(G, inferredType.value, expectedType.value)
				// not working, as primitive types are not part of currently validated resource:
				// errorGenerator.generateErrors(this, result, expression)
				// so we create error here differently:
				createError(result, expression)
			}
		}
	}
	
	@Check
	def checkBogusTypeReference(TypedElement te) {
		if(te.bogusTypeRef !== null) {
			addIssue(IssueCodes.getMessageForTYS_INVALID_TYPE_SYNTAX, te.bogusTypeRef, TYS_INVALID_TYPE_SYNTAX);
		}
	}
	
//  TODO IDE-1010 Code-snippet with partial solution
//	@Check
//	def checkApplyParameters(ParameterizedCallExpression callExpression) {
//
//		val target = callExpression.target
//
//		// check apply with types.
//		if (target instanceof ParameterizedPropertyAccessExpression) {
//			val prop = target.property
//			if (prop instanceof TMethod) {
//				if ("apply" == prop.name) {
//
//					val func = callExpression.receiver
//					val thisArg = callExpression.arguments.head
//
//					if (thisArg !== null && func !== null) {
//
//						// apply called on a function or functionexpress or...
//						// infer type of this-Environment:
//						var G = typeInferencer.getConfiguredRuleEnvironment(thisArg);
//
//						val inferredThisArgTypeResult = ts.type(G, thisArg);
//						if (inferredThisArgTypeResult.failed) return
//
//						val inferredThisArgType = inferredThisArgTypeResult.value
//
//						// infer Type of function we want to call:
//						val funcTypeResult = ts.type(G, func)
//						if (funcTypeResult.failed) return
//
//						val funcType = funcTypeResult.value
//
//						if (funcType instanceof FunctionTypeExprOrRef) {
//
//							// if there is a declared this-type
//							val reqThisType = funcType.declaredThisType
//							if (reqThisType !== null) {
//								val result = ts.subtypeTypeRef(G, inferredThisArgType, reqThisType);
//
//								// report.
//								createError(result, callExpression)
//							}
//						}
//
//					}
//
//				}
//			}
//
//		}
//
//	}

	def boolean createError(Result<?> result, EObject source) {
		if (result.failed) {
			errorGenerator.generateErrors(getMessageAcceptor(), result, source);
			return true;
		}
		false;
	}
}
