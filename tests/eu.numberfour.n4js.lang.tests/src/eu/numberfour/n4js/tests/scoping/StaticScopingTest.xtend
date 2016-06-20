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
package eu.numberfour.n4js.tests.scoping

import com.google.inject.Inject
import eu.numberfour.n4js.N4JSInjectorProvider
import eu.numberfour.n4js.n4JS.N4JSPackage
import eu.numberfour.n4js.n4JS.NewExpression
import eu.numberfour.n4js.n4JS.ParameterizedPropertyAccessExpression
import eu.numberfour.n4js.n4JS.Script
import eu.numberfour.n4js.n4JS.ThisLiteral
import eu.numberfour.n4js.n4JS.VariableDeclaration
import eu.numberfour.n4js.scoping.members.MemberScope
import eu.numberfour.n4js.typeinference.N4JSTypeInferencer
import eu.numberfour.n4js.ts.typeRefs.ClassifierTypeRef
import eu.numberfour.n4js.ts.typeRefs.ConstructorTypeRef
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.types.TMember
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.eclipse.xtext.junit4.validation.ValidationTestHelper
import org.eclipse.xtext.resource.IEObjectDescription
import org.eclipse.xtext.scoping.IScopeProvider
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import java.util.List
import static org.junit.Assert.*

/**
 * Tests for static scoping, combined with type system test.
 *
 * @see AT_185_ThisScopingTest
 * @see ThisScopingTest
 * @see MemberScope
 * @see <a href="https://numberfour.jira.com/browse/IDE-343">IDE-343</a>
 */
@InjectWith(N4JSInjectorProvider)
@RunWith(XtextRunner)
class StaticScopingTest {

	@Inject extension ParseHelper<Script>
	@Inject extension ValidationTestHelper
	@Inject extension N4JSTypeInferencer
	@Inject extension IScopeProvider scopeProvider

	private static var numberOfInstanceMembersInN4ObjectCache = -1;
	private static var numberOfStaticMembersInN4ObjectCache = -1;
	/** Helper for retrieving number in base classes. */
	private def int numberOfMembersInN4Object(boolean isStatic) {
		if (numberOfInstanceMembersInN4ObjectCache < 0) {
			val script = '''
				class Control extends N4Object {
					x  =1; static y;
					i = (new Control).x;
					static j = new Control.y;

				}
			'''.parse

			val accesses = script.eAllContents.filter(ParameterizedPropertyAccessExpression);
			numberOfInstanceMembersInN4ObjectCache = getPropertyAccessScope(accesses.next).size - 2 // x and i
			numberOfStaticMembersInN4ObjectCache = getPropertyAccessScope(accesses.next).size - 2 // y and j
		}
		if (isStatic) {
			return numberOfStaticMembersInN4ObjectCache;
		} else {
			return numberOfInstanceMembersInN4ObjectCache;
		}
	}

	@Test
	def void testStaticThisAccess() {
		val script = '''
			class A {

				one() {
				}

				two() {
				this.one()
				}

				static one() {
				}

				static two() {
				this.one()
				}
			}
		'''.parse
		val accesses = script.eAllContents.filter(ParameterizedPropertyAccessExpression)

		val nonStaticPropertyAccessExpression = accesses.head
		val nonStaticResult = getScope(nonStaticPropertyAccessExpression,
			N4JSPackage.Literals.PARAMETERIZED_PROPERTY_ACCESS_EXPRESSION__PROPERTY).allElements
		Assert.assertEquals(2, nonStaticResult.size-numberOfMembersInN4Object(false))
		Assert.assertEquals(2, nonStaticResult.map[EObjectOrProxy].filter(TMember).filter[isStatic == false].size-numberOfMembersInN4Object(false))

		val staticPropertyAccessExpression = accesses.last
		val staticResult = getScope(staticPropertyAccessExpression,
			N4JSPackage.Literals.PARAMETERIZED_PROPERTY_ACCESS_EXPRESSION__PROPERTY).allElements
		Assert.assertEquals(2, staticResult.size-numberOfMembersInN4Object(true))
		Assert.assertEquals(2, staticResult.map[EObjectOrProxy].filter(TMember).filter[isStatic == true].size-numberOfMembersInN4Object(true))

		val issues = validate(script)
		Assert.assertEquals(0, issues.size)
	}

	@Test
	def void testStaticMethodClassTypeAccess() {
		val script = '''
			class A {
				one() {
				}
				static two() {
				}
			}
			class B {

				execute() {
					var a: A = null
					a.one
					A.two()
				}
			}
		'''.parse
		val accesses = script.eAllContents.filter(ParameterizedPropertyAccessExpression)

		val nonStaticPropertyAccessExpression = accesses.head
		val nonStaticResult = getPropertyAccessScope(nonStaticPropertyAccessExpression)
		Assert.assertEquals(1, nonStaticResult.size-numberOfMembersInN4Object(false))
		Assert.assertEquals(1, nonStaticResult.map[EObjectOrProxy].filter(TMember).filter[isStatic == false].size-numberOfMembersInN4Object(false))

		val staticPropertyAccessExpression = accesses.last
		val staticResult = getPropertyAccessScope(staticPropertyAccessExpression)
		Assert.assertEquals(1, staticResult.size-numberOfMembersInN4Object(true))
		Assert.assertEquals(1, staticResult.map[EObjectOrProxy].filter(TMember).filter[isStatic == true].size-numberOfMembersInN4Object(true))

		val issues = validate(script)
		Assert.assertEquals(0, issues.size)
	}

	private def List<IEObjectDescription> getPropertyAccessScope(
		ParameterizedPropertyAccessExpression propertyAccessExpression) {
		assertNotNull(propertyAccessExpression);
		val list = getScope(propertyAccessExpression,
			N4JSPackage.Literals.PARAMETERIZED_PROPERTY_ACCESS_EXPRESSION__PROPERTY).allElements.toList();
		return list;
	}



	@Test
	def void testStaticFieldClassTypeAccess() {
		val script = '''
			class A extends N4Object {
				oneField = 1;
				static secondField = 1;
			}
			class B extends N4Object {
				thirdField = (new A).oneField;
				static forthField = A.secondField;
			}
		'''.parse

		val accesses = script.eAllContents.filter(ParameterizedPropertyAccessExpression);

		val nonStaticPropertyAccessExpression = accesses.next
		val nonStaticResult = getPropertyAccessScope(nonStaticPropertyAccessExpression)
		Assert.assertEquals(1, nonStaticResult.size - numberOfMembersInN4Object(false));
		Assert.assertEquals(1,
			nonStaticResult.map[EObjectOrProxy].filter(TMember).filter[isStatic == false].size - numberOfMembersInN4Object(false))

		val staticPropertyAccessExpression = accesses.next
		val staticResult = getPropertyAccessScope(staticPropertyAccessExpression)
		Assert.assertEquals(1, staticResult.size - numberOfMembersInN4Object(true))
		Assert.assertEquals(1,
			staticResult.map[EObjectOrProxy].filter(TMember).filter[isStatic == true].size - numberOfMembersInN4Object(true))

		val issues = validate(script)
		Assert.assertEquals(0, issues.size)
	}

	@Test
	def void testStaticGetterSetterAccess() {
		val script = '''
			class Callee {
			   private static myPrivateStaticField: string = "myPrivateStaticField";
			   private myPrivateNonStaticField: string = "myPrivateNonStaticField";

			   static get myPrivateStaticAccessor() {
					return this.myPrivateStaticField;
					 }

			   static set myPrivateStaticAccessor(myPrivateStaticParam: string) {
					/*this*/Callee.myPrivateStaticField = myPrivateStaticParam;
					 }

			   get myPrivateNonStaticAccessor() {
					return this.myPrivateNonStaticField;
					 }

			   set myPrivateNonStaticAccessor(myPrivateParam: string) {
					this.myPrivateNonStaticField = myPrivateParam;
					 }

			}
			class Caller {

				call() {
					Callee.myPrivateStaticAccessor = "a"
					var a = Callee.myPrivateStaticAccessor

					var callee: Callee = null;
					callee.myPrivateNonStaticAccessor = "a"
					a = callee.myPrivateNonStaticAccessor
				}
			}
		'''.parse

		val issues = validate(script)
		Assert.assertEquals(issues.join(", "), 0, issues.size)
	}

	@Test
	// https://numberfour.jira.com/browse/IDE-528
	def void testStaticTypeInference() {
		val script = '''
			class A {
				static m() {
					return this; // type {A}
				}
				m() {
					return this; // A
				}
			}
		'''.parse

		val issues = validate(script)
		Assert.assertEquals(issues.join(", "), 0, issues.size)

		val thisInMethod1 = script.eAllContents.filter(ThisLiteral).head
		val thisType1 = thisInMethod1.tau
		Assert.assertTrue("expected type{A} but was " + thisType1.class, thisType1 instanceof ClassifierTypeRef)
		val classifierTypeRef1 = thisType1 as ClassifierTypeRef
		val typeName1 = classifierTypeRef1.staticType.name
		Assert.assertEquals("A", typeName1)

		val thisInMethod2 = script.eAllContents.filter(ThisLiteral).last
		val thisType2 = thisInMethod2.tau
		Assert.assertTrue("expected type{A} but was " + thisType2.class, thisType2 instanceof ParameterizedTypeRef)
		val parameterizedTypeRef = thisType2 as ParameterizedTypeRef
		val typeName2 = parameterizedTypeRef.declaredType.name
		Assert.assertEquals("A", typeName2)
	}

	@Test
	def void testTyping() {
		val script = '''
			class C {}
			var x = C
			var c: C;
			var y = c.constructor
			var z1 = new y()
			var z2 = new C()
		'''.parse

		val issues = validate(script)
		Assert.assertEquals(issues.join(", "), 1, issues.size)
		assertEquals("A reference to method constructor is created detached from a (correct) this-instance.", issues.get(0).getMessage())

		val varX = script.eAllContents.filter(VariableDeclaration).filter[name == "x"].head
		val varXType = varX.tau
		Assert.assertTrue("type{C}", varXType instanceof ConstructorTypeRef)
		val varXConstructorTypeRef = varXType as ConstructorTypeRef
		val varXTypeName = varXConstructorTypeRef.staticType.name
		Assert.assertEquals("C", varXTypeName)

		val varC = script.eAllContents.filter(VariableDeclaration).filter[name == "c"].head
		val varCType = varC.tau
		Assert.assertTrue(varCType instanceof ParameterizedTypeRef)
		val varCParameterizedTypeRef = varCType as ParameterizedTypeRef
		val varCTypeName = varCParameterizedTypeRef.declaredType?.name
		Assert.assertEquals("C", varCTypeName)

		val newCExpression = script.eAllContents.filter(NewExpression).last
		val identifierRefType = newCExpression.callee.tau
		Assert.assertTrue("ConstructorTypeRef expected but was " + identifierRefType.class,
			identifierRefType instanceof ConstructorTypeRef)
		val identifierConstructorTypeName = (identifierRefType as ConstructorTypeRef).staticType?.name
		Assert.assertEquals("C", identifierConstructorTypeName)

		val varZ2 = script.eAllContents.filter(VariableDeclaration).filter[name == "z2"].head
		val varZ2Type = varZ2.tau
		Assert.assertTrue("C expected but was " + varZ2Type.class, varZ2Type instanceof ParameterizedTypeRef)
		val varZ2ParameterizedTypeRef = varZ2Type as ParameterizedTypeRef
		val varZ2TypeName = varZ2ParameterizedTypeRef.declaredType?.name
		Assert.assertEquals("C", varZ2TypeName)
	}

	@Test
	@Ignore("Check, if .constructor should return Function or constructor{T} and adapt test accordingly")
	def void testTypingForConstructorProperty() {
		val script = '''
			class C {}
			var C c;
			var y = c.constructor
			var z1 = new y()
		'''.parse

		val issues = validate(script)
		Assert.assertEquals(issues.join(", "), 0, issues.size)

		val varC = script.eAllContents.filter(VariableDeclaration).filter[name == "c"].head
		val varCType = varC.tau
		Assert.assertTrue(varCType instanceof ParameterizedTypeRef)
		val varCParameterizedTypeRef = varCType as ParameterizedTypeRef
		val varCTypeName = varCParameterizedTypeRef.declaredType?.name
		Assert.assertEquals("C", varCTypeName)

		val accesses = script.eAllContents.filter(ParameterizedPropertyAccessExpression)
		val constructor = accesses.head
		Assert.assertEquals("constructor", constructor.property.name)
		val constructorType = constructor.property.tau

		//		Assert.assertTrue("constructor{Y} expected but was " + constructorType.class, constructorType instanceof ConstructorTypeRef)
		//		val constructorTypeRef = constructorType as ConstructorTypeRef
		//		val constructorTypeName = constructorTypeRef.staticType.name
		//		Assert.assertEquals("C", constructorTypeName)
		Assert.assertTrue(constructorType instanceof ParameterizedTypeRef)
		val constructorTypeRef = constructorType as ParameterizedTypeRef
		val constructorTypeName = constructorTypeRef.declaredType?.name
		Assert.assertEquals("Function", constructorTypeName)

		val varY = script.eAllContents.filter(VariableDeclaration).filter[name == "y"].head
		val varYType = varY.tau

		//		Assert.assertTrue("constructor{Y} expected but was " + constructorType.class, varYType instanceof ConstructorTypeRef)
		//		val varYConstructorTypeRef = varCType as ConstructorTypeRef
		Assert.assertTrue(varYType instanceof ParameterizedTypeRef)
		val varYTypeRef = varYType as ParameterizedTypeRef
		val varYTypeName = varYTypeRef.declaredType?.name
		Assert.assertEquals("Function", varYTypeName)

		val varZ1 = script.eAllContents.filter(VariableDeclaration).filter[name == "z1"].head
		val varZ1Type = varZ1.tau
		Assert.assertTrue("constructor{Y} expected but was " + varZ1Type.class,
			varZ1Type instanceof ParameterizedTypeRef)
		val varZ1ParameterizedTypeRef = varZ1Type as ParameterizedTypeRef
		val varZ1TypeName = varZ1ParameterizedTypeRef.declaredType?.name

		//		Assert.assertEquals("C", varZ1TypeName)
		Assert.assertEquals("Function", varZ1TypeName)
	}

	@Test
	@Ignore("Check, if .constructor should return Function or constructor{T} and adapt test accordingly")
	def void testStaticConstructorAccess() {
		val script = '''
			class A {
				one() {
					this.constructor.two()
				}
				static two() {
				}
				three() {
				}
			}
		'''.parse

		val issues = validate(script)
		Assert.assertEquals(issues.join(", "), 0, issues.size)

		val accesses = script.eAllContents.filter(ParameterizedPropertyAccessExpression)

		val constructor = accesses.head
		Assert.assertEquals("constructor", constructor.property.name)

		val staticPropertyAccessExpression = accesses.last
		val staticResult = getScope(staticPropertyAccessExpression,
			N4JSPackage.Literals.PARAMETERIZED_PROPERTY_ACCESS_EXPRESSION__PROPERTY).allElements
		println(staticResult)
		Assert.assertEquals(1, staticResult.size)
		Assert.assertEquals(1, staticResult.map[EObjectOrProxy].filter(TMember).filter[isStatic == true].size)
	}
}
