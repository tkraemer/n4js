package eu.numberfour.n4jsx.scoping

import com.google.inject.Inject
import eu.numberfour.n4js.scoping.N4JSScopeProvider
import eu.numberfour.n4js.scoping.members.MemberScopingHelper
import eu.numberfour.n4js.scoping.utils.DynamicPseudoScope
import eu.numberfour.n4js.ts.typeRefs.FunctionTypeExprOrRef
import eu.numberfour.n4js.ts.typeRefs.TypeRef
import eu.numberfour.n4js.ts.typeRefs.TypeRefsPackage
import eu.numberfour.n4js.ts.typeRefs.TypeTypeRef
import eu.numberfour.n4js.ts.utils.TypeUtils
import eu.numberfour.n4js.typesystem.N4JSTypeSystem
import eu.numberfour.n4js.typesystem.TypeSystemHelper
import eu.numberfour.n4jsx.helpers.ReactLookupHelper
import eu.numberfour.n4jsx.n4JSX.JSXElement
import eu.numberfour.n4jsx.n4JSX.JSXElementName
import eu.numberfour.n4jsx.n4JSX.JSXPropertyAttribute
import eu.numberfour.n4jsx.n4JSX.N4JSXPackage
import org.eclipse.emf.ecore.EObject
import org.eclipse.emf.ecore.EReference

import static extension eu.numberfour.n4js.typesystem.RuleEnvironmentExtensions.*

class N4JSXScopeProvider extends N4JSScopeProvider {

	@Inject N4JSTypeSystem ts
	@Inject TypeSystemHelper tsh
	@Inject MemberScopingHelper memberScopingHelper
	@Inject ReactLookupHelper reactLookupHelper;

	@Override
	override getScope(EObject context, EReference reference) {
		if (reference == N4JSXPackage.Literals.JSX_PROPERTY_ATTRIBUTE__PROPERTY) {
			if (context instanceof JSXPropertyAttribute || context instanceof JSXElement) {
				// Define scoping for attributes of JSX Elements
				var expr = if (context instanceof JSXPropertyAttribute) {
						(context.eContainer as JSXElement).jsxElementName.expression;
					} else {
						(context as JSXElement).jsxElementName.expression;
					}

				val G = expr.newRuleEnvironment;
				val exprResult = ts.type(G, expr);
				val exprTypeRef = exprResult.value;
				if (!exprResult.failed) {
					if (exprTypeRef instanceof TypeTypeRef && (exprTypeRef as TypeTypeRef).constructorRef) {
						// The JSX name is of type class constructor
						val tclass = tsh.getStaticType(G, exprTypeRef as TypeTypeRef);
						val EReference referenceParameterizedTypeRef = TypeRefsPackage.Literals.
							PARAMETERIZED_TYPE_REF__DECLARED_TYPE;
						val tComponentClassifier = reactLookupHelper.lookUpReactClassifier(context,
							referenceParameterizedTypeRef, "Component", "react");
						val reactComponentProps = tComponentClassifier.typeVars.get(0);
						tsh.addSubstitutions(G, TypeUtils.createTypeRef(tclass));
						ts.substTypeVariablesInTypeRef(G, TypeUtils.createTypeRef(reactComponentProps));

						// TODO: How can we configure visibility check here?
						val checkVisibility = false;
						val staticAccess = false;
						val reactComponentPropsTypeRef = G.get(reactComponentProps);
						if (reactComponentPropsTypeRef !== null && (reactComponentPropsTypeRef instanceof TypeRef)) {
							return memberScopingHelper.createMemberScopeFor(G.get(reactComponentProps) as TypeRef,
								context, checkVisibility, staticAccess);
						}
					} else if (exprTypeRef instanceof FunctionTypeExprOrRef) { // TODO: check subtype <: Function is more generic?
					// JSX name is a function expression
						if (exprTypeRef.fpars.length > 0) {
							val tPropsParam = exprTypeRef.fpars.get(0)
							// TODO: How can we configure visibility check here?
							val checkVisibility = false;
							return memberScopingHelper.createMemberScopeFor(tPropsParam.typeRef, context,
								checkVisibility, false);
						}
					} else {
						val scope = super.getScope(context, reference);
						return new DynamicPseudoScope(scope);
					}
				}
			}
		}

		// TODO: Clean up this
		// Ignore binding for JSXElement names and property attributes
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
