package eu.numberfour.n4js.tests.codegen

import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.util.List
import java.util.Map
import java.util.Objects

/**
 * Generates code for a module containing imports and classifiers.
 */
class Module {
	/**
	 * A builder for instances of {@link Module}.
	 */
	static class Builder {
		String name;
		List<Classifier> classifiers = newLinkedList();
		Map<String, List<String>> imports = newLinkedHashMap();

		/**
		 * Creates a new builder for a module with the given name.
		 *
		 * @param name the module name without extension
		 */
		public new(String name) {
			this.name = name;
		}

		/**
		 * Adds the classifier built by the given builder to the module built by this builder.
		 *
		 * @param classifierBuilder the builder for the classifier to be added
		 *
		 * @return this builder
		 */
		public def Builder addClassifier(Classifier.Builder classifierBuilder) {
			return addClassifier(Objects.requireNonNull(classifierBuilder).build())
		}

		/**
		 * Adds the given classifier to the module built by this builder.
		 *
		 * @param classifier the classifier to add
		 *
		 * @return this builder
		 */
		public def Builder addClassifier(Classifier classifier) {
			classifiers.add(Objects.requireNonNull(classifier));
			return this;
		}

		/**
		 * Adds an import to the module built by this builder.
		 *
		 * @param importedType the name of the type to be imported
		 * @param sourceModule the name of the module containing the imported type
		 *
		 * @return this builder
		 */
		public def Builder imports(String importedType, String sourceModule) {
			var List<String> importedTypesForModule = imports.get(Objects.requireNonNull(sourceModule));
			if (importedTypesForModule === null) {
				importedTypesForModule = newLinkedList();
				imports.put(sourceModule, importedTypesForModule);
			}
			importedTypesForModule.add(Objects.requireNonNull(importedType))
			return this;
		}

		/**
		 * Adds an import to the module built by this builder.
		 *
		 * @param importedType the name of the type to be imported
		 * @param sourceModule the module containing the imported type
		 *
		 * @return this builder
		 */
		public def Builder imports(String importedType, Module sourceModule) {
			return imports(importedType, sourceModule.name)
		}

		/**
		 * Adds an import to the module built by this builder.
		 *
		 * @param importedType the classifier representing the type to be imported
		 * @param sourceModule the module containing the imported type
		 *
		 * @return this builder
		 */
		public def Builder imports(Classifier importedType, Module sourceModule) {
			return imports(importedType.name, sourceModule)
		}

		/**
		 * Creates a new instance of {@link Module} with the previously configured parameters.
		 *
		 * @return the newly created module
		 */
		public def Module build() {
			return new Module(name, classifiers, imports)
		}
	}

	String name;
	List<Classifier> classifiers;
	Map<String, List<String>> imports;

	/**
	 * Creates a new instance with the given parameters.
	 *
	 * @param name the module name without extension
	 * @param classifiers the classifiers defined in the module
	 * @param imports the imports
	 */
	public new(String name, List<Classifier> classifiers, Map<String, List<String>> imports) {
		this.name = Objects.requireNonNull(name);
		this.classifiers = classifiers;
		this.imports = imports
	}

	/**
	 * Returns the name of this module.
	 *
	 * @return the name of this module
	 */
	public def String getName() {
		return name
	}

	/**
	 * Creates this module as a file in the given parent directory, which must already exist.
	 *
	 * @param parentDirectory a file representing the parent directory
	 */
	public def create(File parentDirectory) {
		Objects.requireNonNull(parentDirectory);
		if (!parentDirectory.exists)
			throw new IOException("Directory '" + parentDirectory + "' does not exist");
		if (!parentDirectory.directory)
			throw new IOException("'" + parentDirectory + "' is not a directory");

		val File filePath = new File(parentDirectory, this.name + ".n4mf");
		var FileWriter out = null;
		try {
			out = new FileWriter(filePath);
			out.write(generate().toString());
		} finally {
			if (out !== null)
				out.close();
		}
	}

	/**
	 * Generates the N4JS code for this module.
	 */
	public def generate() '''
	«IF hasImports»
	«generateImports()»

	«ENDIF»
	«generateClassifiers()»
	'''

	private def generateImports() '''
	«FOR entry: imports.entrySet()»
	import { «FOR type: entry.value SEPARATOR ', '»«type»«ENDFOR» } from "«entry.key»";
	«ENDFOR»
	'''

	private def generateClassifiers() '''
	«FOR classifier: classifiers»
	«classifier.generate()»
	«ENDFOR»
	'''

	private def boolean hasImports() {
		return !imports.isEmpty();
	}
}