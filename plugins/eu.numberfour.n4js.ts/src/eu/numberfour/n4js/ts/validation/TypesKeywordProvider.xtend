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
package eu.numberfour.n4js.ts.validation

import com.google.inject.Singleton
import eu.numberfour.n4js.ts.types.BuiltInType
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
import eu.numberfour.n4js.ts.types.TSetter
import eu.numberfour.n4js.ts.types.TStructField
import eu.numberfour.n4js.ts.types.TStructGetter
import eu.numberfour.n4js.ts.types.TStructSetter
import eu.numberfour.n4js.ts.types.TVariable
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.types.TypeAccessModifier
import eu.numberfour.n4js.ts.types.TypeVariable
import org.eclipse.emf.ecore.EObject

/**
 * Helper returning the keyword of a given AST or type element, e.g., "class" for a class declaration.
 */
@Singleton
class TypesKeywordProvider {

	def dispatch String keyword(EObject eobj) {
		val modelName = eobj.eClass.name;
		val strb = new StringBuilder(modelName.length+1);
		for (var i=0; i<modelName.length; i++) {
			val c = modelName.charAt(i);
			if (Character.isUpperCase(c)) {
				if (i>0) {
					strb.append(' ')
				};
				strb.append(Character.toLowerCase(c));
			} else {
				strb.append(c);
			}
		}
		return strb.toString();
	}

	def dispatch String keyword(TClass n4Class) {
		"class"
	}

	def dispatch String keyword(TEnum tEnum) {
		"enum"
	}

	def dispatch String keyword(TInterface n4Interface) {
		"interface"
	}

	def dispatch String keyword(TClassifier n4Classifier) {
		"classifier"
	}

	def dispatch String keyword(TMember n4Member) {
		"member"
	}

	def dispatch String keyword(TStructField structField) {
		"structural field"
	}

	def dispatch String keyword(TStructGetter structGetter) {
		"structural getter"
	}

	def dispatch String keyword(TStructSetter structSetter) {
		"structural setter"
	}

	def dispatch String keyword(TField n4Field) {
		"field"
	}

	def dispatch String keyword(TMethod n4Method) {
		"method"
	}

	def dispatch String keyword(TFunction function) {
		"function"
	}

	def dispatch String keyword(TGetter n4Getter) {
		"getter"
	}

	def dispatch String keyword(TSetter n4Setter) {
		"setter"
	}

	def dispatch String keyword(TVariable variable) {
		"variable"
	}

	def dispatch String keyword(TFormalParameter parameter) {
		"parameter"
	}

	def dispatch String keyword(TypeVariable variable) {
		"type variable"
	}

	def dispatch String keyword(PrimitiveType primitive) {
		"primitive"
	}

	def dispatch String keyword(BuiltInType primitive) {
		"built-in type"
	}

	// note: following method will also be required in case of unresolved proxies
	def dispatch String keyword(Type other) {
		"type"
	}

	def dispatch String keyword(TypeAccessModifier accessModifier) {
		return switch (accessModifier) {
			case PUBLIC_INTERNAL: "@Internal public"
			default: accessModifier.getName()
		}
	}

	def dispatch String keyword(MemberAccessModifier accessModifier) {
		return switch (accessModifier) {
			case PUBLIC_INTERNAL: "@Internal public"
			case PROTECTED_INTERNAL: "@Internal protected"
			default: accessModifier.getName()
		}
	}

}
