package eu.numberfour.n4js.tests.codegen

/**
 * Abstract base class for code generators that generate code for members of a {@link Classifier}.
 */
abstract class Member extends Fragment {
	/**
	 * Possible visibilities for members.
	 */
	public static enum Visibility {
		/**
		 * Private visibility.
		 */
		PRIVATE,
		/**
		 * Project visibility.
		 */
		PROJECT,
		/**
		 * Internal protected visibility.
		 */
		PROTECTED_INTERNAL,
		/**
		 * Protected visibility.
		 */
		PROTECTED,
		/**
		 * Internal public visibility.
		 */
		PUBLIC_INTERNAL,
		/**
		 * Public visibility.
		 */
		PUBLIC
	}

	/**
	 * Extensions for the {@link Visibility} enumeration.
	 */
	public static class VisibilityExtensions {
		/**
		 * Builds a member name from the given name and visibility by appending an appropriate string
		 * to the given name.
		 *
		 * @param visibility the visibility value
		 * @param the member name prefix
		 *
		 * @return the newly created member name
		 */
		static def String makeName(Visibility visibility, String memberName) {
			memberName + visibility.nameExtension
		}

		/**
		 * Returns an appropriate member name extension depending on the given visibility.
		 *
		 * @param visibility the visibility value
		 *
		 * @return the name extension
		 */
		static def String getNameExtension(Visibility visibility) {
			switch visibility {
				case PRIVATE: "_private"
				case PROJECT: "_project"
				case PROTECTED_INTERNAL: "_protected_internal"
				case PROTECTED: "_protected"
				case PUBLIC_INTERNAL: "_public_internal"
				case PUBLIC: "_public"
			}
		}

		/**
		 * Builds an appropriate code fragment for the given member visibility.
		 *
		 * @param visibility the visibility value
		 *
		 * @return the code fragment
		 */
		static def String generate(Visibility visibility) {
			switch visibility {
				case PRIVATE: "private"
				case PROJECT: "project"
				case PROTECTED_INTERNAL: "@Internal protected"
				case PROTECTED: "protected"
				case PUBLIC_INTERNAL: "@Internal public"
				case PUBLIC: "public"
			}
		}
	}

	/**
	 * Possible values for whether or not a member is static.
	 */
	public enum Static {
		/**
		 * Static.
		 */
		YES,
		/**
		 * Non-static.
		 */
		NO
	}


	/**
	 * Possible values for whether or not a member overrides an inherited member.
	 */
	static enum Override {
		YES,
		NO
	}

	/**
	 * Extensions for {@link Static} enumeration.
	 */
	public static class StaticExtensions {
		/**
		 * Builds a member name from the given name and static specifier by appending an appropriate string
		 * to the given name.
		 *
		 * @param static_ whether or not the member is static
		 * @param the member name prefix
		 *
		 * @return the newly created member name
		 */
		static def String makeName(Static static_, String classifierName) {
			classifierName + static_.nameExtension
		}

		/**
		 * Returns an appropriate member name extension depending on the given static specification.
		 *
		 * @param static_ whether or not the member is static
		 *
		 * @return the name extension
		 */
		static def String getNameExtension(Static static_) {
			switch static_ {
				case YES: "_static"
				case NO:  ""
			}
		}


		/**
		 * Returns an appropriate code fragment depending on the given static specification.
		 *
		 * @param static_ whether or not the member is static
		 *
		 * @return the code fragment
		 */
		static def String generate(Static static_) {
			switch static_ {
				case YES: "static "
				case NO:  ""
			}
		}
	}

	/**
	 * Abstract base class for builders of subclasses of {@link Member}.
	 *
	 * <b>Default Values</b>
	 * <ul>
	 * <li>Visibility: private</li>
	 * <li>Non-static</li>
	 * <li>Non-overriding</li>
	 * </ul>
	 */
	public static abstract class Builder extends Fragment.Builder {
		protected Visibility visibility = Visibility.PRIVATE;
		protected Static static_ = Static.NO;
		protected Override override_ = Override.NO;
		protected String name;

		/**
		 * Creates a new builder for a member with the given name.
		 *
		 * @param name the name of the member to be built
		 */
		public new(String name) {
			this.name = name;
		}

		/**
		 * Sets visibility to project visible.
		 *
		 * @return this builder
		 */
		public def Builder makeProjectVisible() {
			visibility = Visibility.PROJECT;
			return this;
		}

		/**
		 * Sets visibility to internal protected.
		 *
		 * @return this builder
		 */
		public def Builder makeProtectedInternal() {
			visibility = Visibility.PROTECTED_INTERNAL;
			return this;
		}

		/**
		 * Sets visibility to protected.
		 *
		 * @return this builder
		 */
		public def Builder makeProtected() {
			visibility = Visibility.PROTECTED;
			return this;
		}

		/**
		 * Sets visibility to internal public.
		 *
		 * @return this builder
		 */
		public def Builder makePublicInternal() {
			visibility = Visibility.PUBLIC_INTERNAL;
			return this;
		}

		/**
		 * Sets visibility to public.
		 *
		 * @return this builder
		 */
		public def Builder makePublic() {
			visibility = Visibility.PUBLIC;
			return this;
		}

		/**
		 * Make the member static.
		 *
		 * @return this builder
		 */
		public def Builder makeStatic() {
			static_ = Static.YES;
			return this;
		}

		/**
		 * Make the member an overriding member.
		 */
		public def Builder overrides() {
			override_ = Override.YES;
			return this;
		}

		/**
		 * Creates a new member.
		 *
		 * @return the newly created member
		 */
		public abstract def Member build();
	}

	protected Visibility visibility;
	protected Static static_;
	protected String name;
	protected Override override_

	/**
	 * Creates a new member with the given parameters.
	 *
	 * @param visibility the member's visibility
	 * @param abstract_ whether or not the member is abstract
	 * @param static_ whether or not the member is static
	 * @param name the name of the member
	 */
	protected new(Visibility visibility, Abstract abstract_, Static static_, String name, Override override_) {
		super(abstract_)
		this.visibility = visibility
		this.static_ = static_
		this.name = name
		this.override_ = override_
	}

	/**
	 * Indicates whether this member is static.
	 *
	 * @return <code>true</code> if this member is static and <code>false</code> otherwise
	 */
	protected def boolean isStatic() {
		static_ == Static.YES
	}

	/**
	 * Indicates whether this member overrides an inherited member.
	 *
	 * @return <code>true</code> if this member overrides an inherited mmember and <code>false</code> otherwise
	 */
	protected def boolean overrides() {
		override_ == Override.YES
	}

	override def generate() '''
		«generateOverride()»«generateVisibility()»«generateStatic()»«generateMember()»
	'''

	private def generateOverride() '''«IF overrides()»@Override «ENDIF»'''

	/**
	 * Generates a code fragment for the visibility of this member.
	 *
	 * @return the code fragment
	 */
	private def generateVisibility() '''«VisibilityExtensions.generate(visibility)» '''

	/**
	 * Generates a code fragment according to whether this member is static or not.
	 *
	 * @return the code fragment
	 */
	private def generateStatic() {
		StaticExtensions.generate(static_)
	}

	/**
	 * Abstract method that generates the actual member code.
	 *
	 * @return the generated code fragment
	 */
	protected abstract def CharSequence generateMember()
}
