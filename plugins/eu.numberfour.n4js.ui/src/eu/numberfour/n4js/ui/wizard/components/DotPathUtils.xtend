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
package eu.numberfour.n4js.ui.wizard.components

/**
 * Utility class to handle the dot separated specifiers.
 */
class DotPathUtils {

		/**
		 * Returns all dot separated segments but the last one.
		 *
		 * @param dotSeparatedString
		 *            A dot separated string
		 * @return The front segments
		 */
		public static def String frontDotSegments(String dotSeparatedString) {
			if (dotSeparatedString.nullOrEmpty) {
				return "";
			}
			
			val lastSegment = lastDotSegment(dotSeparatedString);
			if (lastSegment.length == dotSeparatedString.length) {
				return "";
			}
			return dotSeparatedString.substring(0, dotSeparatedString.length() - 1 - lastSegment.length());
		}

		/**
		 * Returns the last dot separated segment.
		 * 
		 * <p>Note: Returns an empty string if no dot is found in the string.</p>
		 *
		 * @param dotSeparatedString
		 *            A dot separated string
		 * @return The last segment
		 */
		public static def String lastDotSegment(String dotSeparatedString) {
			val segments = dotSeparatedString.split("\\.");
			if (segments.length > 0) {
				return segments.last;
			}
			return "";
		}

}
