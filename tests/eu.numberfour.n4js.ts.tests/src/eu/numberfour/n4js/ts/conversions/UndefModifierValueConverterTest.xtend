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
package eu.numberfour.n4js.ts.conversions

import com.google.inject.Inject
import eu.numberfour.n4js.ts.TypesInjectorProvider
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TypeDefs
import org.eclipse.xtext.junit4.InjectWith
import org.eclipse.xtext.junit4.XtextRunner
import org.eclipse.xtext.junit4.util.ParseHelper
import org.junit.Test
import org.junit.runner.RunWith

import static org.junit.Assert.*

@RunWith(XtextRunner)
@InjectWith(TypesInjectorProvider)
/**
 */
class UndefModifierValueConverterTest {

	@Inject
	extension ParseHelper<TypeDefs>

	@Test
	def void testParsing() {
		val typedefs = '''
			public class C {
				public na_na: C
				public na_optional: C?
				public na_mandatory: C!
				public notnull_na: C notnull
				public nullable_na: C nullable
				public notnull_optional: C? notnull
				public nullable_optional: C? nullable
				public notnull_mandatory: C! notnull
				public nullable_mandatory: C! nullable
			}
		'''.parse;
		assertNotNull(typedefs);
		assertTrue(typedefs.eResource.errors.toString, typedefs.eResource.errors.empty)

		val fields = typedefs.eAllContents.filter(TField).toList
		assertEquals(9, fields.size)

		for(field: fields) {
			val tr = field.typeRef
			val name = field.name
			val expected = field.name.split("_")
			assertEquals(name, expected.get(0), tr.nullModifier.toString)
			assertEquals(name, expected.get(1), tr.undefModifier.toString)
		}

	}

}
