package eu.numberfour.n4js.tests.codegen

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.nio.file.Path
import java.util.List
import java.util.Objects

/**
 * Generates the code for a project.
 */
public class Project {
	public static enum ProjectType {
		APPLICATION,
		PROCESSOR,
		LIBRARY,
		API,
		RUNTIME_ENVIRONMENT,
		RUNTIME_LIBRARY,
		TEST
	}

	/**
	 * Extension methods for enumeration {@link ProjectType}.
	 */
	public static class ProjectTypeExtensions {
		/**
		 * Returns the generated string for the given value.
		 *
		 * @param projectType the project type
		 *
		 * @return the generated string
		 */
		static def String generate(ProjectType projectType) {
			switch projectType {
				case APPLICATION: "application"
				case PROCESSOR: "processor"
				case LIBRARY: "library"
				case API: "api"
				case RUNTIME_ENVIRONMENT: "runtimeEnvironment"
				case RUNTIME_LIBRARY: "runtimeLibrary"
				case TEST: "test"
			}
		}
	}

	/**
	 * Represents a source folder that has a name and contains modules.
	 */
	public static class SourceFolder {
		/**
		 * A builder for instances of {@link SourceFolder}.
		 */
		public static class Builder {
			private String name;
			private List<Module> modules = newLinkedList();

			/**
			 * Creates a new instance with the given name.
			 *
			 * @param name the name of this source folder
			 */
			public new(String name) {
				this.name = Objects.requireNonNull(name);
			}

			/**
			 * Adds the module built by the given module builder to this source folder.
			 *
			 * @param builder the builder that builds the module to add
			 *
			 * @return this builder
			 */
			public def Builder add(Module.Builder builder) {
				return add(builder.build());
			}

			/**
			 * Adds the given module to the source folder to created.
			 *
			 * @param module the module to add
			 *
			 * @return this source folder
			 */
			public def Builder add(Module module) {
				modules.add(Objects.requireNonNull(module));
				return this;
			}

			/**
			 * Creates a new source folder with the previously configured parameters.
			 *
			 * @return the newly created source folder
			 */
			public def SourceFolder build() {
				return new SourceFolder(name, modules);
			}
		}

		private String name;
		private List<Module> modules

		/**
		 * Creates a new instance with the given parameters.
		 *
		 * @param name the name of this source folder
		 * @param modules the modules contained in this source folder
		 */
		public new(String name, List<Module> modules) {
			this.name = Objects.requireNonNull(name);
			this.modules = Objects.requireNonNull(modules);
		}

		/**
		 * Creates this source folder within the given parent directory, which must exist.
		 *
		 * This method first creates a new folder within the given parent directory, and then
		 * it creates all of its modules within that folder by calling their {@link Module#create(File)}
		 * function with the newly created folder as the parameter.
		 *
		 * @param parentDirectory a file representing the parent directory of this source folder
		 */
		public def create(File parentDirectory) {
			Objects.requireNonNull(parentDirectory);
			if (!parentDirectory.exists)
				throw new IOException("Directory '" + parentDirectory + "' does not exist");
			if (!parentDirectory.directory)
				throw new IOException("'" + parentDirectory + "' is not a directory");

			var File sourceFolder = new File(parentDirectory, name);
			sourceFolder.mkdir();

			for (module: modules)
				module.create(sourceFolder)
		}
	}

	/**
	 * A builder for instances of {@link Project}.
	 */
	public static class Builder {
		String projectId;
		String vendorId;
		String vendorName;
		ProjectType projectType = ProjectType.LIBRARY
		String projectVersion = "1.0.0";
		String outputFolder = "src-gen";
		List<SourceFolder> sourceFolders = newLinkedList();
		List<Project> projectDependencies = newLinkedList();

		/**
		 * Creates a new builder for a project with the given parameters.
		 *
		 * <b>Default values</b>
		 * <ul>
		 * <li>project type: library</li>
		 * <li>project version: 1.0.0</li>
		 * <li>output folder: "src-gen"</li>
		 * </ul>
		 *
		 *
		 * @param projectId the project ID
		 * @param vendorId the vendor ID
		 * @param vendorName the vendor name
		 */
		public new(String projectId, String vendorId, String vendorName) {
			this.projectId = Objects.requireNonNull(projectId);
			this.vendorId = Objects.requireNonNull(vendorId);
			this.vendorName = Objects.requireNonNull(vendorName);
		}

		/**
		 * Sets the type of the project to be built.
		 *
		 * @param projectType the project type
		 *
		 * @return this builder
		 */
		public def Builder type(ProjectType projectType) {
			this.projectType = projectType;
			return this;
		}

		/**
		 * Sets the version of the project to be built.
		 *
		 * @param projectVersion the project version
		 *
		 * @return this builder
		 */
		public def Builder version(String projectVersion) {
			this.projectVersion = Objects.requireNonNull(projectVersion);
			return this;
		}

		/**
		 * Sets the output folder of the project to be built.
		 *
		 * @param outputFolder the output folder
		 *
		 * @return this builder
		 */
		public def Builder output(String outputFolder) {
			this.outputFolder = Objects.requireNonNull(outputFolder);
			return this;
		}

		/**
		 * Adds the source folder built by the given builder to the project to be built.
		 *
		 * @param builder the builder
		 *
		 * @return this builder
		 */
		public def Builder addSourceFolder(SourceFolder.Builder builder) {
			return addSourceFolder(Objects.requireNonNull(builder).build());
		}

		/**
		 * Adds a source folder to the project to be built.
		 *
		 * @param sourceFolder the source folder
		 *
		 * @return this builder
		 */
		public def Builder addSourceFolder(SourceFolder sourceFolder) {
			this.sourceFolders.add(Objects.requireNonNull(sourceFolder))
			return this;
		}

		/**
		 * Adds a project dependency to the project to be built.
		 *
		 * @param project the project that the project to be built shall depend on
		 *
		 * @return this builder
		 */
		public def Builder depends(Project project) {
			this.projectDependencies.add(Objects.requireNonNull(project));
			return this;
		}

		/**
		 * Creates a new project with the previously configured parameters.
		 *
		 * @return the newly created project
		 */
		public def Project build() {
			return new Project(projectId, vendorId, vendorName, projectType, projectVersion, outputFolder,
				sourceFolders, projectDependencies);
		}
	}

	String projectId;
	String vendorId;
	String vendorName;
	ProjectType projectType;
	String projectVersion;
	String outputFolder;
	List<SourceFolder> sourceFolders;
	List<Project> projectDependencies;

	/**
	 * Creates a new instance with the given parameters.
	 *
	 * @param projectId the project ID
	 * @param vendorId the vendor ID
	 * @param vendorName the vendor name
	 * @param projectType the project type
	 * @param projectVersion the project version
	 * @param outputFolder the output folder
	 * @param sourceFolders the source folders
	 * @param projectDependencies the project dependencies
	 */
	public new(String projectId, String vendorId, String vendorName, ProjectType projectType, String projectVersion,
		String outputFolder, List<SourceFolder> sourceFolders, List<Project> projectDependencies) {
		this.projectId = Objects.requireNonNull(projectId);
		this.vendorId = Objects.requireNonNull(vendorId);
		this.vendorName = Objects.requireNonNull(vendorName);
		this.projectType = Objects.requireNonNull(projectType);
		this.projectVersion = Objects.requireNonNull(projectVersion);
		this.outputFolder = Objects.requireNonNull(outputFolder);
		this.sourceFolders = Objects.requireNonNull(sourceFolders);
		this.projectDependencies = Objects.requireNonNull(projectDependencies);
	}

	/**
	 * Generates the N4MF manifest for this project.
	 */
	public def generate() '''
		ProjectId: «projectId»
		VendorId: «vendorId»
		VendorName: "«vendorName»"
		ProjectType: «ProjectTypeExtensions.generate(projectType)»
		ProjectVersion: «projectVersion»
		«IF !outputFolder.empty»Output: "«outputFolder»"«ENDIF»
		«IF !sourceFolders.empty»
			Sources {
				source {
					«FOR sourceFolder: sourceFolders SEPARATOR ','»
						"«sourceFolder.name»"
					«ENDFOR»
				}
			}
		«ENDIF»
	'''

	/**
	 * Creates this project in the given parent directory, which must exist.
	 *
	 * This method first creates a manifest file by means of the {@link #generate} method,
	 * and then it creates each source folder by calling its {@link SourceFolder#create(File))
	 * method.
	 *
	 * @param parentDirectoryPath the path to the parent directory
	 */
	public def create(Path parentDirectoryPath) {
		var File parentDirectory = Objects.requireNonNull(parentDirectoryPath).toFile
		if (!parentDirectory.exists)
			throw new IOException("Directory '" + parentDirectory + "' does not exist");
		if (!parentDirectory.directory)
			throw new IOException("'" + parentDirectory + "' is not a directory");

		createManifest(parentDirectory);
		createModules(parentDirectory);
	}

	private def createManifest(File parentDirectory) {
		val File filePath = new File(parentDirectory, "manifest.n4mf");
		var FileWriter out = null;
		try {
			out = new FileWriter(filePath);
			out.write(generate().toString());
		} finally {
			if (out !== null)
				out.close();
		}
	}

	private def createModules(File parentDirectory) {
		for (sourceFolder: sourceFolders)
			sourceFolder.create(parentDirectory)
	}
}
