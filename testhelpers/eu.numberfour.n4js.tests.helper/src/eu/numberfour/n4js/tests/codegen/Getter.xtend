package eu.numberfour.n4js.tests.codegen

/**
 * Generates code for a getter method of a {@link Classifier}.
 */
class Getter extends Member {
	/**
	 * Builder for instances of {@link Getter}.
	 */
	public static class Builder extends Member.Builder {
		protected String fieldType;
		protected String defaultValue;

		/**
		 * Creates a new builder for a getter with the specified name.
		 *
		 * @param name the name of the getter to build
		 */
		public new(String name) {
			super(name);
		}

		/**
		 * Sets the return type of the getter.
		 *
		 * @param fieldType the return type
		 *
		 * @return this builder
		 */
		public def Builder type(String fieldType) {
			this.fieldType = fieldType;
			return this;
		}

		/**
		 * Sets the default value of the getter.
		 *
		 * @param defaultValue the default value
		 *
		 * @return this builder
		 */
		public def Builder defaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
			return this;
		}

		/**
		 * Creates a new instance of {@link Getter} with the previously configured values.
		 *
		 * @return the newly created getter
		 */
		override public def Getter build() {
			return new Getter(visibility, abstract_, static_, name, override_, fieldType, defaultValue);
		}
	}

	String fieldType
	String defaultValue;

	/**
	 * Creates a new getter with the given parameters.
	 *
	 * @param visibility the visibility of the getter
	 * @param abstract_ whether or not the getter is abstract
	 * @param static_ whether or not the getter is static
	 * @param name the getter's name
	 * @param fieldType the getter's return type
	 * @param defaultValue the getter's default value
	 */
	new(Visibility visibility, Abstract abstract_, Static static_, String name, Override override_, String fieldType, String defaultValue) {
		super(visibility, abstract_, static_, name, override_)
		this.fieldType = fieldType
		this.defaultValue = defaultValue
	}

	override protected generateMember() '''
	«generateAbstract()»get «name»()«IF !fieldType.nullOrEmpty»: «fieldType»«ENDIF»«IF abstract»;«ELSE» { return «IF defaultValue.nullOrEmpty»null«ELSE»defaultValue«ENDIF»; }«ENDIF»
	'''
}