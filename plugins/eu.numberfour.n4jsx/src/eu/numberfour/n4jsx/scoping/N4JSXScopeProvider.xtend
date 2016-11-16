package eu.numberfour.n4jsx.scoping

import eu.numberfour.n4js.scoping.N4JSScopeProvider
import eu.numberfour.n4js.scoping.utils.DynamicPseudoScope
import eu.numberfour.n4jsx.n4JSX.JSXElementName
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

class N4JSXScopeProvider extends N4JSScopeProvider {

	@Override
	override getScope(EObject context, EReference reference) {
		var JSXElementName jsxElName;
		for (var ei = context; ei !== null; ei = ei.eContainer) {
			if (ei instanceof JSXElementName) {
				jsxElName = ei;
			}
		}

		if (jsxElName !== null) {
			val scope = super.getScope(context, reference);
			return new DynamicPseudoScope(scope);
		}

		return super.getScope(context, reference);
	}
}
