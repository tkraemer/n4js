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
package eu.numberfour.n4js.utils

import eu.numberfour.n4js.n4JS.AbstractAnnotationList
import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration
import eu.numberfour.n4js.n4JS.FormalParameter
import eu.numberfour.n4js.n4JS.FunctionDeclaration
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.N4ClassifierDeclaration
import eu.numberfour.n4js.n4JS.N4EnumLiteral
import eu.numberfour.n4js.n4JS.N4FieldDeclaration
import eu.numberfour.n4js.n4JS.N4GetterDeclaration
import eu.numberfour.n4js.n4JS.N4JSASTUtils
import eu.numberfour.n4js.n4JS.N4MemberAnnotationList
import eu.numberfour.n4js.n4JS.N4MemberDeclaration
import eu.numberfour.n4js.n4JS.N4MethodDeclaration
import eu.numberfour.n4js.n4JS.NumericLiteral
import eu.numberfour.n4js.n4JS.PropertyAssignment
import eu.numberfour.n4js.n4JS.PropertyAssignmentAnnotationList
import eu.numberfour.n4js.n4JS.PropertyMethodDeclaration
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.TypeDefiningElement
import eu.numberfour.n4js.n4JS.UnaryExpression
import eu.numberfour.n4js.n4JS.UnaryOperator
import eu.numberfour.n4js.ts.conversions.ComputedPropertyNameValueConverter
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.types.ContainerType
import eu.numberfour.n4js.ts.types.IdentifiableElement
import eu.numberfour.n4js.ts.types.MemberAccessModifier
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TModule
import eu.numberfour.n4js.ts.types.TStructMember
import eu.numberfour.n4js.ts.types.TVariable
import eu.numberfour.n4js.ts.types.TypableElement
import eu.numberfour.n4js.ts.types.Type
import eu.numberfour.n4js.ts.types.TypeVariable
import eu.numberfour.n4js.ts.types.util.Variance
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions
import it.xsemantics.runtime.RuleEnvironment
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.nodemodel.util.NodeModelUtils

import static eu.numberfour.n4js.validation.helper.N4JSLanguageConstants.CONSTRUCTOR
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.AnnotableElement
import eu.numberfour.n4js.ts.types.TAnnotableElement

/**
 * Intended for small, static utility methods that
 * <ul>
 * <li>need both the AST and types model or code from the main n4js bundle, and can therefore not be put into
 *     {@link N4JSASTUtils} and {@link TypeUtils}.
 * <li>implement a fundamental rule or logic of the core language that defines an important part of N4JS semantics.
 * </ul>
 *
 * @see N4JSASTUtils
 * @see TypeUtils
 */
class N4JSLanguageUtils {

	/**
	 * See {@link ComputedPropertyNameValueConverter#SYMBOL_IDENTIFIER_PREFIX}.
	 */
	public static final String SYMBOL_IDENTIFIER_PREFIX = ComputedPropertyNameValueConverter.SYMBOL_IDENTIFIER_PREFIX;


	/**
	 * If the given function definition is asynchronous, will wrap given return type into a Promise.
	 * Otherwise, returns given return type unchanged.
	 */
	def static TypeRef makePromiseIfAsync(FunctionDefinition functionDef, TypeRef returnTypeRef,
			BuiltInTypeScope builtInTypeScope) {
		if (functionDef !== null && returnTypeRef !== null) {
			if (functionDef.isAsync()) {
				// for async functions with declared return type R: actual return type is Promise<R,?>
				return TypeUtils.createPromiseTypeRef(builtInTypeScope, returnTypeRef, null);
			}
			return returnTypeRef;
		}
		return null;
	}


	/**
	 * Tells if given object is an <em>AST node</em>, i.e. contained below a {@link Script} element.
	 * <p>
	 * Note that it is not possible to tell AST nodes from type model elements only based on the object's type, because
	 * there exist type model entities that may appear as a node in the AST (e.g. some TypeRefs, TStructField).
	 */
	def static boolean isASTNode(EObject obj) {
		// note: despite its name, #getContainerOfType() returns 'obj' if instance of Script
		return EcoreUtil2.getContainerOfType(obj, Script)!==null;
	}

	/**
	 * Tells if given object is a <em>type model element</em>, i.e. is contained below a {@link TModule} element.
	 * <p>
	 * Note that it is not possible to tell AST nodes from type model elements only based on the object's type, because
	 * there exist type model entities that may appear as a node in the AST (e.g. some TypeRefs, TStructField).
	 */
	def static boolean isTypeModelElement(EObject obj) {
		// note: despite its name, #getContainerOfType() returns 'obj' if instance of TModule
		return EcoreUtil2.getContainerOfType(obj, TModule)!==null;
	}

	/**
	 * Tells if given AST node is a typable AST node, i.e. a node that has an (actual) type that can be inferred
	 * using the type system.
	 * <p>
	 * For performance reasons, this method will simply assume {@code astNode} to be an AST node (i.e. contained below
	 * a {@link Script} element) and will not check this again.
	 */
	def static boolean isTypableNode(EObject astNode) {
		astNode instanceof TypableElement && !(astNode instanceof AbstractAnnotationList)
	}

	def static boolean isIdentifiableSubtree(EObject astNode) {
		astNode instanceof IdentifiableElement || astNode.getDefinedTypeModelElement instanceof IdentifiableElement
		|| astNode instanceof FunctionDeclaration
		// classes with compile error, e.g. missing name, do not necessarily have DefinedTypeModelElement
		// thus the additional check below
		|| astNode instanceof N4ClassDeclaration
	}

	def static boolean isTypeModelElementDefiningASTNode(EObject astNode) {
		astNode instanceof ExportedVariableDeclaration
		|| astNode instanceof TypeDefiningElement
		|| (astNode instanceof N4MemberDeclaration && !(astNode instanceof N4MemberAnnotationList))
		|| (astNode instanceof PropertyAssignment && !(astNode instanceof PropertyAssignmentAnnotationList))
		|| astNode instanceof FormalParameter
		|| astNode instanceof TStructMember // they can play the role of AST nodes!
		|| astNode instanceof N4EnumLiteral
	}

	def static EObject getDefinedTypeModelElement(EObject astNode) {
		switch(astNode) {
			ExportedVariableDeclaration: astNode.definedVariable
			PropertyMethodDeclaration: astNode.definedMember
			TypeDefiningElement: astNode.definedType
			N4MemberDeclaration case !(astNode instanceof N4MemberAnnotationList): astNode.definedTypeElement
			PropertyAssignment case !(astNode instanceof PropertyAssignmentAnnotationList): astNode.definedMember
			FormalParameter: astNode.definedTypeElement
			TStructMember case astNode.isASTNode: astNode.definedMember // note: a TStructMember may be an AST node or types model element!
			N4EnumLiteral: astNode.definedLiteral
		}
	}

	/**
	 * Returns with {@code true} if the {@link TMember member} argument represents a constructor.
	 * More precisely, when the argument is an instance of {@link TMethod} and its {@link TMethod#getName() name}
	 * is {@code constructor}. Otherwise returns with {@code false}.
	 */
	def static isConstructor(TMember it) {
		return it instanceof TMethod && CONSTRUCTOR == name;
	}

	/**
	 * Returns with {@code true} if the member argument is a {@link TField} instance and the field is
	 * {@link TField#isWriteable() writable}, otherwise returns with {@code false}.
	 */
	def static isWriteableField(TMember m) {
		return m instanceof TField && m.writeable;
	}

	/**
	 * Returns with {@code true} if the member argument is a {@link TField} instance and the field is
	 * <b>NOT</b> {@link TField#isWriteable() writable}, otherwise returns with {@code false}.
	 */
	def static isReadOnlyField(TMember m) {
		return m instanceof TField && !m.writeable;
	}

	/**
	 * Tells if the given identifiable element is exported.
	 */
	def static boolean isExported(IdentifiableElement elem) {
		return switch(elem) {
			ExportedVariableDeclaration: true
			TVariable: elem.exported
			Type: elem.exported
			default: false
		};
	}

	/**
	 * Is the given TFunction tagged ASYNC, and moreover does it return Promise?
	 */
	def static boolean isAsync(TFunction tfunction, BuiltInTypeScope scope) {
		if (tfunction.declaredAsync) {
			if (tfunction.returnTypeRef instanceof ParameterizedTypeRef) {
				return TypeUtils.isPromise(tfunction.returnTypeRef, scope)
			}
		}
		return false;
	}

	/**
	 * Does the given function-type denote an async function?
	 * (two cases: declared type available or not, in the latter case heuristically assume Promise-returning implies async).
	 * <p>
	 * The declared type (ie, a TFunction) is usually but not always available.
	 */
	def static boolean isAsync(FunctionTypeExprOrRef fteor, RuleEnvironment G) {
		val tfunction = fteor.functionType
		val tscope = RuleEnvironmentExtensions.getPredefinedTypes(G).builtInTypeScope
		if (null === tfunction) {
			return TypeUtils.isPromise(fteor.returnTypeRef, tscope)
		} else {
			return N4JSLanguageUtils.isAsync(tfunction, tscope)
		}
	}


	/**
	 * Either returns the variance of the position where <code>typeRef</code> is located in a classifier declaration or
	 * <code>null</code> if it is located at a position where type variables of all variances may be located, i.e.
	 * a position that need not be checked (e.g. type of local variable, type of a private field).
	 * <p>
	 * Also returns <code>null</code> in case of error, e.g. invalid AST.
	 */
	def public static Variance getVarianceOfPosition(ParameterizedTypeRef typeRef) {
		
		// FIXME only partial implementation for testing, not all cases supported (esp. nesting in wildcards, etc.)
		
		if(typeRef===null)
			return null;
		val tv = typeRef.declaredType as TypeVariable;
		val tClassifier = tv.eContainer as TClassifier; // safety of this cast not checked above, but should be safe bet
		val parent = typeRef.eContainer;
		val grandParent = parent?.eContainer;
		return switch(parent) {
			FormalParameter case parent.declaredTypeRef===typeRef && grandParent.isNonPrivateMemberOf(tClassifier):
				Variance.CONTRA
			N4MethodDeclaration case parent.returnTypeRef===typeRef && parent.isNonPrivateMemberOf(tClassifier):
				Variance.CO
			N4GetterDeclaration case parent.declaredTypeRef===typeRef && parent.isNonPrivateMemberOf(tClassifier):
				Variance.CO
			N4FieldDeclaration case parent.declaredTypeRef===typeRef && parent.isNonPrivateMemberOf(tClassifier): {
				val tField = parent.definedField;
				if(tField.final) {
					Variance.CO // final field is like a getter
				} else {
					Variance.INV
				}
			}
			ParameterizedTypeRef case
				parent.declaredType instanceof ContainerType<?>
				&& parent.typeArgs.contains(typeRef)
				&& grandParent instanceof N4ClassifierDeclaration
				&& (grandParent as N4ClassifierDeclaration).superClassifierRefs.exists[it===parent]: {
				
				// typeRef is used in the "extends" or "implements" clause of the declaration of tClassifier
				
				// class C<T> extends G<T> {}
				val parentDeclType = parent.declaredType as ContainerType<?>;
				val idx = parent.typeArgs.indexOf(typeRef);
				val matchingTypeVar = if(idx>=0 && idx<parentDeclType.typeVars.size) parentDeclType.typeVars.get(idx) else null;
				if(matchingTypeVar===null) {
					null // error that will be covered by another validation
				} else {
					matchingTypeVar.variance
				}
			}
			default:
				null
		}
	}

	def private static boolean isNonPrivateMemberOf(EObject member, TClassifier tClassifier) {
		if(member instanceof N4MemberDeclaration)
			member?.definedTypeElement?.memberAccessModifier!==MemberAccessModifier.PRIVATE
			&& member.definedTypeElement.containingType === tClassifier
		else
			false
	}


	/**
	 * Tells if the given numeric literal is a Javascript int32.
	 */
	def static boolean isIntLiteral(NumericLiteral numLit) {
		val parent = numLit.eContainer;
		val node = NodeModelUtils.findActualNodeFor(numLit);
		val text = NodeModelUtils.getTokenText(node);
		val result = isIntLiteral(text);
		if(result===2) {
			return parent instanceof UnaryExpression && (parent as UnaryExpression).op===UnaryOperator.NEG;
		}
		return result===1;
	}
	/**
	 * Tells if the given string represents a Javascript int32. Returns 0 if not, 1 if it does, and 2 if the literal
	 * represents a number that is an int32 only if it is negative, but not if it is positive (only for literal
	 * "2147483648" and equivalent literals).
	 * <p>
	 * Some notes:
	 * <ol>
	 * <li>the range of int32 is asymmetric: [ -2147483648, 2147483647 ]
	 * <li>in Java, 1E0 etc. are always of type double, so we follow the same rule below.
	 * <li>hexadecimal and octal literals are always interpreted as positive integers (important difference to Java).
	 * </ol>
	 * See N4JS Specification, Section 8.1.3.1 for details.
	 */
	def static int isIntLiteral(String numLitStr) {
		if(numLitStr===null || numLitStr.length===0) {
			return 0;
		}
		val hasFractionOrExponent = numLitStr.containsOneOf('.','e','E');
		if(hasFractionOrExponent) {
			return 0;
		}
		try {
			val isHex = numLitStr.startsWith("0x") || numLitStr.startsWith("0X");
			val isOct = !isHex && numLitStr.startsWith("0") && numLitStr.length>1 && !numLitStr.containsOneOf('8','9');
			val value = if(isHex) {
				Long.parseLong(numLitStr.substring(2), 16)
			} else if(isOct) {
				Long.parseLong(numLitStr.substring(1), 8)
			} else {
				Long.parseLong(numLitStr) // here we support a leading '+' or '-'
			};
			if(value==2147483648L) { // <-- the one value that is in int32 range if negative, but outside if positive
				return 2;
			}
			if(Integer.MIN_VALUE<=value && value<=Integer.MAX_VALUE) {
				return 1;
			}
			return 0;
		}
		catch(NumberFormatException e) {
			return 0;
		}
	}

	def private static boolean containsOneOf(String str, char... ch) {
		val len = str.length;
		for(var i=0;i<len;i++) {
			val chStr = str.charAt(i);
			for(var j=0;j<ch.length;j++) {
				if(chStr===ch.get(j)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/** Checks presence of {@link AnnotationDefinition#POLYFILL} annotation. See also {@link N4JSLanguageUtils#isStaticPolyfill(AnnotableElement) }*/	
	def static boolean isPolyfill(AnnotableElement astElement) {
		return AnnotationDefinition.POLYFILL.hasAnnotation( astElement );
	}

	/** Checks presence of {@link AnnotationDefinition#STATIC_POLYFILL} annotation. See also {@link N4JSLanguageUtils#isPolyfill(AnnotableElement) }*/	
	def static boolean isStaticPolyfill(AnnotableElement astElement) {
		return AnnotationDefinition.STATIC_POLYFILL.hasAnnotation( astElement );
	}
	
	/** Checks presence of {@link AnnotationDefinition#STATIC_POLYFILL_MODULE} annotation on the containing module. 
	 * See also {@link N4JSLanguageUtils#isContainedInStaticPolyfillAware(AnnotableElement) }*/	
	def static boolean isContainedInStaticPolyfillModule(AnnotableElement astElement) {
		return AnnotationDefinition.STATIC_POLYFILL_MODULE.hasAnnotation( astElement ); // transitively inherited
	}

	/** Checks presence of {@link AnnotationDefinition#STATIC_POLYFILL_MODULE} annotation on the containing module. 
	 * See also {@link N4JSLanguageUtils#isContainedInStaticPolyfillAware(TAnnotableElement) }*/	
	def static boolean isContainedInStaticPolyfillModule(TAnnotableElement tsElement) {
		return AnnotationDefinition.STATIC_POLYFILL_MODULE.hasAnnotation( tsElement ); // transitively inherited
	}

	/** Checks presence of {@link AnnotationDefinition#STATIC_POLYFILL_AWARE} annotation on the containing module. 
	 * See also {@link N4JSLanguageUtils#isContainedInStaticPolyfillModule(AnnotableElement) }*/	
	def static boolean isContainedInStaticPolyfillAware(AnnotableElement astElement) {
		return AnnotationDefinition.STATIC_POLYFILL_AWARE.hasAnnotation( astElement ); // transitively inherited
	}

	/** Checks presence of {@link AnnotationDefinition#STATIC_POLYFILL_AWARE} annotation on the containing module. 
	 * See also {@link N4JSLanguageUtils#isContainedInStaticPolyfillModule(TAnnotableElement) }*/	
	def static boolean isContainedInStaticPolyfillAware(TAnnotableElement tsElement) {
		return AnnotationDefinition.STATIC_POLYFILL_AWARE.hasAnnotation( tsElement ); // transitively inherited
	}
	
}
