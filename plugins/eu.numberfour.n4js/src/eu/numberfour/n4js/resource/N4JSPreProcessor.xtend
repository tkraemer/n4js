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
package eu.numberfour.n4js.resource

import com.google.inject.Singleton
import eu.numberfour.n4js.n4JS.Expression
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.PropertyNameOwner
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.StringLiteral
import eu.numberfour.n4js.ts.conversions.ComputedPropertyNameValueConverter
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.validation.ASTStructureValidator
import org.eclipse.emf.ecore.EObject

/**
 * This class performs some early pre-processing of the AST. This happens after {@link N4JSLinker lazy linking} and
 * before {@link ASTStructureValidator AST structure validation}.
 */
@Singleton
package final class N4JSPreProcessor {

	/**
	 * Performs an early processing of the AST, e.g. initialization of transient helper values.
	 * <b>This method assumes that it is allowed to change the AST!</b> Thus, it should be invoked from an "exec without
	 * cache clear" handler, see {@code OnChangeEvictingCache#execWithoutCacheClear(N4JSResource,IUnitOfWork)}.
	 */
	def public void process(Script script, N4JSResource resource) {
		val builtInTypes = BuiltInTypeScope.get(resource.resourceSet);
		for (node : resource.script.eAllContents.toIterable) {
			processNode(node, resource, builtInTypes);
		}
	}

	def private dispatch void processNode(EObject astNode, N4JSResource resource, BuiltInTypeScope builtInTypes) {
		// by default, do nothing
	}

	def private dispatch void processNode(PropertyNameOwner memberDecl, N4JSResource resource, BuiltInTypeScope builtInTypes) {
		val nameDecl = memberDecl.declaredName;
		if(nameDecl!==null && nameDecl.literalName===null && nameDecl.expression!==null) {
			nameDecl.computedName = getPropertyNameFromExpression(nameDecl.expression);
		}
	}
	def private String getPropertyNameFromExpression(Expression expr) {
		if (expr instanceof StringLiteral) {
			return expr.value;
		} else if (expr instanceof ParameterizedPropertyAccessExpression) {
			val target = expr.target;
			if (target instanceof IdentifierRef) {
				if (target.idAsText == 'Symbol') {
					return ComputedPropertyNameValueConverter.SYMBOL_IDENTIFIER_PREFIX + expr.propertyAsText;
				}
			}
		}
		return null;
	}

	/**
	 * Support for new array type syntax:
	 * <pre>
	 * var arr: [string];
	 * </pre>
	 */
	def private dispatch void processNode(ParameterizedTypeRef typeRef, N4JSResource resource, BuiltInTypeScope builtInTypes) {
		if (typeRef.isArrayTypeLiteral) {
			typeRef.declaredType = builtInTypes.arrayType;
		}
	}
}
