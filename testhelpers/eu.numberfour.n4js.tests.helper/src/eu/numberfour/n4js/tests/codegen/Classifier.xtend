package eu.numberfour.n4js.tests.codegen

import java.util.List
import java.util.Objects

/**
 * Abstract base class for classifiers.
 */
abstract class Classifier extends Fragment {
	/**
	 * Possible visibility modifiers for classifiers.
	 */
	public static enum Visibility {
		PRIVATE,
		PROJECT,
		PUBLIC_INTERNAL,
		PUBLIC
	}

	/**
	 * Extension methods for the {@link Visibility} enumeration.
	 */
	public static class VisibilityExtensions {
		/**
		 * Builds a classifier name from the given name and visibility by appending an appropriate string
		 * to the given name.
		 *
		 * @param visibility the visibility value
		 * @param the classifier name prefix
		 *
		 * @return the newly created classifier name
		 */
		static def String makeName(Visibility visibility, String classifierName) {
			classifierName + visibility.nameExtension
		}

		/**
		 * Returns an appropriate classifier name extension depending on the given visibility.
		 *
		 * @param visibility the visibility value
		 *
		 * @return the name extension
		 */
		static def String getNameExtension(Visibility visibility) {
			switch visibility {
				case PRIVATE: 			"_private"
				case PROJECT: 			"_project"
				case PUBLIC_INTERNAL:	"_public_internal"
				case PUBLIC: 			"_public"
			}
		}

		/**
		 * Builds an appropriate code fragment for the given classifier visibility.
		 *
		 * @param visibility the visibility value
		 *
		 * @return the code fragment
		 */
		static def String generate(Visibility visibility) {
			switch visibility {
				case PRIVATE: 			"/* private */"
				case PROJECT: 			"export project"
				case PUBLIC_INTERNAL:	"export @Internal public"
				case PUBLIC: 			"export public"
			}
		}
	}

	/**
	 * Abstract base class for classifier builders.
	 *
	 * <b>Default Values</b>
	 * <ul>
	 * <li>visibility: private</li>
	 * </ul>
	 */
	public static abstract class Builder extends Fragment.Builder {
		protected Visibility visibility = Visibility.PRIVATE;
		protected String name;
		protected List<Member> members = newLinkedList()

		/**
		 * Creates a new builder for a classifier with the given name.
		 *
		 * @param the name of the classifier to be built
		 */
		public new(String name) {
			this.name = name;
		}

		/**
		 * Set the visibility to <code>project</code>.
		 *
		 * @return this builder
		 */
		public def Builder makeProjectVisible() {
			visibility = Visibility.PROJECT;
			return this;
		}


		/**
		 * Set the visibility to <code>public @Internal</code>.
		 *
		 * @return this builder
		 */
		public def Builder makePublicInternal() {
			visibility = Visibility.PUBLIC_INTERNAL;
			return this;
		}


		/**
		 * Set the visibility to <code>public</code>.
		 *
		 * @return this builder
		 */
		public def Builder makePublic() {
			visibility = Visibility.PUBLIC;
			return this;
		}

		/**
		 * Add the member built by the given builder to this builder.
		 *
		 * @param member the builder for the member to add
		 *
		 * @return this builder
		 */
		public def Builder addMember(Member.Builder builder) {
			return addMember(Objects.requireNonNull(builder).build());
		}

		/**
		 * Add the given member to this builder.
		 *
		 * @param member the member to add
		 *
		 * @return this builder
		 */
		public def Builder addMember(Member member) {
			members.add(Objects.requireNonNull(member));
			return this;
		}

		/**
		 * Builds the classifier.
		 *
		 * @return the newly created classifier
		 */
		public abstract def Classifier build();
	}

	Visibility visibility
	String name
	List<Member> members

	/**
	 * Creates a new classifier instance.
	 *
	 * @param visibility the visibility of the newly created classifier
	 * @param abstract_ whether or not the classifier should be abstract
	 * @param name the name of the new classifier
	 * @param members the list of members for the newly created classifier
	 */
	new(Visibility visibility, Abstract abstract_, String name, List<Member> members) {
		super(abstract_)
		this.visibility = visibility
		this.name = name
		this.members = members
	}

	/**
	 * Returns the name of this classifier.
	 *
	 * @return the name of this classifier
	 */
	public def String getName() {
		return name;
	}

	override def generate() '''
	«generateVisibility()»«generateAbstract()»«generateType()»«name»«generateTypeRelations()» «IF !hasMembers»{}«ELSE»{
		«generateMembers()»
	}
	«ENDIF»
	'''

	/**
	 * Generate an appropriate code fragment for this classifier's visibility.
	 *
	 * @return the generated visibility code fragment
	 */
	protected def generateVisibility() '''«VisibilityExtensions.generate(visibility)» '''

	/**
	 * Generate the code fragments for each of this classifier's members.
	 *
	 * @return the generated member code fragment
	 */
	protected def generateMembers() '''
	«FOR m : members»
	«m.generate()»
	«ENDFOR»
	'''

	/**
	 * Generates a code fragment for the actual type of this classifier.
	 *
	 * @return the generated code fragment
	 */
	protected abstract def CharSequence generateType()

	/**
	 * Generates a code fragment for the type relations of this classifier, e.g.
	 * its base types or implemented interfaces.
	 */
	protected abstract def CharSequence generateTypeRelations()

	private def boolean hasMembers() {
		!members.empty
	}
}