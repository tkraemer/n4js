package eu.numberfour.n4jsx.scoping

import com.google.inject.Inject
import eu.numberfour.n4js.scoping.N4JSScopeProvider
import eu.numberfour.n4js.scoping.members.MemberScopingHelper
import eu.numberfour.n4js.scoping.utils.DynamicPseudoScope
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4jsx.helpers.ReactHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXElementName
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

/**
 * This class contains custom scoping for N4JSX language. The main difference compared to N4JSScopeProvider 
 * is that we need to bind JSX property attributes to fields of "props" type 
 * 
 * see : http://www.eclipse.org/Xtext/documentation/latest/xtext.html#scoping
 * on how and when to use it
 */
class N4JSXScopeProvider extends N4JSScopeProvider {
	@Inject MemberScopingHelper memberScopingHelper
	@Inject extension ReactHelper reactHelper;

	@Override
	override getScope(EObject context, EReference reference) {
		if (reference == N4JSXPackage.Literals.JSX_PROPERTY_ATTRIBUTE__PROPERTY) {
			if (context instanceof JSXPropertyAttribute) {
				val jsxElem = (context.eContainer as JSXElement);
				val TypeRef propsTypeRef = jsxElem.getPropsType();

				//TODO check visibility and static??				
				val checkVisibility = false;
				val staticAccess = false;
				if (propsTypeRef !== null) {
					return memberScopingHelper.createMemberScopeFor(propsTypeRef, context,
					checkVisibility, staticAccess);
				} else {
					val scope = super.getScope(context, reference);
					return new DynamicPseudoScope(scope);
				}
			}
		}

		// Otherwise, if we are within a JSX element context but cannot bind JSX element, ignore binding for now
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
