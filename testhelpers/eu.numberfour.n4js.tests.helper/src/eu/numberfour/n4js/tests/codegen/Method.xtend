package eu.numberfour.n4js.tests.codegen

import java.util.List

/**
 * Code generator for member methods of a {@link Classifier}.
 */
class Method extends Member {
	/**
	 * A method parameter specification.
	 */
	static class Param {
		String type;
		String name;

		/**
		 * Creates a new instance with the given type and name.
		 *
		 * @param type the parameter type
		 * @param name the parameter name
		 */
		new(String type, String name) {
			this.type = type;
			this.name = name;
		}
	}

	/**
	 * Builder to create an instance of {@link Method}.
	 */
	public static class Builder extends Member.Builder {
		protected String returnType;
		protected List<Param> params = newLinkedList();
		protected String body;

		/**
		 * Creates a new builder for method with the given name.
		 *
		 * @param name the name of the method to be built
		 */
		public new(String name) {
			super(name);
		}

		/**
		 * Sets the return type.
		 *
		 * @param returnType the return type
		 *
		 * @return this builder
		 */
		public def Builder returnType(String returnType) {
			this.returnType = returnType;
			return this;
		}

		/**
		 * Sets the body of the method to be created.
		 *
		 * @param body the body to set
		 *
		 * @return this builder
		 */
		public def Builder body(String body) {
			this.body = body;
			return this;
		}

		/**
		 * Adds a method parameter with the given type and name.
		 *
		 * @param type the parameter type
		 * @param name the parameter name
		 *
		 * @return this builder
		 */
		public def Builder param(String type, String name) {
			params.add(new Param(type, name));
			return this;
		}

		/**
		 * Adds a method parameter.
		 *
		 * @param param the method parameter
		 *
		 * @return this builder
		 */
		public def Builder param(Param param) {
			params.add(param);
			return this;
		}

		/**
		 * Creates a new instance of {@link Method} according to the previously specified parameters.
		 *
		 * @return the newly created method
		 */
		override public def Method build() {
			return new Method(visibility, abstract_, static_, name, override_, returnType, params, body);
		}
	}

	String returnType;
	List<Param> params;
	String body;

	/**
	 * Creates a new instance with the given parameters.
	 *
	 * @param visibility the method's visibility
	 * @param abstract_ whether or not the method is abstract
	 * @param static_ whether or not the method is static
	 * @param name the method name
	 * @param returnType the return type
	 * @param params the list of method parameters
	 * @param body the method's body
	 */
	new(Visibility visibility, Abstract abstract_, Static static_, String name, Override override_, String returnType, List<Param> params, String body) {
		super(visibility, abstract_, static_, name, override_);
		this.returnType = returnType;
		this.params = params;
		this.body = body;
	}

	override protected generateMember() '''
	«generateAbstract()»«name»(«IF params !== null»«FOR p : params»«p.name»: «p.type»«ENDFOR»«ENDIF»)«IF !returnType.nullOrEmpty»: «returnType»«ENDIF»«IF abstract»;«ELSE» «IF !hasBody»{}«ELSE»{
		«IF !body.nullOrEmpty»
		«body»
		«ELSEIF !returnType.nullOrEmpty»
		return new «returnType»()
		«ENDIF»
	}«ENDIF»
	«ENDIF»
	'''

	private def boolean hasBody() {
		!body.nullOrEmpty || !returnType.nullOrEmpty
	}
}
