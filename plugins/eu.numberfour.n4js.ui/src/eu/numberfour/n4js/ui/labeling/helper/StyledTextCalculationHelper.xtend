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
package eu.numberfour.n4js.ui.labeling.helper

import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.n4JS.N4SetterDeclaration
import eu.numberfour.n4js.ui.labeling.N4JSLabelProvider
import eu.numberfour.n4js.ts.types.TFormalParameter
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TypeVariable
import java.util.List
import org.eclipse.jface.viewers.StyledString
import org.eclipse.xtext.ui.label.AbstractLabelProvider

/**
 * This helper class serves as replacement for the polymorphic dispatch done
 * by {@link AbstractLabelProvider} in favor of
 * Xtend dispatch methods. Here the dispatch of styled labels is done.
 * It is called in {@link N4JSLabelProvider#getStyledText}. getStyledText
 * calls {@link N4JSLabelProvider#doGetText} so in most cases its good
 * practice to call here getLabelProvider.getSuperStyledText to keep this
 * behavior. By this you can reuse the label calculated by
 * {@link LabelCalculationHelper} so far. Here you can then append the styled
 * parts of the label (so its still possible to append non styled strings as
 * well).
 * <br /><br />
 * General use case for styled string is the highlighting of inferred types
 * in the outline (to see the difference to declared types). So styled texts
 * are created for fields, methods, getters, setters and exported variables
 * as only those can have types.
 */
class StyledTextCalculationHelper {
	private N4JSLabelProvider labelProvider;

	def setLabelProvider(N4JSLabelProvider provider) {
		this.labelProvider = provider;
	}

	def getLabelProvider() {
		return this.labelProvider
	}

	/**
	 * Should not happen as this will lead to consequential errors, still added here to make helper more robust.
	 */
	def dispatch StyledString dispatchGetStyledText(Void _null) {
		return new StyledString("*error*")
	}

	// fallback
	def dispatch StyledString dispatchGetStyledText(Object element) {
		getLabelProvider.getSuperStyledText(element)
	}

	// produces e.g. functionName(param1TypeName, param2TypeName) : returnTypeName
	def dispatch StyledString dispatchGetStyledText(FunctionDefinition functionDefinition) {
		var styledText = getLabelProvider.getSuperStyledText(functionDefinition)
		val definedType = functionDefinition.definedType
		if(definedType instanceof TFunction) {
			val typeStr = (if(!definedType.constructor && definedType.returnTypeRef !== null) " : " + (
				if (definedType.returnTypeRef?.declaredType?.name!==null) {
					definedType.returnTypeRef.declaredType.name
				} else {
					""
				}
			) else "")
			styledText = styledText.appendStyledTextForFormalParameters(functionDefinition, definedType)
			styledText = styledText.append(definedType.typeVars.handleTypeVars)
			return if(functionDefinition.returnTypeRef?.declaredType === null) {
				// type was inferred
				styledText.append(typeStr, StyledString.DECORATIONS_STYLER)
			} else {
				styledText.append(typeStr)
			}
		}
		return styledText
	}

	// produces e.g. <T, U>
	def private handleTypeVars(List<TypeVariable> typeVars) {
		if(typeVars.size > 0) " <" + typeVars.map[name].join(", ") + ">" else ""
	}

	// produces e.g. getterName() : returnTypeName
	def dispatch StyledString dispatchGetStyledText(N4GetterDeclaration n4GetterDeclaration) {
		var styledText = getLabelProvider.getSuperStyledText(n4GetterDeclaration)
		val definedGetter = n4GetterDeclaration.definedGetter
		val typeStr = (if(definedGetter.declaredTypeRef !== null) " : "
			+ (definedGetter.declaredTypeRef?.declaredType?.name?:"<unknown>") else "")
		return if(n4GetterDeclaration.declaredTypeRef?.declaredType === null) {
			// type was inferred
			styledText.append(typeStr, StyledString.DECORATIONS_STYLER)
		} else {
			styledText.append(typeStr)
		}
	}

	// produces e.g. setterName(paramTypeName)
	def dispatch StyledString dispatchGetStyledText(N4SetterDeclaration n4SetterDeclaration) {
		var styledText = getLabelProvider.getSuperStyledText(n4SetterDeclaration)
		val definedSetter = n4SetterDeclaration.definedSetter
		if(n4SetterDeclaration.fpar !== null && definedSetter.fpar !== null) {
			styledText.append("(").append(getStyledTextForFormalParameter(n4SetterDeclaration.fpar, definedSetter.fpar)).append(")")
		}
	}

	// produces e.g. (param1TypeName, param2TypeName)
	def private appendStyledTextForFormalParameters(StyledString styledText, FunctionDefinition n4Function, TFunction tFunction) {
		 (styledText.append("(") => [
		 	if(tFunction.fpars.size > 0) {
			 	it.append((0..tFunction.fpars.size-1).map[
			 		getStyledTextForFormalParameter(n4Function.fpars.get(it), tFunction.fpars.get(it))
			 	].reduce(l, r | l.append(", ").append(r)))
		 	}
		 ]).append(")")
	}

	def private getStyledTextForFormalParameter(FormalParameter formalParameter, TFormalParameter tFormalParameter) {
		return if(formalParameter.declaredTypeRef?.declaredType === null) {
			// type was inferred
			new StyledString(tFormalParameter.typeRef?.declaredType?.name ?: "<unknown>", StyledString.DECORATIONS_STYLER)
		} else {
			new StyledString(formalParameter.declaredTypeRef?.declaredType?.name ?: "<unknown>")
		}
	}

	// produces e.g. fieldName : fieldTypeName
	def dispatch StyledString dispatchGetStyledText(N4FieldDeclaration n4FieldDeclaration) {
		val styledText = getLabelProvider.getSuperStyledText(n4FieldDeclaration)
		val definedField = n4FieldDeclaration.definedField
		val typeStr = (if(definedField?.typeRef !== null) " : "
			+ definedField?.typeRef?.declaredType?.name else "")
		return if(n4FieldDeclaration.declaredTypeRef?.declaredType === null) {
			// type was inferred
			styledText?.append(typeStr, StyledString.DECORATIONS_STYLER)
		} else {
			styledText?.append(typeStr)
		}
	}

	// produces e.g. variableName : variableTypeName
	def dispatch StyledString dispatchGetStyledText(ExportedVariableDeclaration variableDeclaration) {
		val styledText = getLabelProvider.getSuperStyledText(variableDeclaration)
		val definedVariable = variableDeclaration.definedVariable
		val declTypeName = definedVariable?.typeRef?.declaredType?.name;
		val typeStr = (if (declTypeName !== null) " : " + declTypeName else "")
		return if(variableDeclaration.declaredTypeRef?.declaredType === null) {
			// type was inferred
			styledText?.append(typeStr, StyledString.DECORATIONS_STYLER)
		} else {
			styledText?.append(typeStr)
		}
	}
}
