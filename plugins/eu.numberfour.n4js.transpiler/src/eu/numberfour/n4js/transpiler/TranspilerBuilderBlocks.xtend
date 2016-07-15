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
package eu.numberfour.n4js.transpiler

import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.AdditiveExpression
import eu.numberfour.n4js.n4JS.AdditiveOperator
import eu.numberfour.n4js.n4JS.AnnotableN4MemberDeclaration
import eu.numberfour.n4js.n4JS.Annotation
import eu.numberfour.n4js.n4JS.AnnotationList
import eu.numberfour.n4js.n4JS.Argument
import eu.numberfour.n4js.n4JS.ArrayElement
import eu.numberfour.n4js.n4JS.ArrayLiteral
import eu.numberfour.n4js.n4JS.AssignmentExpression
import eu.numberfour.n4js.n4JS.AssignmentOperator
import eu.numberfour.n4js.n4JS.BinaryLogicalExpression
import eu.numberfour.n4js.n4JS.BinaryLogicalOperator
import eu.numberfour.n4js.n4JS.Block
import eu.numberfour.n4js.n4JS.BooleanLiteral
import eu.numberfour.n4js.n4JS.CommaExpression
import eu.numberfour.n4js.n4JS.ConditionalExpression
import eu.numberfour.n4js.n4JS.EqualityExpression
import eu.numberfour.n4js.n4JS.EqualityOperator
import eu.numberfour.n4js.n4JS.ExportDeclaration
import eu.numberfour.n4js.n4JS.ExportableElement
import eu.numberfour.n4js.n4JS.ExportedVariableStatement
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.ExpressionAnnotationList
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.FunctionExpression
import eu.numberfour.n4js.n4JS.FunctionOrFieldAccessor
import eu.numberfour.n4js.n4JS.IfStatement
import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.n4JS.ImportSpecifier
import eu.numberfour.n4js.n4JS.IndexedAccessExpression
import eu.numberfour.n4js.n4JS.IntLiteral
import eu.numberfour.n4js.n4JS.N4EnumDeclaration
import eu.numberfour.n4js.n4JS.N4EnumLiteral
import eu.numberfour.n4js.n4JS.N4JSFactory
import eu.numberfour.n4js.n4JS.N4MemberAnnotationList
import eu.numberfour.n4js.n4JS.N4MemberDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.N4Modifier
import eu.numberfour.n4js.n4JS.N4SetterDeclaration
import eu.numberfour.n4js.n4JS.NamedImportSpecifier
import eu.numberfour.n4js.n4JS.NamespaceImportSpecifier
import eu.numberfour.n4js.n4JS.NewExpression
import eu.numberfour.n4js.n4JS.NumericLiteral
import eu.numberfour.n4js.n4JS.ObjectLiteral
import eu.numberfour.n4js.n4JS.ParameterizedCallExpression
import eu.numberfour.n4js.n4JS.ParenExpression
import eu.numberfour.n4js.n4JS.PropertyAssignment
import eu.numberfour.n4js.n4JS.PropertyNameValuePair
import eu.numberfour.n4js.n4JS.RelationalExpression
import eu.numberfour.n4js.n4JS.RelationalOperator
import eu.numberfour.n4js.n4JS.ReturnStatement
import eu.numberfour.n4js.n4JS.Statement
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.SuperLiteral
import eu.numberfour.n4js.n4JS.ThisLiteral
import eu.numberfour.n4js.n4JS.ThrowStatement
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.n4JS.UnaryOperator
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.n4JS.VariableStatement
import eu.numberfour.n4js.n4JS.VariableStatementKeyword
import eu.numberfour.n4js.n4JS.YieldExpression
import eu.numberfour.n4js.transpiler.im.IdentifierRef_IM
import eu.numberfour.n4js.transpiler.im.ImFactory
import eu.numberfour.n4js.transpiler.im.ParameterizedPropertyAccessExpression_IM
import eu.numberfour.n4js.transpiler.im.ParameterizedTypeRef_IM
import eu.numberfour.n4js.transpiler.im.Snippet
import eu.numberfour.n4js.transpiler.im.SymbolTableEntry
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.TExportableElement
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TModule
import eu.numberfour.n4js.ts.types.TSetter
import eu.numberfour.n4js.ts.utils.TypeUtils
import java.math.BigDecimal
import java.util.List

/**
 * Builder methods for intermediate elements.
 */
public class TranspilerBuilderBlocks
{

	// ############################################################################################
	// n4js.xcore

	public static def ImportDeclaration _ImportDecl(TModule importedModule, ImportSpecifier... importSpecifiers) {
		val result = N4JSFactory.eINSTANCE.createImportDeclaration;
		result.module = importedModule;
		result.importSpecifiers += importSpecifiers.filterNull;
		return result;
	}

	public static def NamedImportSpecifier _NamedImportSpecifier(TExportableElement importedElement, String alias, boolean usedInCode) {
		val result = N4JSFactory.eINSTANCE.createNamedImportSpecifier;
		result.importedElement = importedElement;
		result.alias = alias;
		result.flaggedUsedInCode = usedInCode;
		return result;
	}

	public static def NamespaceImportSpecifier _NamespaceImportSpecifier(String namespaceName, boolean usedInCode) {
		val result = N4JSFactory.eINSTANCE.createNamespaceImportSpecifier;
		result.alias = namespaceName;
		result.flaggedUsedInCode = usedInCode;
		return result;
	}

	public static def VariableStatement _VariableStatement(boolean exported, VariableDeclaration... varDecls) {
		if(exported) {
			return _ExportedVariableStatement(varDecls);
		} else {
			return _VariableStatement(varDecls);
		}
	}
	public static def VariableStatement _VariableStatement(VariableDeclaration... varDecls) {
		return _VariableStatement(VariableStatementKeyword.VAR, varDecls);
	}

	public static def VariableStatement _VariableStatement(VariableStatementKeyword keyword, VariableDeclaration... varDecls) {
		val result = N4JSFactory.eINSTANCE.createVariableStatement;
		result.varStmtKeyword = keyword;
		result.varDeclsOrBindings += varDecls;
		return result;
	}

	public static def ExportedVariableStatement _ExportedVariableStatement(VariableDeclaration... varDecls) {
		val result = N4JSFactory.eINSTANCE.createExportedVariableStatement;
		result.varStmtKeyword = VariableStatementKeyword.VAR;
		result.varDeclsOrBindings += varDecls.filterNull;
		return result;
	}

	public static def VariableDeclaration _VariableDeclaration(String name) {
		val result = N4JSFactory.eINSTANCE.createVariableDeclaration;
		result.name = name;
		return result;
	}

	public static def VariableDeclaration _VariableDeclaration(String name, Expression exp) {
		val result = N4JSFactory.eINSTANCE.createVariableDeclaration;
		result.name = name;
		result.expression = exp;
		return result;
	}

	public static def ExportDeclaration _ExportDeclaration(ExportableElement exported) {
		val result = N4JSFactory.eINSTANCE.createExportDeclaration;
		result.exportedElement = exported
		return result;
	}

	public static def ReturnStatement _ReturnStmnt() {
		val result = N4JSFactory.eINSTANCE.createReturnStatement;
		return result;
	}

	public static def ReturnStatement _ReturnStmnt(Expression expr) {
		val result = N4JSFactory.eINSTANCE.createReturnStatement;
		result.expression = expr;
		return result;
	}

	public static def IfStatement _IfStmnt(Expression condition, Statement ifStmnt) {
		return _IfStmnt(condition, ifStmnt, null);
	}

	public static def IfStatement _IfStmnt(Expression condition, Statement ifStmnt, Statement elseStmnt) {
		val result = N4JSFactory.eINSTANCE.createIfStatement;
		result.expression = condition;
		result.ifStmt = ifStmnt;
		result.elseStmt = elseStmnt;
		return result;
	}

	public static def ThrowStatement _ThrowStmnt(Expression expression) {
		val result = N4JSFactory.eINSTANCE.createThrowStatement;
		result.expression = expression;
		return result;
	}

	public static def ConditionalExpression _ConditionalExpr(Expression condition, Expression trueExpr, Expression falseExpr) {
		val result = N4JSFactory.eINSTANCE.createConditionalExpression;
		result.expression = condition;
		result.trueExpression = trueExpr;
		result.falseExpression = falseExpr;
		return result;
	}

	public static def YieldExpression _YieldExpr(Expression expr) {
		val result = N4JSFactory.eINSTANCE.createYieldExpression;
		result.expression = expr;
		return result;
	}

	public static def ParameterizedCallExpression _CallExpr() {
		val result = N4JSFactory.eINSTANCE.createParameterizedCallExpression;
		return result;
	}

	public static def ParameterizedCallExpression _CallExpr(Expression target, Expression... arguments) {
		val result = N4JSFactory.eINSTANCE.createParameterizedCallExpression;
		result.target = target;
		result.arguments += arguments.filterNull.map[_Argument];
		return result;
	}
	
	public static def Argument _Argument(Expression expression) {
		return _Argument(false, expression);
	}
	public static def Argument _Argument(boolean spread, Expression expression) {
		val result = N4JSFactory.eINSTANCE.createArgument;
		result.spread = spread;
		result.expression = expression;
		return result;
	}

	public static def ExpressionStatement _ExprStmnt(Expression expr) {
		val result = N4JSFactory.eINSTANCE.createExpressionStatement;
		result.expression = expr;
		return result;
	}

	public static def AssignmentExpression _AssignmentExpr() {
		val result = N4JSFactory.eINSTANCE.createAssignmentExpression;
		result.op = AssignmentOperator.ASSIGN;
		return result;
	}

	public static def AssignmentExpression _AssignmentExpr(Expression lhs, Expression rhs) {
		val result = N4JSFactory.eINSTANCE.createAssignmentExpression;
		result.lhs = lhs;
		result.op = AssignmentOperator.ASSIGN;
		result.rhs = rhs;
		return result;
	}

	public static def ParameterizedPropertyAccessExpression_IM _PropertyAccessExpr() {
		val result = ImFactory.eINSTANCE.createParameterizedPropertyAccessExpression_IM;
		return result;
	}

	public static def ParameterizedPropertyAccessExpression_IM _PropertyAccessExpr(SymbolTableEntry target,
		SymbolTableEntry... properties) {
		return _PropertyAccessExpr(_IdentRef(target), properties);
	}

	public static def ParameterizedPropertyAccessExpression_IM _PropertyAccessExpr(Expression target,
		SymbolTableEntry... properties) {
		if(properties===null || properties.exists[it===null]) {
			throw new IllegalArgumentException("none of the properties may be null")
		}
		var result = ImFactory.eINSTANCE.createParameterizedPropertyAccessExpression_IM;
		result.target = target;
		if(properties.length>0) {
			result.rewiredTarget = properties.get(0);
			for(idx : 1..<properties.length) {
				val newResult = ImFactory.eINSTANCE.createParameterizedPropertyAccessExpression_IM;
				newResult.target = result;
				newResult.rewiredTarget = properties.get(idx);
				result = newResult;
			}
		}
		return result;
	}

	public static def IndexedAccessExpression _IndexAccessExpr() {
		return _IndexAccessExpr(null as Expression, null);
	}

	public static def IndexedAccessExpression _IndexAccessExpr(SymbolTableEntry target, Expression index) {
		return _IndexAccessExpr(_IdentRef(target), index);
	}

	public static def IndexedAccessExpression _IndexAccessExpr(Expression target, Expression index) {
		val result = N4JSFactory.eINSTANCE.createIndexedAccessExpression;
		result.target = target;
		result.index = index;
		return result;
	}

	public static def NewExpression _NewExpr(Expression callee, Expression... arguments) {
		val result = N4JSFactory.eINSTANCE.createNewExpression;
		result.callee = callee;
		result.withArgs = !arguments.filterNull.empty;
		result.arguments += arguments.filterNull.map[_Argument];
		return result;
	}

	public static def RelationalExpression _RelationalExpr(Expression lhs, RelationalOperator op, Expression rhs) {
		val result = N4JSFactory.eINSTANCE.createRelationalExpression;
		result.lhs = lhs;
		result.op = op;
		result.rhs = rhs;
		return result;
	}

	public static def EqualityExpression _EqualityExpr(Expression lhs, EqualityOperator op, Expression rhs) {
		val result = N4JSFactory.eINSTANCE.createEqualityExpression;
		result.lhs = lhs;
		result.op = op;
		result.rhs = rhs;
		return result;
	}

	public static def UnaryExpression _NOT(Expression expr) {
		return _UnaryExpr(UnaryOperator.NOT, expr);
	}
	public static def BinaryLogicalExpression _OR(Expression lhs, Expression rhs) {
		return _BinaryLogicalExpr(lhs, BinaryLogicalOperator.OR, rhs);
	}
	public static def BinaryLogicalExpression _AND(Expression lhs, Expression rhs) {
		return _BinaryLogicalExpr(lhs, BinaryLogicalOperator.AND, rhs);
	}
	public static def UnaryExpression _UnaryExpr(UnaryOperator op, Expression expr) {
		val result = N4JSFactory.eINSTANCE.createUnaryExpression;
		result.op = op;
		result.expression = expr;
		return result;
	}
	public static def BinaryLogicalExpression _BinaryLogicalExpr(Expression lhs, BinaryLogicalOperator op, Expression rhs) {
		val result = N4JSFactory.eINSTANCE.createBinaryLogicalExpression;
		result.lhs = lhs;
		result.op = op;
		result.rhs = rhs;
		return result;
	}
	public static def CommaExpression _CommaExpression(Expression ...expressions) {
		val result = N4JSFactory.eINSTANCE.createCommaExpression;
		result.exprs += expressions;
		return result;
	}

	public static def AdditiveExpression _AdditiveExpression(Expression lhs, AdditiveOperator op, Expression rhs) {
		val result = N4JSFactory.eINSTANCE.createAdditiveExpression;
		result.lhs = lhs;
		result.op = op;
		result.rhs = rhs;
		return result;
	}
	public static def AdditiveExpression _AdditiveExpression(AdditiveOperator op, Expression... operands) {
		if(operands===null || operands.exists[it===null]) {
			throw new IllegalArgumentException("none of the operands may be null")
		}
		if(operands.size<2) {
			throw new IllegalArgumentException("need at least two operands")
		}
		var result = N4JSFactory.eINSTANCE.createAdditiveExpression;
		result.lhs = operands.get(0);
		result.op = op;
		result.rhs = operands.get(1);
		for(idx : 2..<operands.length) {
			val newResult = N4JSFactory.eINSTANCE.createAdditiveExpression;
			newResult.lhs = result;
			newResult.op = op;
			newResult.rhs = operands.get(idx);
			result = newResult;
		}
		return result;
	}


	public static def ObjectLiteral _ObjLit() { // required to resolve the ambiguity between the other two methods
		return _ObjLit(null as PropertyAssignment[]);
	}

	/**
	 * Convenience method for creating object literals that only contain {@link PropertyNameValuePair}s.
	 * It is legal to pass in one or more <code>null</code> values (they will be ignored).
	 */
	public static def ObjectLiteral _ObjLit(Pair<String,Expression>... nameValuePairs) {
		return _ObjLit(nameValuePairs.filterNull.map[_PropertyNameValuePair(key,value)]);
	}

	public static def ObjectLiteral _ObjLit(PropertyAssignment... pas) {
		val result = N4JSFactory.eINSTANCE.createObjectLiteral;
		if(pas!==null) {
			result.propertyAssignments += pas.filterNull;
		}
		return result;
	}

	public static def PropertyNameValuePair _PropertyNameValuePair(String name, Expression value) {
		val result = N4JSFactory.eINSTANCE.createPropertyNameValuePair;
		result.declaredName = _LiteralOrComputedPropertyName(name);
		result.expression = value;
		return result;
	}

	public static def ArrayLiteral _ArrLit() { // required to resolve the ambiguity between the other two methods
		return _ArrLit(null as ArrayElement[]);
	}

	public static def ArrayLiteral _ArrLit(Expression... elements) {
		return _ArrLit(elements.filterNull.map[_ArrayElement(it)]);
	}

	public static def ArrayLiteral _ArrLit(ArrayElement... elements) {
		val result = N4JSFactory.eINSTANCE.createArrayLiteral;
		if(elements!==null) {
			result.elements += elements.filterNull;
		}
		return result;
	}

 	public static def ArrayElement _ArrayElement(Expression expression) {
		return _ArrayElement(false, expression);
	}
	public static def ArrayElement _ArrayElement(boolean spread, Expression expression) {
 		val result = N4JSFactory.eINSTANCE.createArrayElement;
		result.spread = spread;
 		result.expression = expression;
 		return result;
 	}

	public static def FunctionDeclaration _FunDecl(String name, Statement... statements) {
		return _FunDecl(name, #[], statements);
	}

	public static def FunctionDeclaration _FunDecl(String name, FormalParameter[] fpars, Statement... statements) {
		val result = N4JSFactory.eINSTANCE.createFunctionDeclaration;
		result.name = name;
		result.fpars += fpars;
		result.body = _Block(statements);
		return result;
	}

	public static def FunctionExpression _FunExpr(boolean async, Statement... statements) {
		return _FunExpr(async, null, #[], statements);
	}

	public static def FunctionExpression _FunExpr(boolean async, String name, Statement... statements) {
		return _FunExpr(async, name, #[], statements);
	}

	public static def FunctionExpression _FunExpr(boolean async, String name, FormalParameter[] fpars, Statement... statements) {
		if(statements !== null && statements.length===1 && statements.get(0) instanceof Block) {
			// safe guard: in case complex EMF inheritance hierarchy causes wrong overload to be invoked
			return _FunExprWithBlock(async, name, fpars, statements.get(0) as Block);
		}
		val result = N4JSFactory.eINSTANCE.createFunctionExpression;
		result.declaredAsync = async;
		result.name = name;
		result.fpars += fpars;
		result.body = _Block(statements);
		return result;
	}
	public static def FunctionExpression _FunExpr(boolean async, String name, FormalParameter[] fpars, Block block) {
		return _FunExprWithBlock(async, name, fpars, block);
	}
	private static def FunctionExpression _FunExprWithBlock(boolean async, String name, FormalParameter[] fpars, Block block) {
		val result = N4JSFactory.eINSTANCE.createFunctionExpression;
		result.declaredAsync = async;
		result.name = name;
		result.fpars += fpars;
		result.body = block;
		return result;
	}

	public static def N4MemberDeclaration _N4MemberDecl(TMember template, Statement... statements) {
		if(template instanceof TField && !statements.empty) {
			throw new IllegalArgumentException("fields cannot have statements");
		}
		val result = switch(template) {
			TField: N4JSFactory.eINSTANCE.createN4FieldDeclaration
			TGetter: N4JSFactory.eINSTANCE.createN4GetterDeclaration
			TSetter: N4JSFactory.eINSTANCE.createN4SetterDeclaration
			TMethod: N4JSFactory.eINSTANCE.createN4MethodDeclaration
			default: throw new IllegalArgumentException("unsupported subtype of TMember: " + template.eClass.name)
		};
		// basic properties
		result.declaredName = _LiteralOrComputedPropertyName(template.name);
		// body
		if(result instanceof FunctionOrFieldAccessor) {
			result.body = _Block(statements.filterNull);
		}
		// formal parameters
		if(template instanceof TSetter) {
			val fparName = template.fpar?.name ?: "value";
			(result as N4SetterDeclaration).fpar = _Fpar(fparName);
		}
		if(template instanceof TMethod) {
			(result as N4MethodDeclaration).fpars += template.fpars.map[_Fpar(name)];
			(result as N4MethodDeclaration).declaredAsync = template.declaredAsync;
		}
		// static / non-static
		if(template.static) {
			result.declaredModifiers += N4Modifier.STATIC;
		}
		// access modifiers
		switch(template.memberAccessModifier) {
			case PUBLIC: result.declaredModifiers += N4Modifier.PUBLIC
			case PUBLIC_INTERNAL: {
				result.declaredModifiers += N4Modifier.PUBLIC;
				result.getOrCreateMemberAnnotationList.annotations += _Annotation(AnnotationDefinition.INTERNAL);
			}
			case PROTECTED: result.declaredModifiers += N4Modifier.PROTECTED
			case PROTECTED_INTERNAL: {
				result.declaredModifiers += N4Modifier.PROTECTED;
				result.getOrCreateMemberAnnotationList.annotations += _Annotation(AnnotationDefinition.INTERNAL);
			}
			case PROJECT: result.declaredModifiers += N4Modifier.PROJECT
			case PRIVATE: result.declaredModifiers += N4Modifier.PRIVATE
			case UNDEFINED: {/* NOP */}
		}
		return result;
	}
	private static def N4MemberAnnotationList getOrCreateMemberAnnotationList(AnnotableN4MemberDeclaration memberDecl) {
		var annList = memberDecl.annotationList;
		if(annList===null) {
			annList = N4JSFactory.eINSTANCE.createN4MemberAnnotationList;
			memberDecl.annotationList = annList;
		}
		return annList;
	}

	public static def N4MethodDeclaration _N4MethodDecl(String name, Statement... statements) {
		val result = N4JSFactory.eINSTANCE.createN4MethodDeclaration;
		result.declaredName = _LiteralOrComputedPropertyName(name);
		result.body = _Block(statements.filterNull);
		return result;
	}

	public static def FormalParameter _Fpar() {
		return _Fpar(null, false, null, false);
	}
	public static def FormalParameter _Fpar(String name) {
		return _Fpar(name, false, null, false);
	}
	public static def FormalParameter _Fpar(String name, boolean variadic) {
		return _Fpar(name, variadic, null, false);
	}
	public static def FormalParameter _Fpar(String name, boolean variadic, TypeRef typeRef, boolean isSpecFpar) {
		val result = N4JSFactory.eINSTANCE.createFormalParameter;
		result.name = name;
		result.variadic = variadic;
		result.declaredTypeRef = TypeUtils.copy(typeRef); // ok if typeRef===null
		if(isSpecFpar) {
			result.annotations += _Annotation(AnnotationDefinition.SPEC);
		}
		return result;
	}

	public static def Annotation _Annotation(AnnotationDefinition annDef) {
		val result = N4JSFactory.eINSTANCE.createAnnotation;
		result.name = annDef.name;
		return result;
	}

	public static def AnnotationList _AnnotationList(List<AnnotationDefinition> annDef) {
		val result = N4JSFactory.eINSTANCE.createAnnotationList;
		if( annDef !== null )
			result.annotations += annDef.map[ _Annotation(it) ];
		return result;
	}

	public static def ExpressionAnnotationList _ExprAnnoList(Annotation[] annotations) {
		val result = N4JSFactory.eINSTANCE.createExpressionAnnotationList;
		result.annotations += annotations;
		return result;
	}

	public static def Block _Block(Statement... statements) {
		val result = N4JSFactory.eINSTANCE.createBlock;
		result.statements += statements.filterNull;
		return result;
	}

	public static def ParenExpression _Parenthesis(Expression expr) {
		val result = N4JSFactory.eINSTANCE.createParenExpression;
		result.expression = expr;
		return result;
	}

	public static def BooleanLiteral _TRUE() {
		return _BooleanLiteral(true);
	}
	public static def BooleanLiteral _FALSE() {
		return _BooleanLiteral(false);
	}
	public static def BooleanLiteral _BooleanLiteral(boolean value) {
		val result = N4JSFactory.eINSTANCE.createBooleanLiteral;
		result.^true = value;
		return result;
	}

	public static def NumericLiteral _NumericLiteral(int num) {
		val result = N4JSFactory.eINSTANCE.createNumericLiteral;
		result.value = BigDecimal.valueOf(num);
		return result;
	}

	public static def StringLiteral _StringLiteral(String s, String rawValue) {
		val result = _StringLiteral(s);
		result.rawValue = rawValue;
		return result;
	}
	
	public static def StringLiteral _StringLiteral(String s) {
		val result = N4JSFactory.eINSTANCE.createStringLiteral;
		result.value = s;
		return result;
	}

	public static def StringLiteral _StringLiteralForSTE(SymbolTableEntry symbolTableEntry) {
		return _StringLiteralForSTE(symbolTableEntry, false);
	}
	
	public static def StringLiteral _StringLiteralForSTE(SymbolTableEntry symbolTableEntry, boolean useExportedName) {
		val result = ImFactory.eINSTANCE.createStringLiteralForSTE;
		result.entry = symbolTableEntry;
		result.useExportedName = useExportedName;
		return result;
	}

	public static def IntLiteral _IntLiteral(int i) {
		val result = N4JSFactory.eINSTANCE.createIntLiteral;
		result.value = BigDecimal.valueOf(i);
		return result;
	}

	public static def ThisLiteral _ThisLiteral() {
		val result = N4JSFactory.eINSTANCE.createThisLiteral;
		return result;
	}

	public static def SuperLiteral _SuperLiteral() {
		val result = N4JSFactory.eINSTANCE.createSuperLiteral;
		return result;
	}

	public static def _emptyStatement() {
		return N4JSFactory.eINSTANCE.createEmptyStatement
	}

	/** generic export where the {@code steExportedElement} is exported under its name. */
	public static def _N4ExportExpr(SymbolTableEntry steExportedElement, SymbolTableEntry symbolFor_n4Export )
	{
		return _N4ExportExpr(steExportedElement, _IdentRef( steExportedElement ), symbolFor_n4Export)
	}

	/** export of {@code expression}-to-be-evaluated under the name of {@code steExportedElement} */
	public static def _N4ExportExpr(SymbolTableEntry steExportedElement, Expression expression, SymbolTableEntry symbolFor_n4Export )
	{
		return _CallExpr => [
			target = _IdentRef(symbolFor_n4Export)
			arguments += _Argument(_StringLiteralForSTE( steExportedElement, true ))
			arguments += _Argument(expression)
		]
	}


	public static def N4EnumDeclaration _EnumDeclaration(String name, List<N4EnumLiteral> literals) {
		val result = N4JSFactory.eINSTANCE.createN4EnumDeclaration;
		result.name = name;
		result.literals += literals;
		return result;
	}

	public static def _EnumLiteral(String name, String value) {
		val result = N4JSFactory.eINSTANCE.createN4EnumLiteral;
		result.name = name;
		result.value = value;
		return result;
	}

	public static def _N4ClassDeclaration(String name){
		val result = N4JSFactory.eINSTANCE.createN4ClassDeclaration;
		result.name = name;
		return result;
	}

	public static def _N4InterfaceDeclaration(String name){
		val result = N4JSFactory.eINSTANCE.createN4InterfaceDeclaration;
		result.name = name;
		return result;
	}

	public static def _LiteralOrComputedPropertyName(String name) {
		val result = N4JSFactory.eINSTANCE.createLiteralOrComputedPropertyName;
		result.literalName = name;
		return result;
	}

	// ############################################################################################
	// IM.xcore

	public static def IdentifierRef_IM _IdentRef(SymbolTableEntry symbolTableEntry) {
		if(symbolTableEntry===null) {
			throw new IllegalArgumentException("when creating an IdentifierRef_IM: symbol table entry may not be null");
		}
		val result = ImFactory.eINSTANCE.createIdentifierRef_IM;
		result.rewiredTarget = symbolTableEntry;
		return result;
	}

	public static def ParameterizedTypeRef_IM _ParameterizedTypeRef(SymbolTableEntry symbolTableEntry) {
		if(symbolTableEntry===null) {
			throw new IllegalArgumentException("when creating an ParameterizedTypeRef_IM: symbol table entry may not be null");
		}
		val result = ImFactory.eINSTANCE.createParameterizedTypeRef_IM;
		result.rewiredTarget = symbolTableEntry;
		return result;
	}

	public static def SymbolTableEntry _SymbolTableEntry(String name) {
		throw new UnsupportedOperationException("do not manually create symbol table entries; use methods #createSymbolTableEntry() or #getSymbolTableEntry() instead");
	}

	public static def ExpressionStatement _SnippetAsStmnt(String code) {
		return _ExprStmnt(_Snippet(code));
	}

	public static def Snippet _Snippet(String code) {
		val result = ImFactory.eINSTANCE.createSnippet;
		result.code = code;
		return result;
	}
}
