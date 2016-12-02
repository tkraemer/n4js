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
package eu.numberfour.n4jsx.internal;

import org.eclipse.emf.common.util.URI;

import com.google.inject.Singleton;

import eu.numberfour.n4js.internal.FileBasedWorkspace;
import eu.numberfour.n4js.internal.N4JSModel;
import eu.numberfour.n4js.internal.N4JSRuntimeCore;
import eu.numberfour.n4jsx.validation.N4JSXJavaScriptVariantHelper.N4JSXResourceType;

/**
 */
@Singleton
public class N4JSXRuntimeCore extends N4JSRuntimeCore {

	/**
	 * Constructor
	 */
	public N4JSXRuntimeCore(FileBasedWorkspace workspace, N4JSModel model) {
		super(workspace, model);
	}

	/**
	 * Check for raw JS-files
	 *
	 * @param uri
	 *            to test
	 * @boolean if ends in .js or .js.xt
	 */
	@Override
	protected boolean isJsFile(URI uri) {
		N4JSXResourceType resourceType = N4JSXResourceType.getResourceType(uri);
		if (resourceType.equals(N4JSXResourceType.JSX)) {
			return true;
		} else if (resourceType.equals(N4JSXResourceType.N4JSX)) {
			return false;
		}
		return super.isJsFile(uri);
	}
}
