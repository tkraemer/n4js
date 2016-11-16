package eu.numberfour.n4jsx.scoping

import eu.numberfour.n4js.scoping.N4JSScopeProvider
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

class N4JSXScopeProvider extends N4JSScopeProvider {

	@Override
	override getScope(EObject context, EReference reference) {
		//TODO: implement custom scoping for N4JSX here
		return super.getScope(context, reference);
	}
}
