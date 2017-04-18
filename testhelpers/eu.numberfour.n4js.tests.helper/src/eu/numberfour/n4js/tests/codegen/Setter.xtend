package eu.numberfour.n4js.tests.codegen

/**
 * Generates code for a setter method of a {@link Classifier}.
 */
class Setter extends Member<Setter> {
	protected String fieldType

	/**
	 * Creates a new setter with the given parameters.
	 *
	 * @param name the setter's name
	 */
	public new(String name) {
		super(name)
	}

	/**
	 * Sets the field type of this setter.
	 *
	 * @param fieldType the field type
	 */
	public def Setter setFieldType(String fieldType) {
		this.fieldType = fieldType;
		return this;
	}

	override protected generateMember() '''
	«generateAbstract()»set «name»(value«IF !fieldType.nullOrEmpty»: «fieldType»«ENDIF»)«IF abstract»;«ELSE» {}«ENDIF»
	'''
}
