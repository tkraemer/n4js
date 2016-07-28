package eu.numberfour.n4js.tests.codegen

import java.util.List

/**
 * Generates the code for an interface.
 */
class Interface extends Classifier {
	List<String> extendedInterfaces;

	/**
	 * A builder for instances of {@link Interface}.
	 */
	public static class Builder extends Classifier.Builder {
		protected List<String> extendedInterfaces = newLinkedList();

		/**
		 * Creates a new builder for an interface with the given name.
		 *
		 * @param name the name of the interface to be built
		 */
		public new(String name) {
			super(name);
		}

		/**
		 * Adds a super interface of the interface to be built.
		 *
		 * @param extendedInterface the name of the super interface to add
		 *
		 * @return this builder
		 */
		public def Builder extendsInterface(String extendedInterface) {
			extendedInterfaces.add(extendedInterface);
			return this;
		}

		/**
		 * Creates a new instance of {@link Interface} with the previously configured parameters.
		 *
		 * @return the newly created interface
		 */
		override public def Interface build() {
			return new Interface(visibility, abstract_, name, extendedInterfaces, members);
		}
	}

	/**
	 * Creates a new instance with the given parameters.
	 *
	 * @param visibility the interface's visibility
	 * @param abstract_ whether or not the interface is abstract
	 * @param name the name of the interface
	 * @param extendedInterfaces the names of the super interfaces
	 * @param members the members of the interface
	 */
	new(Visibility visibility, Abstract abstract_, String name, List<String> extendedInterfaces, List<Member> members) {
		super(visibility, abstract_, name, members)
		this.extendedInterfaces = extendedInterfaces
	}

	override protected def generateType() '''interface '''

	override protected def CharSequence generateTypeRelations() '''
	«FOR i : extendedInterfaces BEFORE ' extends ' SEPARATOR ', '»«i»«ENDFOR»
	'''
}