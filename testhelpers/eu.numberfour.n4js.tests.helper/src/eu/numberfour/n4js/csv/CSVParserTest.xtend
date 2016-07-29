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
	public def void testEmptyString() {
		var CSVParser parser = new CSVParser("");
		
		assertEquals(parser.parse(), #[]);
	}

	@Test
	public def void testOneEmptyField() {
		var CSVParser parser = new CSVParser("\n");
		
		assertEquals(parser.parse(), #[
			#[""]
		]);
	}

	@Test
	public def void testTwoRowsWithOneFieldEach() {
		var CSVParser parser = new CSVParser("\n\n");
		
		assertEquals(parser.parse(), #[
			#[""],
			#[""]
		]);
	}
	
	@Test
	public def void testOneRowWithThreeFields() {
		var CSVParser parser = new CSVParser('''
		One,Two,Three
		''');

		assertEquals(parser.parse(), #[
			#["One", "Two", "Three"]
		]);
	}
	
	@Test
	public def void testOneRowWithThreeEmptyFields() {
		var CSVParser parser = new CSVParser('''
		,,
		''');

		assertEquals(parser.parse(), #[
			#["", "", ""]
		]);
	}
	
	@Test
	public def void testOneRowWithSomeEmptyFields() {
		assertEquals(new CSVParser('''
		One,,,
		''').parse(), #[
			#["One", "", "", ""]
		]);

		assertEquals(new CSVParser('''
		,One,,
		''').parse(), #[
			#["", "One", "", ""]
		]);

		assertEquals(new CSVParser('''
		,,One,
		''').parse(), #[
			#["", "", "One", ""]
		]);

		assertEquals(new CSVParser('''
		,,,One
		''').parse(), #[
			#["", "", "", "One"]
		]);
		
		assertEquals(new CSVParser('''
		One,,Two,
		''').parse(), #[
			#["One", "", "Two", ""]
		]);
		
		assertEquals(new CSVParser('''
		One,,,Two
		''').parse(), #[
			#["One", "", "", "Two"]
		]);
		
		assertEquals(new CSVParser('''
		,One,Two,
		''').parse(), #[
			#["", "One", "Two"]
		]);
	}
	
	@Test
	public def void testSeveralRowsOfSameLength() {
		assertEquals(new CSVParser('''
		One,Two,Three,Four
		Five,Six,Seven,Eight
		''').parse(), #[
			#["One", "Two", "Three", "Four"],
			#["Five", "Six", "Seven", "Eight"]
		]);

		assertEquals(new CSVParser('''
		,Two,Three,
		,Six,Seven,
		''').parse(), #[
			#["", "Two", "Three", ""],
			#["", "Six", "Seven", "d"]
		]);

		assertEquals(new CSVParser('''
		One,Two,Three,Four
		,,,
		Five,Six,Seven,Eight
		,,,
		''').parse(), #[
			#["One", "Two", "Three", "Four"],
			#["", "", "", ""],
			#["Five", "Six", "Seven", "Eight"],
			#["", "", "", ""]
		]);
	}
	
	@Test
	public def void testSeveralRowsOfDifferingLengths() {
		assertEquals(new CSVParser('''
		One,Two,Three
		Five,Six,Seven,Eight
		''').parse(), #[
			#["One", "Two", "Three"],
			#["Five", "Six", "Seven", "Eight"]
		]);

		assertEquals(new CSVParser('''

		Five,Six,Seven,Eight
		''').parse(), #[
			#[""],
			#["Five", "Six", "Seven", "Eight"]
		]);

		assertEquals(new CSVParser('''
		Five,Six,Seven,Eight

		''').parse(), #[
			#["Five", "Six", "Seven", "Eight"],
			#[""]
		]);

		assertEquals(new CSVParser('''
		Five,Six,Seven,Eight

		,,
		''').parse(), #[
			#["Five", "Six", "Seven", "Eight"],
			#[""],
			#["", ""]
		]);
		assertEquals(new CSVParser('''
		One,Two,Three
		
		,
		
		Five,Six,Seven,Eight
		''').parse(), #[
			#["One", "Two", "Three"],
			#[""],
			#["", ""],
			#[""],
			#["Five", "Six", "Seven", "Eight"]
		]);
	}

	
	@Test
	public def void testFieldsWithControlChars() {
		assertEquals(new CSVParser('''
		"One,Two, Three"
		Five,Six,Seven,Eight
		''').parse(), #[
			#["One,Two, Three"],
			#["Five", "Six", "Seven", "Eight"]
		]);

		assertEquals(new CSVParser('''
		"One,Two, 
		
		Three"
		"
		"
		Five,Six,Seven,Eight
		''').parse(), #[
			#["One,Two, \n\nThree"],
			#["\n"],
			#["Five", "Six", "Seven", "Eight"]
		]);

		assertEquals(new CSVParser('''
		"""This is a quote!""",This isn't,"This is not, either"
		Five,Six,Seven,Eight
		''').parse(), #[
			#["\"This is a quote\"", "This isn't", "This is not, either"],
			#["Five", "Six", "Seven", "Eight"]
		]);
	}
}
