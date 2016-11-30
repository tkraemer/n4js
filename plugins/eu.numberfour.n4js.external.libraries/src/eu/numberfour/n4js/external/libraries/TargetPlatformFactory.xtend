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
package eu.numberfour.n4js.external.libraries

import com.google.common.base.Preconditions
import com.google.common.base.Strings
import eu.numberfour.n4js.external.libraries.TargetPlatformModel.RepositoryType

/**
 * Factory for {@code package.json} file representing N4 target platform.
 */
class TargetPlatformFactory {

	/**
	 * Creates and returns with the default instance.
	 */
	static def createN4Default() {
		return new PackageJson => [
			name = 'targetplatfrom';
			version = '0.0.1';
			description = 'N4 target platform for N4JS node development';
			main = 'index';
			scripts = newHashMap(
				new Pair('test', '''echo "Error: no test specified" && exit 1''')
			);
			repository = newHashMap(
				new Pair('type', 'git'),
				new Pair('url', 'git+ssh://git@github.numberfour.eu/NumberFour/targetplatform.git')
			);
			keywords = newArrayList('n4', 'n4js');
			author = new Person => [
				name = 'Numberfour AG'
			]
			license = 'ISC'
			bugs = newHashMap(
				new Pair('url', 'https://github.numberfour.eu/NumberFour/targetplatform/issues')
			);
			homepage = 'https://github.numberfour.eu/NumberFour/targetplatform#readme';
		];
	}

	/**
	 * Creates and returns with the default instance with the dependencies specified in the N4 target platform file.
	 */
	static def createN4DefaultWithDependencies(TargetPlatformModel model) {
		Preconditions.checkNotNull(model, 'model');
		val packageJson = createN4Default();
		if (null === packageJson.dependencies) {
			packageJson.dependencies = newHashMap();
		}
		if (!model.location.nullOrEmpty) {
			for (loc : model.location.filter[RepositoryType.npm === repoType]) {
				if (null !== loc.projects) {
					loc.projects.forEach [ projectId, property |
						val version = Strings.nullToEmpty(property?.version);
						packageJson.dependencies.put(projectId, version);
					];
				}
			}
		}
		return packageJson;
	}
}
