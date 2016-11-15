package eu.numberfour.n4jsx.scoping

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.n4JS.N4ClassDeclaration
import eu.numberfour.n4js.scoping.N4JSScopeProvider
import eu.numberfour.n4js.scoping.members.MemberScope.MemberScopeFactory
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

class N4JSXScopeProvider extends N4JSScopeProvider {


	@Inject
	MemberScopeFactory memberScopeFactory


	@Override
	override getScope(EObject context, EReference reference) {
		if (reference == N4JSXPackage.Literals.JSX_PROPERTY_ATTRIBUTE__PROPERTY) {
			if (context instanceof JSXPropertyAttribute) {
				val e = (context.eContainer as JSXElement).jsxElementName.expression as IdentifierRef;
				//val c = e.id as N4ClassDeclaration
				return memberScopeFactory.create(e.id as TClass, context, false);
			}
		}
		return super.getScope(context, reference);
	}
}
