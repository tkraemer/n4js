/**
 * Copyright (c) 2016 NumberFour AG.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   NumberFour AG - Initial API and implementation
 */
package eu.numberfour.n4js.validation

import com.google.inject.Inject
import eu.numberfour.n4js.AnnotationDefinition
import eu.numberfour.n4js.n4JS.FunctionDefinition
import eu.numberfour.n4js.n4JS.NamedElement
import eu.numberfour.n4js.ts.scoping.builtin.BuiltInTypeScope
import eu.numberfour.n4js.ts.typeRefs.ParameterizedTypeRef
import eu.numberfour.n4js.ts.types.IdentifiableElement
import eu.numberfour.n4js.ts.types.TClassifier
import eu.numberfour.n4js.ts.types.TField
import eu.numberfour.n4js.ts.types.TFunction
import eu.numberfour.n4js.ts.types.TInterface
import eu.numberfour.n4js.ts.types.TMember
import eu.numberfour.n4js.ts.types.TMethod
import eu.numberfour.n4js.ts.types.TVariable
import eu.numberfour.n4js.ts.types.TypesPackage
import eu.numberfour.n4js.ts.utils.TypeUtils
import it.xsemantics.runtime.Result
import javax.inject.Singleton
import org.eclipse.emf.ecore.EObject
import org.eclipse.xtext.nodemodel.util.NodeModelUtils

/**
 */
@Singleton
class ValidatorMessageHelper {

	@Inject
	protected extension N4JSElementKeywordProvider;

	/**
	 * Returns type of member and short qualified name, and, if this is similar to another member, including the line number.
	 */
	public def String descriptionDifferentFrom(TMember member, TMember differentMembers) {
		return descriptionDifferentFrom(member, #[differentMembers]);
	}

	/**
	 * Returns type of member and short qualified name, and, if this is similar one of the other members, including the line number.
	 */
	public def String descriptionDifferentFrom(TMember member, Iterable<TMember> otherMembers) {
		if (otherMembers.map[it.containingType.name].toList.contains(member.containingType.name)) {
			val EObject ast = member.eGet(TypesPackage.Literals.SYNTAX_RELATED_TELEMENT__AST_ELEMENT, false) as EObject
			if (null !== ast && !ast.eIsProxy) { // do not trigger automatic resolving here, since it will possibly invalidate TMember-references in our caller
				val node = NodeModelUtils::getNode(ast);
				if (node !== null) {
					val memberLine = node.startLine
					return '''«member.description» (line «memberLine»)'''
				}
			}
		}
		return member.description;
	}

	/**
	 * Returns "static " if member is static, "const " for const fields and an empty strign otherwise;
	 * used in description methods.
	 */
	private def String staticOrConstKeyword(TMember member) {
		if (member !== null && member.static) {
			// exception for 'const' which is tagged static but meaning is implied by 'const' keyword 
			if(member instanceof TField) {
				if( member.isConst ) return "const ";
			}
			return "static ";
		} else {
			return ""
		}
	}

	/**
	 * Returns "static " or "const "-prefixed keyword if member is static, used in description methods.
	 */
	private def String prefixedKeyword(TMember member) {
		return '''«member.staticOrConstKeyword»«member.keyword»''';
	}

	/**
	 * Returns with the keyword and the name of the function definition.
	 */
	public def String description(FunctionDefinition definition) {
		'''«definition.keyword» «definition.name»'''
	}

	/**
	 * Returns keyword and name of a named element; this method is seldom called, usually more concrete methods are used instead.
	 */
	public def String description(NamedElement namedElement) {
		'''«namedElement.keyword» «namedElement.name»'''
	}

	/**
	 * Returns keyword and name of an identifiable element; this method is seldom called, usually more concrete methods are used instead.
	 */
	public def String description(IdentifiableElement identifiableElement) {
		if (identifiableElement === null) {
			return "unknown "
		}
		return '''«identifiableElement.keyword» «identifiableElement.name»'''
	}

	/**
	 * Returns type of member and short qualified name.
	 */
	public def String description(TMember member) {
		if(member.isConstructor) {
			val container = member.containingType;
			val preposition = if(container instanceof TInterface) "in" else "of";
			return '''«member.keyword» «preposition» «container?.description»''';
		}
		return '''«member.prefixedKeyword» «member.shortQualifiedName»''';
	}

	/**
	 * Returns type of member and simple name.
	 */
	public def String shortDescription(TMember member) {
		'''«member.prefixedKeyword» «member.name»'''
	}

	/**
	 * Returns type of member and simple name.
	 */
	public def String shortDescription(TFunction func) {
		'''«func.keyword» «func.name»'''
	}

	/**
	 * Returns type of member and simple name.
	 */
	public def String shortDescription(TVariable tvar) {
		'''«tvar.keyword» «tvar.name»'''
	}

	/**
	 * Returns descriptions, comma separated, last one added with "and".
	 */
	public def String descriptions(Iterable<? extends TMember> members) {
		val TMember last = members.last;
		if (last === null) {
			return "";
		}
		if (last === members.head) {
			return description(last);
		}
		return members.filter[it != last].join('', ', ', '', [description]) + " and " + description(last);
	}

	public def String shortQualifiedName(TMember member) {
		return member.containingType.name + "." + member.name;
	}

	public def String shortQualifiedName(TFunction func) {
		return func.name;
	}

	public def String shortQualifiedName(TVariable tvar) {
		return tvar.name;
	}

	/**
	 * Returns type of classifier and short qualified name.
	 */
	public def String description(TClassifier classifier) {
		'''«classifier.keyword» «classifier.name»'''
	}

	/**
	 * Returns description of "interface" and short qualified name for all interfaces.
	 */
	public def String names(
		Iterable<? extends IdentifiableElement> namedElements) {
		return '''«FOR i : namedElements.filter[it!==namedElements.last] SEPARATOR ',' AFTER " and "»«i.name»«ENDFOR»«namedElements.last.name»'''
	}

	/**
	 * Returns type of member and name of the member with line number in brackets.
	 */
	public def String descriptionWithLine(TMember member) {
		val memberLine = member.astElement.getMemberLine
		'''«member.prefixedKeyword» «member.name»«memberLine»'''
	}

	private def String getMemberLine(EObject element) {
		if (element !== null) {
			val node = NodeModelUtils::getNode(element)
			if (node !== null) {
				return ''' (line «node.startLine»)'''
			}
		}
		return ""
	}

	/**
	 * Returns type of ast element and line number in brackets.
	 */
	public def String descriptionWithLine(EObject eAST) {
		val memberLine = eAST.getMemberLine
		val result = '''«eAST.keyword»«memberLine»'''
		return result.trim
	}

	/**
	 * Returns type and name of ast element and line number in brackets.
	 */
	public def String descriptionWithLine(EObject eAST, String name) {
		val memberLine = eAST.getMemberLine
		'''«eAST.keyword» «name»«memberLine»'''
	}

	/**
	 * Returns type and name of the EObject.
	 */
	public def String description(EObject eAST, String name) {
		'''«eAST.keyword» «name»'''
	}

	/**
	 * Returns verb of redefinition, that is either
	 * "implemented", "consumed", or "overridden", or "inherited".
	 *
	 */
	public def String redefinitionString(TClassifier currentClassifier, TMember overriding, TMember overridden) {
		val overriddenContainer = overridden.containingType
		if (overridden.abstract) { // members of interfaces are always abstract
			return "implemented"
		}
		if (overriddenContainer instanceof TInterface) {
			return "implemented"
		}
		if (overriding.containingType === currentClassifier) {
			return "overridden"
		}
		return "inherited";
	}

	/**
	 * Returns verb of inheritance, that is either
	 * "consumed", or "inherited".
	 *
	 */
	public def String inheritanceString(TClassifier currentClassifier, TMember inheritedMember) {
		val container = inheritedMember.containingType
		if (container instanceof TInterface) {
			return "consumed"
		}
		return "inherited";
	}

	/**
	 * Returns message provided by type system, or empty string if no message is provided.
	 * The returned message is trimmed, that is, preceeding "failed: " is removed and
	 * whitespaces are trimmed.
	 */
	public def String trimTypesystemMessage(Result<?> tsresult) {
		val String msg = tsresult?.ruleFailedException?.message;
		if (msg === null) {
			return "";
		}
		if (msg.startsWith("failed:")) {
			return msg.substring("failed:".length).trim();
		}
		return msg.trim();
	}

	public def String fullFunctionSignature(TFunction tfunction) {
		val StringBuilder strb = new StringBuilder();
		val async = isAsyncOrPromise(tfunction);

		strb.
			append('''«FOR a : tfunction.annotations.filter[it.name!=AnnotationDefinition.INTERNAL.name]»«a.annotationAsString» «ENDFOR»''');
		if (tfunction instanceof TMethod) {
			if (tfunction.final) {
				strb.append("@Final ");
			}
			if (tfunction.memberAccessModifier !== null) {
				strb.append(keyword(tfunction.memberAccessModifier));
				strb.append(" ")
			}
			if (tfunction.abstract) {
				strb.append("abstract ");
			}
		}
		if (!(tfunction instanceof TMethod)) {
			if (tfunction.isAsyncOrPromise) strb.append("async ");
			strb.append("function ")
		}
		if (tfunction.generic) {
			strb.append("<" + tfunction.typeVars.map[typeAsString].join(",") + "> ");
		}

		if (tfunction instanceof TMethod) {
			if (tfunction.isAsyncOrPromise) strb.append("async ");
		}
		strb.append(tfunction.name + "(" + tfunction.fpars.map[formalParameterAsString].join(", ") + ")");
		strb.append(": ");
		appendPromisedReturnType(strb, tfunction, async)
		return strb.toString();
	}

	/**
	 * Returns true if a type ref has been appended.
	 */
	def boolean appendPromisedReturnType(StringBuilder strb, TFunction tfunction, boolean async) {
		if (async) {
			val ptr = tfunction.returnTypeRef as ParameterizedTypeRef;
			val asyncReturnType = ptr.typeArgs.get(0);
			if (asyncReturnType !== null) {
				strb.append(asyncReturnType.typeRefAsString);
				return true;
			}
		} else {
			if (tfunction.returnTypeRef !== null) {
				strb.append(tfunction.returnTypeRef.typeRefAsString);
				return true;
			}
		}
		return false;
	}

	private def boolean isAsyncOrPromise(TFunction tfunction) {
		if (! tfunction.declaredAsync) {
			return false;
		}

		val rs = tfunction.eResource?.resourceSet
		if (rs === null) { // should not happen, but in case of messages the tfunction may be a temp. instance
			return false;
		}
		return TypeUtils.isPromise(tfunction.returnTypeRef, BuiltInTypeScope.get(rs));
	}

}
