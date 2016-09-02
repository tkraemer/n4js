package eu.numberfour.n4js.tests.codegen

import java.util.List

/**
 * Generates the code for a class.
 */
class Class extends Classifier<Class> {
	String superClass;
	List<String> implementedInterfaces;

	/**
	 * Creates a new instance with the given parameters.
	 * 
	 * @param name the name of the class
	 */
	public new(String name) {
		super(name)
	}

	/**
	 * Sets the super class.
	 * 
	 * @param superClass the super class or interface.
	 */
	public def Class setSuperClass(Class superClass) {
		return setSuperClass(superClass.name)
	}

	/**
	 * Sets the super class.
	 * 
	 * @param superClass the name of the super class or interface.
	 */
	public def Class setSuperClass(String superClass) {
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
	public def Class addInterface(Interface implementedInterface) {
		return addInterface(implementedInterface.name)
	}

	/**
	 * Adds an interface implemented by the class to be built.
	 * 
	 * @param implementedInterface the interface to implement
	 */
	public def Class addInterface(String implementedInterface) {
		if (implementedInterfaces === null)
			implementedInterfaces = newLinkedList();
		implementedInterfaces.add(implementedInterface);
		return this;
	}

	override protected def generateType() '''class '''

	override protected def CharSequence generateTypeRelations() '''«generateSuperClass()»«generateImplementedInterfaces()»'''
	
	private def CharSequence generateSuperClass() '''«IF !superClass.nullOrEmpty» extends «superClass»«ENDIF»'''
	
	private def CharSequence generateImplementedInterfaces() '''«IF !implementedInterfaces.nullOrEmpty»«FOR i : implementedInterfaces BEFORE ' implements ' SEPARATOR ', '»«i»«ENDFOR»«ENDIF»'''
}
