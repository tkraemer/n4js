/**
 * Copyright (c) 2017 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.n4mf.utils.content;

import java.util.function.Consumer;

/**
 * Type declaration for lambdas used in factories / builders.
 *
 * Purpose of this interface is to provide type alias for <code>Consumer&lt;ManifestContentData&gt;</code>. This Type
 * alias saves us from having type saves us from issue of "Type safety: Potential heap pollution via varargs parameter
 * dataSetters".
 *
 * @see <a href= "http://docs.oracle.com/javase/specs/jls/se8/html/jls-9.html#jls-9.6.4.7">Safe Varargs</a>
 */
@FunctionalInterface
public interface ManifestDataSetter extends Consumer<ManifestContentData> {
	// just type alias, no changes to the body
}
