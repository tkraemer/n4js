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
package eu.numberfour.n4js.csv

import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.util.ArrayList
import java.util.LinkedList
import java.util.Objects

/**
 * This is a simple CSV (comma-separated values) parser that handles escaping of control characters in fields (commas and newlines)
 * using double quotation marks and escaping double quotation marks as a sequence of two double quotation marks.
 */
class CSVParser {
	private static enum TokenType {
		FIELD,
		FIELD_SEPARATOR,
		ROW_SEPARATOR,
		EOF
	}

	private static class Token {
		TokenType type;
		int offset;
		int length;
		String data;

		new(TokenType type, int offset, int length, String data) {
			this.type = type;
			this.offset = offset;
			this.length = length;
			this.data = data;
		}

		new(TokenType type, int offset, int length) {
			this(type, offset, length, null)
		}

		public def TokenType getType() {
			return type;
		}

		public def int getOffset() {
			return offset;
		}

		override public def String toString() {
			return type + " [" + offset + ", " + length + "]" + if (data !== null) ": " + data else ""
		}
	}

	private static class CSVTokenizer {
		private String data;
		private int curPos;
		private LinkedList<Token> tokenStack = newLinkedList();

		new(String data) {
			this.data = Objects.requireNonNull(data);
		}

		public def void reset() {
			curPos = 0;
			tokenStack.clear();
		}

		private def Token nextToken() {
			if (!tokenStack.isEmpty())
				return popToken();
			return readToken();
		}

		private static final char COMMA = ',';
		private static final char NEWLINE = '\n';
		private static final char CARRIAGE_RETURN = '\r';
		private static final char DOUBLE_QUOTATION_MARK = '"';

		private def Token readToken() {
			while (curPos < data.length) {
				val char c = data.charAt(curPos);
				curPos++;
				if (c == COMMA)
					return new Token(TokenType.FIELD_SEPARATOR, curPos - 1, 1);
				if (c == NEWLINE)
					return new Token(TokenType.ROW_SEPARATOR, curPos - 1, 1);

				if (c != CARRIAGE_RETURN) {
					curPos--;
					return readField();
				}
			}

			return new Token(TokenType.EOF, data.length, 0);
		}

		private def Token readField() {
			if (data.charAt(curPos) == DOUBLE_QUOTATION_MARK)
				return readQuotedField();

			val int startPos = curPos;
			var char c = data.charAt(curPos);
			while (c != CARRIAGE_RETURN && c != NEWLINE && c != COMMA && !eof()) {
				curPos++;
				if (!eof())
					c = data.charAt(curPos);
			} 

			val Token result = new Token(TokenType.FIELD, startPos, curPos - startPos, data.substring(startPos, curPos));
			if (c == CARRIAGE_RETURN)
				curPos++;
			return result;
		}

		private def Token readQuotedField() {
			curPos++;
			val int startPos = curPos;

			var StringBuilder buf = new StringBuilder();

			while (!eof()) {
				val char c = data.charAt(curPos);
				curPos++;
				if (c == DOUBLE_QUOTATION_MARK) {
					if (eof() || data.charAt(curPos) != DOUBLE_QUOTATION_MARK)
						return new Token(TokenType.FIELD, startPos, curPos - startPos - 2, buf.toString());
					// next is also ", skip it
					curPos++;
				}
				buf.append(c);
			}
			return new Token(TokenType.EOF, curPos, 0);
		}

		private def boolean eof() {
			return eof(curPos);
		}

		private def boolean eof(int pos) {
			return pos >= data.length;
		}

		public def void pushToken(Token token) {
			tokenStack.push(token);
		}

		private def Token popToken() {
			return tokenStack.pop();
		}
	}

	private CSVTokenizer tokenizer;
	private ArrayList<ArrayList<String>> cachedData;

	/**
	 * Creates a new parser that parses the given string.
	 * 
	 * @param data the string to parse
	 */
	public new(String data) {
		this.tokenizer = new CSVTokenizer(data);
	}

	/**
	 * Creates a new parser that parses the file at the given location with the given encoding.
	 * 
	 * @param path the path to the file to be parsed
	 * @param encoding the encoding of the file to be parsed
	 * 
	 * @throws IOException if the file cannot be found or cannot be opened
	 */
	public new(Path path, Charset encoding) throws IOException {
		this(new String(Files.readAllBytes(path), encoding));
	}

	/**
	 * Returns all rows and columns and returns them as a nested array list.
	 * 
	 * @return the parsed rows
	 */
	public def ArrayList<ArrayList<String>> getData() {
		if (cachedData === null)
			cachedData = doParse();
		return cachedData;
	}

	/**
	 * Returns all columns of those rows starting at the given zero based index.
	 * 
	 * @param minRow the index of the first row to return
	 * 
	 * @return the parsed rows
	 */
	public def ArrayList<ArrayList<String>> getRowRange(int minRow) {
		return getRowRange(minRow, -1);
	}

	/**
	 * Returns all columns of those rows within the given zero based range.
	 * 
	 * @param minRow the index of the first row to return
	 * @param rowCount the maximum number of rows to return, or -1 to indicate that all rows should be returned
	 * 
	 * @return the parsed rows
	 */
	public def ArrayList<ArrayList<String>> getRowRange(int minRow, int rowCount) {
		return getRange(minRow, rowCount, 0, -1);
	}

	/**
	 * Returns all rows, but only those columns starting at the given zero based index.
	 * 
	 * @param minColumn the index of the first column to return
	 * 
	 * @return the parsed rows
	 */
	public def ArrayList<ArrayList<String>> getColumnRange(int minColumn) {
		return getColumnRange(minColumn, -1);
	}

	/**
	 * Returns all rows, but only those columns within the given zero based  range.
	 * 
	 * @param minColumn the index of the first column to return
	 * @param columnCount the number of columns to return, or -1 to indicate that all columns should be returned
	 * 
	 * @return the parsed rows
	 */
	public def ArrayList<ArrayList<String>> getColumnRange(int minColumn, int columnCount) {
		return getRange(0, -1, minColumn, columnCount);
	}

	/**
	 * Returns all rows and columns and returns only the rows with an index that is
	 * greater or equal to the specified row index (zero based) and only those columns
	 * with an index in the specified zero based  column range.
	 * 
	 * @param minRow the index of the first row to return
	 * @param rowCount the maximum number of rows to return, or -1 to indicate that all rows should be returned
	 * @param minColumn the index of the first column to return
	 * @param columnCount the number of columns to return, or -1 to indicate that all columns should be returned
	 * 
	 * @return the parsed rows
	 */
	public def ArrayList<ArrayList<String>> getRange(int minRow, int rowCount, int minColumn, int columnCount) {
		val ArrayList<ArrayList<String>> source = getData();
		var ArrayList<ArrayList<String>> result = newArrayList();

		for (var int rowIndex = minRow; rowIndex < source.size(); rowIndex++) {
			if (isIndexInRange(rowIndex, minRow, rowCount)) {
				val ArrayList<String> sourceRow = source.get(rowIndex);
				var ArrayList<String> resultRow = newArrayList();
				for (var int columnIndex = minColumn; columnIndex < sourceRow.size(); columnIndex++) {
					if (isIndexInRange(columnIndex, minColumn, columnCount)) {
						resultRow.add(sourceRow.get(columnIndex));
					}
				}
				result.add(resultRow);
			}
		}

		return result;
	}

	private static def boolean isIndexInRange(int index, int minIndex, int count) {
		return index >= minIndex && (count < 0 || index < minIndex + count);
	}

	private def ArrayList<ArrayList<String>> doParse() {
		var ArrayList<ArrayList<String>> result = newArrayList();
		tokenizer.reset();

		var Token token = tokenizer.nextToken();
		while (token.type != TokenType.EOF) {
			tokenizer.pushToken(token);
			result.add(parseRow);
			token = tokenizer.nextToken();
		}

		return result;
	}

	private def ArrayList<String> parseRow() {
		var ArrayList<String> result = new ArrayList<String>();

		var Token token = null;
		do {
			result.add(parseField());
			token = tokenizer.nextToken();
		} while (token.type != TokenType.ROW_SEPARATOR && token.type != TokenType.EOF);

		return result;
	}

	private def String parseField() {
		var Token token = tokenizer.nextToken();
		if (token.type == TokenType.FIELD)
			return token.data;

		if (token.type == TokenType.ROW_SEPARATOR || token.type == TokenType.FIELD_SEPARATOR ||
			token.type == TokenType.EOF) {
			tokenizer.pushToken(token);
			return "";
		}

		unexpectedToken(token);
		return ""; // unreachable		
	}

	private def void unexpectedToken(Token token) {
		throw new RuntimeException("Unexpected token type " + token.type + " at offset " + token.offset);
	}
}
