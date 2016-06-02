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
import eu.numberfour.n4js.utils.JsonPrettyPrinterFactory
import java.io.File
import java.net.URI
import java.util.Collection
import java.util.HashMap
import org.codehaus.jackson.annotate.JsonIgnore
import org.codehaus.jackson.annotate.JsonIgnoreProperties
import org.codehaus.jackson.map.ObjectMapper
import org.codehaus.jackson.map.annotate.JsonSerialize
import org.eclipse.xtend.lib.annotations.Accessors

/**
 * POJO for representing an N4 target platform file.
 *
 * <pre>
 *{
 *  "location" : [
 *    {
 *      "config" : {
 *        "anotherConfigurationKey" : "value",
 *        "someConfigurationKey" : 36
 *      },
 *      "repoType" : "npm",
 *      "projects" : {
 *        "project.without.version.id" : {
 *        },
 *        "another.prject.id" : {
 *          "version" : "1.2.3"
 *        },
 *        "some.project.id" : {
 *          "version" : "0.0.1"
 *        }
 *      }
 *    }
 *  ]
 *}
 * </pre>
 */
@Accessors
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonSerialize(include = NON_NULL)
class TargetPlatformModel {

	/** The file extension (without the '.') of the N4 target platform file */
	public static val TP_EXTENSION = 'n4tp';

	/** The file extension file. Starts with star (*), followed by dot (.) finally the actual extension. */
	public static val TP_FILTER_EXTENSION = '''*.«TP_EXTENSION»''';

	/** The unique file name of the target platform file. (targetplatform.n4tp)*/
	public static val TP_FILE_NAME = '''targetplatform.«TP_EXTENSION»'''

	/**
	 * Creates a new target platform model instance with {@link RepositoryType#npm npm} repository type
	 * and with projects according to the iterable of project identifiers argument.
	 */
	static def createFromNpmProjectIds(Iterable<String> projectIds) {
		return new TargetPlatformModel => [ projectIds.toSet.forEach[id | addNpmDependency(id)] ];
	}

	var Collection<Location> location;

	@Accessors
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonSerialize(include = NON_NULL)
	static class Location {
		var Config config;
		var RepositoryType repoType;
		var Projects projects;
	}

	@Accessors
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonSerialize(include = NON_NULL)
	static class Config extends HashMap<String, Object> {

		new() {
			super(16, 0.75F)
		}

	}

	@Accessors
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonSerialize(include = NON_NULL)
	static class Projects extends HashMap<String, ProjectProperties> {

		new() {
			super(16, 0.75F)
		}

	}

	@Accessors
	@JsonIgnoreProperties(ignoreUnknown = true)
	@JsonSerialize(include = NON_NULL)
	static class ProjectProperties {
		var String version;
	}

	/**
	 * Adds a project with the given project ID as a new npm dependency without version specifier.
	 * Initializes the model if not initialized yet.
	 */
	@JsonIgnore
	def addNpmDependency(String projectId) {
		Preconditions.checkNotNull(projectId, 'projectId');
		addNpmDependency(projectId, null as String);
	}

	/**
	 * Adds a project with the given project ID as a new npm dependency with the given version specifier.
	 * If the version is {@code null}, then no version specifier will be used. Initializes the model if
	 * not initialized yet.
	 */
	@JsonIgnore
	def addNpmDependency(String projectId, String version) {
		Preconditions.checkNotNull(projectId, 'projectId');
		val properties = new ProjectProperties();
		properties.version = version;
		addNpmDependency(projectId, properties);
	}

	/**
	 * Adds a project with the given project ID as a new npm dependency with the given project properties.
	 * If the version is not available in the project properties, or the properties argument is{@code null},
	 * then no version specifier will be used. Initializes the model if not initialized yet.
	 */
	@JsonIgnore
	def addNpmDependency(String projectId, ProjectProperties properties) {
		Preconditions.checkNotNull(projectId, 'projectId');
		if (null === location) {
			location = newArrayList();
		}
		if (!location.exists[repoType === RepositoryType.npm]) {
			location += new Location => [
				config = new Config;
				repoType = RepositoryType.npm;
				projects = new Projects;
			];
		}
		val loc = location.findFirst[repoType === RepositoryType.npm];
		val proj = location.findFirst[repoType === RepositoryType.npm].projects;
		if (null === proj) {
			loc.projects = new Projects;
		}

		loc.projects.put(projectId, properties);
	}
	@Override
	override String toString() {
		try {
			val mapper  = new ObjectMapper(new JsonPrettyPrinterFactory());
			return mapper.writeValueAsString(this);
		} catch (Exception e) {
			throw new RuntimeException('''Error while serializing target platform file: «this»''', e);
		}
	}

	/**
	 * Reads and parses the target platform file content and returns with an assembled
	 * instance representing the content of the file.
	 *
	 * @param location the location of the target platform file.
	 *
	 * @return a new instance representing the content of the parsed target platform file.
	 */
	static def TargetPlatformModel readValue(URI location) {
		try {
			val mapper  = new ObjectMapper(new JsonPrettyPrinterFactory());
			return mapper.readValue(new File(location), TargetPlatformModel);
		} catch (Exception e) {
			throw new RuntimeException('''Error while reading target platform content from «location».''', e);
		}
	}

	static enum RepositoryType {
		npm
	}

}
