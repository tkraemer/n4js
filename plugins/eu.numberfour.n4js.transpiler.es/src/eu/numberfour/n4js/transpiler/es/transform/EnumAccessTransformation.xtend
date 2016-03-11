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

import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.transpiler.Transformation
import eu.numberfour.n4js.transpiler.im.IdentifierRef_IM
import eu.numberfour.n4js.transpiler.im.ParameterizedPropertyAccessExpression_IM
import eu.numberfour.n4js.transpiler.im.SymbolTableEntryOriginal
import eu.numberfour.n4js.ts.types.TEnum
import eu.numberfour.n4js.ts.types.TEnumLiteral
import eu.numberfour.n4js.ts.types.TMember
import java.util.Map

import static eu.numberfour.n4js.transpiler.TranspilerBuilderBlocks.*

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * Transforms references to literals of <code>@StringBased</code> enums by replacing them with a plain string literal.
 * References to ordinary enums (i.e. not string-based) are not touched by this transformation.
 */
class EnumAccessTransformation extends Transformation {

	private final Map<TEnum, VariableDeclaration> literalsConstants = newHashMap; // need not be linked
	private TMember member_StringBasedEnum_literals;


	override assertPreConditions() {
		member_StringBasedEnum_literals = state.G.n4StringBasedEnumType.findOwnedMember("literals", false, true);
		assertNotNull("member of built-in type not found: StringBasedEnum#literals", member_StringBasedEnum_literals);
	}

	override assertPostConditions() {
	}

	override analyze() {
	}

	override transform() {
		collectNodes(state.im, ParameterizedPropertyAccessExpression_IM, true).forEach[transformEnumLiteralAccess];
	}

	def private void transformEnumLiteralAccess(ParameterizedPropertyAccessExpression_IM expr) {
		val target = expr.target;
		if(target instanceof IdentifierRef_IM) {
			val idSTE = target.id_IM;
			val id = if(idSTE instanceof SymbolTableEntryOriginal) idSTE.originalTarget;
			if(id instanceof TEnum && (id as TEnum).isStringBased) {
				val tEnum = id as TEnum;
				val propSTE = expr.property_IM;
				val prop = if(propSTE instanceof SymbolTableEntryOriginal) propSTE.originalTarget;
				if(prop instanceof TEnumLiteral) {
					// case 1: reference to an enum literal
					// so, 'expr' is something like "Color.RED" with Color being a string-based enum,
					// and RED one of its literals
					// --> turn this into a plain string literal
					replace(expr, prop.enumLiteralToStringLiteral);
				} else if(prop === member_StringBasedEnum_literals) {
					// case 2: references to getter 'literals' of an enum
					// --> turn this into an array literal of string literals representing the enum's literals
					replace(expr, getReferenceToLiteralsConstant(tEnum));
				}
			}
		}
	}


	def private getReferenceToLiteralsConstant(TEnum tEnum) {
		var vdecl = literalsConstants.get(tEnum);
		if(vdecl===null) {
			vdecl = createLiteralsConstant(tEnum);
			literalsConstants.put(tEnum, vdecl);
		}
		// note: always have to create a new reference
		return _IdentRef(findSymbolTableEntryForElement(vdecl, false));
	}

	def private VariableDeclaration createLiteralsConstant(TEnum tEnum) {
		val name = findUniqueNameForLiteralsConstant(tEnum);
		val vdecl = _VariableDeclaration(name, _ArrLit(tEnum.literals.map[enumLiteralToStringLiteral]));
		state.info.markAsToHoist(vdecl);
		val vstmnt = _VariableStatement(vdecl);
		val lastImport = state.im.scriptElements.filter(ImportDeclaration).last;
		if(lastImport!==null) {
			insertAfter(lastImport, vstmnt);
		} else if(!state.im.scriptElements.empty) {
			insertBefore(state.im.scriptElements.head, vstmnt);
		} else {
			state.im.scriptElements.add(vstmnt);
		}
		createSymbolTableEntryIMOnly(vdecl);
		return vdecl;
	}
	def private String findUniqueNameForLiteralsConstant(TEnum tEnum) {
		val names = literalsConstants.values.map[name].toSet;
		var newName = "$enumLiteralsOf" + (tEnum?.name ?: "Unnamed");
		if(names.contains(newName)) {
			var cnt = 1;
			while(names.contains(newName + cnt)) {
				cnt++;
			}
			newName = newName + cnt;
		}
		return newName;
	}

	def private boolean isStringBased(TEnum tEnum) {
		return AnnotationDefinition.STRING_BASED.hasAnnotation(tEnum);
	}
	def private StringLiteral enumLiteralToStringLiteral(TEnumLiteral enumLiteral) {
		return _StringLiteral(enumLiteral?.value ?: enumLiteral.name);
	}
}
