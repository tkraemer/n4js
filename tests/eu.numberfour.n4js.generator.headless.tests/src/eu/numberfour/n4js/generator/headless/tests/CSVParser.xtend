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
package eu.numberfour.n4js.generator.headless.tests

import java.io.IOException
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Path
import java.util.ArrayList
import java.util.LinkedList
import java.util.Objects
import java.util.Collections

/**
 * This is a simple CSV (comma-separated values) parser that, according to Torsten and Oliver, could have been 
 * written in three lines of Xtend, so I apologize in advance for this waste of characters ;-).
 */
class CSVParser {
	/**
	 * A CSV row contains a list of fields. Each field is a string.
	 */
	public static class Row implements Iterable<String> {
		private ArrayList<String> fields = newArrayList();

		/**
		 * Adds the given field to this row.
		 * 
		 * @param field the field to add
		 */
		private def void addField(String field) {
			fields.add(Objects.requireNonNull(field));
		}

		/**
		 * Returns the field value at the given index.
		 * 
		 * @param index the index of the field to return
		 */
		public def String get(int index) {
			return fields.get(index);
		}
		
		/**
		 * Returns the length of this row.
		 * 
		 * @return the length of this row
		 */
		public def int length() {
			return fields.size();
		}

		override iterator() {
			return Collections.unmodifiableList(fields).iterator();
		}

		override public def String toString() {
			return fields.toString() + " (" + fields.size() + " fields)";
		}
		
	}

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

		private def Token readToken() {
			while (curPos < data.length) {
				val String c = data.substring(curPos, curPos + 1);
				curPos++;
				if (c == ",")
					return new Token(TokenType.FIELD_SEPARATOR, curPos - 1, 1);
				if (c == "\n")
					return new Token(TokenType.ROW_SEPARATOR, curPos - 1, 1);

				if (c == "\r") {
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
				val String c = data.substring(curPos, curPos + 1);
				switch (c) {
					case "\r",
					case "\n",
					case ",":
						return new Token(TokenType.FIELD, startPos, curPos - startPos, data.substring(startPos, curPos))
					default:
						curPos++
				}
			}

			return new Token(TokenType.EOF, curPos, 0);
		}

		private def Token readQuotedField() {
			curPos++;
			val int startPos = curPos;

			var StringBuilder buf = new StringBuilder();

			while (!eof()) {
				val String c = data.substring(curPos, curPos + 1);
				curPos++;
				if (c == "\"") {
					if (eof() || data.substring(curPos, curPos + 1) != "\"")
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
	public def ArrayList<Row> parse() {
		var ArrayList<Row> result = newArrayList();
		tokenizer.reset();

		var Token token = tokenizer.nextToken();
		while (token.type != TokenType.EOF) {
			tokenizer.pushToken(token);
			result.add(parseRow());
			token = tokenizer.nextToken();
		}

		return result;
	}

	private def Row parseRow() {
		var Row result = new Row();
		
		var Token token = null;
		do {
			result.addField(parseField());
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

	def static void main(String[] args) throws IOException {
		var CSVParser parser = new CSVParser('''
			"1,2,3","""","
			
			""
			
			",
			First,Second,Third,Fourth
			,,,
			,Supplier,Class,
			,
			
			,
		''');
		parser.parse();
	}
}
