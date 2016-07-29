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
package eu.numberfour.n4js.csv;

import org.junit.Test

import static org.junit.Assert.*

/**
 * A few test cases for the CSV parser.
 */
public class CSVParserTest {

	@Test
	public def void testWindowsLineEndings() {
		assertEquals(#[
			#["1", "2", "3"],
			#["1", "2", "3"]
		], 
		new CSVParser("1,2,3\r\n1,2,3").data);

		assertEquals(#[
			#["1", "2", ""],
			#["1", "2", "3"]
		], 
		new CSVParser("1,2,\r\n1,2,3").data);

		assertEquals(#[
			#["1", "2", ""],
			#["", "2", "3"]
		], 
		new CSVParser("1,2,\r\n,2,3").data);

		assertEquals(#[
			#["", "", "Supplier", "Class","",""],
			#["", "", "Subject of Access", "Field, Accessor, Method", "", "Static Field, Getter, Setter, Method", "", ""]
		], 
		new CSVParser(",,Supplier,Class,,\r\n,,Subject of Access,\"Field, Accessor, Method\",,\"Static Field, Getter, Setter, Method\",,\r\n").data);
	}

	@Test
	public def void testEmptyString() {
		var CSVParser parser = new CSVParser("");
		
		assertEquals(#[], parser.data);
	}

	@Test
	public def void testOneEmptyField() {
		var CSVParser parser = new CSVParser("\n");
		
		assertEquals(#[
			#[""]
		], parser.data);
	}

	@Test
	public def void testTwoRowsWithOneFieldEach() {
		var CSVParser parser = new CSVParser("\n\n");
		
		assertEquals(#[
			#[""],
			#[""]
		], parser.data);
	}
	
	@Test
	public def void testOneRowWithThreeFields() {
		var CSVParser parser = new CSVParser('''
		One,Two,Three
		''');

		assertEquals(#[
			#["One", "Two", "Three"]
		], parser.data);
	}
	
	@Test
	public def void testOneRowWithThreeEmptyFields() {
		var CSVParser parser = new CSVParser('''
		,,
		''');

		assertEquals(#[
			#["", "", ""]
		], parser.data);
	}
	
	@Test
	public def void testOneRowWithSomeEmptyFields() {
		assertEquals(#[
			#["One", "", "", ""]
		],
		new CSVParser('''
		One,,,
		''').data);

		assertEquals(#[
			#["", "One", "", ""]
		],
		new CSVParser('''
		,One,,
		''').data);

		assertEquals(#[
			#["", "", "One", ""]
		],
		new CSVParser('''
		,,One,
		''').data);

		assertEquals(#[
			#["", "", "", "One"]
		], 
		new CSVParser('''
		,,,One
		''').data);
		
		assertEquals(#[
			#["One", "", "Two", ""]
		],
		new CSVParser('''
		One,,Two,
		''').data);
		
		assertEquals(#[
			#["One", "", "", "Two"]
		],
		new CSVParser('''
		One,,,Two
		''').data);
		
		assertEquals(#[
			#["", "One", "Two", ""]
		],
		new CSVParser('''
		,One,Two,
		''').data);
	}
	
	@Test
	public def void testSeveralRowsOfSameLength() {
		assertEquals(#[
			#["One", "Two", "Three", "Four"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		One,Two,Three,Four
		Five,Six,Seven,Eight
		''').data);

		assertEquals(#[
			#["", "Two", "Three", ""],
			#["", "Six", "Seven", ""]
		],
		new CSVParser('''
		,Two,Three,
		,Six,Seven,
		''').data);

		assertEquals(#[
			#["One", "Two", "Three", "Four"],
			#["", "", "", ""],
			#["Five", "Six", "Seven", "Eight"],
			#["", "", "", ""]
		],
		new CSVParser('''
		One,Two,Three,Four
		,,,
		Five,Six,Seven,Eight
		,,,
		''').data);
	}
	
	@Test
	public def void testSeveralRowsOfDifferingLengths() {
		assertEquals(#[
			#["One", "Two", "Three"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		One,Two,Three
		Five,Six,Seven,Eight
		''').data);

		assertEquals(#[
			#[""],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''

		Five,Six,Seven,Eight
		''').data);

		assertEquals(#[
			#["Five", "Six", "Seven", "Eight"],
			#[""]
		],
		new CSVParser('''
		Five,Six,Seven,Eight

		''').data);

		assertEquals(#[
			#["Five", "Six", "Seven", "Eight"],
			#[""],
			#["", "", ""]
		],
		new CSVParser('''
		Five,Six,Seven,Eight

		,,
		''').data);
		
		assertEquals(#[
			#["One", "Two", "Three"],
			#[""],
			#["", ""],
			#[""],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		One,Two,Three
		
		,
		
		Five,Six,Seven,Eight
		''').data);
	}

	
	@Test
	public def void testFieldsWithControlChars() {
		assertEquals(#[
			#["One,Two, Three"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		"One,Two, Three"
		Five,Six,Seven,Eight
		''').data);

		assertEquals(#[
			#["One,Two, \n\nThree"],
			#["\n"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		"One,Two, 
		
		Three"
		"
		"
		Five,Six,Seven,Eight
		''').data);

		assertEquals(#[
			#["\"This is a quote!\"", "This isn't", "This is not, either"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		"""This is a quote!""",This isn't,"This is not, either"
		Five,Six,Seven,Eight
		''').data);
	}
}
