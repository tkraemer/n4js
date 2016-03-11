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
package eu.numberfour.n4js.ts.utils

import com.google.inject.Inject
import eu.numberfour.n4js.ts.TypesInjectorProvider
import eu.numberfour.n4js.ts.types.TypeDefs
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith
import static org.junit.Assert.*;

/**
 */
@RunWith(XtextRunner)
@InjectWith(TypesInjectorProvider)
class TypeUtilsTest {

	@Inject
	extension ParseHelper<TypeDefs>


	@Test
	def void testDeclaredSuperTypesClass() {
		val typeDefs =
		'''
		public class A {}
		public class B extends A{}
		public class C extends A implements R {}
		public class D extends B implements R, I{}
		public class E extends B implements R,S, I,J{}
		public class F implements R,S, I,J{}
		public interface R {}
		public interface S {}
		public interface I {}
		public interface J {}
		'''.parse();

		assertNotNull(typeDefs);

		val A = typeDefs.types.get(0);
		val B = typeDefs.types.get(1);
		val C = typeDefs.types.get(2);
		val D = typeDefs.types.get(3);
		val E = typeDefs.types.get(4);
		val F = typeDefs.types.get(5);

		assertEquals("", TypeUtils.declaredSuperTypes(A).map[typeRefAsString].join(","));
		assertEquals("A", TypeUtils.declaredSuperTypes(B).map[typeRefAsString].join(","));
		assertEquals("A,R", TypeUtils.declaredSuperTypes(C).map[typeRefAsString].join(","));
		assertEquals("B,R,I", TypeUtils.declaredSuperTypes(D).map[typeRefAsString].join(","));
		assertEquals("B,R,S,I,J", TypeUtils.declaredSuperTypes(E).map[typeRefAsString].join(","));
		assertEquals("R,S,I,J", TypeUtils.declaredSuperTypes(F).map[typeRefAsString].join(","));

	}

	@Test
	def void testDeclaredSuperTypesInterfaces() {
		val typeDefs =
		'''
		public interface I {}
		public interface J extends I {}
		public interface L extends I,K {}
		public interface K  {}
		'''.parse();

		assertNotNull(typeDefs);

		val I = typeDefs.types.get(0);
		val J = typeDefs.types.get(1);
		val L = typeDefs.types.get(2);

		assertEquals("", TypeUtils.declaredSuperTypes(I).map[typeRefAsString].join(","));
		assertEquals("I", TypeUtils.declaredSuperTypes(J).map[typeRefAsString].join(","));
		assertEquals("I,K", TypeUtils.declaredSuperTypes(L).map[typeRefAsString].join(","));

	}

	@Test
	def void testDeclaredSuperTypesOthers() {
		val typeDefs =
		'''
			undefined{}
			null{}
			primitive boolean {}
			any{}
			void{}
		'''.parse();

		assertNotNull(typeDefs);

		val undefType = typeDefs.types.get(0);
		val nullType = typeDefs.types.get(1);
		val booleanType = typeDefs.types.get(2);
		val anyType = typeDefs.types.get(2);
		val voidType = typeDefs.types.get(2);

		assertEquals("", TypeUtils.declaredSuperTypes(null).map[typeRefAsString].join(","));
		assertEquals("", TypeUtils.declaredSuperTypes(undefType).map[typeRefAsString].join(","));
		assertEquals("", TypeUtils.declaredSuperTypes(nullType).map[typeRefAsString].join(","));
		assertEquals("", TypeUtils.declaredSuperTypes(booleanType).map[typeRefAsString].join(","));
		assertEquals("", TypeUtils.declaredSuperTypes(anyType).map[typeRefAsString].join(","));
		assertEquals("", TypeUtils.declaredSuperTypes(voidType).map[typeRefAsString].join(","));

	}



}
