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
package eu.numberfour.n4jsx.helpers

import com.google.inject.Inject
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TGetter
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference
import org.eclipse.xtext.EcoreUtil2
import org.eclipse.xtext.naming.IQualifiedNameConverter
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScope
import org.eclipse.xtext.scoping.IScopeProvider
import org.eclipse.xtext.util.IResourceScopeCache

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

/**
 * This helper provides utilities for looking up React definitions such as React.Component or React.Element or
 * for calculating types related to React (e.g. of props property) etc.
 */
class ReactHelper {
	@Inject	protected N4JSTypeSystem ts;
	@Inject protected TypeSystemHelper tsh
	@Inject	IScopeProvider scopeProvider
	@Inject	private IResourceScopeCache resourceScopeCacheHelper;
	@Inject private extension IQualifiedNameConverter qualifierNameConverter;

	public final static String REACT_TYPE = "REACT_TYPE__";
	public final static String REACT_MODULE = "react";	
	public final static String REACT_COMPONENT = "Component";
	public final static String REACT_ELEMENT = "Element";
	
	

	/**
	 * Lookup React component/element type. For increased efficiency, the found results are cached
	 *
	 */
	def public TClassifier lookUpReactClassifier(EObject context, EReference reference, String reactName, String reactModuleName) {
		val String key = REACT_TYPE + reactModuleName + "." + reactName;
		return resourceScopeCacheHelper.get(key, context.eResource, [
			val IScope scope = scopeProvider.getScope(context, reference)
			val IEObjectDescription eod = scope.allElements.findFirst [ e |
				val classifier = e.EObjectOrProxy;
				if (classifier instanceof TClassifier) {
					return reactName == classifier.exportedName &&
						reactModuleName == classifier.containingModule.qualifiedName.toQualifiedName.lastSegment;
				}
				return false;
			]

			if (eod === null)
				return null;

			val classifier = eod.EObjectOrProxy;
			if (classifier.eIsProxy) {
				EcoreUtil2.resolve(classifier, context.eResource)
			}
			return classifier as TClassifier;
		])
	}
	
	
	/**
	 * Calculate the type that an JSX element is binding to, usually class/function type
	 * 
	 * @param jsxElem the input JSX element
	 * @return the typeref that the JSX element is binding to and null if not found
	 */
	def public TypeRef getJSXElementBindingType(JSXElement jsxElem) {
		val expr = jsxElem.jsxElementName.expression;
		val G = expr.newRuleEnvironment;
		val exprResult = ts.type(G, expr);
		if (exprResult.failed) {
			return null;
		} else {
			return exprResult.value;
		}
	}
	
	/**
	 * Calculate the "props" type of an JSX element. It is either the first type parameter of React.Component class or
	 * the type of the first parameter of a functional React component
	 * 
	 * @param jsxElement the input JSX element
	 * @return the typeref if exists and null otherwise
	 */
	def public TypeRef getPropsType(JSXElement jsxElem) {
		val TypeRef exprTypeRef = jsxElem.JSXElementBindingType
		if (exprTypeRef === null)
			return null;
					
		val G = jsxElem.newRuleEnvironment;		
		if (exprTypeRef instanceof TypeTypeRef && (exprTypeRef as TypeTypeRef).constructorRef) {
			// The JSX elements refers to a class
			val tclass = tsh.getStaticType(G, exprTypeRef as TypeTypeRef);
			val EReference referenceParameterizedTypeRef = TypeRefsPackage.Literals.
				PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
			val tComponentClassifier = lookUpReactClassifier(jsxElem,
				referenceParameterizedTypeRef, ReactHelper.REACT_COMPONENT, REACT_MODULE);
			if (tComponentClassifier === null || tComponentClassifier.typeVars.isEmpty) {
				return null;
			}	
			
			val reactComponentProps = tComponentClassifier.typeVars.get(0);
			tsh.addSubstitutions(G, TypeUtils.createTypeRef(tclass));
			ts.substTypeVariablesInTypeRef(G, TypeUtils.createTypeRef(reactComponentProps));

			val reactComponentPropsTypeRef = G.get(reactComponentProps);
			if (reactComponentPropsTypeRef !== null && (reactComponentPropsTypeRef instanceof TypeRef))
				return reactComponentPropsTypeRef as TypeRef;

		} else if (exprTypeRef instanceof FunctionTypeExprOrRef) {
			// The JSX elements refers to a function, assume that the first parameter is props
			if (exprTypeRef.fpars.length > 0) {
				val tPropsParam = exprTypeRef.fpars.get(0);
				return tPropsParam.typeRef
			}
		}
		return null;
	}
	
	/**
	 * Return the type of a field or return type of a getter
	 * 
	 * @param member MUST be either a field or getter
	 */
	def public typeRefOfFieldOrGetter(TMember member) {
		if (member instanceof TField) {
			return member.typeRef;
		} else if (member instanceof TGetter) {
			return member.declaredTypeRef;
		} else {
			throw new IllegalArgumentException(member + " must be either a TField or TGetter");
		}
	}
}
