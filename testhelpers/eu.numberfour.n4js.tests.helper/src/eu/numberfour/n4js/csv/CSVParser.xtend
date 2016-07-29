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

				if (c == CARRIAGE_RETURN) {
					curPos++;
				} else {
					curPos--;
					return readField();
				}
			}

			return new Token(TokenType.EOF, data.length, 0);
		}

		private def Token readField() {
			if (data.substring(curPos, curPos + 1) == "\"")
				return readQuotedField();

			val int startPos = curPos;
			while (!eof()) {
				val char c = data.charAt(curPos);
				if (c == NEWLINE || c == COMMA) {
					return new Token(TokenType.FIELD, startPos, curPos - startPos, data.substring(startPos, curPos))
				}
				curPos++
			}

			return new Token(TokenType.EOF, curPos, 0);
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
			return curPos >= data.length;
		}

		public def void pushToken(Token token) {
			tokenStack.push(token);
		}

		private def Token popToken() {
			return tokenStack.pop();
		}
	}

	private CSVTokenizer tokenizer;

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
		this(new String(Files.readAllBytes(path), encoding))
	}

	/**
	 * Performs the parsing and returns the parsed rows. Note that the parse result is not cached,
	 * so calling this method multiple times will always parse the data passed into the constructor again.
	 * 
	 * @return the parsed rows
	 */
	public def ArrayList<ArrayList<String>> parse() {
		var ArrayList<ArrayList<String>> result = newArrayList();
		tokenizer.reset();

		var Token token = tokenizer.nextToken();
		while (token.type != TokenType.EOF) {
			tokenizer.pushToken(token);
			result.add(parseRow());
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

		if (token.type == TokenType.ROW_SEPARATOR || token.type == TokenType.FIELD_SEPARATOR) {
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
