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
import eu.numberfour.n4js.utils.JsonPrettyPrinterFactory
import java.io.File
import java.net.URI
import java.util.Collection
import java.util.Map
import org.codehaus.jackson.annotate.JsonIgnoreProperties
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.map.annotate.JsonSerialize
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * POJO for the {@code package.json} file.
 */
@Accessors
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = NON_NULL)
class PackageJson {

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
					loc.projects.forEach[projectId, property |
						val version = Strings.nullToEmpty(property?.version);
						packageJson.dependencies.put(projectId, version);
					];
				}
			}
		}
		return packageJson;
	}

	/** The file name with the extension of the {@code package.json} file for npm. */
	public static val String PACKAGE_JSON = 'package.json';

	var Collection<Collection<String>> _args;
	var String _from;
	var String _id;
	var boolean _inCache
	var String _location;
	var Person _npmUser;
	var String _npmVersion;
	var Map<String, Object> _phantomChildren;
	var Map<String, Object> _requested;
	var Collection<String> _requiredBy;
	var String _resolved;
	var String _shasum;
	var String _shrinkwrap;
	var String _spec;
	var String _where;
	var Person author;
	var Map<String, Object> bugs;
	var Collection<Person> contributors;
	var Map<String, String> dependencies;
	var String description;
	var Map<String, String> devDependencies;
	var Map<String, String> directories;
	var Map<String, String> dist;
	var Map<String, String> engines;
	var Collection<String> files;
	var String gitHead;
	var String homepage;
	var boolean installable;
	var Collection<String> keywords;
	var Object license;
	var Collection<Person> maintainers;
	var String name;
	var Map<String, String> optionalDependencies;
	var Map<String, String> repository;
	var Map<String, String> scripts;
	var String version;
	var String main;

	@Accessors
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonSerialize(include = NON_NULL)
	static class Person {
		var String name;
		var String email;
		var String url;
	}

	@Override
	override String toString() {
		try {
			val mapper  = new ObjectMapper(new JsonPrettyPrinterFactory());
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			throw new RuntimeException('''Error while serializing package.json: «this»''', e);
		}
	}

	/**
	 * Reads and parses the {@code package.json} content and returns with an assembled instance
	 * representing the content of the file.
	 *
	 * @param packageLocation the location of the {@code package.json} file.
	 *
	 * @return a new instance representing the content of the parsed file.
	 */
	static def PackageJson readValue(URI packageLocation) {
		try {
			val mapper  = new ObjectMapper(new JsonPrettyPrinterFactory());
			return mapper.readValue(new File(packageLocation), PackageJson);
		} catch (Exception e) {
			throw new RuntimeException('''Error while reading package.json from «packageLocation».''', e);
		}
	}

}
