package eu.numberfour.n4js.tests.codegen

/**
 * Generates code for a setter method of a {@link Classifier}.
 */
class Setter extends Member {
	/**
	 * Builder for instances of {@link Setter}.
	 */
	public static class Builder extends Member.Builder {
		protected String fieldType;

		/**
		 * Creates a new builder for a setter with the given name.
		 *
		 * @param name the setter's name
		 */
		public new(String name) {
			super(name);
		}

		/**
		 * Sets the parameter type of the setter.
		 *
		 * @param fieldType the parameter type
		 *
		 * @return this builder
		 */
		public def Builder type(String fieldType) {
			this.fieldType = fieldType;
			return this;
		}

		/**
		 * Creates an instance of {@link Setter} with the previously configured values.
		 */
		override public def Setter build() {
			return new Setter(visibility, abstract_, static_, name, override_, fieldType);
		}
	}

	protected String fieldType

	/**
	 * Creates a new setter with the given parameters.
	 *
	 * @param visibility the setter's visibility
	 * @param abstract_ whether or not the setter is abstract
	 * @param static_ whether or not the setter is static
	 * @param name the setter's name
	 * @param fieldType the setter's parameter type
	 */
	public new(Visibility visibility, Abstract abstract_, Static static_, String name, Override override_, String fieldType) {
		super(visibility, abstract_, static_, name, override_)
		this.fieldType = fieldType
	}

	override protected generateMember() '''
	«generateAbstract()»set «name»(value«IF !fieldType.nullOrEmpty»: «fieldType»«ENDIF»)«IF abstract»;«ELSE» {}«ENDIF»
	'''
}