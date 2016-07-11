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
import eu.numberfour.n4js.n4JS.ArrowFunction
import eu.numberfour.n4js.n4JS.Block
import eu.numberfour.n4js.n4JS.BreakStatement
import eu.numberfour.n4js.n4JS.ExportDeclaration
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.FieldAccessor
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.FunctionOrFieldAccessor
import eu.numberfour.n4js.n4JS.GetterDeclaration
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.ReturnStatement
import eu.numberfour.n4js.n4JS.SetterDeclaration
import eu.numberfour.n4js.n4JS.ThrowStatement
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef
import eu.numberfour.n4js.ts.typeRefs.ComposedTypeRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExpression
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.ThisTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TStructField
import eu.numberfour.n4js.ts.types.TStructSetter
import eu.numberfour.n4js.ts.types.UndefModifier
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.utils.nodemodel.HiddenLeafAccess
import eu.numberfour.n4js.utils.nodemodel.HiddenLeafs
import eu.numberfour.n4js.validation.AbstractN4JSDeclarativeValidator
import eu.numberfour.n4js.validation.IssueCodes
import eu.numberfour.n4js.validation.JavaScriptVariant
import java.util.List
import org.eclipse.emf.common.util.EList
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EStructuralFeature
import org.eclipse.xtext.nodemodel.util.NodeModelUtils
import org.eclipse.xtext.validation.Check
import org.eclipse.xtext.validation.EValidatorRegistrar

import static eu.numberfour.n4js.n4JS.N4JSPackage.Literals.*
import static eu.numberfour.n4js.validation.IssueCodes.*
import static eu.numberfour.n4js.validation.helper.N4JSLanguageConstants.*
import static eu.numberfour.n4js.validation.validators.StaticPolyfillValidatorExtension.*
import static org.eclipse.xtext.util.Strings.toFirstUpper

import static extension com.google.common.base.Strings.*
import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*
import static extension eu.numberfour.n4js.utils.EcoreUtilN4.*

/**
 */
class N4JSFunctionValidator extends AbstractN4JSDeclarativeValidator {

	@Inject
	private N4JSTypeSystem ts;

	@Inject
	private ReturnOrThrowAnalysis returnOrThrowAnalysis
	
	@Inject
	private HiddenLeafAccess hla;
	

	/**
	 * NEEEDED
	 *
	 * when removed check methods will be called twice once by N4JSValidator, and once by
	 * AbstractDeclarativeN4JSValidator
	 */
	override register(EValidatorRegistrar registrar) {
		// nop
	}

	/*
	 * "Also, an ExpressionStatement cannot start with the function keyword because that might make it ambiguous with a FunctionDeclaration."
	 * [ECMAScript] 12.4 Expression Statement, [N4JS] 8.2. Function Definition
	 *
	 * see also ECMAScript Test Suite Sbp_12.5_A9_T3.js
	 *
	 */
	@Check
	def checkFunctionExpressionInExpressionStatement(FunctionDeclaration functionDeclaration) {
		val container = functionDeclaration.eContainer
		if (container instanceof Block && JavaScriptVariant.getVariant(functionDeclaration).isECMAScript()) {
			val msg = getMessageForFUN_BLOCK
			if (functionDeclaration.name !== null) {
				addIssue(msg, functionDeclaration, N4JSPackage.Literals.FUNCTION_DECLARATION__NAME, FUN_BLOCK)
			} else {
				addIssue(msg, container, functionDeclaration.eContainmentFeature, FUN_BLOCK)
			}
		}
	}

	/**
	 *
	 * TODO once ISSUE-666 is resolved this method could be dropped when
	 * the check is carried out with #checkFunctionReturn(FunctionOrFieldAccessor)
	 *
	 * Return-Type checking.
     *
     * [N4JSSpec] 7.1.4 Return Statement
     *
     * Constraint 111
	 *
	 * @see #checkFunctionReturn(FunctionOrFieldAccessor)
	 */
	@Check
	def checkSetter(SetterDeclaration setterDeclaration) {
		holdsFunctionReturnsVoid(setterDeclaration, true)
	}

	/**
	 *
	 * TODO once ISSUE-666 is resolved this method could be dropped when
	 * the check is carried out with #checkFunctionReturn(FunctionOrFieldAccessor)
	 *
	 * Return-Type checking.
     *
     * [N4JSSpec] 7.1.4 Return Statement
     *
     * Constraint 111
	 *
	 * @see #checkFunctionReturn(FunctionOrFieldAccessor)
	 */
	@Check
	def checkGetter(GetterDeclaration getterDeclaration) {
		holdsFunctionReturn(getterDeclaration)
	}

	/**
	 *
	 * TODO once ISSUE-666 is resolved this method could be dropped when
	 * the check is carried out with #checkFunctionReturn(FunctionOrFieldAccessor)
	 *
	 * Return-Type checking.
     *
     * [N4JSSpec] 7.1.4 Return Statement
     *
     * Constraint 111
	 *
	 * @see #checkFunctionReturn(FunctionOrFieldAccessor)
	 */
	@Check
	def checkFunctionReturn(FunctionDefinition functionDefinition) {
		if (JavaScriptVariant.getVariant(functionDefinition).isECMAScript()) {
			return; // cf. 13.1
		}
		holdsDeclaredReturnTypeRefDoesNotReferToNull(functionDefinition);
		holdsFunctionReturn(functionDefinition as FunctionOrFieldAccessor);
	}

	/** IDEBUG-779  Return-Type Annotations without type but just an optional modifier are not allowed.
	 */
    private def boolean holdsDeclaredReturnTypeRefDoesNotReferToNull(FunctionDefinition funDef) {
    	val declTypeRef = funDef.returnTypeRef;
    	if( declTypeRef !== null ) {
    		if( declTypeRef instanceof ParameterizedTypeRef ){
	    		if( declTypeRef.declaredType === null )
	    		{
	    			val kind = if( funDef instanceof N4MethodDeclaration ) "Method" else "Function";
	    			val message = IssueCodes.getMessageForFUN_RETURN_TYPE_MODIFIER_WITHOUT_TYPE(kind, funDef.name);
	    			addIssue(message, funDef, N4JSPackage.Literals.FUNCTION_DEFINITION__RETURN_TYPE_REF, IssueCodes.FUN_RETURN_TYPE_MODIFIER_WITHOUT_TYPE );
	    			return false;
	    		}
    		}
    	}
    	return true;
    }

	/**
     * Return-Type checking.
     *
     * [N4JSSpec] 7.1.4 Return Statement
     *
     * Constraint 111
     *
     * @param functionDefinition of for Methods and Functions
     *
     */
	// @Check Disabled because setter still claim to be of type ANY.  IDE-666
	// IF setter claims to be of type VOID/NULL this method could serve as entry point for the
	// three checks above.
	private def boolean holdsFunctionReturn(FunctionOrFieldAccessor functionOrFieldAccessor) {
		// simple inference without context: we only need to check IF there is a return type declared (or inferred), we do not need the concrete type
		val inferredType = ts.tau(functionOrFieldAccessor)
		val TypeRef retTypeRef = switch inferredType {
			// note: order is important, because FunctionTypeRef IS a ParameterizedTypeRef as well
			FunctionTypeExprOrRef: {
				if(functionOrFieldAccessor instanceof FunctionDefinition && functionOrFieldAccessor.isAsync) {
					(functionOrFieldAccessor as FunctionDefinition).returnTypeRef
				}
				else {
					inferredType?.returnTypeRef
				}
			}
			ParameterizedTypeRef: inferredType
			default: null
		}

		if (retTypeRef === null) return true; // probably consequential error

		// obtain the built-in void-Type
		val _void = newRuleEnvironment(functionOrFieldAccessor).voidType

		val isDeclaredVoid = if(functionOrFieldAccessor instanceof FieldAccessor) {
			TypeUtils.isOrContainsType(functionOrFieldAccessor.declaredTypeRef, _void)
		} else if(functionOrFieldAccessor instanceof FunctionDefinition) {
			TypeUtils.isOrContainsType(functionOrFieldAccessor.returnTypeRef, _void)
		} else false
		val isVoid = TypeUtils.isOrContainsType(retTypeRef, _void);
		val isNotVoid =  !isVoid ||
			(retTypeRef instanceof ComposedTypeRef && (retTypeRef as ComposedTypeRef).typeRefs.size>1);
		val isOptionalReturnType = retTypeRef.undefModifier == UndefModifier.OPTIONAL;


		val FunctionFullReport analysis = returnOrThrowAnalysis.exitBehaviourWithFullReport(functionOrFieldAccessor.body?.statements)

		// Dead Code? : Constraints 107
		holdsNoDeadCode(functionOrFieldAccessor, analysis);

		if (isOptionalReturnType) {
			// anything goes!
			// (function may or may not leave with return statement; return statements may or may not have an expression)
			return true;
		}
		if (isVoid && isNotVoid) {
			return true; // union{any,void} --> everything is ok
		}
		if (isDeclaredVoid || (isVoid && !(functionOrFieldAccessor instanceof GetterDeclaration))) {
			// Only if the return type is explicitly set to void
			// 1. Search for all explicit return statements
			// 2. Implicit returns: ( No implicit return given )
			return holdsFunctionReturnsVoid(functionOrFieldAccessor, false)
		}

		// isNotVoid:
		return holdsFunctionReturnsSomething(functionOrFieldAccessor, retTypeRef, analysis)
	}


	/**
	 * Given a function/method with returntype void, checking the lack of returns or presence of empty returns
	 *
	 * @param functionDefinition definition whith void-returntype
	 * @param _void precomputed builtin void type
	 * @param isSetter true for setter and therefore ensuring no return at all, false in case of ordinary function/methods
	 * 			where TS already does the job
	 */
	private def boolean holdsFunctionReturnsVoid(FunctionOrFieldAccessor functionOrFieldAccessor, boolean isSetter) {

		val retstatements = allReturnstatementsAsList(functionOrFieldAccessor)
		val _void = newRuleEnvironment(functionOrFieldAccessor).voidType

		// Constraint 111.2.
		for (rst : retstatements) {

			// ...no expression
			if (rst.expression !== null) {

				val expressionType = ts.tau(rst.expression)

				val actualType = if (expressionType instanceof ParameterizedTypeRef) {
						expressionType.declaredType
					} else {
						expressionType
					}

				// ... or the type of expression is void:
				if (actualType !== null && actualType != _void) {

					// Issue if isSetter.
					if (isSetter) {

						// Something else than void is returned -> Error
						val String msg = messageForFUN_RETURNTYPE_VOID_FOR_SETTER_VIOLATED
						addIssue(msg, rst, FUN_RETURNTYPE_VOID_FOR_SETTER_VIOLATED)
						return false;
					} else {
						// other cases are handled by type system (except setters).
						// that is, error message is created by type system: xx is not a subtype of void
					}
				}
			}
		}
		return true;
	}

	/**
	 *
	 * Constraint 111.3 Item 2 "all control flows must either end with a return or throw statement"
	 *
	 * @param functionDefinition functionDefinition with return type not void
	 * @param returnTypeRef only used for error message
	 */
	private def boolean holdsFunctionReturnsSomething(FunctionOrFieldAccessor functionOrFieldAccessor,
		TypeRef returnTypeRef, FunctionFullReport analysis) {

		if (functionOrFieldAccessor.body === null) {
			return true;
		}
		if (functionOrFieldAccessor instanceof N4MethodDeclaration) {
			if(functionOrFieldAccessor.isConstructor) {
				// we have a non-void constructor -> there will already be an error elsewhere -> avoid duplicate errors
				return true;
			}
		}

		// Constraint 111.3.a
		holdsAllReturnStatementsContainExpression(functionOrFieldAccessor, returnTypeRef);

		// ok, but maybe a control flow ends without a return or throw:

		// Constrain 111.3.b
		var bFoundControlFlowWOReturn = switch ( analysis.returnMode ) {
			case ReturnMode.noReturnsMode: isMissingReturnDisallowed(functionOrFieldAccessor)
			BreakOrContinue: false // should not happen.
			case ReturnMode.throwsMode: false // all fine - no check for throws.
			case ReturnMode.returnsMode: false // all fine - TS doing the check.
		}

		return ! bFoundControlFlowWOReturn;
	}

	/**
	 * Helper method. The argument does contain a control-flow path where no value is returned.
	 * However, before raising an error (due to breaking Constraint 111.3.a)
	 * it's necessary to check whether we're dealing with an arrow function of the single-expression variety,
	 * to which ES6 grants an implicit-return, thus allowing them.
	 */
	private def boolean isMissingReturnDisallowed(FunctionOrFieldAccessor accessor) {
		// ES6 arrow functions allow single-exprs to lack an explicit return
		if (accessor instanceof ArrowFunction) {
			if (accessor.isSingleExprImplicitReturn) {
				return false
			}
		}
		// at least one leaking control-flow path out of Method w/o returning anything:
		val highlightFeature = switch accessor {
			FunctionDeclaration: N4JSPackage.Literals.FUNCTION_DECLARATION__NAME
			N4MethodDeclaration: N4JSPackage.Literals.PROPERTY_NAME_OWNER__DECLARED_NAME
			FunctionExpression: N4JSPackage.Literals.FUNCTION_EXPRESSION__NAME
			GetterDeclaration: N4JSPackage.Literals.GETTER_DECLARATION__DEFINED_GETTER
			default: null
		}
		val String msg = messageForFUN_MISSING_RETURN_OR_THROW_STATEMENT
		addIssue(msg, accessor, highlightFeature, FUN_MISSING_RETURN_OR_THROW_STATEMENT)
		return true;
	}

	/**
	 * Method for checking whether the name of a function definition such as:
	 * <p>
	 * <ul>
	 * <li>{@link FunctionDeclaration <em>Function declaration</em>},</li>
	 * <li>{@link N4MethodDeclaration <em>Method declaration</em>} or</li>
	 * <li>{@link FunctionExpression <em>Function expression</em>}</li>
	 * </ul>
	 * conflicts with a reserved keyword or a future reserved keyword.
	 * In case of conflicts this method creates a validation if the name violates the constraint.
	 * </p>
	 * IDEBUG-287
	 *
	 * @param definition the function definition to validate in respect if its name.
	 */
 	@Check
	def checkFunctionName(FunctionDefinition definition) {
		val name = definition.name.nullToEmpty;
		val desc = validatorMessageHelper.description(definition);
		var errorMessage = "";

		// Disable function name validation against keywords if not N4JS file.
		if (JavaScriptVariant.n4js.isActive(definition)) {

			//IDEBUG-304 : allow keywords as member names
			if(definition instanceof N4MethodDeclaration === false){
				if (FUTURE_RESERVED_WORDS.contains(name)) {
					errorMessage = getMessageForFUN_NAME_RESERVED(desc, "future reserved word")
				}

				if ('yield' != name && KEYWORDS.contains(name)) {
					errorMessage = getMessageForFUN_NAME_RESERVED(desc, "keyword")
				}
			}
		}

		if (!errorMessage.nullOrEmpty) {
			var feature = switch (definition) {
				FunctionDeclaration: FUNCTION_DECLARATION__NAME
				N4MethodDeclaration: PROPERTY_NAME_OWNER__DECLARED_NAME
				FunctionExpression: FUNCTION_EXPRESSION__NAME
				default: null
			}
			addIssue(toFirstUpper(errorMessage), definition, feature, FUN_NAME_RESERVED);
		}

		return errorMessage.nullOrEmpty
	}

	/**
	 * Constraints 107
	 */
	private def boolean holdsNoDeadCode(FunctionOrFieldAccessor functionOrFieldAccessor, FunctionFullReport analysis) {
		for (db : analysis.deadCode) {
			val firstNode = NodeModelUtils.findActualNodeFor(db.statements.head)
			val lastNode = NodeModelUtils.findActualNodeFor(db.statements.last)
			val off = firstNode.offset
			val len = lastNode.offset - firstNode.offset + lastNode.length

			val String stmtDescription = switch db.lastExecutedStmt {
				ThrowStatement: 'throw'
				ReturnStatement: 'return'
				BreakStatement: 'break'
				default: db.lastExecutedStmt.eClass.name
			}

			val String msg = getMessageForFUN_DEAD_CODE(stmtDescription)
			addIssue(msg, functionOrFieldAccessor, off, len, FUN_DEAD_CODE)
		}
		return analysis.deadCode.empty
	}

	/**
	 * Grab all returns in body that apply to this function/get/set. (Leave out returns of nested definitions)
	 * @param functionOrFieldAccessor body to inspect
	 * @return all return statements in a functionOrFieldAccessor
	 * 				(=function-definition or field accessor) (nested) but not in included functionExpressions, function definitions ....
	 */
	private def Iterable<ReturnStatement> allReturnstatementsAsList(FunctionOrFieldAccessor functionOrFieldAccessor) {
		val retsByEcore = if (functionOrFieldAccessor.body === null)
				#[]
			else
				functionOrFieldAccessor.body.getAllContentsFiltered(
					[! ( it instanceof Expression || it instanceof FunctionOrFieldAccessor)]).filter(ReturnStatement).
					toList()
		return retsByEcore
	}

	/**
	 * Assuring all return statements do have an expression
	 * @param returnTypeRef only used for error message
	 */
	private def boolean holdsAllReturnStatementsContainExpression(FunctionOrFieldAccessor definition, TypeRef returnTypeRef) {

		val retstatements = allReturnstatementsAsList(definition)
		var errorsFound = false;
		for (ReturnStatement rst : retstatements) {

			// check missing return-expression
			if (rst.expression === null) {
				val String msg = getMessageForFUN_MISSING_RETURN_EXPRESSION(returnTypeRef.typeRefAsString)
				addIssue(msg, rst, FUN_MISSING_RETURN_EXPRESSION)
				errorsFound = true;
			}

		// given return-expressions will be checked by xsemantics.
		// TODO what about null - tests with primitives (builtin types) expected ?
		}
		return errorsFound;

	}

	/** additional check on top of {@link #checkFunctionName()} */
	@Check
	def checkFunctionDeclarationName(FunctionDeclaration functionDeclaration) {
		if( functionDeclaration.name === null ) {
			// Function declaration without name is only allowed for default-exported functions.
			val container = functionDeclaration.eContainer;
			if( container instanceof ExportDeclaration){
				if( container.isDefaultExport ) {
					// ECMAScript 2015 allows "export default" for anonymous function declarations.
					return;
				}
			}
			// not on "default export":
			// add message "function declarations must have a name"
			if( functionDeclaration.body !== null) {
				// mark up to closing parameter parenthesis
				val firstNode = NodeModelUtils.findActualNodeFor(functionDeclaration);
				val lastNode = NodeModelUtils.findActualNodeFor(functionDeclaration.body);
				val HiddenLeafs hLeafs = hla.getHiddenLeafsBefore(lastNode);
				
				val off = firstNode.offset;
				val len = hLeafs.offset - firstNode.offset;
				addIssue(messageForFUN_NAME_MISSING,functionDeclaration,off,len,FUN_NAME_MISSING);				
			} else { 
			  	// mark complete function.	
				addIssue(messageForFUN_NAME_MISSING,functionDeclaration,FUN_NAME_MISSING);				
			}
			
		}
	}

	@Check
	def checkFunctionDeclarationBody(FunctionDeclaration functionDeclaration) {
		if (functionDeclaration.body === null && functionDeclaration.definedType instanceof TFunction &&
			!(functionDeclaration.definedType as TFunction).external) {
			addIssue(getMessageForFUN_BODY, functionDeclaration, N4JSPackage.Literals.FUNCTION_DECLARATION__NAME,
				FUN_BODY)
		}
	}

	@Check
	def checkParameters(SetterDeclaration fun){
		val isVariadic = if( fun?.fpar !== null ) { fun.fpar.isVariadic } else { false }
		if( isVariadic ) {
			val String msg = messageForFUN_SETTER_CANT_BE_VARIADIC
			addIssue(msg, fun.fpar, FUN_SETTER_CANT_BE_VARIADIC)
		}
	}

	@Check
	def checkParameters(TStructSetter fun){
		val isVariadic = if( fun?.fpar !== null ) { fun.fpar.isVariadic } else { false }
		if( isVariadic ) {
			val String msg = messageForFUN_SETTER_CANT_BE_VARIADIC
			addIssue(msg, fun.fpar, FUN_SETTER_CANT_BE_VARIADIC)
		}
		// setter not optional:
		if( fun?.fpar !== null ) {
			if( fun.fpar.typeRef?.undefModifier == UndefModifier.OPTIONAL ) {
				addIssue(messageForEXP_OPTIONAL_INVALID_PLACE, fun.fpar.typeRef, EXP_OPTIONAL_INVALID_PLACE )
			}
		}
	}

	/**
	 * IDEBUG-211  Check for ..., ?, and missing name in formal parameters.
	 *
	 */
	@Check
	def checkParameters(FunctionDefinition fun){
		holdsModifierOfParamsHaveType(fun.fpars)

		// only optional or variadic after first optional parameter.
		var sawUndefined = false
		var FormalParameter firstUndef = null
		for(p:fun.fpars) {
			if( ! sawUndefined ) {
				sawUndefined = ( p.declaredTypeRef?.undefModifier == UndefModifier.OPTIONAL )
				if( sawUndefined ) firstUndef = p;
			} else {
				// first optional already encountered.
				if( p.declaredTypeRef?.undefModifier != UndefModifier.OPTIONAL &&  (! p.isVariadic ) ){
					addIssue(messageForFUN_PARAM_OPTIONAL_AT_END,firstUndef, FUN_PARAM_OPTIONAL_AT_END )
					// only one Error:
					return
				}
			}
		}
	}

	/**
	 * IDEBUG-211  Check for ..., ?, and missing name in formal parameters.
	 *
	 */
	@Check
	def checkParameters(FunctionTypeExpression fun) {
		holdsModifierOfParamsHaveTType(fun.fpars)

		// 1. Variadic only once
		val variadicsCount = fun.fpars.filter[isVariadic].size
		if ( variadicsCount > 1) {
			val variadicParams = fun.fpars.filter[isVariadic]
			addIssue( messageForFUN_PARAM_VARIADIC_ONLY_LAST, variadicParams.head , FUN_PARAM_VARIADIC_ONLY_LAST )

		}
		// 2. Variadic is last
		if( variadicsCount == 1) {
			val variadicParams = fun.fpars.filter[isVariadic]
			if( ! fun.fpars.last.isVariadic ) {
				addIssue( messageForFUN_PARAM_VARIADIC_ONLY_LAST, variadicParams.head , FUN_PARAM_VARIADIC_ONLY_LAST )
			}
		}
		// 3. only optional or variadic after first optional parameter.
		var sawUndefined = false
		var TFormalParameter firstUndef = null
		for(p:fun.fpars) {
			if( ! sawUndefined ) {
				sawUndefined = ( p.typeRef?.undefModifier == UndefModifier.OPTIONAL )
				if( sawUndefined ) firstUndef = p;
			} else {
				// first optional already encountered.
				if( p.typeRef?.undefModifier != UndefModifier.OPTIONAL &&  (! p.isVariadic ) ){
					addIssue(messageForFUN_PARAM_OPTIONAL_AT_END,firstUndef, FUN_PARAM_OPTIONAL_AT_END )
					// only one Error:
					return
				}
			}
		}
	}

	/**
	 * IDEBUG-211  Check for ..., ?, and missing name in formal parameters.
	 *
	 * TFunctions is supertype of are referenced
	 * */
	@Check
	def checkWithStructuralTypeRef(TFunction fun) {
		holdsModifierOfParamsHaveTType(fun.fpars)

		// 1. Variadic only once
		val variadicsCount = fun.fpars.filter[isVariadic].size
		if ( variadicsCount > 1) {
			val variadicParams = fun.fpars.filter[isVariadic]
			addIssue( messageForFUN_PARAM_VARIADIC_ONLY_LAST, variadicParams.head , FUN_PARAM_VARIADIC_ONLY_LAST )

		}
		// 2. Variadic is last
		if( variadicsCount == 1) {
			val variadicParams = fun.fpars.filter[isVariadic]
			if( ! fun.fpars.last.isVariadic ) {
				addIssue( messageForFUN_PARAM_VARIADIC_ONLY_LAST, variadicParams.head , FUN_PARAM_VARIADIC_ONLY_LAST )
			}
		}
		// 3. only optional or variadic after first optional parameter.
		var sawUndefined = false
		var TFormalParameter firstUndef = null
		for(p:fun.fpars) {
			if( ! sawUndefined ) {
				sawUndefined = ( p.typeRef?.undefModifier == UndefModifier.OPTIONAL )
				if( sawUndefined ) firstUndef = p;
			} else {
				// first optional already encountered.
				if( p.typeRef?.undefModifier != UndefModifier.OPTIONAL &&  (! p.isVariadic ) ){
					addIssue(messageForFUN_PARAM_OPTIONAL_AT_END,firstUndef, FUN_PARAM_OPTIONAL_AT_END )
					// only one Error:
					return
				}
			}
		}
	}

	/* IDEBUG-211 checking Undefined, Variadic and missing Typenames. */
	@Check
	def void checkStructuralTField(TStructField tfield) {
		if(  tfield.typeRef?.undefModifier == UndefModifier.OPTIONAL) {
			if ( tfield.typeRef.isMissing ) {
				addIssue( messageForFUN_PARAM_MISSING_TYPE_NAME_FOR_OPTIONAL, tfield.typeRef, FUN_PARAM_MISSING_TYPE_NAME_FOR_OPTIONAL )
			}
		}
	}


	/** IDEBUG-211 invalid combination of undefined, variadic & omitting type */
	def holdsModifierOfParamsHaveType(EList<FormalParameter> list) {
		for(fp:list) {
			if(  fp.declaredTypeRef?.undefModifier == UndefModifier.OPTIONAL) {
				fp.declaredTypeRef.addIssueIfNoDeclaredOrUsableType
				if(fp.variadic) {
					addIssue(messageForFUN_PARAM_INVALID_COMBINATION_OF_TYPE_MODIFIERS,fp,FUN_PARAM_INVALID_COMBINATION_OF_TYPE_MODIFIERS)
				}
			}

		}
	}

	/** IDEBUG-211 invalid combination of undefined, variadic & omitting type */
	def holdsModifierOfParamsHaveTType(List<TFormalParameter> list) {
		for(fp:list) {
			if(  fp.typeRef?.undefModifier == UndefModifier.OPTIONAL) {
				fp.typeRef.addIssueIfNoDeclaredOrUsableType
				if(fp.variadic) {
					addIssue(messageForFUN_PARAM_INVALID_COMBINATION_OF_TYPE_MODIFIERS,fp,FUN_PARAM_INVALID_COMBINATION_OF_TYPE_MODIFIERS)
				}
			}

		}
	}

	/* Part of the holdsModifierOfParamsHaveXX test. Ensures a usable type information is donated by either a
	 * declaredType, a union/intersection, a ThisTypeRefStructural or a FunctionTypeExpression.*/
	private def addIssueIfNoDeclaredOrUsableType(TypeRef typeRef) {
		if( typeRef.isMissing ) {
			addIssue( messageForFUN_PARAM_MISSING_TYPE_NAME_FOR_OPTIONAL, typeRef, FUN_PARAM_MISSING_TYPE_NAME_FOR_OPTIONAL )
		}
	}

	def boolean isMissing(TypeRef typeRef) {
		!(typeRef instanceof FunctionTypeExpression)
			&& !(typeRef instanceof ThisTypeRef)
			&& !(typeRef instanceof ComposedTypeRef)
			&& !(typeRef instanceof ClassifierTypeRef)
			&& typeRef.declaredType === null
	}

	/**
	 * IDE-1534 Only Promise allowed as inferred return type of an async {@link FunctionDefinition}
	 */
	@Check
	def checkNonVoidAsyncMethod(FunctionDefinition funDef) {
		if (funDef.isAsync && (null !== funDef.definedType)) {
			val TypeRef tfunctionRetType = (funDef.definedType as TFunction).getReturnTypeRef();
			if (TypeUtils.isVoid(tfunctionRetType)) {
				val message = messageForTYS_NON_VOID_ASYNC
				addIssue(message, funDef, TYS_NON_VOID_ASYNC)
			}
		}
	}

	/**
	 * IDE-1534 The return type of an async {@link FunctionDefinition} is not allowed to refer to the this-type.
	 */
	@Check
	def checkNoThisAsyncMethod(FunctionDefinition funDef) {
		if (funDef.isAsync) {
			if (TypeUtils.isOrContainsThisType(funDef.returnTypeRef)) {
				val message = messageForTYS_NON_THIS_ASYNC
				addIssue(message, funDef, TYS_NON_THIS_ASYNC)
			}
		}
	}

	// publish this method.
	public override void addIssue(String message, EObject source, EStructuralFeature feature, String issueCode,
			String... issueData) {
				super.addIssue(message,source,feature,issueCode,issueData)
	}

	/** IDE-1735 restrict content in filling modules. */
	@Check
	def checkFunctionDeclarationInStaticPolyfillModule(FunctionDeclaration functionDeclaration) {
		internalCheckNotInStaticPolyfillModule(functionDeclaration, this)
	}
	
	

}
