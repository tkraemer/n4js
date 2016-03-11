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
package eu.numberfour.n4js.tests.findReferences

import com.google.inject.Inject
import com.google.inject.Provider
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.findReferences.HeadlessReferenceFinder
import eu.numberfour.n4js.n4JS.N4EnumDeclaration
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.VariableStatement
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.resource.XtextResourceSet
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith
import org.eclipse.emf.common.util.URI
import eu.numberfour.n4js.n4JS.ImportDeclaration
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.n4JS.ExportDeclaration
import eu.numberfour.n4js.n4JS.ExportedVariableDeclaration
import eu.numberfour.n4js.n4JS.ExportedVariableStatement
import eu.numberfour.n4js.n4JS.AssignmentExpression
import eu.numberfour.n4js.n4JS.ExpressionStatement
import eu.numberfour.n4js.ts.types.ModuleNamespaceVirtualType

/**
 */
@RunWith(XtextRunner)
@InjectWith(N4JSInjectorProvider)
class HeadlessReferenceFinderTest extends Assert {

	@Inject extension HeadlessReferenceFinder
	@Inject extension ParseHelper<Script>
	@Inject Provider<XtextResourceSet> resourceSetProvider;

	@Test def void test_01() {
		val script = '''
			enum E {
				A , B
			}
			var e: E = E.A
		'''.parse
		val e = script.scriptElements.head as N4EnumDeclaration
		val a = e.literals.head
		val references = a.findReferencesTo
		val resourceSet = script.eResource.resourceSet
		assertEquals(3, references.size)
		val set = references.map[ resourceSet.getEObject(sourceEObjectUri, false) ].toSet
		assertTrue(set.contains(a)) // reference into the type model
		assertTrue(set.contains(a.definedLiteral)) // reference into the ast model
		val varStmt = script.scriptElements.last as VariableStatement
		var varDecl = varStmt.varDecl.head
		var expr = varDecl.expression as ParameterizedPropertyAccessExpression
		assertTrue(set.contains(expr)) // E.A
	}

	@Test def void test_02() {
		val resourceSet = resourceSetProvider.get
		val scriptA = '''
			export class A {
			}
		'''.parse(URI.createURI('a.js'), resourceSet)
		val scriptB = '''
			import * as N from 'a'
			var a: N.A
		'''.parse(URI.createURI('b.js'), resourceSet)
		val aModule = scriptA.module
		val references = aModule.findReferencesTo
		assertEquals(3, references.size)
		val set = references.map[ resourceSet.getEObject(sourceEObjectUri, false) ].toSet
		assertTrue(set.contains(scriptA)) // reference into the type model
		val imp = scriptB.scriptElements.head as ImportDeclaration
		assertTrue(set.contains(imp))
		val namespace = scriptB.module.internalTypes.filter(ModuleNamespaceVirtualType).filter["N".equals(name)].head
		assertTrue(set.contains(namespace)) // reference to target module
	}

	@Test def void test_03() {
		val resourceSet = resourceSetProvider.get
		val scriptA = '''
			export class A {
			}
		'''.parse(URI.createURI('a.js'), resourceSet)
		val scriptB = '''
			import * as N from 'a'
			export var a: N.A
		'''.parse(URI.createURI('b.js'), resourceSet)
		val a = (scriptA.scriptElements.head as ExportDeclaration).exportedElement as N4ClassDeclaration
		val references = a.findReferencesTo
		assertEquals(4, references.size)
		val set = references.map[ resourceSet.getEObject(sourceEObjectUri, false) ].toSet
		assertTrue(set.contains(a))
		assertTrue(set.contains(a.definedType))
		val varStmt = (scriptB.scriptElements.last as ExportDeclaration).exportedElement as ExportedVariableStatement
		val varDecl = varStmt.varDecl.head as ExportedVariableDeclaration
		val typeRef = varDecl.declaredTypeRef
		assertTrue(set.contains(typeRef))
		assertTrue(set.contains(varDecl.definedVariable.typeRef))
	}

	@Test def void test_04() {
		val resourceSet = resourceSetProvider.get
		val scriptA = '''
			export class A {
				get p(): String {}
				set p(newP: String) {}
			}
		'''.parse(URI.createURI('a.js'), resourceSet)
		val scriptB = '''
			import * as N from 'a'
			var a: N.A = null
			a.p = ""
		'''.parse(URI.createURI('b.js'), resourceSet)
		val a = (scriptA.scriptElements.head as ExportDeclaration).exportedElement as N4ClassDeclaration
		val setter = a.ownedSetters.head
		val references = setter.findReferencesTo
		assertEquals(3, references.size)
		val set = references.map[ resourceSet.getEObject(sourceEObjectUri, false) ].toSet
		assertTrue(set.contains(setter))
		assertTrue(set.contains(setter.definedSetter))
		val assignment = (scriptB.scriptElements.last as ExpressionStatement).expression as AssignmentExpression
		val propertyAccess = assignment.lhs as ParameterizedPropertyAccessExpression
		assertTrue(set.contains(propertyAccess))
	}

}
