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

import java.util.Iterator
import java.util.List
import org.junit.Test

import static org.junit.Assert.*

/**
 * A few test cases for the CSV parser.
 */
public class CSVParserTest {

	private static def void assertResult(List<List<String>> expected, CSVData actual) {
		var Iterator<List<String>> expectedIt = expected.iterator();
		var Iterator<CSVRecord> actualIt = actual.iterator();
		
		while (expectedIt.hasNext() || actualIt.hasNext()) {
			assertEquals(expectedIt.hasNext(), actualIt.hasNext());
			val List<String> expectedRecord = expectedIt.next();
			val CSVRecord actualRecord = actualIt.next();
			assertRecords(expectedRecord, actualRecord);
		}
	}

	private static def void assertRecords(Iterable<String> expected, Iterable<String> actual) {
		var Iterator<String> expectedIt = expected.iterator();
		var Iterator<String> actualIt = actual.iterator();
		
		while (expectedIt.hasNext() && actualIt.hasNext()) {
			assertEquals(expectedIt.hasNext(), actualIt.hasNext());
			assertEquals(expectedIt.next(), actualIt.next());
		}
	}

	@Test
	public def void testWindowsLineEndings() {
		assertResult(#[
			#["1", "2", "3"],
			#["1", "2", "3"]
		], 
		new CSVParser("1,2,3\r\n1,2,3").data);

		assertResult(#[
			#["1", "2", ""],
			#["1", "2", "3"]
		], 
		new CSVParser("1,2,\r\n1,2,3").data);

		assertResult(#[
			#["1", "2", ""],
			#["", "2", "3"]
		], 
		new CSVParser("1,2,\r\n,2,3").data);

		assertResult(#[
			#["", "", "Supplier", "Class","",""],
			#["", "", "Subject of Access", "Field, Accessor, Method", "", "Static Field, Getter, Setter, Method", "", ""]
		], 
		new CSVParser(",,Supplier,Class,,\r\n,,Subject of Access,\"Field, Accessor, Method\",,\"Static Field, Getter, Setter, Method\",,\r\n").data);
	}

	@Test
	public def void testEmptyString() {
		var CSVParser parser = new CSVParser("");
		
		assertResult(#[], parser.data);
	}

	@Test
	public def void testOneEmptyField() {
		var CSVParser parser = new CSVParser("\n");
		
		assertResult(#[
			#[""]
		], parser.data);
	}

	@Test
	public def void testTwoRowsWithOneFieldEach() {
		var CSVParser parser = new CSVParser("\n\n");
		
		assertResult(#[
			#[""],
			#[""]
		], parser.data);
	}
	
	@Test
	public def void testOneRowWithThreeFields() {
		var CSVParser parser = new CSVParser('''
		One,Two,Three
		''');

		assertResult(#[
			#["One", "Two", "Three"]
		], parser.data);
	}
	
	@Test
	public def void testOneRowWithThreeEmptyFields() {
		var CSVParser parser = new CSVParser('''
		,,
		''');

		assertResult(#[
			#["", "", ""]
		], parser.data);
	}
	
	@Test
	public def void testOneRowWithSomeEmptyFields() {
		assertResult(#[
			#["One", "", "", ""]
		],
		new CSVParser('''
		One,,,
		''').data);

		assertResult(#[
			#["", "One", "", ""]
		],
		new CSVParser('''
		,One,,
		''').data);

		assertResult(#[
			#["", "", "One", ""]
		],
		new CSVParser('''
		,,One,
		''').data);

		assertResult(#[
			#["", "", "", "One"]
		], 
		new CSVParser('''
		,,,One
		''').data);
		
		assertResult(#[
			#["One", "", "Two", ""]
		],
		new CSVParser('''
		One,,Two,
		''').data);
		
		assertResult(#[
			#["One", "", "", "Two"]
		],
		new CSVParser('''
		One,,,Two
		''').data);
		
		assertResult(#[
			#["", "One", "Two", ""]
		],
		new CSVParser('''
		,One,Two,
		''').data);
	}
	
	@Test
	public def void testSeveralRowsOfSameLength() {
		assertResult(#[
			#["One", "Two", "Three", "Four"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		One,Two,Three,Four
		Five,Six,Seven,Eight
		''').data);

		assertResult(#[
			#["", "Two", "Three", ""],
			#["", "Six", "Seven", ""]
		],
		new CSVParser('''
		,Two,Three,
		,Six,Seven,
		''').data);

		assertResult(#[
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
		assertResult(#[
			#["One", "Two", "Three"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		One,Two,Three
		Five,Six,Seven,Eight
		''').data);

		assertResult(#[
			#[""],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''

		Five,Six,Seven,Eight
		''').data);

		assertResult(#[
			#["Five", "Six", "Seven", "Eight"],
			#[""]
		],
		new CSVParser('''
		Five,Six,Seven,Eight

		''').data);

		assertResult(#[
			#["Five", "Six", "Seven", "Eight"],
			#[""],
			#["", "", ""]
		],
		new CSVParser('''
		Five,Six,Seven,Eight

		,,
		''').data);
		
		assertResult(#[
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
		assertResult(#[
			#["One,Two, Three"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		"One,Two, Three"
		Five,Six,Seven,Eight
		''').data);

		assertResult(#[
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

		assertResult(#[
			#["\"This is a quote!\"", "This isn't", "This is not, either"],
			#["Five", "Six", "Seven", "Eight"]
		],
		new CSVParser('''
		"""This is a quote!""",This isn't,"This is not, either"
		Five,Six,Seven,Eight
		''').data);
	}
}
