package eu.numberfour.n4js.tests.codegen

/**
 * Generates code for a field of a {@link Classifier}.
 */
class Field extends Member {
	/**
	 * A builder for instances of {@link Field}.
	 */
	public static class Builder extends Member.Builder {
		protected String fieldType;
		protected String defaultValue;

		/**
		 * Creates a new instance of this builder for a field with the given name.
		 *
		 * @param name the name of the field to build
		 */
		public new(String name) {
			super(name);
		}

		/**
		 * Set the field type.
		 *
		 * @param fieldType the field type to set
		 *
		 * @return this builder
		 */
		public def Builder type(String fieldType) {
			this.fieldType = fieldType;
			return this;
		}

		/**
		 * Set the default value or initializer expression.
		 *
		 * @param defaultValue the default value to set
		 *
		 * @return this builder
		 */
		public def Builder defaultValue(String defaultValue) {
			this.defaultValue = defaultValue;
			return this;
		}

		/**
		 * Creates a new instance of {@link Field} with the previously configured values.
		 *
		 * @return the newly created instance
		 */
		public override def Field build() {
			return new Field(visibility, static_, name, override_, fieldType, defaultValue);
		}
	}

	String fieldType
	String defaultValue

	/**
	 * Creates a new field with the given values.
	 *
	 * @param visibility the visibility of this field
	 * @param static_ whether or not this field is static
	 * @param name the name of this field
	 * @param fieldType the field type, may be <code>null</code> or blank to indicate an untyped field
	 * @param defaultValue the default value or initializer expression, may be <code>null</code> or blank to indicate no default value
	 */
	new(Visibility visibility, Static static_, String name, Override override_, String fieldType, String defaultValue) {
		super(visibility, Abstract.NO, static_, name, override_)
		this.fieldType = fieldType
		this.defaultValue = defaultValue
	}

	override protected generateMember() '''
	«IF !fieldType.nullOrEmpty»«fieldType» «ENDIF»«name»«IF !defaultValue.nullOrEmpty» = «defaultValue»«ENDIF»;
	'''
}

