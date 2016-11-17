package eu.numberfour.n4jsx.scoping

import com.google.inject.Inject
import eu.numberfour.n4js.n4JS.IdentifierRef
import eu.numberfour.n4js.scoping.N4JSScopeProvider
import eu.numberfour.n4js.scoping.members.MemberScopingHelper
import eu.numberfour.n4js.scoping.utils.DynamicPseudoScope
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.types.TClass
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.utils.ContainerTypesHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXElementName
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

class N4JSXScopeProvider extends N4JSScopeProvider {

	@Inject
	ContainerTypesHelper containerTypesHelper;

	@Inject N4JSTypeSystem ts
	
	@Inject MemberScopingHelper memberScopingHelper

	@Override
	override getScope(EObject context, EReference reference) {
		if (reference == N4JSXPackage.Literals.JSX_PROPERTY_ATTRIBUTE__PROPERTY) {
			if (context instanceof JSXPropertyAttribute) {
				var expr = (context.eContainer as JSXElement).jsxElementName.expression
				//TODO: Extend this to cover ParameterizedPropertyAccess as well
				if (expr instanceof IdentifierRef) {
					if (expr.id instanceof TClass) {
						var tclass = expr.id as TClass
						val memberCollector = containerTypesHelper.fromContext(tclass)
						val propsFieldT = memberCollector.findMember(tclass, "props", true, false);
						if (propsFieldT instanceof TField) {
							val G = propsFieldT.newRuleEnvironment;
							var typeRefRaw = ts.tau(propsFieldT, TypeUtils.createTypeRef(tclass))
							// take upper bound to get rid of ExistentialTypeRefs, ThisTypeRefs, etc.
							val TypeRef typeRef = if (typeRefRaw !== null) ts.upperBound(G, typeRefRaw).value else null;
							val staticAccess = typeRef instanceof TypeTypeRef;
							//TODO: How can we configure visibility check here?
							val checkVisibility = false;
							return memberScopingHelper.createMemberScopeFor(typeRef, context, checkVisibility,
								staticAccess);
						}
					}
				}
			}
		}

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
