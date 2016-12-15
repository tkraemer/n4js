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
package eu.numberfour.n4js.resource;

/**
 * This interface defines a method for retrieving file extensions that should be allowed for showing generated JS
 * source. This should be implemented for a language or sub-language
 */
public interface AllowedFileExtensionsForGeneratedSourceProvider {
	/**
	 * Return file extensions that are allowed for showing generated JS source
	 */
	Iterable<String> getAllowedFileExtensions();
}
