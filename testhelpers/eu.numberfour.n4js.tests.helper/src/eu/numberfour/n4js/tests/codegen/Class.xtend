package eu.numberfour.n4js.tests.codegen

import java.util.List

/**
 * Generates the code for a class.
 */
class Class extends Classifier {
	String superClass;
	List<String> implementedInterfaces;

	/**
	 * A builder for instances of {@link Class}.
	 */
	public static class Builder extends Classifier.Builder {
		protected String superClass;
		protected List<String> implementedInterfaces = newLinkedList();

		/**
		 * Creates a new builder for a class with the given name.
		 *
		 * @param name the name of the class to be built
		 */
		public new(String name) {
			super(name);
		}

		/**
		 * Sets the super class.
		 *
		 * @param superClass the name of the super class.
		 *
		 * @return this builder
		 */
		public def Builder extendsClass(String superClass) {
			this.superClass = superClass;
			return this;
		}

		/**
		 * Adds an interface implemented by the class to be built.
		 *
		 * @param implementedInterface the name of the interface to implement
		 *
		 * @return this builder
		 */
		public def Builder implementsInterface(String implementedInterface) {
			implementedInterfaces.add(implementedInterface);
			return this;
		}

		/**
		 * Creates a new instance of {@link Class} with the previously configured parameters.
		 *
		 * @return the newly created class
		 */
		override public def Class build() {
			return new Class(visibility, abstract_, name, superClass, implementedInterfaces, members);
		}
	}

	/**
	 * Creates a new instance with the given parameters.
	 *
	 * @param visibility the class's visibility
	 * @param abstract_ whether or not the class is abstract
	 * @param name the name of the class
	 * @param superClass the super class, <code>null</code> or blank string indicates that there is no super class
	 * @param implementedInterfaces the names of the interfaces implemented by the class
	 * @param members the members of the class
	 */
	new(Visibility visibility, Abstract abstract_, String name, String superClass, List<String> implementedInterfaces, List<Member> members) {
		super(visibility, abstract_, name, members)
		this.superClass = superClass
		this.implementedInterfaces = implementedInterfaces
	}

	override protected def generateType() '''class '''

	override protected def CharSequence generateTypeRelations() '''
	«IF !superClass.nullOrEmpty» extends «superClass»«ENDIF»«FOR i : implementedInterfaces BEFORE ' implements ' SEPARATOR ', '»«i»«ENDFOR»'''
}
